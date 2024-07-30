
package juego;

import java.awt.Graphics;
import java.awt.geom.Area;
import java.awt.geom.Ellipse2D;
import javax.swing.ImageIcon;

public class diamante {
    public static int xDiamante = (int) (Math.random() * 600 + 1);;
    public static int yDiamante = -1000;
    public static int incremento = 2;
    
    public Juego juego;
    
    Area area1, areaInicializacion, areaDiamante;

    public diamante(Juego juego) {
        this.juego = juego;
    }
    
    public void paint(Graphics g) {
        ImageIcon diamante = new ImageIcon(getClass().getResource("../img/diamond.png"));
        if (Modenas.nivel >= 1) {
            g.drawImage(diamante.getImage(), xDiamante, yDiamante, 80, 80, null);
        }
    }
    
    public Area getBoundsDiamante () {
        Ellipse2D diamante, inicializacion;
        inicializacion = new Ellipse2D.Double(xDiamante, yDiamante, 80, 80);
        areaInicializacion = new Area(inicializacion);
        areaDiamante = areaInicializacion;
        if (Modenas.nivel >= 1) {
            diamante = new Ellipse2D.Double(xDiamante + 12, yDiamante, 50, 80);
            area1 = new Area(diamante);
            areaDiamante.add(area1);
        }
        return areaDiamante;
    }
    
    public boolean tocarDiamante () {
        Area areaNave = new Area(juego.miNave.getBoundsMiNave());
        areaNave.intersect(getBoundsDiamante());
        return !areaNave.isEmpty(); //no esta vacio, hay intersección.
    }
    
    public void moverDiamante () {
        
        if (tocarDiamante()) {
            Modenas.puntos = Modenas.puntos + 3;
            Modenas.puntosTotales = Modenas.puntosTotales + 3;
            yDiamante = -1000;
            xDiamante = (int) (Math.random() * 600 + 1);
        }
        
        if (Modenas.nivel >= 2) {
            if (yDiamante > 1000) {//hizo todo su recorrido
                yDiamante = -1000;
                xDiamante = (int)(Math.random() * 600 + 1);
            } else {
                yDiamante = yDiamante + incremento;
            }
        }
    
    }
    
}
