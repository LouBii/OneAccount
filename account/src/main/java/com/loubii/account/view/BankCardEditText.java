package com.loubii.account.view;

import android.content.Context;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.AttributeSet;

import com.loubii.account.util.BankInfoBeanPart;

/**
 * @author luo
 * @date 2017/8/31
 */
public class BankCardEditText extends DeleteEditText {

    private boolean shouldStopChange = false;
    private final String WHITE_SPACE = " ";

    private OnCardPatternListener listener;

    public BankCardEditText(Context context) {
        super(context);
        init();
    }

    public BankCardEditText(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public BankCardEditText(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        format(getText());
        shouldStopChange = false;
        setFocusable(true);
        setEnabled(true);
        setFocusableInTouchMode(true);
        addTextChangedListener(new CardTextWatcher());
    }

    class CardTextWatcher implements TextWatcher {

        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {

        }

        @Override
        public void afterTextChanged(Editable editable) {
            format(editable);
        }
    }

    private void format(Editable editable) {
        if (shouldStopChange) {
            shouldStopChange = false;
            return;
        }

        shouldStopChange = true;

        String str = editable.toString().trim().replaceAll(WHITE_SPACE, "");
        int len = str.length();
        int courPos;

        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < len; i++) {
            builder.append(str.charAt(i));
            if (i == 3 || i == 7 || i == 11 || i == 15) {
                if (i != len - 1)
                    builder.append(WHITE_SPACE);
            }
        }
        courPos = builder.length();
        setText(builder.toString());
        setSelection(courPos);
        if (listener != null && getBankCardText().length() == 6) {
            //char[] ss = getBankCardText().toCharArray();
            BankInfoBeanPart bankinfobean = new BankInfoBeanPart(getBankCardText());
            listener.onCardPattern(bankinfobean.getBankName());
            //Logger.e(bankinfobean.getBankName());
        }
        if (listener != null && getBankCardText().length() < 6)
            listener.onCardPattern("");
    }

    public String getBankCardText() {
        return getText().toString().trim().replaceAll(" ", "");
    }

    public void setOnCardPatternListener(OnCardPatternListener listener) {
        this.listener = listener;
    }

    public interface OnCardPatternListener {
        void onCardPattern(String name);

    }
}
