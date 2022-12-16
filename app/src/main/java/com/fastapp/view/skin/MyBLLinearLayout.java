package com.fastapp.view.skin;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;

import com.noober.background.drawable.DrawableCreator;
import com.noober.background.view.BLLinearLayout;

import skin.support.widget.SkinCompatSupportable;

public class MyBLLinearLayout extends BLLinearLayout implements SkinCompatSupportable {


    public MyBLLinearLayout(Context context) {
        super(context);
    }

    public MyBLLinearLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public MyBLLinearLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

    }

    @Override
    public void applySkin() {
        Drawable drawable = new DrawableCreator.Builder()
                .setSolidColor(Color.parseColor("#333333"))
                .build();
        setBackground(drawable);
    }
}
