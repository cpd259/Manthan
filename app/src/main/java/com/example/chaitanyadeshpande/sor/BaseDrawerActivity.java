package com.example.chaitanyadeshpande.sor;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.chaitanyadeshpande.sor.activities.CommonAttachmentListActivity;
import com.example.chaitanyadeshpande.sor.activities.ProfileActivity;
import com.example.chaitanyadeshpande.sor.activities.ReadingLevelListActivity;
import com.example.chaitanyadeshpande.sor.utilities.UserInfoUtility;

import butterknife.BindView;
import butterknife.ButterKnife;

public class BaseDrawerActivity extends AppCompatActivity {

    @BindView(R.id.toolbarHeaderTv)
    public TextView toolbarHeaderTv;
    @BindView(R.id.img_hamburger)
    ImageView imgHamburger;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.flContentRoot)
    FrameLayout flContentRoot;
    @BindView(R.id.navigation_view)
    NavigationView navigationView;
    @BindView(R.id.drawerLayout)
    DrawerLayout drawerLayout;

     LinearLayout llProgressMain;
     TextView tvLoadingMessage;

    private TextView tvUserName;
    private TextView tvRole;

    @Override
    public void setContentView(int layoutResID) {
        super.setContentView(R.layout.activity_drawer);
        ViewGroup viewGroup = findViewById(R.id.flContentRoot);

        llProgressMain = findViewById(R.id.ll_progress_main);
        tvLoadingMessage = findViewById(R.id.tv_loading_message);

        LayoutInflater.from(this).inflate(layoutResID, viewGroup, true);
        bindViews();
        setUpDrawer();
//        initializeSharedPrefernce();
    }

//    protected void initializeSharedPrefernce() {
//        sharedPref = SPUtils.getInstance(VQCMDrawerActivity.this);
//    }

    @SuppressLint("SetTextI18n")
    private void setUpDrawer() {
        try {
            imgHamburger.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    drawerLayout.openDrawer(GravityCompat.START);

                }
            });

            drawerLayout.addDrawerListener(new DrawerLayout.DrawerListener() {
                @Override
                public void onDrawerSlide(View drawerView, float slideOffset) {

                }

                @Override
                public void onDrawerOpened(View drawerView) {


                }

                @Override
                public void onDrawerClosed(View drawerView) {

                }

                @Override
                public void onDrawerStateChanged(int newState) {

                }
            });

            navigationView.setItemIconTintList(null);
            navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
                @Override
                public boolean onNavigationItemSelected(MenuItem item) {
                    drawerLayout.closeDrawer(GravityCompat.START);

                    if (item.getItemId() == R.id.nav_home) {
                        ReadingLevelListActivity.launch(BaseDrawerActivity.this);

                    } else if (item.getItemId() == R.id.nav_profile) {
                        ProfileActivity.launch(BaseDrawerActivity.this);

                    }else if (item.getItemId() == R.id.nav_common_docs) {
                        CommonAttachmentListActivity.launch(BaseDrawerActivity.this);

                    }else if (item.getItemId() == R.id.nav_logout) {
                        showLogoutDialog();
                    }
                    return false;
                }
            });

            if (navigationView != null) {
                tvUserName = navigationView.getHeaderView(0).findViewById(R.id.tv_user_name);
                tvUserName.setText(UserInfoUtility.getInstance().getSelectedUserDetails().getFirstName()
                        +" "+UserInfoUtility.getInstance().getSelectedUserDetails().getLastName());

                tvRole = navigationView.getHeaderView(0).findViewById(R.id.tv_role);
                tvRole.setText("Teacher");

//                displayVersionNameOnDrawer();
            }
        } catch (Exception e) {

        }
    }


    protected void showLogoutDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);

        builder.setTitle("Logout");

        builder.setMessage("Do you want to logout?");
        builder.setPositiveButton("YES", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
//                LoginActivity.launch(VQCMDrawerActivity.this);
            }
        });
        builder.setNegativeButton("NO", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });
        builder.show();
    }


    private void displayVersionNameOnDrawer() {
//        version.setText("GONOGOv ");
//        version.append(BankUtility.getVersionName(this));
    }

    protected void bindViews() {
        ButterKnife.bind(this);
        setupToolbar();
    }


    protected void setupToolbar() {
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);

        if (getToolbar() != null) {
            getToolbar().setNavigationOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    drawerLayout.openDrawer(GravityCompat.START);
                }
            });
        }
    }

    @Nullable
    public Toolbar getToolbar() {
        return toolbar;
    }


    protected void showDialog(Activity activity, String title, CharSequence message) {
        AlertDialog.Builder builder = new AlertDialog.Builder(activity);

        if (title != null) builder.setTitle(title);

        builder.setMessage(message);
        builder.setPositiveButton("YES", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
            }
        });
        builder.setNegativeButton("NO", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });
        builder.show();
    }

    @Override
    public void onBackPressed() {
        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawers();
            return;
        }
        super.onBackPressed();
    }


    public void disableNavigationDrawer() {
        if (imgHamburger != null) {
            imgHamburger.setVisibility(View.INVISIBLE);
        }
        drawerLayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED);
    }

//    public void showLoadingView(String message) {
//        llProgressMain.setVisibility(View.VISIBLE);
//        tvLoadingMessage.setText(message);
////        Logger.logError(LOG_TAG,"loading msg """);
//    }
//
//    public void hideLoadingView() {
//        llProgressMain.setVisibility(View.GONE);
//    }

}
