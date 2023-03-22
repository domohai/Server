import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class SubThread extends Thread {
    private Socket socket;
    private int clientID;
    private static int count = -1;
    
    public SubThread(Socket socket) {
        this.socket = socket;
        this.clientID = ++count;
    }
    
    @Override
    public void run() {
        System.out.println("Client " + count + " connected!");
        try (BufferedReader input = new BufferedReader(new InputStreamReader(socket.getInputStream()));
             PrintWriter output = new PrintWriter(socket.getOutputStream(), true)) {
            while (true) {
                String inputString = input.readLine();
                if (inputString.equals("quit")) break;
                // normalize here
                System.out.println("String received from client " + clientID + ": " + inputString);
                inputString = NormalizeString.normalize(inputString);
                System.out.println("Normalized String for client " + clientID + ": " + inputString);
                output.println(inputString);
            }
            System.out.println("Client " + clientID + " disconnected!");
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                socket.close();
            } catch (IOException e) {
                System.out.println("Failed to close socket!");
                e.printStackTrace();
            }
        }
    }
}
