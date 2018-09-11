package com.loubii.account.app;

import android.app.Application;
import android.content.Context;

import com.loubii.account.bean.AccountModel;
import com.loubii.account.db.database.DBManager;
import com.loubii.account.db.database.DbHelper;

/**
 * @author luo
 * @date 2017/8/23
 */
public class AccountApplication extends Application {

    private static AccountApplication mInstance;

    public static float screenDensity;

    public static Context getInstance() {
        return mInstance;
    }


    @Override
    public void onCreate() {
        super.onCreate();
        mInstance = this;
        //初始化greendao
        DbHelper.getInstance().init(this);

    }

    public static DBManager<AccountModel, Long> getDbManager() {
        return DbHelper.getInstance().author();
    }

}
