package com.cg.pwa.dao;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class DBConnection {
	private static Connection con;
	public static Connection getConnection() {
		// TODO Auto-generated method stub
		FileReader file;
		try {
			/*
			 * Reading the oracle.properties file using FileReader class and loading it into
			 * the Properties object.
			 */
			file = new FileReader(new File("oracle.properties"));
			Properties prop = new Properties();
			prop.load(file);
			
			/*
			 * Loading all the necessary data from the oracle.properties file
			 * It consists of url, userName, password and driver of the oracle database
			 */
			String url = prop.getProperty("url");
			String userName = prop.getProperty("userName");
			String password = prop.getProperty("password");
			String driver = prop.getProperty("driver");
			
			Class.forName(driver); //Loading the class in order to establish a connection with the DB.
			System.out.println("Class Loaded...");
			con = DriverManager.getConnection(url, userName, password); //Establishing connection with DB.
			System.out.println("Connected to DB...");
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return con;
	}
}
