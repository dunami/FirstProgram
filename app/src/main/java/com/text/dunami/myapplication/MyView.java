package com.text.dunami.myapplication;

import android.content.Context;
import android.graphics.Canvas;
import android.icu.util.Measure;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by Dunami on 2018/4/19.
 */
/**要为自定义View自定义属性，可以通过declare-styleable声明需要的属性
 为了在Theme设置View的默认样式，可以同时定义一个format为reference的属性att_a，在定义Theme时为这个attribute指定一个Style，并且在View的第二个构造函数中将R.attr.attr_a 作为第三个参数调用第三个构造函数
 可以定义一个Style作为Theme中没有定义attr_a时View属性的默认值。
 可以在Theme中直接为属性赋值，但优先级最低
 当defStyleAttr（即View的构造函数的第三个参数）不为0且在Theme中有为这个attr赋值时，defStyleRes（通过obtainStyledAttributes的第四个参数指定）不起作用
 属性值定义的优先级：xml>style>Theme中的默认Sytle>默认Style（通过obtainStyledAttributes的第四个参数指定）>在Theme中直接指定属性值*/
public class MyView extends View {
    // 如果View是在Java代码里面new的，则调用第一个构造函数
    public MyView(Context context) {
        super(context);
    }

    // 如果View是在.xml里声明的，则调用第二个构造函数
    // 自定义属性是从AttributeSet参数传进来的
    public MyView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    // 不会自动调用
    // 一般是在第二个构造函数里主动调用
    // 如View有style属性时
    public MyView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    //API21之后才使用
    // 不会自动调用
    // 一般是在第二个构造函数里主动调用
    // 如View有style属性时
    public MyView(Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }
    //1. 直接在XML中定义>style定义>由defStyleAttr定义的值>defStyleRes指定的默认值、直接在Theme中指定的值

    //2. defStyleAttr（即defStyle）不为0且在当前Theme中可以找到这个attribute的定义时，defStyleRes不起作用，所以attr_four虽然在defStyleRes（DefaultCustomizeStyle）中定义了，但取到的值仍为null。

    //1. defStyleAtrtr即defStyle为0或Theme中没有定义defStyle时defStyleRes才起作用

    //2. defStyleRes>在Theme中直接定义

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
    }
}
