package com.wgw.softdog;

import com.wgw.softdog.utils.AESEncrypter;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * @author wgw
 * @time 2018/8/21 14:42
 * @class describe
 */
public class Encrypt {

    public static String encryptMsg(String key,String value){
        StringBuilder encryValue=new StringBuilder();
        String keyMd = hashKeyForDisk(key);
        String aesValue = "";
        try {
            encryValue.append(AESEncrypter.encrypt(value,keyMd));
            encryValue.reverse();
            aesValue = encryValue.substring(encryValue.length()-3)+encryValue.substring(0,encryValue.length()-3);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return aesValue;
    }

    public static String decryptMsg(String key,String value){
        StringBuilder encryValue=new StringBuilder();
        String keyMd = hashKeyForDisk(key);
        String aesValue = "";
        try {
            encryValue.append(value.substring(3) + value.substring(0,3));
            encryValue.reverse();
            aesValue = AESEncrypter.decrypt(encryValue.toString(),keyMd);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return aesValue;
    }

    public static String encryptValue(String key,String value){
        String encryValue=null;
        try {
            encryValue = AESEncrypter.encrypt(value,key);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return encryValue;
    }

    /**
     * 把加密后的字符串后三位提到前面
     * @param key
     * @param value
     * @return
     */
    public static String encryptValue2(String key,String value){
        String encryValue=null;
        String encryValueChage = null;
        try {
            encryValue = AESEncrypter.encrypt(value,key);
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (null != encryValue){
            encryValueChage = encryValue.substring(encryValue.length()-3)+encryValue.substring(0,encryValue.length()-3);
        }
        return encryValueChage;
    }

    /**
     * 加密后翻转
     * @param key
     * @param value
     * @return
     */
    public static String encryptValue3(String key,String value){
        StringBuilder encryValue=new StringBuilder();
        String encryValueChage = null;
        try {
            encryValue.append(AESEncrypter.encrypt(value,key));
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (null != encryValue){
            encryValueChage = encryValue.reverse().toString();
        }
        return encryValueChage;
    }

    /**
     * A hashing method that changes a string (like a URL) into a hash suitable for using as a
     * disk filename.
     */
    public static String hashKeyForDisk(String key) {
        String cacheKey;
        try {
            final MessageDigest mDigest = MessageDigest.getInstance("MD5");
            mDigest.update(key.getBytes());
            cacheKey = bytesToHexString(mDigest.digest());
        } catch (NoSuchAlgorithmException e) {
            cacheKey = String.valueOf(key.hashCode());
        }
        return cacheKey;
    }

    private static String bytesToHexString(byte[] bytes) {
        // http://stackoverflow.com/questions/332079
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < bytes.length; i++) {
            String hex = Integer.toHexString(0xFF & bytes[i]);
            if (hex.length() == 1) {
                sb.append('0');
            }
            sb.append(hex);
        }
        return sb.toString();
    }
}
