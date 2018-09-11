package com.loubii.account.util;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Build;
import android.preference.PreferenceManager;
import android.text.TextUtils;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.loubii.account.bean.RecycleClassifyPagerBean;
import com.loubii.account.constants.Extra;
import com.loubii.account.constants.SPkeys;

import java.util.ArrayList;
import java.util.List;

import static android.os.Build.VERSION.SDK_INT;
import static android.os.Build.VERSION_CODES.HONEYCOMB;

/**
 * Shared preference util, it's easy to use like SpUtils.load(...).read(...).
 *
 * @since Oct. 29, 2014
 */
public class SPUtil {
    private static SPUtil sInstance;
    private Context mContext;

    public SPUtil(Context context) {
        mContext = context;
    }

    /**
     * Prefer file: shared preference file, save, read
     * or remove value via this class.
     */
    public class PreferFile {
        SharedPreferences sp;

        public PreferFile(SharedPreferences sp) {
            this.sp = sp;
        }

        public void save(String key, int value) {
            sp.edit().putInt(key, value).apply();
        }

        public void save(String key, long value) {
            sp.edit().putLong(key, value).apply();
        }

        public void save(String key, float value) {
            sp.edit().putFloat(key, value).apply();
        }

        public void save(String key, boolean value) {
            sp.edit().putBoolean(key, value).apply();
        }

        public void save(String key, String value) {
            sp.edit().putString(key, value).apply();
        }

        public int read(String key, int defValue) {
            return sp.getInt(key, defValue);
        }

        public long read(String key, long defValue) {
            return sp.getLong(key, defValue);
        }

        public float read(String key, float defValue) {
            return sp.getFloat(key, defValue);
        }

        public boolean read(String key, boolean defValue) {
            return sp.getBoolean(key, defValue);
        }

        public String read(String key, String defValue) {
            return sp.getString(key, defValue);
        }

        public boolean contains(String key) {
            return sp.contains(key);
        }

        public void remove(String key) {
            sp.edit().remove(key).apply();
        }

        public void clear() {
            sp.edit().clear().apply();
        }
    }

    /**
     * The global default shared preference util instance.
     * This instance is automatically created with this method.
     *
     * @param context context
     * @return the single instance
     */
    public static SPUtil with(Context context) {
        synchronized (SPUtil.class) {
            if (sInstance == null) {
                //不要直接引用contex，防止内存泄漏
                sInstance = new SPUtil(context.getApplicationContext());
            }
        }

        return sInstance;
    }

    /**
     * Load default shared preferences(packagename_preferences).
     *
     * @return {@link }
     */
    public PreferFile load() {
        if (sInstance == null) {
            throw new IllegalStateException("call with(context) first");
        }

        if (mContext == null) {
            throw new IllegalArgumentException("context cannot be null");
        }

        SharedPreferences sp = PreferenceManager
                .getDefaultSharedPreferences(mContext);

        return new PreferFile(sp);
    }

    @TargetApi(Build.VERSION_CODES.HONEYCOMB)
    private SharedPreferences getSharedPrefs(String preferName) {
        return mContext.getSharedPreferences(
                preferName, Context.MODE_MULTI_PROCESS);
    }

    /**
     * Load shared preferences according to prefer name.
     *
     * @param preferName the name of shared preference
     * @return {@link PreferFile}
     */
    public PreferFile load(String preferName) {
        if (sInstance == null) {
            throw new IllegalStateException("call with(context) first");
        }

        if (mContext == null) {
            throw new IllegalArgumentException("context cannot be null");
        }

        SharedPreferences sp = SDK_INT < HONEYCOMB ?
                mContext.getSharedPreferences(preferName, Context.MODE_PRIVATE) :
                getSharedPrefs(preferName);

        return new PreferFile(sp);
    }

    /**
     * 支出常用列表
     * @param context
     * @return
     */
    public static List<RecycleClassifyPagerBean> getCommonList(Context context, int outInType) {
        List<RecycleClassifyPagerBean> list = new ArrayList();
        String strCommon;
        if (outInType == Extra.ACCOUNT_TYPE_EXPEND)
            strCommon = with(context).load().read(SPkeys.EXPAND_COMMOM_LIST, "");
        else
            strCommon = with(context).load().read(SPkeys.INCOME_COMMOM_LIST, "");
        if (TextUtils.isEmpty(strCommon))
            return list;
        Gson gson = new Gson();
        list = gson.fromJson(strCommon, new TypeToken<List<RecycleClassifyPagerBean>>() {
        }.getType());
        return list;
    }

    /**
     * 支出 其他列表
     * @param context
     * @return
     */
    public static List<RecycleClassifyPagerBean> getOtherList(Context context, int outInType) {
        List<RecycleClassifyPagerBean> list = new ArrayList();
        String strOther;
        if (outInType == Extra.ACCOUNT_TYPE_EXPEND)
            strOther = with(context).load().read(SPkeys.EXPAND_OTHER_LIST, "");
        else
            strOther = with(context).load().read(SPkeys.INCOME_OTHER_LIST, "");
        if (TextUtils.isEmpty(strOther))
            return list;
        Gson gson = new Gson();
        list = gson.fromJson(strOther, new TypeToken<List<RecycleClassifyPagerBean>>() {
        }.getType());
        return list;
    }
}
