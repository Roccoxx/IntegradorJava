package ar.com.gugler.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import ar.com.gugler.sgc.modelo.Alumno;
import ar.com.gugler.sgc.modelo.Materia;
import ar.com.gugler.sgc.modelo.Profesor;

public class MateriaDAO extends GenericDAO<Materia> {

	@Override
	public String getTable() {
		return "materias";
	}

	@Override
	protected String getInsertSql() {
		return "INSERT INTO `materias` (`codigo`, `nombre`, `id_profesor`, `anio`) VALUES (?, ?, ?, ?) ";
	}

	@Override
	protected void setValuesInsert(PreparedStatement preparedStatement, Materia object) throws SQLException {
		preparedStatement.setInt(1, object.getCodigo());
		preparedStatement.setString(2, object.getNombre());
		preparedStatement.setLong(3, object.getProfesor().getId());
		preparedStatement.setInt(4, object.getAnio());
	}
	
	@Override
	protected String getUpdateSql() {
		return "UPDATE `materias` SET `codigo` = ?, `nombre` = ?, `id_profesor` = ?, `anio` = ? WHERE (`id` = ?) ";
	}

	@Override
	protected void setValuesUpdate(PreparedStatement preparedStatement, Materia object) throws SQLException {
		preparedStatement.setInt(1, object.getCodigo());
		preparedStatement.setString(2, object.getNombre());
		preparedStatement.setLong(3, object.getProfesor().getId());
		preparedStatement.setInt(4, object.getAnio());
		preparedStatement.setLong(5, object.getId());

	}

	@Override
	protected Materia populate(ResultSet rs) throws SQLException {
		// TODO Auto-generated method stub
		Long id = rs.getLong(1);
		Integer codigo = rs.getInt(2);
		String nombre = rs.getString(3);
		Long id_profesor = rs.getLong(4);
		Integer anio = rs.getInt(5);
		
		java.sql.Connection connection = Connection.getInstance().getConnection();
		
		PreparedStatement preparedStatement = connection
		.prepareStatement("SELECT `numeroDocumento`, `apellido`, `nombres`, `fechaNacimiento` , `domicilio` , `telefono` , `email` , `cuil` , `fechaIngreso` FROM profesor WHERE id=? ");
		preparedStatement.setLong(1, id_profesor);
		
		ResultSet resultado = preparedStatement.executeQuery();
		
		if(resultado.next()) {
			var profesor = new Profesor(resultado.getString(1), resultado.getString(2), resultado.getString(3), 
			resultado.getDate(4), resultado.getString(5), resultado.getString(6), resultado.getString(7), 
			resultado.getString(8), resultado.getDate(9));
			profesor.setId(id_profesor);
			
			var m = new Materia(codigo , nombre , profesor , anio);
			m.setId(id);
			return m;
		}
		else {
			var profesor = new Profesor("0", "0", "0", null, "0", "0", "0", "0", null);
			profesor.setId(id_profesor);
			
			var m = new Materia(codigo , nombre , profesor , anio);
			m.setId(id);
			return m;
		}
	}

	public void insertarAlumno(Materia materia, Alumno alumno) throws SQLException {
		java.sql.Connection connection = Connection.getInstance().getConnection();
		
		PreparedStatement preparedStatement = connection
				.prepareStatement("INSERT INTO `alumnos_materias` (`idAlumno`, `idMaterias`) VALUES (?,?);");
		
		preparedStatement.setLong(1, alumno.getId());
		preparedStatement.setLong(2, materia.getId());
		preparedStatement.execute();
	}
	
	@Override
	protected String getDeleteSql() {
		return "delete from materias where id = ? ";
	}

	@Override
	protected void setValuesDelete(PreparedStatement preparedStatement, Materia object) throws SQLException {
		preparedStatement.setLong(1, object.getId());
	}
	
	public void borrarAlumnos(Materia materia) throws SQLException {
		java.sql.Connection connection = Connection.getInstance().getConnection();
		PreparedStatement preparedStatement = connection
				.prepareStatement("delete from alumnos_materias WHERE idMaterias=?");
		preparedStatement.setLong(1, materia.getId());
		preparedStatement.executeUpdate();		
	}
	
	public void borrarAlumno(Materia materia, Alumno alumno) throws SQLException {
		java.sql.Connection connection = Connection.getInstance().getConnection();
		PreparedStatement preparedStatement = connection
				.prepareStatement("delete from alumnos_materias WHERE idMaterias=? AND idAlumno=?");
		preparedStatement.setLong(1, materia.getId());
		preparedStatement.setLong(2, alumno.getId());
		preparedStatement.executeUpdate();		
	}

	@Override
	public Materia get(Long id) throws SQLException {
		var materia = super.get(id);
		java.sql.Connection connection = Connection.getInstance().getConnection();
		PreparedStatement preparedStatement = connection
				.prepareStatement("SELECT `idAlumno` FROM alumnos_materias WHERE idMaterias=? ");
		preparedStatement.setLong(1, materia.getId());
		ResultSet rs = preparedStatement.executeQuery();
		AlumnoDAO alumnoDao = new AlumnoDAO();
		while (rs.next()) {
			var idAlumno = rs.getLong(1);
			var alumno = alumnoDao.get(idAlumno);
			materia.getAlumnos().add(alumno);
		}
		return materia;
	}

	@Override
	public List<Materia> getAll() throws SQLException {
		var materias = super.getAll();
		java.sql.Connection connection = Connection.getInstance().getConnection();
		for (Materia materia : materias) {
			PreparedStatement preparedStatement = connection
					.prepareStatement("SELECT `idAlumno` FROM alumnos_materias WHERE idMaterias=? ");
			preparedStatement.setLong(1, materia.getId());
			ResultSet rs = preparedStatement.executeQuery();
			AlumnoDAO alumnoDao = new AlumnoDAO();
			while (rs.next()) {
				var idAlumno = rs.getLong(1);
				var alumno = alumnoDao.get(idAlumno);
				materia.getAlumnos().add(alumno);
			}
		}
		return materias;
	}

}