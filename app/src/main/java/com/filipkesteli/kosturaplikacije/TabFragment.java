package com.filipkesteli.kosturaplikacije;

/**
 * Created by Filip on 22.5.2016..
 */

//TabFragment je fragment koji se inflate-a kad se pritisne neki gumb na
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class TabFragment extends Fragment {

    public static TabLayout tabLayout;
    public static ViewPager viewPager;
    public static final int NUMBER_OF_ITEMS = 3 ;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        /**
         *Inflate tab_layout and setup Views.
         */
        View view =  inflater.inflate(R.layout.tab_layout,null);
        tabLayout = (TabLayout) view.findViewById(R.id.tabs);
        viewPager = (ViewPager) view.findViewById(R.id.viewpager);

        /**
         *Set an Apater for the View Pager
         */
        viewPager.setAdapter(new ViewPagerAdapter(getChildFragmentManager()));

        /**
         * Now , this is a workaround ,
         * The setupWithViewPager dose't works without the runnable .
         * Maybe a Support Library Bug .
         */
        tabLayout.post(new Runnable() {
            @Override
            public void run() {
                tabLayout.setupWithViewPager(viewPager);
            }
        });
        return view;
    }

    class ViewPagerAdapter extends FragmentPagerAdapter{

        //array of names of fragments:
        private String[] tabs = {"Light", "Dark", "Purple"};

        public ViewPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        /**
         * Return fragment with respect to Position .
         */

        @Override
        public Fragment getItem(int position)
        {
            switch (position){
                case 0 :
                    return new PrimaryFragment();
                case 1 :
                    return new SocialFragment();
                case 2 :
                    return new UpdatesFragment();
            }
            return null;
        }

        @Override
        public int getCount() {
            return NUMBER_OF_ITEMS;
        }

        /**
         * This method returns the title of the tab according to the position.
         */

        @Override
        public CharSequence getPageTitle(int position) {

            switch (position){
                case 0 :
                    return tabs[0];
                case 1 :
                    return tabs[1];
                case 2 :
                    return tabs[2];
            }
            return null;
        }
    }
}
