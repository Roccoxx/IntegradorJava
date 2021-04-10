package ar.com.gugler.dao;

import java.sql.DriverManager;
import java.sql.SQLException;

public class Connection {

	private static Connection instance;
	private java.sql.Connection con;
	
	private Connection() {
		try {
			Class.forName("com.mysql.jdbc.Driver").newInstance();
			this.con = DriverManager.getConnection("jdbc:mysql://localhost/tp?"
					+ "user=root&password=holagonza");
		} catch (SQLException |  InstantiationException | IllegalAccessException
				| ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	public static Connection getInstance(){
		if (instance == null) {
			instance = new Connection();
		}
		return instance;
	}
	
	
	public java.sql.Connection getConnection() {
		return this.con;
	}
}
