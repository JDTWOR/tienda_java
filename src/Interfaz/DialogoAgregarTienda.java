package Interfaz;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import Conexion.TiendaDAO;
import Mundo.Tienda;

public class DialogoAgregarTienda extends JDialog {
    private JTextField txtNombre, txtCategoria, txtContacto, txtDireccion;
    private JButton btnGuardar, btnCancelar;
    private TiendaDAO tiendaDAO;

    public DialogoAgregarTienda(JFrame parent) {
        super(parent, "Agregar Nueva Tienda", true);
        tiendaDAO = new TiendaDAO();
        
        setLayout(new BorderLayout());
        setSize(400, 300);
        setLocationRelativeTo(parent);

        // Panel de campos
        JPanel panelCampos = new JPanel(new GridLayout(4, 2, 10, 10));
        panelCampos.add(new JLabel("Nombre:"));
        txtNombre = new JTextField();
        panelCampos.add(txtNombre);
        
        panelCampos.add(new JLabel("Categoría ID:"));
        txtCategoria = new JTextField();
        panelCampos.add(txtCategoria);
        
        panelCampos.add(new JLabel("Contacto:"));
        txtContacto = new JTextField();
        panelCampos.add(txtContacto);
        
        panelCampos.add(new JLabel("Dirección:"));
        txtDireccion = new JTextField();
        panelCampos.add(txtDireccion);
        
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
    }

    private void guardarTienda() {
        try {
            Tienda tienda = new Tienda(
                0, // ID se generará automáticamente
                txtNombre.getText(),
                Long.parseLong(txtCategoria.getText()),
                txtContacto.getText(),
                txtDireccion.getText(),
                "" // Ruta imagen (opcional)
            );
            
            tiendaDAO.insertarTienda(tienda);
            dispose();
        } catch (Exception e) {
            System.err.println("Error al guardar tienda: " + e.getMessage());
        }
    }
}
