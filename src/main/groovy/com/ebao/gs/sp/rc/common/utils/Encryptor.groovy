package com.ebao.gs.sp.rc.common.utils

import org.jasypt.encryption.pbe.StandardPBEStringEncryptor
import org.jasypt.salt.StringFixedSaltGenerator

/**
 * Created by xiuwu.guo on 11/6/2017.
 */
final class Encryptor {

    static String salt = "21Wk365847qazxsw@s!*"

    private static final String algorithm = "PBEWithMD5AndDES"
    private static final String password = "!!qazx2Ysw@#edcvfr\$"

    private static final StandardPBEStringEncryptor encryptor = new StandardPBEStringEncryptor(
            algorithm: algorithm,
            password: password,
            saltGenerator: new StringFixedSaltGenerator(salt)
    )

    static String encrypt(String toBeEncryptedMessage) {
        if (toBeEncryptedMessage != null && toBeEncryptedMessage.trim() != "") {
            return encryptor.encrypt(toBeEncryptedMessage)
        }
        return null
    }

    static String decrypt(String encryptedMessage) {
        if (encryptedMessage != null && encryptedMessage.trim() != "") {
            return encryptor.decrypt(encryptedMessage)
        }
        return null
    }

    static String toJsonAndEncrypt(Object object) {
        encrypt(JSONUtils.toJSON(object))
    }

    static Object decryptToJsonAndConvertToList(Class convertedClass, String encryptedMessage) {
        if (encryptedMessage != null && encryptedMessage.trim() != "") {
            String json = encryptor.decrypt(encryptedMessage)
            return JSONUtils.fromJSONAsList(json, convertedClass)
        }
        return null
    }

    static Object decryptToJsonAndConvertToObject(Class convertedClass, String encryptedMessage) {
        if (encryptedMessage != null && encryptedMessage.trim() != "") {
            String json = encryptor.decrypt(encryptedMessage)
            return JSONUtils.fromJSON(json, convertedClass)
        }
        return null
    }

    static Map decryptToJsonAndConvertToMap(String encryptedMessage) {
        if (encryptedMessage != null && encryptedMessage.trim() != "") {
            String json = encryptor.decrypt(encryptedMessage)
            return JSONUtils.parseMap(json)
        }
        return null
    }
}
