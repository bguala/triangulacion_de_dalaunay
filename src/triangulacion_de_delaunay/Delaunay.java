
package triangulacion_de_delaunay;
import java.util.*;

class Delaunay
{
	//Mantenemos la lista DCEL general
	public ArrayList aristas;

	//Inicializamos Delaunay creando un Triangulo que abarque todo el canvas
	public Delaunay()
   	{
		aristas = new ArrayList();
	
	  	//Creamos el triangulo inicial que debe abarcar todo el canvas
	  
	  	//Vertices
      	Vertice norte = new Vertice(0, -3000);
      	Vertice seste = new Vertice(3000, 3000);
      	Vertice soeste = new Vertice(-3000, 3000);
	
		//Aristas
      	Arista arista1 = new Arista(soeste, null, null);
      	Arista arista2 = new Arista(norte, null, null);
      	Arista arista3 = new Arista(seste, null, null);
      	Arista arista4 = new Arista(soeste, null, null);
      	Arista arista5 = new Arista(norte, null, null);
      	Arista arista6 = new Arista(seste, null, null);

		//Siguiente arista de cada una para construir la lista DCEL
      	arista1.setNext(arista3);
      	arista3.setNext(arista5);
      	arista5.setNext(arista1);
	  	arista2.setNext(arista4);
	  	arista4.setNext(arista6);
	  	arista6.setNext(arista2);
				
		//Arista gemela de cada arista
      	arista1.setGemelo(arista2);
      	arista3.setGemelo(arista4);
      	arista5.setGemelo(arista6);
  		
  	  	//A�adimos las aristas generadas al arraylist general
	 	aristas.add(arista1);
	 	aristas.add(arista2);
	 	aristas.add(arista3);
	 	aristas.add(arista4);
	 	aristas.add(arista5);
	 	aristas.add(arista6);
   }
   
   //Insertar un nuevo vertice 

   public void insertar(Vertice v)
   {  
   	 //Evita que se fastidie al hacer doble-click
      
	for(int i = 0; i < aristas.size(); i++)
		 if(((Arista)aristas.get(i)).getDestino().get_x() == v.get_x() && ((Arista)aristas.get(i)).getDestino().get_y() == v.get_y())
				return;
	      
     //Arista e = aristaTriangulo(v);

	 //Obtenemos una arista del Triangulo que contiene a v
	 Vertice s = new Vertice(0, 0);
	 Arista e = (Arista)aristas.get(0);

	 while(Izquierda(v, e.getGemelo().getDestino(), e.getDestino()))
	 {System.out.println(e.getGemelo().getDestino().get_x());System.out.println(e.getGemelo().getDestino().get_y());
	    if(Izquierda(e.getNext().getDestino(), s, v))
		{
		  e = e.getNext().getGemelo();
		}
		else
		{
		 e = e.getNext().getNext().getGemelo();
		}
	 }
	 
	 e=e.getGemelo();
	
	 //En e tenemos una arista del triangulo que contiene a v

	 //Nuevas aristas
     Arista e1 = new Arista(v, null, null);
     Arista e2 = new Arista(e.getDestino(), null, null);
     Arista e3 = new Arista(v, null, null);
     Arista e4 = new Arista(e.getNext().getDestino(), null, null);
     Arista e5 = new Arista(v, null, null);
     Arista e6 = new Arista(e.getGemelo().getDestino(), null, null);

	 //Construimos lista DCEL a partir de las nuevas aristas
     e1.setNext(e6);
     e2.setNext(e.getNext());
     e3.setNext(e2);
     e4.setNext(e.getNext().getNext());
     e5.setNext(e4);
     e6.setNext(e);
     e.getNext().getNext().setNext(e5);
     e.getNext().setNext(e3);
     e.setNext(e1);

     e1.setGemelo(e2);
     e3.setGemelo(e4);
     e5.setGemelo(e6);

	 //A�adimos las Aristas al ArrayList general
	 aristas.add(e1);
	 aristas.add(e2);
	 aristas.add(e3);
	 aristas.add(e4);
	 aristas.add(e5);
	 aristas.add(e6);
		
	 //Legalizamos las aristas
     legalizar(e2.getNext());
     legalizar(e4.getNext());
	 legalizar(e6.getNext());
   }

   private void legalizar(Arista e)
   {
      if(test_circulo(e.getDestino(), e.getNext().getDestino(),e.getGemelo().getDestino(), e.getGemelo().getNext().getDestino()))
      {
      	//Intercambiar arista, eliminamos la ilegal
		e.setDestino(e.getNext().getDestino());
	    e.getGemelo().setDestino(e.getGemelo().getNext().getDestino());
	    e.getNext().getNext().setNext(e.getGemelo().getNext());
	    e.getGemelo().getNext().getNext().setNext(e.getNext());
	    e.setNext(e.getNext().getNext());
	    e.getGemelo().setNext(e.getGemelo().getNext().getNext());
	    e.getNext().getNext().setNext(e);
	    e.getGemelo().getNext().getNext().setNext(e.getGemelo());
	 	
	 	//Legalizar las del tri�ngulo adyacente
	 	legalizar(e.getNext().getNext());
	 	legalizar(e.getGemelo().getNext());
      }
   }
   
   /*
     Verificamos si el vertice s esta dentro del triangulo a,b,c. Para ello se emplea una formula matematica.
   */
   private boolean test_circulo(Vertice a, Vertice b, Vertice c, Vertice d)
   {
	  double cx = d.get_x() - a.get_x();
	  double cy = d.get_y() - a.get_y();
	  double ax = b.get_x() - a.get_x();
	  double ay = b.get_y() - a.get_y();
	  double bx = c.get_x() - a.get_x();
	  double by = c.get_y() - a.get_y();
	  

      double aa = (ax * ax) + (ay * ay);
      double bb = (bx * bx) + (by * by);
	  double cc = (cx * cx) + (cy * cy);
      
      return ((  aa * (bx * cy - by * cx)- bb * (ax * cy - ay * cx)+ cc * (ax * by - ay * bx)) < 0);
   }

    /*
      Devolvemos true si t esta a la izquierda de la linea que va de A a B.	
    */
    private boolean Izquierda(Vertice t, Vertice a, Vertice b)
    { 
            return (a.get_x() * (b.get_y() - t.get_y()) - a.get_y() * (b.get_x() - t.get_x()) + ((b.get_x() * t.get_y()) - (b.get_y() * t.get_x())) > 0);
    }

}
