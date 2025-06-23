package Interfaz;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import javax.swing.BorderFactory;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

public class PanelListaTiendas extends JPanel {
    private JList<String> listaTiendas;
    private DefaultListModel<String> modeloLista;
    private JButton btnAgregar;
    
    public PanelListaTiendas() {
        setLayout(new BorderLayout());
        setBorder(BorderFactory.createTitledBorder("Lista de tiendas"));
        setPreferredSize(new Dimension(220, 400));
        
        // Crear lista con tiendas de ejemplo
        modeloLista = new DefaultListModel<>();
        modeloLista.addElement("Tienda El Éxito");
        modeloLista.addElement("Carrefour Centro");
        modeloLista.addElement("Jumbo Plaza");
        modeloLista.addElement("D1 Barrio Norte");
        modeloLista.addElement("Ara Local 45");
        
        listaTiendas = new JList<>(modeloLista);
        JScrollPane scrollPane = new JScrollPane(listaTiendas);
        add(scrollPane, BorderLayout.CENTER);
        
        btnAgregar = new JButton("Agregar Tienda");
        add(btnAgregar, BorderLayout.SOUTH);
    }
    
    public JList<String> getListaTiendas() {
        return listaTiendas;
    }
    
    public DefaultListModel<String> getModeloLista() {
        return modeloLista;
    }
}