package com.achmadhafizh.materialtemplate.utilities;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.Bundle;
import android.widget.RelativeLayout;

import com.achmadhafizh.materialtemplate.R;

/**
 * Created by achmad.hafizh on 11/6/2017.
 */

public class CustomProgressDialog {
    private ProgressDialog progressDialog;
    private Context context;
    private String message;
    private boolean cancelable = false;
    private boolean is_showing = false;

    public CustomProgressDialog(Context context, String message, boolean cancel) {
        this.context = context;
        this.message = message;
        this.cancelable = cancel;
        progressDialog = new ProgressDialog(context, R.style.MyProgressBar) {
            @Override
            protected void onCreate(Bundle savedInstanceState) {
                super.onCreate(savedInstanceState);

                getWindow().setLayout(RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
                setContentView(R.layout.custom_loader);
            }
        };
        progressDialog.setIndeterminate(true);
        progressDialog.setCancelable(this.cancelable);

    }

    public void showDialog() {
        is_showing = true;
        progressDialog.show();
    }

    public void hideDialog() {
        try {
            is_showing = false;
            progressDialog.cancel();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public boolean isShowing() {
        return is_showing;
    }
}
