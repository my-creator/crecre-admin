package com.crecre.admin.uis;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.crecre.admin.R;
import com.crecre.admin.databinding.ActivityMainBinding;

import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityMainBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_main);
    }

    public void voteBtnClick(View view) {
        Intent intent = new Intent(MainActivity.this, VoteActivity.class);
        startActivity(intent);
    }

    public void boardBtnClick(View view) {
        Intent intent = new Intent(MainActivity.this, VoteActivity.class);
        startActivity(intent);
    }

    public void creatorBtnClick(View view) {
        Intent intent = new Intent(MainActivity.this, VoteActivity.class);
        startActivity(intent);
    }

    public void hashtagBtnClick(View view) {
        Intent intent = new Intent(MainActivity.this, VoteActivity.class);
        startActivity(intent);
    }

    public void categoryBtnClick(View view) {
        Intent intent = new Intent(MainActivity.this, VoteActivity.class);
        startActivity(intent);
    }
}
