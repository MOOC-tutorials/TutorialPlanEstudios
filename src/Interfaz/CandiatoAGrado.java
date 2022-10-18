package Interfaz;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Image;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Iterator;

import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.ScrollPaneConstants;

import Console.Logic_Console;
import Planner.Curso;
import Planner.Estudiantes;

public class CandiatoAGrado extends JPanel {
	private JPanel panel;
	private JTextArea CurosAprobados;
	private JTextArea CursosFaltantes; 
	private InterfazPrincipal main;
	private Logic_Console Logica; 
	private Estudiantes e1; 
	
	public CandiatoAGrado(JPanel p, InterfazPrincipal pInterfazPrincipal, ArrayList<Curso> Faltantes) {
		main = pInterfazPrincipal;
		panel= p;
		Logica = main.darLogic_Console(); 
		e1 = Logica.darEstudiante();
		
		JPanel Botones = new JPanel();
		Botones.setLayout(new BoxLayout(Botones, BoxLayout.Y_AXIS));
		
		JButton back = new JButton();
		ImageIcon i = new ImageIcon("data/Botones/btnBack.png");
		back.setOpaque(false);
		back.setContentAreaFilled(false);
		back.setBorderPainted(false);
		back.setIcon(i);
	
		back.addActionListener(new ActionListener() {
	            public void actionPerformed(ActionEvent e) {
	            	Menuanterior(e);	
	            }

				private void Menuanterior(ActionEvent e) {
					main.switchPanel(panel, "estMenu");
				}
		});
		JButton home = new JButton();
		ImageIcon i2 = new ImageIcon("data/Botones/btnHome.png");
		home.setOpaque(false);
		home.setContentAreaFilled(false);
		home.setBorderPainted(false);
		home.setIcon(i2);
	
		home.addActionListener(new ActionListener() {
	            public void actionPerformed(ActionEvent e) {
	            	Menuanterior(e);	
	            }

				private void Menuanterior(ActionEvent e) {
					main.switchPanel(panel, "estInfo");
				}
		});
		Botones.add(home); 


		Botones.add(back); 
		
		
		add(Botones);
		
		JPanel Aprobadas = new JPanel(); 
		Aprobadas.setLayout(new BoxLayout(Aprobadas, BoxLayout.Y_AXIS));
		//Aprobadas.setLayout(null);
		JLabel cumplido = new JLabel();
		cumplido.setText("Cumplidos");
		cumplido.setOpaque(true);
		cumplido.setBounds(cumplido.getX(), cumplido.getY(), 50, 20);;
		cumplido.setBackground(Color.black);
		cumplido.setForeground(Color.white);
		cumplido.setFont(new Font("Cooper Black", Font.PLAIN, 14));
		
		
		CurosAprobados = new JTextArea();
		//CurosAprobados.setSize(CurosAprobados.getWidth(), 250);
		CurosAprobados.setText(DarCursosAprobado());
		CurosAprobados.setFont(new Font("Century Gothic", Font.PLAIN, 12));
		JScrollPane scrollCurosAprobados = new JScrollPane(CurosAprobados);
		scrollCurosAprobados.setPreferredSize(new Dimension(360,480));
		scrollCurosAprobados.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
		
		Aprobadas.add(cumplido);
		Aprobadas.add(scrollCurosAprobados);
		add(Aprobadas);
		
		
		
		JPanel Faltantes1 = new JPanel(); 
		Faltantes1.setLayout(new BoxLayout(Faltantes1, BoxLayout.Y_AXIS));
		JLabel labelFaltantes = new JLabel();
		labelFaltantes.setText("Faltantes");
		labelFaltantes.setOpaque(true);
		labelFaltantes.setBackground(Color.black);
		labelFaltantes.setForeground(Color.white);
		labelFaltantes.setFont(new Font("Cooper Black", Font.PLAIN, 14));
		
		
		CursosFaltantes = new JTextArea(); 
		CursosFaltantes.setText(DarCursosFaltantes( Faltantes));
		CursosFaltantes.setFont(new Font("Century Gothic", Font.PLAIN, 12));
		
		JScrollPane scrollFaltantes= new JScrollPane(CursosFaltantes);
		scrollFaltantes.setPreferredSize(new Dimension(360,480));
		scrollFaltantes.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
		Faltantes1.add(labelFaltantes);
		Faltantes1.add(scrollFaltantes);
		add(Faltantes1);
		
		
		
		
	}

	private String DarCursosFaltantes(ArrayList<Curso> faltantes) {
		String r = ""; 
		Iterator<Curso> iCurso = faltantes.iterator();
		while(iCurso.hasNext()) {
			Curso cA = iCurso.next();
			r = r+"\n"+" → "+ cA.darNombre()+"; "+String.valueOf(cA.darCodigo()); 
		}
		return r;
	}

	private String DarCursosAprobado() {
		String r = ""; 
		Iterator<Curso> iCurso = e1.darCursosAprobados().iterator();
		while(iCurso.hasNext()) {
			Curso cA = iCurso.next();
			r = r+"\n"+" → "+cA.darNombre()+"; "+String.valueOf(cA.darCodigo()) + "      Nota: " +String.valueOf(cA.darNota()); 
		}
		return r;
	}
	
}
