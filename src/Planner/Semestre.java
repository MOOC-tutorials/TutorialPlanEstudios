package Planner;

import java.util.ArrayList;
import java.util.Iterator;

public class Semestre {

	//-------------------------------------------------------------
	//Atributos
	//-------------------------------------------------------------
		
	ArrayList<Curso> cursoInscrito;
	ArrayList<Curso> cursoAprobado; 
	String periodo;
	Boolean probacionAcademica;
	int creditosmax;
	int numSemestre;
	double promedio;
	
	//-------------------------------------------------------------
	//Constructor
	//-------------------------------------------------------------
	
	public Semestre(String pPeriodo, ArrayList<Curso> pCursoInscrito,ArrayList<Curso> pCurosAprobados, int pNumSemestre) {
		periodo= pPeriodo;
		cursoInscrito = pCursoInscrito;
		cursoAprobado =pCurosAprobados;
		numSemestre =pNumSemestre;
	}
	
	
	//-------------------------------------------------------------
	// Metodos
	//-------------------------------------------------------------
	
	//-------------------------------------------------------------
	//Getters
	public ArrayList<Curso>  darInscritos() {
		return cursoInscrito;
	}
	
	public ArrayList<Curso>  darAprobado() {
		return cursoAprobado;
	}
	
	public String darPeriodo() {
		return periodo;
	}
	
	public int darCreditosMax() {
		return creditosmax;
	}
	
	public double darPromedio() {
		return promedio;
	}
	
	public boolean darProbacionAcademica() {
		return probacionAcademica;
	}
	
	public int darNumSemestre() {
		return numSemestre;
	}

	
	//-------------------------------------------------------------
	//Setters
	public void setNumSemestre(int num) {
		numSemestre = num;
	}
	
	public void setProbacionAcademica(boolean b) {
		probacionAcademica = b;
	}
	
	public void SetCursoInscrito(ArrayList<Curso> c) {
		cursoInscrito=c; 	
	}
	
	//-------------------------------------------------------------
	//Helpers
	public void ActualiozarPromedio() {
		double i =0; 
		Iterator <Curso> Curso = cursoInscrito.iterator();
		while (Curso.hasNext()) {
			Curso CActual = Curso.next();
			i=i+CActual.darCreditos();}	
		promedio = i; 
	}	

}
