package com.example.test2.ui.activity;

import android.media.tv.TvTrackInfo;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.Window;
import android.widget.Switch;
import android.widget.TextView;

import com.example.test2.R;
import com.example.test2.ui.fragment.MainFragment;
import com.example.test2.ui.fragment.Snackbar;
import com.example.test2.ui.fragment.TextInputLayout;

/**
 * Created by xiaolin on 2017/2/14.
 */

public class MDActivity extends AppCompatActivity {
    private Toolbar mToolbar;
    private DrawerLayout mDrawerLayout;
    private ActionBarDrawerToggle mDrawerToggle;
    private NavigationView mNavigationView;
    private TextView mTitle;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.md_activity);
        mToolbar = (Toolbar) findViewById(R.id.toolbar);
        mTitle = (TextView) findViewById(R.id.toolbar_title);
//        tvToolTitle.setText("主页");
        setSupportActionBar(mToolbar);
//        getSupportActionBar().setDisplayShowTitleEnabled(false);
        mDrawerLayout= (DrawerLayout) findViewById(R.id.drawer_layout);
        mDrawerToggle=new ActionBarDrawerToggle(this,mDrawerLayout,mToolbar,R.string.drawer_open,R.string.drawer_close);
        mDrawerToggle.syncState();
        mDrawerLayout.addDrawerListener(mDrawerToggle);

        mNavigationView= (NavigationView) findViewById(R.id.navigation_view);
        setupDrawerContent(mNavigationView);
        switchToMain();

    }

    private void switchToMain() {
        getSupportFragmentManager().beginTransaction().replace(R.id.frame_content,new MainFragment()).commit();
        mTitle.setText("主页");
    }

    private void setupDrawerContent(NavigationView view) {
        view.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()){
                    case R.id.navigation_item_main:
                        switchToMain();
                        break;
                    case R.id.navigation_item_t1:
                        switchToBlog();

                        break;
                    case R.id.navigation_item_t2:
                        switchToExample();
                        break;
                }
                item.setChecked(true);
                mDrawerLayout.closeDrawers();
                return true;
            }

            
        });
    }

    private void switchToBlog() {
        getSupportFragmentManager().beginTransaction().replace(R.id.frame_content,new TextInputLayout()).commit();
        mTitle.setText("md输入框");
    }

    private void switchToExample() {
        getSupportFragmentManager().beginTransaction().replace(R.id.frame_content,new Snackbar()).commit();
        mTitle.setText("snanckbar");
    }
    
}
