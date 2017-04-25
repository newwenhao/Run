package www.wtu.com.run.utils;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by Administrator on 2017/4/20.
 */

public class SharedPreferenceUtil {

    public static final String SP_FILE_NAME = "run";

    private SharedPreferences sp;
    private SharedPreferences.Editor editor;

    public SharedPreferenceUtil(Context context, String file) {
        sp = context.getSharedPreferences(file, Context.MODE_PRIVATE);
        editor = sp.edit();
    }

    public void setUsername(String username) {
        editor.putString("username", username);
        editor.commit();
    }

    public String getUsername() {
        return sp.getString("username", "");
    }

    public void setPassword(String password) {
        editor.putString("password", password);
        editor.commit();
    }

    public String getPassword() {
        return sp.getString("password", "");
    }

    public void setIsLogin(boolean isLogin) {
        editor.putBoolean("isLogin", isLogin);
        editor.commit();
    }

    public boolean getIslogin() {
        return sp.getBoolean("isLogin", false);
    }

    public void setWeight(int weight) {
        editor.putInt("weight",weight);
        editor.commit();
    }

    public int getWeight() {
        return sp.getInt("weight", 0);
    }

    public void setHeight(int height) {
        editor.putInt("height",height);
        editor.commit();
    }

    public int getHeight() {
        return sp.getInt("height", 0);
    }

    public void setBuChang(int bc) {
        editor.putInt("bc",bc);
        editor.commit();
    }

    public int getBuChang() {
        return sp.getInt("bc", 0);
    }


    public void setTime(long time) {
        editor.putLong("time",time);
        editor.commit();
    }

    public long getTime() {
        return sp.getLong("time", 0L);
    }


    public void setHotSum(float hotSum) {
        editor.putFloat("hotSum",hotSum);
        editor.commit();
    }

    public float getHotSum() {
        return sp.getFloat("hotSum", 0.0f);
    }





    public void clearData(){
        editor.clear().commit();
    }

}
