import javax.swing.*;

import java.awt.event.*;


public class DatabaseGUI implements ActionListener {
    JFrame f;
    JButton b1, b2, b3, b4;

    public DatabaseGUI() {

        f = new JFrame();
        f.setVisible(true);
        f.setSize(450, 400);
        f.setLayout(null);
        b1 = new JButton("Create table");
        b2 = new JButton("Insert Value");
        b3 = new JButton("Delete Value");
        b4 = new JButton("Select Value");
        f.add(b1);
        b1.setBounds(50, 200, 130, 20);
        b1.addActionListener(this);
        f.add(b2);
        b2.setBounds(240, 200, 130, 20);
        b2.addActionListener(this);
        f.add(b3);
        b3.setBounds(50, 250, 130, 20);
        b3.addActionListener(this);
        f.add(b4);
        b4.setBounds(240, 250, 130, 20);
        b4.addActionListener(this);
    }

    public void actionPerformed(ActionEvent ex) {
            if(ex.getSource() == b1)
            {
                new CreateTable();
            }
            else if(ex.getSource() == b2)
            {
                new InsertData();
            }
            else if(ex.getSource() == b3)
            {
                new DeleteData();
            }
            else 
            {
                new SelectData();
            }
        }
    

    public static void main(String ad[]) {
         new DatabaseGUI();
    }
}
