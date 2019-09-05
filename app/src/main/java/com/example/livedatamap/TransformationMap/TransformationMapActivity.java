package com.example.livedatamap.TransformationMap;

import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.Observer;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.elyeproj.loaderviewlibrary.LoaderTextView;
import com.example.livedatamap.R;

public class TransformationMapActivity extends AppCompatActivity {
    MutableLiveData<String> mutableLiveData = new MutableLiveData<>();
    LoaderTextView liveData;
    Button addFragment;
    Button generate;
    Observer<String> observer = new Observer<String>() {
        @Override
        public void onChanged(@Nullable String s) {
            liveData.setText(s);
        }
    };
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        setContentView(R.layout.activity_transformation_map);
        super.onCreate(savedInstanceState);
        setTitle("Transformation Map");
        mutableLiveData.observe(this , observer);
        initUi();
        if (savedInstanceState != null)
            setFragmentControlButtonListener();

        generate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                liveData.resetLoader();
                mutableLiveData.postValue(String.valueOf((int)(Math.random()*1000)));
            }
        });
        addFragment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (getSupportFragmentManager().getBackStackEntryCount() == 0){
                    getSupportFragmentManager().beginTransaction()
                            .replace(R.id.container , new TransformationFragment())
                            .addToBackStack("").commit();
                }else{
                    getSupportFragmentManager().popBackStack();
                }
            }
        });
    }
    private void initUi(){
        liveData = findViewById(R.id.txt_transform_live);
        addFragment = findViewById(R.id.btn_transform_add_fragment);
        generate = findViewById(R.id.btn_transform_generate);
    }
    private void setFragmentControlButtonListener(){
        if (getSupportFragmentManager().getBackStackEntryCount() == 0)
            addFragment.setText("Add Fragment");
        else
            addFragment.setText("Remove Fragment");
    }
}
