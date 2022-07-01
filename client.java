














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
