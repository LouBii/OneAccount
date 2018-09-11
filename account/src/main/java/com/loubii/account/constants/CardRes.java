package com.loubii.account.constants;

import android.widget.ImageView;
import android.widget.TextView;

import com.loubii.account.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

/**
 * Created by luo on 2016/4/29.
 * 银行卡相关资源
 */
public class CardRes {
    public static int TYPE_CHUXU = 1;
    public static int TYPE_CREDIT = 2;
    //银行名称集合
    private static String[] cardName = new String[]{"工商银行", "中国银行", "中信银行", "民生银行",
            "招商银行", "农业银行", "邮政银行", "建设银行",
            "交通银行", "平安银行", "光大银行", "广发银行",
            "华夏银行", "上海银行", "浦发银行", "兴业银行"};

    private static String cardNameWithRedBg = "^(中信银行|工商银行|中国银行|招商银行|华夏银行|广发银行|北京银行|光大银行|平安银行|上海银行)";
    private static String cardNameWithGreenBg = "^(农业银行|邮政银行|民生银行)";
    private static String cardNameWithBlueBg = "^(建设银行|交通银行|兴业银行|浦发银行)";

    //银行图标资源集合
    private static int[] cardImg = new int[]{R.drawable.icon_bank_gs, R.drawable.icon_bank_zg, R.drawable.icon_bank_zx, R.drawable.icon_bank_ms,
            R.drawable.icon_bank_zs, R.drawable.icon_bank_ny, R.drawable.icon_bank_yz, R.drawable.icon_bank_js,
            R.drawable.icon_bank_jt, R.drawable.icon_bank_pa, R.drawable.icon_bank_gd, R.drawable.icon_bank_gf,
            R.drawable.icon_bank_hx, R.drawable.icon_bank_sh, R.drawable.icon_bank_pf, R.drawable.icon_bank_xy};

    /**
     * 获取数据列表
     */
    public static List<Map<String, Object>> getAdapterData() {
        List<Map<String, Object>> mList = new ArrayList<>();
        for (int i = 0; i < cardImg.length; i++) {
            Map<String, Object> map = new HashMap<>();
            map.put("img", cardImg[i]);
            map.put("name", cardName[i]);
            mList.add(map);
        }
        return mList;
    }

    /**
     * 获取指定位置银行卡名称
     */
    public static String getCardName(int index) {
        return cardName[index];
    }

    /**
     * 获取指定位置银行卡图标
     */
    public static int getImg(int index) {
        return cardImg[index];
    }

    /**
     * 获取银行卡列表不同颜色背景资源
     * @param cardName 银行名称
     * @return
     */
    public static int getCardBg(String cardName) {
        if (Pattern.matches(cardNameWithRedBg, cardName))
            return R.drawable.lin_card_bg_red;
        else if (Pattern.matches(cardNameWithBlueBg, cardName))
            return R.drawable.lin_card_bg_blue;
        else if (Pattern.matches(cardNameWithGreenBg, cardName))
            return R.drawable.lin_card_bg_green;
        else
            return -1;
    }
//    /**
//     * 通过银行卡名称设置图片资源
//     */
//    public static void setImgByName(String name, ImageView ivaBg, ImageView ivRbank) {
//        if (name.contains("民生银行")) {
//            ivRbank.setImageResource(R.drawable.icon_rbank_ms);
//            ivaBg.setImageResource(R.drawable.bg_bank_ms);
//        }
//        if (name.contains("工商银行")) {
//            ivRbank.setImageResource(R.drawable.icon_rbank_gs);
//            ivaBg.setImageResource(R.drawable.bg_bank_gs);
//        }
//        if (name.contains("中国银行")) {
//            ivRbank.setImageResource(R.drawable.icon_rbank_zg);
//            ivaBg.setImageResource(R.drawable.bg_bank_zg);
//        }
//        if (name.contains("中信银行")) {
//            ivRbank.setImageResource(R.drawable.icon_rbank_zx);
//            ivaBg.setImageResource(R.drawable.bg_bank_zx);
//        }
//        if (name.contains("招商银行")) {
//            ivRbank.setImageResource(R.drawable.icon_rbank_zs);
//            ivaBg.setImageResource(R.drawable.bg_bank_zs);
//        }
//        if (name.contains("农业银行")) {
//            ivRbank.setImageResource(R.drawable.icon_rbank_ny);
//            ivaBg.setImageResource(R.drawable.bg_bank_ny);
//        }
//        if (name.contains("邮政")) {
//            ivRbank.setImageResource(R.drawable.icon_rbank_yz);
//            ivaBg.setImageResource(R.drawable.bg_bank_yz);
//        }
//        if (name.contains("建设银行")) {
//            ivRbank.setImageResource(R.drawable.icon_rbank_js);
//            ivaBg.setImageResource(R.drawable.bg_bank_js);
//        }
//        if (name.contains("交通银行")) {
//            ivRbank.setImageResource(R.drawable.icon_rbank_jt);
//            ivaBg.setImageResource(R.drawable.bg_bank_jt);
//        }
//        if (name.contains("平安银行")) {
//            ivRbank.setImageResource(R.drawable.icon_rbank_pa);
//            ivaBg.setImageResource(R.drawable.bg_bank_pa);
//        }
//        if (name.contains("光大银行")) {
//            ivRbank.setImageResource(R.drawable.icon_rbank_gd);
//            ivaBg.setImageResource(R.drawable.bg_bank_gd);
//        }
//        if (name.contains("广发银行")) {
//            ivRbank.setImageResource(R.drawable.icon_rbank_gf);
//            ivaBg.setImageResource(R.drawable.bg_bank_gf);
//        }
//        if (name.contains("华夏银行")) {
//            ivRbank.setImageResource(R.drawable.icon_rbank_hx);
//            ivaBg.setImageResource(R.drawable.bg_bank_hx);
//        }
//        if (name.contains("北京银行")) {
//            ivRbank.setImageResource(R.drawable.icon_rbank_bj);
//            ivaBg.setImageResource(R.drawable.bg_bank_bj);
//        }
//        if (name.contains("上海银行")) {
//            ivRbank.setImageResource(R.drawable.icon_rbank_sh);
//            ivaBg.setImageResource(R.drawable.bg_bank_sh);
//        }
//        if (name.contains("浦发银行")) {
//            ivRbank.setImageResource(R.drawable.icon_rbank_pf);
//            ivaBg.setImageResource(R.drawable.bg_bank_pf);
//        }
//        if (name.contains("兴业银行")) {
//            ivRbank.setImageResource(R.drawable.icon_rbank_xy);
//            ivaBg.setImageResource(R.drawable.bg_bank_xy);
//        }
//        if (name.contains("广州银行")) {
//            ivRbank.setImageResource(R.drawable.icon_rbank_gz);
//            ivaBg.setImageResource(R.drawable.bg_bank_gz);
//        }
//
////        switch (name) {
////            case "中国民生银行":
////                ivRbank.setImageResource(R.drawable.icon_rbank_ms);
////                ivaBg.setImageResource(R.drawable.bg_bank_ms);
////                break;
////            case "中国工商银行":
////                ivRbank.setImageResource(R.drawable.icon_rbank_gs);
////                ivaBg.setImageResource(R.drawable.bg_bank_gs);
////                break;
////            default:
////                break;
////        }
//    }

    //设置别经图片
    public static void setImgByName(String cardName, ImageView imageView) {
        if (cardName.contains("民生银行")) {
            imageView.setImageResource(R.drawable.icon_bank_ms);
        }
        if (cardName.contains("工商银行")) {
            imageView.setImageResource(R.drawable.icon_bank_gs);
        }
        if (cardName.contains("中国银行")) {
            imageView.setImageResource(R.drawable.icon_bank_zg);
        }
        if (cardName.contains("中信银行")) {
            imageView.setImageResource(R.drawable.icon_bank_zx);
        }
        if (cardName.contains("招商银行")) {
            imageView.setImageResource(R.drawable.icon_bank_zs);
        }
        if (cardName.contains("农业银行")) {
            imageView.setImageResource(R.drawable.icon_bank_ny);
        }
        if (cardName.contains("邮政")) {
            imageView.setImageResource(R.drawable.icon_bank_yz);
        }
        if (cardName.contains("建设银行")) {
            imageView.setImageResource(R.drawable.icon_bank_js);
        }
        if (cardName.contains("交通银行")) {
            imageView.setImageResource(R.drawable.icon_bank_jt);
        }
        if (cardName.contains("平安银行")) {
            imageView.setImageResource(R.drawable.icon_bank_pa);
        }
        if (cardName.contains("光大银行")) {
            imageView.setImageResource(R.drawable.icon_bank_gd);
        }
        if (cardName.contains("广发银行")) {
            imageView.setImageResource(R.drawable.icon_bank_gf);
        }
        if (cardName.contains("华夏银行")) {
            imageView.setImageResource(R.drawable.icon_bank_hx);
        }
        if (cardName.contains("上海银行")) {
            imageView.setImageResource(R.drawable.icon_bank_sh);
        }
        if (cardName.contains("浦发银行")) {
            imageView.setImageResource(R.drawable.icon_bank_pf);
        }
        if (cardName.contains("兴业银行")) {
            imageView.setImageResource(R.drawable.icon_bank_xy);
        }

    }


    /**
     * 设置银行卡限额
     *
     * @param name
     * @param tvOne   单笔限额
     * @param tvDay   单日限额
     * @param tvMonth 单月限额
     */
    public static void setLimitCount(String name, int cardType, TextView tvOne, TextView tvDay, TextView tvMonth) {
        if (cardType == TYPE_CHUXU) {
            if (name.contains("民生银行")) {

            }
            if (name.contains("工商银行")) {
                tvOne.setText("10000");
                tvDay.setText("20000");
                tvMonth.setText("20000");

            }
            if (name.contains("中国银行")) {
                tvOne.setText("5000");
                tvDay.setText("5000");
                tvMonth.setText("");
            }
            if (name.contains("中信银行")) {
                tvOne.setText("20000");
                tvDay.setText("20000");
                tvMonth.setText("50000");
            }
            if (name.contains("招商银行")) {

            }
            if (name.contains("农业银行")) {
                tvOne.setText("5000");
                tvDay.setText("5000");
                tvMonth.setText("");
            }
            if (name.contains("邮政")) {
                tvOne.setText("5000");
                tvDay.setText("5000");
                tvMonth.setText("");
            }
            if (name.contains("建设银行")) {
                tvOne.setText("5000");
                tvDay.setText("5000");
                tvMonth.setText("");
            }
            if (name.contains("交通银行")) {
                tvOne.setText("5000");
                tvDay.setText("5000");
                tvMonth.setText("");
            }
            if (name.contains("平安银行")) {
                tvOne.setText("20000");
                tvDay.setText("20000");
                tvMonth.setText("50000");
            }
            if (name.contains("光大银行")) {
                tvOne.setText("20000");
                tvDay.setText("20000");
                tvMonth.setText("50000");
            }
            if (name.contains("广发银行")) {

            }
            if (name.contains("华夏银行")) {
                tvOne.setText("5000");
                tvDay.setText("10000");
                tvMonth.setText("10000");
            }
            if (name.contains("北京银行")) {
                tvOne.setText("5000");
                tvDay.setText("10000");
                tvMonth.setText("20000");
            }
            if (name.contains("上海银行")) {
                tvOne.setText("5000");
                tvDay.setText("10000");
                tvMonth.setText("10000");
            }
            if (name.contains("浦发银行")) {

            }
            if (name.contains("兴业银行")) {
                tvOne.setText("5000");
                tvDay.setText("5000");
                tvMonth.setText("");
            }
            if (name.contains("广州银行")) {
                tvOne.setText("20000");
                tvDay.setText("50000");
                tvMonth.setText("50000");
            }
        } else {
            tvOne.setText("50000");
            tvDay.setText("50000");
            tvMonth.setText("");
            if (name.contains("交通银行") || name.contains("农业银行")) {
                tvOne.setText("20000");
            }
        }

    }


}
