package com.loubii.account.util;

import android.content.Context;
import android.widget.Toast;

import com.loubii.account.app.AccountApplication;

/**
 * @author luo
 * @date 2017/8/25
 */
public class ToastUtil {
    public static void showShort(Context context, String message) {
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show();
    }
    public static void showShort( String message) {
        Toast.makeText(AccountApplication.getInstance(), message, Toast.LENGTH_SHORT).show();
    }
}
