package Planner;

import java.util.*;

public class Curso {



	//-------------------------------------------------------------
	//Atributos
	//-------------------------------------------------------------

	String nombre;
	String codigo;
	String CRN;
	String prereq;
	String coreq;
	int creditos;
	String tipo;
	String ciclo;
	String horario;
	int semestre;
	//int crn;
	//int periodo;
	//String horario;
	
	double nota=0.0;





	//-------------------------------------------------------------
	//Constructor
	//-------------------------------------------------------------


	public Curso(String pNombre, String pCodigo, String pPre, String pCoreq, int pCreditos, String pTipo, String pCiclo, String pHorario) {
		nombre= pNombre;
		codigo= pCodigo;
		prereq = pPre;
		coreq= pCoreq;
		tipo= pTipo;
		ciclo = pCiclo;
		creditos = pCreditos;
		horario = pHorario;
	}
	

	
	//-------------------------------------------------------------
	//Metodos
	//-------------------------------------------------------------
	
	public void cambiarNota(double pNota) {
		nota= pNota;
	}
	
	
	
	
	//---------------------------------------------------------------------------------------------

	public String darciclo() {
		return ciclo;
	}


	public int darCreditos() {
		return creditos;
	}

	public String darNombre() {
		return nombre;
	}

//	public int darCRN() {
//		return crn	}

	public String darCodigo() {
		return codigo;
	}

	public String darTipo() {
		return tipo;
	}

	//public String darHorario() {
	//	return horario;	}


	public double darNota(){
		return nota;
	}

	public String darPreRequ() {
		return  prereq;
	}

	public String darCoRequ() {
		return coreq;
	}

	public String darHorario() {
		return horario;
	}
//	public int darPeriodo() {
//		return periodo;	}
	
	

}