package ar.com.gugler.sgc.modelo;

import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

import ar.com.gugler.dao.*;

public class TPFinal1 {

	public static void main(String[] args) throws ParseException, SQLException {
		// TODO Auto-generated method stub	
	
		SimpleDateFormat date = new SimpleDateFormat("dd/M/yyyy");
		Long IdPersona;
		
		/*
		AlumnoDAO alumnobd = new AlumnoDAO();
		
		Alumno a1 = new Alumno("39839107" , "Lopez" , "Joaquin" , date.parse("02/11/1996") , 
				"Leopoldo Lugones 876" , "4352072" , "Jellopez1@gmail.com" , "legajo123");
		
		alumnobd.insert(a1);
		IdPersona = alumnobd.GetIdToNewObject(); a1.setId(IdPersona);
		
		Alumno a2 = new Alumno("39839205" ,"Santa Cruz" , "Santiago" , date.parse("10/09/1996") , 
				"Luis Gudiño Kramer" , "4353535" , "elpajaroloco205@gmail.com" , "Ellegajo789");

		alumnobd.insert(a2);
		IdPersona = alumnobd.GetIdToNewObject(); a2.setId(IdPersona);
		
		alumnobd.update(a2);
		alumnobd.delete(a2);*/
		
		////////////////////////////////////////////////////// PROFESOR ////////////////////////////////////////////
		
		/*
		ProfesorDAO profesorbd = new ProfesorDAO();
		
		Profesor p1 = new Profesor("39124112" ,"Alonso" , "Javier" , date.parse("20/03/1997") , 
	"Division de los andes" , "4248000" , "alonsojavier123@gmail.com" , "20391241122" , date.parse("11/02/2009") );
		
		profesorbd.insert(p1);
		IdPersona = profesorbd.GetIdToNewObject(); p1.setId(IdPersona);
		
		profesorbd.update(p1);
		profesorbd.delete(p1);*/
		
		////////////////////////////////////////////////////// CURSO ////////////////////////////////////////////
		
		/*
		Curso Programacion_Java = new Curso(6969, "Java para Cracks", p1, 24);
		CursoDAO Cursobd = new CursoDAO();
		
		Cursobd.insert(Programacion_Java);
		Long IdCurso = Cursobd.GetIdToNewObject(); Programacion_Java.setId(IdCurso);
		
		Cursobd.update(Programacion_Java);
		
		Cursobd.delete(Programacion_Java);
		Cursobd.borrarAlumnos(Programacion_Java);
		
		Programacion_Java.agregarPersona(a1); Programacion_Java.agregarPersona(a2);
		Cursobd.insertarAlumnos(Programacion_Java);
		
		Programacion_Java.MostrarLista();
		*/
		
		////////////////////////////////////////////////////// MATERIA ////////////////////////////////////////////
		
		/*
		MateriaDAO Materiabd = new MateriaDAO();
		
		Materia POO = new Materia(6969, "Programacion Orientada a objetos", p1, 2020);
		
		Materiabd.insert(POO);
		Long IdMateria = Materiabd.GetIdToNewObject(); POO.setId(IdMateria);
		
		Materiabd.insertarAlumno(POO, a1); Materiabd.insertarAlumno(POO, a2);
		*/
		
		//////////////////////////////////////////////////////VER DATOS DE UNA PERSONA ////////////////////////////////////////////
		
		/*
		Universidad a = new Universidad();
		a.mostrarDatos(a1);
		a.mostrarDatos(a2);
		a.mostrarDatos(p1);*/
	}

}
