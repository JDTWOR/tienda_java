package Interfaz;

import java.awt.FlowLayout;
import java.awt.GridLayout;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class PanelOpciones extends JPanel {
    private JComboBox<String> cmbOrdenar, cmbBuscar;
    private JTextField txtBuscar;
    private JButton btnOrdenar, btnBuscar;
    private PanelPrincipal panelPrincipal;
    
    public PanelOpciones() {
        this(null);
    }
    
    public PanelOpciones(PanelPrincipal panelPrincipal) {
        this.panelPrincipal = panelPrincipal;
        setLayout(new GridLayout(1, 3, 15, 5));
        setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        
        // Panel de ordenar
        JPanel panelOrdenar = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 15));
        panelOrdenar.setBorder(BorderFactory.createTitledBorder("Ordenar"));
        
        panelOrdenar.add(new JLabel("Por:"));
        cmbOrdenar = new JComboBox<>(new String[]{"Nombre", "Categoría"});
        btnOrdenar = new JButton("Ordenar");
        
        panelOrdenar.add(cmbOrdenar);
        panelOrdenar.add(btnOrdenar);
        
        // Panel de buscar
        JPanel panelBuscar = new JPanel(new FlowLayout(FlowLayout.LEFT));
        panelBuscar.setBorder(BorderFactory.createTitledBorder("Buscar"));
        
        panelBuscar.add(new JLabel("Por:"));
        cmbBuscar = new JComboBox<>(new String[]{"Nombre", "Categoría"});
        txtBuscar = new JTextField(12);
        txtBuscar.setToolTipText("Ingrese término de búsqueda");
        btnBuscar = new JButton("Buscar");
        
        panelBuscar.add(cmbBuscar);
        panelBuscar.add(txtBuscar);
        panelBuscar.add(btnBuscar);

        // Panel de categorías
        JPanel panelCategorias = new JPanel(new FlowLayout(FlowLayout.LEFT));
        panelCategorias.setBorder(BorderFactory.createTitledBorder("Categorías"));
        
        JButton btnAgregarCategoria = new JButton("Agregar Categoría");
        if (panelPrincipal != null) {
            btnAgregarCategoria.addActionListener(e -> {
                new DialogoAgregarCategoria(panelPrincipal).setVisible(true);
            });
        } else {
            btnAgregarCategoria.setEnabled(false);
        }
        
        panelCategorias.add(btnAgregarCategoria);
        
        add(panelOrdenar);
        add(panelBuscar);
        add(panelCategorias);

        btnOrdenar.addActionListener(e -> {
            if (panelPrincipal != null) {
                String criterio = (String) cmbOrdenar.getSelectedItem();
                if (criterio.equals("Nombre")) {
                    panelPrincipal.ordenarPorNombre();
                } else if (criterio.equals("Categoría")) {
                    panelPrincipal.ordenarPorCategoria();
                }
            }
        });

        btnBuscar.addActionListener(e -> {
            if (panelPrincipal != null) {
                String criterio = (String) cmbBuscar.getSelectedItem();
                String texto = txtBuscar.getText();
                if (criterio.equals("Nombre")) {
                    panelPrincipal.buscarTiendaPorNombre(texto);
                } else if (criterio.equals("Categoría")) {
                    panelPrincipal.buscarTiendaPorCategoria(texto);
                }
            }
        });
    }
    
    public JComboBox<String> getCmbOrdenar() {
        return cmbOrdenar;
    }
    
    public JComboBox<String> getCmbBuscar() {
        return cmbBuscar;
    }
    
    public JTextField getTxtBuscar() {
        return txtBuscar;
    }
    
    public JButton getBtnOrdenar() {
        return btnOrdenar;
    }
    
    public JButton getBtnBuscar() {
        return btnBuscar;
    }
}
