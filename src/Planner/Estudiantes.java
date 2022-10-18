package Planner;

import java.util.*;


public class Estudiantes {

	
	
	//-------------------------------------------------------------
	//Atributos
	//-------------------------------------------------------------
	
	String nombre;
	String login;
	int codigo;
	ArrayList<Semestre> historicoSemestres = new ArrayList<Semestre>();
	Semestre semestreActual; 
	ArrayList<Curso> CursosAprobados;
	ArrayList<Plan> Planes = new ArrayList<Plan>();
	Plan Registro;
	Boolean candidatoAGrado;
	double pormedioAcumulado;
	ArrayList<Plan> planes;
	
	//-------------------------------------------------------------
	//Constructor
	//-------------------------------------------------------------
	
	public Estudiantes(String pNombre, String pLogin, int pCodigo, double pPromedio, ArrayList<Curso> pC_aproabdos) {
		nombre = pNombre;
		login = pLogin;
		codigo = pCodigo;
		pormedioAcumulado = pPromedio;
		CursosAprobados = pC_aproabdos; 
		
	}
	
	//-------------------------------------------------------------
	//Metodos
	//-------------------------------------------------------------
	
	public void setRegistro(Plan p) {
		Registro=p; 
	}
	public Plan darRegistro() {
		return Registro; 
	}
	
	public void validarCandidatoAGrado(boolean b){
		candidatoAGrado = b;
	}
	
	
	public boolean isCandidato() {
		return candidatoAGrado;
	}
	
	public void RegistarSemestreCursado(Semestre s) {
		historicoSemestres.add(s);
		}
	

	public double darPromedioAcumulado() {
		return pormedioAcumulado;
	}
	
	public int crearPlan(String pNombre, ArrayList<Curso> Cursos) {
		
		if (Planes.size()<=3) {
			Plan p = new Plan( pNombre,Cursos); 
			Planes.add(p);
			return Planes.size();
		}else {
			return -1;
		}
		
	}
	
	
	public void modificarPlan() {
		//TODO
	}
	
	public void cambiarNota(double pNota) {
		//Curso.cambiarNota(pNota);
	}
	
	
	
	//----------------------------------------------------------------
	public String darNombre() {
		return nombre;
	}
	
	public String darLogin() {
		return login;
	}
	
	public int darCodigo() {
		return codigo;
	}

	public Semestre darSemestre() {
		return semestreActual;
	}
	public ArrayList<Semestre> darHistoricoSemestres(){
		return historicoSemestres;
	}
	public ArrayList<Curso> darCursosAprobados(){
		return CursosAprobados;
	}
	public ArrayList<Plan> darPlanes(){
		return Planes;
	}
	public void eliminarPlan(Plan pPlan) {
		Planes.remove(pPlan);		
	}
	
	public void AnadirCursoArpobado(Curso curso) {
		System.out.print(curso.darNombre()+" Tal vez");
		if(!CursosAprobados.contains(curso) && curso.darNota()>=3) {
			CursosAprobados.add(curso);
			System.out.print(curso.darNombre());
		}
	}
	
	public void anadirSemestre(String pPeriodo ,ArrayList<Curso> Aprobados) {
		ArrayList<Curso> Reprobados = new ArrayList<Curso>();
		
		Iterator<Curso> iCurso = Aprobados.iterator();
		while(iCurso.hasNext()) {
			Curso cActual = iCurso.next();
			if( cActual.darNota()<3){
				Reprobados.add(cActual);
			}
			
		Semestre s = new Semestre(pPeriodo, Aprobados, Reprobados,historicoSemestres.size()+1);
		if (!historicoSemestres.contains(s)) {
		historicoSemestres.add(s);
		}else {
			System.out.println("Semestre repetido" + "   Clase estudiaqntres");
			
		}
	}}
	
}
