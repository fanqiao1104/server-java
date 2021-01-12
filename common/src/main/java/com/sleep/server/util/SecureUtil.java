package com.sleep.server.util;

import cn.hutool.core.codec.Base64;
import cn.hutool.core.util.RandomUtil;
import cn.hutool.crypto.digest.HMac;
import cn.hutool.crypto.digest.HmacAlgorithm;
import cn.hutool.crypto.symmetric.SymmetricAlgorithm;
import cn.hutool.crypto.symmetric.SymmetricCrypto;
import org.apache.commons.lang3.tuple.Pair;

/**
 * @author sunmengyuan
 * @date 2020-07-15
 */
public class SecureUtil {
    /**
     * MD5加密
     *
     * @param str
     * @return
     */
    public static String md5(String str) {
        return cn.hutool.crypto.SecureUtil.md5(str);
    }
    /**
     * Double MD5加密
     * */
    public static String doubleMD5(String str){
        return  cn.hutool.crypto.SecureUtil.md5(cn.hutool.crypto.SecureUtil.md5(str));
    }

    /**
     * 生成Access Key
     *
     * @return
     */
    public static Pair<String, String> generateAccessKey() {
        return Pair.of(RandomUtil.randomString(24), RandomUtil.randomString(32));
    }

    /**
     * AES对称加密（AccessKeyToken）
     * AES_KEYSIZES = { 16, 24, 32 };
     * 返回Base64编码格式
     *
     * @param content
     * @return
     */
    public static String encrypt(String seed, String content) {
        byte[] key = cn.hutool.crypto.SecureUtil.generateKey(SymmetricAlgorithm.AES.getValue(), seed.getBytes()).getEncoded();
        SymmetricCrypto aes = new SymmetricCrypto(SymmetricAlgorithm.AES, key);
        return aes.encryptBase64(content);
    }

    /**
     * 解密（AccessKeyToken）
     *
     * @param encryptBase64
     * @return
     */
    public static String decrypt(String seed, String encryptBase64) {
        byte[] key = cn.hutool.crypto.SecureUtil.generateKey(SymmetricAlgorithm.AES.getValue(), seed.getBytes()).getEncoded();
        SymmetricCrypto aes = new SymmetricCrypto(SymmetricAlgorithm.AES, key);
        return aes.decryptStr(encryptBase64);
    }

    /**
     * 使用HmacSHA1对字符串进行摘要加密
     * 并且将结果使用参数URL_SAFE来进行Base64编码
     * Key为AccessKeySecret
     *
     * @param seed
     * @param content
     * @return
     */
    public static String digest(String seed, String content) {
        HMac mac = new HMac(HmacAlgorithm.HmacSHA1, seed.getBytes());
        return Base64.encode(mac.digest(content));
    }
}
