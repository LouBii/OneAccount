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

import java.util.Calendar;
import java.util.Date;
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
     * @param startTime
     * @param endTime
     * @return
     */
    public List<AccountModel> getAccountList(int accountType, Date startTime, Date endTime) {
        QueryBuilder<AccountModel> builder = author().queryBuilder()
                .where(AccountModelDao.Properties.Time.between(startTime, endTime));
        if (accountType != (Extra.ACCOUNT_TYPE_ALL))
            builder.where(AccountModelDao.Properties.OutIntype.eq(accountType));
        builder.orderAsc(AccountModelDao.Properties.Time);
        return builder.list();
//        List<AccountModel> accountList = author().queryBuilder()
//                .where(AccountModelDao.Properties.Time.between
//                        (startTime, endTime), AccountModelDao.Properties.OutIntype.eq(accountType))
//                .orderAsc(AccountModelDao.Properties.Time)
////                .offset(offSet * Config.LIST_LOAD_NUM)
////                .limit(Config.LIST_LOAD_NUM)
//                .list();
//        return accountList;
    }

    /**
     *
     * @param accountType 1:支出 ， 2收入
     * @param detailType 具体类型（餐饮等）
     * @param startTime
     * @param endTime
     * @return
     */
    public List<AccountModel> getAccountList(int accountType, String detailType, Date startTime, Date endTime) {

        QueryBuilder<AccountModel> builder = author().queryBuilder()
                .where(AccountModelDao.Properties.Time.between(startTime, endTime),
                        AccountModelDao.Properties.OutIntype.eq(accountType));
        if ( !detailType.equals(Extra.DETAIL_TYPE_DEFAULT))
            builder.where(AccountModelDao.Properties.DetailType.eq(detailType));
        builder.orderAsc(AccountModelDao.Properties.Time);
        //List<AccountModel> accountList  .list();
        return builder.list();

//        List<AccountModel> accountList = author().queryBuilder()
//                .where(AccountModelDao.Properties.Time.between(startTime, endTime),
//                        AccountModelDao.Properties.OutIntype.eq(accountType),
//                        AccountModelDao.Properties.DetailType.eq(detailType))
//                .orderAsc(AccountModelDao.Properties.Time)
////                .offset(offSet * Config.LIST_LOAD_NUM)
////                .limit(Config.LIST_LOAD_NUM)
//                .list();
//        return accountList;
    }

    /**
     * 最小时间
     */
    public Date getMinDate() {
        List<AccountModel> accountList = author().queryBuilder()
                .orderAsc(AccountModelDao.Properties.Time)
                .offset(0)
                .limit(1)
                .list();
        if (accountList != null && accountList.size() > 0) {
            Date date = accountList.get(0).getTime();
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(date);
            int year = calendar.get(Calendar.YEAR);
            if (year == Calendar.getInstance().get(Calendar.YEAR))
                return date;
            else
                return TimeUtil.getBeginDayOfYear(new Date());
        } else
            return null;

    }


    /**
     * 最大时间
     */
    public Date getMaxDate() {
        List<AccountModel> accountList = author().queryBuilder()
                .orderDesc(AccountModelDao.Properties.Time)
                .offset(0)
                .limit(1)
                .list();
        if (accountList != null && accountList.size() > 0) {
            Date date = accountList.get(0).getTime();
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(date);
            int year = calendar.get(Calendar.YEAR);
            if (year == Calendar.getInstance().get(Calendar.YEAR))
                return date;
            else
                return null;
        } else
            return null;

    }


}
