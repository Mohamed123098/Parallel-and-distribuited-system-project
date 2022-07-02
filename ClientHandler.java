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
public class ClientHandler extends Thread {
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
            
            if(cmsg.contains("signup")){                
                insertusers(cmsg);               
            }
            else if(cmsg.contains("login")){
                authenticate(cmsg);
            }
            else if (cmsg.contains("Account")){
            showInfo(cmsg);
            }
            else if(cmsg.contains("getCLientList")){
                ShowClientList();
            }
            else if (cmsg.contains("show_Balance")){
            //System.out.println("Show_balance");
            ShowBalance(cmsg);
            }
            else if (cmsg.contains("change")){
             //System.out.println("chaange");
            changeB(cmsg);
            }
            
            else if (cmsg.contains("getProductsList")){
                ShowProductsList(cmsg);
            }
            else if (cmsg.contains("deposit")){
            deposit(cmsg);
            }
            else if(cmsg.equals("getProducts")){
                getproductsDB();
            }
            else if (cmsg.startsWith("addToCart")){
                addToCartDB(cmsg);
            }
            else if (cmsg.startsWith("removeFromCart")){
                removeFromCartDB(cmsg);    
            }
        }
    }
 //////////////// Sign Up function /////////////////
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
    //////////////// Authentication function /////////////////
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
     //////////////// Show Account Info function /////////////////
        private void showInfo (String req){
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
    //////////////// deposit function /////////////////
        public synchronized void deposit(String req){
     String[] Array=req.split(" ");
       PreparedStatement quer=null;
       //ResultSet rs=null;
       
       System.out.println(Array[0]+"     "+Array[1]+"    "+Array[2]+"   "+Array[3]);
   int balance = Integer.parseInt(Array[2]);
   int depositedM = Integer.parseInt(Array[3]);
   int cash = balance + depositedM;
            try {
                System.out.println("in");
                quer=con.prepareStatement( "UPDATE client SET cash='"+cash+"' where c_name= '" + Array[1] +  "';");
                
                quer.executeUpdate();
            } catch (SQLException ex) {
                
                    System.out.println("failed");
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
    //////////////// Get Client list function /////////////////
       private void ShowClientList(){
        String list="";
        Statement stmt=null;
       // Statement s=null;
        
        try {
            stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery("select c.c_name, c.address, c.phone, ca.pname,ca.no_of_products,ca.total_price, p.cat_name from client AS c,cart AS ca,product AS p where c.c_name=ca.c_name and p.pname = ca.pname ;");
            while(rs.next()){
                list+=rs.getString(1)+"~@";
                list+=rs.getString(2)+"~@";
                list=list.concat(String.valueOf(rs.getInt(3)))+"~@";
                list+=rs.getString(4)+"~@";
                list=list.concat(String.valueOf(rs.getInt(5)))+"~@";
                list=list.concat(String.valueOf(rs.getFloat(6)))+"~@";
                list+=rs.getString(7)+"~@";
            }
            //s=con.createStatement();
            //ResultSet rss =s.executeQuery("select price from product where pname=' "+Array[1]+"';");
            System.out.println(list);
             try {
                 //out.println(products);
                 dos.writeUTF(list);
                 dos.flush();
             } catch (IOException ex) {
                 Logger.getLogger(ClientHandler.class.getName()).log(Level.SEVERE, null, ex);
             }
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
    
    
    //////////////// Get Products function /////////////////
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
    //////////////// Add to cart function /////////////////
    private void addToCartDB(String cmsg){
        String []cmsgArray=cmsg.split("~@");
        PreparedStatement stmt=null;
        PreparedStatement s=null;
        try {
            // table name
            stmt=con.prepareStatement("INSERT INTO cart(c_name,total_price,no_of_products,pname) VALUES(?,?,?,?)");
            stmt.setString(1, cmsgArray[1]);
            stmt.setFloat(2,Float.parseFloat(cmsgArray[2]));
            stmt.setInt(3, Integer.parseInt( cmsgArray[3]));
            stmt.setString(4, cmsgArray[4]);
            stmt.execute();
            s = con.prepareStatement("update product set quantity=quantity-? where pname=?");
            s.setInt(1,Integer.parseInt( cmsgArray[3]));
            s.setString(2,cmsgArray[4]);
            s.executeUpdate();
        } catch (SQLException ex) {  
                Logger.getLogger(ClientHandler.class.getName()).log(Level.SEVERE, null, ex);
            
        }
        finally{
            try {
                if(stmt !=null)
                    stmt.close();
                if(s!=null)
                    s.close();
            } catch (SQLException ex) {
                Logger.getLogger(ClientHandler.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
        //////////////// Add to cart function /////////////////
    private void removeFromCartDB(String cmsg){
        
        String []cmsgArray=cmsg.split("~@");
        PreparedStatement stmt=null;
        Statement ss=null;
        PreparedStatement upd=null;
         int id=-1;
        try {
            // table name
            String Q = "select MAX(cart_id) from cart where c_name='"+cmsgArray[1]+"' and pname='"+cmsgArray[2]+"' and no_of_products="+Integer.parseInt(cmsgArray[3]);
            
            ss = con.createStatement();                                                   
            ResultSet rss =ss.executeQuery(Q);
            

            while(rss.next()){
            id = rss.getInt("MAX(cart_id)");
            }

            stmt=con.prepareStatement("delete from cart where cart_id=?");           
            stmt.setInt(1, id);
            stmt.executeUpdate();  
            
             //update product table 
            upd=con.prepareStatement("update product set quantity=quantity+? where pname=?");
            
            upd.setInt(1,Integer.parseInt(cmsgArray[3]));
            upd.setString(2, cmsgArray[2]);
            upd.executeUpdate();
            
            
            
        } catch (SQLException ex) { 
            
        }
        finally{
            try {
                if(stmt !=null)
                    stmt.close();
                if(ss !=null)
                    ss.close();
                if(upd !=null)
                    upd.close();
            } catch (SQLException ex) {
                Logger.getLogger(ClientHandler.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
        
    }
    //////////////// Get Purshased items function /////////////////
  
    
    
    ////////// ShowBalance function/////////////////////
     public synchronized void ShowBalance (String req){
    String[] Array=req.split(" ");
       Statement quer=null;
       ResultSet rs=null;
       System.out.println(Array[0]+"     "+Array[1]);
       String name="";     
       int balance=0;
            try {
                System.out.println("in");
                quer = con.createStatement();
                 rs=quer.executeQuery("SELECT cash FROM client where c_name= '" + Array[1] +  "'");
                while(rs.next()){
               balance = Integer.parseInt(rs.getString(1));
                    System.out.println(balance);

                }
                String data = String.valueOf(balance);
               // dos.writeUTF(data);
                //dos.flush();
               out.println(data);
               System.out.println(" balance sent");
                
            } catch (SQLException ex) {
                    out.println("notvalid");
                    Logger.getLogger(ClientHandler.class.getName()).log(Level.SEVERE, null, ex);
                   // dos.writeUTF("notvalid");
                    //dos.flush();
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
    /////////////////////////function change Balance//////////////////
    public synchronized void changeB (String req){
     String[] Array=req.split(" ");
       PreparedStatement quer=null;
       //ResultSet rs=null;
       
       System.out.println(Array[0]+"     "+Array[1]+"    "+Array[2]+"   "+Array[3]);
   int total = Integer.parseInt(Array[2]);
   int balance = Integer.parseInt(Array[3]);
   int cash = balance - total;
            try {
                System.out.println("in");
                quer=con.prepareStatement( "UPDATE client SET cash='"+cash+"' where c_name= '" + Array[1] +  "';");
                
                quer.executeUpdate();
            } catch (SQLException ex) {
                
                    System.out.println("failed");
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
    //////////////////////function get products List/////////////////////////
      private void ShowProductsList(String req ){
        String products="";
        String[] Array=req.split(" ");
        Statement stmt=null;
        Statement s=null;
        //String nam="";
        
        try {
            stmt = con.createStatement();
            ResultSet rs =stmt.executeQuery("select pname,total_price,no_of_products from cart where c_name='"+Array[1]+"';");
            while(rs.next()){
                products+=rs.getString(1)+"~@";
                products=products.concat(String.valueOf(rs.getFloat(2)))+"~@";
                products=products.concat(String.valueOf(rs.getInt(3)))+"~@";
            }
            //s=con.createStatement();
            //ResultSet rss =s.executeQuery("select price from product where pname=' "+Array[1]+"';");
            //System.out.println(products);
             //try {
                 out.println(products);
                 //dos.writeUTF(products);
                 //dos.flush();
             /*} catch (IOException ex) {
                 Logger.getLogger(ClientHandler.class.getName()).log(Level.SEVERE, null, ex);
             }*/
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
    
    


