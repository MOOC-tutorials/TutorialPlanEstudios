package Planner;

import java.util.*;

public class Plan {

	
	//-----------------------------------------
	//Attributes

	ArrayList<Curso> cursos = new ArrayList<Curso>();
	String nombrePlan; 
	
	
	//-------------------------------------------------------------
	//methods
	
	public Plan(String pNombre,ArrayList<Curso> pCursos) {
		nombrePlan = pNombre;
		cursos = pCursos; 
	}
	public void guardarplan(ArrayList<Curso> pCursos) {	
		cursos = pCursos;
	
	}
	

	
	public String darNombre() {
		return nombrePlan;
	}
	public ArrayList<Curso> darCursos() {
		
		return cursos;
		
	}
	public double darCreditos() {
		double i=0; 
		if (cursos.size()>0) {
			Iterator <Curso> Cprereq = cursos.iterator();
			while (Cprereq.hasNext()) {
				Curso CActual = Cprereq.next();
				i=i+CActual.darCreditos();
		}return i;
	}
		return i;
	
}}
