package com.loubii.account.event;

import android.content.Intent;

/**
 * @author luo
 * @date 2017/8/23
 */
public class AccountChangeEvent extends BaseEvent {
    private Intent intent;

    public AccountChangeEvent(Intent intent) {
        this.intent = intent;
    }

    public Intent getMessage() {
        return intent;
    }

    public void setMessage(Intent intent) {
        this.intent = intent;
    }
}
