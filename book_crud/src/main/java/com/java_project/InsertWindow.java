package com.java_project;

import com.dieselpoint.norm.Database;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import java.util.List;

// import com.java_project.Author;

public class InsertWindow extends JFrame {

    private String winType;
    private JPanel mainPanel;
    private GridBagConstraints gbc;
    private JTextField t1, t2, t3, t4, t6, t7, t8;
    private JComboBox<String> t5;
    private InsertWindow auxWin;
    private boolean auxWinOpen;
    private String bookRef;

    private String date;
    public boolean isOpen = true;

    private Database db;

    public InsertWindow(String type, Database db) {
        super("Logging...");
        setSize(WIDTH, HEIGHT);
        setResizable(false);
        setSize(400, 500);
        winType = type;
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
        System.out.println(winType);
        if (winType == "Book") {
            newBookWin();
        } else if (winType == "Copy") {
            newCopyWin();
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

        t5 = new JComboBox<String>();
        t5.addItem("ACTION ADVENTURE");
        t5.addItem("CHILDREN");
        t5.addItem("DYSTOPIAN");
        t5.addItem("FANTASY");
        t5.addItem("GRAPHIC_NOVEL");
        t5.addItem("HISTORICAL_FICTION");
        t5.addItem("HORROR");
        t5.addItem("MYSTERY");
        t5.addItem("THRILLER_SUSPENSE");
        t5.addItem("NON_FICTION");
        t5.addItem("ROMANCE");
        t5.addItem("SCIENCE_FICTION");
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
        mainPanel.add(t6, gbc);

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

        JButton closeBtn = new JButton("Close");
        gbc.gridx = 1;
        gbc.gridy = 10;
        gbc.anchor = GridBagConstraints.EAST;
        mainPanel.add(closeBtn, gbc);
        closeBtn.addActionListener(e -> {
            dispose();
        });

        submit.addActionListener(e -> {
            String dateText = t8.getText();
            SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
            dateFormat.setLenient(false);

            try {
                if (!t8.getText().equals("")) {
                    dateFormat.parse(dateText);
                    date = dateText;
                    System.out.println("Parsed date: " + date);
                }

                JTextField[] fields = { t1, t2, t3, t4 };
                for (JTextField field : fields) {
                    System.out.println(
                            field.getText() + "-" + field.getText().equals(""));
                    if (field.getText().equals("")) {
                        throw new Exception("Empty fields");
                    }
                }

                insertToDB();
            } catch (ParseException ex) {
                JOptionPane.showMessageDialog(
                        this,
                        "Invalid date format. Please enter in MM/dd/yyyy format.",
                        "Invalid Date Format",
                        JOptionPane.ERROR_MESSAGE);
            } catch (Exception ex) {
                System.out.println(ex);
                JOptionPane.showMessageDialog(
                        this,
                        "Please fill in all mandatory fields.",
                        "Missing Fields",
                        JOptionPane.ERROR_MESSAGE);
            }
        });

        add(mainPanel);
    }

    public void newCopyWin() {
        JLabel bookISBN = new JLabel("Book ISBN reference");
        gbc.gridx = 0;
        gbc.gridy = 0;
        mainPanel.add(bookISBN, gbc);

        t1 = new JTextField(20);
        gbc.gridx = 1;
        gbc.gridy = 0;
        mainPanel.add(t1, gbc);

        JLabel acqDate = new JLabel("Date Acquired");
        gbc.gridx = 0;
        gbc.gridy = 1;
        mainPanel.add(acqDate, gbc);

        t2 = new JTextField(20);
        gbc.gridx = 1;
        gbc.gridy = 1;
        mainPanel.add(t2, gbc);

        JLabel acqType = new JLabel("Acquired by");
        gbc.gridx = 0;
        gbc.gridy = 2;
        mainPanel.add(acqType, gbc);

        t5 = new JComboBox<String>();
        t5.addItem("DONATED");
        t5.addItem("GIVEN");
        t5.addItem("BOUGHT");
        gbc.gridx = 1;
        gbc.gridy = 2;
        mainPanel.add(t5, gbc);

        JButton submit = new JButton("Submit");
        gbc.gridx = 0;
        gbc.gridy = 10;
        gbc.anchor = GridBagConstraints.WEST;
        mainPanel.add(submit, gbc);

        JButton closeBtn = new JButton("Close");
        gbc.gridx = 1;
        gbc.gridy = 10;
        gbc.anchor = GridBagConstraints.EAST;
        mainPanel.add(closeBtn, gbc);
        closeBtn.addActionListener(e -> {
            dispose();
        });

        submit.addActionListener(e -> {
            String dateText = t2.getText();
            SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
            dateFormat.setLenient(false);

            try {
                if (!t2.getText().equals("")) {
                    dateFormat.parse(dateText);
                    date = dateText;
                    System.out.println("Parsed date: " + date);
                }

                JTextField[] fields = { t1, t2 };
                for (JTextField field : fields) {
                    System.out.println(
                            field.getText() + "-" + field.getText().equals(""));
                    if (field.getText().equals("")) {
                        throw new Exception("Empty fields");
                    }
                }

                List<BookInfo> matches = db.where("isbn = ?", t1.getText()).results(BookInfo.class);

                if (matches.isEmpty()) {
                    throw new Exception("Match not found");
                } else {
                    bookRef = matches.get(0).isbn;
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
                            "Book ISBN code could not be resolved to a book",
                            "Book ISBN not valid",
                            JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        add(mainPanel);
    }

    public void insertToDB() {
        if (winType == "Book") {
            String genre = (String) t5.getSelectedItem();
            int edition = 0;
            if (!t6.getText().equals("")) {
                edition = Integer.parseInt(t6.getText());
            }
            BookInfo book = new BookInfo(
                    t2.getText().toLowerCase(),
                    t1.getText().toLowerCase(),
                    t3.getText().toLowerCase(),
                    edition,
                    t7.getText().toLowerCase(),
                    genre,
                    date,
                    t4.getText().toLowerCase());
            db.insert(book);
        } else if (winType == "Copy") {
            String acqType = (String) t5.getSelectedItem();
            BookCopy copy = new BookCopy(
                    t2.getText(),
                    acqType,
                    bookRef);
            db.insert(copy);
        }
        dispose();
        System.exit(0);
    }
}
