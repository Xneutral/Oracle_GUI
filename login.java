import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;

public class login extends JFrame implements ActionListener
{
    JLabel l1,l2;
    JTextField t1;
    JPasswordField t2;
    JButton button;
    public  login()
    {
        setLayout(new FlowLayout());
        setSize(1000,500);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        l1=new JLabel("Username: ");
        l1.setBounds(300,450,50,50);
        l2=new JLabel("Password: ");
        l2.setBounds(400,500,50,50);
        t1=new JTextField(20);
        t2=new JPasswordField(20);
        button=new JButton("Login");
        button.addActionListener(this);
        add(l1);
        add(t1);
        add(l2);
        add(t2);
        add(button);
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e)
    {
        if(t1.getText().equals(""))
        {
            JOptionPane.showMessageDialog(null,"Please fill out the form.");
            return;
        }
        try
       {
           Class.forName("oracle.jdbc.OracleDriver");
           Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl","system","iamxneutral");
           Statement st= con.createStatement();
           String query="select passwrd from login where user_id='"+t1.getText()+"'";
           ResultSet rs=st.executeQuery(query);
           String get_password="";
           while(rs.next())
           {
               get_password=rs.getString(1);
           }
           String str= String.valueOf(t2.getPassword());
           if(get_password.equals(str))
           {
               new displayTable();
           }
           else
           {
               JOptionPane.showMessageDialog(null,"Invalid user");
           }
       }
       catch (Exception ex)
       {
           JOptionPane.showMessageDialog(null,ex);

       }
    }

    public static void main(String[]args)
    {
        new login();
    }

}