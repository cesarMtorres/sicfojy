
/*
 * PanelFondo.java
 *
 * Created on 28/06/2013, 02:29:11 PM
 */

package vista;
import java.awt.*;
import javax.swing.*;
/**
 *
 * @author Rover Monserrat :: 30384
 */
public class PanelFondo extends javax.swing.JPanel {
    private ImageIcon imgfondo = null;
    /** Creates new form PanelFondo */
    public PanelFondo(int x, int y, String laimg) {
        initComponents();
        this.setSize(x,y);
       // this.setImgfondo(new ImageIcon(new ImageIcon(getClass().getResource(laimg)).getImage()));
    }


    @Override
    public void paintComponent(Graphics g){
        Dimension tam = getSize();
     //   ImageIcon fondo = getImgfondo();
        //ImageIcon fondo = new ImageIcon(new ImageIcon(getClass().getResource("/images/principalnew.jpg")).getImage());
//        g.drawImage(fondo.getImage(), 0, 0, tam.width, tam.height,null);
        setOpaque(false);
        super.paintComponent(g);
        
    }

    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 300, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables

}
