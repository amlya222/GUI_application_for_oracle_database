import java.sql.*;
import java.awt.event.*;
import javax.swing.*;

public class CreateTable implements ActionListener {
    JFrame f;
    JLabel l1, l2, op;
    JButton b1;
    JTextField t1, t2;

    public CreateTable() {
        f = new JFrame();
        f.setVisible(true);
        f.setSize(500, 400);
        f.setLayout(null);

        l1 = new JLabel("Enter Table name:");
        f.add(l1);
        l1.setBounds(50, 50, 170, 30);

        t1 = new JTextField(50);
        f.add(t1);
        t1.setBounds(300, 50, 150, 30);

        l2 = new JLabel("Enter Columns of Table with datatype:");
        f.add(l2);
        l2.setBounds(50, 90, 230, 30);

        t2 = new JTextField();
        f.add(t2);
        t2.setBounds(300, 90, 150, 30);

        b1 = new JButton("Create Table");
        f.add(b1);
        b1.setBounds(180, 140, 140, 30);
        b1.addActionListener(this);

        op = new JLabel("");
        f.add(op);
        op.setBounds(130, 250, 100, 30);
    }

    public void actionPerformed(ActionEvent e) {
        try {
            Class.forName("oracle.jdbc.driver.OracleDriver");

            Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "system", "system");

            Statement stmt = con.createStatement();

            String s1 = t1.getText();
            String s2 = t2.getText();

            String query = "create table " + s1 + " (" + s2 + ")";
            stmt.executeUpdate(query);

            op.setText("Table is created");
            con.close();
        } catch (Exception ex) {
            System.out.println(ex);
        }
    }

}
