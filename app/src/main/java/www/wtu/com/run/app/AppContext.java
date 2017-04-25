package www.wtu.com.run.app;

import android.app.Application;
import android.app.Service;
import android.database.sqlite.SQLiteDatabase;
import android.os.Vibrator;

import com.baidu.mapapi.SDKInitializer;

import www.wtu.com.run.beans.dao.DaoMaster;
import www.wtu.com.run.beans.dao.DaoSession;
import www.wtu.com.run.map.LocationService;
import www.wtu.com.run.utils.SharedPreferenceUtil;

/**
 * Created by Administrator on 2017/4/20.
 */

public class AppContext extends Application {

    /**
     * 百度地图
     */
    public LocationService locationService;
    public Vibrator mVibrator;

    private static AppContext appContext;

    private DaoSession daoSession;

    private static AppContext app;

    public static AppContext getApp() {
        return app;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        /***
         * 百度地图初始化定位sdk，建议在Application中创建
         */
        locationService = new LocationService(getApplicationContext());
        mVibrator =(Vibrator)getApplicationContext().getSystemService(Service.VIBRATOR_SERVICE);
        SDKInitializer.initialize(getApplicationContext());


        if(app==null){
            AppContext.app = (AppContext) getApplicationContext();
        }
        appContext = this;
        initDB();
    }

    private void initDB() {
        DaoMaster.DevOpenHelper devOpenHelper = new DaoMaster.DevOpenHelper(this,"db_test",null);
        SQLiteDatabase db = devOpenHelper.getWritableDatabase();
        DaoMaster daoMaster = new DaoMaster(db);
        daoSession = daoMaster.newSession();
    }

    public DaoSession getDaoSession() {
        return daoSession;
    }

    /**
     * 用户信息配置
     */
    private SharedPreferenceUtil mSp;

    public SharedPreferenceUtil getSharePreferenceInstance(){
        if(mSp == null)
            mSp = new SharedPreferenceUtil(getApplicationContext(), SharedPreferenceUtil.SP_FILE_NAME);
        return mSp;
    }
}
