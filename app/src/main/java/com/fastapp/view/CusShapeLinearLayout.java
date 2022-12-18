//package com.fastapp.view;
//
//import static com.fastapp.ui.skin.AdapterSkinShapeKt.adapterSkinShapeLinearLayout;
//
//import android.content.Context;
//import android.graphics.Color;
//import android.util.AttributeSet;
//
//import com.hjq.shape.layout.ShapeLinearLayout;
//
//import skin.support.utils.SkinPreference;
//import skin.support.widget.SkinCompatSupportable;
//
//// implements SkinCompatSupportable
//public class CusShapeLinearLayout extends ShapeLinearLayout {
//    public CusShapeLinearLayout(Context context) {
//        super(context);
//    }
//
//    public CusShapeLinearLayout(Context context, AttributeSet attrs) {
//        super(context, attrs);
//        aaaa();
//    }
//
//    public CusShapeLinearLayout(Context context, AttributeSet attrs, int defStyleAttr) {
//        super(context, attrs, defStyleAttr);
//    }
//
////    @Override
//    public void applySkin() {
//        aaaa();
//    }
//
//    private void aaaa(){
//        String skinColorStr = "#333333";
//        if (SkinPreference.getInstance().getSkinName().equals("black")){
//            skinColorStr = "#333333";
//        }
//        if (SkinPreference.getInstance().getSkinName().equals("green")){
//            skinColorStr = "#4CAF50";
//        }
//        if (SkinPreference.getInstance().getSkinName().equals("orange.skin")){
//            skinColorStr = "#FF9800";
//        }
//
//        getShapeDrawableBuilder()
//                .setSolidColor(Color.parseColor(skinColorStr))
//                .intoBackground();
//    }
//}
