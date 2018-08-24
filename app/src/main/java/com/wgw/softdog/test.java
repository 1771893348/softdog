package com.wgw.softdog;

/**
 * @author wgw
 * @time 2018/8/21 14:53
 * @class describe
 */
public class test {
    public static void main(String arg[]) {
//        StringBuilder encryValue = new StringBuilder();
//        encryValue.append("123456789");
//        System.out.println("*********************");
//        String sd = toHash(encryValue.toString())+"";//encryValue.reverse().toString();
//        System.out.println(sd);

        String key = "wgw@123.com";
        String value = "王国伟";
        String aesMsg = Encrypt.encryptMsg(key,value);
        System.out.println("加密后："+aesMsg);
        String desMsg = Encrypt.decryptMsg(key,aesMsg);
        System.out.println("解密后："+desMsg);

    }



    // 将字符串转成hash值
    public static int toHash(String key) {
        int arraySize = 11113; // 数组大小一般取质数
        int hashCode = 0;
        for (int i = 0; i < key.length(); i++) { // 从字符串的左边开始计算
            int letterValue = key.charAt(i) - 96;// 将获取到的字符串转换成数字，比如a的码值是97，则97-96=1
            // 就代表a的值，同理b=2；
            hashCode = ((hashCode << 5) + letterValue) % arraySize;// 防止编码溢出，对每步结果都进行取模运算
        }
        return hashCode;
    }
}
