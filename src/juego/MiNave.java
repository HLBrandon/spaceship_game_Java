package juego;

import java.awt.event.KeyEvent;
import java.awt.Graphics;
import java.awt.geom.Ellipse2D;
import javax.swing.ImageIcon;

public class MiNave {

    public static int x = 310, y = 850;//posición inicial de la nave.
    public static int vida = 3;//Numero de vidas al comienzo del juego.

    public void paint(Graphics g) {//Metodo para dibujar la nave.
        ImageIcon miNave = new ImageIcon(getClass().getResource("../img/MiNave.png"));
        g.drawImage(miNave.getImage(), x, y, 80, 80, null);
        //g.drawOval(x + 10, y + 10, 60, 70);
    }

    public void keyPressed(KeyEvent e) {
        //***************** ESTO ES PARA MOVERSE EN X
        if (e.getKeyCode() == 37 || e.getKeyCode() == 65) { // ir izquierda.
            if (x > 10) {
                x = x - 30;// 30 incremento del movimiento de la nave.
                //37 codigo de la flecha izquierda.
                //65 codigo de la tecla A.
            }
        }
        if (e.getKeyCode() == 39 || e.getKeyCode() == 68) { // ir derecha.
            if (x < 600) {
                x = x + 30;
                //39 codigo de la flecha derecha.
                //68 codigo de la tecla D.
            }
        }
        //****************** ESTO ES PARA MOVERSE EN Y.
        if (e.getKeyCode() == 38 || e.getKeyCode() == 87) {// subir.
            if (y > 10) {
                y = y - 30;
                //38 codigo de la flecha arriba.
                //87 codigo de la tecla W.
            }
        }
        if (e.getKeyCode() == 40 || e.getKeyCode() == 83) { // bajar.
            if (y < 880) {
                y = y + 30;
                //40 codigo de la flecha abajo.
                //83 codigo de la tecla S.
            }
        }
        if (e.getKeyCode() == 27) {
            System.exit(0);
        }
    }
    
    public int MisVidas(){// Funcion que obtiene el conteo de la vidas.
        return vida;
    }
    
    public Ellipse2D getBoundsMiNave() {
        /**
         * Metodo para crear un perimetro alrededor de la nave.
         * Se crea una elipse que encierra a la nave.
         * Sirve para detectar una colisión.
         */
        return new Ellipse2D.Double(x + 10, y + 10, 60, 70);
    }

}
