/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cn.movinginfo.tztf.common.fax;

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

    private static final String PASSWORD_CRYPT_KEY = "12348765";//��Կ
    private final static String DES = "DES";
    private static final byte[] EncryptionIV = {0x12, 0x34, 0x56, 0x78, (byte) 0x90, (byte) 0xAB, (byte) 0xCD, (byte) 0xEF};//��ʼ����
    //private static final byte[] EncryptionIV ="1234567890ABCDEF".getBytes();//��ʼ����

    /** 
     * ���� 
     * @param src ����Դ 
     * @param key ��Կ�����ȱ�����8�ı���
     * @return  ���ؼ��ܺ������ 
     * @throws Exception 
     */
    public static byte[] encrypt(byte[] src, byte[] key) throws Exception {
        //DES�㷨Ҫ����һ�������ε������Դ 
        //SecureRandom sr = new SecureRandom(EncryptionIV);
        IvParameterSpec spec = new IvParameterSpec(EncryptionIV);
        // ��ԭʼ�ܳ����ݴ���DESKeySpec���� 
        DESKeySpec dks = new DESKeySpec(key);
        // ����һ���ܳ׹�����Ȼ��������DESKeySpecת���� 
        // һ��SecretKey���� 
        SecretKeyFactory keyFactory = SecretKeyFactory.getInstance(DES);
        SecretKey securekey = keyFactory.generateSecret(dks);
        // Cipher����ʵ����ɼ��ܲ��� 
        Cipher cipher = Cipher.getInstance("DES/CBC/PKCS5Padding");
        // ���ܳ׳�ʼ��Cipher���� 
        cipher.init(Cipher.ENCRYPT_MODE, securekey, spec);
        // ���ڣ���ȡ���ݲ����� 
        // ��ʽִ�м��ܲ��� 
        return cipher.doFinal(src);
    }

    /** 
     * ���� 
     * @param src ����Դ 
     * @param key ��Կ�����ȱ�����8�ı��� 
     * @return   ���ؽ��ܺ��ԭʼ���� 
     * @throws Exception 
     */
    public static byte[] decrypt(byte[] src, byte[] key) throws Exception {
        // DES�㷨Ҫ����һ�������ε������Դ 
        //SecureRandom sr = new SecureRandom();
        //SecureRandom sr = new SecureRandom(EncryptionIV);
        IvParameterSpec spec = new IvParameterSpec(EncryptionIV);
        // ��ԭʼ�ܳ����ݴ���һ��DESKeySpec���� 
        DESKeySpec dks = new DESKeySpec(key);
        // ����һ���ܳ׹�����Ȼ��������DESKeySpec����ת���� 
        // һ��SecretKey���� 
        SecretKeyFactory keyFactory = SecretKeyFactory.getInstance(DES);
        SecretKey securekey = keyFactory.generateSecret(dks);
        // Cipher����ʵ����ɽ��ܲ��� 
        Cipher cipher = Cipher.getInstance("DES/CBC/PKCS5Padding");
        // ���ܳ׳�ʼ��Cipher���� 
        cipher.init(Cipher.DECRYPT_MODE, securekey, spec);
        // ���ڣ���ȡ���ݲ����� 
        // ��ʽִ�н��ܲ��� 
        return cipher.doFinal(src);
    }

    /** 
     * ������� 
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
     * ������� 
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
