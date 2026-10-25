import java.net.*;

public class UDPServer {
    public static void main(String[] args) throws Exception {
        DatagramSocket ds = new DatagramSocket(9876);
        System.out.println("UDP Server Started...");
        byte[] buf = new byte[1024];

        DatagramPacket dp = new DatagramPacket(buf, buf.length);
        ds.receive(dp);
        
        String msg = new String(dp.getData(), 0, dp.getLength()).toUpperCase();
        ds.send(new DatagramPacket(msg.getBytes(), msg.length(), dp.getAddress(), dp.getPort()));
        ds.close();
    }
}
javac UDPServer.java
java UDPServer
