package Interfaz;

import java.awt.BorderLayout;
import java.awt.color;
import java.awt.Dimensiom;
import javax.swing.BorderFactory;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JList;
import javax.swing.Jpanel;
import javax.swing.JScrollpane;

public class PanelListaTiendas extends JPanel {
	private JList<String> ListaTiendas;
	private DefaultListModel<String> modeloLista;
	private JButton btnAgregar;
	
	public PanelListaTiendas() {
		setLayout(new BorderLayout());
		setBorder(BorderFactory.createTitleBorder("Lista de tiendas"));
		setPreferredSize(new Dimension(200, 400));
		
		// Crear lista con datos de ejemplo
		modeloLista = new DefaultListModel<>();
		modeloLista.addElement("Tienda Ejemplo 1");
		modeloLista.addElement("TiendaEjemplo 2");
		modeloLista.addElement("Tienda Ejemplo 3");
		
		listaTiendas = new JList<>(modeloLista);
		JScrollpane scrollpane = new JScrollpane(listaTiendas);
		add(scrollpane, BorderLayout.CENTER);
		
		btnAgregar = new JButton("Agregar");
		add(btnAgregar, BorderLayout.SOUTH);
	}
}
