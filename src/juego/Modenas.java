package juego;

import java.awt.Graphics;
import java.awt.geom.Area;
import java.awt.geom.Ellipse2D;
import javax.swing.ImageIcon;

public class Modenas {

    public static int xMoneda1 = 310, yMoneda1 = -200;

    public static int puntos = 0;
    public static int puntosTotales = 0;
    public Juego juego;
    public static int nivel = 1;
    public static int incremento = 5;
    
    Area area1, prebencion, areaMoneda;

    public Modenas(Juego juego3) {
        this.juego = juego3;
    }

    public void paint(Graphics g) {
        ImageIcon moneda = new ImageIcon(getClass().getResource("../img/moneda.png"));
        if (nivel >= 1) {
            g.drawImage(moneda.getImage(), xMoneda1, yMoneda1, 50, 50, null);
        }
    }
    
    public boolean tocarMoneda() {
        Area areaNave = new Area(juego.miNave.getBoundsMiNave());
        areaNave.intersect(getBoundsMoneda());
        return !areaNave.isEmpty(); //no esta vacio, hay intersección.
    }
    
    public void MoverMoneda () {
        if (tocarMoneda()) {
            puntos++;
            puntosTotales++;
            yMoneda1 = -190;
            xMoneda1 = (int) (Math.random() * 600 + 1);
        }
        if (puntos >= 5) {
            nivel++;
            yMoneda1 = -190;
            xMoneda1 = (int) (Math.random() * 600 + 1);
            puntos = 0;
        }
        if (nivel >= 1) {
            if (yMoneda1 > 1000) { //ha terminado su recorrido
                yMoneda1 = -190;
                xMoneda1 = (int) (Math.random() * 600 + 1); //nueva posicion
            } else {
                yMoneda1 = yMoneda1 + incremento;
            }
        }
    }
    
    public int obtenerPuntos(){
        return puntos;
    }
    public int obtenerPuntosTotales() {
        return puntosTotales;
    }
    public int obtenerNIvel () {
        return nivel;
    }
    
    public Area getBoundsMoneda() {
        Ellipse2D aMoneda, aPrebe;
        aPrebe= new Ellipse2D.Double(xMoneda1, yMoneda1, 50, 50);
        prebencion = new Area(aPrebe);
        areaMoneda = prebencion;
        if (nivel >= 1) {
            aMoneda = new Ellipse2D.Double(xMoneda1, yMoneda1, 48, 48);
            area1 = new Area(aMoneda);
            areaMoneda.add(area1);
        }
        return areaMoneda;
    }

}
