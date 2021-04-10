package ar.com.gugler.dao;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;

public abstract class BaseDAO<T extends BaseModelo> {
	protected Connection con;
	public void connect() {
		try {
			Class.forName("com.mysql.jdbc.Driver").newInstance();
			con =
					DriverManager.getConnection("jdbc:mysql://localhost/tp?"
							+ "user=root&password=holagonza");
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Error conectandose a la base de datos");
		}
	}
	
	public void closeConnection() {
		try {
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("Error cerrando la conexion a la base");
		}
	}
	
	public abstract void insert(T b) throws SQLException;
	public abstract void delete(T b) throws SQLException;
	public abstract void update(T b);
	public abstract List<T> getAll() throws SQLException;
}