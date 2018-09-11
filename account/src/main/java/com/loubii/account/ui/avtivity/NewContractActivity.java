package com.loubii.account.ui.avtivity;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.loubii.account.R;

public class NewContractActivity extends BaseToolBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void initData() {

    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_new_contract;
    }

    @Override
    protected void initView() {
        setTitle("加入契约");
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_finish, menu);
        menu.getItem(0).setTitle("玩法");
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        return super.onOptionsItemSelected(item);
    }
}
