/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package triangulacion_de_delaunay;

/**
 *
 * @author Bruno
 */
public class Vertice {
    private int x;
    private int y;
    
    public Vertice (int x, int y){
        this.x=x;
        this.y=y;
    }
    
    public int get_x (){
        return x;
    }
    
    public int get_y (){
        return y;
    }
}
