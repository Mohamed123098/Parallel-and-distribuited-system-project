package parallel2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.derby.iapi.reference.ClassName;

public class Client {
    
    private static BufferedReader in;
    private static PrintWriter out;
    private static String c_name=""; // c_name="" when login is finished
    private static Socket connection=null ;
    private static String IP;
    public static void setIP(String IP) {
     Client.IP = IP;
  
    }
    
    public static void init() {
        try {
            //InetAddress addr=InetAddress.getByName ("Localhost");
            connection = new Socket(IP, 1234);
            
            in=new BufferedReader(new InputStreamReader(connection.getInputStream()));
            out  = new PrintWriter(connection.getOutputStream(),true);
            //String x=in.readLine();
            //out.println("hello");
        } catch (UnknownHostException ex) {
            Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static void setC_name(String c_name) {
        Client.c_name = c_name;
    }
    
    public static String getProducts(){
        String products="";
        out.println("getProducts");
        try {
            products=in.readLine();
        } catch (IOException ex) {
            Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
        }
        return products;
    }

   public static void addToCart(String product ,String quantiy,double total){
       out.println("addToCart~@"+c_name+"@"+total+"@"+quantiy+"~@"+product);
   }
   
    public static void removeFromCart(String pname ,String quantity) {
        out.println("removeFromCart~@"+c_name+"@"+pname+"@"+quantity);
    }
    public static String getAvailQun(String pname) {
        String AvailQun="";
        out.println("getAvailQun~@"+pname);
        try {
            AvailQun= in.readLine();
        } catch (IOException ex) {
            Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
        }
        return AvailQun;
        
    }
    
    public static void addProduct(String pname,String quantity,String price,String category ) {
        out.println("addProduct~@"+quantity+"@"+price+"@"+pname+"~@"+category);
    }
    public static void editProduct(String pname,String quantity,String price,String category ) {
        out.println("editProduct~@"+quantity+"@"+price+"@"+pname+"~@"+category);
    }
    public static void deleteProduct(String pname){
        out.println("deleteProduct~@"+pname);
    }
    
    public static void close() {       
        try {
            out.close();
            in.close();
            connection.close();
        } catch (IOException ex) {
            Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
        }
    }



//////////////Function request for Sign Up////////////////////
   public static String signupCS(String uname,String pass,String address,int phone,String gender){
        
        out.println("signup "+uname+" "+pass+" "+address+" "+phone+" "+gender);
        String valid="";
        
        try {
            valid=in.readLine();
        } catch (IOException ex) {
            Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
        }
        return valid;
    }

//////////////Function request for Login////////////////////
    public static String loginCS(String Role,String uname,String pass){
        out.println("login "+Role+" "+uname+" "+pass);
        
        String valid="";
         try {
           
            valid=in.readLine();

        } catch (IOException ex) {
            Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
        }
        return valid;
    }
//////////////Function request for ViewAccountInformation////////////////////
  public static String AccountInformationCS(String uname){
    String data ="";
      out.println("Account "+uname);
    try{
    //dos.writeUTF("Account "+uname);
    //dos.flush();
    data = in.readLine();
    }
    catch (IOException ex) {
            Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
        }
    return data;
    }
//////////////Function request for ShowClientList to Admin////////////////////
  public static String getList(){
      String clients="";
        out.println("getCLientList");
        
        try {
            //dos.writeUTF("getCLientList");
            //dos.flush();
            clients=in.readLine();
            //clients=dis.readUTF();
        } catch (IOException ex) {
            Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
        }
        return clients;
    }
//////////////Function request to deposit money in E-wallet////////////////////
    public synchronized static void depositCS (String uname,int balance,int depositedMoney)
    {
     //String data = "";
        out.println("deposit "+uname+" "+balance+" "+depositedMoney);
    try{
   // dos.writeUTF("deposit "+uname+" "+balance+" "+depositedMoney);
   // dos.flush();
    //out.println("deposit "+uname+" "+balance+" "+depositedMoney);
  // data = dis.readUTF();
    }
    catch (IOException ex) {
            Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
        }
    //return data;
    
    }
    
    public static void changeBalance(String uname,int total,int balance){
        
   out.println("change "+uname+" "+total+" "+balance);
   
    
     }
    public static String purchase (String uname){
    String data="";
    out.println("show_Balance "+uname);
    System.out.println("op");
        System.out.println("oooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooo");
    
        try {
            data =String.valueOf(in.readLine());
        } catch (IOException ex) {
            Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
        }
   
        System.out.println(" client bal"+data);
    return data;
    }
        public static String getProductsList(String uname){
        String products="";
        out.println("getProductsList "+uname);
        
        try {
      //      dos.writeUTF("getProductsList "+uname);
        //    dos.flush();
            products=in.readLine();
           // products=dis.readUTF();
        } catch (IOException ex) {
            Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
        }
        return products;
    }
}
