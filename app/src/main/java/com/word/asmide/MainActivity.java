package com.word.asmide;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.word.asmide.file.FileHelper;
import com.word.asmide.ui.WRecyclerAdapter;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;

public class MainActivity extends AppCompatActivity {

    
    Toolbar mainToolbar;
    DrawerLayout mainDrawerLayout_left;
    FloatingActionButton main_fab;
    RecyclerView main_RecyclerView_left;
    private final int REQUEST_CODE = 2;
    private FileHelper fileHelper;
    private String[] filelist;
    private ArrayList<String> left_item_text;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //用于初始化
        init();

    }

    /**
     * 本方法主要用于初始化
     * 如findViewById，设置事件监听等
     */
    void init(){
        //判断是否需要动态申请权限
        if(!checkPermission()) {
            startRequestPermision(new String[]{Manifest.permission.READ_EXTERNAL_STORAGE});
        }

        initToolbar();
        initFloatActionBar();
        initDrawerLayout();
        initRecyclerView();
    }

    //请求权限
    private void startRequestPermision(String[] permissions) {
        ActivityCompat.requestPermissions(MainActivity.this, permissions, REQUEST_CODE);
    }

    //处理权限申请回调
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if (requestCode == REQUEST_CODE) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    //如果用户允许
                } else {
                    //如果拒绝授予权限,且勾选了不再提醒
                    if (!shouldShowRequestPermissionRationale(permissions[0])) {
                        //说明申请权限原因
                        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(this);
                        dialogBuilder.setView(R.layout.dialog);
                    } else {
                        //去设置页面
//                         goSetting();
                    }
                }
            }
        }
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }

    //检查是否存在危险权限
    private boolean checkPermission() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (ContextCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED) {
                return true;
            } else {
                return false;
            }
        } else {
            return true;
        }
    }

    private void initRecyclerView() {
        //获取RecyclerLayout实例
        left_item_text = new ArrayList<>();
        fileHelper = new FileHelper(FileHelper.STORAGE_DIR);
        filelist = fileHelper.list();
        Collections.addAll(left_item_text, filelist);
        main_RecyclerView_left = findViewById(R.id.main_drawer_left_recycler);
        final WRecyclerAdapter adapter = new WRecyclerAdapter(this,left_item_text);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        main_RecyclerView_left.setLayoutManager(layoutManager);
        //设置分割线
        main_RecyclerView_left.addItemDecoration(new DividerItemDecoration(this,DividerItemDecoration.VERTICAL));
        //设置点击监听
        adapter.setOnItemClickListener(new WRecyclerAdapter.OnItemClickListener() {
            @Override
            public void OnItemClick(int position) {
                File file = new File(fileHelper.getCurrentPath() + filelist[position]);
                if (file.isDirectory()) {
                    fileHelper.gotoDir(filelist[position]);
                    left_item_text = new ArrayList<>();
                    filelist = fileHelper.list();
                    Collections.addAll(left_item_text, filelist);
                    adapter.update(left_item_text);
                } else {
                    try {
                        fileHelper.readWithName(filelist[position]);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
        main_RecyclerView_left.setAdapter(adapter);
    }


    private void initDrawerLayout() {
        mainDrawerLayout_left = findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, mainDrawerLayout_left, mainToolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        mainDrawerLayout_left.addDrawerListener(toggle);
        toggle.syncState();
    }

    private void initFloatActionBar() {
        main_fab = findViewById(R.id.fab);
        main_fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }

    private void initToolbar() {
        mainToolbar = findViewById(R.id.toolbar);
        setSupportActionBar(mainToolbar);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
		super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
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

}


