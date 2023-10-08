package workutil;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.spec.SecretKeySpec;
import java.security.SecureRandom;

/**
 * @author kin
 * @version $: v 0.1 2016/6/6 Exp $$
 */
public class AESUtil {

    /**
     * 密钥算法
     */
    private static final String KEY_ALGORITHM = "AES";
    private static final int KEY_SIZE = 128;

    /**
     * AES加密
     *
     * @param content    待加密的内容
     * @param encryptKey 加密密钥
     * @return 加密后的byte[]
     * @throws Exception
     */
    public static byte[] encryptToBytes(String content, String encryptKey) throws Exception {
        final KeyGenerator keyGenerator = KeyGenerator.getInstance(KEY_ALGORITHM);

        keyGenerator.init(KEY_SIZE, new SecureRandom(encryptKey.getBytes()));

        Cipher cipher = Cipher.getInstance(KEY_ALGORITHM);
        cipher.init(Cipher.ENCRYPT_MODE, new SecretKeySpec(keyGenerator.generateKey().getEncoded(), KEY_ALGORITHM));

        return cipher.doFinal(content.getBytes("utf-8"));
    }

    /**
     * AES加密为base 64 code
     *
     * @param content    待加密的内容
     * @param encryptKey 加密密钥
     * @return 加密后的base 64 code
     * @throws Exception
     */
    public static String encrypt(String content, String encryptKey) throws Exception {


        byte[] bytes = encryptToBytes(content, encryptKey);
        return new BASE64Encoder().encode(bytes);
    }


    /**
     * AES解密
     *
     * @param encryptBytes 待解密的byte[]
     * @param decryptKey   解密密钥
     * @return 解密后的String
     * @throws Exception
     */
    public static String decryptByBytes(byte[] encryptBytes, String decryptKey) throws Exception {


        final KeyGenerator keyGenerator = KeyGenerator.getInstance(KEY_ALGORITHM);
        keyGenerator.init(KEY_SIZE, new SecureRandom(decryptKey.getBytes()));

        Cipher cipher = Cipher.getInstance(KEY_ALGORITHM);
        cipher.init(Cipher.DECRYPT_MODE, new SecretKeySpec(keyGenerator.generateKey().getEncoded(), KEY_ALGORITHM));
        byte[] decryptBytes = cipher.doFinal(encryptBytes);

        return new String(decryptBytes);
    }

    /**
     * 将base 64 code AES解密
     *
     * @param encryptStr 待解密的base 64 code
     * @param decryptKey 解密密钥
     * @return 解密后的string
     * @throws Exception
     */
    public static String decrypt(String encryptStr, String decryptKey) throws Exception {

        byte[] bytes = new BASE64Decoder().decodeBuffer(encryptStr);
        return decryptByBytes(bytes, decryptKey);
    }
}
