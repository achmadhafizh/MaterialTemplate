package com.achmadhafizh.materialtemplate.font;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.widget.TextView;

/**
 * Created by achmad.hafizh on 11/6/2017.
 */

public class CustomRobotoCondensedRegularText extends TextView {
    public CustomRobotoCondensedRegularText(Context context) {
        super(context);
        Typeface face = Typeface.createFromAsset(context.getAssets(), "RobotoCondensed-Regular.ttf");
        this.setTypeface(face);
    }

    public CustomRobotoCondensedRegularText(Context context, AttributeSet attrs) {
        super(context, attrs);
        Typeface face = Typeface.createFromAsset(context.getAssets(), "RobotoCondensed-Regular.ttf");
        this.setTypeface(face);
    }

    public CustomRobotoCondensedRegularText(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        Typeface face = Typeface.createFromAsset(context.getAssets(), "RobotoCondensed-Regular.ttf");
        this.setTypeface(face);
    }

    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
    }
}
