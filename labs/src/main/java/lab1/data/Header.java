package lab1.data;

public class Header{

    public String source;
    public String destination;

    public Header(String source, String destination) {
        this.source = source;
        this.destination = destination;
    }

    public String getSource() {
        return source;
    }

    public String getDestination() {
        return destination;
    }
}