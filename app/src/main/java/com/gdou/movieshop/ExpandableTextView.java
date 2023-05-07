package com.gdou.movieshop;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.LinearLayout;
import android.widget.TextView;

/**
 *
 */
public class ExpandableTextView extends LinearLayout {

    private TextView mContentTextView;

    private TextView mExpansionButton;


    private int mMaxLine = 6;

    private CharSequence mContent;


    private boolean mIsExpansion;

    public ExpandableTextView(Context context) {
        super(context);
        init(context, null);
    }

    public ExpandableTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs);
    }

    public ExpandableTextView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context, attrs);
    }

    private void init(Context context, AttributeSet attrs) {
        setOrientation(VERTICAL);

        LayoutInflater.from(context).inflate(R.layout.view_expandable, this);

        mContentTextView = findViewById(R.id.tv_content);
        mExpansionButton = findViewById(R.id.v_expansion);


        mContentTextView.getViewTreeObserver().addOnGlobalLayoutListener(
                new ViewTreeObserver.OnGlobalLayoutListener() {
                    @Override
                    public void onGlobalLayout() {
                        if (mContentTextView.getWidth() == 0) {
                            return;
                        }
                        mContentTextView.getViewTreeObserver().removeOnGlobalLayoutListener(this);
                        setText(mContent);
                    }
                }
        );


        mExpansionButton.setOnClickListener(
                new OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        toggleExpansionStatus();
                    }
                }
        );

        mExpansionButton.setVisibility(GONE);
    }


    private void toggleExpansionStatus() {
        // 切换状态
        mIsExpansion = !mIsExpansion;

        // 更新内容和切换按钮的显示
        if (mIsExpansion) {
            mExpansionButton.setText("收起");                       // 全文状态, 按钮显示 "收起"
            mContentTextView.setMaxLines(Integer.MAX_VALUE);        // 全文状态, 行数设置为最大
        } else {
            mExpansionButton.setText("全文");                       // 收起状态, 按钮显示 "全文"
            mContentTextView.setMaxLines(mMaxLine);                 // 收起状态, 最大显示指定的行数
        }
    }


    public void setMaxLine(int maxLine) {
        this.mMaxLine = maxLine;
        setText(mContent);
    }


    public void setText(CharSequence text) {
        mContent = text;


        if (mContentTextView.getWidth() == 0) {
            return;
        }

        mContentTextView.setMaxLines(Integer.MAX_VALUE);
        mContentTextView.setText(mContent);

        int lineCount = mContentTextView.getLineCount();

        if (lineCount > mMaxLine) {
            mExpansionButton.setVisibility(VISIBLE);
            mExpansionButton.setText("All");

            mContentTextView.setMaxLines(mMaxLine);
            mIsExpansion = false;

        } else {

            mExpansionButton.setVisibility(GONE);
        }
    }

}
