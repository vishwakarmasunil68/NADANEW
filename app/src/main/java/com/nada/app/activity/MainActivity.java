package com.nada.app.activity;

import android.Manifest;
import android.annotation.TargetApi;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.os.StrictMode;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.downloader.Error;
import com.downloader.OnCancelListener;
import com.downloader.OnDownloadListener;
import com.downloader.OnPauseListener;
import com.downloader.OnProgressListener;
import com.downloader.OnStartOrResumeListener;
import com.downloader.PRDownloader;
import com.downloader.PRDownloaderConfig;
import com.downloader.Progress;
import com.google.android.material.navigation.NavigationView;
import com.nabinbhandari.android.permissions.PermissionHandler;
import com.nabinbhandari.android.permissions.Permissions;
import com.nada.app.R;
import com.nada.app.adapter.HomeGridAdapter;
import com.nada.app.fragment.AdminFragment;
import com.nada.app.fragment.CheckTestSampleFragment;
import com.nada.app.fragment.DCOSelectFragment;
import com.nada.app.fragment.DCOWhereAboutFragment;
import com.nada.app.fragment.MedicineCheckFragment;
import com.nada.app.fragment.RTPWhereAboutFragment;
import com.nada.app.fragmentcontroller.ActivityManager;
import com.nada.app.pojo.HomGridPOJO;
import com.nada.app.pojo.UserPOJO;
import com.nada.app.utils.Pref;
import com.nada.app.utils.StringUtils;
import com.nada.app.utils.TagUtils;
import com.nada.app.utils.UtilityFunction;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends ActivityManager {

    private DrawerLayout mDrawer;
    private ActionBarDrawerToggle drawerToggle;
    private NavigationView nvDrawer;
    private Toolbar toolbar;
    @BindView(R.id.ic_ham)
    ImageView ic_ham;
    @BindView(R.id.rv_home)
    RecyclerView rv_home;

    List<HomGridPOJO> homGridPOJOS = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setStatusBarGradiant(this);
        setContentView(R.layout.activity_main);

        StrictMode.VmPolicy.Builder builder = new StrictMode.VmPolicy.Builder();
        StrictMode.setVmPolicy(builder.build());

        PRDownloaderConfig config = PRDownloaderConfig.newBuilder()
                .setDatabaseEnabled(true)
                .build();
        PRDownloader.initialize(getApplicationContext(), config);

        ButterKnife.bind(this);
        settingNavDrawer();

        UserPOJO userPOJO = UtilityFunction.getUserPOJO(getApplicationContext());
        if (userPOJO != null) {
            Log.d(TagUtils.getTag(), "roles:-" + userPOJO.getUserRole());
            if (userPOJO.getUserRole().equalsIgnoreCase("1")) {
                homGridPOJOS.add(new HomGridPOJO(R.drawable.ic_news, StringUtils.NEWS));
                homGridPOJOS.add(new HomGridPOJO(R.drawable.ic_art, StringUtils.GALLERY));
                homGridPOJOS.add(new HomGridPOJO(R.drawable.ic_about, StringUtils.ABOUT));
                homGridPOJOS.add(new HomGridPOJO(R.drawable.ic_panel, StringUtils.DISCIPLINARY));
                homGridPOJOS.add(new HomGridPOJO(R.drawable.ic_appeal, StringUtils.APPEAL));
//                homGridPOJOS.add(new HomGridPOJO(R.drawable.ic_registered_users, StringUtils.RTP_WHEREABOUT));
                homGridPOJOS.add(new HomGridPOJO(R.drawable.admin, StringUtils.ADMIN));
                homGridPOJOS.add(new HomGridPOJO(R.drawable.prohibited, StringUtils.PROHIBITED_LIST));
                homGridPOJOS.add(new HomGridPOJO(R.drawable.drugs, StringUtils.DRUGS));
//                homGridPOJOS.add(new HomGridPOJO(R.drawable.ic_officer, StringUtils.DCO_WHEREABOUT));
                homGridPOJOS.add(new HomGridPOJO(R.drawable.ic_medicine, StringUtils.CHECK_MEDICINE));
                homGridPOJOS.add(new HomGridPOJO(R.drawable.ic_test, StringUtils.DOPE_TEST));
            } else if (userPOJO.getUserRole().equalsIgnoreCase("3")) {
                homGridPOJOS.add(new HomGridPOJO(R.drawable.ic_news, StringUtils.NEWS));
                homGridPOJOS.add(new HomGridPOJO(R.drawable.ic_art, StringUtils.GALLERY));
                homGridPOJOS.add(new HomGridPOJO(R.drawable.ic_about, StringUtils.ABOUT));
                homGridPOJOS.add(new HomGridPOJO(R.drawable.ic_panel, StringUtils.DISCIPLINARY));
                homGridPOJOS.add(new HomGridPOJO(R.drawable.ic_appeal, StringUtils.APPEAL));
                homGridPOJOS.add(new HomGridPOJO(R.drawable.ic_registered_users, StringUtils.RTP_WHEREABOUT));
//                homGridPOJOS.add(new HomGridPOJO(R.drawable.prohibited, StringUtils.ADMIN));
                homGridPOJOS.add(new HomGridPOJO(R.drawable.prohibited, StringUtils.PROHIBITED_LIST));
                homGridPOJOS.add(new HomGridPOJO(R.drawable.ic_officer, StringUtils.DRUGS));
//                homGridPOJOS.add(new HomGridPOJO(R.drawable.drugs, StringUtils.DCO_WHEREABOUT));
                homGridPOJOS.add(new HomGridPOJO(R.drawable.ic_medicine, StringUtils.CHECK_MEDICINE));
                homGridPOJOS.add(new HomGridPOJO(R.drawable.ic_test, StringUtils.DOPE_TEST));
            } else if (userPOJO.getUserRole().equalsIgnoreCase("2")) {
                homGridPOJOS.add(new HomGridPOJO(R.drawable.ic_news, StringUtils.NEWS));
                homGridPOJOS.add(new HomGridPOJO(R.drawable.ic_art, StringUtils.GALLERY));
                homGridPOJOS.add(new HomGridPOJO(R.drawable.ic_about, StringUtils.ABOUT));
                homGridPOJOS.add(new HomGridPOJO(R.drawable.ic_panel, StringUtils.DISCIPLINARY));
                homGridPOJOS.add(new HomGridPOJO(R.drawable.ic_appeal, StringUtils.APPEAL));
//                homGridPOJOS.add(new HomGridPOJO(R.drawable.ic_registered_users, StringUtils.RTP_WHEREABOUT));
//                homGridPOJOS.add(new HomGridPOJO(R.drawable.prohibited, StringUtils.ADMIN));
                homGridPOJOS.add(new HomGridPOJO(R.drawable.prohibited, StringUtils.PROHIBITED_LIST));
                homGridPOJOS.add(new HomGridPOJO(R.drawable.drugs, StringUtils.DRUGS));
                homGridPOJOS.add(new HomGridPOJO(R.drawable.ic_officer, StringUtils.DCO_WHEREABOUT));
                homGridPOJOS.add(new HomGridPOJO(R.drawable.ic_medicine, StringUtils.CHECK_MEDICINE));
                homGridPOJOS.add(new HomGridPOJO(R.drawable.ic_test, StringUtils.DOPE_TEST));
            }
        }
        attachGenericAdapter(rv_home);
    }

    public void attachGenericAdapter(RecyclerView rv) {
        GridLayoutManager linearLayoutManager = new GridLayoutManager(this, 2);
        rv.setHasFixedSize(true);
        rv.setLayoutManager(linearLayoutManager);
        HomeGridAdapter searchResultAdapter = new HomeGridAdapter(this, null, homGridPOJOS);
        rv.setAdapter(searchResultAdapter);
        rv.setNestedScrollingEnabled(false);
        rv.setItemAnimator(new DefaultItemAnimator());
    }


//    public void startDCOWhereAboutFragment(DCODeputeUserPOJO dcoDeputeUserPOJO) {
//        Log.d(TagUtils.getTag(),"starting dco");
//        startFragment(R.id.frame_main, new DCOWhereAboutFragment(dcoDeputeUserPOJO));
//    }
    public void startDCOWhereAboutFragment() {
        Log.d(TagUtils.getTag(),"starting dco");
        startFragment(R.id.frame_main, new DCOWhereAboutFragment());
    }

    public void startRTPWhereAboutFragment() {
        startFragment(R.id.frame_main, new RTPWhereAboutFragment());
    }

    public void startAdminFragment() {
//        startFragment(R.id.frame_main, new AdminFragment());
        startFragment(R.id.frame_main, new AdminFragment());
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public static void setStatusBarGradiant(Activity activity) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Window window = activity.getWindow();
            Drawable background = activity.getResources().getDrawable(R.drawable.ic_header);
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.setStatusBarColor(activity.getResources().getColor(android.R.color.transparent));
            window.setNavigationBarColor(activity.getResources().getColor(android.R.color.transparent));
            window.setBackgroundDrawable(background);
        }
    }

    private void settingNavDrawer() {
        mDrawer = (DrawerLayout) findViewById(R.id.drawerLayout);
        nvDrawer = (NavigationView) findViewById(R.id.nvView);

        View view = nvDrawer.inflateHeaderView(R.layout.home_header);

        LinearLayout ll_header = view.findViewById(R.id.ll_header);
        TextView tv_profile_name = view.findViewById(R.id.tv_profile_name);
//        tv_profile_name.setText(Constants.userDetail.getUsername());

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        setupDrawerContent(nvDrawer);

        setupDrawerToggle();
//        mDrawer.addDrawerListener(drawerToggle);
        drawerToggle.setDrawerIndicatorEnabled(false);

        nvDrawer.setItemIconTintList(null);
        ic_ham.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mDrawer.openDrawer(GravityCompat.START);
            }
        });

        ll_header.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                startFragment(R.id.frame_home, new ProfileFragment());
            }
        });

    }

    private void setupDrawerContent(NavigationView navigationView) {
        navigationView.setNavigationItemSelectedListener(
                new NavigationView.OnNavigationItemSelectedListener() {
                    @Override
                    public boolean onNavigationItemSelected(MenuItem menuItem) {
                        selectDrawerItem(menuItem);
                        return true;
                    }
                });
    }

    private void setupDrawerToggle() {
        drawerToggle = new ActionBarDrawerToggle(this, mDrawer, toolbar, R.string.drawer_open, R.string.drawer_close) {
            public void onDrawerClosed(View view) {
                super.onDrawerClosed(view);
            }

            /**
             * Called when a drawer has settled in a completely open state.
             */
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
                // Do whatever you want here

            }
        };
        // Set the drawer toggle as the DrawerListener
        mDrawer.addDrawerListener(drawerToggle);
    }

    public void selectDrawerItem(MenuItem menuItem) {
        switch (menuItem.getItemId()) {
            case R.id.popup_home:

                break;
            case R.id.popup_news:
                openNewsActivity();
                break;
            case R.id.popup_gallery:
                openGalleryActivity();
                break;
            case R.id.popup_about:
                openAboutUsActivity();
                break;
            case R.id.popup_discipline:
                openDisciplineActivity();
                break;
            case R.id.popup_appeal:
                openAppealPanel();
                break;
            case R.id.popup_rtp_whereabout:
                startRTPWhereAboutFragment();
                break;
            case R.id.popup_admin:
                startAdminFragment();
                break;
            case R.id.popup_rtp:
                openRTPActivity();
                break;
            case R.id.popup_prohibited:
                startProhibitedListActivity();
                break;
            case R.id.pupup_drugs:
                startDrugsActivity();
                break;
            case R.id.popup_logout:
                Pref.SetBooleanPref(getApplicationContext(), StringUtils.IS_LOGIN, false);
                startActivity(new Intent(MainActivity.this, LoginActivity.class));
                finishAffinity();
                break;
        }
        mDrawer.closeDrawers();
    }

    public void startProhibitedListActivity() {
        Intent intent = new Intent(MainActivity.this, PdfViewerActivity.class);
        intent.putExtra("type", "prohibited");
        startActivity(intent);
    }

    public void startDrugsActivity() {
        Intent intent = new Intent(MainActivity.this, PdfViewerActivity.class);
        intent.putExtra("type", "drugs");
        startActivity(intent);
    }

    public void openNewsActivity() {
        startActivity(new Intent(MainActivity.this, NewsActivity.class));
    }

    public void openGalleryActivity() {
        startActivity(new Intent(MainActivity.this, GalleryActivity.class));
    }

    public void openAboutUsActivity() {
        startActivity(new Intent(MainActivity.this, AboutUsActivity.class));
    }

    public void openDisciplineActivity() {
        startActivity(new Intent(MainActivity.this, DisciplinaryActivity.class));
    }

    public void openAppealPanel() {
        startActivity(new Intent(MainActivity.this, AppealPanelActivity.class));
    }

    public void openRTPActivity() {
        startActivity(new Intent(MainActivity.this, RTPActivity.class));
    }

    public String checkDirExist() {
        try {
            String dirPath = Environment.getExternalStorageDirectory() + File.separator + "Download";
            File appFolder = new File(dirPath);
            if (!appFolder.exists()) {
                appFolder.mkdir();
            }
            return dirPath;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }

    public void downloadPermission(String url, String file_name) {
        String[] permissions = {Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE};
        Permissions.check(MainActivity.this/*context*/, permissions, null/*rationale*/, null/*options*/, new PermissionHandler() {
            @Override
            public void onGranted() {

                String dir_path = checkDirExist();

                if (new File(dir_path, file_name).exists()) {
                    //open file
                    openPdf(new File(dir_path, file_name));
                } else {
                    downloadFromUrl(url, file_name, dir_path);
//                    new DownloadFile(url, file_name, dir_path).execute();
                }

            }
        });
    }

    public void startSelectDCOForWhereAbout() {
        startFragment(R.id.frame_main,new DCOSelectFragment());
    }

    public void startCheckMedicine() {
        startFragment(R.id.frame_main,new MedicineCheckFragment());
    }

    public void checkDopeTest() {
        startFragment(R.id.frame_main,new CheckTestSampleFragment());
    }

    private class DownloadFile extends AsyncTask<String, Integer, String> {

        ProgressDialog mProgressDialog;

        String server_url;
        String file_name;
        String dir_path;

        DownloadFile(String url, String file_name, String dir_path) {
            this.server_url = url;
            this.file_name = file_name;
            this.dir_path = dir_path;
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            mProgressDialog = new ProgressDialog(MainActivity.this);
            mProgressDialog.setMessage("Downloading File");
            mProgressDialog.setIndeterminate(false);
            mProgressDialog.setMax(100);
            mProgressDialog.setCancelable(false);
            mProgressDialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
            mProgressDialog.show();
        }

        @Override
        protected String doInBackground(String... sUrl) {
            try {
                URL url = new URL(server_url);
                URLConnection connection = url.openConnection();
                connection.connect();
                // this will be useful so that you can show a typical 0-100% progress bar
                int fileLength = connection.getContentLength();

                // download the file
                InputStream input = new BufferedInputStream(url.openStream());
                Log.d(TagUtils.getTag(), "filepath:-" + (dir_path + File.separator + file_name));
                OutputStream output = new FileOutputStream(new File(dir_path + File.separator + file_name));

                byte data[] = new byte[1024];
                long total = 0;
                int count;
                while ((count = input.read(data)) != -1) {
                    total += count;
                    // publishing the progress....
                    int progress = (int) (total * 100 / fileLength);
                    publishProgress(progress);
                    Log.d(TagUtils.getTag(), "on progress:-" + progress);
                    output.write(data, 0, count);
                }

                output.flush();
                output.close();
                input.close();
            } catch (Exception e) {
            }
            return null;
        }

        @Override
        protected void onProgressUpdate(Integer... values) {
            super.onProgressUpdate(values);
            Log.d(TagUtils.getTag(), "progress update:-" + values[0]);
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            if (mProgressDialog != null && mProgressDialog.isShowing()) {
                mProgressDialog.dismiss();
            }
            openPdf(new File(dir_path, file_name));
            Log.d(TagUtils.getTag(), "download completed");
        }
    }

    public void downloadFromUrl(String url, String file_name, String dir_path) {

        ProgressDialog mProgressDialog;
// instantiate it within the onCreate method
        mProgressDialog = new ProgressDialog(MainActivity.this);
        mProgressDialog.setMessage("Downloading File");
        mProgressDialog.setIndeterminate(false);
        mProgressDialog.setMax(100);
        mProgressDialog.setCancelable(false);
        mProgressDialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
        mProgressDialog.show();

        int downloadId = PRDownloader.download(url, dir_path, file_name)
                .build()
                .setOnStartOrResumeListener(new OnStartOrResumeListener() {
                    @Override
                    public void onStartOrResume() {

                    }
                })
                .setOnPauseListener(new OnPauseListener() {
                    @Override
                    public void onPause() {

                    }
                })
                .setOnCancelListener(new OnCancelListener() {
                    @Override
                    public void onCancel() {

                    }
                })
                .setOnProgressListener(new OnProgressListener() {
                    @Override
                    public void onProgress(Progress progress) {
                        mProgressDialog.setProgress((int) ((progress.currentBytes / progress.totalBytes) * 100));
                    }
                })
                .start(new OnDownloadListener() {
                    @Override
                    public void onDownloadComplete() {
                        mProgressDialog.dismiss();
                        Toast.makeText(getApplicationContext(), "File Downloaded", Toast.LENGTH_SHORT).show();
                        openPdf(new File(dir_path, file_name));
                    }

                    @Override
                    public void onError(Error error) {
                        mProgressDialog.dismiss();
                        Log.d(TagUtils.getTag(), "server message:-" + error.getServerErrorMessage());
                        Log.d(TagUtils.getTag(), "error:-" + error.toString() + ",response code:-" + error.getResponseCode());

                        Toast.makeText(getApplicationContext(), "Something went wrong", Toast.LENGTH_SHORT).show();
                    }
                });
    }

    public void openPdf(File file) {
        Uri path = Uri.fromFile(file);
        Intent pdfOpenintent = new Intent(Intent.ACTION_VIEW);
        pdfOpenintent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        pdfOpenintent.setDataAndType(path, "application/pdf");
        try {
            startActivity(pdfOpenintent);
        } catch (ActivityNotFoundException e) {
            Toast.makeText(getApplicationContext(), "No supported application found", Toast.LENGTH_SHORT).show();
        }
    }

}
