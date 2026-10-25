import java.net.*;

public class UDPClient {
    public static void main(String[] args) throws Exception {
        DatagramSocket ds = new DatagramSocket();
        InetAddress ip = InetAddress.getByName("127.0.0.1");
        
        byte[] buf = "hello udp".getBytes();
        ds.send(new DatagramPacket(buf, buf.length, ip, 9876));

        byte[] rBuf = new byte[1024];
        DatagramPacket rDp = new DatagramPacket(rBuf, rBuf.length);
        ds.receive(rDp);
        
        System.out.println("Response: " + new String(rDp.getData(), 0, rDp.getLength()));
        ds.close();
    }
}
javac UDPClient.java
java UDPClient
