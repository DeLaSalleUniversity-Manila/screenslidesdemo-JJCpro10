package com.example.janssen.screenslide;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.app.NavUtils;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.Menu;
import android.view.MenuItem;
/**
 * Created by Janssen on 12/7/2015.
 */
public class ScreenSlideActivity  extends FragmentActivity {

private static final int NUM_PAGES = 5;

private ViewPager mPager;

private PagerAdapter mPagerAdapter;



        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_screen_slide);
            mPager = (ViewPager) findViewById(R.id.pager);
            mPagerAdapter = new ScreenSlidePagerAdapter(getSupportFragmentManager());
            mPager.setAdapter(mPagerAdapter);
            mPager.setPageTransformer(true, new DepthPageTransformer());
            mPager.setOnPageChangeListener(new ViewPager.SimpleOnPageChangeListener() {
                @Override
                public void onPageSelected(int position) {

                    invalidateOptionsMenu();
                }
            });
        }

        @Override
        public boolean onCreateOptionsMenu(Menu menu) {
            super.onCreateOptionsMenu(menu);
            getMenuInflater().inflate(R.menu.activity_screen_slide, menu);

            menu.findItem(R.id.action_previous).setEnabled(mPager.getCurrentItem() > 0);


            MenuItem item = menu.add(Menu.NONE, R.id.action_next, Menu.NONE,
                    (mPager.getCurrentItem() == mPagerAdapter.getCount() - 1)
                            ? R.string.action_finish
                            : R.string.action_next);
            item.setShowAsAction(MenuItem.SHOW_AS_ACTION_IF_ROOM | MenuItem.SHOW_AS_ACTION_WITH_TEXT);
            return true;
        }

        @Override
        public boolean onOptionsItemSelected(MenuItem item) {
            switch (item.getItemId()) {
                case android.R.id.home:

                    NavUtils.navigateUpTo(this, new Intent(this, MainActivity.class));
                    return true;

                case R.id.action_previous:

                    mPager.setCurrentItem(mPager.getCurrentItem() - 1);
                    return true;

                case R.id.action_next:

                    mPager.setCurrentItem(mPager.getCurrentItem() + 1);
                    return true;
            }

            return super.onOptionsItemSelected(item);
        }


private class ScreenSlidePagerAdapter extends FragmentStatePagerAdapter {
    public ScreenSlidePagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {

        return ScreenSlidePageFragment.create(position);
    }

    @Override
    public int getCount() {

        return NUM_PAGES;
    }
}
}