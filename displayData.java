import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;



public class displayData extends JFrame implements ActionListener {



    JFrame frame1;
    JLabel l0, l1;

    JButton b1;
    JTextField t1=new JTextField(20);
    PreparedStatement pst;
    static JTable table;

    String[] columnNames = {"Book ID","Book Title","Author ID","Author Name"};

    String from;



    public displayData()
    {
        l0 = new JLabel("Fetching Book Information");
        l0.setForeground(Color.red);
        l0.setFont(new Font("TimesNewRoman", Font.BOLD, 20));
        l1 = new JLabel("Enter Book ID: ");
        b1 = new JButton("Submit");
        l0.setBounds(100, 50, 350, 40);
        l1.setBounds(75, 110, 100, 20);
        t1.setBounds(190,110,100,20);
        b1.setBounds(150, 150, 150, 20);
        b1.addActionListener(this);
        setTitle("Fetching Book Information");
        setLayout(null);
        setVisible(true);
        setSize(500, 500);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        add(l0);
        add(l1);
        add(b1);
        add(t1);

    }



    public void actionPerformed(ActionEvent ae) {

        if (ae.getSource() == b1)
        {

            showTableData();

        }



    }



    public void showTableData() {



        frame1 = new JFrame("Database Search Result");
        frame1.setLayout(new BorderLayout());

//TableModel tm = new TableModel();

        DefaultTableModel model = new DefaultTableModel();

        model.setColumnIdentifiers(columnNames);

        table = new JTable();

        table.setModel(model);

        table.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);

        table.setFillsViewportHeight(true);

        JScrollPane scroll = new JScrollPane(table);

        scroll.setHorizontalScrollBarPolicy(

                JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);

        scroll.setVerticalScrollBarPolicy(

                JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);

        from = t1.getText();
        int id;
        String  name, name1;

        try
        {

            Class.forName("oracle.jdbc.OracleDriver");
            Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl","system","iamxneutral");
            pst = con.prepareStatement("select * FROM book_data where book_id ="+t1.getText());
            ResultSet rs = pst.executeQuery();
            int i = 0;

            while (rs.next())
            {
                id=rs.getInt("book_id");
                name = rs.getString("title");
                name1=rs.getString("auth_name");
                String id2=rs.getString("auth_id");
                model.addRow(new Object[]{id,name,id2,name1});
                i++;
            }

            if (i < 1)
            {

                JOptionPane.showMessageDialog(null, "No Record Found", "Error", JOptionPane.ERROR_MESSAGE);
                return;

            }

        } catch (Exception ex) {

            JOptionPane.showMessageDialog(null, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);

        }

        frame1.add(scroll);
        frame1.setVisible(true);
        frame1.setSize(400, 300);

    }
    public static void main(String []args)
    {
        new displayData();
    }

}
