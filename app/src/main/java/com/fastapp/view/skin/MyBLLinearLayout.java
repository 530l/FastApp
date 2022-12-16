package com.fastapp.view.skin;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;

import com.drake.logcat.LogCat;
import com.fastapp.R;
import com.fastapp.config.UserConfig;
import com.noober.background.drawable.DrawableCreator;
import com.noober.background.view.BLLinearLayout;

import skin.support.widget.SkinCompatSupportable;

public class MyBLLinearLayout extends BLLinearLayout implements SkinCompatSupportable {


    public MyBLLinearLayout(Context context) {
        super(context);
        applySkin();
    }

    public MyBLLinearLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        applySkin();
    }

    public MyBLLinearLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        applySkin();
    }

    //规范，颜色的规范，这个主题用什么色值，统一处理
    @Override
    public void applySkin() {
        String skinModel = UserConfig.INSTANCE.getSkinModel();
        LogCat.i(skinModel,"skinModel");
        Drawable drawable = null;
        if ("black".equals(skinModel)) {
            drawable = new DrawableCreator.Builder()
                    .setSolidColor(Color.parseColor("#333333"))
                    .build();
        }else if ("green".equals(skinModel)){
            drawable = new DrawableCreator.Builder()
                    .setSolidColor(Color.parseColor("#4CAF50"))
                    .build();
        }else if ("orange.skin".equals(skinModel)){
            drawable = new DrawableCreator.Builder()
                    .setSolidColor(Color.parseColor("#FF9800"))
                    .build();
        }
        setBackground(drawable);
    }
}
