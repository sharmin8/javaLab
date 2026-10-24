import java.io.*;
import java.net.*;

public class TCPServer {
    public static void main(String[] args) {
        int port = 5000;
        try (ServerSocket serverSocket = new ServerSocket(port)) {
            System.out.println("[TCP Server] Listening for connection on port " + port + "...");
            
            Socket socket = serverSocket.accept();
            System.out.println("[TCP Server] Client connected successfully!");

            BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            PrintWriter writer = new PrintWriter(socket.getOutputStream(), true);

            String clientMessage = reader.readLine();
            System.out.println("[TCP Server] Received message: " + clientMessage);

            // Convert to uppercase
            String upperMessage = clientMessage.toUpperCase();
            writer.println(upperMessage);
            System.out.println("[TCP Server] Sent uppercase message back to client.");

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}