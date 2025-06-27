package Interfaz;

import javax.swing.*;
import java.awt.*;
import Conexion.TiendaDAO;
import Conexion.CategoriaDAO;
import Mundo.Categoria;
import java.util.List;

public class DialogoEditarTienda extends JDialog {
    private JTextField txtNombre, txtTelefono, txtDireccion;
    private JComboBox<String> cmbCategorias;
    private String nombreOriginal;
    private PanelListaTiendas panelListaTiendas;
    private List<Categoria> listaCategorias;

    public DialogoEditarTienda(String nombre, String categoria, String telefono, String direccion, PanelListaTiendas panelListaTiendas) {
        setTitle("Editar Tienda");
        setModal(true);
        setSize(350, 300);
        setLocationRelativeTo(null);
        this.nombreOriginal = nombre;
        this.panelListaTiendas = panelListaTiendas;

        JPanel panel = new JPanel(new GridLayout(5, 2, 10, 10));
        panel.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));

        panel.add(new JLabel("Nombre:"));
        txtNombre = new JTextField(nombre);
        panel.add(txtNombre);

        panel.add(new JLabel("Categoría:"));
        cmbCategorias = new JComboBox<>();
        cargarCategorias(categoria);
        panel.add(cmbCategorias);

        panel.add(new JLabel("Teléfono:"));
        txtTelefono = new JTextField(telefono);
        panel.add(txtTelefono);

        panel.add(new JLabel("Dirección:"));
        txtDireccion = new JTextField(direccion);
        panel.add(txtDireccion);

        JButton btnActualizar = new JButton("Actualizar");
        btnActualizar.addActionListener(e -> actualizarTienda());
        panel.add(new JLabel()); // Espacio
        panel.add(btnActualizar);

        add(panel);
    }

    private void cargarCategorias(String categoriaActual) {
        try {
            CategoriaDAO categoriaDAO = new CategoriaDAO();
            listaCategorias = categoriaDAO.obtenerCategorias();
            cmbCategorias.removeAllItems();
            int indexSeleccionado = 0;
            for (int i = 0; i < listaCategorias.size(); i++) {
                Categoria cat = listaCategorias.get(i);
                String nombreCat = cat.getNombre() + " (ID: " + cat.getId() + ")";
                cmbCategorias.addItem(nombreCat);
                if (cat.getNombre().equalsIgnoreCase(categoriaActual)) {
                    indexSeleccionado = i;
                }
            }
            cmbCategorias.setSelectedIndex(indexSeleccionado);
        } catch (Exception e) {
            System.err.println("Error al cargar categorías: " + e.getMessage());
        }
    }

    private void actualizarTienda() {
        String nuevoNombre = txtNombre.getText().trim();
        String nuevoTelefono = txtTelefono.getText().trim();
        String nuevaDireccion = txtDireccion.getText().trim();
        String categoriaSeleccionada = (String) cmbCategorias.getSelectedItem();
        if (nuevoNombre.isEmpty() || categoriaSeleccionada == null) {
            JOptionPane.showMessageDialog(this, "Nombre y categoría no pueden estar vacíos.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        // Extraer nombre e id de la categoría
        int idx = cmbCategorias.getSelectedIndex();
        Categoria cat = listaCategorias.get(idx);
        String nombreCategoria = cat.getNombre();
        int idCategoria = cat.getId();
        TiendaDAO dao = new TiendaDAO();
        boolean exito = dao.actualizarTiendaPorNombre(nombreOriginal, nuevoNombre, nombreCategoria, nuevoTelefono, nuevaDireccion, idCategoria);
        if (exito) {
            JOptionPane.showMessageDialog(this, "Tienda actualizada correctamente.", "Éxito", JOptionPane.INFORMATION_MESSAGE);
            if (panelListaTiendas != null) {
                panelListaTiendas.cargarTiendas();
            }
            dispose();
        } else {
            JOptionPane.showMessageDialog(this, "No se pudo actualizar la tienda.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
} 