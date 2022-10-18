package Interfaz;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.Iterator;

import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;

import Console.Logic_Console;
import Planner.Curso;
import Planner.Estudiantes;
import Planner.Plan;

public class EstudianteEditarInscribir extends JPanel {
	JPanel panel;
	InterfazPrincipal main;
	private Logic_Console Logica;
	private JTextField CodigoCurso;
	private JButton Buscar; 
	private int Creditos;
	private ArrayList<Curso> CInscritos;
	private JTextArea cursos;
	private JLabel dCreditos;
	private JLabel PNombre;
	ArrayList<Curso> Correquisitos = new ArrayList<Curso>();
	ArrayList<Curso> Aeliminar = new ArrayList<Curso>();
	ImageIcon iconHome = new ImageIcon("data/Botones/btnHomeB.png");
	ImageIcon iconBack = new ImageIcon("data/Botones/btnBack.png");

	
	public EstudianteEditarInscribir(JPanel p, InterfazPrincipal pInterfazPrincipal, Plan pPlan, Plan Registro) {
		
		Logica = pInterfazPrincipal.darLogic_Console();
		main = pInterfazPrincipal;
		panel= p;
		setLayout(new GridBagLayout());
		GridBagConstraints gbc = new GridBagConstraints();


		JLabel bienvenido = new JLabel();
		bienvenido.setText("Editar Inscripcion");
		bienvenido.setFont(new Font("Verdana", Font.PLAIN, 30));
		bienvenido.setSize(800, 000);
		gbc.gridx =0;
		gbc.gridy =0;
		
		add(bienvenido,gbc);
		

		gbc.insets=new Insets(5,5,5,5);

		JPanel DisplayCursosInscritos = new JPanel() ;
		DisplayCursosInscritos.setLayout(new BoxLayout(DisplayCursosInscritos,  BoxLayout.Y_AXIS));
		DisplayCursosInscritos.updateUI();
		ShowCursos(DisplayCursosInscritos, pPlan, Registro);
		gbc.gridx=0;
		gbc.gridy=1;
		add(DisplayCursosInscritos,gbc);
		
	
		
		JPanel Busqueda = new JPanel();
		Busqueda.setLayout(new BoxLayout(Busqueda, BoxLayout.Y_AXIS));
		
		JTextField Nombre = new JTextField(); 
		Nombre.setText("Nombre");
		Nombre.setForeground(new Color(162,162,162));
		
		Nombre.addMouseListener(new MouseListener() {
			int click = 0;
			@Override
			public void mouseClicked(MouseEvent e) {
				
				if(click==0) {
				Nombre.setText("");
				Nombre.setForeground(Color.BLACK);
				System.out.println("CLICk");
				click++;
				}
				
			}

			@Override
			public void mousePressed(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void mouseReleased(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void mouseEntered(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void mouseExited(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
		});
	
		Busqueda.add(Nombre);
		
		
		CodigoCurso = new JTextField(); 
		CodigoCurso.setText("CodigoCurso");
		CodigoCurso.setForeground(new Color(162,162,162));
		CodigoCurso.addMouseListener(new MouseListener() {
			int click = 0;
			@Override
			public void mouseClicked(MouseEvent e) {
				
				if(click==0) {
					CodigoCurso.setText("");
					CodigoCurso.setForeground(Color.BLACK);
				System.out.println("CLICk");
				click++;
				}
				
			}

			@Override
			public void mousePressed(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void mouseReleased(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void mouseEntered(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void mouseExited(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
		});
		Busqueda.add(CodigoCurso);
		gbc.gridx=1;
		gbc.gridy=1;
		add(Busqueda,gbc);
		
		
		
		JPanel Botones = new JPanel();
		Botones.setLayout(new BoxLayout(Botones, BoxLayout.Y_AXIS));
		Buscar = new JButton();
		Buscar.setText("Buscar");
		
		Buscar.addActionListener(new ActionListener() {
			

			@Override
			public void actionPerformed(ActionEvent e) {
				
				
				String n = Nombre.getText();
				String c = CodigoCurso.getText();
				if(n.equals("Nombre")) {
					n=null; 
				}else if(c.equals("CodigoCurso")) {
					c= null;
				}
				funcionMain(n,c,pPlan, Registro);
				}
			
		});
		
		Botones.add(Buscar);
		
		JButton MostrarCursos = new JButton();
		MostrarCursos.setText("MOSTARS CURSOS");
		MostrarCursos.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				main.cards.add(new EstudainteMostrarCursosInscribir(main.cards, main), "EstMostrarCursosInscribir");
				main.switchPanel(panel, "EstMostrarCursosInscribir");
				
			}
			
		});
		Botones.add(MostrarCursos);
		
		
		JButton Guardar = new JButton();
		Guardar.setText("Inscribir");
		Guardar.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				Estudiantes e1 = Logica.darEstudiante();
				CInscritos = pPlan.darCursos();
				Logica.VerificadorCorrequisitos( CInscritos, Correquisitos, Aeliminar);
				Logica.RegistrarPlan(e1.darCodigo(), CInscritos, e1.darPlanes().size(),"Algo" );
				e1.setRegistro(new Plan("Registro", CInscritos));
				main.switchPanel(panel, "estInscribir");
			}
			
		});
		Botones.add(Guardar);
		gbc.gridx=2;
		gbc.gridy=1;
		add(Botones,gbc);
		
		JButton siguiente = new JButton();
		siguiente.setOpaque(false);
		siguiente.setContentAreaFilled(false);
		siguiente.setBorderPainted(false);
		siguiente.setSize(800, 200);

		siguiente.setIcon(iconHome);
	
		siguiente.addActionListener(new ActionListener() {
	            public void actionPerformed(ActionEvent e) {
	            	abrirMenuPrincipal(e);	
	            }
		});
		gbc.gridx=1;
		gbc.gridy=2;
		add(siguiente,gbc);
		
		JButton back = new JButton();
		back.setOpaque(false);
		back.setContentAreaFilled(false);
		back.setBorderPainted(false);
		back.setIcon(iconBack);
		back.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				
				back();
			}
			
		});
		gbc.gridx=2;
		gbc.gridy=2;
		add(back, gbc);
		
	}
	
	//--------------------------------
	//transition methods
	//------------------------------
	
	public void ShowCursos(JPanel displayCursosInscritos, Plan pPlan, Plan Registro) {
		displayCursosInscritos.updateUI();
		JTextArea cursos = new JTextArea();
		ArrayList<Curso> Aiterar = pPlan.darCursos();
		if (Registro!=null) {
			Iterator<Curso> iC = Registro.darCursos().iterator();
			while(iC.hasNext()) {
				Curso CursoA = iC.next();
				if(!Aiterar.contains(CursoA)) {
					Aiterar.add(CursoA);
					}
		}}
	
		int resta =0; 
		Iterator<Curso> iCurso2 =Aiterar.iterator();
		String info="";
		while(iCurso2.hasNext()) {
			
			Curso cA2 = iCurso2.next();
			info = info +" \n"+ cA2.darNombre()+" "+cA2.darCodigo() ;
			resta= resta + cA2.darCreditos();
		}
		cursos = new JTextArea();
		cursos.setText(info);
		cursos.setEditable(false);
		
		JScrollPane scrollCursos= new JScrollPane(cursos);
		scrollCursos.setPreferredSize(new Dimension(300,300));
		scrollCursos.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
		scrollCursos.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED), pPlan.darNombre(), TitledBorder.LEFT, TitledBorder.DEFAULT_POSITION));
		displayCursosInscritos.add(scrollCursos);
		
		
		DarNumCreditos(resta); 
		dCreditos = new JLabel();
		dCreditos.setText("Creditos: "+ (String.valueOf(Creditos)));
		displayCursosInscritos.add(dCreditos);
		
		
		}
	

	public void DarNumCreditos(int resta) {
		if (Logica.darEstudiante().darPromedioAcumulado()>=4.0) {
			Creditos = 25-resta;
			
			
		}else {
			Creditos = 20-resta;
		}
	}
	
	public void funcionMain(String n, String c, Plan pPlan, Plan Registro) {
		
		Estudiantes e1 = Logica.darEstudiante();
		int alertcambio;
		ArrayList<Curso> CInscritos;

		if (Registro != null) {
			CInscritos = pPlan.darCursos() ;
			Iterator<Curso> iC = Registro.darCursos().iterator();
			while(iC.hasNext()) {
				Curso CursoA = iC.next();
				if(!CInscritos.contains(CursoA)) {
					CInscritos.add(CursoA);
					}
				
			}
			alertcambio = CInscritos.size(); 
		}else if (pPlan != null) {
			CInscritos = pPlan.darCursos() ;
			alertcambio = CInscritos.size(); 
		}else {
			CInscritos = new ArrayList<Curso>();	
			 alertcambio =0;
		}
		
		
		if(n==(null) && c!=(null)) {
			try {
				ArrayList<Curso> lst= Logica.verificadorDerequisitos(c, Creditos, Logica.darEstudiante(), CInscritos, Correquisitos, Aeliminar);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				JOptionPane.showMessageDialog(this, "Curso No encontrado ");
				e.printStackTrace();
			}
			e1.darPlanes().get(e1.darPlanes().indexOf(pPlan)).guardarplan(CInscritos);
			if (CInscritos.size()> alertcambio) {
				Curso CursoA = CInscritos.get(CInscritos.size()-1);
				cursos.setText(cursos.getText()+"\n"+CursoA.darNombre() +"; "+ CursoA.darCodigo());
				Creditos = Creditos - CursoA.darCreditos();
				dCreditos.setText(String.valueOf(Creditos));
				dCreditos.updateUI();
				alertcambio++;
			}

			
		}else if((n!=(null) && c==(null))) {
			try {
				ArrayList<Curso> lst = Logica.verificadorDerequisitos(n, Creditos, Logica.darEstudiante(), CInscritos, Correquisitos, Aeliminar);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				JOptionPane.showMessageDialog(this, "Curso No encontrado ");
				e.printStackTrace();
			}
			e1.darPlanes().get(e1.darPlanes().indexOf(pPlan)).guardarplan(CInscritos);
			if (CInscritos.size()> alertcambio) {
				Curso CursoA = CInscritos.get(CInscritos.size()-1);
				cursos.setText(cursos.getText()+"\n"+CursoA.darNombre() +"; "+ CursoA.darCodigo());
				Creditos = Creditos - CursoA.darCreditos();
				dCreditos.setText(String.valueOf(Creditos));
				dCreditos.updateUI();
				alertcambio++;
				}
		}else if((n==(null) && c==(null))) {
			
			JOptionPane.showMessageDialog(this, "Campos vacios");
		} else {
			try {
				ArrayList<Curso> lst =Logica.verificadorDerequisitos(n, Creditos, Logica.darEstudiante(), CInscritos, Correquisitos, Aeliminar);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				JOptionPane.showMessageDialog(this, "Curso No encontrado ");
				e.printStackTrace();
			}
			e1.darPlanes().get(e1.darPlanes().indexOf(pPlan)).guardarplan(CInscritos);
			if (CInscritos.size()> alertcambio) {
				Curso CursoA = CInscritos.get(CInscritos.size()-1);
				cursos.setText(cursos.getText()+"\n"+CursoA.darNombre() +"; "+ CursoA.darCodigo());
				Creditos = Creditos - CursoA.darCreditos();
				dCreditos.setText(String.valueOf(Creditos));
				dCreditos.updateUI();
				alertcambio++;
				}
			
		}
		
	}
	

	public void abrirMenuPrincipal(ActionEvent e) {
		
		main.switchPanel(panel, "estMenu");
		
	}	
	
	private void back() {
		main.switchPanel(panel, "estInscribir");
	}

	
	
	
}

