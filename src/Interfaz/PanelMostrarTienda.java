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
    private JTextField txtNombre, txtCategoria, txtTelefono, txtDireccion;
    
    public PanelMostrarTienda() {
        setLayout(new GridBagLayout());
        setBorder(BorderFactory.createTitledBorder("Información de la tienda"));
        
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(8, 8, 8, 8);
        gbc.anchor = GridBagConstraints.WEST;
        
        // Campos para mostrar información de la tienda
        String[] etiquetas = {"Nombre:", "Categoría:", "Teléfono:", "Dirección:"};
        JTextField[] campos = {
            txtNombre = new JTextField(20),
            txtCategoria = new JTextField(20),
            txtTelefono = new JTextField(20),
            txtDireccion = new JTextField(20)
        };
        
        // Hacer los campos no editables (solo para mostrar información)
        for (JTextField campo : campos) {
            campo.setEditable(false);
            campo.setBackground(Color.WHITE);
        }
        
        // Agregar etiquetas y campos
        for (int i = 0; i < etiquetas.length; i++) {
            gbc.gridx = 0;
            gbc.gridy = i;
            gbc.fill = GridBagConstraints.NONE;
            add(new JLabel(etiquetas[i]), gbc);
            
            gbc.gridx = 1;
            gbc.fill = GridBagConstraints.HORIZONTAL;
            add(campos[i], gbc);
        }
        
        // Inicializar campos vacíos
        limpiarInformacion();
    }
    
    public void actualizarInformacion(String nombre, String categoria, String telefono, 
                                    String direccion) {
        txtNombre.setText(nombre);
        txtCategoria.setText(categoria);
        txtTelefono.setText(telefono);
        txtDireccion.setText(direccion);
    }
    
    public void limpiarInformacion() {
        txtNombre.setText("");
        txtCategoria.setText("");
        txtTelefono.setText("");
        txtDireccion.setText("");
    }
}
