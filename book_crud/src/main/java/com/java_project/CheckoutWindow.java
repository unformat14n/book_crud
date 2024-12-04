package com.java_project;

import com.dieselpoint.norm.Database;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class CheckoutWindow extends JFrame {
    
    private JPanel mainPanel;
    private GridBagConstraints gbc;
    private JTextField t1, t2, t3;
    // private String copyRef;
    private BookCopy copyB;

    private Database db;
    public boolean isOpen = true;

    public CheckoutWindow(Database db) {
        super("Logging...");
        setResizable(false);
        setSize(400, 500);
        this.db = db;

        initialize();
    }

    public void setOpen(boolean b) {
        isOpen = b;
    }

    public void initialize() {
        mainPanel = new JPanel(new GridBagLayout());
        gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5); // Adds padding between components
        
        JLabel date = new JLabel("Checkout Date: ");
        gbc.gridx = 0;
        gbc.gridy = 1;
        mainPanel.add(date, gbc);

        t1 = new JTextField(20);
        gbc.gridx = 1;
        gbc.gridy = 1;
        mainPanel.add(t1, gbc);

        JLabel client = new JLabel("Client ID: ");
        gbc.gridx = 0;
        gbc.gridy = 2;
        mainPanel.add(client, gbc);

        t2 = new JTextField(20);
        gbc.gridx = 1;
        gbc.gridy = 2;
        mainPanel.add(t2, gbc);

        JLabel copy = new JLabel("Copy ID: ");
        gbc.gridx = 0;
        gbc.gridy = 3;
        mainPanel.add(copy, gbc);

        t3 = new JTextField(20);
        gbc.gridx = 1;
        gbc.gridy = 3;
        mainPanel.add(t3, gbc);

        JButton submit = new JButton("Submit");
        gbc.gridx = 0;
        gbc.gridy = 4;
        gbc.anchor = GridBagConstraints.WEST;
        mainPanel.add(submit, gbc);

        JButton closeBtn = new JButton("Close");
        gbc.gridx = 1;
        gbc.gridy = 4;
        gbc.anchor = GridBagConstraints.EAST;
        mainPanel.add(closeBtn, gbc);
        closeBtn.addActionListener(e -> {
            dispose();
        });

        submit.addActionListener(e -> {
            String dateText = t1.getText();
            SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
            dateFormat.setLenient(false);

            try {
                if (!t1.getText().equals("")) {
                    dateFormat.parse(dateText);
                    System.out.println("Parsed date: " + t1.getText());
                }

                JTextField[] fields = { t2, t3};
                for (JTextField field : fields) {
                    if (field.getText().equals("")) {
                        throw new Exception("Empty fields");
                    }
                }

                List<BookCopy> matches = db.where("copyId LIKE ?", t3.getText().toUpperCase()).results(BookCopy.class);

                if (matches.isEmpty()) {
                    throw new Exception("Match not found");
                } else {
                    copyB = matches.get(0);
                }

                insertToDB();

            } catch (ParseException ex) {
                JOptionPane.showMessageDialog(
                        this,
                        "Invalid date format. Please enter in MM/dd/yyyy format.",
                        "Invalid Date Format",
                        JOptionPane.ERROR_MESSAGE);
            } catch (Exception ex) {
                if (ex.getMessage().contains("Empty")) {

                    System.out.println(ex);
                    JOptionPane.showMessageDialog(
                            this,
                            "Please fill in all mandatory fields.",
                            "Missing Fields",
                            JOptionPane.ERROR_MESSAGE);
                } else {
                    System.out.println(ex);
                    JOptionPane.showMessageDialog(
                            this,
                            "The copy ID does not match any book copy. Please make sure everything is correct.",
                            "Missing Fields",
                            JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        add(mainPanel);
    }

    public void insertToDB() {
        Checkout ch = new Checkout(
                t1.getText().toUpperCase(),
                t2.getText().toUpperCase(),
                t3.getText());
        ch.copy = copyB;
        System.out.println(copyB);
        db.insert(ch);
        dispose();
    }

}
