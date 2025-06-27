package Interfaz;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import Conexion.CategoriaDAO;
import Mundo.Categoria;

public class DialogoAgregarCategoria extends JDialog {
    private JTextField txtNombre;
    private JButton btnGuardar, btnCancelar;
    private CategoriaDAO categoriaDAO;

    public DialogoAgregarCategoria(JFrame parent) {
        super(parent, "Agregar Nueva Categoría", true);
        categoriaDAO = new CategoriaDAO();
        
        setLayout(new BorderLayout());
        setSize(300, 150);
        setLocationRelativeTo(parent);

        // Panel de campos
        JPanel panelCampos = new JPanel();
        panelCampos.add(new JLabel("Nombre:"));
        txtNombre = new JTextField(20);
        panelCampos.add(txtNombre);
        add(panelCampos, BorderLayout.CENTER);

        // Panel de botones
        JPanel panelBotones = new JPanel();
        btnGuardar = new JButton("Guardar");
        btnCancelar = new JButton("Cancelar");
        
        btnGuardar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                guardarCategoria();
            }
        });
        
        btnCancelar.addActionListener(e -> dispose());
        
        panelBotones.add(btnGuardar);
        panelBotones.add(btnCancelar);
        add(panelBotones, BorderLayout.SOUTH);
    }

    private void guardarCategoria() {
        try {
            String nombre = txtNombre.getText().trim();
            if (!nombre.isEmpty()) {
                // Generar ID único basado en timestamp
                int id = (int) System.currentTimeMillis();
                Categoria categoria = new Categoria(id, nombre);
                categoriaDAO.insertarCategoria(categoria);
                dispose();
            }
        } catch (Exception e) {
            System.err.println("Error al guardar categoría: " + e.getMessage());
        }
    }
}
