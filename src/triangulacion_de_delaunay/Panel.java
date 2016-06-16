/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package triangulacion_de_delaunay;
import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.util.*;
/**
 *
 * @author Bruno
 */
public class Panel extends JPanel implements MouseListener {
    
    private Graphics pantalla;
    private ArrayList vertices;
    Image imagen;
    private int x;
    private int y;
    
    public Panel(){
        setSize(400, 300);
        vertices=new ArrayList();
        addMouseListener(this);
    }
    
    public void ini (){
        imagen=createImage(400, 300);
        pantalla=imagen.getGraphics();
    }
    
    /*
      Se ejecuta cada vez que pulsamos el panel.
    */
    public void paint (Graphics g){
        pantalla.setColor(Color.red);
        pantalla.drawOval(x, y, 4, 4);
        pantalla.fillOval(x, y, 4, 4);
        g.drawImage(imagen, 0, 0, null);
    }
    
    /*
      Obtenemos las coordenadas (x, y) de un vertice.
    */
    public void mousePressed (MouseEvent e){
        x=e.getX();
        y=e.getY();
        Vertice v=new Vertice(x, y);
        vertices.add(v);
        
        System.out.print(x);
        System.out.print("\t");
        System.out.print(y);
        System.out.println();
        
        repaint();
    }
    
    
    public void mouseClicked(MouseEvent e){}
    public void mouseEntered(MouseEvent e){}
    public void mouseExited(MouseEvent e){}
    public void mouseReleased(MouseEvent e){}
}
