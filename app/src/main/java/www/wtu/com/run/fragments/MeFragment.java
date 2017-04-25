package www.wtu.com.run.fragments;

import android.content.Intent;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import www.wtu.com.run.R;
import www.wtu.com.run.activities.ClimbRecordActivity;
import www.wtu.com.run.activities.RunRecordActivity;
import www.wtu.com.run.app.AppContext;
import www.wtu.com.run.utils.DateUtils;
import www.wtu.com.run.utils.ToastUtils;

/**
 * Created by Administrator on 2017/4/20.
 * 个人信息主界面
 */

public class MeFragment extends BaseFragment implements View.OnClickListener {

    private TextView etUsername,etKllSum,etSportSum;
    private LinearLayout llSportRecold;
    private LinearLayout llClimbRecord;
    private LinearLayout llExit;
    private LinearLayout llClearData;


    @Override
    protected int returnLayoutRes() {
        return R.layout.layout_fragment_me;
    }

    @Override
    protected void initComp(View root) {
        etUsername = (TextView) root.findViewById(R.id.et_username);
        etKllSum = (TextView) root.findViewById(R.id.et_kll_sum);
        etSportSum = (TextView) root.findViewById(R.id.et_sport_sum);
        llSportRecold = (LinearLayout) root.findViewById(R.id.ll_run_record);
        llClimbRecord = (LinearLayout) root.findViewById(R.id.ll_climb_record);
        llExit = (LinearLayout) root.findViewById(R.id.ll_exit);
        llClearData = (LinearLayout) root.findViewById(R.id.ll_clear_data);

    }

    @Override
    protected void bindEvent() {
        etUsername.setOnClickListener(this);
        llSportRecold.setOnClickListener(this);
        llClimbRecord.setOnClickListener(this);
        llExit.setOnClickListener(this);
        llClearData.setOnClickListener(this);
    }

    @Override
    protected void initData() {
        etUsername.setText(AppContext.getApp().getSharePreferenceInstance().getUsername());
        etKllSum.setText("运动总消耗:"+Math.floor(AppContext.getApp().getSharePreferenceInstance().getHotSum())+"焦耳");
        String dateInfo = "运动总时间:"+ DateUtils.format((AppContext.getApp().getSharePreferenceInstance().getTime()*1000));
        etSportSum.setText(dateInfo);
    }

    @Override
    public void loadData(String[] params) {

    }

    @Override
    public void onClick(View v) {
        Intent intent ;
        switch (v.getId()){
            case R.id.et_username:

                break;
            case R.id.ll_run_record:
                intent = new Intent(getActivity(), RunRecordActivity.class);
                getActivity().startActivity(intent);
                break;

            case R.id.ll_climb_record:
                intent = new Intent(getActivity(), ClimbRecordActivity.class);
                getActivity().startActivity(intent);
                break;
//
            case R.id.ll_clear_data:
                AppContext.getApp().getSharePreferenceInstance().clearData();
                AppContext.getApp().getDaoSession().getRunDataDao().deleteAll();
                ToastUtils.ToastShort("清除成功");
                break;

            case R.id.ll_exit:
//                ToastUtils.ToastShort("exit");
                AppContext.getApp().getSharePreferenceInstance().setIsLogin(false);
                (getActivity()).finish(); //退出应用程序
                break;
        }
    }
}
