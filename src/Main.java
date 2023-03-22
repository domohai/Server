import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        // single thread
        // drawback: only 1 client can access server at a time
        /**
         * 0-1023: System ports
         * 1024-49151: User ports
         * 49152-65535: Dynamic and/or Private Ports
         * Why choose port 6969?
         * https://stackoverflow.com/questions/218839/assigning-tcp-ip-ports-for-in-house-application-use
         */
        /*
        try (ServerSocket serverSocket = new ServerSocket(6969)) {
            // wait until a client connect to the server
            // then accept the connection and return a socket object
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
                inString = NormalizeString(inString);
                // return result to client
                output.println("Result from server: " + inString);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        */
        Arrays.sort(NormalizeString.separators);
        // multi-thread
        try (ServerSocket serverSocket = new ServerSocket(6969)) {
            while (true) new SubThread(serverSocket.accept()).start();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}