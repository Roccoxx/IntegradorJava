package ar.com.gugler.sgc.modelo;
import ar.com.gugler.dao.BaseModelo;
import java.util.ArrayList;
import java.util.List;

public class Asignatura extends BaseModelo {
	
	private Integer codigo; 
	private String nombre;
	private Alumno alumnos;
	protected Profesor profesor;
	protected List<Alumno> listaAlumno = new ArrayList<Alumno>();	
	
	public Integer getCodigo() {
		return codigo;
	}
	public void setCodigo(Integer codigo) {
		this.codigo = codigo;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public Alumno getCantidadAlumnos() {
		return alumnos;
	}
	public void setCantidadAlumnos(Alumno alumnos) {
		this.alumnos = alumnos;
	}
	public List<Alumno> getAlumnos() {
		return listaAlumno;
	}
	public Profesor getProfesor() {
		return profesor;
	}
	public void setProfesor(Profesor profesor) {
		this.profesor = profesor;
	}
	public Asignatura() {
		super();
	}
	public Asignatura(Integer codigo, String nombre, Alumno alumnos, Profesor profesor) {
		super();
		this.codigo = codigo;
		this.nombre = nombre;
		this.alumnos = alumnos;
		this.profesor = profesor;
	}
	public Asignatura(Integer codigo, String nombre, Profesor profesor) {
		super();
		this.codigo = codigo;
		this.nombre = nombre;
		this.profesor = profesor;
	}
	@Override
	public String toString() {
		return "Asignatura [codigo=" + codigo + ", nombre=" + nombre + ", alumnos=" + alumnos + ", profesor="
				+ profesor + "]";
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((codigo == null) ? 0 : codigo.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Asignatura other = (Asignatura) obj;
		if (codigo == null) {
			if (other.codigo != null)
				return false;
		} else if (!codigo.equals(other.codigo))
			return false;
		return true;
	}
	
	
}
