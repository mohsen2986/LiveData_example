package com.example.livedatamap.Mutable;

import android.arch.lifecycle.Observer;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.livedatamap.R;

import java.util.Observable;

public class MutableFragment extends Fragment {
    private View view;
    private TextView element;
    private Observer<String> observer = new Observer<String>() {
        @Override
        public void onChanged(@Nullable String s) {
            element.setText(s);
        }
    };

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_mutable_live_data , container , false);
        element = view.findViewById(R.id.txt_fragment_mutable);
        return view;
    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        ((MutableActivity)(context)).mutableLiveData.observe(this , observer);
    }
}
