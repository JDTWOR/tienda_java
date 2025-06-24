package Interfaz;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JFileChooser;
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
        setSize(400, 300);
        setLocationRelativeTo(parent);

        // Panel de campos
        JPanel panelCampos = new JPanel(new GridLayout(4, 2, 10, 10));
        panelCampos.add(new JLabel("Nombre:"));
        txtNombre = new JTextField();
        panelCampos.add(txtNombre);
        
        panelCampos.add(new JLabel("Categoría:"));
        cmbCategorias = new JComboBox<>();
        cargarCategorias();
        panelCampos.add(cmbCategorias);
        
        panelCampos.add(new JLabel("Contacto:"));
        txtContacto = new JTextField();
        panelCampos.add(txtContacto);
        
        panelCampos.add(new JLabel("Dirección:"));
        txtDireccion = new JTextField();
        panelCampos.add(txtDireccion);
        
        panelCampos.add(new JLabel("Imagen:"));
        JPanel panelImagen = new JPanel(new BorderLayout());
        txtRutaImagen = new JTextField();
        txtRutaImagen.setEditable(false);
        btnSeleccionarImagen = new JButton("Seleccionar Imagen");
        panelImagen.add(txtRutaImagen, BorderLayout.CENTER);
        panelImagen.add(btnSeleccionarImagen, BorderLayout.EAST);
        panelCampos.add(panelImagen);

        add(panelCampos, BorderLayout.CENTER);

        // Panel de botones
        JPanel panelBotones = new JPanel();
        btnGuardar = new JButton("Guardar");
        btnCancelar = new JButton("Cancelar");
        
        btnGuardar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                guardarTienda();
            }
        });
        
        btnCancelar.addActionListener(e -> dispose());
        
        panelBotones.add(btnGuardar);
        panelBotones.add(btnCancelar);
        add(panelBotones, BorderLayout.SOUTH);

        // Acción del botón
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
                0, // ID se generará automáticamente
                txtNombre.getText(),
                nombreCategoria,
                txtContacto.getText(),
                txtDireccion.getText(),
                rutaImagenSeleccionada // Ruta imagen (opcional)
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
