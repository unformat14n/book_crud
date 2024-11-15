package com.java_project;

import com.dieselpoint.norm.Database;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.*;
import java.lang.reflect.Field;
import java.util.List;

public class MainWindow extends JFrame {

    final int WIDTH = 800;
    final int HEIGHT = 500;
    private JPanel resultsPanel;
    private InsertWindow inWin;
    private boolean inWinOpened = false;
    public Database db;
    public JTextField option;

    public MainWindow(Database db) {

        super("Book CRUD");
        this.db = db;
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

        option = new JTextField(10);
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
        find.addActionListener(
                new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        findInDB((String) searchBy.getSelectedItem());
                    }
                });

        JComboBox<String> insertType = new JComboBox<String>();
        insertType.addItem("Book");
        insertType.addItem("Copy");
        insertType.addItem("Author");
        insertType.addItem("Publisher");
        gbc.gridx = 0;
        gbc.gridy = 4;
        gbc.anchor = GridBagConstraints.WEST;
        leftPanel.add(insertType, gbc);

        JButton insert = new JButton("Log Record");
        gbc.gridx = 1;
        gbc.gridy = 4;
        gbc.anchor = GridBagConstraints.EAST;
        leftPanel.add(insert, gbc);

        insert.addActionListener(
                new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        createInsertWin((String) insertType.getSelectedItem());
                    }
                });

        // Right Panel for query results
        resultsPanel = new JPanel();
        resultsPanel.setSize(WIDTH / 2, HEIGHT - 200);
        resultsPanel.setLayout(new BoxLayout(resultsPanel, BoxLayout.Y_AXIS));

        // Making resultsPanel scrollable
        JScrollPane scrollPane = new JScrollPane(resultsPanel);
        scrollPane.setSize(new Dimension(WIDTH / 2, HEIGHT - 200));

        // Split Pane to separate left and right panels
        JSplitPane splitPane = new JSplitPane(
                JSplitPane.HORIZONTAL_SPLIT,
                leftPanel,
                scrollPane);

        splitPane.setDividerLocation(WIDTH / 2);
        splitPane.setResizeWeight(0.7);
        splitPane.setSize(WIDTH / 2, HEIGHT - 200);
        splitPane.setEnabled(false);

        // Add the split pane to the main frame
        add(splitPane);

        // Frame settings
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null); // Center the window
        setVisible(true);
    }

    public void createInsertWin(String type) {
        if (!this.inWinOpened) {
            inWin = new InsertWindow(type, this.db);
            inWin.setVisible(true);
            inWin.setOpen(true);
            this.inWinOpened = true;
            inWin.addWindowListener(
                    new WindowAdapter() {
                        @Override
                        public void windowClosing(WindowEvent e) {
                            inWinOpened = false; // Reset the flag when inWin is closing
                        }
                    });
        } else {
            inWin.toFront();
        }
    }

    public void findInDB(String type) {

        System.out.println(type + ", " + option.getText());
        // resultsPanel.add(insertPanel);
        resultsPanel.removeAll();

        List<BookInfo> books = db.where(type + " LIKE ?", "%" + option.getText().toLowerCase() + "%")
                .results(BookInfo.class);

        if (books.isEmpty()) {
            // JLabel que diga not found o algo asi
        } else {
            for (BookInfo b : books) {
                JPanel bookInfo = new JPanel();
                Field[] fields = b.getClass().getDeclaredFields();
                for (Field fld : fields) {
                    fld.setAccessible(true);
                    try {
                        // Get the value of the field
                        Object value = fld.get(b);
                        String name = fld.getName();
                        // value.toSrting() should get the value of the field
                        // Check if the value is not null or empty
                        if (value != null && !value.toString().isEmpty()) {
                            // Create a JLabel to display the field name and value
                        }
                    } catch (IllegalAccessException e) {
                        e.printStackTrace();
                    }

                }
                resultsPanel.add(bookInfo);
            }
            resultsPanel.revalidate();
            resultsPanel.repaint();
        }
    }
}