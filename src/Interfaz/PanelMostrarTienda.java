package Interfaz;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;


public class PanelMostrarTienda extends JPanel {
	private JTextField txtNombre, txtPresentacion, txtAno, txtContenido, txtTipo, txtLugar, txtLugar;
	
	public PanelMostrarTienda() {
		setLayout(new GridBagLayout());
		setBorder(BorderFacory.createTitledBorder("Información de la tienda"));
		
		
	}
}
