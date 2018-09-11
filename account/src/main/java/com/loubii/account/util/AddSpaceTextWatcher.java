package com.loubii.account.util;

import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;

/**
 * @author luo
 * @date 2017/8/30
 */
public class AddSpaceTextWatcher implements TextWatcher {

    private final EditText editText;

    public AddSpaceTextWatcher(EditText editText) {
        this.editText = editText;
    }

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {

    }

    @Override
    public void afterTextChanged(Editable s) {
        String str = s.toString().trim().replace(" ", "");
        String result = "";
        if (str.length() >= 4) {
            editText.removeTextChangedListener(this);
            for (int i = 0; i < str.length(); i++) {
                result += str.charAt(i);
                if ((i + 1) % 4 == 0) {
                    result += " ";
                }
            }
            if (result.endsWith(" ")) {
                result = result.substring(0, result.length() - 1);
            }
            editText.setText(result);
            editText.addTextChangedListener(this);
            editText.setSelection(editText.getText().toString().length());//焦点到输入框最后位置
        }
    }
}
