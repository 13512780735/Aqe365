package com.likeit.aqe365.utils;

import android.content.Context;

import com.likeit.aqe365.activity.login.activity.LoginActivity;

import java.text.SimpleDateFormat;

public class SignUtils {


    public static String getSign(Context mContex) {
        String sign = null;
        String Lkey = "uKmy0e45wgh0B3e7";
        String Lappid = "200001";
        String timestamp = String.valueOf(DateUtil.getTime());//获取系统时间戳
        String randomstr = RandomUtils.getRandomString(6);
        SharedPreferencesUtils.put(mContex, "timestamp", timestamp);
        SharedPreferencesUtils.put(mContex, "randomstr", randomstr);

        sign = MD5Utils.md5(SHAUtils.getSHA(Lappid + Lkey + randomstr + timestamp)) + "##" + timestamp + "##" + randomstr;
        return sign;
    }


}
