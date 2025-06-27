package Interfaz;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.ImageIcon;
import java.awt.Image;

public class PanelImagen extends JPanel {
    private String rutaImagen;
    private Image imagen;

    public PanelImagen() {
        setBorder(BorderFactory.createTitledBorder("Logo/Imagen"));
        setPreferredSize(new Dimension(180, 300));
        setBackground(Color.WHITE);
        this.rutaImagen = null;
        this.imagen = null;
    }
    
    public void setImagen(String rutaImagen) {
        this.rutaImagen = rutaImagen;
        if (rutaImagen != null && !rutaImagen.isEmpty()) {
            ImageIcon icon = new ImageIcon(rutaImagen);
            this.imagen = icon.getImage();
        } else {
            this.imagen = null;
        }
        repaint();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        
      
        int width = getWidth() - 40;
        int height = getHeight() - 60;
        int x = 20;
        int y = 30;
        
       
        g2d.setColor(new Color(240, 240, 240));
        g2d.fillRoundRect(x, y, width, height, 10, 10);
        
        
        g2d.setColor(Color.GRAY);
        g2d.drawRoundRect(x, y, width, height, 10, 10);
        
        
        g2d.setColor(Color.DARK_GRAY);
        g2d.drawString("Logo de", x + 20, y + height/2 - 10);
        g2d.drawString("la tienda", x + 20, y + height/2 + 5);
        
     
        g2d.setColor(new Color(70, 130, 180));
        g2d.fillRect(x + width/2 - 15, y + 20, 30, 20);
        g2d.fillRect(x + width/2 - 10, y + 15, 20, 5);
        
        if (imagen != null) {
            g.drawImage(imagen, x, y, width, height, this);
        }
    }
}
