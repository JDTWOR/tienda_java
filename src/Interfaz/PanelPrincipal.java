package Interfaz;

import java.awt.BorderLayout;
import java.util.List;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import Conexion.TiendaDAO;
import Mundo.Tienda;

public class PanelPrincipal extends JFrame {
	public static final String DB_URL = "jdbc:sqlite:tiendas.db";
	
	public PanelImagen panelImagen;
	public PanelBanner panelBanner;
	public PanelMostrarTienda mostrarTienda;
	public PanelOpciones panelOpciones;
	public PanelListaTiendas panelListaTiendas;
	private JButton btnAgregarCategoria;
	
    /* constructor */
    public PanelPrincipal() {
        setLayout(new BorderLayout());
        
        // Inicializar paneles
        panelBanner = new PanelBanner();
        mostrarTienda = new PanelMostrarTienda();
        panelListaTiendas = new PanelListaTiendas(this);
        panelImagen = new PanelImagen();
        panelOpciones = new PanelOpciones(this);
        
        // Configurar listener para selección de tiendas
        panelListaTiendas.getListaTiendas().addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting() && !panelListaTiendas.getListaTiendas().isSelectionEmpty()) {
                mostrarTiendaSeleccionada();
            }
        });
        
        // Cargar tiendas iniciales
        panelListaTiendas.cargarTiendas();
        
        // Agregar paneles en las posiciones correctas
        add(panelBanner, BorderLayout.NORTH);
        add(mostrarTienda, BorderLayout.CENTER);
        add(panelListaTiendas, BorderLayout.WEST);
        add(panelImagen, BorderLayout.EAST);
        add(panelOpciones, BorderLayout.SOUTH);

        // Configuración de la ventana
        setSize(1150, 550);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("Directorio de tiendas");
        setLocationRelativeTo(null);
        setResizable(false);
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
        System.out.println("Evento de selección detectado"); // Debug 1
        
        String nombreTienda = panelListaTiendas.getListaTiendas().getSelectedValue();
        System.out.println("Tienda seleccionada: " + nombreTienda); // Debug 2
        
        if (nombreTienda != null) {
            try {
                System.out.println("Intentando obtener tienda de la base de datos..."); // Debug 3
                TiendaDAO tiendaDAO = new TiendaDAO();
                Tienda tienda = tiendaDAO.obtenerTiendaPorNombre(nombreTienda);
                
                if (tienda != null) {
                    System.out.println("Datos de la tienda obtenidos:"); // Debug 4
                    System.out.println("Nombre: " + tienda.getNombre());
                    System.out.println("Categoría: " + tienda.getCategoria());
                    System.out.println("Contacto: " + tienda.getContacto());
                    System.out.println("Dirección: " + tienda.getDireccion());
                    
                    mostrarTienda.actualizarInformacion(
                        tienda.getNombre(),
                        tienda.getCategoria(),
                        tienda.getContacto(),
                        tienda.getDireccion()
                    );
                    System.out.println("Información mostrada en el panel"); // Debug 5
                    panelImagen.setImagen(tienda.getRutaImagen());
                } else {
                    System.out.println("No se encontró la tienda en la base de datos"); // Debug 6
                }
            } catch (Exception e) {
                System.err.println("Error al cargar tienda: " + e.getMessage());
                e.printStackTrace();
            }
        } else {
            System.out.println("Nombre de tienda es null"); // Debug 7
        }
    }

	public void ordenarPorNombre() {
	        TiendaDAO tiendaDAO = new TiendaDAO();
	        List<Tienda> tiendas = tiendaDAO.obtenerTiendas();
	        // Ordenar con burbuja tradicional por nombre
	        for (int i = 0; i < tiendas.size() - 1; i++) {
	            for (int j = 0; j < tiendas.size() - i - 1; j++) {
	                if (tiendas.get(j).getNombre().compareToIgnoreCase(tiendas.get(j + 1).getNombre()) > 0) {
	                    Tienda temp = tiendas.get(j);
	                    tiendas.set(j, tiendas.get(j + 1));
	                    tiendas.set(j + 1, temp);
	                }
	            }
	        }
	        panelListaTiendas.actualizarListaTiendas(tiendas);
	}

	public void ordenarPorCategoria() {
	        TiendaDAO tiendaDAO = new TiendaDAO();
	        List<Tienda> tiendas = tiendaDAO.obtenerTiendas();
	        // Ordenar con burbuja tradicional por categoría
	        for (int i = 0; i < tiendas.size() - 1; i++) {
	            for (int j = 0; j < tiendas.size() - i - 1; j++) {
	                String cat1 = tiendas.get(j).getCategoria() != null ? tiendas.get(j).getCategoria() : "";
	                String cat2 = tiendas.get(j + 1).getCategoria() != null ? tiendas.get(j + 1).getCategoria() : "";
	                if (cat1.compareToIgnoreCase(cat2) > 0) {
	                    Tienda temp = tiendas.get(j);
	                    tiendas.set(j, tiendas.get(j + 1));
	                    tiendas.set(j + 1, temp);
	                }
	            }
	        }
	        panelListaTiendas.actualizarListaTiendas(tiendas);
	}

	public void buscarTiendaPorNombre(String nombre) {
	    TiendaDAO tiendaDAO = new TiendaDAO();
	    List<Tienda> tiendas = tiendaDAO.obtenerTiendas();
        List<Tienda> resultado = new java.util.ArrayList<>();
	    for (Tienda t : tiendas) {
	        if (t.getNombre().toLowerCase().contains(nombre.toLowerCase())) {
	            resultado.add(t);
	            }
	        }
	        panelListaTiendas.actualizarListaTiendas(resultado);
	    }

	public void buscarTiendaPorCategoria(String categoria) {
	    TiendaDAO tiendaDAO = new TiendaDAO();
	    List<Tienda> tiendas = tiendaDAO.obtenerTiendas();
        List<Tienda> resultado = new java.util.ArrayList<>();
	    for (Tienda t : tiendas) {
	        if (t.getCategoria() != null && t.getCategoria().toLowerCase().contains(categoria.toLowerCase())) {
	            resultado.add(t);
	        }
        }
        panelListaTiendas.actualizarListaTiendas(resultado);
	    }
}
