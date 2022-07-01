














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
    try{
    dos.writeUTF("Account "+uname);
    dos.flush();
    data = dis.readUTF();
    }
    catch (IOException ex) {
            Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
        }
    return data;
    }
//////////////Function request for ShowClientList to Admin////////////////////
  public static String getList(){
      String clients="";
        //out.println("getProductsList");
        
        try {
            dos.writeUTF("getCLientList");
            dos.flush();
            //products=in.readLine();
            clients=dis.readUTF();
        } catch (IOException ex) {
            Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
        }
        return clients;
    }
//////////////Function request to deposit money in E-wallet////////////////////
    public static void depositCS (String uname,int balance,int depositedMoney)
    {
     //String data = "";
    try{
    dos.writeUTF("deposit "+uname+" "+balance+" "+depositedMoney);
    dos.flush();
    //out.println("deposit "+uname+" "+balance+" "+depositedMoney);
  // data = dis.readUTF();
    }
    catch (IOException ex) {
            Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
        }
    //return data;
    
    }
