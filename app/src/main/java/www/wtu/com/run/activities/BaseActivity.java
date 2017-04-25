package www.wtu.com.run.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public abstract class BaseActivity extends AppCompatActivity {

    /**
     * 返回视图布局文件
     * @return
     */
    protected abstract int returnLayoutRes();

    /**
     * 初始化组件
     */
    protected abstract void initComponent();

    /**
     * 绑定事件
     */
    protected abstract void bindEvent();

    /**
     * 初始化数据
     */
    protected abstract void initData();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(returnLayoutRes());
        initComponent();
        bindEvent();
        initData();
    }

}
