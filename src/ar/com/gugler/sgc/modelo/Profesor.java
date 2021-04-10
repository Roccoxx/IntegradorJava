package ar.com.gugler.sgc.modelo;

import java.util.Date;

public class Profesor extends Persona {

	private String cuil;
	private Date fechaIngreso;

	public String mostrarInformacion(Profesor x ) {
		   //NOMBRE Y APELLIDO CONCATENADO CON SU CUIL
			return x.toString() + "CUIL= "  + this.getCuil(); 
			}

	public String getCuil() {
		return cuil;
	}

	public void setCuil(String cuil) {
		this.cuil = cuil;
	}

	public Date getFechaIngreso() {
		return fechaIngreso;
	}

	public void setFechaIngreso(Date fechaIngreso) {
		this.fechaIngreso = fechaIngreso;
	}

	
	public Profesor(String numeroDocumento, String apellido, String nombres, Date fechaNacimiento, String domicilio,
			String telefono, String email, String cuil, Date fechaIngreso) {
		super(numeroDocumento, apellido, nombres, fechaNacimiento, domicilio, telefono, email);
		this.cuil = cuil;
		this.fechaIngreso = fechaIngreso;
	}
	

	@Override
	public String toString() {
		return "Profesor [Nombre=" + this.getNombres().toUpperCase() + " Apellido= " + this.getApellido().toUpperCase() +  "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((cuil == null) ? 0 : cuil.hashCode());
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
		Profesor other = (Profesor) obj;
		if (cuil == null) {
			if (other.cuil != null)
				return false;
		} else if (!cuil.equals(other.cuil))
			return false;
		return true;
	}
	
	
}
