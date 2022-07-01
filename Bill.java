/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package parallel2;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.Socket;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import static javax.swing.text.html.HTML.Tag.P;
import net.proteanit.sql.DbUtils;
//import net.proteanit.sql.DbUtils;


//import org.apache.derby.iapi.sql.Statement;

/**
 *
 * @author Eslam Mohamed
 */
public class Bill extends javax.swing.JFrame {
 Vector productRecords;
     String str;
    ResultSet rs;
    Vector records;
    int i=0;
    ObjectInputStream br = null;
    Socket clientSocket = null;
     Publisher pub;
    public Bill() {
        initComponents();
         SelectSeller();
         GetCat();
        // insertBill();
       //  GetClient();
         /* records = new Vector();
         try {
                clientSocket = new Socket("localhost",1400);
                  br = new ObjectInputStream(clientSocket.getInputStream());
                  records =(Vector)br.readObject();
                  br.close();
                  int i =0;
              while(i < records.size())
                  {
                      pub = (Publisher)records.elementAt(i);
                      str = pub.ProductID;                 
                      ProductID.setText(str);
                      
                      str = pub.ProductName;
                      ProductName.setText(str);
                      
                      str = pub.ProductQun;
                      ProductQun.setText(str);
                      //str = pub.ProductPrice;
                     // ProductPrice.setText(str);

                      i++;                    
                  }
                  records.removeAllElements();



           } catch (IOException ex) {
                Logger.getLogger(AccountInformation.class.getName()).log(Level.SEVERE, null, ex);
            } catch (ClassNotFoundException ex) {
            Logger.getLogger(AccountInformation.class.getName()).log(Level.SEVERE, null, ex);
        }*/
        // insertBill();
    }
     public void SelectSeller(){
       try{       
       Con =(Connection) DriverManager.getConnection("jdbc:derby://localhost:1527/Store","user1","1234");      
       st=Con.createStatement();      
       Rs=(ResultSet) st.executeQuery("select * from user1.PRODUCTTABLE");       
       ProductTable.setModel(DbUtils.resultSetToTableModel((java.sql.ResultSet) Rs));
         
   }catch(Exception e){
       e.printStackTrace();
    }
   }
     
       private void GetCat(){
       try{    
       Con =DriverManager.getConnection("jdbc:derby://localhost:1527/Store","user1","1234");       
       st=Con.createStatement();
       String query="select * from user1.CATEGORY";      
       Rs=st.executeQuery(query);
       while(Rs.next()){
           String mycat=Rs.getString("CATNAME");
           catcb.addItem(mycat);         
       }                
   }catch(Exception e){
       e.printStackTrace();
    }  
   }
        int BNum;
       private void countBill()
       {
           try{
               st=Con.createStatement();
               Rs=st.executeQuery("select Max(BNum) from CARTTABLE");
               Rs.next();
               BNum=Rs.getInt(1)+1;
               
           }catch(Exception e){
       e.printStackTrace();
    }  
       }
    
     /*  private void insertBill(){          
        try{
            countBill();
            Con=DriverManager.getConnection("jdbc:derby://localhost:1527/Store","user1","1234");              
            PreparedStatement add=Con.prepareStatement("Insert into CARTTABLE Values(?,?)"); 
             System.out.println("1");
            add.setInt(1,Integer.valueOf(SellerName.getText()));
             System.out.println("2");
           
               System.out.println("3");
            add.setInt(3,Integer.valueOf(ProductQun.getText())); 
            add.setInt(4,ProdTotal); 
            add.setInt(5,Total);
            DateTimeFormatter dtf=DateTimeFormatter.ofPattern("yyyy/mm/dd");
            LocalDateTime now=LocalDateTime.now(); 
            add.setString(6, now.toString().substring(1, 10));
            int row=add.executeUpdate();
            JOptionPane.showMessageDialog(this, "Bill is Added Successfully");    
            Con.close();
            SelectSeller();
            
        }catch(Exception e){
             System.out.println("154");
            e.printStackTrace(); 
            
        }
      
       }
    /*   private void insertBillAdmin(){          
        try{
            countBill();
            Con=DriverManager.getConnection("jdbc:derby://localhost:1527/Store","user1","1234");              
            PreparedStatement add=Con.prepareStatement("Insert into BILLTABLE Values(?,?,?,?)");    
            add.setInt(1,BNum);
           // add.setInt(4,Integer.valueOf(ProductID.getText()));
            add.setString(2,SellerName.getText());
            DateTimeFormatter dtf=DateTimeFormatter.ofPattern("yyyy/mm/dd");
            LocalDateTime now=LocalDateTime.now(); 
            add.setString(3, now.toString().substring(1, 10));
            add.setInt(4,ProdTotal);  
            int row=add.executeUpdate();
            JOptionPane.showMessageDialog(this, "Bill is Added Successfully");    
            Con.close();
            SelectSeller();
        }catch(Exception e){
            e.printStackTrace();        
        }
      
       }*/
    Connection Con=null;
    Statement st=null;
    ResultSet Rs=null;
   /* private void GetClientName(){
        
        try{
             String query="select SELERNAME from SELERTABLE"; 
       Con =DriverManager.getConnection("jdbc:derby://localhost:1527/Store","user1","1234");       
       st=Con.createStatement();    
       Rs=st.executeQuery(query);
       if(Rs.next())
       {
           SellerName.setText(Rs.getString("SellerName"));
       }
        }catch(Exception e){
            e.printStackTrace();        
        }
    }
    private void GetClient(){
         
        try{
       Con =DriverManager.getConnection("jdbc:derby://localhost:1527/Store","user1","1234");       
       st=Con.createStatement();    
         String query="Select* from SELERTABLE"; 
       Rs=st.executeQuery(query);
     
        }catch(Exception e){
            e.printStackTrace();        
        }
    }
   */
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jPanel4 = new javax.swing.JPanel();
        jLabel8 = new javax.swing.JLabel();
        ProductQun = new javax.swing.JTextField();
        ProductName = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        DeleteBtn = new javax.swing.JButton();
        ClearBtn = new javax.swing.JButton();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        ProductTable = new javax.swing.JTable();
        jScrollPane2 = new javax.swing.JScrollPane();
        BillText = new javax.swing.JTextArea();
        AddBtn = new javax.swing.JButton();
        catcb = new javax.swing.JComboBox<>();
        jLabel1 = new javax.swing.JLabel();
        filter = new javax.swing.JButton();
        Refresh = new javax.swing.JButton();
        jLabel6 = new javax.swing.JLabel();
        SellerName = new javax.swing.JTextField();
        TotalLable = new javax.swing.JLabel();
        purchase1 = new javax.swing.JButton();
        jButton1 = new javax.swing.JButton();
        totallbl = new javax.swing.JLabel();
        LogBtn = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(51, 153, 0));

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));

        jPanel4.setBackground(new java.awt.Color(255, 255, 255));

        jLabel8.setBackground(new java.awt.Color(51, 153, 0));
        jLabel8.setFont(new java.awt.Font("Tahoma", 1, 36)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(51, 153, 0));
        jLabel8.setText("Order");

        ProductQun.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N

        ProductName.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N

        jLabel3.setBackground(new java.awt.Color(0, 0, 51));
        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(51, 153, 0));
        jLabel3.setText("Product List");

        jLabel4.setBackground(new java.awt.Color(0, 0, 51));
        jLabel4.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(51, 153, 0));
        jLabel4.setText("username");

        jLabel5.setBackground(new java.awt.Color(0, 0, 51));
        jLabel5.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(51, 153, 0));
        jLabel5.setText("Quantity");

        DeleteBtn.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        DeleteBtn.setForeground(new java.awt.Color(51, 153, 0));
        DeleteBtn.setText("Remove");
        DeleteBtn.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                DeleteBtnMouseClicked(evt);
            }
        });

        ClearBtn.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        ClearBtn.setForeground(new java.awt.Color(51, 153, 0));
        ClearBtn.setText("Clear");
        ClearBtn.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                ClearBtnMouseClicked(evt);
            }
        });

        jLabel9.setBackground(new java.awt.Color(0, 0, 51));
        jLabel9.setFont(new java.awt.Font("Tahoma", 1, 30)); // NOI18N
        jLabel9.setText("Manage Products");

        jLabel10.setBackground(new java.awt.Color(0, 0, 51));
        jLabel10.setFont(new java.awt.Font("Tahoma", 1, 30)); // NOI18N
        jLabel10.setText("Manage Products");

        jLabel11.setBackground(new java.awt.Color(0, 0, 51));
        jLabel11.setFont(new java.awt.Font("Tahoma", 1, 30)); // NOI18N
        jLabel11.setText("Manage Products");

        ProductTable.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        ProductTable.setForeground(new java.awt.Color(51, 153, 0));
        ProductTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "Name", "Quantity", "Price", "category"
            }
        ));
        ProductTable.setIntercellSpacing(new java.awt.Dimension(0, 0));
        ProductTable.setRowHeight(25);
        ProductTable.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                ProductTableMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(ProductTable);

        BillText.setColumns(20);
        BillText.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        BillText.setRows(5);
        jScrollPane2.setViewportView(BillText);

        AddBtn.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        AddBtn.setForeground(new java.awt.Color(51, 153, 0));
        AddBtn.setText(" Add to cart");
        AddBtn.setToolTipText("");
        AddBtn.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                AddBtnMouseClicked(evt);
            }
        });
        AddBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                AddBtnActionPerformed(evt);
            }
        });

        catcb.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        catcb.setForeground(new java.awt.Color(51, 153, 0));
        catcb.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                catcbItemStateChanged(evt);
            }
        });
        catcb.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                catcbMouseClicked(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel1.setText("Filtered By");

        filter.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        filter.setForeground(new java.awt.Color(51, 153, 0));
        filter.setText("Filter");
        filter.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                filterMouseClicked(evt);
            }
        });

        Refresh.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        Refresh.setForeground(new java.awt.Color(51, 153, 0));
        Refresh.setText("Refresh");
        Refresh.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                RefreshMouseClicked(evt);
            }
        });

        jLabel6.setBackground(new java.awt.Color(0, 0, 51));
        jLabel6.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(51, 153, 0));
        jLabel6.setText("Product name");

        SellerName.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(321, 321, 321))
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 82, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addComponent(AddBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 117, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(ClearBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(DeleteBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel6)
                                    .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(SellerName, javax.swing.GroupLayout.DEFAULT_SIZE, 189, Short.MAX_VALUE)
                                    .addComponent(ProductName)
                                    .addComponent(ProductQun)))
                            .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 394, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addComponent(jLabel1)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(catcb, javax.swing.GroupLayout.PREFERRED_SIZE, 137, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(filter, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(Refresh))
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 416, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGap(357, 357, 357)
                        .addComponent(jLabel8)))
                .addContainerGap(260, Short.MAX_VALUE))
            .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                    .addContainerGap(350, Short.MAX_VALUE)
                    .addComponent(jLabel9)
                    .addGap(229, 229, 229)))
            .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                    .addContainerGap(360, Short.MAX_VALUE)
                    .addComponent(jLabel10)
                    .addGap(219, 219, 219)))
            .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                    .addContainerGap(370, Short.MAX_VALUE)
                    .addComponent(jLabel11)
                    .addGap(209, 209, 209)))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel8)
                        .addGap(16, 16, 16)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(SellerName, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(ProductName, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGap(58, 58, 58)
                        .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(filter, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(catcb, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel1)
                            .addComponent(Refresh, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(ProductQun, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(26, 26, 26)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(AddBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(ClearBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(DeleteBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane2)
                        .addContainerGap())
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 382, Short.MAX_VALUE)))
            .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel4Layout.createSequentialGroup()
                    .addGap(58, 58, 58)
                    .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 0, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(478, Short.MAX_VALUE)))
            .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel4Layout.createSequentialGroup()
                    .addGap(68, 68, 68)
                    .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 0, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(468, Short.MAX_VALUE)))
            .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel4Layout.createSequentialGroup()
                    .addGap(78, 78, 78)
                    .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 0, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(458, Short.MAX_VALUE)))
        );

        TotalLable.setFont(new java.awt.Font("Tahoma", 0, 20)); // NOI18N
        TotalLable.setForeground(new java.awt.Color(51, 153, 0));
        TotalLable.setText("Total: ");

        purchase1.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        purchase1.setForeground(new java.awt.Color(51, 153, 0));
        purchase1.setText("purchase");
        purchase1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                purchase1MouseClicked(evt);
            }
        });
        purchase1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                purchase1ActionPerformed(evt);
            }
        });

        jButton1.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jButton1.setForeground(new java.awt.Color(51, 153, 0));
        jButton1.setText("Account");
        jButton1.setActionCommand("");
        jButton1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButton1MouseClicked(evt);
            }
        });
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        totallbl.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        totallbl.setForeground(new java.awt.Color(51, 153, 0));
        totallbl.setText("Total:");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(175, 175, 175)
                        .addComponent(TotalLable, javax.swing.GroupLayout.PREFERRED_SIZE, 149, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(58, 58, 58)
                        .addComponent(totallbl, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(210, 210, 210)
                        .addComponent(purchase1, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(205, 205, 205)
                        .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 94, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap(46, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(TotalLable, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel4, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(purchase1, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(totallbl, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(26, 26, 26))
        );

        LogBtn.setBackground(new java.awt.Color(51, 153, 0));
        LogBtn.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        LogBtn.setForeground(new java.awt.Color(240, 240, 240));
        LogBtn.setText("Logout");
        LogBtn.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                LogBtnMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGap(4, 4, 4)
                .addComponent(LogBtn, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, 851, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(27, 27, 27))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(LogBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(37, 37, 37))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void DeleteBtnMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_DeleteBtnMouseClicked
        
    }//GEN-LAST:event_DeleteBtnMouseClicked

    private void ClearBtnMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_ClearBtnMouseClicked
      //  BillID.setText("");
        ProductName.setText("");
      //  ProductPrice.setText("");
        ProductQun.setText("");
    }//GEN-LAST:event_ClearBtnMouseClicked
 /*public  void inserttovec()
     {  
         DefaultTableModel model=(DefaultTableModel)ProductTable.getModel();
         int Myindex=ProductTable.getSelectedRow();
         Publisher p=new Publisher();
           p.ProductID=model.getValueAt(Myindex, 1).toString();
           p.ProductName=model.getValueAt(Myindex, 2).toString();
           p.ProductQun=Integer.valueOf(model.getValueAt(Myindex, 3).toString()).toString();
           p.ProductPrice=Integer.valueOf(model.getValueAt(Myindex, 4).toString()).toString();
          productRecords.addElement(p);

        
         
     }*/
    Double ProdTotal=0.0,Uprice,Total=0.0;
int AvailQun;
    private void ProductTableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_ProductTableMouseClicked
        DefaultTableModel model=(DefaultTableModel)ProductTable.getModel();
       int Myindex=ProductTable.getSelectedRow();
       AvailQun=Integer.valueOf(model.getValueAt(Myindex, 2).toString());
       ProductName.setText(model.getValueAt(Myindex, 1).toString());
       Uprice=Double.valueOf(model.getValueAt(Myindex, 3).toString());
       ProdTotal=Uprice*Integer.valueOf(ProductQun.getText());
    }//GEN-LAST:event_ProductTableMouseClicked

    private void AddBtnMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_AddBtnMouseClicked
       // insertBillAdmin();;
      //  inserttovec();
        if(ProductName.getText().isEmpty()||ProductQun.getText().isEmpty())
      {
          JOptionPane.showMessageDialog(this, "Missing Information");    
      }else if(AvailQun<=Integer.valueOf(ProductQun.getText())){
          JOptionPane.showMessageDialog(this, "Not Enough In Stock");    
      }
         else{
              i++;
              ProdTotal=Uprice*Integer.valueOf(ProductQun.getText());
              Total+=ProdTotal;
              if(i==1){
                  BillText.setText(BillText.getText()+"      ==============H&M STORE==========\n"+" NUMBER    PRODUCT    PRICE    QUANTITY     TOTAL\n"+i+"               "+ProductName.getText()+"         "+Uprice+"         "+ProductQun.getText()+"               "+ProdTotal+"\n");
              }else{
                  BillText.setText(BillText.getText()+i+"               "+ProductName.getText()+"         "+Uprice+"         "+ProductQun.getText()+"               "+ProdTotal+"\n");
              }
              totallbl.setText("Total: "+Total);
              
          }
    }//GEN-LAST:event_AddBtnMouseClicked

    private void AddBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_AddBtnActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_AddBtnActionPerformed

    private void LogBtnMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_LogBtnMouseClicked
       new Login().setVisible(true);
       this.dispose();
    }//GEN-LAST:event_LogBtnMouseClicked

    private void catcbMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_catcbMouseClicked
  
        try{      
       Con =(Connection) DriverManager.getConnection("jdbc:derby://localhost:1527/Store","user1","1234");     
       st=Con.createStatement();      
     Rs=(ResultSet) st.executeQuery("select * from user1.PRODUCTTABLE where CATNAME='"+catcb.getSelectedItem().toString()+"'");      
       ProductTable.setModel(DbUtils.resultSetToTableModel((java.sql.ResultSet) Rs));   
   }catch(Exception e){
       e.printStackTrace();
    }
    }//GEN-LAST:event_catcbMouseClicked

    private void filterMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_filterMouseClicked
        // TODO add your handling code here:
         
         try{        
       Con =(Connection) DriverManager.getConnection("jdbc:derby://localhost:1527/Store","user1","1234");
       st=Con.createStatement();      
       //Rs=(ResultSet) st.executeQuery("select * from user1.CATEGORY where CATNAME='"+catcb.getSelectedItem().toString()+"'");   
       Rs=(ResultSet) st.executeQuery("select * from user1.PRODUCTTABLE where CATEGORY='"+catcb.getSelectedItem().toString()+"'");   
       ProductTable.setModel(DbUtils.resultSetToTableModel((java.sql.ResultSet) Rs));        
   }catch(Exception e){
       e.printStackTrace();
    }
    }//GEN-LAST:event_filterMouseClicked

    private void RefreshMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_RefreshMouseClicked
       SelectSeller();
    }//GEN-LAST:event_RefreshMouseClicked

    private void catcbItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_catcbItemStateChanged
       /*  try{      
       Con =(Connection) DriverManager.getConnection("jdbc:derby://localhost:1527/Store","user1","1234");     
       st=Con.createStatement();      
     Rs=(ResultSet) st.executeQuery("select * from user1.CATEGORY where CATNAME='"+catcb.getSelectedItem().toString()+"'");      
       ProductTable.setModel(DbUtils.resultSetToTableModel((java.sql.ResultSet) Rs));   
   }catch(Exception e){
       e.printStackTrace();
    }*/
    }//GEN-LAST:event_catcbItemStateChanged

    private void purchase1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_purchase1MouseClicked
     //   insertBill();
        new payment().setVisible(true);
       this.dispose();
    }//GEN-LAST:event_purchase1MouseClicked

    private void purchase1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_purchase1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_purchase1ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton1MouseClicked
        new AccountInformation().setVisible(true);
       this.dispose();        
    }//GEN-LAST:event_jButton1MouseClicked
  
    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Bill.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Bill.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Bill.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Bill.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Bill().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton AddBtn;
    private javax.swing.JTextArea BillText;
    private javax.swing.JButton ClearBtn;
    private javax.swing.JButton DeleteBtn;
    private javax.swing.JLabel LogBtn;
    private javax.swing.JTextField ProductName;
    private javax.swing.JTextField ProductQun;
    private javax.swing.JTable ProductTable;
    private javax.swing.JButton Refresh;
    private javax.swing.JTextField SellerName;
    private javax.swing.JLabel TotalLable;
    private javax.swing.JComboBox<String> catcb;
    private javax.swing.JButton filter;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JButton purchase1;
    private javax.swing.JLabel totallbl;
    // End of variables declaration//GEN-END:variables
}
