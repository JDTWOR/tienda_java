package Interfaz;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.util.List;
import javax.swing.BorderFactory;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import Conexion.TiendaDAO;
import Mundo.Tienda;

public class PanelListaTiendas extends JPanel {
    private JList<String> listaTiendas;
    private DefaultListModel<String> modeloLista;
    private JButton btnAgregar;
    private PanelPrincipal panelPrincipal;
    
    public PanelListaTiendas() {
        this(null);
    }
    
    public PanelListaTiendas(PanelPrincipal panelPrincipal) {
        this.panelPrincipal = panelPrincipal;
        setLayout(new BorderLayout());
        setBorder(BorderFactory.createTitledBorder("Lista de tiendas"));
        setPreferredSize(new Dimension(220, 400));
        
        // Crear lista vacía que se llenará desde la base de datos
        modeloLista = new DefaultListModel<>();
        listaTiendas = new JList<>(modeloLista);
        
        // Cargar tiendas desde la base de datos
        cargarTiendas();
        JScrollPane scrollPane = new JScrollPane(listaTiendas);
        add(scrollPane, BorderLayout.CENTER);
        
        btnAgregar = new JButton("Agregar Tienda");
        if (panelPrincipal != null) {
            btnAgregar.addActionListener(e -> {
                new DialogoAgregarTienda(panelPrincipal).setVisible(true);
            });
        } else {
            btnAgregar.setEnabled(false);
        }
        add(btnAgregar, BorderLayout.SOUTH);
    }
    
    public JList<String> getListaTiendas() {
        return listaTiendas;
    }
    
    public DefaultListModel<String> getModeloLista() {
        return modeloLista;
    }
    
    public void cargarTiendas() {
        modeloLista.clear();
        try {
            TiendaDAO tiendaDAO = new TiendaDAO();
            List<Tienda> tiendas = tiendaDAO.obtenerTiendas();
            
            for (Tienda tienda : tiendas) {
                modeloLista.addElement(tienda.getNombre());
            }
        } catch (Exception e) {
            System.err.println("Error al cargar tiendas: " + e.getMessage());
        }
    }
    
    public void agregarTienda(Tienda tienda) {
        modeloLista.addElement(tienda.getNombre());
    }
}
