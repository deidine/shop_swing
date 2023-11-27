/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.inventory.Database;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;
import org.apache.commons.io.FileUtils;

/**
 *
 * @author asjad
 */
//Class to retrieve connection for database and login verfication.
public final class ConnectionFactory {

    public String readTxtFile(int number) throws IOException {
        String line = null;
        File file = new File("resources/info/databaseConnection.txt");

        String line2 = FileUtils.readLines(file).get(number);
         return line2;
    }

    static final String driver = "com.mysql.cj.jdbc.Driver";
//    static final String url = "jdbc:mysql://localhost:3306/"+readTxtFile(0);
    static String username;
    static String password;

    Properties prop;

    Connection conn = null;
    Statement statement = null;
    ResultSet resultSet = null;

    public ConnectionFactory() {
        // try {
        //     //Username and Password saved as configurable properties to allow changes without recompilation.
        //     prop = new Properties();
        //     prop.loadFromXML(new FileInputStream("lib/DBCredentials.xml"));
        // } catch (IOException e) {
        //     e.printStackTrace();
        // }
        // username = prop.getProperty("username");
        // password = prop.getProperty("password");

        try {
            Class.forName(driver);
            conn = DriverManager.getConnection("jdbc:mysql://" + readTxtFile(1) + ":" + readTxtFile(4) + "/" + readTxtFile(0),
                    readTxtFile(2),
                    readTxtFile(3));
            statement = conn.createStatement();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public Connection getConn() {
        try {
            Class.forName(driver);
            conn = DriverManager.getConnection("jdbc:mysql://" + readTxtFile(1) + ":" + readTxtFile(4) + "/" + readTxtFile(0),
                    readTxtFile(2),
                    readTxtFile(3));

        } catch (Exception e) {
            e.printStackTrace();
        }
        return conn;
    }

    public boolean checkConn() {
        getConn();
            
        System.out.println("Server connected!");
        Statement stmt = null;
        ResultSet resultset = null;

        try {

            resultset = statement.executeQuery("SHOW DATABASES;");
            if (resultset.next()) {
                System.out.println(resultset.getString("Database"));
                return true;
            }

        } catch (Exception ex) {
            // handle any errors
            ex.printStackTrace();
        }

        return false;
    }

    //Login verification method
    public boolean checkLogin(String username, String password, String userType) {
        String query = "SELECT * FROM users WHERE username='"
                + username
                + "' AND password='"
                + password
                + "' AND usertype='"
                + userType
                + "' LIMIT 1";

        try {
            resultSet = statement.executeQuery(query);
            if (resultSet.next()) {
                return true;
            }
            System.out.println("Connected successfully.");

        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return false;
    }
}
