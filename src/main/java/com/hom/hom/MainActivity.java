package com.hom.hom;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity {

    /**
     * The {@link android.support.v4.view.PagerAdapter} that will provide
     * fragments for each of the sections. We use a
     * {@link FragmentPagerAdapter} derivative, which will keep every
     * loaded fragment in memory. If this becomes too memory intensive, it
     * may be best to switch to a
     * {@link android.support.v4.app.FragmentStatePagerAdapter}.
     */
    private SectionsPagerAdapter mSectionsPagerAdapter;

    /**
     * The {@link ViewPager} that will host the section contents.
     */
    private ViewPager mViewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // Create the adapter that will return a fragment for each of the three
        // primary sections of the activity.
        mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());

        // Set up the ViewPager with the sections adapter.
        mViewPager = (ViewPager) findViewById(R.id.container);
        mViewPager.setAdapter(mSectionsPagerAdapter);

        TabLayout tabLayout = (TabLayout) findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(mViewPager);
        tabLayout.getTabAt(0).setIcon(R.drawable.tab_beranda);
        tabLayout.getTabAt(1).setIcon(R.drawable.tab_pesanan);
        tabLayout.getTabAt(2).setIcon(R.drawable.tab_bantuan);
        tabLayout.getTabAt(3).setIcon(R.drawable.tab_profil);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    /**
     * A placeholder fragment containing a simple view.
     */
    public static class BerandaFragment extends Fragment {
        /**
         * The fragment argument representing the section number for this
         * fragment.
         */
        private static final String ARG_SECTION_NUMBER = "section_number";
        ViewPager viewPager;
        CustomSwipeAdapter customSwipeAdapter;
        Timer timer;
        private int dotscount;
        private ImageView[] dots;

        public BerandaFragment() {
        }

        /**
         * Returns a new instance of this fragment for the given section
         * number.
         */
        public static BerandaFragment newInstance() {
            BerandaFragment fragment = new BerandaFragment();
            Bundle args = new Bundle();
            return fragment;
        }

        @Override
        public void onCreate(@Nullable Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            timer = new Timer();
            timer.scheduleAtFixedRate(new MyTimerTask(), 2000, 4000);
            Log.d("ONCREATE", "onCreate: IS CALLED");

        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.fragment_beranda, container, false);

            viewPager = (ViewPager) rootView.findViewById(R.id.viewPagerBeranda);
            customSwipeAdapter = new CustomSwipeAdapter(this.getActivity());
            viewPager.setAdapter(customSwipeAdapter);

            dotscount = customSwipeAdapter.getCount();
            dots = new ImageView[dotscount];
            dots[0] = (ImageView) rootView.findViewById(R.id.dot1);
            dots[1] = (ImageView) rootView.findViewById(R.id.dot2);
            dots[2] = (ImageView) rootView.findViewById(R.id.dot3);

            Log.d("TESTTTT", "onCreateView: IS CALLED");

            dots[0].setImageDrawable(ContextCompat.getDrawable(getContext(),R.drawable.dot_active));

            viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
                @Override
                public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

                }

                @Override
                public void onPageSelected(int position) {
                    for (int i = 0; i < dotscount; i++) {
                        dots[i].setImageDrawable(ContextCompat.getDrawable(getContext(),R.drawable.dot_inactive));
                    }
                    dots[position].setImageDrawable(ContextCompat.getDrawable(getContext(),R.drawable.dot_active));


                }

                @Override
                public void onPageScrollStateChanged(int state) {

                }
            });

            return rootView;
        }

        public class MyTimerTask extends TimerTask{
            @Override
            public void run() {
                if (getActivity()!=null && viewPager!=null){
                    getActivity().runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            if(viewPager.getCurrentItem() < 2){
                                viewPager.setCurrentItem(viewPager.getCurrentItem()+1);
                            }else{
                                viewPager.setCurrentItem(0);
                            }
                        }
                    });
                }

            }
        }

        @Override
        public void onDestroy() {
            timer.cancel();
            timer.purge();
            super.onDestroy();
        }
    }

    public static class PesananFragment extends Fragment {
        /**
         * The fragment argument representing the section number for this
         * fragment.
         */
        private static final String ARG_SECTION_NUMBER = "section_number";

        public PesananFragment() {
        }

        /**
         * Returns a new instance of this fragment for the given section
         * number.
         */
        public static PesananFragment newInstance() {
            PesananFragment fragment = new PesananFragment();
            Bundle args = new Bundle();
            args.putInt(ARG_SECTION_NUMBER, 2);
            fragment.setArguments(args);
            return fragment;
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.fragment_pesanan, container, false);
            TextView textView = (TextView) rootView.findViewById(R.id.section_label);
            textView.setText(getString(R.string.section_format, getArguments().getInt(ARG_SECTION_NUMBER)));
            return rootView;
        }
    }

    /**
     * A {@link FragmentPagerAdapter} that returns a fragment corresponding to
     * one of the sections/tabs/pages.
     */
    public class SectionsPagerAdapter extends FragmentPagerAdapter {

        public SectionsPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            // getItem is called to instantiate the fragment for the given page.
            switch(position) {
                case 0:
                    return BerandaFragment.newInstance();

                case 1:
                    return PesananFragment.newInstance();

                default:
                    return PesananFragment.newInstance();
            }
        }

        @Override
        public int getCount() {
            // Show 4 total pages.
            return 4;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            switch (position) {
                case 0:
                    return "Beranda";
                case 1:
                    return "Pesanan";
                case 2:
                    return "Bantuan";
                case 3:
                    return "Profil Diri";
            }
            return null;
        }
    }
}
