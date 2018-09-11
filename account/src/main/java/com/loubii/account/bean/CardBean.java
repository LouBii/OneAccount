package com.loubii.account.bean;

/**
 * @author luo
 * @date 2017/8/25
 */
public class CardBean {
    private int id;

    //银行名称
    private String cardName;
    //银行卡号
    private String cardId;
    //银行卡类型（储蓄卡/信用卡）
    private String cardType;
    //预留手机号码
    private String reservedPhone;
    //绑定id
    private String bindingId;
    //所属银行代码
    private String bankCode;

    public String getReservedPhone() {
        return reservedPhone;
    }

    public void setReservedPhone(String reservedPhone) {
        this.reservedPhone = reservedPhone;
    }

    public String getBindingId() {
        return bindingId;
    }

    public void setBindingId(String bindingId) {
        this.bindingId = bindingId;
    }

    public String getBankCode() {
        return bankCode;
    }

    public void setBankCode(String bankCode) {
        this.bankCode = bankCode;
    }


    public String getCardType() {
        return cardType;
    }

    public void setCardType(String cardType) {
        this.cardType = cardType;
    }

    public String getCardName() {
        return cardName;
    }

    public void setCardName(String cardName) {
        this.cardName = cardName;
    }

    public String getCardId() {
        return cardId;
    }

    public void setCardId(String cardId) {
        this.cardId = cardId;
    }
}
