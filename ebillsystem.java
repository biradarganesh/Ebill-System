import java.sql.*;
import java.sql.DriverManager;
import javax.swing.table.DefaultTableModel;
import java.sql.Connection;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.print.PrinterException;
import java.util.logging.Level;

public class ebillsystem implements ActionListener{  
JLabel CID,Cname,unit,Electricity;
JTextField t1,t2,t3, t4;
JTextArea tx;
JButton cal,print,Clear,Exit,View,Insert,Delete;

ebillsystem()


{
	
JFrame frame = new JFrame("EBILLSYSTEM");

        JPanel panel = new JPanel();   
        panel.setLayout(null); 
        panel.setBackground(Color.LIGHT_GRAY);

 
        JLabel label = new JLabel("Electricity Billing System");
        label.setForeground(Color.RED);
        label.setBounds(600,0,700,350);  
        label.setFont(new Font("Tahoma",Font.BOLD,50));
        panel.add(label); 

CID=new JLabel ("Customer Id :-");
CID.setBounds(200,200,200,120);
CID.setFont(new Font("Tahoma",Font.PLAIN,30));
panel.add(CID);

t1=new JTextField (10);
t1.setBounds(450,250,200,35);
t1.setFont(new Font("Tahoma",Font.PLAIN,30));
panel.add(t1);

Cname=new JLabel ("Customer Name :-");
Cname.setBounds(200,320,300,120);
Cname.setFont(new Font("Tahoma",Font.PLAIN,30));
panel.add(Cname); 

t2=new JTextField (10);
t2.setBounds(450,360,200,35);
t2.setFont(new Font("Tahoma",Font.PLAIN,30));
panel.add(t2); 

unit= new JLabel ("Unit :-");
unit.setBounds(200,470,120,40);
unit.setFont(new Font("Tahoma",Font.PLAIN,30));
panel.add(unit);

t3=new JTextField (10);
t3.setBounds(450,470,200,35);
t3.setFont(new Font("Tahoma",Font.PLAIN,30));
panel.add(t3); 

View=new JButton("View");
View.setBounds(300,650,200,50); 
View.setFont(new Font("Tahoma",Font.BOLD,30));
panel.add(View);

View.addActionListener(this);

cal=new JButton("Calculator");
cal.setBounds(300,580,200,50); 
cal.setFont(new Font("Tahoma",Font.BOLD,30));
panel.add(cal);

cal.addActionListener(this);


tx=new JTextArea (50,30);
tx.setBounds(800,250,400,250);
tx.setFont(new Font("Tahoma",Font.PLAIN,30));
panel.add(tx); 

print=new JButton ("Print");
print.setBounds(900,580,180,50);
print.setFont(new Font("Tahoma",Font.BOLD,30));
panel.add(print);

print.addActionListener(this);

Clear=new JButton("Clear");
Clear.setBounds(600,580,200,50); 
Clear.setFont(new Font("Tahoma",Font.BOLD,30));
panel.add(Clear);

Clear.addActionListener(this);


Exit=new JButton("Exit");
Exit.setBounds(1150,580,200,50); 
Exit.setFont(new Font("Tahoma",Font.BOLD,30));
panel.add(Exit);

Exit.addActionListener(this);

Insert=new JButton("Insert");
Insert.setBounds(600,650,200,50); 
Insert.setFont(new Font("Tahoma",Font.BOLD,30));
panel.add(Insert);

Insert.addActionListener(this);

Delete=new JButton("Delete");
Delete.setBounds(900,650,200,50); 
Delete.setFont(new Font("Tahoma",Font.BOLD,30));
panel.add(Delete);

Delete.addActionListener(this);

t4=new JTextField (5);
t4.setBounds(1150,650,150,40);
t4.setFont(new Font("Tahoma",Font.PLAIN,30));
panel.add(t4); 






panel.setLayout(new BorderLayout());




        
        frame.add(panel);  
        frame.setSize(200, 300);  
       // frame.setLocationRelativeTo(null);  
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);  
        frame.setVisible(true); 
}

 String cid;
        double charge,grantam;
        int count;
        
        public void print()
        {
            String cid= t1.getText();
            String cname=t2.getText();
            String unit=t3.getText();
           
            tx.setText(tx.getText() + "Electricity Billing System" + "\n");
            tx.setText(tx.getText() + "\n");
            tx.setText(tx.getText() + "Customer ID : =" +cid  + "\n");
            tx.setText(tx.getText() + "Customer Name : =" +cname +"\n");
            tx.setText(tx.getText() + "Amount : =" +grantam + "\n"); 
            tx.setText(tx.getText() + "Thank You Come Again " + "\n");
                    
        }

public void actionPerformed(ActionEvent e)
{
  

if(e.getActionCommand()== "Calculator")
{
   cid=t1.getText();
      count=Integer.parseInt(t3.getText());
        
              if(count<500)
              {
                  charge=1.00;
              }
              else if(count<500 && count <600)
              {
                  charge=1.50;
              }
              else if(count<600 && count <800)
              {
                  charge=2.00;
              }
               else
              {
                  charge=3.00;
              }
              
              grantam= charge*count;
              print();
}

 if(e.getActionCommand()== "Exit")
{
  System.exit(0);
}

 if(e.getActionCommand()== "Clear")
{
  t1.setText("");
  t2.setText("");
  t3.setText("");
  tx.setText("");

}



try{

Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");

String url="jdbc:ucanaccess://D://jdk1.8.0_271//bin//EBill.accdb";

Connection con=DriverManager.getConnection(url);


 if(e.getActionCommand()== "View")
{

 
 
 String query = "SELECT * FROM EBillD";
    
      Statement stm = con.createStatement();
      ResultSet res = stm.executeQuery(query);
    
      String columns[] = { "ID", "Name", "Unit" };
      String data[][] = new String[8][3];
    
      int i = 0;
      while (res.next()) {
		String id=String.valueOf(res.getInt("Cust_ID"));
String name=res.getString("Cust_Name");
String unit=String.valueOf(res.getInt("Unit"));
      

	  
        data[i][0] = id ;
        data[i][1] = name;
        data[i][2] = unit;
        i++;
      }
    
      DefaultTableModel model = new DefaultTableModel(data, columns);
      JTable table = new JTable(model);
      table.setShowGrid(true);
      table.setShowVerticalLines(true);
      JScrollPane pane = new JScrollPane(table);
      JFrame f = new JFrame("Populate JTable from Database");
      JPanel panel = new JPanel();
      panel.add(pane);
      f.add(panel);
      f.setSize(500, 250);
      f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      f.setVisible(true);
        }

 if(e.getActionCommand()== "Insert")
{
String id,name,unit;
id=t1.getText();
name=t2.getText();
unit=t3.getText();

PreparedStatement pst=con.prepareStatement("insert into EBillD (Cust_ID,Cust_Name,Unit) values(?,?,?) ");
pst.setString(1, id);
pst.setString(2, name);
pst.setString(3, unit);
pst.executeUpdate();

tx.setText("Record inserted");


}
 if(e.getActionCommand()== "Delete")
{
String id,name,unit;
id=t4.getText();
name=t2.getText();
unit=t3.getText();

String q="delete from EBillD where Cust_ID=? "; 

PreparedStatement pst=con.prepareStatement(q);
int s=Integer.parseInt(id);
pst.setInt(1,s);

pst.execute();			
//to execute query 										
tx.setText("Record Deleted...");


}

con.close();

}
catch(Exception d)
{
d.printStackTrace();}



if(e.getActionCommand()== "Print")
{
      try {
                  // TODO add your handling code here:
            tx.print();
                    } catch (PrinterException ex) {
                                java.util.logging.Logger.getLogger(ebillsystem.class.getName()).log(Level.SEVERE, null, ex);
                                        }
                                          
    }                               
    
}



    public static void main(String args[]) {  
        new ebillsystem();
}
}
