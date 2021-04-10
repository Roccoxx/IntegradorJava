package ar.com.gugler.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;


import ar.com.gugler.sgc.modelo.Profesor;

public class ProfesorDAO extends GenericDAO<Profesor> {
	@Override
	public String getTable() {
		return "profesor";
	}

	@Override
	protected String getInsertSql() {
		return "insert into profesor (numeroDocumento, apellido, nombres, fechaNacimiento , domicilio , telefono , email , cuil , fechaIngreso) "
				+ "values (?, ?, ?, ?, ?, ?, ?, ?, ?) ";
	}

	@Override
	protected void setValuesInsert(PreparedStatement preparedStatement, Profesor object) throws SQLException {
		preparedStatement.setString(1, object.getNumeroDocumento());
		preparedStatement.setString(2, object.getApellido());
		preparedStatement.setString(3, object.getNombres());
		preparedStatement.setDate(4, new java.sql.Date(object.getFechaNacimiento().getTime()));
		preparedStatement.setString(5, object.getDomicilio());
		preparedStatement.setString(6, object.getTelefono());
		preparedStatement.setString(7, object.getEmail());
		preparedStatement.setString(8, object.getCuil());
		preparedStatement.setDate(9, new java.sql.Date(object.getFechaIngreso().getTime()));
	}

	@Override
	protected String getUpdateSql() {
		return "update profesor set cuil = ?, numeroDocumento = ?, apellido = ?, "
				+ "nombres = ?, domicilio = ?, telefono = ?, "
				+ "email = ?, fechaNacimiento = ?, fechaIngreso = ? "
				+ "where id = ? ";
	}

	@Override
	protected void setValuesUpdate(PreparedStatement preparedStatement, Profesor object) throws SQLException {
		preparedStatement.setString(1, object.getCuil());
		preparedStatement.setString(2, object.getNumeroDocumento());
		preparedStatement.setString(3, object.getApellido());
		preparedStatement.setString(4, object.getNombres());
		preparedStatement.setString(5, object.getDomicilio());
		preparedStatement.setString(6, object.getTelefono());
		preparedStatement.setString(7, object.getEmail());
		preparedStatement.setDate(8, new java.sql.Date(object.getFechaNacimiento().getTime()));
		preparedStatement.setDate(9, new java.sql.Date(object.getFechaIngreso().getTime()));
		preparedStatement.setLong(10, object.getId());
	}
	
	@Override
	protected String getDeleteSql() {
		return "delete from profesor where id = ? ";
	}

	@Override
	protected void setValuesDelete(PreparedStatement preparedStatement, Profesor object) throws SQLException {
		preparedStatement.setLong(1, object.getId());
	}
	
	@Override
	protected Profesor populate(ResultSet rs) throws SQLException {
		Long id = rs.getLong(1);
		String cuil = rs.getString(2);
		String numeroDocumento = rs.getString(3);
		String apellido = rs.getString(4);
		String nombre = rs.getString(5);
		String domicilio = rs.getString(6);
		String telefono = rs.getString(7);
		String email = rs.getString(8);
		Date fechaNacimiento = rs.getDate(9);
		Date fechaIngreso = rs.getDate(10);
		
		Profesor p = new Profesor(numeroDocumento, apellido, nombre, fechaNacimiento , domicilio , telefono , email , cuil , fechaIngreso);
		p.setId(id);
		return p;
	}
	
	public static void createTable() throws SQLException {
		Statement st = Connection.getInstance().getConnection().createStatement();
		st.execute("CREATE TABLE PROFESOR ("
				+ "id int(11) NOT NULL AUTO_INCREMENT, "
				+ "cuil text NOT NULL, "
				+ "numeroDocumento text NOT NULL, "
				+ "apellido text NOT NULL, "
				+ "nombre text NOT NULL, "
				+ "domicilio text , "
				+ "telefono text , "
				+ "email text , "
				+ "fechaNacimiento date , "
				+ "fechaIngreso date ,"
				+ " PRIMARY KEY (id))");
		st.close();
	}
}
