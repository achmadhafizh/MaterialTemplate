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

public class ThreeFragment extends Fragment {
    private static final String TAG = ThreeFragment.class.getSimpleName();
    public static final String TITLE = "Three";
    private Unbinder unbinder;

    public ThreeFragment() {
    }

    public static ThreeFragment newInstance() {
        return new ThreeFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_three, container, false);
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
