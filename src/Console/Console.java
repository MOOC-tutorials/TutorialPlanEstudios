package Console;
import java.io.BufferedReader;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import Planner.Administrativos;
import Planner.Curso;
import Planner.Estudiantes;
import Planner.Pensum;
import Planner.PlanDeEstudios;
import Planner.Semestre;
import Planner.Plan;


public class Console {
	
	File source = new File ("docs/Data_P1.txt");
	File estudiantes = new File ("docs/Estudiante.txt");
	File Admin = new File ("docs/Administrativos.txt");
	private PlanDeEstudios plan = new PlanDeEstudios(); 
	private Estudiantes estudianteActual; 
	
	public void setEstudiante(Estudiantes e1) {
		estudianteActual = e1;
	}
	
	public Estudiantes darEstudiante() {
		return estudianteActual;
	}
	
	//Carga los cursos al plan de estudioo
	public void cargarArchivos(File source, File estudiantes, File Admon) {
		try {
			plan.cargarInfoBanner(source);
			plan.cargarPensumSugerido(source);
			plan.CargarAdministrativo(Admin);
			plan.CargarEstudiante(estudiantes);
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	//-----------------------------------------------------------
	// Apclicaicon de Ejecución
	//-----------------------------------------------------------
	public void ejecutarAplicacion() 
	{
		System.out.println("***********************  Banner  ***********************\n");
		
		cargarArchivos(source, estudiantes, Admin);
		Show1();
			
	}

	public void Show1() {
		try
		{
			mostrarMenu();
			int opcion_seleccionada = Integer.parseInt(input("Por favor seleccione una opción"));
			if (opcion_seleccionada == 1)
				mostrarmenuEstudiantes1();
			else if (opcion_seleccionada == 2)
				mostrarAdministrativos1();
			
			else if (opcion_seleccionada == 0)
			{
				System.out.println("Saliendo de la aplicación ...");
			}
			else
			{
				System.out.println("Por favor seleccione una opción valida.");
				ejecutarAplicacion();
			}
		}
		catch (NumberFormatException e)
		{
			System.out.println("Debe seleccionar uno de los numeros de las opciones.");
			Show1();
		}
	}
	
	
	
	//-----------------------------------------------------------
	//Menus de Secundarios
	//-----------------------------------------------------------
	
	
	public void mostrarMenu()
	{
		System.out.println("\nOpciones de la aplicación\n");
		System.out.println("1. Estudiante");
		System.out.println("2. Administrativo");
		}
	

	
	public void mostrarmenuEstudiantes1()
	{
		System.out.println("\n************Bienvenido a Banner 2.0 revenge************\n");
		String codigoString = (input("Por favor Digite su codigo uniandes"));
		int codigo = Integer.parseInt(codigoString);
		Estudiantes e1;
		if (plan.getCodigo_Estudiantes().contains(codigo) ) {
			e1 = buscarEstudiantePorCodigo(codigo);
			System.out.println("\n");
			System.out.println("\nBienvenido " + e1.darNombre()+"\n");
			mostrarmenuEstudiantes( e1);
		}else {
			System.out.println("\n¡Vaya al parecer eres un nuevo estudiante!! (PrimerSemestre)");
			System.out.println("Por favor ingresa los siguientes datos: \n");
			String Nombre = (input("Nombre"));
			String Longin =(input("Login Uniandes"));
			plan.NuevoEstudiante(Nombre, Longin, codigo, 0, "None");
			plan.crearCarpeta(codigoString, "docs/Estudiantes/");
			registrarEstudianteEnDoc(Nombre,Longin,codigo);
			System.out.println("\nGracias :), Ya estas registrado \n");
			Estudiantes e2 = plan.getEstudiantes_Inscritos().get(plan.getEstudiantes_Inscritos().size() -1);
			System.out.println("\n");
			System.out.println("\nBienvenido " + Nombre+ "\n");
			mostrarmenuEstudiantes(e2);
		}	
	}
	
	
	public void mostrarmenuEstudiantes( Estudiantes e1) {
		System.out.println("===================================================================");
		System.out.println("                 Sistema de inscripcion de cursos                  ");
		System.out.println("===================================================================");
		System.out.println("¿Que deseas hacer?");
		System.out.println("1. Planear Horario");
		System.out.println("2. Registrar Horario");
		System.out.println("3. Verificar Candito a Grado");
		System.out.println("0. Menu principal");
		System.out.println("9. Salir");
		

			try
			{
				int opcion_seleccionada = Integer.parseInt(input("Por favor seleccione una opción"));
				if (opcion_seleccionada == 1)
					planearhorario(e1);
				else if (opcion_seleccionada == 2) {
					registrarcursos(e1);
					mostrarmenuEstudiantes(e1);}
				else if (opcion_seleccionada == 3) {
					CandidatoGrado(e1, plan.getPensumSugerido());
					mostrarmenuEstudiantes(e1);
					}
				
				else if (opcion_seleccionada == 9)
				{
					System.out.println("Saliendo de la aplicación ...");
				}else if(opcion_seleccionada == 0){
					Show1();
				}else
				{
					System.out.println("Por favor seleccione una opción valida.");
					mostrarmenuEstudiantes(e1);
				}
			}
			catch (NumberFormatException e)
			{
				System.out.println("Debe seleccionar uno de los numeros de las opciones.");
				mostrarmenuEstudiantes(e1);
			}
		}
		
	
	
	private void mostrarmenuAdministrativos(Administrativos a1) {
		System.out.println("\n******************************************************");
		System.out.println("                     MENU ADMINISTRATIVOS               ");
		System.out.println("******************************************************");
		System.out.println("Bienvenido");
		System.out.println("¿Que desea hacer? \n");
		System.out.println("1. Actualizar info estudiante");
		System.out.println("2. Imprimir reporte de notas");
		System.out.println("3. Validar Estudiante Candidato a grado");
		try
		{
			int opcion_seleccionada = Integer.parseInt(input("Por favor seleccione una opción"));
			if (opcion_seleccionada == 1) {
				int codigo = Integer.parseInt(input("Por favor Digite su el codigo del estudiante a buscar"));
				Estudiantes e1 = buscarEstudiantePorCodigo(codigo);		
				
				System.out.println("1. Actualizacion de Notas ");
				System.out.println("2. Actualizacion Manual");
				int opcion = Integer.parseInt(input("Por favor seleccione una opción"));
				if (opcion==1) {
					String Ruta = input("Ingrese La ruta del Archivo ");
					ActualizarEstudiante(e1,Ruta,a1); 
				}else if(opcion==2){
					System.out.println("Estamos trabajando en ello ... ");
				}}
			else if (opcion_seleccionada == 2) {
				int codigo = Integer.parseInt(input("Por favor Digite su el codigo del estudiante a buscar"));
				Estudiantes e1 = buscarEstudiantePorCodigo(codigo);	
				ReporteNotas(e1,a1);}
			else if (opcion_seleccionada == 3) {
				int codigo = Integer.parseInt(input("Por favor Digite su el codigo del estudiante a buscar"));
				Estudiantes e1 = buscarEstudiantePorCodigo(codigo);			
				CandidatoGradoParaAdmin(e1,a1, plan.getPensumSugerido().darPensumSugerido());
			}
			
			else if (opcion_seleccionada == 9)
			{
				System.out.println("Saliendo de la aplicación ...");
			}else if(opcion_seleccionada == 0){
				Show1();
			}else
			{
				System.out.println("Por favor seleccione una opción valida.");
				mostrarmenuAdministrativos(a1);
			}
		}
		catch (NumberFormatException e)
		{
			System.out.println("Debe seleccionar uno de los numeros de las opciones.");
			mostrarmenuAdministrativos(a1);
		
		
	}}
	
	
	
	public void mostrarAdministrativos1()
	{
		System.out.println("\n************Bienvenido a Banner 2.0 revenge************\n");
		int codigo = Integer.parseInt(input("Por favor Digite su codigo de administrativo"));
	
		Iterator <Administrativos> icode = plan.getAdministrativosList().iterator();
		if (plan.getCodigo_Administrativos().contains(codigo) ) {
		while (icode.hasNext()) {
			Administrativos i = icode.next();
			if (i.darCodigo() == (codigo)) {
				System.out.println("\n");
				System.out.println("\nBienvenido " + i.darNombre()+"\n");
				mostrarmenuAdministrativos(i);
				
			}
		}}else {
			System.out.println("\nNo esta autorizado para esta accion ");
			
		}}
	
	
	
	
	

	//-----------------------------------------------------------
	//Helpers Menus de Secundarios
	//-----------------------------------------------------------
	
	public Estudiantes buscarEstudiantePorCodigo(int Codigo) {
		ArrayList<Integer> CodigosLista = plan.getCodigo_Estudiantes();
		ArrayList<Estudiantes> Lst = plan.getEstudiantes_Inscritos();
		Iterator <Estudiantes> icode = Lst.iterator();
		if (CodigosLista.contains(Codigo) ) {
		while (icode.hasNext()) {
			Estudiantes i = icode.next();
			System.out.println(i.darCodigo() + " Codigo ");
			if (i.darCodigo() == (Codigo)) {
				return i; 
			}
		}}
		return null;}
	
	//Imprime un reporte de Notas Por Semestre
	// Con cada curso y la nota que obtuno en cada semestre visto
	
	private void ReporteNotas(Estudiantes e1, Administrativos a1) {
		double Promedio_Acumulado = e1.darPromedioAcumulado();
		
		
		
		System.out.println("---------------------------------------------------------------------------------------");
		System.out.println(" 		                            Reporte de Notas                     ");
		System.out.println("---------------------------------------------------------------------------------------");
		System.out.println(" 		                                         Estudiente: "+e1.darNombre()+" coidgo: " +e1.darCodigo());
		System.out.println("");
		System.out.println("Promedio Acumulado: "+ Promedio_Acumulado);
		
		
			int numSemestre=1;
			ArrayList<Semestre> historicoSemestre = e1.darHistoricoSemestres();
			ArrayList<Curso> Cursos_Perdidos = new ArrayList<Curso>();
			
			if (historicoSemestre.size()>1){
			Iterator <Semestre> semestre = historicoSemestre.iterator();
			while(semestre.hasNext()) {
				Semestre S = semestre.next();
				System.out.println("");
				System.out.println("			Semestre Numero "+ numSemestre);
				numSemestre++; 
				ArrayList<Curso> Cursos_Aprobados = S.darAprobado(); 
				Iterator <Curso> CurosA = S.darInscritos().iterator();
				
				System.out.println("");
				System.out.println("__Cursos Inscritos: ");
				while(CurosA.hasNext()) {
					Curso cursoImprimir = CurosA.next();
					System.out.println(" - "+cursoImprimir.darNombre() +" Nota: " +cursoImprimir.darNota() );
					if(!(Cursos_Aprobados.contains(cursoImprimir))) {
						Cursos_Perdidos.add(cursoImprimir); 
					}}
				}if (Cursos_Perdidos.size()==0) {
					System.out.println("NO hay Cursos Perdidos");
				}else if (Cursos_Perdidos.size()==1){
					System.out.println("Solo ha perdido Un curos: " +Cursos_Perdidos.get(0).darNombre());
				}else {
					Iterator <Curso> CurosPer = Cursos_Perdidos.iterator();
					System.out.println("");
					System.out.println("__Cursos Perdidos: ");
					while(CurosPer.hasNext()) {
						Curso cursoImprimir2 = CurosPer.next();
						System.out.println(" - "+cursoImprimir2.darNombre());
				}}
				
				
				mostrarmenuAdministrativos(a1);
				} else if (historicoSemestre.size()==1){
				Iterator <Curso> CurosA = e1.darHistoricoSemestres().get(0).darAprobado().iterator();
				ArrayList<Curso> Cursos_Aprobados = e1.darSemestre().darAprobado(); 
				System.out.println("");
				System.out.println("__Cursos Inscritos: ");
				while(CurosA.hasNext()) {
					Curso cursoImprimir = CurosA.next();
					System.out.println(" - "+cursoImprimir.darNombre() +" Nota: " +cursoImprimir.darNota() );
					if(!(Cursos_Aprobados.contains(cursoImprimir))) {
						Cursos_Perdidos.add(cursoImprimir); 
					}}
				if (Cursos_Perdidos.size()==0) {
					System.out.println("NO hay Cursos Perdidos");
				}else if (Cursos_Perdidos.size()==1){
					System.out.println("Solo ha perdido Un curos: " +Cursos_Perdidos.get(0).darNombre());
				}else {
					Iterator <Curso> CurosPer = Cursos_Perdidos.iterator();
					System.out.println("");
					System.out.println("__Cursos Perdidos: ");
					while(CurosPer.hasNext()) {
						Curso cursoImprimir2 = CurosPer.next();
						System.out.println(" - "+cursoImprimir2.darNombre());

				
			}}
				mostrarmenuAdministrativos(a1);
		}else{
			System.out.println("Estudaintre sin Registros ");
			System.out.println("Posible error esta en primer semestre");
			mostrarmenuAdministrativos(a1);}
	}



	private void ActualizarEstudiante(Estudiantes e1, String Ruta, Administrativos a1) {
		ArrayList<Curso> Cursos_Aprobados = new ArrayList<Curso>();
		try {
			File source = new File(Ruta);
			Scanner fScn = new Scanner(source);
			String data;
	
			while( fScn.hasNextLine() ){
				data = fScn.nextLine();
				
				// Se Actualizan Datos del Estudiante: 
				//Curso Aprobado; Nota;
				
				String[] token = data.split(";");
				Curso CursoI = plan.convertidor_Curso(token[0]);
				double Nota = Double.valueOf(token[1]);
				CursoI.cambiarNota(Nota);
				Cursos_Aprobados.add(CursoI);
				}fScn.close();
				//e1.RegistarSemestre(Cursos_Aprobados, Cursos_Aprobados);
				System.out.println("Se ha actualizado la informacion con Exito");
				System.out.println(e1.darHistoricoSemestres().size());
		}catch(IOException e) {
			e.printStackTrace();}
		mostrarmenuAdministrativos(a1);
		
	}
	public ArrayList<Curso> CandidatoGrado(Estudiantes e1, Pensum PensumSugerido) {
		int i=0; 

		ArrayList<Curso> CursoAprobados = e1.darCursosAprobados();
		ArrayList<Curso> Faltantes = new ArrayList<Curso>();
		
		Iterator <Curso> Curso = PensumSugerido.darPensumSugerido().iterator();
		while (Curso.hasNext()) {
			Curso CActual = Curso.next();
			if (CursoAprobados.contains(CActual)) {
				i=i+1;
				}else {
					Iterator <Curso> CursoHomologable = PensumSugerido.darHomologables().iterator();
					while (CursoHomologable.hasNext()) {
						Curso CHActual = CursoHomologable.next();
						if (CursoAprobados.contains(CHActual)) {
							i=i+1;
							//Aca se puede hacer el cambio, si se quiere, para que se anada el nuevo curso en lugar del anterior
							e1.darCursosAprobados().add(CHActual);
						}else {
							Faltantes.add(CActual);
						}
					}
					
					}
				}if (i>=PensumSugerido.darPensumSugerido().size()) {
					e1.validarCandidatoAGrado(true);
					System.out.println("\nEl estudiante " +e1.darNombre()+"  es Candidato A grado");
					Iterator <Curso> Curso1 = CursoAprobados.iterator();
					while (Curso1.hasNext()) {
						Curso CActual1 = Curso1.next();
						System.out.println("	▬ " + CActual1.darNombre()+ CActual1.darNota()+" HOLA");
					}
					String codigoString = String.valueOf(e1.darCodigo());
					crearyEscribirArchivo(codigoString ,CursoAprobados,"docs/Estudiantes/", "/CursosAprobados");
					crearyEscribirArchivo(codigoString ,CursoAprobados,"docs/Estudiantes/", "/CursosFaltantes");
			}else {
				e1.validarCandidatoAGrado(false);
				System.out.println("\nEl estudiante " +e1.darNombre()+" NO es Candidato A grado");
				System.out.println("Ya curso los siguientes cursos: ");
				Iterator <Curso> Curso2 = CursoAprobados.iterator();
				while (Curso2.hasNext()) {
					Curso CActual2 = Curso2.next();
					System.out.println("	▬ " + CActual2.darNombre()+ CActual2.darNota());
				}
				System.out.println("\nTiene Que cursar los siguientes cursos: ");
				Iterator <Curso> pos = Faltantes.iterator();
				while (pos.hasNext()) {
					Curso posMostrar = pos.next();
					System.out.println("	▬ " + (posMostrar).darNombre());
					}
				String codigoString = String.valueOf(e1.darCodigo());
				crearyEscribirArchivo(codigoString ,CursoAprobados,"docs/Estudiantes/", "/CursosAprobados");
				crearyEscribirArchivo(codigoString ,CursoAprobados,"docs/Estudiantes/", "/CursosFaltantes");
				}
				return Faltantes;
		}

		
	public void CandidatoGradoParaAdmin(Estudiantes e1, Administrativos a1, ArrayList<Curso> PensumSugerido) {
		int i=0; 
		int f=0; 
		ArrayList<Curso> CursoAprobados = e1.darCursosAprobados();
		ArrayList<Curso> Faltantes = new ArrayList<Curso>();
		
		Iterator <Curso> Curso = PensumSugerido.iterator();
		while (Curso.hasNext()) {
			Curso CActual = Curso.next();
			if (CursoAprobados.contains(CActual)) {
				i=i+1;
				}else {
					Faltantes.add(CActual);
					}
				}if (i>=PensumSugerido.size()) {
					System.out.println("\nEl estudiante " +e1.darNombre()+"  es Candidato A grado");
					Iterator <Curso> Curso1 = CursoAprobados.iterator();
					while (Curso1.hasNext()) {
						Curso CActual1 = Curso1.next();
						System.out.println("	▬ " + CActual1.darNombre());
					}
					String codigoString = String.valueOf(e1.darCodigo());
					crearyEscribirArchivoAdmin(String.valueOf(a1.darCodigo()), codigoString ,CursoAprobados,"docs/Administrativos/", "/CursosAprobados");
					crearyEscribirArchivoAdmin(String.valueOf(a1.darCodigo()) ,codigoString, CursoAprobados,"docs/Administrativos/", "/CursosFaltantes");
					mostrarmenuAdministrativos(a1);
			}else {
				System.out.println("\nEl estudiante " +e1.darNombre()+" NO es Candidato A grado");
				System.out.println("Ya curso los siguientes cursos: ");
				Iterator <Curso> Curso2 = CursoAprobados.iterator();
				while (Curso2.hasNext()) {
					Curso CActual2 = Curso2.next();
					System.out.println("	▬ " + CActual2.darNombre());
				}
				System.out.println("\nTiene Que cursar los siguientes cursos: ");
				Iterator <Curso> pos = Faltantes.iterator();
				while (pos.hasNext()) {
					Curso posMostrar = pos.next();
					System.out.println("	▬ " + (posMostrar).darNombre());
					}
				String codigoString = String.valueOf(e1.darCodigo());
				crearyEscribirArchivoAdmin(String.valueOf(a1.darCodigo()), codigoString ,CursoAprobados,"docs/Administrativos/", "/CursosAprobados");
				crearyEscribirArchivoAdmin(String.valueOf(a1.darCodigo()) ,codigoString, CursoAprobados,"docs/Administrativos/", "/CursosFaltantes");
				mostrarmenuAdministrativos(a1);
				}
		}
	
	

		
		

	
		


//----------------------------------------------------------------------
//Menus de reguistro Y Planes
//----------------------------------------------------------------------
	
	public void registrarcursos( Estudiantes e1) {
		System.out.println("------------------------------------------------------");
		System.out.println("                    Registar cursos                   ");
		System.out.println("------------------------------------------------------");
		System.out.println("\n Curos disponibles: \n");
		Iterator <Curso> icode = plan.getCursosCargados().iterator();
		while (icode.hasNext()) {
			Curso i = icode.next();
			System.out.printf("    ► "+ i.darNombre()+  "\n");}
		
		
		if (e1.darRegistro()==null) {
			if (e1.darPromedioAcumulado() < 4) {
				double creditos = 20 ;
				ArrayList<Curso> CInscritos = new ArrayList<Curso>();
				ArrayList<Curso> Correquisitos = new ArrayList<Curso>();	
				ArrayList<Curso> Aeliminar = new ArrayList<Curso>();
				System.out.println("Recuerda que tienes 20,5 creditos max");
				System.out.println("Que materia deses inscribir");
				String periodo = input("Periodo Actual(debe ser de la forma 2020-1");
				verificadorDerequisitos(creditos, e1, CInscritos, Correquisitos, Aeliminar);
				//e1.RegistarSemestre(CInscritos, Correquisitos);
				Plan p = new Plan("Registro", CInscritos);
				e1.setRegistro(p);
				inscribirAPlan1(e1.darCodigo(), CInscritos);
				mostrarmenuEstudiantes(e1); 
				
				}else {
				
					double creditos = 25 ;
					ArrayList<Curso> CInscritos = new ArrayList<Curso>();
					ArrayList<Curso> Correquisitos = new ArrayList<Curso>();
					ArrayList<Curso> Aeliminar = new ArrayList<Curso>();
					System.out.println("Recuerda que tienes 25 creditos max");
					System.out.println("EL proceso Finaliza cuando oprimes 0 o se acaban los creditos");
					String periodo = input("Periodo Actual(debe ser de la forma 2020-1");
					verificadorDerequisitos(creditos, e1, CInscritos, Correquisitos, Aeliminar);
					//e1.RegistarSemestre(CInscritos, Correquisitos);
					Plan p = new Plan("Registro", CInscritos);
					e1.setRegistro(p);
					inscribirAPlan1(e1.darCodigo(), CInscritos);
					mostrarmenuEstudiantes(e1); 
					
				}}else {
					EditorDeRegistro( e1.darRegistro(), e1); 
					mostrarmenuEstudiantes(e1); 
			}
		
	}
	
	
	

	private void planearhorario(Estudiantes e1) {
		System.out.println("------------------------------------------------------");
		System.out.println("                   Planear Horario                    ");
		System.out.println("------------------------------------------------------");
		System.out.println("\n Curos disponibles: \n");
		if (e1.darPlanes().size()<=3) {
		Iterator <Curso> icode = plan.getCursosCargados().iterator();
		while (icode.hasNext()) {
			Curso i = icode.next();
			System.out.printf("    ► "+ i.darNombre()+"\n");}

		if (e1.darPromedioAcumulado() < 4) {
			double creditos = 20 ;
			
			ArrayList<Curso> CInscritos = new ArrayList<Curso>();
			ArrayList<Curso> Correquisitos = new ArrayList<Curso>();
			ArrayList<Curso> Aeliminar = new ArrayList<Curso>();
			String NombrePlan = (input("En primer lugar, Como se llamara este plan"));
			System.out.println("\nRecuerda que tienes 20,5 creditos max");
			System.out.println("Que materia deses inscribir");
			ArrayList<Curso> CursosPlaneados = verificadorDerequisitos(creditos, e1, CInscritos, Correquisitos, Aeliminar);
			int i = e1.crearPlan(NombrePlan, CInscritos);			
			if (i==-1) {
				planearHorarioMenu(e1);
				
			}else {
				inscribirAPlan(e1.darCodigo(), CInscritos, e1.darPlanes().size());
				System.out.println("   \nFelicidades su Plan "+ NombrePlan +" Ha sido creado");
				planearHorarioMenu(e1);
			}
				
		}else {
		
			double creditos = 25 ;
			ArrayList<Curso> CInscritos = new ArrayList<Curso>();
			ArrayList<Curso> Correquisitos = new ArrayList<Curso>();
			ArrayList<Curso> Aeliminar = new ArrayList<Curso>();
			String NombrePlan = (input("En primer lugar, Como se llamara este plan"));
			System.out.println("\nRecuerda que tienes 25 creditos max");
			System.out.println("Que materia deses inscribir");
			ArrayList<Curso> CursosPlaneados = new ArrayList<Curso>();
			CursosPlaneados = verificadorDerequisitos(creditos, e1, CInscritos, Correquisitos, Aeliminar);
			int i = e1.crearPlan(NombrePlan, CInscritos);
			
			if (i==-1) {
				System.out.println("\nHa inscrito el numero maximo de planes");
				planearHorarioMenu(e1);
				
			}else {
				inscribirAPlan(e1.darCodigo(), CInscritos, e1.darPlanes().size());
				System.out.println("   \nFelicidades su Plan "+ NombrePlan +" Ha sido creado");
				planearHorarioMenu(e1);
			}
		}}else{
			int numPlan =1; 
			ArrayList<Plan> PlanesActuales = new ArrayList<Plan>(); 
			System.out.println("Ya tiene tres Planes creados ");
			System.out.println("Desea editar alguno ");
			Iterator <Plan> planIt = e1.darPlanes().iterator();
			while(planIt.hasNext()) {
				Plan plan = planIt.next();
				PlanesActuales.add(plan);
				System.out.println(numPlan+". "+plan.darNombre());
				numPlan++;}
			System.out.println("9. Salir ");
			System.out.println("0. Menu Principal ");
			try
			{
				int opcion_seleccionada = Integer.parseInt(input("Por favor seleccione una opción"));
				if (opcion_seleccionada == 1)
					EditorDeHorario(PlanesActuales.get(0),e1);
				else if (opcion_seleccionada == 2)
					EditorDeHorario(PlanesActuales.get(1),e1);
				else if (opcion_seleccionada == 3)
					EditorDeHorario(PlanesActuales.get(2),e1);
				
				else if (opcion_seleccionada ==9 )
				{
					System.out.println("Saliendo de la aplicación ...");
				}else if(opcion_seleccionada == 0){
					Show1();
				}else
				{
					System.out.println("Por favor seleccione una opción valida.");
					mostrarmenuEstudiantes(e1);
				}
			}
			catch (NumberFormatException e)
			{
				System.out.println("Debe seleccionar uno de los numeros de las opciones.");
				mostrarmenuEstudiantes(e1);
			}
			
		}	
	}
	
	
	
	public void EditorDeHorario(Plan plan, Estudiantes e1 ) {
		int i = 0; 
		
		System.out.println("Ya tiene los siguientes cursos Inscritos");
		ArrayList<Curso> CInscritos = new ArrayList<Curso>();
		ArrayList<Curso> Correquisitos = new ArrayList<Curso>();
		ArrayList<Curso> Aeliminar = new ArrayList<Curso>();
		Iterator <Curso> c = plan.darCursos().iterator();
		while(c.hasNext()) {
			Curso CursoImprimir = c.next();
			CInscritos.add(CursoImprimir);
			i=i+CursoImprimir.darCreditos();			
			System.out.println(" - "+CursoImprimir.darNombre());
			}System.out.println("Y ha gastado " + i+ " creditos" );
			verificadorDerequisitos(25-i,e1, CInscritos, Correquisitos, Aeliminar );
			inscribirAPlan(e1.darCodigo(), CInscritos, e1.darPlanes().size());
			planearHorarioMenu(e1);
		
	}
	
	public void EditorDeRegistro(Plan plan, Estudiantes e1 ) {
		int i = 0; 
		
		System.out.println("Ya tiene los siguientes cursos Inscritos");
		ArrayList<Curso> CInscritos = new ArrayList<Curso>();
		ArrayList<Curso> Correquisitos = new ArrayList<Curso>();
		ArrayList<Curso> Aeliminar = new ArrayList<Curso>();
		Iterator <Curso> c = plan.darCursos().iterator();
		while(c.hasNext()) {
			Curso CursoImprimir = c.next();
			CInscritos.add(CursoImprimir);
			i=i+CursoImprimir.darCreditos();			
			System.out.println(" - "+CursoImprimir.darNombre());
			}System.out.println("Y ha gastado " + i+ " creditos" );
			verificadorDerequisitos(25-i,e1, CInscritos, Correquisitos, Aeliminar );
			inscribirAPlan1(e1.darCodigo(), CInscritos);
		
	}


	public void planearHorarioMenu(Estudiantes e1) {
		System.out.println("\n------------------------------------------------------");
		System.out.println("                   Planear Horario                    ");
		System.out.println("------------------------------------------------------");
		System.out.println(" 1. Planear Nuevo Horario");
		System.out.println(" 2. Editar Plan");
		System.out.println(" 3. Menu Anterior ");
		System.out.println(" 9. Salir ");
		System.out.println(" 0. Menu Principal ");
		try
		{
			int opcion_seleccionada = Integer.parseInt(input("Por favor seleccione una opción"));
			if (opcion_seleccionada == 1)
				planearhorario(e1);
			else if (opcion_seleccionada == 2)
				EditarPlan(e1);
			
			else if (opcion_seleccionada == 9)
			{
				System.out.println("Saliendo de la aplicación ...");
			}else if(opcion_seleccionada==3) {
				mostrarmenuEstudiantes(e1);
			} else if(opcion_seleccionada == 0){
				Show1();
			}else
			{
				System.out.println("Por favor seleccione una opción valida.");
				planearHorarioMenu(e1);
			}
		}
		catch (NumberFormatException e)
		{
			System.out.println("Debe seleccionar uno de los numeros de las opciones.");
			planearHorarioMenu(e1);
		}
	}

	
	
	

	
	
	public void EditarPlan(Estudiantes e1) {
		if (e1.darPlanes().size()==0) {
			System.out.println("No hay ningun plan creado .");
		}else if(e1.darPlanes().size()==1)  {
			System.out.println("\nSolo hay un plan creado .");
			System.out.println("Se editara el plan                                    " + e1.darPlanes().get(0).darNombre());
			EditorDeHorario(e1.darPlanes().get(0),e1);
			
		}else {
			int numPlan =1; 
			ArrayList<Plan> PlanesActuales = new ArrayList<Plan>(); 
			System.out.println("Ya tiene tres Planes creados ");
			System.out.println("Desea editar alguno ");
			Iterator <Plan> planIt = e1.darPlanes().iterator();
			while(planIt.hasNext()) {
				Plan plan = planIt.next();
				PlanesActuales.add(plan);
				System.out.println(numPlan+". "+plan.darNombre());
				numPlan++;}
			System.out.println("9. Salir ");
			System.out.println("0. Menu Principal ");
			try
			{
				int opcion_seleccionada = Integer.parseInt(input("Por favor seleccione una opción"));
				if (opcion_seleccionada == 1)
					EditorDeHorario(PlanesActuales.get(0),e1);
				else if (opcion_seleccionada == 2)
					EditorDeHorario(PlanesActuales.get(1),e1);
				else if (opcion_seleccionada == 3)
					EditorDeHorario(PlanesActuales.get(2),e1);
				
				else if (opcion_seleccionada ==9 )
				{
					System.out.println("Saliendo de la aplicación ...");
				}else if(opcion_seleccionada == 0){
					Show1();
				}else
				{
					System.out.println("Por favor seleccione una opción valida.");
					mostrarmenuEstudiantes(e1);
				}
			}
			catch (NumberFormatException e)
			{
				System.out.println("Debe seleccionar uno de los numeros de las opciones.");
				mostrarmenuEstudiantes(e1);
			}
			
			}
		}
	
	
	//-------------------------------------------------------------------------------------
	//Verifica que el curso o curos a inscribir cumplan con las restriciones.
	//-------------------------------------------------------------------------------------
	public ArrayList<Curso> verificadorDerequisitos(double creditos, Estudiantes e1, ArrayList<Curso> CInscritos, ArrayList<Curso> Correquisitos, ArrayList<Curso> Aeliminar) {
		
		System.out.println("\nPara Guardar y finalizar presiona 0");
		System.out.println("Para Eliminar agrega un # al final del Nombre\n");
		String CursoAInscribir = (input("Por favor seleccione una opción(Solo se puede Inscribir de a un curso)Puede con el nombre o Codigo"));
		
	
		CursoAInscribir = CursoAInscribir.trim(); 
		if (CursoAInscribir.endsWith("#")) {
			CursoAInscribir = CursoAInscribir.replace("#", ""); 
			Curso CursoI = plan.convertidor_Curso(CursoAInscribir);
			if (CInscritos.contains(CursoI)) {
			CInscritos.remove(CInscritos.indexOf(CursoI));
			System.out.println("\nHas Eliminado "+ CursoI.darNombre());
			verificadorDerequisitos(creditos, e1, CInscritos, Correquisitos, Aeliminar);
			return CInscritos; 
			}else {
				System.out.println("\nNo has Inscrito "+ CursoI.darNombre());
				return CInscritos;
			}
			
		}else {
		Curso CursoI = plan.convertidor_Curso(CursoAInscribir);
		
		if (CursoAInscribir.equals("0") && CInscritos.size()!=0){
			VerificadorCorrequisitos(CInscritos, Correquisitos, Aeliminar);
			return CInscritos;
			
		}else if (e1.darCursosAprobados().contains(CursoI) ) {
			System.out.println("El curos "+ CursoI.darNombre() + " ya ha sido aprobado");
			
		}else if(CInscritos.contains(CursoI)){
			System.out.println("El curos "+ CursoI.darNombre() + " ya ha sido Inscrito");
		}else {
			//Verifica que el curso existe
			if (plan.getCursosCargados().contains(CursoI)) {
				
				//Verifica Materias Sin requisitos
				if(CursoI.darCoRequ().equals("ninguno") && CursoI.darPreRequ().equals("ninguno")) {
					creditos = creditos - CursoI.darCreditos();
					CInscritos.add(CursoI);
					System.out.println("\n::::::::::: Felicidades ha inscrito " + CInscritos.get(CInscritos.size()-1).darNombre()+" :::::::::::");
					
				}
				//Verifica Materias con Prerequisitos
				else if(CursoI.darCoRequ().equals("ninguno") && !(CursoI.darPreRequ().equals("ninguno")))  {
					Iterator <Curso> Cprereq = plan.convertidor_string_Curso(CursoI.darPreRequ()).iterator();
					while (Cprereq.hasNext()) {
						Curso PrerequisitoActual = Cprereq.next();
						if(!(e1.darCursosAprobados().contains(PrerequisitoActual))) {
							System.out.println("\nPara Inscribir " + CursoI.darNombre()+ " es necesario aprobar primero "+ CursoI.darPreRequ()+"("+ PrerequisitoActual.darNombre()+")");
							
						}else {
							creditos = creditos - CursoI.darCreditos();
							CInscritos.add(CursoI);
							System.out.println("\n::::::::::: Felicidades ha inscrito " + CursoI.darNombre()+" :::::::::::");
							}}		
				}
				//Verifica Materias con Correquisitos
				else if(!(CursoI.darCoRequ().equals("ninguno")) && CursoI.darPreRequ().equals("ninguno")) {
					
					//Inscribe el curos, al final de la inscripcion se validan los correquisitos, si no los cumple se eliminan y se informa al usuario. 
					System.out.println("Recurda que este curso tiene como corequisito " + CursoI.darCoRequ());
					Iterator <Curso> Ccoreq = plan.convertidor_string_Curso(CursoI.darCoRequ()).iterator();
					
					creditos = creditos - CursoI.darCreditos();
					while (Ccoreq.hasNext()) {
						Curso CorrequisitoActual = Ccoreq.next();
						Correquisitos.add(CorrequisitoActual);
						Aeliminar.add(CursoI);
						System.out.println(CursoI.darNombre());
						CInscritos.add(CursoI);
						} 
				}
				//Verifica Materias con Correquisitos y Prerrequisitos
				else {
					System.out.println("Correquisito y Prerequisito Al tiempo");
					Iterator <Curso> Cprereq = plan.convertidor_string_Curso(CursoI.darPreRequ()).iterator();
				
					while (Cprereq.hasNext()) {
						Curso PrerequisitoActual = Cprereq.next();
						if(!(e1.darCursosAprobados().contains(PrerequisitoActual))) {
							System.out.println("\nPara Inscribir " + CursoI.darNombre()+ " es necesario aprobar primero "+ CursoI.darPreRequ()+"("+ PrerequisitoActual.darNombre()+")");
			
						}else {
						System.out.println("Recurda qu este curso tiene como corequisito " + CursoI.darCoRequ());
						Iterator <Curso> Ccoreq = plan.convertidor_string_Curso(CursoI.darCoRequ()).iterator();
						creditos = creditos - CursoI.darCreditos();
						while (Ccoreq.hasNext()) {
							Curso CorrequisitoActual = Ccoreq.next();
							Correquisitos.add(CorrequisitoActual);
							Aeliminar.add(CursoI);
							System.out.println(CursoI.darNombre());
							CInscritos.add(CursoI);}
							}}
						}
				}else{System.out.println("\nCurso No encontrado\n");}
			
			}if (creditos>=0) {
		System.out.println("\nTienes " + creditos + " Creditos");
		System.out.println("Tienes " + CInscritos.size() + " cursos Inscritos");
		verificadorDerequisitos(creditos, e1, CInscritos, Correquisitos, Aeliminar);
		
		
	}else {
		return CInscritos;
		}
		
	
	return CInscritos;
}}

	//Funcion de ayuda para la verificación
	public ArrayList<Curso> VerificadorCorrequisitos(ArrayList<Curso> CInscritos, ArrayList<Curso> Correquisitos, ArrayList<Curso> Aeliminar ){
		int i = 0; 
		String Eliminado = " ";
		Iterator <Curso> Correc = Correquisitos.iterator();
		while (Correc.hasNext()) {
			Curso CorrecActual = Correc.next();
		if (CInscritos.contains(CorrecActual)) {
			i=i+1;}
		}
		
		if (i==Correquisitos.size()) {
			System.out.println("         Felicidades todos tus cursos se han registrado con extio         ");
			return CInscritos;
		}else {
			if (Aeliminar.size()>1) {
			Iterator <Curso> PosEliminar = Aeliminar.iterator();
			while (PosEliminar.hasNext()) {
				Curso Eliminar = PosEliminar.next();
				Eliminado = Eliminado +" " + Eliminar.darNombre();
				CInscritos.remove(CInscritos.indexOf(Eliminar));
				
			}
			System.out.println("\nHaz Inscrito " + CInscritos.size() + " cursos ");
			System.out.println("Los cursos  "+ Eliminado + "han sido eliminados No cumplian los requisitos" );
			return CInscritos;
			}else {
			System.out.println("A eliminar" + Aeliminar.get(0));
			Eliminado = Aeliminar.get(0).darNombre();
			
			CInscritos.remove(CInscritos.indexOf(Aeliminar.get(0)));
		System.out.println("\nHaz Inscrito " + CInscritos.size() + " cursos ");
		System.out.println("El curso "+ Eliminado + " No cumplia los requisitos" );
		return CInscritos;
		}}
	}

	/**
	 * Metodo Reusado Taller0
	 * Este metodo sirve para imprimir un mensaje en la consola pidiÃ©ndole
	 * informaciÃ³n al usuario y luego leer lo que escriba el usuario.
	 * 
	 * @param mensaje El mensaje que se le mostrarÃ¡ al usuario
	 * @return La cadena de caracteres que el usuario escriba como respuesta.
	 */
	public String input(String mensaje)
	{
		try
		{
			System.out.print(mensaje + ": ");
			BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
			return reader.readLine();
		}
		catch (IOException e)
		{
			System.out.println("Error leyendo de la consola");
			e.printStackTrace();
		}
		return null;
	}
	
	
	
	
	//_____________________________________________
	//Manejo de .TXT
	//_____________________________________________
	public void inscribirAPlan(int codigo, ArrayList<Curso> cursos,int planesExistentes ) {
		try {

			int numcrear= planesExistentes;
			String crear = "docs/Estudiantes/"+ codigo +"/Plan" + numcrear +".txt"; 
			File archivo = new File(crear);
			archivo.delete();
			
			archivo.createNewFile();
			
			FileWriter fstream =new FileWriter(archivo, true);
			BufferedWriter out = new BufferedWriter(fstream);

			System.out.println("");
			System.out.println("---------------------------------------------");
			System.out.println("Cursos Escogidos:");
			System.out.println("");


			for (int i = 0; i < cursos.size(); i++) {

				Curso actual = cursos.get(i);
				System.out.println(actual.darNombre());

				//este if es para manejar el caso en que es la ultima linea, evita que se cree una linea en blanco al final, esto evita errores en la lectura de planes existentes
				if(i+1==cursos.size()) {
					out.write(actual.darNombre()+";"+actual.darCodigo()+";"+actual.darPreRequ()+";"+actual.darCoRequ()+";"+actual.darCreditos()+";"+actual.darTipo()+";"+ actual.darciclo());
				}
				else {
					out.write(actual.darNombre()+";"+actual.darCodigo()+";"+actual.darPreRequ()+";"+actual.darCoRequ()+";"+actual.darCreditos()+";"+actual.darTipo()+";"+ actual.darciclo()+"\n");
				}
			}
			out.close();


		} catch (IOException e) {
			e.printStackTrace();
		}

	}
	
	public void inscribirAPlan1(int codigo, ArrayList<Curso> cursos ) {
		try {

			String crear = "docs/Estudiantes/"+ codigo +"/Registo.txt"; 
			File archivo = new File(crear);
			archivo.delete();
			
			archivo.createNewFile();
			
			FileWriter fstream =new FileWriter(archivo, true);
			BufferedWriter out = new BufferedWriter(fstream);

			System.out.println("");
			System.out.println("---------------------------------------------");
			System.out.println("Cursos Escogidos:");
			System.out.println("");


			for (int i = 0; i < cursos.size(); i++) {

				Curso actual = cursos.get(i);
				System.out.println(actual.darNombre());

				//este if es para manejar el caso en que es la ultima linea, evita que se cree una linea en blanco al final, esto evita errores en la lectura de planes existentes
				if(i+1==cursos.size()) {
					out.write(actual.darNombre()+";"+actual.darCodigo()+";"+actual.darPreRequ()+";"+actual.darCoRequ()+";"+actual.darCreditos()+";"+actual.darTipo()+";"+ actual.darciclo());
				}
				else {
					out.write(actual.darNombre()+";"+actual.darCodigo()+";"+actual.darPreRequ()+";"+actual.darCoRequ()+";"+actual.darCreditos()+";"+actual.darTipo()+";"+ actual.darciclo()+"\n");
				}
			}
			out.close();


		} catch (IOException e) {
			e.printStackTrace();
		}

	}
	
	public void crearyEscribirArchivo(String codigo, ArrayList<Curso> cursos, String Ruta, String NomrbeArchivo ) {
		try {
			
			String crear = Ruta+ codigo +NomrbeArchivo+".txt";
			File archivo = new File(crear);
			archivo.delete();
			archivo.createNewFile();
			
			FileWriter fstream =new FileWriter(archivo, true);
			BufferedWriter out = new BufferedWriter(fstream);

			System.out.println("");
			System.out.println("---------------------------------------------");


			for (int i = 0; i < cursos.size(); i++) {

				Curso actual = cursos.get(i);

				//este if es para manejar el caso en que es la ultima linea, evita que se cree una linea en blanco al final, esto evita errores en la lectura de planes existentes
				if(i+1==cursos.size()) {
					out.write(actual.darNombre()+";"+actual.darCodigo()+";"+actual.darPreRequ()+";"+actual.darCoRequ()+";"+actual.darCreditos()+";"+actual.darTipo()+";"+ actual.darciclo());
				}
				else {
					out.write(actual.darNombre()+";"+actual.darCodigo()+";"+actual.darPreRequ()+";"+actual.darCoRequ()+";"+actual.darCreditos()+";"+actual.darTipo()+";"+ actual.darciclo()+"\n");
				}
			}
			out.close();


		} catch (IOException e) {
			e.printStackTrace();
		}

	}


	
	public void crearyEscribirArchivoAdmin(String codigoAdmin,String codigo,  ArrayList<Curso> cursos, String Ruta, String NomrbeArchivo ) {
		try {
			
			String crear = Ruta+codigoAdmin +NomrbeArchivo+ codigo+".txt";
			File archivo = new File(crear);
			archivo.delete();
			archivo.createNewFile();
			
			FileWriter fstream =new FileWriter(archivo, true);
			BufferedWriter out = new BufferedWriter(fstream);

			System.out.println("");
			System.out.println("---------------------------------------------");


			for (int i = 0; i < cursos.size(); i++) {

				Curso actual = cursos.get(i);

				//este if es para manejar el caso en que es la ultima linea, evita que se cree una linea en blanco al final, esto evita errores en la lectura de planes existentes
				if(i+1==cursos.size()) {
					out.write(actual.darNombre()+";"+actual.darCodigo()+";"+actual.darPreRequ()+";"+actual.darCoRequ()+";"+actual.darCreditos()+";"+actual.darTipo()+";"+ actual.darciclo());
				}
				else {
					out.write(actual.darNombre()+";"+actual.darCodigo()+";"+actual.darPreRequ()+";"+actual.darCoRequ()+";"+actual.darCreditos()+";"+actual.darTipo()+";"+ actual.darciclo()+"\n");
				}
			}
			out.close();


		} catch (IOException e) {
			e.printStackTrace();
		}

	}
	
	public Boolean buscarCarpeta(String Codigo, File direct) {
		Boolean existe=false;
		for (File file : direct.listFiles()) {
			if (file.isDirectory()) {
				if (file.getName().equals(Codigo)) {
					existe = true;
					return existe;
				}
			}
		}
		return existe;
	}

	
	public void registrarEstudianteEnDoc(String nombre, String login, int codigo) {

		try {

			FileWriter fstream =new FileWriter("docs/Estudiante.txt", true);
			BufferedWriter out = new BufferedWriter(fstream);
			out.write("\n" + nombre+ ";"+login+ ";" + String.valueOf(codigo)+";0;None");
			out.close();


		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	
	/**
	 * Este es el mÃ©todo principal de la aplicaciÃ³n, con el que inicia la ejecuciÃ³n
	 * de la aplicaciÃ³n
	 * 
	 * @param args ParÃ¡metros introducidos en la lÃ­nea de comandos al invocar la
	 *             aplicaciÃ³n
	 */
	public static void main(String[] args)
	{
		Console consola = new Console();
		consola.ejecutarAplicacion();
	}

}
