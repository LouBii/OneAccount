package com.loubii.account.bean;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.annotation.Id;

import java.util.Date;

/**
 * @author luo
 * @date 2017/10/23
 */
@Entity

//        (
//                // schema 名，多个 schema 时设置关联实体。插件产生不支持，需使用产生器
//                // schema = "myschema",
//
//                // 标记一个实体是否处于活动状态，活动实体有 update、delete、refresh 方法。默认为 false
//                active = false,
//
//                // 表名，默认为类名
//                nameInDb = "AWESOME_USERS",
//
//                // 定义多列索引
//                indexes = {
//                        @Index(value = "name DESC", unique = true)
//                },
//
//                // 标记是否创建表，默认 true。多实体对应一个表或者表已创建，不需要 greenDAO 创建时设置 false
//                createInDb = true,
//
//                // 是否产生所有参数构造器。默认为 true。无参构造器必定产生
//                generateConstructors = true,
//
//                // 如果没有 get/set 方法，是否生成。默认为 true
//                generateGettersSetters = true
//        )
public class AccountModel implements Comparable<AccountModel>{

    @Id(autoincrement = true)
    private Long id; //用户唯一标识

    // 唯一，默认索引
    //@Unique
    //private Long userId;
    //记账金额
    private float count;
    //支出/收入 1：支出  2：收入
    private int outIntype;
    //具体类型
    private String detailType;
    //类型图标
    private int picRes;
    //记账日期 (yyyy-MM-dd HH:mm)
    private Date time;
//    //记账日期 (年)
//    private int year;
//    //记账日期 (月)
//    private int month;
//    //记账日期 (日)
//    private int day;
    //备注
    private String note;
    //标签
    private String remark;
    public String getNote() {
        return this.note;
    }
    public void setNote(String note) {
        this.note = note;
    }
    public String getDetailType() {
        return this.detailType;
    }
    public void setDetailType(String detailType) {
        this.detailType = detailType;
    }
    public int getOutIntype() {
        return this.outIntype;
    }
    public void setOutIntype(int outIntype) {
        this.outIntype = outIntype;
    }
    public float getCount() {
        return this.count;
    }
    public void setCount(float count) {
        this.count = count;
    }
    public Long getId() {
        return this.id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getRemark() {
        return this.remark;
    }
    public void setRemark(String remark) {
        this.remark = remark;
    }
    public void setTime(Date time) {
        this.time = time;
    }
    public Date getTime() {
        return this.time;
    }
    public int getPicRes() {
        return this.picRes;
    }
    public void setPicRes(int picRes) {
        this.picRes = picRes;
    }
    public AccountModel(Long id, float count, int outIntype, String detailType, String note, String remark) {
        this.id = id;
        this.count = count;
        this.outIntype = outIntype;
        this.detailType = detailType;
        this.note = note;
        this.remark = remark;
    }
    public AccountModel() {
    }
    @Generated(hash = 741446720)
    public AccountModel(Long id, float count, int outIntype, String detailType, int picRes, Date time,
            String note, String remark) {
        this.id = id;
        this.count = count;
        this.outIntype = outIntype;
        this.detailType = detailType;
        this.picRes = picRes;
        this.time = time;
        this.note = note;
        this.remark = remark;
    }

    @Override
    public int compareTo(AccountModel o) {
        if (this.count < o.count)
            return -1;
        else if (this.count > o.count)
            return 1;
        else
            return 0;
    }
}
