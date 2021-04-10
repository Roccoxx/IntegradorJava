package ar.com.gugler.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;
import ar.com.gugler.sgc.modelo.Alumno;

public class AlumnoDAO extends GenericDAO<Alumno> {


	@Override
	public String getTable() {
		return "alumno";
	}

	@Override
	protected String getInsertSql() {
		return "insert into alumno (numeroDocumento, apellido, nombres, fechaNacimiento , domicilio , telefono , email , legajo) "
				+ "values (?, ?, ?, ?, ?, ?, ?, ?) ";
	}

	@Override
	protected void setValuesInsert(PreparedStatement preparedStatement, Alumno object) throws SQLException {
		preparedStatement.setString(1, object.getNumeroDocumento());
		preparedStatement.setString(2, object.getApellido());
		preparedStatement.setString(3, object.getNombres());
		preparedStatement.setDate(4,new java.sql.Date(object.getFechaNacimiento().getTime()));
		preparedStatement.setString(5, object.getDomicilio());
		preparedStatement.setString(6, object.getTelefono());
		preparedStatement.setString(7, object.getEmail());
		preparedStatement.setString(8, object.getLegajo());
	}

	@Override
	protected String getUpdateSql() {
		return "update alumno set numeroDocumento = ?, apellido = ?, "
				+ "nombres = ?,  domicilio = ?, telefono = ?, "
				+ "email = ?, fechaNacimiento = ?, legajo = ? "
				+ "where id = ? ";
	}

	@Override
	protected void setValuesUpdate(PreparedStatement preparedStatement, Alumno object) throws SQLException {
		preparedStatement.setString(1, object.getNumeroDocumento());
		preparedStatement.setString(2, object.getApellido());
		preparedStatement.setString(3, object.getNombres());
		preparedStatement.setString(4, object.getDomicilio());
		preparedStatement.setString(5, object.getTelefono());
		preparedStatement.setString(6, object.getEmail());
		preparedStatement.setDate(7, new java.sql.Date(object.getFechaNacimiento().getTime()));
		preparedStatement.setString(8, object.getLegajo());
		preparedStatement.setLong(9, object.getId());
	}
	
	@Override
	protected String getDeleteSql() {
		return "delete from alumno where id = ? ";
	}

	@Override
	protected void setValuesDelete(PreparedStatement preparedStatement, Alumno object) throws SQLException {
		preparedStatement.setLong(1, object.getId());
	}

	@Override
	protected Alumno populate(ResultSet rs) throws SQLException {
		Long id = rs.getLong(1);
		String numeroDocumento = rs.getString(2);
		String apellido = rs.getString(3);
		String nombre = rs.getString(4);
		Date fechaNacimiento = rs.getDate(5);
		String domicilio = rs.getString(6);
		String telefono = rs.getString(7);
		String email = rs.getString(8);
		String legajo = rs.getString(9);
		
		Alumno a = new Alumno(numeroDocumento, apellido,nombre,fechaNacimiento, domicilio, telefono, email, legajo);
		a.setId(id);
		return a;
	}
	
	public static void createTable() throws SQLException {
		Statement st = Connection.getInstance().getConnection().createStatement();
		st.execute("CREATE TABLE ALUMNO ("
				+ "id int(11) NOT NULL AUTO_INCREMENT, "
				+ "numeroDocumento text NOT NULL, "
				+ "apellido text NOT NULL, "
				+ "nombre text NOT NULL, "
				+ "fechaNacimiento date , "
				+ "domicilio text , "
				+ "telefono text , "
				+ "email text , "
				+ "legajo text ,"
				+ " PRIMARY KEY (id))");
				
		st.close();
	}

}
