/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cn.movinginfo.tztf.webserviceClient.faxold;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;
import javax.crypto.spec.IvParameterSpec;


/**
 *
 * @author fufu
 */
public class MDES {

    private static final String PASSWORD_CRYPT_KEY = "12348765";//密钥
    private final static String DES = "DES";
    private static final byte[] EncryptionIV = {0x12, 0x34, 0x56, 0x78, (byte) 0x90, (byte) 0xAB, (byte) 0xCD, (byte) 0xEF};//初始向量
    //private static final byte[] EncryptionIV ="1234567890ABCDEF".getBytes();//初始向量

    /** 
     * 加密 
     * @param src 数据源 
     * @param key 密钥，长度必须是8的倍数
     * @return  返回加密后的数据 
     * @throws Exception 
     */
    public static byte[] encrypt(byte[] src, byte[] key) throws Exception {
        //DES算法要求有一个可信任的随机数源 
        //SecureRandom sr = new SecureRandom(EncryptionIV);
        IvParameterSpec spec = new IvParameterSpec(EncryptionIV);
        // 从原始密匙数据创建DESKeySpec对象 
        DESKeySpec dks = new DESKeySpec(key);
        // 创建一个密匙工厂，然后用它把DESKeySpec转换成 
        // 一个SecretKey对象 
        SecretKeyFactory keyFactory = SecretKeyFactory.getInstance(DES);
        SecretKey securekey = keyFactory.generateSecret(dks);
        // Cipher对象实际完成加密操作 
        Cipher cipher = Cipher.getInstance("DES/CBC/PKCS5Padding");
        // 用密匙初始化Cipher对象 
        cipher.init(Cipher.ENCRYPT_MODE, securekey, spec);
        // 现在，获取数据并加密 
        // 正式执行加密操作 
        return cipher.doFinal(src);
    }

    /** 
     * 解密 
     * @param src 数据源 
     * @param key 密钥，长度必须是8的倍数 
     * @return   返回解密后的原始数据 
     * @throws Exception 
     */
    public static byte[] decrypt(byte[] src, byte[] key) throws Exception {
        // DES算法要求有一个可信任的随机数源 
        //SecureRandom sr = new SecureRandom();
        //SecureRandom sr = new SecureRandom(EncryptionIV);
        IvParameterSpec spec = new IvParameterSpec(EncryptionIV);
        // 从原始密匙数据创建一个DESKeySpec对象 
        DESKeySpec dks = new DESKeySpec(key);
        // 创建一个密匙工厂，然后用它把DESKeySpec对象转换成 
        // 一个SecretKey对象 
        SecretKeyFactory keyFactory = SecretKeyFactory.getInstance(DES);
        SecretKey securekey = keyFactory.generateSecret(dks);
        // Cipher对象实际完成解密操作 
        Cipher cipher = Cipher.getInstance("DES/CBC/PKCS5Padding");
        // 用密匙初始化Cipher对象 
        cipher.init(Cipher.DECRYPT_MODE, securekey, spec);
        // 现在，获取数据并解密 
        // 正式执行解密操作 
        return cipher.doFinal(src);
    }

    /** 
     * 密码解密 
     * @param data 
     * @return 
     * @throws Exception 
     */
    public final static String decrypt(String data) {
        try {
            return new String(decrypt(Util.hex2byte(data.getBytes()),
                    PASSWORD_CRYPT_KEY.getBytes()));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /** 
     * 密码加密 
     * @param password
     * @return 
     * @throws Exception
     */
    public final static String encrypt(String password) {
        try {
            return Util.byte2hex(encrypt(password.getBytes(), PASSWORD_CRYPT_KEY.getBytes()));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static void main(String[] args) {
        try {
           System.out.println(encrypt("wh"));
           System.out.println(decrypt("3ACED6FDDA23E1CE"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
