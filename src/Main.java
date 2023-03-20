import java.io.IOException;
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
            
            
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}