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
                insertusers(cmsg);               
            }
            else if(cmsg.equals("getProducts")){
                getproductsDB();
            }
            
        }
    }
 
    private void insertusers(String cmsg) {
        String[] Array =cmsg.split(" ");
        PreparedStatement quer=null;
        if(Array[1].trim().isEmpty()||Array[2].trim().isEmpty())
      {
                      
          out.println("Missing_Info");
                  
      }
        else {
        try {
            // table name
           
            quer=con.prepareStatement("INSERT INTO client VALUES(?,?,?,?,?)");
            quer.setString(1, Array[1]);
            quer.setString(2, Array[2]);
            quer.setInt(3, Integer.parseInt(Array[4]));
            quer.setString(4, Array[3]);
            quer.setString(5, Array[5]);
            quer.execute();
            out.println("valid");
            
        }
        catch (SQLException ex) {
                out.println("notvalid");
                
        } 
        finally{
            try {
                if(quer !=null)
                    quer.close();
            } catch (SQLException ex) {
                Logger.getLogger(ClientHandler.class.getName()).log(Level.SEVERE, null, ex);
            }
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
    private void authenticate(String req){
        String[] Array=req.split(" ");
        Statement quer=null;
        ResultSet rs;
        //System.out.println(Array[0]+"     "+Array[1]+"      "+Array[2]+"    "+Array[3]);
        String a="";
        String b="";
        
         if (Array[1].equals("Client")) {
              try {
                
                String q="SELECT `c_name`,`c_password` FROM client where c_name= '" + Array[2] + "' and c_password= '" + Array[3] + "';";
                
                quer= con.createStatement();
                
                rs=quer.executeQuery(q);
                if(rs.next())
                    
                { 
                    a=rs.getString(1);
                   b=rs.getString(2);
                   out.println("validClient");

                } else {
                    out.println("notvalid");
                
                }   
            } catch (SQLException ex) {
              
              }
        finally{
            try {
                if(quer !=null)
                {quer.close();
               }
            } catch (SQLException ex) {
                Logger.getLogger(ClientHandler.class.getName()).log(Level.SEVERE, null, ex);
            }
        }}
        
       else{
                 try{
                //System.out.println("adminnnnnnnnnnn");
                String q= "SELECT a_name,a_password FROM admin where a_name='"+Array[2]+"'and a_password='"+Array[3]+"';";
              
                quer= con.createStatement();
                
                rs=quer.executeQuery(q);
                if(rs.next())
                {   a=rs.getString(1);
                 b=rs.getString(2);
                
                out.println("Avalid");
              
                 }
                else{
                  
                 //System.out.println("message");
                out.println("notinA");
             
                    
                 }  
            }
            catch (SQLException ex) {
           
        }
            
       
        finally{
            try {
                if(quer !=null)
                    quer.close();
            } catch (SQLException ex) {
                Logger.getLogger(ClientHandler.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
            
        } 
    }
        
        public void showInfo (String req){
    String[] Array=req.split(" ");
       PreparedStatement quer=null;
       ResultSet rs=null;
       System.out.println(Array[0]+"     "+Array[1]);
       String name="";
       String add="";
       int phone_no=0;
       int cash=0;
            try {
                System.out.println("in");
                quer = con.prepareStatement("SELECT c_name, address, phone, cash FROM client where c_name= '" + Array[1] +  "';");
                 rs=quer.executeQuery();
                while(rs.next()){
                name = rs.getString(1);
                add = rs.getString(2);
                phone_no = rs.getInt(3);
                cash=rs.getInt(4);
                }
                String data = name+" "+add+" "+phone_no+" "+cash;
            
                out.println(data);
                
            } catch (SQLException ex) {
               out.println("notvalid");
            } 
        finally{
            try {
                if(quer !=null)
                {quer.close();
               }
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
