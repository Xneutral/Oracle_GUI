import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;



public class displayTable extends JFrame implements ActionListener
{
    JFrame frame1;
    JLabel l0;
    JButton b1;
    PreparedStatement pst;
    static JTable table;
    String[] columnNames = {"Book ID","Book Title","Author Id","Author Name"};

    public displayTable() throws NullPointerException
    {
        l0 = new JLabel("Fetching Databases");
        l0.setForeground(Color.red);
        l0.setFont(new Font("TimesNewRoman", Font.BOLD, 20));
        b1 = new JButton("Fetch View");
        l0.setBounds(100, 50, 350, 40);
        b1.setBounds(150, 150, 150, 20);
        b1.addActionListener(this);
        setTitle("Fetching Database");
        setLayout(null);
        setVisible(true);
        setSize(1366,768);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        add(l0);
        add(b1);
    }



    public void actionPerformed(ActionEvent ae)
    {
        if (ae.getSource() == b1)
        {
            showTableData();
        }
    }



    public void showTableData()
    {
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
        scroll.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        scroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        int id,id2;
        String  name, name1;
        try
        {
            Class.forName("oracle.jdbc.OracleDriver");
            Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl","system","iamxneutral");
            pst = con.prepareStatement("select * FROM book_data");
            ResultSet rs = pst.executeQuery();
            int i = 0;

            while (rs.next())
            {
                id=rs.getInt("book_id");
                name = rs.getString("title");
                id2= Integer.parseInt(rs.getString("auth_id"));
                 String auth_name=rs.getString("auth_name");
                model.addRow(new Object[]{id,name,id2,auth_name});
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
        frame1.setSize(500,200);

    }
    public static void main(String []args) throws NullPointerException
    {
        new displayTable();
    }

}
