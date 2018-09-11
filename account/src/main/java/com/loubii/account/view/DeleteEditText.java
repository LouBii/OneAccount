package com.loubii.account.view;

import android.content.Context;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.EditText;

import com.loubii.account.R;
import com.loubii.account.constants.ColorParms;
import com.loubii.account.util.DensityUtil;

/**
 * @author luo
 * @date 2017/8/30
 */
public class DeleteEditText extends EditText {
    private final int NORMAL_MODEL = 0;
    private final int WRONG_MODEL = 1;
    private int MODEL = NORMAL_MODEL;
    private Context mContext;
    private Drawable mDrawableDelete;

    public DeleteEditText(Context context) {
        super(context);
        this.mContext = context;
        init();
    }

    public DeleteEditText(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.mContext = context;
        init();
    }

    public DeleteEditText(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.mContext = context;
        init();
    }

    private void init() {
        mDrawableDelete = mContext.getResources().getDrawable(R.drawable.ic_edittext_delete_red);
        mDrawableDelete.setBounds(0, 0, DensityUtil.dip2px(16), DensityUtil.dip2px(16));
        this.addTextChangedListener(new TextWatcher() {
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void afterTextChanged(Editable s) {
                if (length() == 0 && MODEL == WRONG_MODEL) {
                    MODEL = NORMAL_MODEL;
                    setCompoundDrawables(null, null, null, null);
                    setTextColor(ColorParms.COLOR_EDITTEXT_DELETE_NORMAL);
                    setHintTextColor(ColorParms.COLOR_EDITTEXT_DELETE_HINT);
                }
            }
        });
    }

    public boolean toWrongModel() {
        //if ( MODEL == NORMAL_MODEL) {
        MODEL = WRONG_MODEL;
        setFocusable(true);
        setFocusableInTouchMode(true);
        requestFocus();
        if (length() > 0) {
            setCompoundDrawablePadding(DensityUtil.dip2px(10));
            setCompoundDrawables(null, null, mDrawableDelete, null);
            setTextColor(ColorParms.COLOR_EDITTEXT_DELETE_WRONG);
        } else
            setHintTextColor(ColorParms.COLOR_EDITTEXT_DELETE_WRONG);
        //}
        return false;
    }

    // 处理删除事件
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        setFocusableInTouchMode(true);
        if (mDrawableDelete != null && event.getAction() == MotionEvent.ACTION_UP) {
            int eventX = (int) event.getRawX();
            int eventY = (int) event.getRawY();
            //Logger.e("eventX = " + eventX + "; eventY = " + eventY);
            Rect rect = new Rect();
            //获取当前视图在屏幕中(包括statebar与toolbar)的rect坐标
            getGlobalVisibleRect(rect);
            //Logger.e(rect + "");
            rect.left = rect.right - getPaddingRight() - DensityUtil.dip2px(20);
            if (getPaddingRight() > DensityUtil.dip2px(15))
                rect.right = rect.right - getPaddingRight() + DensityUtil.dip2px(15);
            if (rect.contains(eventX, eventY) && MODEL == WRONG_MODEL) {
                MODEL = NORMAL_MODEL;
                setText("");
                setCompoundDrawables(null, null, null, null);
                setTextColor(ColorParms.COLOR_EDITTEXT_DELETE_NORMAL);
                //Logger.e(getCompoundDrawables().length + "");
                setHintTextColor(ColorParms.COLOR_EDITTEXT_DELETE_HINT);
            }
        }
        return super.onTouchEvent(event);
    }
}
