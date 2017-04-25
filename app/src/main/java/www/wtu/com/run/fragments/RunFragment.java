package www.wtu.com.run.fragments;

import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.text.method.ScrollingMovementMethod;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.baidu.location.BDLocation;
import com.baidu.location.BDLocationListener;
import com.baidu.location.Poi;

import java.util.Date;

import www.wtu.com.run.R;
import www.wtu.com.run.activities.PersonInfoActivity;
import www.wtu.com.run.app.AppContext;
import www.wtu.com.run.beans.RunData;
import www.wtu.com.run.interf.DistanceComputeInterface;
import www.wtu.com.run.interf.impl.DistanceComputeImpl;
import www.wtu.com.run.map.LocationService;
import www.wtu.com.run.utils.Formulary;
import www.wtu.com.run.utils.ToastUtils;

import static www.wtu.com.run.app.AppContext.getApp;

/**
 * Created by Administrator on 2017/4/20.
 * 跑步主界面
 */

public class RunFragment extends BaseFragment implements View.OnClickListener {

    private LocationService locationService;


    private Button btnStart,btnPause,btnStop;
    private TextView tvInfo,tvWarn;
    private LinearLayout llOperator,llWran;

    private String personInfo ;
    private Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what){
                case R.id.tv_info:
                    String info = (String) msg.obj;
                    tvInfo.setText(info);
                    break;
            }
        }
    };




    @Override
    protected int returnLayoutRes() {
        return R.layout.layout_fragment_run;
    }

    @Override
    protected void initComp(View root) {
        llOperator = (LinearLayout) root.findViewById(R.id.ll_operator);
        llWran = (LinearLayout) root.findViewById(R.id.ll_warn);

        tvInfo = (TextView) root.findViewById(R.id.tv_info);
        tvInfo.setMovementMethod(ScrollingMovementMethod.getInstance());
        tvWarn = (TextView) root.findViewById(R.id.tv_warn);
        btnStart = (Button) root.findViewById(R.id.bt_start);
        btnPause = (Button) root.findViewById(R.id.bt_pause);
        btnStop = (Button) root.findViewById(R.id.bt_stop);
    }

    @Override
    protected void bindEvent() {
        llWran.setOnClickListener(this);
        btnStart.setOnClickListener(this);
        btnStop.setOnClickListener(this);
        btnPause.setOnClickListener(this);
    }

    @Override
    protected void initData() {
        int h = getApp().getSharePreferenceInstance().getHeight();
        if(h <= 0){
            llWran.setVisibility(View.VISIBLE);
            llOperator.setVisibility(View.GONE);
        }else{
            llWran.setVisibility(View.GONE);
            llOperator.setVisibility(View.VISIBLE);
            personInfo = getPersonInfo();
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        initData();
    }

    @Override
    public void loadData(String[] params) {

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.ll_warn:
//                ToastUtils.ToastShort("warn");
                Intent intent = new Intent(getActivity(), PersonInfoActivity.class);
                startActivity(intent);
                break;

            case R.id.bt_start:
                ToastUtils.ToastShort("开启定位");
                locationService.start();// 定位SDK
                btnStart.setVisibility(View.GONE);
                btnStop.setVisibility(View.VISIBLE);
                btnPause.setVisibility(View.VISIBLE);
                // start之后会默认发起一次定位请求，开发者无须判断isstart并主动调用request
                break;
            case R.id.bt_pause:
                ToastUtils.ToastShort("暂停定位");
                locationService.stop();

                btnStart.setVisibility(View.VISIBLE);
                btnPause.setVisibility(View.GONE);
                btnStop.setVisibility(View.VISIBLE);
                break;
            case R.id.bt_stop:
                ToastUtils.ToastShort("结束行程");
                locationService.stop();

//                保存此次数据
                RunData runData = new RunData();
                runData.setUsername(String.valueOf(AppContext.getApp().getSharePreferenceInstance().getUsername()));
                runData.setBushu(String.valueOf(Math.floor(this.bushuSum)));
                runData.setDistance(String.valueOf(Math.floor(this.distanceSum)));
                runData.setDate(new Date().getTime());
                runData.setKll(String.valueOf(Math.floor(this.hotSum)));
                runData.setTime(String.valueOf(this.timeSum));
                Log.i("info", "onClick: "+runData.toString());
                AppContext.getApp().getDaoSession().getRunDataDao().save(runData);

                //保存总时间
                AppContext.getApp().getSharePreferenceInstance().setTime(
                        (AppContext.getApp().getSharePreferenceInstance().getTime()+this.timeSum)
                );

                //保存总消耗
                AppContext.getApp().getSharePreferenceInstance().setHotSum(
                        AppContext.getApp().getSharePreferenceInstance().getHotSum()+this.hotSum
                );

                ToastUtils.ToastShort("数据保存成功");

                initLocData();


                btnStart.setVisibility(View.VISIBLE);
                btnStop.setVisibility(View.GONE);
                btnPause.setVisibility(View.GONE);
                break;
        }
    }

    @Override
    public void onStart() {
        super.onStart();

        // -----------location config ------------
        locationService = getApp().locationService;
        //获取locationservice实例，建议应用中只初始化1个location实例，然后使用，可以参考其他示例的activity，都是通过此种方式获取locationservice实例的
        locationService.registerListener(mListener);

        //注册监听
        int type = getActivity().getIntent().getIntExtra("from", 0);
        if (type == 0) {
            locationService.setLocationOption(locationService.getDefaultLocationClientOption());
        } else if (type == 1) {
            locationService.setLocationOption(locationService.getOption());
        }

    }

    @Override
    public void onStop() {
        super.onStop();
        locationService.unregisterListener(mListener); //注销掉监听
        locationService.stop(); //停止定位服务
    }

    /**
     * 显示请求字符串
     *
     * @param str
     */
    public void logMsg(String str) {
        Message message = Message.obtain();
        message.obj = str;
        message.what = R.id.tv_info;
        handler.sendMessage(message);

        Log.i("info", "logMsg: "+str);
    }


    private volatile double preLatitude = 0.0f;       //定位数据
    private volatile double preLongitude = 0.0f;
    private boolean flag = true;
    private float distanceSum = 0.0f; //总距离；
    private int bushuSum = 0; //总步数
    private long timeSum = 0; //总时间
    private float hotSum = 0.0f; //总热量

    private int height = 0;
    private int weight = 0;
    private float bc = 0;
    /*****
     *
     * 定位结果回调，重写onReceiveLocation方法，可以直接拷贝如下代码到自己工程中修改
     *
     */
    private BDLocationListener mListener = new BDLocationListener() {

        @Override
        public void onReceiveLocation(BDLocation location) {
            // TODO Auto-generated method stub
            if (null != location && location.getLocType() != BDLocation.TypeServerError) {
                if(flag){  //第一次
                    initLocData(); //初始化数据
                    flag = false;
                    preLatitude = location.getLatitude();
                    preLongitude = location.getLongitude();
                    printLocInfo(location,new String[]{
                            personInfo,
                            "正在获取定位....."
                    });
                }else{
                    /** 计算距离  */
                    DistanceComputeInterface distanceComputeInterface = DistanceComputeImpl.getInstance();
                    float distance = (float) distanceComputeInterface.getLongDistance(preLatitude,preLongitude,location.getLatitude(),location.getLongitude());
                    timeSum += 3; //每三秒更新一次
                    distance += 1.543; //后台模拟
                    if (!noMove(distance)) {                //是否在移动
                        distanceSum += distance;
                        bushuSum += (int ) (distance / bc);

                        hotSum += weight * (distanceSum / 1000 )* 1.036;
                        printLocInfo(location,new String[]{
                                personInfo,
                                "移动状态：正在移动",
                                "当前移动距离："+distance +"米",
                                "总移动距离："+Math.floor(distanceSum)+"米",
                                "行走步数:"+bushuSum+"步",
                                "总热量消耗:"+Math.floor(hotSum)+"焦耳",
                                "总时间:"+timeSum+"秒",
                        });
                    }else{
                        printLocInfo(location,new String[]{
                                personInfo,
                                "移动状态：未发生移动或移动距离太小",
                                "总移动距离："+Math.floor(distanceSum)+"米",
                                "行走步数:"+bushuSum+"步",
                                "总热量消耗:"+Math.floor(hotSum)+"焦耳",
                                "总时间:"+timeSum+"秒",
                        });
                    }
                    //更新上一次的值
                    preLatitude = location.getLatitude();
                    preLongitude = location.getLongitude();
                }
            }else{
                logMsg("定位获取失败");
            }
        }

        /**
         * 打印信息
         * @param location
         */
        private void printLocInfo(BDLocation location,String contents[]) {
            StringBuffer sb = new StringBuffer();
            StringBuffer sb2 = new StringBuffer();
            for (String s :contents){
                sb2.append(s+"\n");
            }
            sb.append(sb2.toString()+"\n\n");
            sb.append("定位状态 : ");
            sb.append("\ntime : ");
            /**
             * 时间也可以使用systemClock.elapsedRealtime()方法 获取的是自从开机以来，每次回调的时间；
             * location.getTime() 是指服务端出本次结果的时间，如果位置不发生变化，则时间不变
             */
            sb.append(location.getTime());
            sb.append("\nlocType : ");// 定位类型
            sb.append(location.getLocType());
            sb.append("\nlocType description : ");// *****对应的定位类型说明*****
            sb.append(location.getLocTypeDescription());
            sb.append("\nlatitude : ");// 纬度
            sb.append(location.getLatitude());
            sb.append("\nlontitude : ");// 经度
            sb.append(location.getLongitude());

            sb.append("\nradius : ");// 半径
            sb.append(location.getRadius());
            sb.append("\nCountryCode : ");// 国家码
            sb.append(location.getCountryCode());
            sb.append("\nCountry : ");// 国家名称
            sb.append(location.getCountry());
            sb.append("\ncitycode : ");// 城市编码
            sb.append(location.getCityCode());
            sb.append("\ncity : ");// 城市
            sb.append(location.getCity());
            sb.append("\nDistrict : ");// 区
            sb.append(location.getDistrict());
            sb.append("\nStreet : ");// 街道
            sb.append(location.getStreet());
            sb.append("\naddr : ");// 地址信息
            sb.append(location.getAddrStr());
            sb.append("\nUserIndoorState: ");// *****返回用户室内外判断结果*****
            sb.append(location.getUserIndoorState());
            sb.append("\nDirection(not all devices have value): ");
            sb.append(location.getDirection());// 方向
            sb.append("\nlocationdescribe: ");
            sb.append(location.getLocationDescribe());// 位置语义化信息
            sb.append("\nPoi: ");// POI信息
            if (location.getPoiList() != null && !location.getPoiList().isEmpty()) {
                for (int i = 0; i < location.getPoiList().size(); i++) {
                    Poi poi = (Poi) location.getPoiList().get(i);
                    sb.append(poi.getName() + ";");
                }
            }
            if (location.getLocType() == BDLocation.TypeGpsLocation) {// GPS定位结果
                sb.append("\nspeed : ");
                sb.append(location.getSpeed());// 速度 单位：km/h
                sb.append("\nsatellite : ");
                sb.append(location.getSatelliteNumber());// 卫星数目
                sb.append("\nheight : ");
                sb.append(location.getAltitude());// 海拔高度 单位：米
                sb.append("\ngps status : ");
                sb.append(location.getGpsAccuracyStatus());// *****gps质量判断*****
                sb.append("\ndescribe : ");
                sb.append("gps定位成功");
            } else if (location.getLocType() == BDLocation.TypeNetWorkLocation) {// 网络定位结果
                // 运营商信息
                if (location.hasAltitude()) {// *****如果有海拔高度*****
                    sb.append("\nheight : ");
                    sb.append(location.getAltitude());// 单位：米
                }
                sb.append("\noperationers : ");// 运营商信息
                sb.append(location.getOperators());
                sb.append("\ndescribe : ");
                sb.append("网络定位成功");
            } else if (location.getLocType() == BDLocation.TypeOffLineLocation) {// 离线定位结果
                sb.append("\ndescribe : ");
                sb.append("离线定位成功，离线定位结果也是有效的");
            } else if (location.getLocType() == BDLocation.TypeServerError) {
                sb.append("\ndescribe : ");
                sb.append("服务端网络定位失败，可以反馈IMEI号和大体定位时间到loc-bugs@baidu.com，会有人追查原因");
            } else if (location.getLocType() == BDLocation.TypeNetWorkException) {
                sb.append("\ndescribe : ");
                sb.append("网络不同导致定位失败，请检查网络是否通畅");
            } else if (location.getLocType() == BDLocation.TypeCriteriaException) {
                sb.append("\ndescribe : ");
                sb.append("无法获取有效定位依据导致定位失败，一般是由于手机的原因，处于飞行模式下一般会造成这种结果，可以试着重启手机");
            }
            logMsg(sb.toString());
        }

        public void onConnectHotSpotMessage(String s, int i){
        }

        /**
         * 检测是否在原地不动
         * @param distance
         * @return
         */
        private boolean noMove(float distance){
            if (distance < 0.01) {
                return true;
            }
            return false;
        }




    };

    private void initLocData(){
        this.preLatitude = 0.0f;       //定位数据
        this.preLongitude = 0.0f;
        this.flag = true;
        this.distanceSum = 0.0f; //总距离；
        this.bushuSum = 0; //总步数
        this.timeSum = 0; //总时间
        this.hotSum = 0.0f; //总热量

        this.height = getApp().getSharePreferenceInstance().getHeight();
        this.weight = getApp().getSharePreferenceInstance().getWeight();
        this.bc =(((float) Formulary.getBuChang(this.height,weight)) / 100);
        logMsg("欢迎使用本款软件");
    }


    private String getPersonInfo(){
        int h = getApp().getSharePreferenceInstance().getHeight();
        int weight = AppContext.getApp().getSharePreferenceInstance().getWeight();
        String username = AppContext.getApp().getSharePreferenceInstance().getUsername();
        String info =
                "用户名：[\t"+username+"\t]\n"+
                        "身高："+h+"cm\n"+
                        "体重："+weight+"kg\n"+
                        "步长："+ Formulary.getBuChang(h,weight)+"cm\n";
        return info;
    }

}
