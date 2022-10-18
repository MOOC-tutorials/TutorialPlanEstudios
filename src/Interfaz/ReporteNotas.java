package Interfaz;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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

public class ReporteNotas extends JPanel{
	
	JPanel panel;
	InterfazPrincipal main;
	private Logic_Console Logica; 
	private Estudiantes e1; 
	int Aprobadas=0;
	int Reprobadas=0;
	ImageIcon iconBack = new ImageIcon("data/Botones/btnBack.png");
	
	public ReporteNotas(JPanel p, InterfazPrincipal pInterfazPrincipal) {
		panel = p; 
		main = pInterfazPrincipal; 
		Logica = main.darLogic_Console();
		e1 =Logica.darEstudiante(); 
		
		JPanel r = new JPanel();
		r.setLayout(new BoxLayout(r, BoxLayout.Y_AXIS));
		JLabel reporteLabel = new JLabel();
		reporteLabel.setText("Reporte Notas              "+e1.darNombre());
		r.add(reporteLabel);
		JTextArea sReporte= new JTextArea();
		sReporte.setText(darReporte());
		sReporte.setEditable(false);
		JScrollPane sRScrollable = new JScrollPane(sReporte);
		sRScrollable.setPreferredSize(new Dimension(300,250));
		sRScrollable.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
		
		add(sRScrollable);
		JButton back = new JButton();
		back.setOpaque(false);
		back.setContentAreaFilled(false);
		back.setBorderPainted(false);
		back.setIcon(iconBack);
		back.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				
				main.switchPanel(panel,"estInfo");
			}
			
		});
		add(back);
		
	}

	private String darReporte() {
		String a= "Aprobados ";
		String r="\n \n Reprobados ";
		Iterator<Curso> iCurso = e1.darCursosAprobados().iterator();
		while(iCurso.hasNext()) {
			Curso cA = iCurso.next();
			if (cA.darNota()<3) {
				r+= "\n"+cA.darNombre()+";   Nota: "+String.valueOf(cA.darNota());			
				}else {
					a+= "\n"+cA.darNombre()+";   Nota: "+String.valueOf(cA.darNota());		
					}
				
		}
		
		return (a+r);
	}

}
