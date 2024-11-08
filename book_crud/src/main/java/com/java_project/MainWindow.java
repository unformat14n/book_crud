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
        setResizable(false);

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

        JComboBox<String> searchBy = new JComboBox<String>();
        searchBy.addItem("Title");
        searchBy.addItem("Author");
        searchBy.addItem("ISBN");
        searchBy.addItem("Publisher");
        searchBy.addItem("Genre");
        gbc.gridx = 0;
        gbc.gridy = 2;
        leftPanel.add(searchBy, gbc);

        JTextField option = new JTextField(10);
        gbc.gridx = 1;
        gbc.gridy = 2;
        leftPanel.add(option, gbc);
        
        // Insert button
        JButton find = new JButton("Find");
        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        leftPanel.add(find, gbc);

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
