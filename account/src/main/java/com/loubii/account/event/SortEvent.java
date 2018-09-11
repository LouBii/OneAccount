package com.loubii.account.event;

/**
 * @author luo
 * @date 2017/8/23
 */
public class SortEvent {
    private String message;

    public SortEvent(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
