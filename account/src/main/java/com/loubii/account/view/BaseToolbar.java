package com.loubii.account.view;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.support.annotation.ColorInt;
import android.support.annotation.DrawableRes;
import android.support.annotation.StringRes;
import android.support.annotation.StyleRes;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.loubii.account.R;

/*
 <com.xywallet.account.view.BaseToolbar
        android:id="@+id/toolbar"
        android:layout_width="match_parent"
        android:layout_height="?attr/actionBarSize"
        android:background="@color/colorPrimary"
        app:theme="@style/ToolbarStyle">

    </com.xywallet.account.view.BaseToolbar>
 */
/**
 * @author luo
 * @date 2017/8/24
 */
public class BaseToolbar extends Toolbar {
    //中心标题
    private TextView mCenterTitle;
    //中心icon
    private ImageView mCenterIcon;
    //左侧文字
    private TextView mNavigationText;
    //右侧文字
    private TextView mSettingText;
    //右侧按钮
    private ImageButton mSettingIcon;

    public BaseToolbar(Context context) {
        super(context);
    }

    public BaseToolbar(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public BaseToolbar(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }




    public void setMyNavigationTextAppearance(Context context, @StyleRes int resId) {
        if (this.mNavigationText != null) {
            this.mNavigationText.setTextAppearance(context, resId);
        }
    }

    public void setMyNavigationTextColor(@ColorInt int color) {
        if (this.mNavigationText != null) {
            this.mNavigationText.setTextColor(color);
        }
    }

    public void setNavigationTextOnClickListener(OnClickListener listener) {
        if (mNavigationText != null) {
            mNavigationText.setOnClickListener(listener);
        }
    }

    /**
     * 居中标题
     *
     * @param Rid
     */
    public void setCenterTitle(@StringRes int Rid) {
        setCenterTitle(this.getContext().getText(Rid));
    }

    public void setCenterTitle(CharSequence text) {
        Context context = this.getContext();
        if (this.mCenterTitle == null) {
            this.mCenterTitle = new TextView(context);
            this.mCenterTitle.setGravity(Gravity.CENTER);
            this.mCenterTitle.setSingleLine();
            this.mCenterTitle.setEllipsize(TextUtils.TruncateAt.END);
            setCenterTextAppearance(getContext(), R.style.ToolBar_Title);
            //textView in center
            this.addMyView(this.mCenterTitle, Gravity.CENTER);
        } else {
            if (this.mCenterTitle.getVisibility() != VISIBLE) {
                mCenterTitle.setVisibility(VISIBLE);
            }
        }
        if (mCenterIcon != null && mCenterIcon.getVisibility() != GONE) {
            mCenterIcon.setVisibility(GONE);
        }
        //隐藏toolbar自带的标题
        setTitle("");
        mCenterTitle.setText(text);
    }

    public void setCenterTextAppearance(Context context, @StyleRes int resId) {
        if (this.mCenterTitle != null) {
            this.mCenterTitle.setTextAppearance(context, resId);
        }
    }

    public void setCenterTextColor(@ColorInt int color) {
        if (this.mCenterTitle != null) {
            this.mCenterTitle.setTextColor(color);
        }
    }

    /**
     * 居中图标
     *
     * @param resId
     */
    public void setCenterIcon(@DrawableRes int resId) {
        setCenterIcon(ContextCompat.getDrawable(this.getContext(), resId));
    }

    public void setCenterIcon(Drawable drawable) {
        Context context = this.getContext();
        if (this.mCenterIcon == null) {
            this.mCenterIcon = new ImageView(context);
            this.mCenterIcon.setScaleType(ImageView.ScaleType.CENTER);
            //textView in center
            this.addMyView(this.mCenterIcon, Gravity.CENTER);
        } else {
            if (mCenterIcon.getVisibility() != VISIBLE) {
                mCenterIcon.setVisibility(VISIBLE);
            }
        }
        if (mCenterTitle != null && mCenterTitle.getVisibility() != GONE) {
            mCenterTitle.setVisibility(GONE);
        }
        //隐藏toolbar自带的标题
        setTitle("");
        mCenterIcon.setImageDrawable(drawable);
    }

    /**
     * 右侧文字
     *
     * @param Rid
     */
    public void setSettingText(@StringRes int Rid) {
        setSettingText(this.getContext().getText(Rid));
    }

    public void setSettingText(CharSequence text) {
        Context context = this.getContext();
        if (this.mSettingText == null) {
            this.mSettingText = new TextView(context);
            this.mSettingText.setTextSize(16);
            this.mSettingText.setTextColor(Color.parseColor("#e6ffffff"));
            this.mSettingText.setGravity(Gravity.CENTER);
            this.mSettingText.setSingleLine();
            this.mSettingText.setEllipsize(TextUtils.TruncateAt.END);
            //setSettingTextAppearance(getContext(), R.style.ToolBar_Title);
            //textView in center
            int padding = (int) this.getContext().getResources().getDimension(R.dimen.title_right_margin);
            this.mSettingText.setPadding(padding, 0, padding, 0);

            this.addMyView(this.mSettingText, Gravity.END);
        } else {
            if (mSettingText.getVisibility() != VISIBLE) {
                mSettingText.setVisibility(VISIBLE);
            }
        }
        if (mSettingIcon != null && mSettingIcon.getVisibility() != GONE) {
            mSettingIcon.setVisibility(GONE);
        }
        mSettingText.setText(text);
    }

    public void setSettingTextAppearance(Context context, @StyleRes int resId) {
        if (mSettingText != null) {
            mSettingText.setTextAppearance(context, resId);
        }
    }

    public void setSettingTextColor(@ColorInt int color) {
        if (mSettingText != null) {
            mSettingText.setTextColor(color);
        }
    }

    public void setOnSettingTextClickListener(OnClickListener listener) {
        if (mSettingText != null) {
            mSettingText.setOnClickListener(listener);
        }
    }

    /**
     * 右侧图标
     *
     * @param resId
     */
    public void setSettingIcon(@DrawableRes int resId) {
        setSettingIcon(ContextCompat.getDrawable(this.getContext(), resId));
        //获取系统判定的最低华东距离
//        ViewConfiguration.get(this.getContext()).getScaledTouchSlop();
    }

    public void setSettingIcon(Drawable drawable) {
        Context context = this.getContext();
        if (this.mSettingIcon == null) {
            this.mSettingIcon = new ImageButton(context);
            this.mSettingIcon.setBackground(null);
            //保持点击区域
            int padding = (int) this.getContext().getResources().getDimension(R.dimen.title_right_margin);
            this.mSettingIcon.setPadding(padding, 0, padding, 0);

            this.mSettingIcon.setScaleType(ImageView.ScaleType.CENTER);
            //textView in center
            this.addMyView(this.mSettingIcon, Gravity.END);
        } else {
            if (mSettingIcon.getVisibility() != VISIBLE) {
                mSettingIcon.setVisibility(VISIBLE);
            }
        }
        if (mSettingText != null && mSettingText.getVisibility() != GONE) {
            mSettingText.setVisibility(GONE);
        }
        mSettingIcon.setImageDrawable(drawable);
    }

    public void setSettingIconOnClickListener(OnClickListener listener) {
        if (mSettingIcon != null) {
            mSettingIcon.setOnClickListener(listener);
        }
    }

    /**
     * @param v
     * @param gravity
     */
    private void addMyView(View v, int gravity) {
        addMyView(v, gravity, 0, 0, 0, 0);
    }

    private void addMyView(View v, int gravity, int left, int top, int right, int bottom) {
        LayoutParams lp = new LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT,
                ViewGroup.LayoutParams.WRAP_CONTENT, gravity);
        lp.setMargins(left, top, right, bottom);
        this.addView(v, lp);
    }

}
