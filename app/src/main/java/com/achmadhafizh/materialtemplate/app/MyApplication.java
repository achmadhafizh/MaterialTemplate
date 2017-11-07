package com.achmadhafizh.materialtemplate.app;

import android.app.Application;
import android.graphics.Typeface;
import android.text.TextUtils;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

/**
 * Created by achmad.hafizh on 11/3/2017.
 */

public class MyApplication extends Application {
    public static final String TAG = MyApplication.class.getSimpleName();
    private static Typeface ralewayBold, ralewayLight, ralewayMedium, ralewayRegular,
                            robotoBold, robotoLight, robotoMedium, robotoRegular,
                            robotoCondensedBold, robotoCondensedLight, robotoCondensedRegular,
                            alexBrush, oswaldStencbab;
    private static MyApplication mInstance;
    private RequestQueue mRequestQueue;

    @Override
    public void onCreate() {
        super.onCreate();
        mInstance = this;
        ralewayBold = Typeface.createFromAsset(getAssets(), "Raleway-Bold.ttf");
        ralewayLight = Typeface.createFromAsset(getAssets(), "Raleway-Light.ttf");
        ralewayMedium = Typeface.createFromAsset(getAssets(), "Raleway-Medium.ttf");
        ralewayRegular = Typeface.createFromAsset(getAssets(), "Raleway-Regular.ttf");
        robotoBold = Typeface.createFromAsset(getAssets(), "Roboto-Bold.ttf");
        robotoLight = Typeface.createFromAsset(getAssets(), "Roboto-Light.ttf");
        robotoMedium = Typeface.createFromAsset(getAssets(), "Roboto-Medium.ttf");
        robotoRegular = Typeface.createFromAsset(getAssets(), "Roboto-Regular.ttf");
        robotoCondensedBold = Typeface.createFromAsset(getAssets(), "RobotoCondensed-Bold.ttf");
        robotoCondensedLight = Typeface.createFromAsset(getAssets(), "RobotoCondensed-Light.ttf");
        robotoCondensedRegular = Typeface.createFromAsset(getAssets(), "RobotoCondensed-Regular.ttf");
        alexBrush = Typeface.createFromAsset(getAssets(), "AlexBrush.ttf");
        oswaldStencbab = Typeface.createFromAsset(getAssets(), "Oswald-Stencbab.ttf");
    }

    public static synchronized MyApplication getInstance() {
        return mInstance;
    }

    public RequestQueue getRequestQueue() {
        if (mRequestQueue == null) {
            mRequestQueue = Volley.newRequestQueue(getApplicationContext());
        }

        return mRequestQueue;
    }

    public <T> void addToRequestQueue(Request<T> req, String tag) {
        // set the default tag if tag is empty
        req.setTag(TextUtils.isEmpty(tag) ? TAG : tag);
        getRequestQueue().add(req);
    }

    public <T> void addToRequestQueue(Request<T> req) {
        req.setTag(TAG);
        getRequestQueue().add(req);
    }

    public void cancelPendingRequests(Object tag) {
        if (mRequestQueue != null) {
            mRequestQueue.cancelAll(tag);
        }
    }
}
