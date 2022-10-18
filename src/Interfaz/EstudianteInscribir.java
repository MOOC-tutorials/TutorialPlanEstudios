package Interfaz;


import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Iterator;

import javax.swing.*;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;

import Console.Logic_Console;
import Planner.Curso;
import Planner.Estudiantes;
import Planner.Plan;





public class EstudianteInscribir extends JPanel{

	JPanel panel;
	InterfazPrincipal main;
	private Logic_Console Logica;

	ImageIcon iconBack = new ImageIcon("data/Botones/btnBack.png");
	
	public EstudianteInscribir(JPanel p, InterfazPrincipal pInterfazPrincipal) {
		
		Logica = pInterfazPrincipal.darLogic_Console();
		main = pInterfazPrincipal;
		panel= p;
		setLayout(new GridBagLayout());
		GridBagConstraints gbc = new GridBagConstraints();


		JLabel bienvenido = new JLabel();
		bienvenido.setText("Inscribir");
		bienvenido.setFont(new Font("Cooper Black", Font.PLAIN, 25));
		bienvenido.setSize(800, 000);
		gbc.gridx =0;
		gbc.gridy =0;
		gbc.insets=new Insets(0,0,5,0);
		add(bienvenido,gbc);


		
		JButton crear = new JButton();
		crear.setText("Inscribir Desde 0");
		crear.setFont(new Font("Verdana", Font.PLAIN, 15));
		crear.setSize(800, 200);
		gbc.gridx =0;
		gbc.gridy =1;
		gbc.insets=new Insets(5,0,20,0);
		crear.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				abrirPlanNuevo(e);
				
			}


			
		});
		add(crear,gbc);
		
		
		
		JPanel PanleDisplayer = new JPanel();
		PanleDisplayer.setLayout(new BoxLayout(PanleDisplayer, BoxLayout.Y_AXIS));
		showPlanes(PanleDisplayer); 
		PanleDisplayer.updateUI();
		JScrollPane PanleDisplayerScrollable = new JScrollPane(PanleDisplayer);
		PanleDisplayerScrollable.setPreferredSize(new Dimension(500,320));
		PanleDisplayerScrollable.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
		gbc.gridx = 0;
		gbc.gridy = 2;
		gbc.insets=new Insets(5,5,5,5);
		add(PanleDisplayerScrollable, gbc);
		
		
		JButton siguiente = new JButton();
		siguiente.setOpaque(false);
		siguiente.setContentAreaFilled(false);
		siguiente.setBorderPainted(false);
		siguiente.setIcon(iconBack);
		
	

		siguiente.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				abrirPlan2(e);	
			}
		});
		gbc.gridx = 0;
		gbc.gridy = 3;

		add(siguiente,gbc);
		
		JLabel extra = new JLabel(); 
		if(Logica.darEstudiante().darPromedioAcumulado()>=4) {
			extra.setText("Recuerda que puedes inscribir 25 creditos" );
		}else {
			extra.setText("Recuerda que puedes inscribir 20 creditos");
		}
		add(extra);

	}

	//--------------------------------
	//transition methods
	//------------------------------

	
	//Muestra La informacion al inetrior de los planes del estudiate.
	public void showPlanes(JPanel p) {
		int numPlanes = Logica.darEstudiante().darPlanes().size();
		Estudiantes e1 = Logica.darEstudiante();
		
		if (numPlanes == 0) {
			System.out.println("NOP PLanes");
		}else if (numPlanes==1) {
			
			String NombrePlan = e1.darPlanes().get(0).darNombre();
			JTextArea txt = new JTextArea();
			Plan plan = e1.darPlanes().get(0);
			String info="";
			Iterator<Curso> iCurso = e1.darPlanes().get(0).darCursos().iterator();
			while(iCurso.hasNext()) {
				Curso cA = iCurso.next();
				if(cA!=null) {
				info = info +" \n"+ cA.darNombre()+" "+cA.darCodigo() ; 
			}}
			txt.setText( info);
			txt.setEditable(false);
			txt.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED), NombrePlan, TitledBorder.LEFT, TitledBorder.DEFAULT_POSITION));

			p.add(txt);
			
			JButton seleccionar = new JButton("Seleccionar");
			seleccionar.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {
					main.cards.add(new EstudianteEditarInscribir(main.cards, main, plan, e1.darRegistro()),"estEditarInscribir");
					main.switchPanel(panel, "estEditarInscribir");					
				}
				
			});
			p.add(seleccionar);
			
			JButton eliminar = new JButton("Borrar");
			eliminar.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {
					Logica.eliminarPlan(e1, e1.darPlanes().get(e1.darPlanes().indexOf(plan)));
					p.remove(txt);
					p.remove(eliminar);
					p.remove(seleccionar);
					p.updateUI();
							}
				
			});
			p.add(eliminar);
			
			
			
		}else if (numPlanes==2) {
			
			String NombrePlan = e1.darPlanes().get(0).darNombre();
			JTextArea txt = new JTextArea();
			Plan plan = e1.darPlanes().get(0);
			String info="";
			Iterator<Curso> iCurso = e1.darPlanes().get(0).darCursos().iterator();
			while(iCurso.hasNext()) {
				Curso cA = iCurso.next();
				info = info +" \n"+ cA.darNombre()+" "+cA.darCodigo() ; 
			}
			txt.setText(  info);
			txt.setEditable(false);
			txt.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED), NombrePlan, TitledBorder.LEFT, TitledBorder.DEFAULT_POSITION));

			p.add(txt);
			
			JButton seleccionar = new JButton("Seleccionar");
			seleccionar.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {
					main.cards.add(new EstudianteEditarInscribir(main.cards, main, plan, e1.darRegistro()),"estEditarInscribir");
					main.switchPanel(panel, "estEditarInscribir");				
				}
				
			});
			p.add(seleccionar);
			
			JButton eliminar = new JButton("Borrar");
			eliminar.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {
					Logica.eliminarPlan(e1, e1.darPlanes().get(e1.darPlanes().indexOf(plan)));
					p.remove(txt);
					p.remove(eliminar);
					p.remove(seleccionar);
					p.updateUI();
							}
				
			});
			p.add(eliminar);
			
			
			//Dispay 2
			String NombrePlan2 = e1.darPlanes().get(1).darNombre();
			JTextArea txt2 = new JTextArea();
			
			String info2="";
			Iterator<Curso> iCurso2 = e1.darPlanes().get(1).darCursos().iterator();
			while(iCurso2.hasNext()) {
				Curso cA2 = iCurso2.next();
				info2 = info2 +" \n"+ cA2.darNombre()+" "+cA2.darCodigo() ; 
			}
			txt2.setText( info2);
			txt2.setEditable(false);
			txt2.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED), NombrePlan2, TitledBorder.LEFT, TitledBorder.DEFAULT_POSITION));

			p.add(txt2);
			Plan plan2 = e1.darPlanes().get(1);

			JButton seleccionar2 = new JButton("Seleccionar");
			seleccionar2.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {
					main.cards.add(new EstudianteEditarInscribir(main.cards, main, plan, e1.darRegistro()),"estEditarInscribir");
					main.switchPanel(panel, "estEditarInscribir");						
				}
				
			});
			p.add(seleccionar2);
			
			
			JButton eliminar2 = new JButton("Borrar");
			eliminar2.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {
					Logica.eliminarPlan(e1, e1.darPlanes().get(e1.darPlanes().indexOf(plan2)));
					p.remove(txt2);
					p.remove(eliminar2);
					p.remove(seleccionar2);
					p.updateUI();
							}
				
			});
			p.add(eliminar2);
			
			
		}else if (numPlanes==3){
			
			String NombrePlan = e1.darPlanes().get(0).darNombre();
			JTextArea txt = new JTextArea();
			Plan plan = e1.darPlanes().get(0);
			String info="";
			Iterator<Curso> iCurso = e1.darPlanes().get(0).darCursos().iterator();
			while(iCurso.hasNext()) {
				Curso cA = iCurso.next();
				info = info +" \n"+ cA.darNombre()+" "+cA.darCodigo() ; 
			}
			txt.setText(  info);
			txt.setEditable(false);
			txt.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED), NombrePlan, TitledBorder.LEFT, TitledBorder.DEFAULT_POSITION));

			p.add(txt);
			
			JButton seleccionar = new JButton("Seleccionar");
			seleccionar.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {
					main.cards.add(new EstudianteEditarInscribir(main.cards, main, plan, e1.darRegistro()),"estEditarInscribir");
					main.switchPanel(panel, "estEditarInscribir");			
				}
				
			});
			p.add(seleccionar);
			
			JButton eliminar = new JButton("Borrar");
			eliminar.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {
					Logica.eliminarPlan(e1, e1.darPlanes().get(e1.darPlanes().indexOf(plan)));
					p.remove(txt);
					p.remove(eliminar);
					p.remove(seleccionar);
					p.updateUI();
							}
				
			});
			p.add(eliminar);
			
			
			//Dispay 2
			String NombrePlan2 = e1.darPlanes().get(1).darNombre();
			JTextArea txt2 = new JTextArea();
			
			String info2="";
			Iterator<Curso> iCurso2 = e1.darPlanes().get(1).darCursos().iterator();
			while(iCurso2.hasNext()) {
				Curso cA2 = iCurso2.next();
				info2 = info2 +" \n"+ cA2.darNombre()+" "+cA2.darCodigo() ; 
			}
			txt2.setText(  info2);
			txt2.setEditable(false);
			txt2.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED), NombrePlan2, TitledBorder.LEFT, TitledBorder.DEFAULT_POSITION));

			p.add(txt2);
			Plan plan2 = e1.darPlanes().get(1);

			JButton seleccionar2 = new JButton("Seleccionar");
			seleccionar2.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {
					main.cards.add(new EstudianteEditarInscribir(main.cards, main, plan, e1.darRegistro()),"estEditarInscribir");
					main.switchPanel(panel, "estEditarInscribir");				
				}
				
			});
			p.add(seleccionar2);
			
			
			JButton eliminar2 = new JButton("Borrar");
			eliminar2.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {
					Logica.eliminarPlan(e1, e1.darPlanes().get(e1.darPlanes().indexOf(plan2)));
					p.remove(txt2);
					p.remove(eliminar2);
					p.remove(seleccionar2);
					p.updateUI();
							}
				
			});
			p.add(eliminar2);
			
			//Display 3
			String NombrePlan3 = e1.darPlanes().get(2).darNombre();
			JTextArea txt3 = new JTextArea();
			Plan plan3 = e1.darPlanes().get(2);

			String info3="";
			Iterator<Curso> iCurso3 = e1.darPlanes().get(2).darCursos().iterator();
			while(iCurso3.hasNext()) {
				Curso cA3 = iCurso3.next();
				info3 = info3 +" \n"+ cA3.darNombre()+" "+cA3.darCodigo() ; 
			}
			txt3.setText( info3);
			txt3.setEditable(false);
			txt3.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED), NombrePlan3, TitledBorder.LEFT, TitledBorder.DEFAULT_POSITION));
			p.add(txt3);
			
			JButton seleccionar3 = new JButton("Seleccionar");
			seleccionar3.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {
					main.cards.add(new EstudianteEditarInscribir(main.cards, main, plan, e1.darRegistro()),"estEditarInscribir");
					main.switchPanel(panel, "estEditarInscribir");				
				}
				
			});
			p.add(seleccionar3);
			
			JButton eliminar3 = new JButton("Borrar");
			eliminar3.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {
					Logica.eliminarPlan(e1, e1.darPlanes().get(e1.darPlanes().indexOf(plan3)));
					p.remove(txt3);
					p.remove(eliminar3);
					p.remove(seleccionar3);
					p.updateUI();
							}
				
			});
			p.add(eliminar3);
			
		}

	

				
			
		}

	public void abrirPlan2(ActionEvent e) {

		main.switchPanel(panel, "estMenu");
		
	}
	private void abrirPlanNuevo(ActionEvent e) {
		Estudiantes e1 = Logica.darEstudiante();
		if (e1.darPlanes().size() < 3) {
		main.cards.add(new EstudianteNuevaInscripcion(main.cards, main),"estNuevaInscrip");
		main.switchPanel(panel, "estNuevaInscrip");
		}else {
			System.out.println("Planes Maximos Alcanzados");
		}
		
	}



	public void abrirInscribir2(ActionEvent e) {
		
		main.switchPanel(panel, "estBuscarInscripcion");
	
	}	
	
	
}
