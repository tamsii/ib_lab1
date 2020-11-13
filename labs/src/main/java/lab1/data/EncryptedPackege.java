package lab1.data;

public class EncryptedPackege  extends Package {

    public static String mic;

    public EncryptedPackege(Header header, String pn, String data, String mic) {
        super(header, pn, data);
        EncryptedPackege.mic = mic;
    }

    @Override
    public String toString() {
        return "Package: "+ "||"+ header.destination + '|' + header.source + "||"+ pn + "||" + data + "||"+ mic + "||";
    }
}
