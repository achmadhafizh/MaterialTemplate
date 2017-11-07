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

public class TwoFragment extends Fragment {
    private static final String TAG = TwoFragment.class.getSimpleName();
    public static final String TITLE = "Two";
    private Unbinder unbinder;

    public TwoFragment() {
    }

    public static TwoFragment newInstance() {
        return new TwoFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_two, container, false);
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
