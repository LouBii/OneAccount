package com.loubii.account.bean;

/**
 * @author luo
 * @date 2017/8/17
 */
public class RecycleClassifyPagerBean {
    private String name;
    private int iconRes;
    private int iconResGray;
    private int id;
    /** 是否被选中 */
    private boolean checked;

    public boolean isChecked() {
        return checked;
    }

    public void setChecked(boolean checked) {
        this.checked = checked;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIconResGray() {
        return iconResGray;
    }

    public void setIconResGray(int iconResGray) {
        this.iconResGray = iconResGray;
    }

    public RecycleClassifyPagerBean() {
    }

    public RecycleClassifyPagerBean(int id, String name, int iconRes, int iconResGray) {
        this.id = id;
        this.name = name;
        this.iconRes = iconRes;
        this.iconResGray = iconResGray;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getIconRes() {
        return iconRes;
    }

    public void setIconRes(int iconRes) {
        this.iconRes = iconRes;
    }


}
