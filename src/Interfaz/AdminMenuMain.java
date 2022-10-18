package Interfaz;



import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.util.ArrayList;

import javax.swing.*;

import Console.Console;
import Planner.Curso;
import Planner.PlanDeEstudios;





public class AdminMenuMain extends JPanel{

	JPanel panel;
	InterfazPrincipal main;

	Console c;
	PlanDeEstudios p;
	

	ImageIcon iconoPensum = new ImageIcon("data/central/iconoPensum.png"); 
	ImageIcon iconoInscribir = new ImageIcon("data/central/iconoInscribir.png"); 
	ImageIcon iconoInfo = new ImageIcon("data/central/iconoInfo.png"); 
	ImageIcon iconoPlanear = new ImageIcon("data/central/iconoPlanear.png"); 
	ImageIcon iconBack = new ImageIcon("data/Botones/btnBack.png");
	final JFileChooser fc = new JFileChooser();
	
	





	public AdminMenuMain( PlanDeEstudios pl,Console c, JPanel p, InterfazPrincipal pInterfazPrincipal) {
		
		main =pInterfazPrincipal; 
		panel= p;
		setLayout(new GridBagLayout());
		GridBagConstraints gbc = new GridBagConstraints();

		gbc.insets=new Insets(50,200,50,50);

		JLabel bienvenido = new JLabel();
		bienvenido.setText("Seleccione una opcion");
		bienvenido.setFont(new Font("Verdana", Font.PLAIN, 30));
		gbc.gridy=0;
		add(bienvenido,gbc);


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
		/*
		pensum.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				abrirPensum(e);	
			}
		});
		 */

		add(pensum,gbc);

		JLabel est = new JLabel(escalarIcono(iconoPlanear, 80,80));
		est.setText("Buscar Estudiante");
		est.setFont(new Font("Verdana", Font.PLAIN, 20));
		est.setSize(800, 200);
		gbc.gridy=05;

		est.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				abrirBusquedaEstudiante(e);	
			}

		});
		add(est,gbc);
		
		JButton back = new JButton();
		back.setOpaque(false);
		back.setContentAreaFilled(false);
		back.setBorderPainted(false);
		back.setIcon(iconBack);
		back.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				main.switchPanel(panel, "adminLogin");
				
			}
			
		});
		add(back);
	}



	
	//--------------------------------
	//transition methods
	//------------------------------
	
	public void abrirBusquedaEstudiante(MouseEvent e) {
		
		main.switchPanel(panel, "adminBuscarest");
	
	}	
	
	
	public void abrirPensum(MouseEvent e) {
		fc.setCurrentDirectory(new File("docs/pensum"));
		fc.showOpenDialog(panel);
		File file = fc.getSelectedFile();
		
		try {
		p.cargarInfoBanner(file);
		System.out.println("Pensum cargado");}
		catch (Exception ex) {

		}
		
		//p.CargarEstudiante(file, new ArrayList<Curso>());
		 
		
		
		

	}	
	
	
	private ImageIcon escalarIcono(ImageIcon src, int w, int h){
	Image image = src.getImage(); // transform it 
	Image newimg = image.getScaledInstance(w,h,  java.awt.Image.SCALE_SMOOTH); // scale it the smooth way  
	ImageIcon resize = new ImageIcon(newimg);

    return resize;
}

	
}
