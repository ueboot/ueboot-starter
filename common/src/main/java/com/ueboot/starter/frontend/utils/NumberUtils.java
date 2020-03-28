/*
 * Copyright (c)  2018, XiQiao
 * All rights reserved.
 *
 * Id:NumberUtils.java   2018-08-22 下午4:17 andy
 */
package com.ueboot.starter.frontend.utils;


public class NumberUtils {

    /**
     * 获取指定长度的数字字符串，不足前面补零
     * @param number
     * @param length
     * @return
     */
    public static String getNumberByLength(String number, int length) {
        if (number.length() < length) {
            return NumberUtils.getNumberByLength("0" + number, length);
        } else {
            return number;
        }
    }
}
