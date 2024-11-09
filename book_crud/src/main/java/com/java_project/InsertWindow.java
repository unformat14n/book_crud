package com.java_project;
import javax.swing.*;
import java.awt.*;


public class InsertWindow extends JFrame {
    private String winType;
    private JPanel mainPanel;
    private GridBagConstraints gbc;
    private JTextField t1, t2, t3, t4, t5;
    public boolean isOpen = true;

    public InsertWindow(String type) {
        super("Book CRUD");
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
        JLabel title = new JLabel("Title");
        gbc.gridx = 0;
        gbc.gridy = 0;
        mainPanel.add(title, gbc);

        t1 = new JTextField(20);
        gbc.gridx = 1;
        gbc.gridy = 0;
        mainPanel.add(t1, gbc);

        JLabel isbn = new JLabel("ISBN");
        gbc.gridx = 0;
        gbc.gridy = 1;
        mainPanel.add(isbn, gbc);

        t2 = new JTextField(20);
        gbc.gridx = 1;
        gbc.gridy = 1;
        mainPanel.add(t2, gbc);

        JLabel author = new JLabel("Author");
        gbc.gridx = 0;
        gbc.gridy = 2;
        mainPanel.add(author, gbc);

        t3 = new JTextField(20);
        gbc.gridx = 1;
        gbc.gridy = 2;
        mainPanel.add(t3, gbc);

        JLabel publisher = new JLabel("Publisher");
        gbc.gridx = 0;
        gbc.gridy = 3;
        mainPanel.add(publisher, gbc);

        t4 = new JTextField(20);
        gbc.gridx = 1;
        gbc.gridy = 3;
        mainPanel.add(t4, gbc);

        JLabel genre = new JLabel("Genre");
        gbc.gridx = 0;
        gbc.gridy = 4;
        mainPanel.add(genre, gbc);

        t5 = new JTextField(20);
        gbc.gridx = 1;
        gbc.gridy = 4;
        mainPanel.add(t5, gbc);
        
        add(mainPanel);
    }
}
