package com.word.asmide;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.word.asmide.ui.CodeEditor;
import com.word.asmide.ui.WRecyclerAdapter;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {


    Toolbar mainToolbar;
    DrawerLayout mainDrawerLayout_left;
    private FileHelper fileHelper;
    private ArrayList<String> filelist;
    private WRecyclerAdapter Wadapter;
    private CodeEditor codeEditor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //用于初始化
        init();

        //设置点击监听
        Wadapter.setOnItemClickListener(new WRecyclerAdapter.OnItemClickListener() {
            @Override
            public void OnItemClick(int position) {
                File file = new File(fileHelper.getCurrentPath() + filelist.get(position));
                if (file.isDirectory()) {
                    fileHelper.gotoDir(filelist.get(position));
                    refreashFileList();
                } else {
                    try {
                        codeEditor.setText(fileHelper.readWithName(filelist.get(position)));
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        });



        //CodeEditor滑动监听
        codeEditor.setOnMoveListener(new CodeEditor.OnMoveListener() {
            @Override
            public void OnMove(float offsetX,float offsetY) {

            }
        });
    }


    /**
     * 本方法主要用于初始化
     * 如findViewById，设置事件监听等
     */
    void init(){
        initToolbar();
        initDrawerLayout();
        initRecyclerView();
        initCodeEditor();
    }


    private void initCodeEditor() {
        codeEditor = findViewById(R.id.main_CodeEditor);
    }


    private void initRecyclerView() {
        //获取RecyclerLayout实例
        fileHelper = new FileHelper(FileHelper.STORAGE_DIR);
        filelist = fileHelper.list();
        RecyclerView main_RecyclerView_left = findViewById(R.id.main_drawer_left_recycler);
        Wadapter = new WRecyclerAdapter(this,
                filelist);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        main_RecyclerView_left.setLayoutManager(layoutManager);
        //设置分割线
        main_RecyclerView_left.addItemDecoration(new DividerItemDecoration(this,DividerItemDecoration.VERTICAL));

        main_RecyclerView_left.setAdapter(Wadapter);
    }

    void refreashFileList() {
        filelist = fileHelper.list();
        Wadapter.update(filelist);
    }

    private void initDrawerLayout() {
        mainDrawerLayout_left = findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, mainDrawerLayout_left, mainToolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        mainDrawerLayout_left.addDrawerListener(toggle);
        toggle.syncState();
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
}


