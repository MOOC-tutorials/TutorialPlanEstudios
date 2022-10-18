package Interfaz;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.*;

import Console.Logic_Console;



public class AdminMenuEstudiante extends JPanel{

	JPanel panel;
	InterfazPrincipal main ;
	Logic_Console Logica; 


	ImageIcon iconoPensum = new ImageIcon("data/central/iconoPensum.png"); 
	ImageIcon iconoInscribir = new ImageIcon("data/central/iconoInscribir.png"); 
	ImageIcon iconoInfo = new ImageIcon("data/central/iconoInfo.png"); 
	ImageIcon iconoPlanear = new ImageIcon("data/central/iconoPlanear.png"); 
	ImageIcon iconBack = new ImageIcon("data/Botones/btnBack.png");
	final JFileChooser fc = new JFileChooser();
	
	





	public AdminMenuEstudiante(JPanel p, Logic_Console Logic, InterfazPrincipal pInterfazPrincipal) {
		main =pInterfazPrincipal;
		panel= p;
		Logica = Logic;
		
		setLayout(new GridBagLayout());
		GridBagConstraints gbc = new GridBagConstraints();

		gbc.insets=new Insets(50,100,50,0);

		JLabel bienvenido = new JLabel();
		bienvenido.setText("Seleccione una opcion");
		bienvenido.setFont(new Font("Verdana", Font.PLAIN, 30));
		gbc.gridy=0;
		add(bienvenido,gbc);




		JLabel plan = new JLabel(escalarIcono(iconoPlanear, 80,80));
		plan.setText("Planes Estudiante");
		plan.setFont(new Font("Verdana", Font.PLAIN, 20));
		plan.setSize(800, 200);
		gbc.gridy=05;

		plan.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				abrirPlanes(e);	
			}

		});
		add(plan,gbc);

		JLabel info = new JLabel(escalarIcono(iconoInfo, 80,80));
		info.setText("Info Estudiante");
		info.setFont(new Font("Verdana", Font.PLAIN, 20));
		info.setSize(000, 000);
		gbc.gridy=10;

		info.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				abrirHistorial(e);	
			}	
		});

		add(info,gbc);


		gbc.insets=new Insets(50,50,50,50);
		JLabel inscribir = new JLabel(escalarIcono(iconoInscribir, 80,80));
		inscribir.setText("Materias Inscritas");
		inscribir.setFont(new Font("Verdana", Font.PLAIN, 20));
		inscribir.setSize(800, 200);
		//gbc.insets=new Insets(0,0,0,0);
		//gbc.gridy=0;

		inscribir.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				abrirInscritas(e);	
			}
		});

		add(inscribir,gbc);
		
		
		JButton back = new JButton();
		back.setOpaque(false);
		back.setContentAreaFilled(false);
		back.setBorderPainted(false);
		back.setIcon(iconBack);
		back.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				main.switchPanel(panel, "adminMenuMain");
				
			}
			
		});
		add(back);
	}







	
	
	
	//--------------------------------
	//transition methods
	//------------------------------
	
	public void abrirHistorial(MouseEvent e) {
		
		main.switchPanel(panel, "estInfo");
	
	}	
	
	public void abrirInscritas(MouseEvent e) {
		
		main.switchPanel(panel, "adminInscritas");
	
	}	
	public void abrirPlanes(MouseEvent e) {
		
		main.switchPanel(panel, "estPlan");
	
	}	
	
	
	private ImageIcon escalarIcono(ImageIcon src, int w, int h){
	Image image = src.getImage(); // transform it 
	Image newimg = image.getScaledInstance(w,h,  java.awt.Image.SCALE_SMOOTH); // scale it the smooth way  
	ImageIcon resize = new ImageIcon(newimg);

    return resize;
}

	
	
}
