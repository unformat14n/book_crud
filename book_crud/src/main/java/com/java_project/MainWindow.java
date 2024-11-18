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
import java.util.HashMap;
import java.util.Map;
import java.util.ArrayList;

public class MainWindow extends JFrame {

    final int WIDTH = 800;
    final int HEIGHT = 500;
    private JPanel resultsPanel;
    private InsertWindow inWin;
    private boolean inWinOpened = false;
    public Database db;
    public JTextField option;
    public JPanel checksPanel;

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

        option = new JTextField(15);
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

        // JLabel checks = new JLabel("Active Check Outs:");
        // checks.setFont(new Font("Arial", Font.BOLD, 18));
        // gbc.gridx = 0;
        // gbc.gridy = 5;
        // gbc.anchor = GridBagConstraints.WEST;
        // leftPanel.add(checks, gbc);

        // checksPanel = new JPanel();
        // checksPanel.setPreferredSize(new Dimension());
        // checksPanel.setLayout(new BoxLayout(checksPanel, BoxLayout.Y_AXIS));
        // JScrollPane checksScroll = new JScrollPane(checksPanel);
        // gbc.gridx = 0;
        // gbc.gridy = 6;
        // leftPanel.add(checksScroll, gbc);

        // Right Panel for query results
        resultsPanel = new JPanel();
        resultsPanel.setLayout(new BoxLayout(resultsPanel, BoxLayout.Y_AXIS)); // Vertical stacking

        // Making resultsPanel scrollable
        JScrollPane scrollPane = new JScrollPane(resultsPanel);

        JSplitPane splitPane = new JSplitPane(
                JSplitPane.HORIZONTAL_SPLIT,
                leftPanel,
                scrollPane);

        splitPane.setDividerLocation(WIDTH / 2);
        splitPane.setResizeWeight(0.7);
        splitPane.setEnabled(false);

        // Add the split pane to the main frame
        add(splitPane);

        // Frame settings
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null); // Center the window
        setVisible(true);
    }

    public void createInsertWin(String type) {
        if (!inWinOpened) {
            inWin = new InsertWindow(type, this.db);
            inWin.setVisible(true);
            inWin.setOpen(true);
            this.inWinOpened = true;
            inWin.addWindowListener(
                    new WindowAdapter() {
                        @Override
                        public void windowDeactivated(WindowEvent e) {
                            if (!inWin.isShowing()) {
                                inWinOpened = false;
                                findInDB("Title");
                            }
                        }
                    });
        } else {
            inWin.toFront();
        }
    }

    public void findInDB(String type) {
        resultsPanel.removeAll();
        resultsPanel.revalidate();
        resultsPanel.repaint();

        // Fetch BookInfo objects based on the search criteria
        List<BookInfo> books = db.where(type + " LIKE ?", "%" + option.getText().toUpperCase() + "%")
                .results(BookInfo.class);

        if (books.isEmpty()) {
            JLabel noResults = new JLabel(
                    "No copies were found with *" + type + "* like [" +
                            option.getText().toLowerCase() + "]");
            resultsPanel.add(noResults);
        } else {
            // Map ISBNs to their respective BookInfo objects
            Map<String, BookInfo> bookMap = new HashMap<>();
            for (BookInfo book : books) {
                bookMap.put(book.isbn, book);
            }

            // Fetch BookCopy objects and link them to BookInfo
            List<BookCopy> copies = new ArrayList<>();
            for (String isbn : bookMap.keySet()) {
                List<BookCopy> matches = db.where("bookISBN = ?", isbn).results(BookCopy.class);
                for (BookCopy copy : matches) {
                    copy.book = bookMap.get(isbn); // Link the BookInfo object
                    copies.add(copy);
                }
            }

            // Display BookCopy details with linked BookInfo
            for (BookCopy copy : copies) {
                JPanel bookInfoPanel = new JPanel();
                bookInfoPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
                bookInfoPanel.setPreferredSize(new Dimension(resultsPanel.getWidth() - 50, 120));

                Field[] fields = copy.getClass().getDeclaredFields();
                for (Field fld : fields) {
                    fld.setAccessible(true);
                    try {
                        Object value = fld.get(copy);
                        if (value != null && !value.toString().isEmpty() &&
                                !fld.getName().equals("book")) {
                            JLabel fieldLabel = new JLabel(fld.getName() + ": " + value.toString());
                            bookInfoPanel.add(fieldLabel);
                        }
                    } catch (IllegalAccessException e) {
                        e.printStackTrace();
                    }
                }

                // Add details from BookInfo
                if (copy.book != null) {
                    JLabel bookTitle = new JLabel("Title: " + copy.book.title);
                    JLabel bookAuthor = new JLabel(
                            "Author: " + copy.book.author);
                    JLabel isbn = new JLabel(
                            "ISBN: " + copy.book.isbn);
                    JLabel genre = new JLabel(
                            "Genre: " + copy.book.genre);
                    JLabel publisher = new JLabel(
                            "Publisher: " + copy.book.publisher);
                    bookInfoPanel.add(bookTitle);
                    bookInfoPanel.add(bookAuthor);
                    bookInfoPanel.add(isbn);
                    bookInfoPanel.add(publisher);
                    bookInfoPanel.add(genre);
                }

                JButton del = new JButton("DELETE");
                bookInfoPanel.add(del);
                System.out.println(copy.copyId + " " + copy.book.isbn);
                del.addActionListener(e -> {
                    db.sql(
                            "DELETE FROM BookCopy WHERE copyId = ? AND bookISBN = ?;",
                            copy.copyId, copy.book.isbn).execute();
                    findInDB(type);
                });

                resultsPanel.add(bookInfoPanel);
            }
            resultsPanel.revalidate();
            resultsPanel.repaint();
        }
    }
}