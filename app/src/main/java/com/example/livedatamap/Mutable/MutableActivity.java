package com.example.livedatamap.Mutable;

import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.Observer;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.elyeproj.loaderviewlibrary.LoaderTextView;
import com.example.livedatamap.R;

import static java.lang.Math.random;

public class MutableActivity extends AppCompatActivity {
    MutableLiveData<String> mutableLiveData = new MutableLiveData<String>();
    LoaderTextView liveData;
    Button addFragment;
    Button btn_generate;
    Observer<String> changeObserver =new Observer<String>() {
        @Override
        public void onChanged(@Nullable String s) {
            liveData.setText(s);
        }
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mutable);
        initUi();
        mutableLiveData.observe(this,changeObserver);
        setTitle("Mutable LiveData");
        if (savedInstanceState != null)
            setFragmentControlButtonText();
        btn_generate.setOnClickListener(new View.OnClickListener() {
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
                            .replace(R.id.container ,new  MutableFragment())
                            .addToBackStack("").commit();
                }else{
                    getSupportFragmentManager().popBackStack();
                }
            }
        });
        getSupportFragmentManager().addOnBackStackChangedListener(
                new FragmentManager.OnBackStackChangedListener() {
            @Override
            public void onBackStackChanged() {
                setFragmentControlButtonText();
            }
        });
    }
    private void initUi(){
        liveData = findViewById(R.id.txt_mutable_live_a);
        addFragment = findViewById(R.id.btn_mutable_add_fragment);
        btn_generate = findViewById(R.id.btn_mutable_generate);
    }
    private void setFragmentControlButtonText(){
        if (getSupportFragmentManager().getBackStackEntryCount() == 0)
            addFragment.setText("Remove Fragment");
        else
            addFragment.setText("Add Fragment");
    }
}
