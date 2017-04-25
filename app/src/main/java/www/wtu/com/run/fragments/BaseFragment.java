package www.wtu.com.run.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by Administrator on 2017/4/20.
 */

public abstract class BaseFragment extends Fragment {

    /**
     * 加载分页页码
     */
    private int pFirstPage = 0;

    /**
     * 加载分页页大小
     */
    private int pPageMax = 2;

    public int getpPageMax() {
        return pPageMax;
    }

    public void setpPageMax(int pPageMax) {
        this.pPageMax = pPageMax;
    }

    public int getpFirstPage() {
        return pFirstPage;
    }

    public void setpFirstPage(int pFirstPage) {
        this.pFirstPage = pFirstPage;
    }

    /**
     * 返回布局视图
     * @return
     */
    protected abstract int returnLayoutRes();

    /**
     * 初始化组件
     * @param root
     */
    protected abstract void initComp(View root);

    /**
     * 绑定事件
     */
    protected abstract void bindEvent();

    /**
     * 初始化数据
     */
    protected abstract void initData();


    /**
     * 加载网络数据
     */
    public abstract void loadData(String[] params);

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View root = inflater.inflate(returnLayoutRes(),container,false);
        initComp(root);
        bindEvent();
        initData();
        return root;
    }


    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        // 判断该Fragment时候已经正在前台显示，就可以知道什么时候去加载数据了
        if (isVisibleToUser) {
            loadData(new String[]{String.valueOf(getpFirstPage()*getpPageMax()),String.valueOf( getpPageMax())}); // 加载数据的方法
        }
        super.setUserVisibleHint(isVisibleToUser);
    }

}
