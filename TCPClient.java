import java.io.*;
import java.net.*;

public class TCPClient {
    public static void main(String[] args) {
        String serverAddress = "localhost";
        int port = 5000;

        try (Socket socket = new Socket(serverAddress, port)) {
            System.out.println("[TCP Client] Connected to server.");

            PrintWriter writer = new PrintWriter(socket.getOutputStream(), true);
            BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));

            String message = "hello ice pust network programming";
            System.out.println("[TCP Client] Sending message: " + message);
            writer.println(message);

            String response = reader.readLine();
            System.out.println("[TCP Client] Response from server: " + response);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}