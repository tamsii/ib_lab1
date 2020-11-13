package lab1.data;

public class Package {
    public final Header header;
    public final String pn;
    public final String data;

    public Package(Header header, String pn, String data) {
        this.header = header;
        this.pn = pn;
        this.data = data;
    }

    @Override
    public String toString() {
        return "Package: "+ "||"+ header.destination + '|' + header.source + "||"+ pn + "||" + data + "||";
    }

    public Header getHeader() {
        return header;
    }

    public String getPn() {
        return pn;
    }

    public String getData() {
        return data;
    }

}


