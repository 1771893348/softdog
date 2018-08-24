package com.wgw.softdog.utils;

import java.security.MessageDigest;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

public class AESEncrypter {
	// private static final Logger logger = Logger.getLogger(AESUtil.class);
//	private static final String defaultCharset = "UTF-8";
//	private static final String KEY_AES = "AES";
//	private static final

	public static void main(String[] args) throws Exception {

		 String KEY = "123456789";
		 
		String content = "zhaoshengtang@163.com";
		System.out.println("加密前：" + content);
		System.out.println("加密密钥和解密密钥：" + KEY);
		String encrypt = encrypt(content, KEY);
		System.out.println("加密后：" + encrypt);
		String decrypt = decrypt(encrypt, KEY);
		System.out.println("解密后：" + decrypt);
//	String key1="com.jinrongweb.entity.UserBase.loginname.62F0C44C07F8436061FCB10C29AC9FFD8393E4FBCD44B3CB2106BF9EAEF6E606";
//	String key2="com.jinrongweb.entity.UserBase.mobile.62F0C44C07F8436061FCB10C29AC9FFD8393E4FBCD44B3CB2106BF9EAEF6E606";
//
//	System.out.println("key1:" + MD5Util.md5(key1));
//	System.out.println("key2:" + MD5Util.md5(key2));
	}

	/**
	 * 加密
	 *
	 * @param data
	 *            需要加密的内容
	 * @param key
	 *            加密密码
	 * @return
	 */
	public static String encrypt(String data, String key) throws Exception {
		return doAES(data, key, Cipher.ENCRYPT_MODE);
	}

	/**
	 * 解密
	 *
	 * @param data
	 *            待解密内容
	 * @param key
	 *            解密密钥
	 * @return
	 */
	public static String decrypt(String data, String key) throws Exception {
		return doAES(data, key, Cipher.DECRYPT_MODE);
	}

	/**
	 * 加解密
	 * @param data
	 * 				待处理数据
	 * @param key
	 * 				密钥
	 * @param mode
	 * 				加解密mode
	 * @return
	 * @throws Exception
	 */
	private static String doAES(String data, String key, int mode) throws Exception {
		 try {
	            if (StringUtils.isEmpty(data) || StringUtils.isEmpty(key)) {
	                return null;
	            }
	            boolean encrypt = mode == Cipher.ENCRYPT_MODE;
	            byte[] content;
	            //true 加密内容 false 解密内容
	            if (encrypt) {
	                content = data.getBytes("UTF-8");
	            } else {
	                 content = parseHexStr2Byte(data);
	            }
	            
	            SecretKeySpec keySpec = new SecretKeySpec(MessageDigest.getInstance("MD5").digest(key.getBytes("UTF-8")), "AES");//构造一个密钥
	            Cipher cipher = Cipher.getInstance("AES");// 创建密码器
	            cipher.init(mode, keySpec);// 初始化
	            byte[] result = cipher.doFinal(content);//加密或解密
	            if (encrypt) {
	                return parseByte2HexStr(result);
	            } else {
	                return new String(result, "UTF-8");
	            }
	        } catch (Exception e) {
//	            logger.error("AES 密文处理异常", e);
	        		e.printStackTrace();
	        }
	        return null;

	}

	/**
	 * 将二进制转换成16进制
	 *
	 * @param buf
	 * @return
	 */
	public static String parseByte2HexStr(byte buf[]) {
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < buf.length; i++) {
			String hex = Integer.toHexString(buf[i] & 0xFF);
			if (hex.length() == 1) {
				hex = '0' + hex;
			}
			sb.append(hex.toUpperCase());
		}
		return sb.toString();
	}

	/**
	 * 将16进制转换为二进制
	 *
	 * @param hexStr
	 * @return
	 */
	public static byte[] parseHexStr2Byte(String hexStr) {
		if (hexStr.length() < 1) {
			return null;
		}
		byte[] result = new byte[hexStr.length() / 2];
		for (int i = 0; i < hexStr.length() / 2; i++) {
			int high = Integer.parseInt(hexStr.substring(i * 2, i * 2 + 1), 16);
			int low = Integer.parseInt(hexStr.substring(i * 2 + 1, i * 2 + 2), 16);
			result[i] = (byte) (high * 16 + low);
		}
		return result;
	}

}
