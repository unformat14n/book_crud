package com.java_project;

import com.dieselpoint.norm.Database;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;

import java.util.List;

// import com.java_project.Author;

public class AllBooksWindow extends JFrame {


    private Database db;

    public AllBooksWindow(Database db) {
        super("All Books");
        this.db = db;
        setSize(400, 500);
        setResizable(false);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        initialize();
    }

    public void initialize() {
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
        
        List<BookInfo> books = db.results(BookInfo.class);

        if (books.isEmpty()){
            JLabel inexistentBook = new JLabel("No books found.");
            inexistentBook.setAlignmentX(Component.CENTER_ALIGNMENT);
            mainPanel.add(inexistentBook);
        } else{
            for (BookInfo book : books){
                JPanel bookPanel = new JPanel();
                bookPanel.setLayout(new BoxLayout(bookPanel, BoxLayout.Y_AXIS));
                bookPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

                JLabel title = new JLabel("Title: " + book.title);
                JLabel author = new JLabel("Author: " + book.author);
                JLabel isbn = new JLabel("ISBN: " + book.isbn);
                JLabel genre = new JLabel("Genre: " + book.genre);
                JLabel publisher = new JLabel("Publisher: " + book.publisher);

                bookPanel.add(title);
                bookPanel.add(author);
                bookPanel.add(isbn);
                bookPanel.add(genre);
                bookPanel.add(publisher);

                bookPanel.setAlignmentX(Component.LEFT_ALIGNMENT);
                mainPanel.add(bookPanel);

                mainPanel.add(Box.createRigidArea(new Dimension(0, 10)));

            }
        }

        JScrollPane scrollPane = new JScrollPane(mainPanel);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        add(scrollPane);

        }
    }

