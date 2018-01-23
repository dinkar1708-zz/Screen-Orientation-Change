package com.example.withconfigchanges;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * A placeholder fragment containing a simple view.
 */
public class MainActivityOtherFragment extends Fragment {
    private static final String TAG = MainActivityOtherFragment.class.getSimpleName();

    public MainActivityOtherFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
//        Log.i(TAG,"onCreateView......other fragment");
        return inflater.inflate(R.layout.other_fragment_main, container, false);
    }
}
