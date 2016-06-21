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
    private Image imagen;
    private  Delaunay delaunay;
    private int ancho;
    private int alto;
    
    private int x;
    private int y;
    
    public Panel(){
        //Definimos el tamanio del panel.
        setSize(400, 300);
        vertices=new ArrayList();
        
        //Asociamos un evento de mouse al panel.
        addMouseListener(this);
        
        //Creamos un objeto de tipo de Delaunay donde tenemos la interfaz adecuada para insertar nodos, legalizar 
        //lados y realizar el test del circulo.
        delaunay=new Delaunay();
    }
    
    public void ini (){
        ancho=400;
        alto=300;
        
        imagen=createImage(ancho, alto);
        pantalla=imagen.getGraphics();
    }
    
    /*
      Se ejecuta cada vez que pulsamos el panel. Necesitamos volver a dibujar el panel cada vez que introducimos 
      un nuevo vertice.
      Contiene la logica necesaria para realizar reestructuraciones en la nube de puntos.
    */
    public void paint (Graphics g){
        //Establecemos un color. Todos los objetos se dibujan en el panel teniendo en cuenta el color especificado. 
        pantalla.setColor(Color.red);
        //Dibujamos un ovalo.
        //pantalla.drawOval(x, y, 4, 4);
        //Aplicamos un relleno a cada vertice.
        //pantalla.fillOval(x, y, 4, 4);
        
        pantalla.clearRect(0, 0, ancho, alto);
      
        //Mostramos los triangulos pertenecientes a una  nube de puntos incremental. En cada iteracion se dibujan
        //dos vertices.
        int r = 3;
        for(int i = 6; i < delaunay.aristas.size(); i = i + 2)
        {System.out.println("Dibujamos un ovalo");
         //Dibujamos y rellenamos cada par de puntos representados por ovalos.
         pantalla.drawOval(((Arista)delaunay.aristas.get(i)).getDestino().get_x() - r,-((Arista)delaunay.aristas.get(i)).getDestino().get_y() - r, r*2, r*2);
         pantalla.fillOval(((Arista)delaunay.aristas.get(i)).getDestino().get_x() - r, -((Arista)delaunay.aristas.get(i)).getDestino().get_y() - r, r*2, r*2);
         
         pantalla.drawOval(((Arista)delaunay.aristas.get(i)).getGemelo().getDestino().get_x() - r,-((Arista)delaunay.aristas.get(i)).getGemelo().getDestino().get_y() - r, r*2, r*2);
         pantalla.fillOval(((Arista)delaunay.aristas.get(i)).getGemelo().getDestino().get_x() - r, -((Arista)delaunay.aristas.get(i)).getGemelo().getDestino().get_y() - r, r*2, r*2);
        }
        
        
        pantalla.setColor(Color.black);
	 	 	
	 	 	
        for(int i = 0; i < delaunay.aristas.size(); i++)
        {

                //No mostramos las aristas que triangulan con el triangulo general imaginario 
                if(!(((Arista)delaunay.aristas.get(i)).getDestino().get_x() < 0 ||
                 ((Arista)delaunay.aristas.get(i)).getDestino().get_x() > ancho ||
                 ((Arista)delaunay.aristas.get(i)).getGemelo().getDestino().get_x() < 0 ||
                 ((Arista)delaunay.aristas.get(i)).getGemelo().getDestino().get_x() > ancho ||
                 -((Arista)delaunay.aristas.get(i)).getDestino().get_y() < 0 ||
                 -((Arista)delaunay.aristas.get(i)).getDestino().get_y() > alto ||
                 -((Arista)delaunay.aristas.get(i)).getGemelo().getDestino().get_y() < 0 ||
                 -((Arista)delaunay.aristas.get(i)).getGemelo().getDestino().get_y() > alto))
                {
        System.out.println("Dibujamos una linea");
                pantalla.drawLine(((Arista)delaunay.aristas.get(i)).getDestino().get_x(),
                                         -((Arista)delaunay.aristas.get(i)).getDestino().get_y(),
                                          ((Arista)delaunay.aristas.get(i)).getGemelo().getDestino().get_x(),
                                         -((Arista)delaunay.aristas.get(i)).getGemelo().getDestino().get_y());
                }
                i++; //Nos saltamos las aristas gemelas
        }
        
        //Esta es la sentencia que debemos utilizar para volver a dibujar nuestro panel.
        g.drawImage(imagen, 0, 0, null);
    }
    
    /*
      Obtenemos las coordenadas (x, y) de un vertice. Esta funcion se ejecuta cada vez que hacemos click sobre el
      panel del applet.
    */
    public void mousePressed (MouseEvent e){
        x=e.getX();
        y=e.getY();
        //Creamos un vertice con las coordenadas del punto que introduce el usuario.
        Vertice v=new Vertice(e.getX(), e.getY());
        
        //Agregamos el nuevo vertice a la nube de puntos incremetal.
        delaunay.insertar(v);
        vertices.add(v);
        
        System.out.print(x);
        System.out.print("\t");
        System.out.print(y);
        System.out.println();
        
        //Este metodo perteneciente a la clase Graphics actualiza el contenido del panel invocando implicitamente
        //al metodo redefinido paint(Graphics g) de la clase abstracta Graphics. 
        repaint();
    }
    
    /*
      Estos metodos se deben especificar porque pertenecen a la interface MouseListener. Siempre se deben 
      implementar todos los metodos de una interface, caso contrario Java reporta un error. 
    */
    public void mouseClicked(MouseEvent e){}
    public void mouseEntered(MouseEvent e){}
    public void mouseExited(MouseEvent e){}
    public void mouseReleased(MouseEvent e){}
    
}
