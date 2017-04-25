package www.wtu.com.run.activities;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;

import www.wtu.com.run.MainActivity;
import www.wtu.com.run.R;
import www.wtu.com.run.app.AppContext;

public class SplashActivity extends AppCompatActivity {

    private final int SPLASH_DISPLAY_LENGHT = 3000; //延迟三秒

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        new Handler().postDelayed(new Runnable(){
            @Override
            public void run() {
                Intent mainIntent ;
                boolean isLogin = AppContext.getApp().getSharePreferenceInstance().getIslogin();
                if(!isLogin){
                    mainIntent = new Intent(SplashActivity.this,LoginActivity.class);
                }else{
                    mainIntent = new Intent(SplashActivity.this,MainActivity.class);
                }
                SplashActivity.this.startActivity(mainIntent);
                SplashActivity.this.finish();
            }
        }, SPLASH_DISPLAY_LENGHT);
    }
}
