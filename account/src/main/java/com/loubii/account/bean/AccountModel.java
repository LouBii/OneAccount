package com.loubii.account.bean;
import com.loubii.account.util.LocalDateTimeConverter;

import org.greenrobot.greendao.annotation.Convert;
import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.annotation.Id;

import java.time.LocalDateTime;

@Entity
public class AccountModel implements Comparable<AccountModel> {

    @Id(autoincrement = true)
    private Long id; // 用户唯一标识

    // 记账金额
    private float count;
    // 支出/收入 1：支出  2：收入
    private int outIntype;
    // 具体类型
    private String detailType;
    // 类型图标
    private int picRes;
    // 记账日期 (yyyy-MM-dd HH:mm)
    @Convert(converter = LocalDateTimeConverter.class,columnType = String.class)
    private LocalDateTime dateTime;
    // 备注
    private String note;
    // 标签
    private String remark;

    public AccountModel() {
    }

    @Generated(hash = 547056644)
    public AccountModel(Long id, float count, int outIntype, String detailType, int picRes, LocalDateTime dateTime, String note, String remark) {
        this.id = id;
        this.count = count;
        this.outIntype = outIntype;
        this.detailType = detailType;
        this.picRes = picRes;
        this.dateTime = dateTime;
        this.note = note;
        this.remark = remark;
    }

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
    public void setDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }
    public LocalDateTime getDateTime() {
        return this.dateTime;
    }
    public int getPicRes() {
        return this.picRes;
    }
    public void setPicRes(int picRes) {
        this.picRes = picRes;
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
