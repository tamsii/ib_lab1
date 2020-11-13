package lab1;

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
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.util.Base64;

public class EncryptedFrame {
    public static Package decrypt(EncryptedPackege encryptedPackege, String key, byte [] iv) throws UnsupportedEncodingException, NoSuchAlgorithmException, InvalidKeyException, NoSuchPaddingException, InvalidAlgorithmParameterException, BadPaddingException, IllegalBlockSizeException, NoSuchProviderException {
        AESImpl aes = new AESImpl();
        IvParameterSpec ivspec = new IvParameterSpec(iv);
        SecretKeySpec secretKeySpec = aes.setSecretKeyMAC(key);
        Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding", new BouncyCastleProvider());
        cipher.init(Cipher.DECRYPT_MODE, secretKeySpec);

        String data = encryptedPackege.getData();
        Header header = encryptedPackege.getHeader();
        String pn = encryptedPackege.getPn();
        String clear = new String(cipher.doFinal(Base64.getDecoder().decode(data.getBytes("UTF-8"))));
        Package packege = new Package(header, pn, clear);
        return packege;
    }
}
