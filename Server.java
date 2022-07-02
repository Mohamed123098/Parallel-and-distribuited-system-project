
import java.io.IOException;
import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.sql.SQLException;

public class server1c {

    static public void main(String[] args) throws SQLException {
        ServerSocket server = null;
        try {
            System.out.println(".......server......");
            System.out.println("Waiting for connection....");
            // creating the socket with port 909
            server = new ServerSocket(1234);
            while (true) {
                Socket c = server.accept();
                System.out.println("client accepted ... ");
                ClientHandler ch = new ClientHandler(c);
                ch.start();
                // use the multi threading techniques
              //  Thread th = new Thread(ch);
                System.out.println("12");
               // th.start();
            }

        } catch (IOException ex) {
            System.out.println(ex.getMessage());

        } finally {
            try {
                server.close();
            } catch (IOException e) {
            }
        }
    }
}
