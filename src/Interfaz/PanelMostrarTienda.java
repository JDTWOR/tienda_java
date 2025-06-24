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
import javax.swing.JButton;

public class PanelMostrarTienda extends JPanel {
    private JTextField txtNombre, txtCategoria, txtTelefono, txtDireccion;
    private JButton btnEliminar;
    private PanelListaTiendas panelListaTiendas;
    
    public PanelMostrarTienda(PanelListaTiendas panelListaTiendas) {
        this.panelListaTiendas = panelListaTiendas;
        setLayout(new BorderLayout());
        setBorder(BorderFactory.createTitledBorder("Información de la tienda"));
        
        JPanel panelCampos = new JPanel(new GridBagLayout());
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
            panelCampos.add(new JLabel(etiquetas[i]), gbc);
            
            gbc.gridx = 1;
            gbc.fill = GridBagConstraints.HORIZONTAL;
            panelCampos.add(campos[i], gbc);
        }
        
        add(panelCampos, BorderLayout.CENTER);
        
        // Botón Eliminar Tienda
        btnEliminar = new JButton("Eliminar tienda");
        add(btnEliminar, BorderLayout.SOUTH);
        
        btnEliminar.addActionListener(e -> eliminarTiendaActual());
        
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
    
    private void eliminarTiendaActual() {
        String nombre = txtNombre.getText();
        if (nombre == null || nombre.trim().isEmpty()) {
            javax.swing.JOptionPane.showMessageDialog(this, "No hay tienda seleccionada para eliminar.", "Aviso", javax.swing.JOptionPane.WARNING_MESSAGE);
            return;
        }
        int confirm = javax.swing.JOptionPane.showConfirmDialog(this, "¿Está seguro de que desea eliminar la tienda '" + nombre + "'?", "Confirmar eliminación", javax.swing.JOptionPane.YES_NO_OPTION);
        if (confirm == javax.swing.JOptionPane.YES_OPTION) {
            Conexion.TiendaDAO tiendaDAO = new Conexion.TiendaDAO();
            boolean exito = tiendaDAO.eliminarTiendaPorNombre(nombre);
            if (exito) {
                javax.swing.JOptionPane.showMessageDialog(this, "Tienda eliminada correctamente.", "Éxito", javax.swing.JOptionPane.INFORMATION_MESSAGE);
                limpiarInformacion();
                if (panelListaTiendas != null) {
                    panelListaTiendas.cargarTiendas();
                }
            } else {
                javax.swing.JOptionPane.showMessageDialog(this, "No se pudo eliminar la tienda.", "Error", javax.swing.JOptionPane.ERROR_MESSAGE);
            }
        }
    }
}
