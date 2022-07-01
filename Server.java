

package parallel2;

import java.io.*;
//import java.lang.System.Logger.Level;
import java.sql.*;
import java.net.*;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.table.DefaultTableModel;
 
public class Server extends Thread {
 
    Statement stmt=null;
    Vector records = new Vector(10,10);
    ResultSet rs = null;
    ServerSocket server = null;
    Socket client = null;
    Connection con = null;
    ObjectOutputStream out =null;
    String str = null;
    Publisher pub = null;
    
    public Server()
    {
        try {
            server = new ServerSocket(1400);
            System.out.println("Starting the Server");
            start();
        } catch (IOException ex) {
            Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }        
    public void run()
    {
        while(true)
        {
            try {
                int CC;
                client = server.accept();
                System.out.println("Connection accepted");  
                out = new ObjectOutputStream(client.getOutputStream());
                System.out.println("OutputStream received");
                try {
                    Class.forName("com.mysql.cj.jdbc.Driver");
                    con = DriverManager.getConnection("jdbc:derby://localhost:1527/Store","user1","1234");
                    stmt = con.createStatement();
                    rs = stmt.executeQuery("select * from SELERTABLE");
                    records.removeAllElements();
 
            ResultSetMetaData RSMD = rs.getMetaData();
            CC = RSMD.getColumnCount();
 
                    while(rs.next())
                    {
                        pub = new Publisher();
                        pub.SellerName = rs.getString(1);
                        pub.SellerPass = rs.getString(2);
                        pub.SellerGen = rs.getString(3);
                        pub.SellerPhone = rs.getString(4);
                        pub.SellerAdress = rs.getString(5);
                     //   pub.cash = rs.getString(6);
                        records.addElement(pub);
                        System.out.println("row returned");
                    }
 
                  out.writeObject(records);
                    out.close();
                    System.out.println("String returned");
                    
                    
                } catch (ClassNotFoundException ex) {
                    Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
                } catch (SQLException ex) {
                    Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
                }
 
            } catch (IOException ex) {
                Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
            }
            
        }
    }
    public static void main(String args[])
{
   new Server();
}
}
