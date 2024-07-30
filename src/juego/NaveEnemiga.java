
package juego;

import java.awt.Graphics;
import java.awt.geom.Area;
import java.awt.geom.Ellipse2D;
import javax.swing.ImageIcon;

public class NaveEnemiga {
    // Coordenadas iniciales de las naves enemigas.
    public static int xEnemigo1 = 310, yEnemigo1 = -100;
    public static int xEnemigo2 = 100, yEnemigo2 = -500;
    public static int xEnemigo3 = 510, yEnemigo3 = -800;
    
    public Modenas monedas;// Objeto de la clase Modenas.
    public Juego juego;// Objeto de la clase juego.
   
    public static int incrementoEnemigo = 10;//Velocidad de las naves.
    Area areaNave1, areaNave2, areaNave3, areaNave;//Areas de las naves.
    
    public NaveEnemiga(Juego juego) {//Constructor.
        this.juego = juego;
    }
    
    public void paint(Graphics g) {//Metodo para pintar las naves.
        ImageIcon ene = new ImageIcon(getClass().getResource("../img/enemigoNave.png"));
        if (Modenas.nivel >= 1) {
            //Nivel 1, solo hay una nave pintada fuera de la pantalla.
            g.drawImage(ene.getImage(), xEnemigo1, yEnemigo1, 80, 80, null);
        }
        if (Modenas.nivel >= 2) {
            //Nivel 2, se pinta la segunda nave, ahora son dos.
            g.drawImage(ene.getImage(), xEnemigo2, yEnemigo2, 80, 80, null);
            incrementoEnemigo = 12;//En el nivel 2 se aumentar la velocidad.
        }
        if (Modenas.nivel >= 3) {
            //nivel 3, se pinta la tercera nave, ahora son tres.
            g.drawImage(ene.getImage(), xEnemigo3, yEnemigo3, 80, 80, null);
            incrementoEnemigo = 14;//Se vuelve aumentar la velocidad.
        }
    }
    
    public boolean choque () {//Metodo la detectar una colisión.
        //Se crea un area usando la elipse de la clase MiNave.
        Area areaMiNave = new Area(juego.miNave.getBoundsMiNave());
        //Se busca una intersección con un area de la nave del enemigo.
        areaMiNave.intersect(getBoundsEnemigo());
        //Si la intersección NO esta vacia, significa que existio un choque.
        return !areaMiNave.isEmpty();//True si hay choque, false si no hay.
    }
    
    public void moverEnemigo () {// Metodo de movimiento de las naves.
        if (choque()) {// Si hubo un choque. Pierdes una vida.
            juego.choqueNaves = true; //choqueNaves cambia de false a true.
            MiNave.vida--; //Se reduce la vida una unidad.
            MiNave.x = 310;// La mi nave vuelve a su posición original en x
            MiNave.y = 850;// La mi nave vuelve a su posición original en y
            /**
             * Se cambia la posicion de las naves de todos los enemigos.
             * Los valores en Y vuelven a su posicion orginal.
             * Los valores en X se calculan de forma random entre 0 y 600.
             */
            yEnemigo1 = -100; 
            xEnemigo1 = (int)(Math.random() * 600 + 0);
            yEnemigo2 = -150;
            xEnemigo2 = (int)( Math.random() * 600 + 0);
            yEnemigo3 = -250;
            xEnemigo3 = (int)( Math.random() * 600 + 0);
        }
        if (Modenas.nivel >= 1) {//En el nivel 1, solo se mueve el enemigo 1.
            if (yEnemigo1 > 1000) {//Si el enemigo terminó su recorrido desde arriba a abajo.
                yEnemigo1 = -100;//Vuelve a su posicion orginal en Y fuera de la pantalla.
                xEnemigo1 = (int)(Math.random() * 600 + 0);//Se calcula un nuevo valor para X entre 0 y 600;
            } else {//La nave enemiga no ha completado su recorrido.
                //Va aumentando su valor en Y, comenzado aparecer en la pantalla desde arriba.
                yEnemigo1 = yEnemigo1 + incrementoEnemigo;
            }
        } 
        if (Modenas.nivel >= 2) {//En el nivel 2, comienza a moverse en enemigo 2.
            if (yEnemigo2 > 1000) {// la nave termino su recorrido
                yEnemigo2 = -150; //vuelve a su posicion original
                xEnemigo2 = (int)( Math.random() * 600 + 0);//nueva posicion random.
            } else {//No ha terminado su recorrido.
                yEnemigo2 = yEnemigo2 + incrementoEnemigo;//comienza abajar.
            }
        }
        if (Modenas.nivel >= 3) {//En el nivel 3, comienza a moverse en enemigo 3.
            if (yEnemigo3 > 1000) {// la nave termino su recorrido
                yEnemigo3 = -250; //vuelve a su posicion original
                xEnemigo3 = (int)( Math.random() * 600 + 0);//nueva posicion random.
            } else {//No ha terminado su recorrido.
                yEnemigo3 = yEnemigo3 + incrementoEnemigo;//comienza abajar.
            }
        }
    }
    
    public Area getBoundsEnemigo(){//Metodo para obtener las areas de las naves enemigas.
        Ellipse2D nave1, nave2, nave3;//Elipses que envolveran a las naves enemigas.
        
        //creando una area para evitar error.
        nave3 = new Ellipse2D.Double(xEnemigo1 + 10, yEnemigo1 + 10, 60, 60);
        areaNave3 = new Area(nave3);
        areaNave = areaNave3;
        
        //Si es el nivel 1.
        if (Modenas.nivel >= 1) {
            //Se crea a la elipse que cubre a la nave enemiga 1
            nave1 = new Ellipse2D.Double(xEnemigo1 + 10, yEnemigo1 + 10, 60, 60);
            //La elipse de la nave 1 se combierte en un area.
            areaNave1 = new Area(nave1);
            //Se agrega esta area a la AreaNave.
            areaNave.add(areaNave1);
        }
        //Si es el nivel 2.
        if (Modenas.nivel >= 2) {
            //Se crea a la elipse que cubre a la nave enemiga 2
            nave2 = new Ellipse2D.Double(xEnemigo2 + 10, yEnemigo2 + 10, 60, 60);
            //La elipse de la nave 2 se combierte en un area.
            areaNave2 = new Area(nave2);
            areaNave.add(areaNave2);
        }
        //Si es el nivel 3.
        if (Modenas.nivel >= 3) {
            //Se crea a la elipse que cubre a la nave enemiga 3.
            nave3 = new Ellipse2D.Double(xEnemigo3 + 10, yEnemigo3 + 10, 60, 60);
            //La elipse de la nave 2 se combierte en un area.
            areaNave3 = new Area(nave3);
            areaNave.add(areaNave3);
        }
        return areaNave;//Devuelve el AreaNave.
    }
    
}
