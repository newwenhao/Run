package www.wtu.com.run.activities;

import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Build;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.CardView;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import www.wtu.com.run.MainActivity;
import www.wtu.com.run.R;
import www.wtu.com.run.app.AppContext;
import www.wtu.com.run.utils.ToastUtils;

public class LoginActivity extends BaseActivity implements View.OnClickListener {

    private EditText etUsername;
    private EditText etPassword;
    private Button btGo;
    private CardView cv;
    private FloatingActionButton fab;


    @Override
    protected int returnLayoutRes() {
        return R.layout.activity_login;
    }

    @Override
    protected void initComponent() {
        etUsername = (EditText) findViewById(R.id.et_username);
        etPassword = (EditText) findViewById(R.id.et_password);
        btGo = (Button) findViewById(R.id.bt_go);
        cv = (CardView) findViewById(R.id.cv);
        fab = (FloatingActionButton) findViewById(R.id.fab);
    }

    @Override
    protected void bindEvent() {
        fab.setOnClickListener(this);
        btGo.setOnClickListener(this);
    }

    @Override
    protected void initData() {

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.fab:
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    getWindow().setExitTransition(null);
                    getWindow().setEnterTransition(null);
                    ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(this, fab, fab.getTransitionName());
                    startActivity(new Intent(this, RegisterActivity.class), options.toBundle());
                } else {
                    startActivity(new Intent(this, RegisterActivity.class));
                }
                break;
            case R.id.bt_go:

                if(TextUtils.isEmpty(etUsername.getText().toString().trim())){
                    ToastUtils.ToastShort("用户名不能为空");
                    return ;
                }

                if(TextUtils.isEmpty(etPassword.getText().toString().trim())){
                    ToastUtils.ToastShort("密码不能为空");
                    return ;
                }

                String username = AppContext.getApp().getSharePreferenceInstance().getUsername();
                String password = AppContext.getApp().getSharePreferenceInstance().getPassword();

                if( !(username.equals(etPassword.getText().toString().trim())
                        && password.equals(etPassword.getText().toString().trim()) )){
                    ToastUtils.ToastShort("用户名或密码错误");
                    return ;
                }
                AppContext.getApp().getSharePreferenceInstance().setIsLogin(true);
                Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                startActivity(intent);
                finish();
                break;
        }
    }
}
