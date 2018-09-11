package com.loubii.account.event;

/**
 * @author luo
 * @date 2017/9/4
 */
public class BaseEvent<T> {
    private T message;

    public T getMessage() {
        return message;
    }

    public void setMessage(T message) {
        this.message = message;
    }
}
