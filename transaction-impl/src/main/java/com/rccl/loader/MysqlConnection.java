package com.rccl.loader;

import java.io.BufferedReader;
import java.io.FileReader;
import java.sql.DriverManager;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.StringTokenizer;

public class MysqlConnection {
    
    private static final String FILE_PATH = "/home/abhishek/fullstack-crud/transaction-impl/src/main/mockdata/txn.csv";
    
    MysqlConnection() {
        
        System.out.println("-------- MYSQL JDBC Connection Testing ------------");
        addtxnsData();
        System.out.println("PostgreSQL JDBC Driver Registered!");
        
    }
    
    private static void addtxnsData() {
        
        Connection connection = null;
        Statement stmt = null;
        
        try {
            
            Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager.getConnection(
                    "jdbc:mysql://localhost/txns", "root",
                    "root");
        } catch (Exception e) {
            
            System.out.println("Connection Failed! Check output console");
            e.printStackTrace();
        }
        
        
        try {
            
            String fileName = FILE_PATH;
            BufferedReader br = new BufferedReader(new FileReader(fileName));
            String strLine = null;
            StringTokenizer st = null;
            int lineNumber = 0;
            
            while ((fileName = br.readLine()) != null) {
                lineNumber++;
                
                //break comma separated line using ","
                st = new StringTokenizer(fileName, ",");
                
                while (st.hasMoreTokens()) {
                    //display csv values
                    Statement statement = null;
                    try {
                        statement = connection.createStatement();
                        String userID = st.nextToken();
                        String productID = st.nextToken();
                        String qunatity = st.nextToken();
                        String theStatement = "INSERT INTO  txn VALUES(" + userID + ", " + productID + ", " + qunatity + ")";

//						System.out.println(theStatement);
                        statement.executeUpdate(theStatement);
                    } catch (Exception e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }
                }
            }
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        
        
        if (connection != null) {
            System.out.println("You made it, take control your database now!");
        } else {
            System.out.println("Failed to make connection!");
        }
    }
}
