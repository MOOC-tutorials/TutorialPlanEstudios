package Planner;

import java.util.ArrayList;

public class Pensum {
	
	//-------------------------------------------------
	//Atributos
	//-------------------------------------------------
	private ArrayList<Curso> CursosSugeridos; 
	private ArrayList<Curso> Homologables;//Curos por los qu se cambia(Nuevo pensum)
	private ArrayList<Curso> AHomologar;//Las que se pueden cambiar
	private String Carrera;
	


	//-------------------------------------------------
	//Constructor
	//-------------------------------------------------
	public Pensum(ArrayList<Curso> lstPensumSugerido) {
		CursosSugeridos= lstPensumSugerido;
	}
	//-------------------------------------------------
	//Metodos
	//-------------------------------------------------
	public ArrayList<Curso> darPensumSugerido(){
		return CursosSugeridos;
	}
	public ArrayList<Curso> darHomologables(){
		return Homologables;
	}
	
	public ArrayList<Curso> darAHomologar(){
		return AHomologar;
	}
	
	public String darCarrera() {
		return Carrera;
	}
	
	public void setCursosHomologables(ArrayList<Curso> pCHologables) {
		Homologables= pCHologables;
		
	}
	
	public void setCursosAHomologar(ArrayList<Curso> pCHologables) {
		AHomologar= pCHologables;
		
	}
	
	public void setNombreCarrera(String pNombre) {
		Carrera = pNombre;
	}
	
}
