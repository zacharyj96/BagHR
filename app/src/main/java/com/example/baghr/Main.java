package com.example.baghr;

import android.app.ActivityOptions;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.os.Handler;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.transition.Slide;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import java.util.Calendar;

public class Main extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    User currentUser;

    boolean addingItem;

    Item currentItem;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // loads first fragment into activity
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ft.replace(R.id.mainFragment, new Settings());
        ft.commit();

        // loads user
        Intent intent = getIntent();
        currentUser = intent.getParcelableExtra("USER");

        //sets up toolbar
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        // sets up drawer layout
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        // sets up navigation view
        NavigationView navigationView = findViewById(R.id.nav_view);

        navigationView.getMenu().clear();

        // get menu based on user type
        if (currentUser.type == "admin") {
            navigationView.inflateMenu(R.menu.activity_home_drawer_admin);
        } else if (currentUser.type == "manager") {
            navigationView.inflateMenu(R.menu.activity_home_drawer_manager);
        } else if (currentUser.type == "hr") {
            navigationView.inflateMenu(R.menu.activity_home_drawer_hr);
        } else {
            navigationView.inflateMenu(R.menu.activity_home_drawer);
        }
        navigationView.setNavigationItemSelectedListener(this);

        // sets up window animations to transition from login
        setupWindowAnimations();

        TextView userName = navigationView.getHeaderView(0).findViewById(R.id.userName);
        TextView userEmail = navigationView.getHeaderView(0).findViewById(R.id.userEmail);

        // userName.setText();
        // userEmail.setText();


        getSupportActionBar().setTitle("Inventory");
    }

    private void setupWindowAnimations() {
        Slide slide = new Slide(Gravity.LEFT);
        slide.setDuration(1200);
        //slide.excludeTarget(R.id.navigation, true);
        getWindow().setEnterTransition(slide);
        getWindow().setAllowEnterTransitionOverlap(false);
    }

    @Override
    public void onBackPressed() {
        launchActivityMenu(0, true);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        return super.onOptionsItemSelected(item);
    }

    // loads new fragments into memory based on what menu item is pressed
    protected void launchActivityMenu(int context, boolean isBack) {
        // portrait orientation is default
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        Fragment fragment = null;
        Class fragmentClass;
        FragmentManager fragmentManager = getSupportFragmentManager();
        String tag = "";

        // context set based on page id
        switch(context) {
            // inventory
            case 0:
                fragment = fragmentManager.findFragmentByTag("Inventory");
                tag = "Inventory";
                fragmentClass = Inventory.class;
                isBack = true;
                getSupportActionBar().setTitle("Inventory");
                break;

            // sales report
            case 1:
                fragment = fragmentManager.findFragmentByTag("Sales");
                tag = "Sales";
                fragmentClass = Sales.class;
                getSupportActionBar().setTitle("Sales Report");
                break;

            // time sheet
            case 2:
                fragment = fragmentManager.findFragmentByTag("TimeSheet");
                tag = "TimeSheet";
                fragmentClass = TimeSheet.class;
                getSupportActionBar().setTitle("Time Sheet");
                break;

            // log out
            case 3:
                fragmentClass = null;

                // sets up new intent and clears existing on task
                final Intent intent = new Intent(this, Login.class);
                Slide slide = new Slide(Gravity.RIGHT);
                slide.setDuration(1200);
                //slide.excludeTarget(R.id.navigation, true);
                getWindow().setExitTransition(slide);
                getWindow().setAllowReturnTransitionOverlap(false);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                final Bundle bundle = ActivityOptions.makeSceneTransitionAnimation(this).toBundle();

                // delays logout so it transitions more smoothly
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        startActivity(intent, bundle);
                    }
                }, 200);
                break;

            // payroll
            case 4:
                fragment = fragmentManager.findFragmentByTag("Payroll");
                tag = "Payroll";
                fragmentClass = Payroll.class;
                getSupportActionBar().setTitle("Payroll");
                break;

            // news
            case 5:
                fragment = fragmentManager.findFragmentByTag("News");
                tag = "News";
                fragmentClass = News.class;
                getSupportActionBar().setTitle("News");
                break;

            // settings
            case 6:
                fragment = fragmentManager.findFragmentByTag("Settings");
                tag = "Settings";
                fragmentClass = Settings.class;
                getSupportActionBar().setTitle("Settings");
                break;

            // aisle
            case 7:
                fragment = fragmentManager.findFragmentByTag("Aisle");
                tag = "Aisle";
                fragmentClass = Aisle.class;
                getSupportActionBar().setTitle("Aisle");
                break;

            // row
            case 8:
                fragment = fragmentManager.findFragmentByTag("Row");
                tag = "Row";
                fragmentClass = Row.class;
                getSupportActionBar().setTitle("Row");
                break;

            // shelf
            case 9:
                fragment = fragmentManager.findFragmentByTag("Shelf");
                tag = "Shelf";
                fragmentClass = Shelf.class;
                getSupportActionBar().setTitle("Shelf");
                break;

            // settings
            default:
                fragment = fragmentManager.findFragmentByTag("Settings");
                tag = "Settings";
                fragmentClass = Settings.class;
                isBack = true;
                getSupportActionBar().setTitle("Settings");
        }

        // for every context code except log out, load a new fragment into memory

        if (context != 11) {
            if (fragment == null) {
                try {
                    fragment = (Fragment) fragmentClass.newInstance();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            Fragment previousFragment = fragmentManager.findFragmentById(R.id.mainFragment);
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            Slide exitSlide;
            Slide enterSlide;

            // checks if this was caused by a back button being pressed, if so mirror the transitions and reset the toolbar icon
            if (isBack) {
                exitSlide = new Slide(Gravity.RIGHT);
                enterSlide = new Slide(Gravity.LEFT);

                Toolbar toolbar = findViewById(R.id.toolbar);

                final DrawerLayout drawer = findViewById(R.id.drawer_layout);

                //toolbar.setNavigationIcon(R.drawable.ic_menu_white);
                toolbar.setNavigationOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        drawer.openDrawer(GravityCompat.START);
                    }
                });
            } else {
                exitSlide = new Slide(Gravity.LEFT);
                enterSlide = new Slide(Gravity.RIGHT);
            }

            exitSlide.setDuration(500);
            previousFragment.setExitTransition(exitSlide);

            enterSlide.setStartDelay(500);
            enterSlide.setDuration(500);
            fragment.setEnterTransition(enterSlide);
            fragmentTransaction.replace(R.id.mainFragment, fragment, tag);
            fragmentTransaction.commitAllowingStateLoss();
            fragmentManager.executePendingTransactions();
            DrawerLayout drawer = findViewById(R.id.drawer_layout);
            drawer.closeDrawer(GravityCompat.START);
        }
    }

    // drawer layout page ids each call their specific version of launchactivitymenu
    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        switch(item.getItemId()) {
            case R.id.nav_inventory:
                launchActivityMenu(0, false);
                break;
            case R.id.nav_sales:
                launchActivityMenu(1, false);
                break;
            case R.id.nav_time:
                launchActivityMenu(2, false);
                break;
            case R.id.nav_signOut:
                launchActivityMenu(3, false);
                break;
            case R.id.nav_payroll:
                launchActivityMenu(4, false);
                break;
            case R.id.nav_news:
                launchActivityMenu(5, false);
                break;
            case R.id.nav_settings:
                launchActivityMenu(6, false);
                break;
            default:
                launchActivityMenu(6, false);
        }
        return false;
    }
}
