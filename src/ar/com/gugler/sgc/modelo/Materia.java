package ar.com.gugler.sgc.modelo;

public class Materia extends Asignatura {
	
	private Integer anio;

	public Integer getAnio() {
		return anio;
	}

	public void setAnio(Integer anio) {
		this.anio = anio;
	}

	public Materia(Integer codigo , String nombre , Alumno alumnos , Profesor profesor , Integer anio) {
		super(codigo,nombre,alumnos,profesor);
		this.anio = anio;
	}
	public Materia(Integer codigo , String nombre , Profesor profesor , Integer anio) {
		super(codigo,nombre,profesor);
		this.anio = anio;
	}

	public Materia() {
		super();
	}

	@Override
	public String toString() {
		return super.getNombre() + ": año= " + anio + " profesor= " + (super.getProfesor().getNombres()) +
		" " + (super.getProfesor().getApellido()) + " codigo= " + super.getCodigo();
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((anio == null) ? 0 : anio.hashCode());
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
		Materia other = (Materia) obj;
		if (anio == null) {
			if (other.anio != null)
				return false;
		} else if (!anio.equals(other.anio))
			return false;
		return true;
	}
	
	
}
