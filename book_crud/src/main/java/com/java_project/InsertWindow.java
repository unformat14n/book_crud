package com.java_project;
import javax.swing.*;
import java.awt.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.dieselpoint.norm.Database;

public class InsertWindow extends JFrame {
    private String winType;
    private JPanel mainPanel;
    private GridBagConstraints gbc;
    private JTextField t1, t2, t3, t4, t5, t6, t7, t8;
    public boolean isOpen = true;

    public InsertWindow(String type, Database db) {
        super("Logging...");
        setSize(WIDTH, HEIGHT);
        setResizable(false);
        setSize(400, 500);
        winType = type;

        initialize();
    }

    public void setOpen(boolean b) {
        isOpen = b;
    }

    public void initialize() {
        mainPanel = new JPanel(new GridBagLayout());
        gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5); // Adds padding between components
        System.out.println(winType);
        if(winType == "New Book") {
            newBookWin();
        }
    }

    public void newBookWin() {
        JLabel man = new JLabel("Mandatory Fields:");
        Font f = new Font("Arial", Font.BOLD, 20);
        man.setFont(f);
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        mainPanel.add(man, gbc);
        gbc.gridwidth = 1;

        JLabel title = new JLabel("Title");
        gbc.gridx = 0;
        gbc.gridy = 1;
        mainPanel.add(title, gbc);

        t1 = new JTextField(20);
        gbc.gridx = 1;
        gbc.gridy = 1;
        mainPanel.add(t1, gbc);

        JLabel isbn = new JLabel("ISBN");
        gbc.gridx = 0;
        gbc.gridy = 2;
        mainPanel.add(isbn, gbc);

        t2 = new JTextField(20);
        gbc.gridx = 1;
        gbc.gridy = 2;
        mainPanel.add(t2, gbc);

        JLabel author = new JLabel("Author");
        gbc.gridx = 0;
        gbc.gridy = 3;
        mainPanel.add(author, gbc);

        t3 = new JTextField(20);
        gbc.gridx = 1;
        gbc.gridy = 3;
        mainPanel.add(t3, gbc);

        JLabel publisher = new JLabel("Publisher");
        gbc.gridx = 0;
        gbc.gridy = 4;
        mainPanel.add(publisher, gbc);

        t4 = new JTextField(20);
        gbc.gridx = 1;
        gbc.gridy = 4;
        mainPanel.add(t4, gbc);

        JLabel genre = new JLabel("Genre");
        gbc.gridx = 0;
        gbc.gridy = 5;
        mainPanel.add(genre, gbc);

        t5 = new JTextField(20);
        gbc.gridx = 1;
        gbc.gridy = 5;
        mainPanel.add(t5, gbc);

        JLabel opt = new JLabel("Optional Fields:");
        f = new Font("Arial", Font.BOLD, 18);
        opt.setFont(f);
        gbc.gridx = 0;
        gbc.gridy = 6;
        gbc.gridwidth = 2;
        mainPanel.add(opt, gbc);

        JLabel ed = new JLabel("Edition");
        gbc.gridwidth = 1;
        gbc.gridx = 0;
        gbc.gridy = 7;
        mainPanel.add(ed, gbc);

        t6 = new JTextField(20);
        gbc.gridx = 1;
        gbc.gridy = 7;
        mainPanel.add(t5, gbc);

        JLabel desc = new JLabel("Description");
        gbc.gridx = 0;
        gbc.gridy = 8;
        mainPanel.add(desc, gbc);

        t7 = new JTextField(20);
        gbc.gridx = 1;
        gbc.gridy = 8;
        mainPanel.add(t7, gbc);

        JLabel pubDate = new JLabel("Published on");
        gbc.gridx = 0;
        gbc.gridy = 9;
        mainPanel.add(pubDate, gbc);

        t8 = new JTextField(20);
        gbc.gridx = 1;
        gbc.gridy = 9;
        mainPanel.add(t8, gbc);

        JButton submit = new JButton("Submit");
        gbc.gridx = 0;
        gbc.gridy = 10;
        gbc.anchor = GridBagConstraints.WEST;
        mainPanel.add(submit, gbc);
        submit.addActionListener(e -> {
            String dateText = t8.getText();
            SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
            dateFormat.setLenient(false);

            try {
                Date date = dateFormat.parse(dateText);
                System.out.println("Parsed date: " + date);
            } catch (ParseException ex) {
                JOptionPane.showMessageDialog(
                    this, 
                    "Invalid date format. Please enter in MM/dd/yyyy format.", 
                    "Invalid Date Format", 
                    JOptionPane.ERROR_MESSAGE
                );
            }
        });
        
        add(mainPanel);
    }
}
