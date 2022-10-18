package Interfaz;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.File;

import javax.swing.*;

import Console.Logic_Console;



public class EstudianteMenu extends JPanel{

	JPanel panel;
	InterfazPrincipal main;

	
	ImageIcon iconoPensum = new ImageIcon("data/central/iconoPensum.png"); 
	ImageIcon iconoInscribir = new ImageIcon("data/central/iconoInscribir.png"); 
	ImageIcon iconoInfo = new ImageIcon("data/central/iconoInfo.png"); 
	ImageIcon iconoPlanear = new ImageIcon("data/central/iconoPlanear.png"); 
	ImageIcon iconoBack =  new ImageIcon("data/Botones/btnBack.png");
	ImageIcon iconoHome = new ImageIcon("data/Botones/btnHome.png");
	
	
	Logic_Console Logica; 





	public EstudianteMenu(JPanel p, Logic_Console pConsole, InterfazPrincipal pInterfazPrincipal) {
		main = pInterfazPrincipal;
		panel= p;
		Logica = pConsole;
		setLayout(new GridBagLayout());
		GridBagConstraints gbc = new GridBagConstraints();

		gbc.insets=new Insets(50,200,50,50);

		JLabel bienvenido = new JLabel();
		bienvenido.setText("Seleccione una opcion");
		bienvenido.setFont(new Font("Verdana", Font.PLAIN, 30));
		gbc.gridy=0;
		add(bienvenido,gbc);
		
		JLabel Nombre = new JLabel();
		Nombre.setText(main.darLogic_Console().darEstudiante().darNombre());
		add(Nombre);

		gbc.insets=new Insets(50,-150,50,50);
		JLabel pensum = new JLabel(escalarIcono(iconoPensum, 80,80));
		pensum.setText("Cargar Pensum");
		pensum.setFont(new Font("Verdana", Font.PLAIN, 20));
		gbc.gridy=05;

		pensum.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				abrirPensum(e);	
			}
		});

		add(pensum,gbc);

		JLabel plan = new JLabel(escalarIcono(iconoPlanear, 80,80));
		plan.setText("Planear Horario");
		plan.setFont(new Font("Verdana", Font.PLAIN, 20));
		plan.setSize(800, 200);
		gbc.gridy=05;

		plan.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				abrirPlan(e);	
			}

		});
		add(plan,gbc);

		JLabel info = new JLabel(escalarIcono(iconoInfo, 80,80));
		info.setText("Info personal");
		info.setFont(new Font("Verdana", Font.PLAIN, 20));
		info.setSize(000, 000);
		gbc.gridy=10;

		info.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				abrirInfo(e);	
			}	
		});

		add(info,gbc);


		JLabel inscribir = new JLabel(escalarIcono(iconoInscribir, 80,80));
		inscribir.setText("Inscribir Horario");
		inscribir.setFont(new Font("Verdana", Font.PLAIN, 20));
		inscribir.setSize(800, 200);
		//gbc.insets=new Insets(0,0,0,0);
		//gbc.gridy=0;

		inscribir.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				abrirInscribir(e);	
			}
		});

		add(inscribir,gbc);
		
		JButton back = new JButton();
		back.setOpaque(false);
		back.setContentAreaFilled(false);
		back.setBorderPainted(false);
		back.setIcon(iconoBack);
	

		back.addActionListener(new ActionListener() {
	            public void actionPerformed(ActionEvent e) {
	            	Menuanterior(e);	
	            }
		});
		
		add(back);

		JButton home = new JButton();
		home.setOpaque(false);
		home.setContentAreaFilled(false);
		home.setBorderPainted(false);
		home.setIcon(iconoHome);
		home.addActionListener(new ActionListener() {
	            public void actionPerformed(ActionEvent e) {
	            	home(e);	
	            }
		});
		
		add(home);
		


	}








	//--------------------------------
	//transition methods
	//------------------------------


	public void abrirInfo(MouseEvent e) {
		main.switchPanel(panel, "estInfo");
	}	

	public void abrirPlan(MouseEvent e) {
		main.switchPanel(panel, "estPlan");
	}	


	public void abrirPensum(MouseEvent e) {
		main.cards.add(new EstudianteAnadir(main.cards, main),  "estA�adir");
		main.switchPanel(panel, "estA�adir");

	}	

	public void abrirInscribir(MouseEvent e) {
		main.switchPanel(panel, "estInscribir");
	}	
	public void Menuanterior(ActionEvent e) {
		Logica.setEstudiante(null);
		main.switchPanel(panel, "estLogin");
	}
	public void home(ActionEvent e) {
		Logica.setEstudiante(null);
		main.switchPanel(panel, "Inicio");
	}


	private ImageIcon escalarIcono(ImageIcon src, int w, int h){
		Image image = src.getImage(); // transform it 
		Image newimg = image.getScaledInstance(w,h,  java.awt.Image.SCALE_SMOOTH); // scale it the smooth way  
		ImageIcon resize = new ImageIcon(newimg);

		return resize;
	}



}
