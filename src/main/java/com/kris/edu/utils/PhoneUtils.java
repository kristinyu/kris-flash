package com.kris.edu.utils;

import org.apache.commons.lang3.StringUtils;

import java.util.regex.Pattern;

public class PhoneUtils {
    private static final Pattern phonePattern = Pattern.compile("1\\d{10}");

    public static boolean isPhone(String phone) {
        if (StringUtils.isBlank(phone)) {
            return false;
        }
        return phonePattern.matcher(phone).matches();
    }

    public static String mask(String phone) {
        if (isPhone(phone)) {
            return phone.substring(0, 3) + "****" + phone.substring(7, 11);
        }
        return phone;
    }

    public static String maskPhone(String phone) {
        if (isPhone(phone)) {
            return phone.substring(0, 3) + "****" + phone.substring(7, 11);
        }
        return phone;
    }
}
