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
import javax.swing.border.CompoundBorder;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;

import Console.Logic_Console;
import Interfaz.EstudainteMostrarCursosInscribir;
import Interfaz.EstudianteMostrarCursos;
import Interfaz.InterfazPrincipal;
import Planner.Curso;
import Planner.Estudiantes;
import Planner.Plan;

public class EstudianteNuevaInscripcion extends JPanel {
	JPanel panel;
	InterfazPrincipal main;
	private Logic_Console Logica;
	private JTextField Nombre;
	private JTextField CodigoCurso;
	private JButton Buscar; 
	private int Creditos;
	private ArrayList<Curso> CInscritos = new ArrayList<Curso>();
	ArrayList<Curso> Correquisitos = new ArrayList<Curso>();
	ArrayList<Curso> Aeliminar = new ArrayList<Curso>();
	private JTextArea cursos = new JTextArea();
	private JLabel dCreditos;
	ImageIcon iconHome = new ImageIcon("data/Botones/btnHomeB.png");
	ImageIcon iconBack = new ImageIcon("data/Botones/btnBack.png");

	
	public EstudianteNuevaInscripcion(JPanel p, InterfazPrincipal pInterfazPrincipal) {
		
		Logica = pInterfazPrincipal.darLogic_Console();
		main = pInterfazPrincipal;
		panel= p;
		setLayout(new GridBagLayout());
		GridBagConstraints gbc = new GridBagConstraints();


		JLabel bienvenido = new JLabel();
		bienvenido.setText("Inscribir desde 0");
		bienvenido.setFont(new Font("Verdana", Font.PLAIN, 30));
		bienvenido.setSize(800, 000);
		gbc.gridx =0;
		gbc.gridy =0;
		gbc.insets=new Insets(50,5,20,5);

		add(bienvenido,gbc);
		gbc.insets=new Insets(5,5,5,5);
		
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
		gbc.gridx =1;
		gbc.gridy =2;
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
		
		
		
		
		
		
		JPanel DisplayCursosInscritos = new JPanel() ;
		DisplayCursosInscritos.setLayout(new BoxLayout(DisplayCursosInscritos, BoxLayout.Y_AXIS));
		cursos.setText("Nombre - Curso ");
		cursos.setEditable(false);
		
		JScrollPane scrollCursos= new JScrollPane(cursos);
		scrollCursos.setPreferredSize(new Dimension(300,300));
		scrollCursos.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
		scrollCursos.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED), "Curos Inscritos ", TitledBorder.LEFT, TitledBorder.DEFAULT_POSITION));
		DisplayCursosInscritos.add(scrollCursos);
		DisplayCursosInscritos.updateUI();


		DarNumCreditos(0); 
		dCreditos = new JLabel();
		dCreditos.setText("Creditos: "+ String.valueOf(Creditos));
		DisplayCursosInscritos.add(dCreditos);
		
		gbc.gridx =0;
		gbc.gridy =1;
		add(DisplayCursosInscritos, gbc);
		

		JPanel Busqueda = new JPanel();
		Busqueda.setLayout(new BoxLayout(Busqueda, BoxLayout.Y_AXIS));
		

		
		Nombre = new JTextField(); 
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
		gbc.gridx = 1;
		gbc.gridy = 1;
		add(Busqueda,gbc);
		
		
		
		JPanel Botones = new JPanel();
		Botones.setLayout(new BoxLayout(Botones, BoxLayout.Y_AXIS));
		Buscar = new JButton();
		Buscar.setText("Buscar");
		
		Buscar.addActionListener(new ActionListener() {
			
			

			@Override
			public void actionPerformed(ActionEvent e) {
				
				Estudiantes e1 = Logica.darEstudiante();
				
				
				String n = Nombre.getText();
				String c = CodigoCurso.getText();
				int pos = e1.darPlanes().size()-1;
				Plan p = e1.darPlanes().get(pos);
				if(n.equals("Nombre") && c.equals("CodigoCurso")) {
					n="";
					c="";
				}else if(c.equals("CodigoCurso")) {
					c= "";
				}else if(n.equals("Nombre")) {
					n="";
				}
				funcionMain(n,c,p, CInscritos);
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
		Guardar.setText("Guardar");
		Guardar.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				Estudiantes e1 = Logica.darEstudiante();
				Logica.VerificadorCorrequisitos( CInscritos, Correquisitos, Aeliminar);
				Logica.RegistrarPlan(e1.darCodigo(), CInscritos, e1.darPlanes().size(),"Algo" );
				e1.setRegistro(new Plan("Registro", CInscritos));
				main.switchPanel(panel, "estInscribir");
			}
			
		});
		Botones.add(Guardar);
		
		gbc.gridx = 2;
		gbc.gridy = 1;
		add(Botones,gbc);
		
	}
	
	//--------------------------------
	//transition methods
	//------------------------------
	public void DarNumCreditos(int resta) {
		if (Logica.darEstudiante().darPromedioAcumulado()>=4.0) {
			Creditos = 25-resta;
			
			
		}else {
			Creditos = 20-resta;
		}
	}

	
	public void funcionMain(String n, String c, Plan pPlan, ArrayList<Curso> CInscritos) {
		
		
		Estudiantes e1 = Logica.darEstudiante();
		int alertcambio =CInscritos.size();
		
	

		
		if((n.equals("") && c.equals(""))) {
			JOptionPane.showMessageDialog(this, "Campos vacios");
			
		}else if(n.equals("") && !c.equals("")) {
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
		}else if(!n.equals("") && c.equals("")) {

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
		JOptionPane.showMessageDialog(this, "No se Guardaron los cambios");
		main.switchPanel(panel, "estInscribir");
		

	}

}
