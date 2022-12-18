//package com.fastapp.view;
//
//import android.content.Context;
//import android.graphics.Color;
//import android.graphics.drawable.Drawable;
//import android.util.AttributeSet;
//
//import com.noober.background.drawable.DrawableCreator;
//import com.noober.background.view.BLLinearLayout;
//
//import skin.support.utils.SkinPreference;
//import skin.support.widget.SkinCompatSupportable;
//
//public class MYBLLinearLayout extends BLLinearLayout implements SkinCompatSupportable {
//    public MYBLLinearLayout(Context context) {
//        this(context,null);
//    }
//
//    public MYBLLinearLayout(Context context, AttributeSet attrs) {
//        this(context,attrs,0);
//    }
//
//    public MYBLLinearLayout(Context context, AttributeSet attrs, int defStyleAttr) {
//        super(context, attrs, defStyleAttr);
//        apply();
//    }
//
//
//    @Override
//    public void applySkin() {
//        apply();
//    }
//
//    private void apply() {
//        String skinColorStr = "#333333";
//        if (SkinPreference.getInstance().getSkinName().equals("black")) {
//            skinColorStr = "#333333";
//        }
//        if (SkinPreference.getInstance().getSkinName().equals("green")) {
//            skinColorStr = "#4CAF50";
//        }
//        if (SkinPreference.getInstance().getSkinName().equals("orange.skin")) {
//            skinColorStr = "#FF9800";
//        }
//        Drawable drawable = new DrawableCreator.Builder()
//                .setSolidColor(Color.parseColor(skinColorStr))
//                .build();
//        setBackground(drawable);
//    }
//}
