package www.wtu.com.run.utils;


import android.widget.Toast;

import www.wtu.com.run.app.AppContext;


public class ToastUtils {

	public static void ToastShort(String content){
		Toast.makeText(AppContext.getApp().getApplicationContext(), content, Toast.LENGTH_SHORT).show();
	}
	
	public static void ToastLong(String content){
		Toast.makeText(AppContext.getApp().getApplicationContext(), content, Toast.LENGTH_SHORT).show();
	}

}
