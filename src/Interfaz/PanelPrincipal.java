package Interfaz;

import java.awt.BorderLayout;
import javax.swing.JFrame;

public class PanelPrincipal extends JFrame {
	public static final String DB_URL = "jdbc:sqlite:tiendas.db";
	
	public PanelImagen panelImagen;
	public PanelBanner panelBanner;
	public PanelMostrarTienda mostrarTienda;
	public PanelOpciones panelOpciones;
	public PanelListaTiendas panelListaTiendas;
	
	/* constructor */
	public PanelPrincipal() {
		//cargarTiendas();
		
		setLayout(new BorderLayout());
		setSize(700, 550);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setTitle("Directorio de tiendas");
		setLocationRelativeTo(null);
		setResizable(false);
		
		//Crear y agregar los paneles en las posiciones correctas
		panelBanner = new PanelBanner();
		add(panelBanner, BorderLayout.NORTH);
		
		mostrarTienda = new PanelMostrarTienda();
		add(mostrarTienda, BorderLayout.CENTER);
		
		panelListaTiendas = new PanelListaTiendas();
		add(panelListaTiendas, BorderLayout.WEST);
		
		panelImagen = new PanelImagen();
		add(panelImagen, BorderLayout.EAST);
		
		panelOpciones = new PanelOpciones();
		add(panelOpciones, BorderLayout.SOUTH);	
	}
	
	/** Método principal para ejecutar la aplicación */
	public static void main(String[] args) {
		PanelPrincipal panelPrincipal = new PanelPrincipal();
		panelPrincipal.setVisible(true);
	}
	
	/** Métodos */
	 private void cargarTiendas() {
	        // Aquí se cargarían las tiendas desde la base de datos
	        // y se actualizaría el panel de lista de tiendas
	    }

	    private void mostrarTiendaSeleccionada() {
	        // Aquí se mostraría la tienda seleccionada en el panel de mostrar tienda
	        // y se actualizarían los detalles en el panel de imagen y opciones
	    }

	    private void actualizarListaTiendas() {
	        // Aquí se actualizaría la lista de tiendas en el panel de lista de tiendas
	        // después de agregar, eliminar o actualizar una tienda
	    }

	    public void agregarTienda() {
	        // Aquí se agregarían nuevas tiendas a la base de datos
	        // y se actualizaría el panel de lista de tiendas
	    }

	    public void actualizarTienda() {
	        // Aquí se actualizarían los detalles de la tienda seleccionada
	        // y se reflejarían en los paneles correspondientes
	    }

	    public void eliminarTienda() {
	        // Aquí se eliminaría la tienda seleccionada de la base de datos
	        // y se actualizaría el panel de lista de tiendas
	    }

	    public void ordenarPorNombre() {
	        // Aquí se ordenarían las tiendas por nombre
	        // y se actualizaría el panel de lista de tiendas
	    }

	    public void agregarCategoria() {
	        // Aquí se agregarían nuevas categorías a la tienda
	        // y se actualizaría el panel de opciones
	    }

	    public void eliminarCategoria() {
	        // Aquí se eliminaría una categoría de la tienda si no tiene tiendas asociadas
	        // y se actualizaría el panel de opciones
	    }

	    public void buscarTiendaPorNombre() {
	        // Aquí se buscaría una tienda por su nombre
	        // y se mostraría en el panel de mostrar tienda
	    }

	    public void buscarTiendaPorCategoria() {
	        // Aquí se buscarían tiendas por su categoría
	        // y se mostrarían en el panel de mostrar tienda
	    }
	}
