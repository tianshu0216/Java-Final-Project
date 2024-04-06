/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dataaccesslayer;

import java.sql.Connection;

import java.sql.DriverManager;

import java.sql.SQLException;

import java.io.*;

import java.util.*;

public class DBConnection {
	private static DBConnection instance = null;

    private String url;
    private String user;
    private String password;
	private String driverString = "com.mysql.cj.jdbc.Driver";
	private static Connection connection = null;

	
	
    private DBConnection() 
    { }

    /**
     * Gets the singleton instance of DBConnection. If the instance does not exist, creates a new one and initializes the connection.
     * @return The singleton instance of DBConnection.
     */
    public static DBConnection getInstance() {
        if (instance == null) {
            instance = new DBConnection();
            instance.initConnection();
        }
        return instance;
    }
    /**
     * Initiate a connection to the database from the properties file.
     * 
     */
    private void initConnection() {
        
        try (InputStream in = new FileInputStream("C:\\javaFinal\\database.properties")) 
        {
            System.out.println(in);
            Properties dbConnectionProps = new Properties();
            dbConnectionProps.load(new InputStreamReader(in));

            // Construct the database URL
            String dbType = dbConnectionProps.getProperty("db");
            String dbName = dbConnectionProps.getProperty("name");
            String dbHost = dbConnectionProps.getProperty("host");
            String dbPort = dbConnectionProps.getProperty("port");

            url = "jdbc:" + dbType + "://" + dbHost + ":" + dbPort + "/" + dbName;
            user = dbConnectionProps.getProperty("user");
            password = dbConnectionProps.getProperty("pass");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Gets a connection to the database.
     * @return The database connection.
     */
    public  Connection getConnection() 
    {
    	 try {
	            Class.forName(driverString);
	            connection = DriverManager.getConnection(url, user, password);  // 添加代码来检查连接是否成功
        if (connection != null && !connection.isClosed()) {
            System.out.println("Database connection established successfully.");
        } else {
            System.out.println("Failed to establish database connection.");
        }
    } catch (ClassNotFoundException | SQLException e) {
        e.printStackTrace();
    }
    return connection;
}
}