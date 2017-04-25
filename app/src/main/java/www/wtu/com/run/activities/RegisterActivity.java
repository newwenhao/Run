package www.wtu.com.run.activities;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.CardView;
import android.text.TextUtils;
import android.transition.Transition;
import android.transition.TransitionInflater;
import android.view.View;
import android.view.ViewAnimationUtils;
import android.view.animation.AccelerateInterpolator;
import android.widget.Button;
import android.widget.EditText;

import www.wtu.com.run.R;
import www.wtu.com.run.app.AppContext;
import www.wtu.com.run.utils.ToastUtils;

public class RegisterActivity extends BaseActivity implements View.OnClickListener {

    FloatingActionButton fab;
    CardView cvAdd;
    EditText etUsername,etPassword,etRepeatPassword;
    Button btnGo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            ShowEnterAnimation();
        }
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                animateRevealClose();
            }
        });
    }


    @Override
    protected int returnLayoutRes() {
        return R.layout.activity_register;
    }

    @Override
    protected void initComponent() {
        fab = (FloatingActionButton) findViewById(R.id.fab);
        cvAdd = (CardView) findViewById(R.id.cv_add);

        etUsername = (EditText) findViewById(R.id.et_username);
        etPassword = (EditText) findViewById(R.id.et_password);
        etRepeatPassword = (EditText) findViewById(R.id.et_repeatpassword);
        btnGo = (Button) findViewById(R.id.bt_go);
    }

    @Override
    protected void bindEvent() {
        btnGo.setOnClickListener(this);
    }

    @Override
    protected void initData() {

    }

    private void ShowEnterAnimation() {
        Transition transition = TransitionInflater.from(this).inflateTransition(R.transition.fabtransition);
        getWindow().setSharedElementEnterTransition(transition);

        transition.addListener(new Transition.TransitionListener() {
            @Override
            public void onTransitionStart(Transition transition) {
                cvAdd.setVisibility(View.GONE);
            }

            @Override
            public void onTransitionEnd(Transition transition) {
                transition.removeListener(this);
                animateRevealShow();
            }

            @Override
            public void onTransitionCancel(Transition transition) {

            }

            @Override
            public void onTransitionPause(Transition transition) {

            }

            @Override
            public void onTransitionResume(Transition transition) {

            }


        });
    }

    public void animateRevealShow() {
        Animator mAnimator = ViewAnimationUtils.createCircularReveal(cvAdd, cvAdd.getWidth() / 2, 0, fab.getWidth() / 2, cvAdd.getHeight());
        mAnimator.setDuration(300);
        mAnimator.setInterpolator(new AccelerateInterpolator());
        mAnimator.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                super.onAnimationEnd(animation);
            }

            @Override
            public void onAnimationStart(Animator animation) {
                cvAdd.setVisibility(View.VISIBLE);
                super.onAnimationStart(animation);
            }
        });
        mAnimator.start();
    }

    public void animateRevealClose() {
        Animator mAnimator = ViewAnimationUtils.createCircularReveal(cvAdd, cvAdd.getWidth() / 2, 0, cvAdd.getHeight(), fab.getWidth() / 2);
        mAnimator.setDuration(300);
        mAnimator.setInterpolator(new AccelerateInterpolator());
        mAnimator.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                cvAdd.setVisibility(View.INVISIBLE);
                super.onAnimationEnd(animation);
                fab.setImageResource(R.drawable.plus);
                RegisterActivity.super.onBackPressed();
            }

            @Override
            public void onAnimationStart(Animator animation) {
                super.onAnimationStart(animation);
            }
        });
        mAnimator.start();
    }

    @Override
    public void onBackPressed() {
        animateRevealClose();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.bt_go:
                String usernameTemp  =  etUsername.getText().toString().trim();
                String passwordTemp  =  etPassword.getText().toString().trim();
                String repeatPasswordTemp  =  etRepeatPassword.getText().toString().trim();
                if(TextUtils.isEmpty(usernameTemp)){
                    ToastUtils.ToastShort("用户名不能为空");
                    return ;
                }

                if(TextUtils.isEmpty(passwordTemp)){
                    ToastUtils.ToastShort("密码不能为空");
                    return ;
                }

                if(!passwordTemp.equals(repeatPasswordTemp)){
                    ToastUtils.ToastShort("两次密码不一致");
                    return ;
                }
                AppContext.getApp().getSharePreferenceInstance().setUsername(usernameTemp);
                AppContext.getApp().getSharePreferenceInstance().setPassword(passwordTemp);
                animateRevealClose();
                break;
        }
    }
}
