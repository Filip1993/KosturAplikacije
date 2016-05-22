package com.filipkesteli.kosturaplikacije;

import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;

public class MainActivity extends AppCompatActivity {
    //private variables that represents views
    private DrawerLayout mDrawerLayout;
    private NavigationView mNavigationView;
    private android.support.v7.widget.Toolbar mToolbar;
    private ActionBarDrawerToggle mDrawerToggle;
    //private variables that represents business logic
    private FragmentManager mFragmentManager;
    private FragmentTransaction mFragmentTransaction;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initWidgets();
        setupFragmentManager();
        setupNavigationDrawer();
    }

    private void initWidgets() {
        /**
         *Setup the DrawerLayout and NavigationView
         */
        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawerLayout);
        mNavigationView = (NavigationView) findViewById(R.id.shitstuff);
        /**
         * Setup Drawer Toggle of the Toolbar
         */
        mToolbar = (android.support.v7.widget.Toolbar) findViewById(R.id.toolbar);
        mDrawerToggle = new ActionBarDrawerToggle(this, mDrawerLayout, mToolbar, R.string.app_name,
                R.string.app_name);
        mDrawerLayout.setDrawerListener(mDrawerToggle);
        mDrawerToggle.syncState();
    }

    private void setupFragmentManager() {
        /**
         * Lets inflate the very first fragment
         * Here , we are inflating the TabFragment as the first Fragment
         */
        //uzmemo trenutni fragment...
        //unutar kontejnera, tj. FragmentLayout-a stavimo TabFragment
        mFragmentManager = getSupportFragmentManager();
        mFragmentManager
                .beginTransaction()
                .replace(R.id.containerView, new TabFragment())
                .commit();

        /*mFragmentTransaction = mFragmentManager.beginTransaction();
        mFragmentTransaction.replace(R.id.containerView, new TabFragment()).commit();*/
    }

    private void setupNavigationDrawer() {
        /**
         * Setup click events on the Navigation View Items.
         */

        mNavigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(MenuItem menuItem) {
                //ugasimo drawer ako kliknemo na neki od itema
                mDrawerLayout.closeDrawers();
                if (menuItem.getItemId() == R.id.nav_item_sent) {
                    mFragmentManager
                            .beginTransaction()
                            .replace(R.id.containerView, new SentFragment())
                            .commit();
                }
                if (menuItem.getItemId() == R.id.nav_item_inbox) {
                    mFragmentManager
                            .beginTransaction()
                            .replace(R.id.containerView, new TabFragment())
                            .commit();
                }
                return false;
            }
        });
    }
}