import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class Main {
    public static void main(String[] args) {
        /**
         * 0-1023: System ports
         * 1024-49151: User ports
         * 49152-65535: Dynamic and/or Private Ports
         * Why choose port 6969?
         * https://stackoverflow.com/questions/218839/assigning-tcp-ip-ports-for-in-house-application-use
         */
        try (ServerSocket serverSocket = new ServerSocket(6969)) {
            // Đợi cho tới khi một kết nối được thực hiện tới,
            // sau đó accept nó, tạo ra và trả về một socket object mới
            Socket socket = serverSocket.accept();
            System.out.println("Client accepted");
            // a bufferedReader to read input from client
            BufferedReader input = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            // a writer to return result to client
            PrintWriter output = new PrintWriter(socket.getOutputStream(), true);
            
            while (true) {
                String inString = input.readLine();
                // check if the client want to disconnect
                if (inString.equals("quit")) break;
                System.out.println("Input String: " + inString);
                // normalize
                inString = inString.toLowerCase().trim();
                inString = inString.substring(0, 1).toUpperCase() + inString.substring(1);
                // return result to client
                output.println("Result from server: " + inString);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}