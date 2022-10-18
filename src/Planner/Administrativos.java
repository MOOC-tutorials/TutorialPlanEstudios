package Planner;

import java.util.*;

public class Administrativos {

	
	ArrayList<Estudiantes> Estudiantes_Inscritos = new ArrayList<Estudiantes>();
	
	//-----------------------------------------
	//Attributes
	String Nombre; 
	String Departamento; 
	int Codigo; 
	
	public Administrativos(String pNombre, String pDepartamento, int pCodigo) {
		Nombre=pNombre;
		Departamento=pDepartamento;
		Codigo= pCodigo; 
	}
	//-------------------------------------------------------------
	//methods

	public String darNombre() {
		return Nombre;
	}
	
	public String darDepartamento() {
		return Departamento;
	}
	
	public int darCodigo() {
		return Codigo;
	}

	

	
	
}
