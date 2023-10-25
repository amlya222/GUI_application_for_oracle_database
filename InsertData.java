import java.sql.*;
import java.awt.event.*;
import javax.swing.*;

public class InsertData implements ActionListener {
    JFrame f;
    JLabel l1, op;
    JButton b1;
    JTextField t1, t2;

    public InsertData() {
        f = new JFrame();
        f.setVisible(true);
        f.setSize(500, 400);
        f.setLayout(null);

        l1 = new JLabel("Enter SQL INSERT Query (e.g., 'INSERT INTO table_name (column1, column2) VALUES (value1, value2)'):");
        f.add(l1);
        l1.setBounds(50, 50, 400, 30);

        t1 = new JTextField(50);
        f.add(t1);
        t1.setBounds(50, 90, 400, 30);

        b1 = new JButton("Execute INSERT Query");
        f.add(b1);
        b1.setBounds(180, 140, 160, 30);
        b1.addActionListener(this);

        op = new JLabel("Result:");
        f.add(op);
        op.setBounds(50, 190, 100, 30);
    }

    public void actionPerformed(ActionEvent e) {
        try {
            Class.forName("oracle.jdbc.driver.OracleDriver");

            Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "system", "system");

            Statement stmt = con.createStatement();

            String query = t1.getText();
            int rowsAffected = stmt.executeUpdate(query);

            if (rowsAffected > 0) {
                op.setText("Data inserted successfully.");
            } else {
                op.setText("Failed to insert data.");
            }

            con.close();
        } catch (Exception ex) {
            System.out.println(ex);
        }
    }
}
