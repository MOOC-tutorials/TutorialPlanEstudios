package Interfaz;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Iterator;

import javax.swing.*;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;

import Console.Logic_Console;
import Planner.Curso;
import Planner.Estudiantes;
import Planner.Semestre;

import java.awt.event.*;



public class EstudianteInfo extends JPanel implements ItemListener{

	JPanel panel;
	InterfazPrincipal main;
	String[] semestresTest = {};
	//String[] semestresTest = { "2018-2", "2019-1", "2019-2", "2020-1", "2020-2" };
	
	JComboBox c1;
	String labelPeriodo= "Periodo Actual: ";
	ImageIcon icono = new ImageIcon("data/central/iconoPersona.PNG");
	private Logic_Console Logica; 
	private Estudiantes e1; 
	
	public EstudianteInfo(JPanel p, InterfazPrincipal pInterfazPrincipal) {
		main = pInterfazPrincipal;
		panel= p;
		Logica = main.darLogic_Console(); 
		setLayout(new GridBagLayout());
		GridBagConstraints gbc = new GridBagConstraints();
		e1 = Logica.darEstudiante();

		gbc.insets=new Insets(5,5,5,5);
		JPanel aux = new JPanel(); 
		aux.setLayout(new BoxLayout(aux, BoxLayout.X_AXIS));
		JLabel periodol= new JLabel(labelPeriodo);
		
		aux.add(periodol);
		semestresTest = darSemestres(e1);
        JComboBox c1 = new JComboBox(semestresTest);
        c1.setSize(50, 10);
        c1.addItemListener(this);
        c1.setToolTipText(labelPeriodo);
        
        aux.add(c1);
        gbc.gridx =1;
		gbc.gridy =0;
		add(aux,gbc);

        

		
		

		JLabel plan = new JLabel(escalarIcono(icono,150,150));
		gbc.gridx =2;
		gbc.gridy =1;
		add(plan,gbc);
		
		
		
		
		
		JTextArea field = new JTextArea();
		field.setEditable(false);
		field.setText(darInfoEstudiante());
		JScrollPane scrollInfo = new JScrollPane(field);
		scrollInfo.setPreferredSize(new Dimension(200,300));
		scrollInfo.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
		scrollInfo.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED), "Datos Personales", TitledBorder.LEFT, TitledBorder.DEFAULT_POSITION));
		gbc.gridx =1;
		gbc.gridy =1;
       
		add(scrollInfo,gbc);
       
	
       	c1.setSelectedIndex(semestresTest.length-1);

       	JPanel cambiante = new JPanel();
   		updateUI();
   		
   		cambiante.removeAll();
		cambiante.updateUI();
		String action = (String) c1.getItemAt(1);
	    JTextArea field2 = new JTextArea();
	    String r = darStringPlanActual(action);
	    field2.setText(r);
	    field2.setEditable(false);
	    JScrollPane field2Scrollabel = new JScrollPane(field2);
	    field2Scrollabel.setPreferredSize(new Dimension(200,300));
		scrollInfo.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED), "Informacion Semestres(Cambia)", TitledBorder.LEFT, TitledBorder.DEFAULT_POSITION));
	    field2Scrollabel.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
	    field2Scrollabel.updateUI();
	    cambiante.add(field2Scrollabel);
	    cambiante.updateUI();
		
	    gbc.gridx =3;
		gbc.gridy =1;
	    add(cambiante, gbc);
       
       
       c1.addActionListener(new ActionListener() {	
			@Override
			public void actionPerformed(ActionEvent e) {
				String action = (String) c1.getSelectedItem();
				if (semestresTest.length==1) {
					updateUI();
					cambiante.removeAll();
					cambiante.updateUI();
			        JTextArea field2 = new JTextArea();
			        String r = darStringPlanActual(action);
			        field2.setText(r);
			        field2.setEditable(false);
			        JScrollPane field2Scrollabel = new JScrollPane(field2);
			        field2Scrollabel.setPreferredSize(new Dimension(200,300));
			        field2Scrollabel.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
			        field2Scrollabel.updateUI();
			        cambiante.add(field2Scrollabel);
			        cambiante.updateUI();

				}else {
				updateUI();
				for(int i =0; i<semestresTest.length;i++) {
					if(semestresTest[i].equals(action)) {
						updateUI();
						cambiante.removeAll();
						cambiante.updateUI();
				        JTextArea field2 = new JTextArea();
				        String r = darStringPlanActual(action);
				        field2.setText(r);
				        field2.setEditable(false);
				        JScrollPane field2Scrollabel = new JScrollPane(field2);
				        field2Scrollabel.setPreferredSize(new Dimension(200,300));
				        field2Scrollabel.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
				        field2Scrollabel.updateUI();
				        cambiante.add(field2Scrollabel);
				        cambiante.updateUI();
					}
				}
				
			}}
			
		});

        
    

		JButton siguiente = new JButton();
		siguiente.setText("Regresar");
		siguiente.setFont(new Font("Verdana", Font.PLAIN, 15));

		siguiente.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				abrirNEXT(e);	
			}
		});
		gbc.gridx =1;
		gbc.gridy =3;
		add(siguiente,gbc);
		

		
		ArrayList<Curso> LstFaltantes = Logica.CandidatoGrado(Logica.darEstudiante(),Logica.darPensumSugerido());
		JButton btnCandidato = new JButton();
		if (e1.isCandidato()) {
			btnCandidato.setText("Candidato "
					+ "\n a Grado: True");
			btnCandidato.setBackground(new Color(105, 181, 120));
		}else {
			btnCandidato.setText("Candidato a Grado: False");
		}
		btnCandidato.setFont(new Font("Verdana", Font.BOLD, 12));
		btnCandidato.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				abirCandidato(e);	
			}

			public void abirCandidato(ActionEvent e) {
				main.cards.add(new CandiatoAGrado(main.cards, main, LstFaltantes), "Candidato");
				main.switchPanel(panel, "Candidato");
			}
		});
		gbc.gridx =2;
		gbc.gridy =3;
		add(btnCandidato,gbc);
		
		
		JButton btnReporte = new JButton();
		btnReporte.setText("Reporte de Notas");
		btnReporte.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				main.cards.add(new ReporteNotas(main.cards, main), "Reporte");
				main.switchPanel(panel, "Reporte");
			}				
		});
		gbc.gridx =3;
		gbc.gridy =3;
		add(btnReporte, gbc);
	
	}



		
	private String[] darSemestres(Estudiantes eActual) {
		String[] semestres = {}; 
		String r ="";
		System.out.println(eActual.darHistoricoSemestres().size()+"  Sise: ");

		Iterator<Semestre> iSemestre =eActual.darHistoricoSemestres().iterator();
		while(iSemestre.hasNext()) {
			Semestre sActual = iSemestre.next();
			System.out.println(sActual.darPeriodo());
			if((!(r.contains(sActual.darPeriodo())))){
			r+=sActual.darPeriodo()+";";
		}}
		semestres = r.split(";");
		if (semestres.length ==0) {
			semestres[0]="2021-1";
		}return semestres;
	}


	private String darInfoEstudiante() {
	
		String r ="";
		r =  r+"\n"+"Nombre: " +e1.darNombre();
		r =  r+"\n"+"Codigo: " +String.valueOf(e1.darCodigo());
		r =  r+"\n"+"LogIn: "+ e1.darLogin()+"@uniandes.edu.co";
		r =  r+"\n"+"PromAcumulado: " +String.valueOf(e1.darPromedioAcumulado());
		return r;
	}


	private String darStringPlanActual(String pPeriodo) {
		ArrayList<Semestre> semestreLst = e1.darHistoricoSemestres();
		String r = ""; 
		int i =0;
		if (semestreLst.size()==1) {
			updateUI();
			Iterator<Curso> iCurso = e1.darHistoricoSemestres().get(0).darInscritos().iterator();
			while(iCurso.hasNext()) {
				Curso cA = iCurso.next();
				r = r+"\n"+cA.darNombre()+"; "+String.valueOf(cA.darCodigo()) + "      Nota: " +String.valueOf(cA.darNota()); 
		}}else {
			
		Iterator<Semestre> iSemestre = semestreLst.iterator();
		while(iSemestre.hasNext()) {
			Semestre sActual = iSemestre.next();
			if (sActual.darPeriodo().equals(pPeriodo)) {
				Iterator<Curso> iCurso = e1.darHistoricoSemestres().get(i).darInscritos().iterator();
				while(iCurso.hasNext()) {
					Curso cA = iCurso.next();
					r = r+"\n"+cA.darNombre()+"; "+String.valueOf(cA.darCodigo()) + "      Nota: " +String.valueOf(cA.darNota()); 
			}}
			i++;
			updateUI();
		}
		}
		
		
		return r;
		
		
	}






	//--------------------------------
	//transition methods
	//------------------------------

	public void abrirNEXT(ActionEvent e) {

		main.switchPanel(panel, "estMenu");

	}	
	
	
	public void itemStateChanged(ItemEvent e)
    {
 
        if (e.getSource() == c1) {
 
           System.out.println("Se selecciono" + c1.getSelectedItem());
        }
    }
	
	
	
	private ImageIcon escalarIcono(ImageIcon src, int w, int h){
	Image image = src.getImage(); // transform it 
	Image newimg = image.getScaledInstance(w,h,  java.awt.Image.SCALE_SMOOTH); // scale it the smooth way  
	ImageIcon resize = new ImageIcon(newimg);

    return resize;
	}

}
