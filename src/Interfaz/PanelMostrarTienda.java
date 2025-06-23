package Interfaz;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class PanelMostrarTienda extends JPanel {
    private JTextField txtNombre, txtCategoria, txtTelefono, txtDireccion, txtHorario;
    private JTextArea txtDescripcion;
    
    public PanelMostrarTienda() {
        setLayout(new GridBagLayout());
        setBorder(BorderFactory.createTitledBorder("Información de la tienda"));
        
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(8, 8, 8, 8);
        gbc.anchor = GridBagConstraints.WEST;
        
        // Campos apropiados para un directorio de tiendas
        String[] etiquetas = {"Nombre:", "Categoría:", "Teléfono:", "Dirección:", "Horario:"};
        JTextField[] campos = {
            txtNombre = new JTextField(20),
            txtCategoria = new JTextField(20),
            txtTelefono = new JTextField(20),
            txtDireccion = new JTextField(20),
            txtHorario = new JTextField(20)
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
        
        // Campo de descripción
        gbc.gridx = 0;
        gbc.gridy = etiquetas.length;
        gbc.fill = GridBagConstraints.NONE;
        add(new JLabel("Descripción:"), gbc);
        
        gbc.gridx = 1;
        gbc.fill = GridBagConstraints.BOTH;
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;
        txtDescripcion = new JTextArea(2, 15);
        txtDescripcion.setEditable(false);
        txtDescripcion.setBackground(Color.WHITE);
        txtDescripcion.setLineWrap(true);
        txtDescripcion.setWrapStyleWord(true);
        JScrollPane scrollDesc = new JScrollPane(txtDescripcion);
        add(scrollDesc, gbc);
        
        // Valores de ejemplo
        txtNombre.setText("Tienda El Éxito");
        txtCategoria.setText("Supermercado");
        txtTelefono.setText("(601) 123-4567");
        txtDireccion.setText("Calle 123 #45-67, Bogotá");
        txtHorario.setText("Lun-Dom: 7:00 AM - 10:00 PM");
        txtDescripcion.setText("Supermercado de cadena con amplia variedad de productos para el hogar.");
    }
    
    public void actualizarInformacion(String nombre, String categoria, String telefono, 
                                    String direccion, String horario, String descripcion) {
        txtNombre.setText(nombre);
        txtCategoria.setText(categoria);
        txtTelefono.setText(telefono);
        txtDireccion.setText(direccion);
        txtHorario.setText(horario);
        txtDescripcion.setText(descripcion);
    }
    
    public void limpiarInformacion() {
        txtNombre.setText("");
        txtCategoria.setText("");
        txtTelefono.setText("");
        txtDireccion.setText("");
        txtHorario.setText("");
        txtDescripcion.setText("");
    }
}