package cn.zsc.utils;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class TokenGenerator {

    // 生成token的方法
    public static String generateToken(String username, String password) {
        try {
            // 获取SHA-256的MessageDigest实例
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            // 将用户名和密码拼接成一个字符串
            String originalString = username + password;
            // 对拼接后的字符串进行hash
            byte[] hash = originalString.getBytes(StandardCharsets.UTF_8);
            // 对hash值进行SHA-256加密
            byte[] encodedhash = digest.digest(hash);
            // 将加密后的字节转换为十六进制字符串
            return bytesToHex(encodedhash);
        } catch (NoSuchAlgorithmException e) {
            // 如果出现异常，打印异常信息，并返回null
            e.printStackTrace();
            return null;
        }
    }

    // 将字节转换为十六进制字符串的方法
    private static String bytesToHex(byte[] hash) {
        StringBuilder hexString = new StringBuilder(2 * hash.length);
        for (byte b : hash) {
            String hex = Integer.toHexString(0xff & b);
            if (hex.length() == 1) {
                hexString.append('0');
            }
            hexString.append(hex);
        }
        return hexString.toString();
    }
}
