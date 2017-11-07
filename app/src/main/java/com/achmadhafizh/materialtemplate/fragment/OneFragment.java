package com.achmadhafizh.materialtemplate.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.achmadhafizh.materialtemplate.R;

import butterknife.ButterKnife;
import butterknife.Unbinder;

public class OneFragment extends Fragment {
    private static final String TAG = OneFragment.class.getSimpleName();
    public static final String TITLE = "One";
    private Unbinder unbinder;

    public OneFragment() {
    }

    public static OneFragment newInstance() {
        return new OneFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_one, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        unbinder = ButterKnife.bind(this, view);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
