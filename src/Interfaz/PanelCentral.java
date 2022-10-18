package Interfaz;


import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

public class PanelCentral extends JPanel{

	JPanel panel;
	InterfazPrincipal main;
	
	public PanelCentral(JPanel pnel, InterfazPrincipal pInterfazPrincipal) {
		main = pInterfazPrincipal;
		//cards=Pcards;
		panel=pnel;
		
		setLayout(new GridBagLayout());
		GridBagConstraints gbc = new GridBagConstraints();



		JLabel bienvenido = new JLabel();
		bienvenido.setText("BIENVENIDO");
		bienvenido.setFont(new Font("Verdana", Font.PLAIN, 30));
		bienvenido.setSize(800, 000);
		gbc.insets=new Insets(-200,0,0,0);
		//gbc.anchor = GridBagConstraints.NORTH;
		add(bienvenido,gbc);




		JLabel bienvenido2 = new JLabel();
		bienvenido2.setText("Escoja perfil para iniciar Sesion");
		bienvenido2.setFont(new Font("Verdana", Font.PLAIN, 14));
		bienvenido.setSize(800, 000);
		gbc.insets=new Insets(-150,0,0,0);
		gbc.gridy=200;

		add(bienvenido2,gbc);



		JButton estd = new JButton();
		estd.setText("ESTUDIANTE");
		estd.setFont(new Font("Verdana", Font.PLAIN, 30));
		estd.setSize(800, 200);
		gbc.insets=new Insets(50,0,0,0);
		gbc.gridy=400;
		
		estd.addActionListener(new ActionListener() {
	            public void actionPerformed(ActionEvent e) {
	            	abrirEstudiante(e);	
	            }
		});
		
		add(estd,gbc);



		JButton admin = new JButton();
		admin.setText("    ADMIN    ");
		admin.setFont(new Font("Verdana", Font.PLAIN, 30));
		admin.setSize(800, 200);
		gbc.insets=new Insets(50,0,0,0);
		gbc.gridy=600;
		
		admin.addActionListener(new ActionListener() {
			
            public void actionPerformed(ActionEvent e) {
            	abrirAdmin(e);
            }
	});
	
		
		add(admin,gbc);
	}


	
	//--------------------------------
	//transition methods
	//------------------------------
	
	public void abrirAdmin(ActionEvent e) {
		
		System.out.println("TEST SUCCESS");
		main.switchPanel(panel, "adminLogin");
		
	}
	
	public void abrirEstudiante(ActionEvent e) {
		
		main.switchPanel(panel, "estLogin");
		
	}
	

}






