package com.api.utilities;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.apache.log4j.Logger;

import com.api.base.Base_API;

public class JDBCUtils extends Base_API{


	final static Logger logger = Logger.getLogger(JDBCUtils.class);
	public  static Connection  connection  = null;
	private static Statement   statement   = null;
	private  static ResultSet   resultset   = null;

	String host;
	String port;
	static String login;
	static String password;
	static String connurl;
	String db;
	boolean connect;

	public JDBCUtils(){
		logger.info("Database Connection Method Called");
		login=prop_api.getProperty("login");
		password=prop_api.getProperty("password");
		host=prop_api.getProperty("host");
		port=prop_api.getProperty("dbport");
		db=prop_api.getProperty("databasename");
		connurl = "jdbc:postgresql://"+host+":"+port+"/"+db;
		logger.info("Constructed Connection URL for the Database:\t"+connurl);
		
	}

	public static Connection Connect() {
		logger.info("JDBC Connection establisment initiated");
		try {
			connection = DriverManager.getConnection(connurl, login, password);
			logger.info("Connection Established");
			logger.info("$$$$ Before Test Configurations - Ended $$$$");
			logger.info("X");
			logger.info("X");
			
		}

		catch (SQLException e) {
			logger.error("Connection to the Database Failed and Hence DB Validation will be skipped\n"+e);

		}
		return connection;
	}


	public static ResultSet Query(String query){


		try {
			statement = connection.createStatement();
			resultset = statement.executeQuery(query);
		}
		catch (Exception e) {
			logger.error("Exception occured:\n" + e);

		}
		return resultset;
		
	}


	public void Change(String query){

		try {
			statement = connection.createStatement();
			int rec1 = statement.executeUpdate(query);

			logger.info("Records used by the Automation Setup inthe previous run is removed\t"+rec1);			

		}
		catch (Exception e) {
			logger.error("Exception occured:\n" + e);
			return;
		}

	}


	public void closeConnection(){

		try {
			connection.close();
			logger.info("******************Databse Connection Closed***************");
		}
		catch(Exception e)         {
			logger.error("Closing Execption\n"+e);
		}

	}

}


