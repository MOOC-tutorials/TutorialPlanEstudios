package Interfaz;

import javax.swing.JLabel;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.GridBagLayout;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;

public class PanelSuperior extends JPanel{


	// -----------------------------------------------------------------
	// Atributos de Interfaz
	// -----------------------------------------------------------------

	private JLabel imagen;




	// -----------------------------------------------------------------
	// Constructor
	// -----------------------------------------------------------------

	public PanelSuperior () {

		GridBagLayout layout = new GridBagLayout( );
		setLayout( layout );

		// Carga la imagen
		ImageIcon icono = new ImageIcon( "data/superior/Superior.png" );

		// La agrega a la etiqueta
		imagen = new JLabel( "" );
		imagen.setIcon(escalarIcono(icono, 850,150) );
		add( imagen );

		// Color de fondo blanco
		setBackground( Color.WHITE );
		setBorder( new LineBorder( Color.GRAY ) );

	}
	
	
	private ImageIcon escalarIcono(ImageIcon src, int w, int h){
		Image image = src.getImage(); // transform it 
		Image newimg = image.getScaledInstance(w,h,  java.awt.Image.SCALE_SMOOTH); // scale it the smooth way  
		ImageIcon resize = new ImageIcon(newimg);

		return resize;
	}
}
