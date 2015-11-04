package com.marvell.zoezhu.birthmanager;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.marvell.zoezhu.birthmanager.R;

/**
 * Created by zoezhu on 2015/11/3.
 */
public class Topbar extends RelativeLayout {

    private Button leftBtn, rightBtn;
    private TextView tvTitle;

    private String title;
    private float titleTextSize;
    private int titleTextColor;

    private String leftText;
    private Drawable leftBackground;
    private int leftTextColor;

    private String rightText;
    private Drawable rightBackground;
    private int rightTextColor;

    private RelativeLayout.LayoutParams titleParams, leftParams, rightParams;


    public Topbar(Context context, AttributeSet attrs) {
        super(context, attrs);
        TypedArray ta = context.obtainStyledAttributes(attrs, R.styleable.Topbar);
        title = ta.getString(R.styleable.Topbar_title);
        titleTextSize = ta.getDimension(R.styleable.Topbar_titleTextSize, 0);
        titleTextColor = ta.getColor(R.styleable.Topbar_titleColor, 0);

        leftText = ta.getString(R.styleable.Topbar_leftText);
        leftBackground = ta.getDrawable(R.styleable.Topbar_leftBackground);
        leftTextColor = ta.getColor(R.styleable.Topbar_leftTextColor, 0);

        rightText = ta.getString(R.styleable.Topbar_rightText);
        rightBackground = ta.getDrawable(R.styleable.Topbar_rightBackground);
        rightTextColor = ta.getColor(R.styleable.Topbar_rightTextColor, 0);

        ta.recycle();

        leftBtn = new Button(context);
        rightBtn = new Button(context);
        tvTitle = new TextView(context);

        tvTitle.setText(title);
        tvTitle.setTextSize(titleTextSize);
        tvTitle.setTextColor(titleTextColor);
        tvTitle.setGravity(Gravity.CENTER);

        leftBtn.setText(leftText);
        leftBtn.setBackground(leftBackground);
        leftBtn.setTextColor(leftTextColor);

        rightBtn.setText(rightText);
        rightBtn.setBackground(rightBackground);
        rightBtn.setTextColor(rightTextColor);

        setBackgroundColor(0xFF80AB);

        titleParams = new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
        titleParams.addRule(RelativeLayout.CENTER_IN_PARENT, TRUE);

        leftParams = new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
        leftParams.addRule(RelativeLayout.ALIGN_PARENT_LEFT);

        rightParams = new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
        rightParams.addRule(RelativeLayout.ALIGN_PARENT_RIGHT);

        addView(tvTitle, titleParams);
        addView(leftBtn,leftParams);
        addView(rightBtn, rightParams);
    }
}
