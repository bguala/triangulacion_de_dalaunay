/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package triangulacion_de_delaunay;
import javax.swing.*;
/**
 *
 * @author Bruno
 */
public class Applet extends JApplet {
    
    private Panel panel;
    
    public void init (){
        panel=new Panel();
        getContentPane().add(panel);
        this.resize(400, 300);
        panel.ini();
    }
}
