
package lab1.aes;


import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;

public class AESImpl{

    private SecretKeySpec secretKey ;
    private byte[] key;
    private byte[] iv = new byte[0];

    public void setSecretKey(String setKey) throws UnsupportedEncodingException, NoSuchAlgorithmException {
        MessageDigest sha = null;
        key = setKey.getBytes("UTF-8");
        sha = MessageDigest.getInstance("SHA-1");
        key = sha.digest(key);
        key = Arrays.copyOf(key,16);
        secretKey = new SecretKeySpec(key, "AES");
    }

    public SecretKeySpec setSecretKeyMAC(String setKey) throws UnsupportedEncodingException, NoSuchAlgorithmException, InvalidKeyException {
        Mac mac = null;
        key = setKey.getBytes("UTF-8");
        mac = Mac.getInstance("HmacSHA256");
        key = Arrays.copyOf(key,16);
        SecretKeySpec secretKeySpec= new SecretKeySpec(key,"AES");
        mac.init(secretKeySpec);
        return secretKeySpec;
    }

//    public static String encrypt(String input, String key) {
//        byte[] crypted = null;
//        try {
//
//            SecretKeySpec skey = new SecretKeySpec(key.getBytes(), "AES");
//
//            Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
//            cipher.init(Cipher.ENCRYPT_MODE, skey);
//            crypted = cipher.doFinal(input.getBytes());
//        } catch (Exception e) {
//            System.out.println(e.toString());
//        }
//        java.util.Base64.Encoder encoder = java.util.Base64.getEncoder();
//
//        return new String(encoder.encodeToString(crypted));
//    }
}