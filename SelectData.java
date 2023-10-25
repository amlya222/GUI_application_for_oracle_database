import java.sql.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class SelectData implements ActionListener {
    JFrame f;
    JLabel l1, op;
    JButton b1;
    JTextField t1;
    JTable table;
    DefaultTableModel model;

    public SelectData() {
        f = new JFrame();
        f.setVisible(true);
        f.setSize(600, 400);
        f.setLayout(null);

        l1 = new JLabel("Enter SQL SELECT Query:");
        f.add(l1);
        l1.setBounds(50, 50, 200, 30);

        t1 = new JTextField(50);
        f.add(t1);
        t1.setBounds(260, 50, 190, 30);

        b1 = new JButton("Execute SELECT Query");
        f.add(b1);
        b1.setBounds(180, 90, 160, 30);
        b1.addActionListener(this);

        op = new JLabel("Results:");
        f.add(op);
        op.setBounds(50, 120, 100, 20);

        model = new DefaultTableModel();
        table = new JTable(model);
        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setBounds(50, 140, 500, 200);
        f.add(scrollPane);
    }

    public void actionPerformed(ActionEvent e) {
        try {
            Class.forName("oracle.jdbc.driver.OracleDriver");

            Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "system", "system");

            Statement stmt = con.createStatement();

            String query = t1.getText();
            ResultSet rs = stmt.executeQuery(query);

            ResultSetMetaData rsmd = rs.getMetaData();
            int columnCount = rsmd.getColumnCount();

            // Clear the table model
            model.setRowCount(0);
            model.setColumnCount(0);

            // Add column headers to the table model
            for (int i = 1; i <= columnCount; i++) {
                model.addColumn(rsmd.getColumnName(i));
            }

            // Add data rows to the table model
            while (rs.next()) {
                Object[] row = new Object[columnCount];
                for (int i = 1; i <= columnCount; i++) {
                    row[i - 1] = rs.getObject(i);
                }
                model.addRow(row);
            }

            con.close();
        } catch (Exception ex) {
            System.out.println(ex);
        }
    }
}
