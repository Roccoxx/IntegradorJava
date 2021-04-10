package ar.com.gugler.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import ar.com.gugler.sgc.modelo.Alumno;
import ar.com.gugler.sgc.modelo.Curso;
import ar.com.gugler.sgc.modelo.Profesor;

public class CursoDAO extends GenericDAO<Curso> {

	@Override
	public String getTable() { return "cursos"; }

	@Override
	protected String getInsertSql() {
		return "INSERT INTO `cursos` (`codigo`, `nombre`, `id_profesor`, `cupo`) VALUES (?, ?, ?, ?) ";
	}

	@Override
	protected void setValuesInsert(PreparedStatement preparedStatement, Curso object) throws SQLException {
		preparedStatement.setInt(1, object.getCodigo());
		preparedStatement.setString(2, object.getNombre());
		preparedStatement.setLong(3, object.getProfesor().getId());
		preparedStatement.setInt(4, object.getCupo());
	}

	@Override
	protected String getUpdateSql() {
		return "UPDATE `cursos` SET `codigo` = ?, `nombre` = ?, `id_profesor` = ?, `cupo` = ? WHERE (`id` = ?) ";
	}
	
	@Override
	protected void setValuesUpdate(PreparedStatement preparedStatement, Curso object) throws SQLException {
		preparedStatement.setInt(1, object.getCodigo());
		preparedStatement.setString(2, object.getNombre());
		preparedStatement.setLong(3, object.getProfesor().getId());
		preparedStatement.setInt(4, object.getCupo());
		preparedStatement.setLong(5, object.getId());
	}
	
	@Override
	protected Curso populate(ResultSet rs) throws SQLException {
		// TODO Auto-generated method stub
		Long id = rs.getLong(1);
		Integer codigo = rs.getInt(2);
		String nombre = rs.getString(3);
		Long id_profesor = rs.getLong(4);
		Integer cupo = rs.getInt(5);
		
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
			
			var m = new Curso(codigo, nombre, profesor, cupo);
			m.setId(id);
			return m;
		}
		else {
			var profesor = new Profesor("0", "0", "0", null, "0", "0", "0", "0", null);
			profesor.setId(id_profesor);
			
			var m = new Curso(codigo, nombre, profesor, cupo);
			m.setId(id);
			return m;
		}
	}

	@Override
	protected String getDeleteSql() {
		return "delete from cursos where id = ? ";
	}

	@Override
	protected void setValuesDelete(PreparedStatement preparedStatement, Curso object) throws SQLException {
		preparedStatement.setLong(1, object.getId());
	}

	public void insertarAlumnos(Curso object) throws SQLException {
		java.sql.Connection connection = Connection.getInstance().getConnection();
		
		for (Alumno a : object.getAlumnos()) {
			PreparedStatement preparedStatement = connection
					.prepareStatement("INSERT INTO `alumnos_cursos` (`idAlumno`, `idCurso`) VALUES (?,?);");
			preparedStatement.setLong(1, a.getId());
			preparedStatement.setLong(2, object.getId());
			preparedStatement.execute();
		}
	}
	
	public void borrarAlumnos(Curso object) throws SQLException {
		java.sql.Connection connection = Connection.getInstance().getConnection();
		PreparedStatement preparedStatement = connection
				.prepareStatement("delete from alumnos_cursos WHERE idCurso=? ");
		preparedStatement.setLong(1, object.getId());
		preparedStatement.executeUpdate();		
	}
	
	public void borrarAlumno(Curso curso, Alumno alumno) throws SQLException {
		java.sql.Connection connection = Connection.getInstance().getConnection();
		PreparedStatement preparedStatement = connection
				.prepareStatement("delete from alumnos_cursos WHERE idCurso=? AND idAlumno=?");
		preparedStatement.setLong(1, curso.getId());
		preparedStatement.setLong(2, alumno.getId());
		preparedStatement.executeUpdate();		
	}

	@Override
	public Curso get(Long id) throws SQLException {
		var curso = super.get(id);
		java.sql.Connection connection = Connection.getInstance().getConnection();
		PreparedStatement preparedStatement = connection
				.prepareStatement("SELECT `idAlumno` FROM alumnos_cursos WHERE idCurso=? ");
		preparedStatement.setLong(1, curso.getId());
		ResultSet rs = preparedStatement.executeQuery();
		AlumnoDAO alumnoDao = new AlumnoDAO();
		while (rs.next()) {
			var idAlumno = rs.getLong(1);
			var alumno = alumnoDao.get(idAlumno);
			curso.getAlumnos().add(alumno);
		}
		
		return curso;
	}

	@Override
	public List<Curso> getAll() throws SQLException {
		var cursos = super.getAll();
		java.sql.Connection connection = Connection.getInstance().getConnection();
		for (Curso curso : cursos) {
			PreparedStatement preparedStatement = connection
					.prepareStatement("SELECT `idAlumno` FROM alumnos_cursos WHERE idCurso=? ");
			preparedStatement.setLong(1, curso.getId());
			ResultSet rs = preparedStatement.executeQuery();
			AlumnoDAO alumnoDao = new AlumnoDAO();
			while (rs.next()) {
				var idAlumno = rs.getLong(1);
				var alumno = alumnoDao.get(idAlumno);
				curso.getAlumnos().add(alumno);
			}
		}
		
		return cursos;
	}

}