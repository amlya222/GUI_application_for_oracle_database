import java.sql.*;
import java.awt.event.*;
import javax.swing.*;

public class DeleteData implements ActionListener {
    JFrame f;
    JLabel l1, op;
    JButton b1;
    JTextField t1;

    public DeleteData() {
        f = new JFrame();
        f.setVisible(true);
        f.setSize(500, 400);
        f.setLayout(null);

        l1 = new JLabel("Enter SQL DELETE Query (e.g., 'DELETE FROM table_name WHERE condition'):");
        f.add(l1);
        l1.setBounds(50, 50, 400, 30);

        t1 = new JTextField(50);
        f.add(t1);
        t1.setBounds(50, 90, 400, 30);

        b1 = new JButton("Execute DELETE Query");
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
                op.setText("Data deleted successfully.");
            } else {
                op.setText("No data was deleted (check your query).");
            }

            con.close();
        } catch (Exception ex) {
            System.out.println(ex);
        }
    }
}
