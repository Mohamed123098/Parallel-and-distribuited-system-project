/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package parallel2;

import com.mysql.cj.x.protobuf.MysqlxSql;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.sql.*;

/**
 *
 * @author ED
 */
public class ClientHandler implements Runnable{
    private Socket clientSocket;
    private  BufferedReader in;
    private PrintWriter out;
    private ArrayList<ClientHandler> clients;
    private Connection con=null;

    public ClientHandler(Socket clientSocket) throws IOException {
        this.clientSocket = clientSocket;
        this.in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
        this.out= new PrintWriter(clientSocket.getOutputStream(),true);
        
        try {
            //database name 
            con =DriverManager.getConnection("jdbc:mysql://localhost:3306/online_bookstore","root","Peter.06423852");
        } catch (SQLException ex) {
            Logger.getLogger(ClientHandler.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    
    
    @Override
    public void run() {
         String cmsg="";
        while(true){
            
            try {
                cmsg=in.readLine();
            } catch (IOException ex) {
                Logger.getLogger(ClientHandler.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            if(cmsg.startsWith("signup")){                
                insertData(cmsg);               
            }
            else if(cmsg.equals("getProducts")){
                getproductsDB();
            }
            
        }
    }
 
    private void insertData(String cmsg){
        String []cmsgArray=cmsg.split(" ");
        PreparedStatement stmt=null;
        try {
            // table name
            stmt=con.prepareStatement("INSERT INTO selertable VALUES(?,?)");
            stmt.setString(1, cmsgArray[1]);
            stmt.setString(2, cmsgArray[2]);
            stmt.execute();
            out.println("valid");
        } catch (SQLException ex) {
            out.println("notvalid");
        }
        finally{
            try {
                if(stmt !=null)
                    stmt.close();
            } catch (SQLException ex) {
                Logger.getLogger(ClientHandler.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
        
    }
    private void getproductsDB(){
        String products="";
        Statement stmt=null;
        try {
            stmt = con.createStatement();
            ResultSet rs =stmt.executeQuery("select * from productlist");
            while(rs.next()){
                products+= rs.getString("ID")+"^";
                products+=rs.getString("Name")+"^";
                products+=rs.getString("Quantity")+"^";
                products+=rs.getString("Price")+"^";
            }
            out.println("products");
        } catch (SQLException ex) {
            Logger.getLogger(ClientHandler.class.getName()).log(Level.SEVERE, null, ex);
        }
        finally{
            try {
                if(stmt !=null)
                    stmt.close();
            } catch (SQLException ex) {
                Logger.getLogger(ClientHandler.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }  
}
    
    

//        try { 
//            try {
//           //  class.forName("com.mysql.jdbc.Driver");
//           in.readLine();
//           out.println("received");
//            Connection con =DriverManager.getConnection("jdbc:mysql://localhost:3306/online_bookstore","root","Peter.06423852");
//            Statement stmt = con.createStatement();
//            ResultSet rs = stmt.executeQuery("select Fname,Lname  from user");
//            while (rs.next()) {
//                String fname = rs.getString("Fname");
//                String lname = rs.getString("Lname");
//                
//                //System.out.println("Fname " + fname +"  Lname "+ lname);
//                out.println(fname+" "+lname);              
//
//            }
//            stmt.close();
//            System.out.println("success");
//        } catch (Exception ex) { }// catch (SQLException ex) {  }
//            
//            
//            if(false){
//                out.println("hello sir");
//                in.readLine();
//            }
//              
//        } catch (IOException ex) {}
//        finally{
//            try {
//            in.close();
//            out.close ();
//            clientSocket.close();
//            } catch (IOException ex) {}
//        }

    
    
//    public void send(String x) throws IOException{
//    out.println(x);
//    out.flush();
//    }
//    public void sendToAll(String x) throws IOException{
//        for (ClientHandler aclient : clients) {
//            aclient.send(x);
//        }
//    
//    }
