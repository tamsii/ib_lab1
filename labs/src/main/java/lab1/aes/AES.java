package lab1.aes;

import javax.crypto.spec.SecretKeySpec;
import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

public interface AES {

    SecretKeySpec secretKey = null;
    static byte[] key = new byte[0];
    static byte[] iv = new byte[0];

    void setSecretKey(String setKey) throws UnsupportedEncodingException, NoSuchAlgorithmException;

    SecretKeySpec setSecretKeyMAC(String setKey) throws UnsupportedEncodingException, NoSuchAlgorithmException, InvalidKeyException;

}
