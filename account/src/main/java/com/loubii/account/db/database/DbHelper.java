package com.loubii.account.db.database;

import android.content.Context;

import com.loubii.account.bean.AccountModel;
import com.loubii.account.constants.Extra;
import com.loubii.account.db.AccountModelDao;
import com.loubii.account.db.DaoMaster;
import com.loubii.account.db.DaoSession;
import com.loubii.account.util.TimeUtil;

import org.greenrobot.greendao.AbstractDao;
import org.greenrobot.greendao.query.QueryBuilder;

import java.time.LocalDateTime;
import java.util.List;

/**
 * @Description: 数据库操作类，由于greenDao的特殊性，不能在框架中搭建，
 * 所有数据库操作都可以参考该类实现自己的数据库操作管理类，不同的Dao实现
 * 对应的getAbstractDao方法就行。
 * @date: 17/1/18 23:18.
 */
public class DbHelper {
    private static final String DB_NAME = "loubii.db";//数据库名称
    private static DbHelper instance;
    private static DBManager<AccountModel, Long> author;
    private DaoMaster.DevOpenHelper mHelper;
    private DaoMaster mDaoMaster;
    private DaoSession mDaoSession;

    private DbHelper() {

    }

    public static DbHelper getInstance() {
        if (instance == null) {
            synchronized (DbHelper.class) {
                if (instance == null) {
                    instance = new DbHelper();
                }
            }
        }
        return instance;
    }

    public void init(Context context) {
        mHelper = new DaoMaster.DevOpenHelper(context, DB_NAME, null);
        mDaoMaster = new DaoMaster(mHelper.getWritableDatabase());
        mDaoSession = mDaoMaster.newSession();
    }

    public void init(Context context, String dbName) {
        mHelper = new DaoMaster.DevOpenHelper(context, dbName, null);
        mDaoMaster = new DaoMaster(mHelper.getWritableDatabase());
        mDaoSession = mDaoMaster.newSession();
    }

    public DBManager<AccountModel, Long> author() {
        if (author == null) {
            author = new DBManager<AccountModel, Long>(){
                @Override
                public AbstractDao<AccountModel, Long> getAbstractDao() {
                    return mDaoSession.getAccountModelDao();
                }
            };
        }
        return author;
    }

    public DaoMaster getDaoMaster() {
        return mDaoMaster;
    }

    public DaoSession getDaoSession() {
        return mDaoSession;
    }

    public void clear() {
        if (mDaoSession != null) {
            mDaoSession.clear();
            mDaoSession = null;
        }
    }

    public void close() {
        clear();
        if (mHelper != null) {
            mHelper.close();
            mHelper = null;
        }
    }

    /**
     *
     * @param accountType 1:支出 ， 2收入
     * @param startDateTime
     * @param endDateTime
     * @return
     */
    public List<AccountModel> getAccountList(int accountType, LocalDateTime startDateTime, LocalDateTime endDateTime) {
        QueryBuilder<AccountModel> builder = author().queryBuilder()
                .where(AccountModelDao.Properties.DateTime.between(startDateTime,endDateTime));
        if (accountType != (Extra.ACCOUNT_TYPE_ALL))
            builder.where(AccountModelDao.Properties.OutIntype.eq(accountType));
        builder.orderAsc(AccountModelDao.Properties.DateTime);
        return builder.list();
//        List<AccountModel> accountList = author().queryBuilder()
//                .where(AccountModelDao.Properties.DateTime.between
//                        (startTime, endTime), AccountModelDao.Properties.OutIntype.eq(accountType))
//                .orderAsc(AccountModelDao.Properties.DateTime)
////                .offset(offSet * Config.LIST_LOAD_NUM)
////                .limit(Config.LIST_LOAD_NUM)
//                .list();
//        return accountList;
    }

    /**
     *
     * @param accountType 1:支出 ， 2收入
     * @param detailType 具体类型（餐饮等）
     * @param startDateTime
     * @param endDateTime
     * @return
     */
    public List<AccountModel> getAccountList(int accountType, String detailType, LocalDateTime startDateTime, LocalDateTime endDateTime) {

        QueryBuilder<AccountModel> builder = author().queryBuilder()
                .where(AccountModelDao.Properties.DateTime.between(startDateTime, endDateTime),
                        AccountModelDao.Properties.OutIntype.eq(accountType));
        if ( !detailType.equals(Extra.DETAIL_TYPE_DEFAULT))
            builder.where(AccountModelDao.Properties.DetailType.eq(detailType));
        builder.orderAsc(AccountModelDao.Properties.DateTime);
        //List<AccountModel> accountList  .list();
        return builder.list();

//        List<AccountModel> accountList = author().queryBuilder()
//                .where(AccountModelDao.Properties.DateTime.between(startTime, endTime),
//                        AccountModelDao.Properties.OutIntype.eq(accountType),
//                        AccountModelDao.Properties.DetailType.eq(detailType))
//                .orderAsc(AccountModelDao.Properties.DateTime)
////                .offset(offSet * Config.LIST_LOAD_NUM)
////                .limit(Config.LIST_LOAD_NUM)
//                .list();
//        return accountList;
    }

    /**
     * 最小时间
     */
    public LocalDateTime getMinDateTime() {
        List<AccountModel> accountList = author().queryBuilder()
                .orderAsc(AccountModelDao.Properties.DateTime)
                .offset(0)
                .limit(1)
                .list();
        if (accountList != null && accountList.size() > 0) {
            LocalDateTime dateTime = accountList.get(0).getDateTime();
            int year = dateTime.getYear();
            if (year == LocalDateTime.now().getYear())
                return dateTime;
        }
        return null;
    }


    /**
     * 最大时间
     */
    public LocalDateTime getMaxDateTime() {
        List<AccountModel> accountList = author().queryBuilder()
                .orderDesc(AccountModelDao.Properties.DateTime)
                .offset(0)
                .limit(1)
                .list();
        if (accountList != null && accountList.size() > 0) {
            LocalDateTime dateTime = accountList.get(0).getDateTime();
            int year = dateTime.getYear();
            if (year == LocalDateTime.now().getYear())
                return dateTime;
        }
        return null;
    }


}
