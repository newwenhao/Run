package www.wtu.com.run;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;

import com.ashokvarma.bottomnavigation.BottomNavigationBar;
import com.ashokvarma.bottomnavigation.BottomNavigationItem;

import java.util.ArrayList;
import java.util.List;

import www.wtu.com.run.activities.BaseActivity;
import www.wtu.com.run.fragments.ClimbFragment;
import www.wtu.com.run.fragments.MeFragment;
import www.wtu.com.run.fragments.RunFragment;
import www.wtu.com.run.fragments.RunInfoFragment;

public class MainActivity extends BaseActivity  {


    private ViewPager vp;

    private FragmentPagerAdapter pagerAdapter;

    private List<Fragment> fragments = new ArrayList<>();

    private BottomNavigationBar bottomNavigationBar;

    @Override
    protected int returnLayoutRes() {
        return R.layout.activity_main;
    }

    @Override
    protected void initComponent() {
        bottomNavigationBar = (BottomNavigationBar) findViewById(R.id.bottom_navigation_bar);
        bottomNavigationBar.setBackgroundStyle(BottomNavigationBar.BACKGROUND_STYLE_STATIC);
        bottomNavigationBar.setBarBackgroundColor(R.color.white);//背景颜色
        bottomNavigationBar.setInActiveColor(R.color.nav_gray);//未选中时的颜色
        bottomNavigationBar.setActiveColor(R.color.colorPrimaryDark);//选中时的颜色
        bottomNavigationBar
                .addItem(new BottomNavigationItem(R.drawable.ic_home_white_24dp, "跑步"))
//                .addItem(new BottomNavigationItem(R.drawable.ic_book_white_24dp, "爬山"))
                .addItem(new BottomNavigationItem(R.drawable.ic_music_note_white_24dp, "了解"))
                .addItem(new BottomNavigationItem(R.drawable.ic_tv_white_24dp, "用户"))
                .initialise();
        vp = (ViewPager) findViewById(R.id.vp);
    }

    @Override
    protected void bindEvent() {
        bottomNavigationBar.setTabSelectedListener(new BottomNavigationBar.OnTabSelectedListener(){
            @Override
            public void onTabSelected(int position) {
                vp.setCurrentItem(position);
            }
            @Override
            public void onTabUnselected(int position) {

            }
            @Override
            public void onTabReselected(int position) {

            }
        });
    }

    @Override
    protected void initData() {


        RunFragment runFragment = new RunFragment();
        ClimbFragment climbFragment = new ClimbFragment();
        RunInfoFragment runInfoFragment = new RunInfoFragment();
        MeFragment meFragment = new MeFragment();
        fragments.add(runFragment);
//        fragments.add(climbFragment);
        fragments.add(runInfoFragment);
        fragments.add(meFragment);

        pagerAdapter = new FragmentPagerAdapter(getSupportFragmentManager()) {
            @Override
            public Fragment getItem(int position) {

                return fragments.get(position);
            }

            @Override
            public int getCount() {
                return fragments.size();
            }
        };

        vp.setAdapter(pagerAdapter);
        vp.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                bottomNavigationBar.setFirstSelectedPosition(position);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }


}
