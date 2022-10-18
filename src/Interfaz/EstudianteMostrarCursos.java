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
import java.util.Iterator;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;

import Console.Logic_Console;
import Planner.Curso;
import Planner.Plan;

public class EstudianteMostrarCursos extends JPanel {
	
	JPanel panel;
	InterfazPrincipal main;
	private Logic_Console Logica;
	private JTextField Nombre;
	private JTextField CodigoCurso;
	private JButton Buscar; 
	ImageIcon iconHome = new ImageIcon("data/Botones/btnHomeB.png");
	ImageIcon iconBack = new ImageIcon("data/Botones/btnBack.png");
	
	public EstudianteMostrarCursos(JPanel p, InterfazPrincipal pInterfazPrincipal) {
		
		Logica = pInterfazPrincipal.darLogic_Console();
		main = pInterfazPrincipal;
		panel= p;
		setLayout(new GridBagLayout());
		
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.insets = new Insets(5,0,5,0);

		JLabel bienvenido = new JLabel();
		bienvenido.setText("Cursos Disponibles");
		bienvenido.setFont(new Font("Verdana", Font.PLAIN, 30));

		gbc.gridx = 0;
		gbc.gridy = 0;
		add(bienvenido,gbc);
		
		
		JButton siguiente = new JButton();
		siguiente.setOpaque(false);
		siguiente.setContentAreaFilled(false);
		siguiente.setBorderPainted(false);
		
		siguiente.setIcon(iconHome);

		siguiente.addActionListener(new ActionListener() {
	            public void actionPerformed(ActionEvent e) {
	            	abrirMenuPrincipal(e);	
	            }
		});

		
		JButton back = new JButton();
		back.setOpaque(false);
		back.setContentAreaFilled(false);
		back.setBorderPainted(false);
		back.setIcon(iconBack);
		

		
		back.addActionListener(new ActionListener() {
	            public void actionPerformed(ActionEvent e) {
	            	main.switchPanel(panel, "estEditarPlan");
	            }
		});

		JPanel displayCursosInscritos = new JPanel();
		ShowCursos(displayCursosInscritos);
		gbc.gridx = 0;
		gbc.gridy = 2;
		add(displayCursosInscritos,gbc);
		gbc.gridx = 0;
		gbc.gridy = 3;
		add(siguiente);
		gbc.gridx = 1;
		gbc.gridy = 3;
		add(back);
		
	}
	
	//--------------------------------
	//transition methods
	//------------------------------
	
	public void ShowCursos(JPanel displayCursosInscritos) {
		if (Logica.darCursosDisponibles().equals(null)) {
			JTextArea cursos = new JTextArea();
			displayCursosInscritos.add(cursos);
		}else {
			Iterator<Curso> iCurso2 = Logica.darCursosDisponibles().iterator();
			String info="";
			while(iCurso2.hasNext()) {
				
				Curso cA2 = iCurso2.next();
				info = info +" \n"+ cA2.darNombre()+"  -  "+cA2.darCodigo() ; 
			}
			JTextArea cursos = new JTextArea();
			cursos.setText("Nombre  -  Codigo "+ info);
			cursos.setEditable(false);
			displayCursosInscritos.add(cursos);
			JScrollPane scrollCursos= new JScrollPane(cursos);
			scrollCursos.setPreferredSize(new Dimension(420,350));
			scrollCursos.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
			displayCursosInscritos.add(scrollCursos);
			scrollCursos.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED),"Cursos Disponibles", TitledBorder.LEFT, TitledBorder.DEFAULT_POSITION));

	}}

	public void abrirMenuPrincipal(ActionEvent e) {
		
		main.switchPanel(panel, "estMenu");
		
	}	
	
	
	
}

