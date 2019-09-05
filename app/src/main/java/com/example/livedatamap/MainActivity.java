package com.example.livedatamap;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.livedatamap.Mutable.MutableActivity;
import com.example.livedatamap.TransformationMap.TransformationMapActivity;

public class MainActivity extends AppCompatActivity {
    private Button btn_mutable;
    private Button btn_transform;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initUi();
        uiActions();
    }
    private void initUi(){
        btn_mutable = findViewById(R.id.btn_main_mutable);
        btn_transform = findViewById(R.id.btn_main_transform);
    }
    private void uiActions(){
        btn_mutable.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this , MutableActivity.class);
                startActivity(intent);
            }
        });
        btn_transform.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this , TransformationMapActivity.class);
                startActivity(intent);
            }
        });
    }
}
