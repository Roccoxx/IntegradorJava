package ar.com.gugler.sgc.modelo;

import java.util.Date;

public class Alumno extends Persona {
	 private String legajo;
	 
	 public String mostrarInformacion(Alumno x ) {
		   //NOMBRE Y APELLIDO CONCATENADO CON SU NRO DE DOCUMENTO
			return x.toString() + "DNI: " + this.getNumeroDocumento(); 
			}

	public String getLegajo() {
		return legajo;
	}

	public void setLegajo(String legajo) {
		this.legajo = legajo;
	}

	public Alumno(String numeroDocumento, String apellido, String nombres, Date fechaNacimiento, String domicilio,
			String telefono, String email, String legajo) {
		super(numeroDocumento, apellido, nombres, fechaNacimiento, domicilio, telefono, email);
		this.legajo = legajo;
	}

	@Override
	public String toString() {
		return "Alumno [Nombre=" + this.getNombres() + " Apellido= " + this.getApellido() +  "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((legajo == null) ? 0 : legajo.hashCode());
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
		Alumno other = (Alumno) obj;
		if (legajo == null) {
			if (other.legajo != null)
				return false;
		} else if (!legajo.equals(other.legajo))
			return false;
		return true;
	}
	
}
