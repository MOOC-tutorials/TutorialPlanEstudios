package Interfaz;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.io.File;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import Console.Logic_Console;
import Planner.Estudiantes;

public class EstudianteAnadir  extends JPanel {
	JPanel panel;
	InterfazPrincipal main;
	private Logic_Console Logica;
	private Estudiantes e1; 
	final JFileChooser fc = new JFileChooser();
	ImageIcon iconBack = new ImageIcon("data/Botones/btnBack.png");
	
	public EstudianteAnadir(JPanel p, InterfazPrincipal pInterfazPrincipal) {
		
		main = pInterfazPrincipal;
		panel= p;
		Logica = main.darLogic_Console(); 
		setLayout(new GridBagLayout());
		e1 = Logica.darEstudiante();
		
		GridBagConstraints gbc = new GridBagConstraints();
		
		JLabel Titulo = new JLabel();
		Titulo.setText("A�adir");
		gbc.gridx =0;
		gbc.gridy =0;
		
		add(Titulo, gbc);
		
		JButton CargarPensum = new JButton();
		CargarPensum.setText("CargarPensum");
		CargarPensum.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				
				abrirPensum();
			}
			
		});
		gbc.gridx =1;
		gbc.gridy =0;
		add(CargarPensum,gbc);
		
		JButton back = new JButton();
		back.setOpaque(false);
		back.setContentAreaFilled(false);
		back.setBorderPainted(false);
		back.setIcon(iconBack);
		

		
		back.addActionListener(new ActionListener() {
	            public void actionPerformed(ActionEvent e) {
	            	main.switchPanel(panel, "estMenu");
	            }
		});
		gbc.gridx =0;
		gbc.gridy =1;
		add(back, gbc);
		
		JButton CargarHologaciones = new JButton();
		CargarHologaciones.setText("Homologacioens");
		CargarHologaciones.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				cargarHomologables();
				
			}
			
		});
		gbc.gridx =1;
		gbc.gridy =1;
		add(CargarHologaciones, gbc);
	}
	
	
	public void abrirPensum() {

		fc.setCurrentDirectory(new File("docs/Pensum"));
		fc.showOpenDialog(panel);
		File source = fc.getSelectedFile();
		Logica.cargarPensumSugerido(source);
		
		JOptionPane.showMessageDialog(this, "Archivo Cargado Con exito");

	}	
	
	
	public void cargarHomologables() {
		fc.setCurrentDirectory(new File("docs/Pensum"));
		fc.showOpenDialog(panel);
		File sourcePensum = fc.getSelectedFile();
		fc.setCurrentDirectory(new File("docs/Pensum"));
		fc.showOpenDialog(panel);
		File sourceHomologaciones = fc.getSelectedFile();
		
		Logica.homologarCuros(sourcePensum, sourceHomologaciones);
	}
}
