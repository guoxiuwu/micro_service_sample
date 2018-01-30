package com.ebao.gs.sp.rc.common.utils

import java.security.MessageDigest

/**
 * Created by xiuwu.guo on 11/6/2017.
 */
class Func {

    static String MD5(String md5) {
        if (md5 == null) {
            return null
        }
        try {
            MessageDigest md = MessageDigest.getInstance("MD5")
            byte[] array = md.digest(md5.getBytes())
            StringBuffer sb = new StringBuffer()
            for (int i = 0; i < array.length; ++i) {
                sb.append(Integer.toHexString((array[i] & 0xFF) | 0x100).substring(1, 3))
            }
            return sb.toString();
        } catch (java.security.NoSuchAlgorithmException e) {
            // do nothing
        }
        return null
    }

    static Date systemData() {
        return new Date()
    }

}
