package com.loubii.account.util;

import java.util.List;

/**
 * @author luo
 * @date 2017/8/23
 */
public class CompareUtil {
    /**
     * 队列比较
     * @param <T>
     * @param a
     * @param b
     * @return
     */
    public static <T extends Object> boolean compare(List<T> a, List<T> b) {
        if(a.size() != b.size())
            return false;
//        Collections.sort(a);
//        Collections.sort(b);
        for(int i=0;i<a.size();i++){
            if(!a.get(i).equals(b.get(i)))
                return false;
        }
        return true;
    }
}
