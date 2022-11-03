package Planner;

import java.util.*;

import java.io.*; 

public class PlanDeEstudios {



	//-----------------------------------------
	//Attributes
	//-------------------------------------------------------------

	
	String requisitos;
	File source = new File ("docs/Administrativos.txt");
	ArrayList<Curso> cursosCargados = new ArrayList<Curso>();  
	ArrayList<Curso> lstPensumSugerido = new ArrayList<Curso>();
	ArrayList<String> NombreCursos = new ArrayList<String>(); 
	ArrayList<Estudiantes> Estudiantes_Inscritos = new ArrayList<Estudiantes>(); 
	ArrayList<Administrativos> AdministrativosList = new ArrayList<Administrativos>(); 
	ArrayList<Integer> Codigo_Estudiantes = new ArrayList<Integer>();
	ArrayList<Integer> Codigo_Administrativos = new ArrayList<Integer>();
	Pensum pensumSugerido;

	
	//-------------------------------------------------------------
	//methods
	//-------------------------------------------------------------

	public ArrayList<Curso> getCursosCargados(){
		return cursosCargados;
	}
	
	public Pensum getPensumSugerido(){
		return pensumSugerido;
	}
	public 	ArrayList<String> getNombreCursos(){
		return NombreCursos; 
	}
	
	public 	ArrayList<Estudiantes> getEstudiantes_Inscritos(){
		return Estudiantes_Inscritos; 
	}
	
	public 	ArrayList<Integer> getCodigo_Estudiantes(){
		return Codigo_Estudiantes; 
	}
	
	public 	ArrayList<Integer> getCodigo_Administrativos(){
		return Codigo_Administrativos; 
	}
	public 	ArrayList<Administrativos> getAdministrativosList(){
		return AdministrativosList; 
	}
	
	public PlanDeEstudios() {
		CargarAdministrativo(source);
	}
	
	
	
	//-------------------------------------------------------------
	//Carga de Archivos
	//-------------------------------------------------------------
	
	//Se carga la información Procente de un archivo .txt
	public void cargarInfoBanner(File source) {
		try {
			Scanner fScn = new Scanner(source);
			String data;
			//N;CRN  ;PARTE;LISTA_CRUZADA; MATERIA    ;SECC;CRED      ;NOMBRE_CURSO        ;CUPO ;INSC;DISP ;FINI1 ;FFIN1 ;HINI1 ;HFIN1 ;SALON1 ;L ;M ;I ;J ;V ;S ;FINI2 ;FFIN2 ;HINI2 ;HFIN2 ;SALON2 ;L ;M ;I ;J ;V ;S ;FINI3 ;FFIN3 ;HINI3 ;HFIN3 ;SALON3 ;L ;M ;I ;J ;V ;S ;FINI4 ;FFIN4 ;HINI4 ;HFIN4 ;SALON4 ;L ;M ;I ;J ;V ;S ;FINI5 ;FFIN5 ;HINI5 ;HFIN5 ;SALON5 ;L ;M ;I ;J ;V ;S ;FINI6 ;FFIN6 ;HINI6 ;HFIN6 ;SALON6 ;L ;M ;I ;J ;V ;S ;FINI7 ;FFIN7 ;HINI7 ;HFIN7 ;SALON7 ;L ;M ;I ;J ;V ;S ;FINI8 ;FFIN8 ;HINI8 ;HFIN8 ;SALON8 ;L ;M ;I ;J ;V ;S ;FINI9 ;FFIN9 ;HINI9 ;HFIN9 ;SALON9 ;L ;M ;I ;J ;V ;S ;FINI10;FFIN10;HINI10;HFIN10;SALON10;L ;M ;I ;J ;V ;S ;FINI11;FFIN11;HINI11;HFIN11;SALON11;L ;M ;I ;J ;V ;S ;FFIN12;HINI12;HFIN12;SALON12;L ;M ;I ;J ;V ;S ;FINI13;FFIN13;HINI13;HFIN13;SALON13;L ;M ;I ;J ;V ;S ;FINI14;FFIN14;HINI14;HFIN14;SALON14;L ;M ;I ;J ;V ;S ;FINI15;FFIN15;HINI15;HFIN15;SALON15;L ;M ;I ;J ;V ;S ;FINI16;FFIN16;HINI16;HFIN16;SALON16;L ;M ;I ;J ;V ;S ;FINI17;FFIN17;HINI17;HFIN17;SALON17;L ;M ;I ;J ;V ;S ;FINI18;FFIN18;HINI18;HFIN18;SALON18;L ;M ;I ;J ;V ;S ;FINI19;FFIN19;HINI19;HFIN19;SALON19;L ;M ;I ;J ;V ;S ;FINI20;FFIN20;HINI20;HFIN20;SALON20;L ;M ;I ;J ;V ;S ;FINI21;FFIN21;HINI21;HFIN21;SALON21;L ;M ;I ;J ;V ;S ;FINI22;FFIN22;HINI22;HFIN22;SALON22;L ;M ;I ;J ;V ;S ;FINI23;FFIN23;HINI23;HFIN23;SALON23;L ;M ;I ;J ;V ;S ;FINI24;FFIN24;HINI24;HFIN24;SALON24;L ;M ;I ;J ;V ;S ;FINI25;FFIN25;HINI25;HFIN25;SALON25;L ;M ;I ;J ;V ;S ;FINI26;FFIN26;HINI26;HFIN26;SALON26;L ;M ;I ;J ;V ;S ;FINI27;FFIN27;HINI27;HFIN27;SALON27;L ;M ;I ;J ;V ;S																							HORARIO
			fScn.nextLine();
			fScn.nextLine();
			fScn.nextLine();
			while( fScn.hasNextLine() ){
				data = fScn.nextLine();
				//																							HORARIO							
				//Nombre materia; Código materia; prerrequisito; coreq; créditos; TipoDeCurso; ciclo; 6:30-7:30, 8:00-9:00;Semestre
				
				String[] token = data.split(";");
				 if (token.length<=1) {
					 System.out.println("Fin");
					 String str = "  hello !   ";
				 }else {
				 String inputNombre = token[7].trim();
				 String inputCod = token[4].trim();
				 String inputPrereq = "ninguno";
				 String inputCoreq = "ninguno";
				 int inputCreditos = Integer.parseInt(token[6]);
				 String inputTipo = token[1].trim();
				 String inputCiclo = token[2].trim();
				 String inputHorario = " ";
				 if (!NombreCursos.contains(inputNombre)) {
				
				 NombreCursos.add(inputNombre); 
				 Curso c = new Curso(inputNombre, inputCod, inputPrereq, inputCoreq, inputCreditos, inputTipo, inputCiclo, inputHorario);
				 System.out.println(c.darNombre() + ";"+ c.darCodigo());
				 cursosCargados.add(c);
				}	}		 
			
		}fScn.close(); 
		}catch(IOException e) {
			e.printStackTrace();
		}	
	}
	
		

	
	public void cargarInfoBannerAntes(File source)  throws FileNotFoundException {

			try {
				Scanner fScn = new Scanner(source);
				String data;

				while( fScn.hasNextLine() ){
					data = fScn.nextLine();
					
					//																							HORARIO							
					//Nombre materia; Código materia; prerrequisito; coreq; créditos; TipoDeCurso; ciclo; 6:30-7:30, 8:00-9:00;Semestre
					
					String[] token = data.split(";");

					 String inputNombre = token[0].trim();
					 String inputCod = token[1].trim();
					 String inputPrereq = token[2].trim();
					 String inputCoreq = token[3].trim();
					 int inputCreditos = Integer.parseInt(token[4]);
					 String inputTipo = token[5].trim();
					 String inputCiclo = token[6].trim();
					 String inputHorario = (token[7].trim());
					 if (!NombreCursos.contains(inputNombre)) {
					 NombreCursos.add(inputNombre); 
					 Curso c = new Curso(inputNombre, inputCod, inputPrereq, inputCoreq, inputCreditos, inputTipo, inputCiclo, inputHorario);
					 cursosCargados.add(c);
					}	}		 
				fScn.close(); 
			}
			catch(IOException e) {
				e.printStackTrace();
			}	Pensum pensumSugerido;
		}
		
	
	public void CargarAdministrativo(File source) {
		try {
			Scanner fScn = new Scanner(source);
			String data;

			while( fScn.hasNextLine() ){
				
				data = fScn.nextLine();
				
				// Se cargan Administrativos de la forma: 
				//Nombre; Departamento; codigo;
				
				String[] token = data.split(";");
				
				String codigoString = (token[2].trim());
				int codigo = Integer.parseInt(codigoString);
				Administrativos a = new Administrativos(token[0],token[1],codigo);
				AdministrativosList.add(a); 
				Codigo_Administrativos.add(codigo);
				crearCarpeta(codigoString, "docs/Administrativos/");
			
			}fScn.close();
		}catch(IOException e) {
			e.printStackTrace();
		}
	}
	
	
	
	public void CargarEstudiante(File source) {
		try {
			Scanner fScn = new Scanner(source);
			String data;

			while( fScn.hasNextLine() ){
				
				data = fScn.nextLine();
				
				// Se cargan estudiantes de la forma: 
				//Nombre; Login; codigo; promedioAcumulado; cursosAprobados
				
				String[] token = data.split(";");
				
				double a = Double.parseDouble((token[3]).trim());
				String codigoString = (token[2].trim());
				int codigo =  Integer.parseInt(codigoString);
				Estudiantes e = new Estudiantes(token[0].trim(), token[1].trim(),codigo , a, convertidor_string_Curso(token[4]));
				
				crearCarpeta(codigoString, "docs/Estudiantes/");
				Codigo_Estudiantes.add(codigo);
				Estudiantes_Inscritos.add(e);
			}fScn.close();
			
		}catch(IOException e) {
				e.printStackTrace();
			}
		cargarPlanes(Estudiantes_Inscritos);
		cargarRegistro(Estudiantes_Inscritos);
		}
	
	
	
	// Se carga la info complemetaria de los estudiantes( Planes, semestres cursados). 
	public void cargarPlanes( ArrayList<Estudiantes> Estudiantes_Inscritos) {
		Iterator<Estudiantes> ie = Estudiantes_Inscritos.iterator();
		while (ie.hasNext()) {
			Estudiantes EstudianteActual = ie.next();
			Cplanes(EstudianteActual); 
			HistoricoSemestres(EstudianteActual);
	}}
	
	
	
	
	public void Cplanes(Estudiantes EstudianteActual) {
		File carpeta = new File("docs/Estudiantes/"+String.valueOf(EstudianteActual.darCodigo())+"/Plan"); 
		if(carpeta.exists()) {
		String[] NombresPlanes = carpeta.list(); 
		
		if (NombresPlanes.length == 1) {
			ArrayList<Curso> C = new ArrayList<Curso>();
			File source = new File( "docs/Estudiantes/"+String.valueOf(EstudianteActual.darCodigo())+"/Plan"+"/"+NombresPlanes[0]); 
			auxCargarPlanes(source, C);
			EstudianteActual.crearPlan(NombresPlanes[0].replace(".txt", ""), C);
		}else if (NombresPlanes.length>1) {
			for(int i=0; i<NombresPlanes.length; i++) {
				ArrayList<Curso> C = new ArrayList<Curso>();
				File source = new File( "docs/Estudiantes/"+String.valueOf(EstudianteActual.darCodigo())+"/Plan"+"/"+NombresPlanes[i]); 
				auxCargarPlanes(source, C);
				EstudianteActual.crearPlan(NombresPlanes[i].replace(".txt", ""), C);
				
			}
		}else {
			System.out.println("El estudiante " +EstudianteActual.darNombre() + " No tiene Planes");
		}}
	}
	
	
	
	public void HistoricoSemestres(Estudiantes EstudianteActual) {
		
		File carpeta = new File("docs/Estudiantes/"+String.valueOf(EstudianteActual.darCodigo())+"/HistoricoSemestres"); 
		
		if (carpeta.exists()) {
		String[] NombresPlanes = carpeta.list(); 
		
		if (NombresPlanes.length == 1) {
			ArrayList<Curso> C = new ArrayList<Curso>();
			System.out.println(NombresPlanes[0]);
			File source = new File( "docs/Estudiantes/"+String.valueOf(EstudianteActual.darCodigo())+"/HistoricoSemestres"+"/"+NombresPlanes[0]); 
			auxCargarSemestre(source, C, EstudianteActual);
			EstudianteActual.anadirSemestre(NombresPlanes[0].replace(".txt", ""), C);
		}else if (NombresPlanes.length>1) {
			for(int i=0; i<NombresPlanes.length; i++) {
				ArrayList<Curso> C = new ArrayList<Curso>();
				System.out.println("\n"+i+ "  "+ NombresPlanes[i] +"  "+ EstudianteActual.darNombre());
				File source = new File( "docs/Estudiantes/"+String.valueOf(EstudianteActual.darCodigo())+"/HistoricoSemestres"+"/"+NombresPlanes[i]); 
				auxCargarSemestre(source, C, EstudianteActual);
				EstudianteActual.anadirSemestre(NombresPlanes[i].replace(".txt", ""), C);
				
			}
		}}else {
			System.out.println("El estudiante " +EstudianteActual.darNombre() + " No tiene Semestres");
		}
	}
	
	
	public void cargarRegistro(ArrayList<Estudiantes> Estudiantes_Inscritos) {
		Iterator<Estudiantes> ie = Estudiantes_Inscritos.iterator();
		while (ie.hasNext()) {
			Estudiantes EstudianteActual = ie.next();
			ArrayList<Curso> lst= new ArrayList<Curso>(); 
			File registro = new File("docs/Estudiantes/"+String.valueOf(EstudianteActual.darCodigo())+"/Registro.txt");
			if (registro.exists()) {
			auxCargarPlanes(registro, lst);
			}
			EstudianteActual.setRegistro(new Plan("Registro", lst));
		}
	}
	
	//Funcion para crear un nuevo estudiante
	public void NuevoEstudiante(String pNombre, String pLogin, int pCodigo, double pProm, String pC_aproabdos ) {
		if ((getCodigo_Estudiantes().contains(pCodigo))) {
			System.out.println("El esutdiante con codigo " + pCodigo +" ya se encuentra Registrado");
		}else {
			Estudiantes e = new Estudiantes(pNombre, pLogin, pCodigo, pProm, convertidor_string_Curso(pC_aproabdos));
			Estudiantes_Inscritos.add(e);
	}}
	
	
	public void cargarPensumSugerido(File source)  throws FileNotFoundException {

		try {
			Scanner fScn = new Scanner(source);
			String data;
			
			while( fScn.hasNextLine() ){
				data = fScn.nextLine();
				
				//																							HORARIO							
				//Nombre materia; Código materia; prerrequisito; coreq; créditos; TipoDeCurso; ciclo; 6:30-7:30, 8:00-9:00; Semestre
				
				String[] token = data.split(";");
				
				
				 String inputNombre = token[0].trim();
				 String inputCod = token[1].trim();
				 String inputPrereq = token[2].trim();
				 String inputCoreq = token[3].trim();
				 int inputCreditos = Integer.parseInt(token[4]);
				 String inputTipo = token[5].trim();
				 String inputCiclo = token[6].trim();
				 String inputHorario = token[7].trim();
				 
				 
				
 
				 NombreCursos.add(inputNombre); 
				 Curso c = new Curso(inputNombre, inputCod, inputPrereq, inputCoreq, inputCreditos, inputTipo, inputCiclo, inputHorario);
				 lstPensumSugerido.add(c);
				 
				}			 
			fScn.close(); 
			pensumSugerido = new Pensum(lstPensumSugerido);
		}
		catch(IOException e) {
			e.printStackTrace();
		}
		
	
	}
	
	public void CargarHomologables(File source) {
		//Se carga de la forma MateriaPensumAnteriror = Matreria2
		ArrayList<Curso> Ahomolagar = new ArrayList<Curso>();
		ArrayList<Curso> Nuevos = new ArrayList<Curso>();
		try {
			Scanner fScn = new Scanner(source);
			String data;
			while( fScn.hasNextLine() ){
				data = fScn.nextLine();
				
				String[] token = data.split("=");
				
				Curso CursoAnterior =convertidor_Curso( token[0]); 
				Curso Nuevo = convertidor_Curso(token[1]);

				Ahomolagar.add(CursoAnterior);
				Nuevos.add(Nuevo);
				}			 
			fScn.close(); 
			pensumSugerido.setCursosAHomologar(Ahomolagar);
			pensumSugerido.setCursosHomologables(Nuevos);
		}
		catch(IOException e) {
			e.printStackTrace();
		}
	
	}
	
	
	//-------------------------------------------------------------
	//funciones Auxiliares
	//-------------------------------------------------------------
	
	//Convierte un nombre de curso en un Objeto Curso
	public Curso convertidor_Curso(String nombre_Curso){
			
			Iterator <Curso> icode = getCursosCargados().iterator();
			while (icode.hasNext()) {
				Curso i = icode.next();
				//System.out.println("Buscado: "+nombre_Curso+ "  A comparaR: "+i.darNombre() );
				if (i.darNombre().equals(nombre_Curso) || i.darCodigo().equals(nombre_Curso) ) {
					System.out.println("                            Buscado: "+nombre_Curso+ "  A comparaR: "+i.darNombre() );
					return i;
					
				}}
			return null;		
	}
	
	//Convierte una cadena de codigos de cursos(ISIS-123) en una lista de clase Curso
	public ArrayList<Curso> convertidor_string_Curso(String Cadena_Cursos){
		
		ArrayList<Curso> CursosInscritos = new ArrayList<Curso>();
		String[] c = Cadena_Cursos.split(",");
		for (String curso: c) {
			curso = curso.trim();
			Iterator <Curso> icode = getCursosCargados().iterator();
			while (icode.hasNext()) {
				Curso i = icode.next();
				if (i.darCodigo().equals(curso)) {
			
					//CursosInscritos.add(i);
					}
			}		
		}return CursosInscritos;
	}
	
	//Aux Para Caragar Planes
	public void auxCargarPlanes(File source, ArrayList<Curso> CursosLst) {
		try {
			Scanner fScn = new Scanner(source);
			String data;

			while( fScn.hasNextLine() ){
				data = fScn.nextLine();
				
				String[] token = data.split(";");
				String inputNombre = token[0].trim();
				
				Curso c = convertidor_Curso(inputNombre);
					if(c!=null) {
					CursosLst.add(c);
				}}
			fScn.close(); 
		}
		catch(IOException e) {
			e.printStackTrace();
		}
	}
	
	//Aux para Cargar Semestre
	public void auxCargarSemestre(File source, ArrayList<Curso> CursosLst, Estudiantes e1) {
		try {
			Scanner fScn = new Scanner(source);
			String data;

			while( fScn.hasNextLine() ){
				data = fScn.nextLine();
				
				//Carga los cursos de un estudiante, tomando como nota el token 8
				String[] token = data.split(";");
				String inputNombre = token[0].trim();
				double inputNota = Double.parseDouble((token[2]).trim());
				
				Curso c = convertidor_Curso(inputNombre);
				if (c!=null) {
					c.cambiarNota(inputNota);
					CursosLst.add(c);
					e1.AnadirCursoArpobado(c);
				}			 }
			fScn.close(); 
		}
		catch(IOException e) {
			e.printStackTrace();
		}
	}
		
	
	
	//-------------------------------------------------------------
	//Manejo de TXT
	//-------------------------------------------------------------

	//Se crea la carpeta para almacenar los txt de cada estudiante.
	public void crearCarpeta(String nombre, String path) {
		path = path +nombre;
		File file=new File(path);
		file.mkdir();

		try {
			File fileAprobados = new File("docs/Estudiantes/"+nombre +"/Aprobados.txt");
			fileAprobados.createNewFile();

		}catch(Exception e) {

		}
	}
	
	} 
	

