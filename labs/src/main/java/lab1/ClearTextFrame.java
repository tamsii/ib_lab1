package lab1;


import lab1.aes.AES;
import lab1.aes.AESImpl;
import lab1.data.EncryptedPackege;
import lab1.data.Header;
import lab1.data.Package;
import org.bouncycastle.jce.provider.BouncyCastleProvider;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.io.UnsupportedEncodingException;
import java.security.*;
import java.util.Arrays;
import java.util.Base64;

public class ClearTextFrame extends AESImpl {

    public static String calculateMic(String sb, SecretKeySpec key, byte [] iv) throws UnsupportedEncodingException, NoSuchPaddingException, NoSuchAlgorithmException, InvalidAlgorithmParameterException, InvalidKeyException, BadPaddingException, IllegalBlockSizeException {
        Cipher cipherMIC = Cipher.getInstance("AES/ECB/PKCS5Padding");
        cipherMIC.init(Cipher.ENCRYPT_MODE, key);
        String  mic = Base64.getEncoder().encodeToString(cipherMIC.doFinal(sb.getBytes("UTF-8")));
        return mic.substring(0, 32);
    }


    public static EncryptedPackege encrypt(Package packege, String key, byte [] iv) throws UnsupportedEncodingException, NoSuchAlgorithmException, InvalidKeyException, NoSuchPaddingException, InvalidAlgorithmParameterException, BadPaddingException, IllegalBlockSizeException, NoSuchProviderException {
        AESImpl aes = new AESImpl();
        IvParameterSpec ivspec = new IvParameterSpec(iv);
        SecretKeySpec secretKeySpec = aes.setSecretKeyMAC(key);
        Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding", new BouncyCastleProvider());
        cipher.init(Cipher.ENCRYPT_MODE, secretKeySpec);

        String data = packege.getData();
        Header header = packege.getHeader();
        String source = header.getSource();
        String destination = header.getDestination();
        String pn = packege.getPn();
        StringBuilder sb = new StringBuilder();
        sb.append(source);
        sb.append(destination);
        sb.append(data);
        String l = sb.toString();
        String encrypted = Base64.getEncoder().encodeToString(cipher.doFinal(data.getBytes("UTF-8")));
        String mic = calculateMic(l,secretKeySpec, iv);
        EncryptedPackege encryptedPackege = new EncryptedPackege(header, pn, encrypted, mic);
        return encryptedPackege;
    }

}


