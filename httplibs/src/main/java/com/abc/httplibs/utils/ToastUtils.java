package com.abc.httplibs.utils;

import android.text.TextUtils;
import android.widget.Toast;

import com.abc.httplibs.HttpConfig;

/**
 * @name lz
 * @time 2019/7/18 16:20
 */
public class ToastUtils {

    public static void showToast(String msg) {
        if (TextUtils.isEmpty(msg)) {
            return;
        }
        Toast.makeText(HttpConfig.getContext().getApplicationContext(), msg, Toast.LENGTH_LONG).show();
    }
}
