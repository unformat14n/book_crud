package com.java_project;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import com.java_project.MainWindow;
import com.dieselpoint.norm.Database;
/**
 * Hello world!
 *
 */
public class Main {
    private static final String ROOT_DIR = System.getProperty("user.home"); // Or root path as needed
    private static final String FOLDER_NAME = ".book_crud";
    private static final String DB_FILE_NAME = "bcrud.db";

    public static Database setupDatabase() {
        try {
            // Define path for hidden folder and database file
            Path folderPath = Paths.get(ROOT_DIR, FOLDER_NAME);
            Path dbFilePath = Paths.get(folderPath.toString(), DB_FILE_NAME);

            // Check and create the folder if it doesn't exist
            if (!Files.exists(folderPath)) {
                Files.createDirectory(folderPath);
                System.out.println("Created directory: " + folderPath);
            }

            // Check if database file exists, if not, create it
            File dbFile = new File(dbFilePath.toString());
            if (!dbFile.exists()) {
                dbFile.createNewFile();
                System.out.println("Created new database file: " + dbFilePath);
            }

            // Set up Norm database connection
            Database db = new Database();
            db.setJdbcUrl("jdbc:sqlite:" + dbFilePath.toString());
            System.out.println(" >>> Database set up correctly!");
            return db;

        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Error setting up database", e);
        }
    }

    public static void main(String[] args) {
        Database db = setupDatabase();
        MainWindow win = new MainWindow();
    }
}