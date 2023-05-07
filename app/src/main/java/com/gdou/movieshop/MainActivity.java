package com.gdou.movieshop;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.ashokvarma.bottomnavigation.BottomNavigationBar;
import com.ashokvarma.bottomnavigation.BottomNavigationItem;


public class MainActivity extends AppCompatActivity implements BottomNavigationBar.OnTabSelectedListener{

    private BottomNavigationBar bottomNavigationBar;
    int lastSelectedPosition = 0;
    private String TAG = MainActivity.class.getSimpleName();
    private MyFragment mMyFragment;
    private MoviesFragment mScanFragment;
    private HomeFragment mHomeFragment;
    private BottomNavigationBar bottomBar;
    FragmentManager fm=getFragmentManager();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        /**
         * bottomNavigation setting
         */

        bottomNavigationBar = (BottomNavigationBar) findViewById(R.id.bottom_navigation_bar);


        bottomNavigationBar
                .setTabSelectedListener(this)
                .setMode(BottomNavigationBar.MODE_FIXED)
                .setBackgroundStyle(BottomNavigationBar.BACKGROUND_STYLE_STATIC)
                .setActiveColor("#DC143C") //Select Color
                .setInActiveColor("#999999") //No color selected
                .setBarBackgroundColor("#eeeeee");//Navigation bar background color

        /** Set Default navigation bar */
        bottomNavigationBar
                .addItem(new BottomNavigationItem(R.mipmap.ic_home, "Home"))
                .addItem(new BottomNavigationItem(R.mipmap.ic_movie, "Film"))
                .addItem(new BottomNavigationItem(R.mipmap.ic_me, "My"))
                .setFirstSelectedPosition(lastSelectedPosition )
                .initialise(); //initialise

        setDefaultFragment();//Set Default navigation bar

    }

    /**
     * Set Default navigation bar
     */
    private void setDefaultFragment() {
        fm = getFragmentManager();
        FragmentTransaction transaction = fm.beginTransaction();
        mHomeFragment = HomeFragment.newInstance("Home");
        transaction.replace(R.id.tb, mHomeFragment);
        transaction.commit();



    }

    /**
     * Set Default navigation bar
     */
    @Override
    public void onTabSelected(int position) {
        Log.d(TAG, "onTabSelected() called with: " + "position = [" + position + "]");
        FragmentManager fm = this.getFragmentManager();
        //开启事务
        FragmentTransaction transaction = fm.beginTransaction();
        switch (position) {
            case 0:
                if (mHomeFragment == null) {
                    mHomeFragment = HomeFragment.newInstance("Home");
                }
                transaction.replace(R.id.tb, mHomeFragment);
                break;
            case 1:
                if (mScanFragment == null) {
                    mScanFragment = MoviesFragment.newInstance("Film");
                }
                transaction.replace(R.id.tb, mScanFragment);
                break;
            case 2:
                if (mMyFragment == null) {
                    mMyFragment = MyFragment.newInstance("My");
                }
                transaction.replace(R.id.tb, mMyFragment);
                break;

            default:
                break;
        }

        transaction.commit();//


    }

    /**
     * Set unselected Fragment transaction
     */
    @Override
    public void onTabUnselected(int position) {

    }

    /**
     * Set Release Fragment Transaction
     */
    @Override
    public void onTabReselected(int position) {

    }
    public void setBottomBarSelection(int tabId) {
        bottomNavigationBar.selectTab(tabId);
    }
}

