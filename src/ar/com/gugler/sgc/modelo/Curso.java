package ar.com.gugler.sgc.modelo;

public class Curso extends Asignatura implements Administrable {
	private Integer cupo = 24;
	
	public boolean admiteInscripciones(){
		if(this.listaAlumno.size() >= cupo) {
			return false;}
		else { return true;}
	}

	public Integer getCupo() {
		return cupo;
	}

	public void setCupo(Integer cupo) {
		this.cupo = cupo;
	}


	public Curso(Integer cupo) {
		super();
		this.cupo = cupo;
	}
	
	public Curso(Integer codigo, String nombre, Profesor profesor, Integer cupo) {
		super(codigo, nombre, profesor);
		this.cupo = cupo;
	}

	@Override
	public String toString() {
		return "Curso [cupo=" + cupo + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((cupo == null) ? 0 : cupo.hashCode());
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
		Curso other = (Curso) obj;
		if (cupo == null) {
			if (other.cupo != null)
				return false;
		} else if (!cupo.equals(other.cupo))
			return false;
		return true;
	}
	
	public boolean agregarPersona(Alumno aux) {
		if(admiteInscripciones()){
			this.listaAlumno.add(aux);
			return true;
		}
		else{
			System.out.println("El curso está lleno, te hubieses anotado antes!");
			return false;
		}
	}
	
	public void eliminarAlumno(Alumno aux) {
		for(int i = 0 ; i<this.listaAlumno.size() ; i++)
		{
			if(this.listaAlumno.get(i).equals(aux)){
				this.listaAlumno.remove(i);
			}
		}
	}
	
	public void MostrarLista(){
		for(int i = 0 ; i<this.listaAlumno.size() ; i++) {
			System.out.println("Nombre Alumno: " + (this.listaAlumno.get(i)).getNombres());
			System.out.println("Apellido Alumno: " + (this.listaAlumno.get(i)).getApellido());
			
		}
		
		System.out.println("Nombre Profesor: " + (this.profesor.getNombres()));
		System.out.println("Apellido Profesor: " + (this.profesor.getApellido()));
	}
}
