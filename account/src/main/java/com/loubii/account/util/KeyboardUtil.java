package com.loubii.account.util;


import android.app.Activity;
import android.content.Context;
import android.inputmethodservice.Keyboard;
import android.inputmethodservice.KeyboardView;
import android.text.Editable;
import android.view.KeyEvent;
import android.view.View;
import android.widget.EditText;

import com.loubii.account.R;

import java.text.DecimalFormat;

/**
 * @author luo
 * @date 2017/8/18
 */
public class KeyboardUtil {
    private Context mContext;
    private Activity mActivity;
    private KeyboardView mKeyboardView;
    private Keyboard mNumberKeyboard; // 数字键盘

    private EditText mEditText;
    private onKeyListener keyListener;

    public KeyboardUtil(Context context, Activity activity, EditText editText) {
        mContext = context;
        mActivity = activity;
        mEditText = editText;

        mEditText.setFocusable(true);
        mNumberKeyboard = new Keyboard(mContext, R.xml.keyboard_number);
        mKeyboardView = (KeyboardView) mActivity.findViewById(R.id.keyboard_view);
        mKeyboardView.setKeyboard(mNumberKeyboard);
        mKeyboardView.setEnabled(true);
        mKeyboardView.setPreviewEnabled(false);
        mKeyboardView.setOnKeyboardActionListener(listener);
    }

    private KeyboardView.OnKeyboardActionListener listener = new KeyboardView.OnKeyboardActionListener() {
        @Override
        public void onPress(int primaryCode) {

        }

        @Override
        public void onRelease(int primaryCode) {

        }

        @Override
        public void onKey(int primaryCode, int[] keyCodes) {
            Editable editable = mEditText.getText();
            int start = mEditText.getSelectionStart();
            String strEdit = editable.toString();
            int length = editable.toString().length();
            String strLast = "";
            if (length > 0) {
                strLast = editable.toString().substring(length - 1, length);
            }
            if (primaryCode == Keyboard.KEYCODE_CANCEL) { // cancel

                if (strEdit.length() != 0) {
                    float num = Float.parseFloat(strEdit.replaceAll("\\-", "").replaceAll("\\+",""));
                    if (num != 0f) {
                        //hideKeyboard();
                        doFinish(strEdit);
                    }
                }

            } else if (primaryCode == Keyboard.KEYCODE_DELETE) { // 回退
                if (editable != null && editable.length() > 0) {
                    if (start > 0) {
                        editable.delete(start - 1, start);
                    }
                }
            }

//            else if (primaryCode == KeyEvent.KEYCODE_NUMPAD_ADD) { // 加
//                mEditText.setText(mEditText.getText() + "+");
//            } else if (primaryCode == 156) { // 减
//                mEditText.setText(mEditText.getText() + "-");
//            }

            else if (primaryCode == 57421) { // 清除
                mEditText.setText("");
            } else { // 输入键盘值
                if (primaryCode == KeyEvent.KEYCODE_NUMPAD_ADD) { // 加
                    if (strLast.equals("+") || strLast.equals("-") || strLast.equals(".") || length == 0)
                        return;
                    //如果再次输入加号之前已经加减过一次，且字符最后一位并不是加减号
                    doAdd(editable, strLast, "+");

                    editable.insert(start, "+");
                } else if (primaryCode == KeyEvent.KEYCODE_NUMPAD_SUBTRACT) { // 减
                    if (strLast.equals("+") || strLast.equals("-") || strLast.equals(".") || length == 0)
                        return;
                    doAdd(editable, strLast, "-");
                    editable.insert(start, "-");
                } else if (primaryCode == 46) { // 小数点
                    if (strLast.equals("+") || strLast.equals("-") || strLast.equals(".") || length == 0)
                        return;
                    // TODO: 2017/8/21

                    if (editable.toString().contains("+")) {
                        if (editable.toString().split("\\+")[1].contains("."))
                            return;
                    } else if (editable.toString().contains("-")) {
                        if (editable.toString().startsWith("-")) {
                            String[] str = editable.toString().substring(1, editable.toString().length()).split("\\-");
                            if (str[1].contains("."))
                                return;
                        } else {
                            if (editable.toString().split("\\-")[1].contains("."))
                                return;
                        }
                    } else if (editable.toString().contains("."))
                        return;

                    editable.insert(start, ".");
                } else
                    editable.insert(start, Character.toString((char) primaryCode));
            }
        }

        @Override
        public void onText(CharSequence text) {

        }

        @Override
        public void swipeLeft() {

        }

        @Override
        public void swipeRight() {

        }

        @Override
        public void swipeDown() {

        }

        @Override
        public void swipeUp() {

        }
    };

    private void doFinish(String strEdit) {
        DecimalFormat decimalFormat = new DecimalFormat(".00");
        String result;
        if (strEdit.endsWith("+") || strEdit.endsWith("-")) {
            result = decimalFormat.format(Float.parseFloat(strEdit.substring(0, strEdit.length() - 1)));
            keyListener.onKeyFinish(Float.parseFloat(result));
        }
        else if (strEdit.contains("+") ) {
            String[] strArr = strEdit.split("\\+");
            result = decimalFormat.format(Float.parseFloat(strArr[0]) + Float.parseFloat(strArr[1]));
            keyListener.onKeyFinish(Float.parseFloat(result));
        } else if (strEdit.contains("-") ) {
            float sum;
            if (strEdit.startsWith("-")) {
                String[] str = strEdit.substring(1, strEdit.length()).split("\\-");
                sum = -Float.parseFloat(str[0]) - Float.parseFloat(str[1]);
            } else {
                String[] str = strEdit.split("\\-");
                sum = Float.parseFloat(str[0]) - Float.parseFloat(str[1]);
            }
            result = decimalFormat.format(sum);
            keyListener.onKeyFinish(Float.parseFloat(result));
        } else
            keyListener.onKeyFinish(Float.parseFloat(strEdit));
    }

    private void doAdd(Editable editable, String strLast, String flag) {
        if ((editable.toString().contains("+"))
                && (!strLast.equals("+") || !strLast.equals("-"))) {
            String[] str = editable.toString().split("\\+");
            float sum = Float.parseFloat(str[0]) + Float.parseFloat(str[1]);
            DecimalFormat decimalFormat = new DecimalFormat(".00");//构造方法的字符格式这里如果小数不足2位,会以0补足.
            mEditText.setText(decimalFormat.format(sum) + flag);
            //设置光标至末尾
            mEditText.setSelection(mEditText.length());
            return;
        }
        if ((editable.toString().contains("-"))
                && (!strLast.equals("+") || !strLast.equals("-"))) {
            float sum;
            if (editable.toString().startsWith("-")) {
                // TODO: 2017/10/24 计算崩溃
                String[] str = editable.toString().substring(1, editable.toString().length()).split("\\-");
                sum = -Float.parseFloat(str[0]) - Float.parseFloat(str[1]);
            } else {
                String[] str = editable.toString().split("\\-");
                sum = Float.parseFloat(str[0]) - Float.parseFloat(str[1]);
            }
            DecimalFormat decimalFormat = new DecimalFormat(".00");//构造方法的字符格式这里如果小数不足2位,会以0补足.
            mEditText.setText(decimalFormat.format(sum) + flag);
            //设置光标至末尾
            mEditText.setSelection(mEditText.length());
            return;
        }
    }


    public void hideKeyboard() {
        int visibility = mKeyboardView.getVisibility();
        if (visibility == View.VISIBLE) {
            mKeyboardView.setVisibility(View.INVISIBLE);
        }
    }

    public void showKeyboard() {
        int visibility = mKeyboardView.getVisibility();
        if (visibility == View.GONE || visibility == View.INVISIBLE) {
            mKeyboardView.setVisibility(View.VISIBLE);
        }
    }

    public interface onKeyListener {
        public void onKeyFinish(float result);
    }

    public void setOnKeyListener(onKeyListener listener) {
        this.keyListener = listener;
    }
}
