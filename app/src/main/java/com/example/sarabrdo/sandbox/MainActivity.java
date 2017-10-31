package com.example.sarabrdo.sandbox;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by SARABRDO on 31/10/2017.
 */

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.btnEndlessScroll)
    Button btnEndlessScroll;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
    }

    @OnClick(R.id.btnEndlessScroll)
    public void endlessSccroll() {
        Intent i = new Intent(this, EndlessScroll.class);
        startActivity(i);

    }
}
