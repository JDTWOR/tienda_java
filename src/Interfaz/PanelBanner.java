package Interfaz;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class PanelBanner extends JPanel {
    public PanelBanner() {
        setLayout(new FlowLayout(FlowLayout.CENTER));
        setBackground(new Color(70, 130, 180)); 
        
        JLabel titulo = new JLabel("DIRECTORIO DE TIENDAS");
        titulo.setFont(new Font("Arial", Font.BOLD, 20));
        titulo.setForeground(Color.WHITE);
        add(titulo);
        
        setPreferredSize(new java.awt.Dimension(700, 60));
    }
}