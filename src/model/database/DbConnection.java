package model.database;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

import model.database.exception.DbException;

public class DbConnection {
	
	private static Connection connection = null;
	
	public static Connection getConnection() {
		if(connection == null) {
				Properties props = loadProperties();
				String url = props.getProperty("dburl");
				
				try {
					connection = DriverManager.getConnection(url, props);
				} catch (SQLException e) {
					throw new DbException(e.getMessage());
				}
		}
		return connection;
		
	}

	private static Properties loadProperties() {
		try(FileInputStream inputStream = new FileInputStream("db.properties")){
			Properties props = new Properties();
			props.load(inputStream);
			return props;
		}catch(IOException err) {
			System.err.println("Error: " + err.getMessage());
			err.printStackTrace();
			return null;
		}
		
	}

	public static void closeConnection() {
		if(connection != null) {
			try {
				connection.close();
			}catch(SQLException e){
				throw new DbException(e.getMessage());
			}
		}
	}
	
	public static void closeStatment(Statement smt) {
		if(smt != null) {
			try {
				smt.close();
			}catch(SQLException e) {
				throw new DbException(e.getMessage());
			}
		}
	}

	public static void closeResultSet(ResultSet resultSet) {
		if(resultSet != null) {
			try {
				resultSet.close();
			}catch(SQLException e) {
				throw new DbException(e.getMessage());
			}
		}
	}
}