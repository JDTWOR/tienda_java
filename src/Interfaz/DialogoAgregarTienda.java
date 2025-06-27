package Interfaz;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;

import Conexion.CategoriaDAO;
import Conexion.TiendaDAO;
import Mundo.Categoria;
import Mundo.Tienda;

public class DialogoAgregarTienda extends JDialog {
    private JTextField txtNombre, txtContacto, txtDireccion, txtRutaImagen;
    private JComboBox<String> cmbCategorias;
    private JButton btnGuardar, btnCancelar, btnSeleccionarImagen;
    private TiendaDAO tiendaDAO;
    private CategoriaDAO categoriaDAO;
    private PanelListaTiendas panelListaTiendas;
    private String rutaImagenSeleccionada;

    public DialogoAgregarTienda(JFrame parent, PanelListaTiendas panelListaTiendas) {
        super(parent, "Agregar Nueva Tienda", true);
        this.panelListaTiendas = panelListaTiendas;
        tiendaDAO = new TiendaDAO();
        categoriaDAO = new CategoriaDAO();
        
        setLayout(new BorderLayout());
        setSize(440, 340);
        setLocationRelativeTo(parent);

        // Panel de campos con mejor estética
        JPanel panelCampos = new JPanel(new GridBagLayout());
        panelCampos.setBorder(javax.swing.BorderFactory.createEmptyBorder(18, 18, 10, 18));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(7, 7, 7, 7);
        gbc.anchor = GridBagConstraints.WEST;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.weightx = 1.0;

        int fila = 0;
        // Nombre
        gbc.gridx = 0; gbc.gridy = fila;
        panelCampos.add(new JLabel("Nombre:"), gbc);
        gbc.gridx = 1;
        txtNombre = new JTextField(22);
        panelCampos.add(txtNombre, gbc);
        fila++;

        // Categoría
        gbc.gridx = 0; gbc.gridy = fila;
        panelCampos.add(new JLabel("Categoría:"), gbc);
        gbc.gridx = 1;
        cmbCategorias = new JComboBox<>();
        cargarCategorias();
        panelCampos.add(cmbCategorias, gbc);
        fila++;

        // Contacto
        gbc.gridx = 0; gbc.gridy = fila;
        panelCampos.add(new JLabel("Contacto:"), gbc);
        gbc.gridx = 1;
        txtContacto = new JTextField(22);
        panelCampos.add(txtContacto, gbc);
        fila++;

        // Dirección
        gbc.gridx = 0; gbc.gridy = fila;
        panelCampos.add(new JLabel("Dirección:"), gbc);
        gbc.gridx = 1;
        txtDireccion = new JTextField(22);
        panelCampos.add(txtDireccion, gbc);
        fila++;

        // Imagen
        gbc.gridx = 0; gbc.gridy = fila;
        panelCampos.add(new JLabel("Imagen:"), gbc);
        gbc.gridx = 1;
        JPanel panelImagen = new JPanel(new BorderLayout(7, 0));
        txtRutaImagen = new JTextField();
        txtRutaImagen.setEditable(false);
        btnSeleccionarImagen = new JButton("Seleccionar Imagen");
        panelImagen.add(txtRutaImagen, BorderLayout.CENTER);
        panelImagen.add(btnSeleccionarImagen, BorderLayout.EAST);
        panelCampos.add(panelImagen, gbc);

        add(panelCampos, BorderLayout.CENTER);

        // Panel de botones con margen superior
        JPanel panelBotones = new JPanel();
        panelBotones.setBorder(javax.swing.BorderFactory.createEmptyBorder(10, 0, 0, 0));
        btnGuardar = new JButton("Guardar");
        btnCancelar = new JButton("Cancelar");
        panelBotones.add(btnGuardar);
        panelBotones.add(btnCancelar);
        add(panelBotones, BorderLayout.SOUTH);

        btnGuardar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                guardarTienda();
            }
        });
        btnCancelar.addActionListener(e -> dispose());
        btnSeleccionarImagen.addActionListener(e -> seleccionarImagen());
    }

    private void cargarCategorias() {
        try {
            List<Categoria> categorias = categoriaDAO.obtenerCategorias();
            cmbCategorias.removeAllItems();
            for (Categoria categoria : categorias) {
                cmbCategorias.addItem(categoria.getNombre() + " (ID: " + categoria.getId() + ")");
            }
        } catch (Exception e) {
            System.err.println("Error al cargar categorías: " + e.getMessage());
        }
    }

    private void guardarTienda() {
        try {
            String categoriaSeleccionada = (String) cmbCategorias.getSelectedItem();
            long idCategoria = Long.parseLong(categoriaSeleccionada.split("\\(ID: ")[1].replace(")", ""));
            
            String nombreCategoria = categoriaSeleccionada.split("\\(")[0].trim();
            Tienda tienda = new Tienda(
                0, 
                txtNombre.getText(),
                nombreCategoria,
                txtContacto.getText(),
                txtDireccion.getText(),
                rutaImagenSeleccionada 
            );
            
            long idTienda = tiendaDAO.insertarTienda(tienda);
            if (idTienda != -1) {
                tiendaDAO.insertarCategoriaTienda(idTienda, idCategoria);
                panelListaTiendas.cargarTiendas();
            }

            dispose();
        } catch (Exception e) {
            System.err.println("Error al guardar tienda: " + e.getMessage());
        }
    }

    private void seleccionarImagen() {
        JFileChooser fileChooser = new JFileChooser("images");
        fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
        fileChooser.setAcceptAllFileFilterUsed(false);
        fileChooser.addChoosableFileFilter(new javax.swing.filechooser.FileNameExtensionFilter("Imágenes", "jpg", "jpeg", "png", "gif"));
        int result = fileChooser.showOpenDialog(this);
        if (result == JFileChooser.APPROVE_OPTION) {
            java.io.File selectedFile = fileChooser.getSelectedFile();
            rutaImagenSeleccionada = selectedFile.getPath();
            txtRutaImagen.setText(rutaImagenSeleccionada);
        }
    }
}
