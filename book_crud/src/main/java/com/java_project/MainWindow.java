package com.java_project;

import javax.swing.*;

import java.awt.*;

public class MainWindow extends JFrame {
    final int WIDTH = 800;
    final int HEIGHT = 500;
    private JPanel resultsPanel;

    public MainWindow () {
        super("Book CRUD");
        setSize(WIDTH, HEIGHT);

        // Initialize the list to hold result labels for future access

        // Left Panel with search fields
        JPanel leftPanel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5); // Adds padding between components

        // Title label
        JLabel title = new JLabel("Welcome to Genesis Lib!");
        title.setFont(new Font("Arial", Font.BOLD, 24));
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        leftPanel.add(title, gbc);
        
        // "Search by:" label
        JLabel searchByLabel = new JLabel("Search by:");
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 1;
        gbc.anchor = GridBagConstraints.WEST;
        leftPanel.add(searchByLabel, gbc);

        // Radio buttons for search type
        JRadioButton searchByTitle = new JRadioButton("By Title");
        JRadioButton searchByISBN = new JRadioButton("By ISBN");
        JRadioButton searchByAuthor = new JRadioButton("By Author");

        ButtonGroup searchGroup = new ButtonGroup();
        searchGroup.add(searchByTitle);
        searchGroup.add(searchByISBN);
        searchGroup.add(searchByAuthor);

        JPanel radioPanel = new JPanel();
        radioPanel.add(searchByTitle);
        radioPanel.add(searchByISBN);
        radioPanel.add(searchByAuthor);

        gbc.gridx = 1;
        gbc.gridy = 1;
        leftPanel.add(radioPanel, gbc);


        
        // Title search field
        JLabel titleLabel = new JLabel("Title:");
        gbc.gridx = 0;
        gbc.gridy = 2;
        leftPanel.add(titleLabel, gbc);
        
        
        JTextField titleField = new JTextField(20);
        gbc.gridx = 1;
        gbc.gridy = 2;
        leftPanel.add(titleField, gbc);
        

        // ISBN search field
        JLabel isbnLabel = new JLabel("ISBN:");
        gbc.gridx = 0;
        gbc.gridy = 3;
        leftPanel.add(isbnLabel, gbc);

        JTextField isbnField = new JTextField(20);
        gbc.gridx = 1;
        gbc.gridy = 3;
        leftPanel.add(isbnField, gbc);

        // Author search field
        JLabel authorLabel = new JLabel("Author:");
        gbc.gridx = 0;
        gbc.gridy = 4;
        leftPanel.add(authorLabel, gbc);

        JTextField authorField = new JTextField(20);
        gbc.gridx = 1;
        gbc.gridy = 4;
        leftPanel.add(authorField, gbc);

        // Insert button
        JButton insertButton = new JButton("Insert");
        gbc.gridx = 0;
        gbc.gridy = 5;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        leftPanel.add(insertButton, gbc);

        // Right Panel for query results
        resultsPanel = new JPanel();
        resultsPanel.setSize(WIDTH/2, HEIGHT - 200);
        resultsPanel.setLayout(new BoxLayout(resultsPanel, BoxLayout.Y_AXIS));
        
        // Making resultsPanel scrollable
        JScrollPane scrollPane = new JScrollPane(resultsPanel);
        scrollPane.setSize(new Dimension(WIDTH/2, HEIGHT - 200));

        // Split Pane to separate left and right panels
        JSplitPane splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, leftPanel, scrollPane);
        splitPane.setDividerLocation(WIDTH/2);
        splitPane.setResizeWeight(0.7);
        splitPane.setSize(WIDTH/2, HEIGHT - 200);

        // Add the split pane to the main frame
        add(splitPane);

        // Frame settings
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null); // Center the window
        setVisible(true);
    }
}
