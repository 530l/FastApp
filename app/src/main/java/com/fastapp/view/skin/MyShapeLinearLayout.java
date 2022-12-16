package com.fastapp.view.skin;


import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;

import com.drake.logcat.LogCat;
import com.hjq.shape.layout.ShapeLinearLayout;
import com.hjq.shape.styleable.ShapeLinearLayoutStyleable;

import skin.support.content.res.SkinCompatResources;
import skin.support.widget.SkinCompatHelper;
import skin.support.widget.SkinCompatSupportable;

@Deprecated
public class MyShapeLinearLayout extends ShapeLinearLayout implements SkinCompatSupportable {


    private static final ShapeLinearLayoutStyleable styleable = new ShapeLinearLayoutStyleable();


    private int shape_solidColor_id;

    public MyShapeLinearLayout(Context context) {
        super(context);
    }

    public MyShapeLinearLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        //如果此视图处于编辑模式，则为 True，否则为 false。
//        if (isInEditMode()) return;
        //1.获取需要支持换肤的自定义属性，例如这里solidColor
        TypedArray array = context.obtainStyledAttributes(attrs, com.hjq.shape.R.styleable.ShapeLinearLayout);
//        //1.2获取资源，某个需要适配的资源id。
        shape_solidColor_id = array.getResourceId(styleable.getSolidColorStyleable(), SkinCompatHelper.INVALID_ID);
        array.recycle();
//        //2.应用之前使用的换肤（回显）
        applySKinShapeLinearLayout();
    }


    @Override
    public void applySkin() {
        //3、应用运行间，手动切换换肤回调，再次进行换肤操作
        applySKinShapeLinearLayout();
    }

    //换肤处理
    private void applySKinShapeLinearLayout() {
        //检查id的合法性
        shape_solidColor_id = SkinCompatHelper.checkResourceId(shape_solidColor_id);
        LogCat.i(shape_solidColor_id, "shape_solidColor_id");
        //不等于默认值，就是色值。
        if (shape_solidColor_id != SkinCompatHelper.INVALID_ID) {
            int color = SkinCompatResources.getColor(getContext(), shape_solidColor_id);
            LogCat.i("颜色：" + color, "shape_solidColor_id");
            getShapeDrawableBuilder().setSolidColor(color).intoBackground();
        }
    }
}
