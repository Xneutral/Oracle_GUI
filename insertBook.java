import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.concurrent.Callable;

public class insertBook extends JFrame implements ActionListener
{
    JLabel l1,l2,l3;
    JTextField t1,t2,t3;
    JButton b1;
    public insertBook()
    {
        l1=new JLabel("Enter Book ID: ");
        l2=new JLabel("Enter Book Title: ");
        l3=new JLabel("Enter Author ID: ");
        t1=new JTextField(30);
        t2=new JTextField(30);
        t3=new JTextField(30);
        b1=new JButton("Insert");
        b1.addActionListener(this);
        setTitle("Insert Book Record");
        setLayout(new FlowLayout());
        setVisible(true);
        setSize(500, 500);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        add(l1);
        add(t1);
        add(l2);
        add(t2);
        add(l3);
        add(t3);
        add(b1);
    }

    public static void main(String[] args)
    {
     new insertBook();
    }

    @Override
    public void actionPerformed(ActionEvent e)
    {
        try {
            Class.forName("oracle.jdbc.OracleDriver");
            Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl","system","iamxneutral");
            CallableStatement cs=con.prepareCall("{ call insertdata(?,?,?) }");
            int book_id= Integer.parseInt(t1.getText());
            int auth_id=Integer.parseInt(t3.getText());
            String title=t2.getText();
            cs.setInt(1,book_id);
            cs.setString(2,title);
            cs.setInt(3,auth_id);
            cs.executeQuery();
            System.out.print("uploaded successfully\n");
        } catch (ClassNotFoundException | SQLException ex)
        {
            ex.printStackTrace();
        }

    }
}
