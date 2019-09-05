package com.example.livedatamap.TransformationMap;

import android.arch.core.util.Function;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.Observer;
import android.arch.lifecycle.Transformations;
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

public class TransformationFragment extends Fragment {
    View view;
    TextView element;
    Observer<String> observer = new Observer<String>() {
        @Override
        public void onChanged(@Nullable String s) {
            element.setText(s);
        }
    };
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.transformation_fragment , container , false);
        element = view.findViewById(R.id.txt_transform_fragment_live_data);
        return view;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        LiveData<String> transformredLiveData =
            Transformations.map(((TransformationMapActivity) getActivity()).mutableLiveData, new Function<String, String>() {
                @Override
                public String apply(String input) {
                    return "A:"+input;
                }
            });
        transformredLiveData.observe(this, observer);
    }
}
