package Console;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Scanner;

import Planner.Administrativos;
import Planner.Curso;
import Planner.Estudiantes;
import Planner.Pensum;
import Planner.Plan;
import Planner.PlanDeEstudios;

public class Logic_Console {
	
	//-----------------------------------------------------------
	// Atributos 
	//-----------------------------------------------------------
	File source = new File ("docs/cartelera.csv");
	File pensumFile = new File("docs/Data_P1.txt");
	File estudiantes = new File ("docs/Estudiante.txt");
	File Admin = new File ("docs/Administrativos.txt");
	private PlanDeEstudios plan; 
	private Estudiantes estudianteActual; 
	
	//-----------------------------------------------------------
	// Constructor 
	//-----------------------------------------------------------
	
	public Logic_Console(PlanDeEstudios pPlan) {
		plan = pPlan;
		System.out.println("ENtro ");
		cargarArchivos(source, estudiantes, Admin);
		
	}
	 
	
	
	
		//----------------------------------------------------------------------------------------------------------------------
		// 															Metodos 
		//----------------------------------------------------------------------------------------------------------------------
	
	public Pensum darPensumSugerido() {
		return plan.getPensumSugerido();		
	}
	//-----------------------------------------------------------
	//Carga de datos
	//-----------------------------------------------------------
	
	public void cargarArchivos(File source, File estudiantes, File Admon) {
		try {
			plan.cargarPensumSugerido(pensumFile);
			plan.cargarInfoBanner(source);
			plan.CargarAdministrativo(Admin);
			plan.CargarEstudiante(estudiantes);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void cargarPensumSugerido(File source) {
		try {
			plan.cargarPensumSugerido(source);

		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	//-----------------------------------------------------------
	// Metodos sobre estudiantes
	//-----------------------------------------------------------
	public ArrayList<Curso> darCursosDisponibles() {
		return plan.getCursosCargados();
	}
	public void setEstudiante(Estudiantes e1) {
		estudianteActual = e1;
	}
	
	public Estudiantes darEstudiante() {
		return estudianteActual;
	}
	
	public Estudiantes buscarEstudiantePorCodigo(int Codigo) {
		ArrayList<Integer> CodigosLista = plan.getCodigo_Estudiantes();
		ArrayList<Estudiantes> Lst = plan.getEstudiantes_Inscritos();
		Iterator <Estudiantes> icode = Lst.iterator();
		if (CodigosLista.contains(Codigo) ) {
		while (icode.hasNext()) {
			Estudiantes i = icode.next();
			if (i.darCodigo() == (Codigo)) {
				return i; 
			}
		}}
		return null;}
	
	
	
	
	private void ActualizarEstudiante(Estudiantes e1, String Ruta, Administrativos a1, String pSemestre) {
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
				e1.anadirSemestre(pSemestre, Cursos_Aprobados);
				System.out.println("Se ha actualizado la informacion con Exito");
				System.out.println(e1.darHistoricoSemestres().size());
		}catch(IOException e) {
			e.printStackTrace();}
		
	}
	
	//cusros y notoas separrdos por ;
	public void anadirCuros(Estudiantes e1, String Cursos, String notas) throws Exception {
		
		String[] lstCursos = Cursos.split(";");
		String[] lstNotas = notas.split(";");
		
		if (lstCursos.length==lstNotas.length) {
			for(int i =0; i<lstCursos.length; i++) {
				Curso CursoActual = plan.convertidor_Curso(lstCursos[i]);
				CursoActual.cambiarNota(Double.parseDouble(lstNotas[i]));
				e1.darCursosAprobados().add(CursoActual);
			}
			
		}else {
			throw new Exception();
		}
		
	}
	
	
	//se a�ade al pensum para que cunado haga la validacion de c
	//
	public void homologarCuros(File pensunReformado, File homologables) {
		try {
			plan.cargarPensumSugerido(pensunReformado);
			plan.CargarHomologables(homologables);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
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
					if (PensumSugerido.darHomologables()==null) {
						Faltantes.add(CActual);
					}else {
					Iterator <Curso> CursoHomologable = PensumSugerido.darHomologables().iterator();
					while (CursoHomologable.hasNext()) {
						Curso CHActual = CursoHomologable.next();
						if (CursoAprobados.contains(CHActual)) {
							i=i+1;
							//Aca se puede hacer el cambio, si se quiere, para que se a�ada el nuevo curso en lugar del anterior
							e1.darCursosAprobados().add(CHActual);
						}else {
							Faltantes.add(CActual);
						}
					}
					
					}}
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
	
	
	public void eliminarPlan(Estudiantes e1, Plan pPlan) {
		System.out.println("Antes : "+ e1.darPlanes().size());		
		String ruta = "docs/Estudiantes/"+String.valueOf(e1.darCodigo())+"/Plan";
		String NombreArchivo = e1.darPlanes().get(e1.darPlanes().indexOf(pPlan)).darNombre();	
		e1.eliminarPlan( pPlan);
		System.out.println("Despues : "+ e1.darPlanes().size());
		eliminarArchivo(ruta, NombreArchivo);		
		}
	
	
	
	
	
	
	
	//-------------------------------------------------------------------------------------
	//Verificar Requisitos
	//-------------------------------------------------------------------------------------
		
	public ArrayList<Curso> verificadorDerequisitos(String CursoAInscribir , double creditos, Estudiantes e1, ArrayList<Curso> CInscritos, ArrayList<Curso> Correquisitos, ArrayList<Curso> Aeliminar) throws Exception {
			
			System.out.println("\nPara Guardar y finalizar presiona 0");
			System.out.println("Para Eliminar agrega un # al final del Nombre\n");
			
			
		
			CursoAInscribir = CursoAInscribir.trim(); 
			if (CursoAInscribir.endsWith("#")) {
				CursoAInscribir = CursoAInscribir.replace("#", ""); 
				Curso CursoI = plan.convertidor_Curso(CursoAInscribir);
				if (CInscritos.contains(CursoI)) {
				CInscritos.remove(CInscritos.indexOf(CursoI));
				System.out.println("\nHas Eliminado "+ CursoI.darNombre());
				return CInscritos; 
				}else {
					System.out.println("\nNo has Inscrito "+ CursoI.darNombre());
					return CInscritos;
				}
				
			}else {
			Curso CursoI = plan.convertidor_Curso(CursoAInscribir);
			if ( CursoI==null) {
				throw new Exception();
			}else {
			System.out.println(CursoI.darNombre());}
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

		
		
	//----------------------------------------------------------------------------------------------------------------------
	// 															TXT 
	//----------------------------------------------------------------------------------------------------------------------
		
	public void eliminarArchivo(String ruta, String NombreArchivo ) {
		String crear = ruta +"/"+ NombreArchivo+".txt";
		File archivo = new File(crear);
		archivo.delete();
	}
	
	public void inscribirAPlan(int codigo, ArrayList<Curso> cursos,int planesExistentes , String NombrePlan) {
		try {

			int numcrear= planesExistentes;
			String crear = "docs/Estudiantes/"+ codigo +"/Plan/" +NombrePlan +".txt"; 
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
					out.write(actual.darNombre()+";"+actual.darCodigo()+";"+actual.darPreRequ()+";"+actual.darCoRequ()+";"+actual.darCreditos()+";"+actual.darTipo()+";"+ actual.darciclo()+";"+actual.darHorario());
				}
				else {
					out.write(actual.darNombre()+";"+actual.darCodigo()+";"+actual.darPreRequ()+";"+actual.darCoRequ()+";"+actual.darCreditos()+";"+actual.darTipo()+";"+ actual.darciclo()+";"+actual.darHorario()+"\n");
				}
			}
			out.close();


		} catch (IOException e) {
			e.printStackTrace();
		}
	}
		
		public void RegistrarPlan(int codigo, ArrayList<Curso> cursos,int planesExistentes , String NombrePlan) {
			try {

				int numcrear= planesExistentes;
				String crear = "docs/Estudiantes/"+ codigo +"/Registro.txt"; 
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
						out.write(actual.darNombre()+";"+actual.darCodigo()+";"+actual.darPreRequ()+";"+actual.darCoRequ()+";"+actual.darCreditos()+";"+actual.darTipo()+";"+ actual.darciclo()+";"+actual.darHorario());
					}
					else {
						out.write(actual.darNombre()+";"+actual.darCodigo()+";"+actual.darPreRequ()+";"+actual.darCoRequ()+";"+actual.darCreditos()+";"+actual.darTipo()+";"+ actual.darciclo()+";"+actual.darHorario()+"\n");
					}
				}
				out.close();


			} catch (IOException e) {
				e.printStackTrace();
			}
	}
	
	public void crearyEscribirArchivo(String codigo, ArrayList<Curso> cursos, String Ruta, String NombreArchivo ) {
		try {
			
			String crear = Ruta+ codigo +NombreArchivo+".txt";
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
	
	
	
}