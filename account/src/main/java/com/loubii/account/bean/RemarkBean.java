package com.loubii.account.bean;

/**
 * @author luo
 * @date 2017/8/25
 */
public class RemarkBean {
    private int id;
    private String name;
    /** 是否被选中 */
    private boolean checked;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isChecked() {
        return checked;
    }

    public void setChecked(boolean checked) {
        this.checked = checked;
    }
}
