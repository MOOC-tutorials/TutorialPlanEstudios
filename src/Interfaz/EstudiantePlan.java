package Interfaz;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Iterator;

import javax.swing.*;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;

import Console.Logic_Console;
import Planner.Curso;
import Planner.Estudiantes;
import Planner.Plan;



public class EstudiantePlan extends JPanel{


	JPanel panel;
	InterfazPrincipal main;
	private Logic_Console Logica;
	 
	ImageIcon iconAdd = new ImageIcon("data/Botones/btnAdd32px.png");
	ImageIcon iconBack = new ImageIcon("data/Botones/btnBack.png");
	
	public EstudiantePlan(JPanel p, Logic_Console pConsole, InterfazPrincipal pInterfazPrincipal) {
		main = pInterfazPrincipal;
		Logica = pConsole; 
		
		panel= p;
		setLayout(new GridBagLayout());
		
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.insets = new Insets(5,0,5,0);

		JLabel bienvenido = new JLabel();
		bienvenido.setText("Planear");
		bienvenido.setFont(new Font("Cooper Black", Font.PLAIN, 28));
		bienvenido.setSize(800, 000);
		gbc.gridx = 0;
		gbc.gridy = 0;
		add(bienvenido,gbc);


		JPanel crearPanel = new JPanel();
		crearPanel.setLayout(new BoxLayout(crearPanel, BoxLayout.X_AXIS));
		JButton crear = new JButton();
		crear.setOpaque(false);
		crear.setContentAreaFilled(false);
		crear.setBorderPainted(false);
		crear.setIcon(iconAdd);
		
		crear.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				abrirPlanNuevo();
				
			}


			
		});
		
		crearPanel.add(crear);
		JLabel crearLabel = new JLabel();
		crearLabel.setText("Crear Nuevo Plan");
		crearLabel.setFont(new Font("Arial", Font.PLAIN, 14));
	
		crearLabel.addMouseListener(new MouseListener() {

			@Override
			public void mouseClicked(MouseEvent e) {
				abrirPlanNuevo();
				
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
			
		}
		);
		
		
		crearPanel.add(crearLabel);
		gbc.gridx =0;
		gbc.gridy = 1;
		add(crearPanel, gbc);
		
		JButton back = new JButton();
		back.setOpaque(false);
		back.setContentAreaFilled(false);
		back.setBorderPainted(false);
		back.setIcon(iconBack);
		back.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				
				back(e);
			}
			
		});
		gbc.gridx = 0;
		gbc.gridy = 4;
		add(back, gbc);
		
		JPanel PanleDisplayer = new JPanel();
		PanleDisplayer.updateUI();
		PanleDisplayer.setLayout(new BoxLayout(PanleDisplayer, BoxLayout.Y_AXIS));
		showPlanes(PanleDisplayer); 
		PanleDisplayer.updateUI();
		JScrollPane PanleDisplayerScrollable = new JScrollPane(PanleDisplayer);
		PanleDisplayerScrollable.setPreferredSize(new Dimension(500,320));
		PanleDisplayerScrollable.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
		gbc.gridx= 0;
		gbc.gridy = 2;
		add(PanleDisplayerScrollable, gbc);
		
		
		JLabel extra = new JLabel(); 
		if(Logica.darEstudiante().darPromedioAcumulado()>=4) {
			extra.setText("Recuerda que: "
					+ "\n Puedes inscribir 25 creditos" );
		}else {
			extra.setText("Recuerda que:"
					+ "\n Puedes inscribir 20 creditos");
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
		p.updateUI();
		if (numPlanes == 0) {
			System.out.println("NOP PLanes");
			p.updateUI();
		}else if (numPlanes==1) {
			p.updateUI();
			String NombrePlan = e1.darPlanes().get(0).darNombre();
			JTextArea txt = new JTextArea();
			Plan plan = e1.darPlanes().get(0);
			String info="";
			Iterator<Curso> iCurso = e1.darPlanes().get(0).darCursos().iterator();
			while(iCurso.hasNext()) {
				Curso cA = iCurso.next();
				if(cA!=null) {
				info = info +" \n"+ cA.darNombre()+"  -  "+cA.darCodigo() ;} 
			}
			txt.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED), NombrePlan, TitledBorder.LEFT, TitledBorder.DEFAULT_POSITION));

			txt.setText( "Nombre - Codigo" + info);
			txt.setEditable(false);
			p.add(txt);
			
			JButton seleccionar = new JButton("Seleccionar");
			seleccionar.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {
					main.cards.add(new EstudianteEditarPlan(main.cards, main, plan),"estEditarPlan");
					main.switchPanel(panel, "estEditarPlan");					
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
			txt.updateUI();

			p.add(eliminar);
			
			
			
		}else if (numPlanes==2) {
			p.updateUI();
			String NombrePlan = e1.darPlanes().get(0).darNombre();
			JTextArea txt = new JTextArea();
			Plan plan = e1.darPlanes().get(0);
			String info="";
			Iterator<Curso> iCurso = e1.darPlanes().get(0).darCursos().iterator();
			while(iCurso.hasNext()) {
				Curso cA = iCurso.next();
				info = info +" \n"+ cA.darNombre()+"  -  "+cA.darCodigo() ; 
			}
			txt.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED), NombrePlan, TitledBorder.LEFT, TitledBorder.DEFAULT_POSITION));
			txt.setText( "Nombre - Codigo" + info);
			txt.setEditable(false);
			p.add(txt);
			
			JButton seleccionar = new JButton("Seleccionar");
			seleccionar.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {
					main.cards.add(new EstudianteEditarPlan(main.cards, main, plan),"estEditarPlan");
					main.switchPanel(panel, "estEditarPlan");					
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
				info2 = info2 +" \n"+ cA2.darNombre()+"  -  "+cA2.darCodigo() ; 
			}
			txt2.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED), NombrePlan2, TitledBorder.LEFT, TitledBorder.DEFAULT_POSITION));
			txt2.setText( "Nombre - Codigo" + info2);
			txt2.setEditable(false);
			p.add(txt2);
			Plan plan2 = e1.darPlanes().get(1);

			JButton seleccionar2 = new JButton("Seleccionar");
			seleccionar2.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {
					main.cards.add(new EstudianteEditarPlan(main.cards, main, plan2),"estEditarPlan");
					main.switchPanel(panel, "estEditarPlan");					
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
			txt.updateUI();

			p.add(eliminar2);
			
			
		}else if (numPlanes==3){
			p.updateUI();
			String NombrePlan = e1.darPlanes().get(0).darNombre();
			JTextArea txt = new JTextArea();
			Plan plan = e1.darPlanes().get(0);
			String info="";
			Iterator<Curso> iCurso = e1.darPlanes().get(0).darCursos().iterator();
			while(iCurso.hasNext()) {
				Curso cA = iCurso.next();
				info = info +" \n"+ cA.darNombre()+"  -  "+cA.darCodigo() ; 
			}
			txt.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED), NombrePlan, TitledBorder.LEFT, TitledBorder.DEFAULT_POSITION));
			txt.setText("Nombre - Codigo" + info);
			txt.setEditable(false);
			p.add(txt);
			
			JButton seleccionar = new JButton("Seleccionar");
			seleccionar.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {
					main.cards.add(new EstudianteEditarPlan(main.cards, main, plan),"estEditarPlan");
					main.switchPanel(panel, "estEditarPlan");					
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
				info2 = info2 +" \n"+ cA2.darNombre()+"  -  "+cA2.darCodigo() ; 
			}
			txt2.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED), NombrePlan2, TitledBorder.LEFT, TitledBorder.DEFAULT_POSITION));
			txt2.setText("Nombre - Codigo" + info2);
			txt2.setEditable(false);
			p.add(txt2);
			Plan plan2 = e1.darPlanes().get(1);

			JButton seleccionar2 = new JButton("Seleccionar");
			seleccionar2.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {
					main.cards.add(new EstudianteEditarPlan(main.cards, main, plan2),"estEditarPlan");
					main.switchPanel(panel, "estEditarPlan");					
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
				info3 = info3 +" \n"+ cA3.darNombre()+"  -  "+cA3.darCodigo() ; 
			}
			txt3.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED), NombrePlan3, TitledBorder.LEFT, TitledBorder.DEFAULT_POSITION));
			txt3.setText( "Nombre - Codigo" + info3);
			txt3.setEditable(false);
			p.add(txt3);
			
			JButton seleccionar3 = new JButton("Seleccionar");
			seleccionar3.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {
					main.cards.add(new EstudianteEditarPlan(main.cards, main, plan3),"estEditarPlan");
					main.switchPanel(panel, "estEditarPlan");					
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
			txt.updateUI();

			p.add(eliminar3);


		}

	
				
			
		}

	public void abrirPlan2(ActionEvent e) {

		main.switchPanel(panel, "estMenu");
		
	}
	private void abrirPlanNuevo() {
		Estudiantes e1 = Logica.darEstudiante();
		if (e1.darPlanes().size() < 3) {
		main.cards.add(new EstudianteNewvoPlan(main.cards, main),"estNuevoPlan");
		main.switchPanel(panel, "estNuevoPlan");
		}else {
			JOptionPane.showMessageDialog(this, "Planes Maximos alcazados");
			System.out.println("Planes Maximos Alcanzados");
		}
		
	}

	private void back(ActionEvent e) {
		main.switchPanel(panel, "estMenu");
	}



}
