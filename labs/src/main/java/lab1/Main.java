package lab1;

import java.io.UnsupportedEncodingException;
import java.security.*;

import lab1.ClearTextFrame;
import lab1.aes.AES;
import lab1.data.EncryptedPackege;
import lab1.data.Header;
import lab1.data.Package;
import org.bouncycastle.jce.provider.BouncyCastleProvider;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;


public class Main {

    public static void main(String[] args) throws NoSuchPaddingException, InvalidAlgorithmParameterException, UnsupportedEncodingException, IllegalBlockSizeException, BadPaddingException, NoSuchAlgorithmException, InvalidKeyException, NoSuchProviderException {
        Security.addProvider(new BouncyCastleProvider());
        String source = "ab.cd.ef.gh";
        String destination = "ij.kl.mn.op";
        Header header = new Header(source, destination);
        String pn = "pn1";
        String data = "TEst data za encoding";
        Package p = new Package(header,pn,data);
        byte [] iv = {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0};
        EncryptedPackege ep = ClearTextFrame.encrypt(p,"tams", iv);
        System.out.println(ep.toString());
        System.out.println(EncryptedFrame.decrypt(ep,"tams",iv).toString());

    }

}
