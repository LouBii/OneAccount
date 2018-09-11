package com.loubii.account.util;

import com.loubii.account.bean.AccountModel;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @author luo
 * @date 2017/11/1
 */
public class AccListUtil {

    /**
     * count求和
     * @param list
     * @return
     */
    public static float sum(List<AccountModel> list) {
        float sum = 0f;
        if (list != null && list.size() > 0) {
            for (AccountModel accountModel : list) {
                sum += accountModel.getCount();
            }
        }
        return sum;
    }

    /**
     * list count列最大值
     * @param list
     * @return
     */
    public static float max(List<AccountModel> list) {
        return Collections.max(list).getCount();
//        float sum = 0f;
//        if (list != null && list.size() > 0) {
//            for (AccountModel accountModel : list) {
//                sum += accountModel.getCount();
//            }
//        }
//        return sum;
    }


    public static List<AccountModel> removeRepeat(List<AccountModel> accList){
        if (accList != null && accList.size() > 0) {
            List<AccountModel> list = new ArrayList<>(accList);
            for (int i = 0; i < list.size()-1; i++) {
                for (int j = list.size()-1; j > i; j--) {
                    if (list.get(j).getDetailType().equals(list.get(i).getDetailType())) {
                        list.remove(j);
                    }
                }
            }
            return list;
        } else
            return new ArrayList<>();

    }
}
