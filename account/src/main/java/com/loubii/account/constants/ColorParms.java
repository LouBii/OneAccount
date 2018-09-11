package com.loubii.account.constants;

import android.content.res.Resources;
import android.graphics.Color;

import com.loubii.account.R;
import com.loubii.account.app.AccountApplication;

/**
 * @author luo
 * @date 2017/8/29
 */
public class ColorParms {
    private static Resources resource = AccountApplication.getInstance().getResources();
    public static int COLOR_CALENDAR_SELECT = Color.parseColor("#24c789");
    public static int COLOR_CALENDAR_COUNT_MINUS= Color.parseColor("#ff5363");
    public static int COLOR_EDITTEXT_DELETE_NORMAL= Color.parseColor("#837f8c");
    public static int COLOR_EDITTEXT_DELETE_WRONG= Color.parseColor("#fa6446");
    public static int COLOR_EDITTEXT_DELETE_HINT= resource.getColor(R.color.colorTextHint);
}
