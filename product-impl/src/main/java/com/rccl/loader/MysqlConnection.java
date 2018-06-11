package com.rccl.loader;

import java.io.BufferedReader;
import java.io.FileReader;
import java.sql.DriverManager;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.StringTokenizer;

public class MysqlConnection {

    public static void main(String[] argv) {

        System.out.println("-------- MYSQL "
                + "JDBC Connection Testing ------------");

        try {

            Class.forName("com.mysql.jdbc.Driver");

        } catch (ClassNotFoundException e) {

            System.out.println("Where is your PostgreSQL JDBC Driver? "
                    + "Include in your library path!");
            e.printStackTrace();
            return;

        }

        addtxnsData();
        addProductsData();
        addUserData();
        
        System.out.println("PostgreSQL JDBC Driver Registered!");

    }


    private static void addProductsData() {

        Connection connection = null;
        Statement stmt = null;

        try {
            connection = DriverManager.getConnection(
                    "\"jdbc:mysql://localhost/txns", "kunal",
                    "root");
        } catch (SQLException e) {

            System.out.println("Connection Failed! Check output console");
            e.printStackTrace();
        }


        try {

            String fileName = "/home/kunal/fullstack-crud/transaction-impl/src/main/mockdata/products.csv";
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
                        String productId = st.nextToken();
                        String productName = st.nextToken();
                        String price = st.nextToken();
                        String theStatement = "INSERT INTO  products VALUES(" + productId + ", " + "'" + productName + "'" + ", " + price + ")";
                        
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


    private static void addUserData() {

        Connection connection = null;
        Statement stmt = null;

        try {
            connection = DriverManager.getConnection(
                    "jdbc:mysql://localhost/txns", "kunal",
                    "root");
        } catch (SQLException e) {

            System.out.println("Connection Failed! Check output console");
            e.printStackTrace();
        }


        try {

            String fileName = "/home/kunal/fullstack-crud/transaction-impl/src/main/mockdata/user.csv";
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
                        String userId = st.nextToken();
                        String name = st.nextToken();
                        String email = st.nextToken();
                        String userName = st.nextToken();
                        String phone = st.nextToken();
                        String theStatement = "INSERT INTO  user_details VALUES(" + userId + ", " + "'" + name + "'" + ", " + "'" + email + "'" + "," + "'" + userName + "'"+ "," + "'" + phone + "'" + ")";
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



    private static void addtxnsData() {

        Connection connection = null;
        Statement stmt = null;

        try {
            connection = DriverManager.getConnection(
                    "jdbc:mysql://localhost/txns", "kunal",
                    "root");
        } catch (SQLException e) {

            System.out.println("Connection Failed! Check output console");
            e.printStackTrace();
        }


        try {

            String fileName = "/home/kunal/fullstack-crud/transaction-impl/src/main/mockdata/txn.csv";
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
