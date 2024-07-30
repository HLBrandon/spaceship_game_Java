
package juego;
/**
 * MI PRIMER JUEGO REALIZADO EN JAVA
 * El juego es simple, consta de una nave la cual tripulamos con el teclado.
 * Pueden ser las flechas del teclado, o es uso de las teclas WASD.
 * El objetivo es ir recolectando monedas. A su vez deberas esquivar a las naves enemigas. 
 * Reunir cinco monedas aumenta al siguiente nivel. Aumentando la velocidad del enemigo.
 * El juego cuenta solo con tres niveles.
 * 
 * Estudiantes:
 *  BHL,
 *  CJRR
 *  KGQ
 *  AGQ
 */

import java.awt.Color;
import java.awt.Font;
import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.Graphics;
import javax.swing.ImageIcon;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.JOptionPane;

public class Juego extends JPanel {
    
    public static boolean choqueNaves = false;
    MiNave miNave = new MiNave();
    NaveEnemiga enemigo = new NaveEnemiga(this);
    Modenas moneda = new Modenas(this);
    diamante diam = new diamante(this);
    
    public Juego () {
        addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {
                
            }

            @Override
            public void keyPressed(KeyEvent e) {
                miNave.keyPressed(e);
            }

            @Override
            public void keyReleased(KeyEvent e) {
                
            }
        });
        setFocusable(true);
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        ImageIcon cielo = new ImageIcon(getClass().getResource("../img/cielo.jpg"));
        ImageIcon espacio = new ImageIcon(getClass().getResource("../img/espacio.jpg"));
        ImageIcon espacio2 = new ImageIcon(getClass().getResource("../img/espacio2.jpg"));
        ImageIcon corazon = new ImageIcon(getClass().getResource("../img/corazon.png"));
        if (Modenas.nivel == 1) {
            g.drawImage(cielo.getImage(), 0, 0, getWidth(), getHeight(), null);
        }
        if (Modenas.nivel == 2) {
            g.drawImage(espacio.getImage(), 0, 0, getWidth(), getHeight(), null);
        }
        if (Modenas.nivel >= 3) {
            g.drawImage(espacio2.getImage(), 0, 0, getWidth(), 1200, null);
        }
        
        // INTERFAZ VISUAL. EL CONTADOR DE VIDAS, PUNTOS Y NIVELES.
        Font fuente = new Font("Impact", Font.BOLD, 25);
        g.setFont(fuente);
        g.setColor(Color.ORANGE);
        g.drawString("Lv: " + moneda.obtenerNIvel(), 10, 70);
        g.drawString("Pts: " + moneda.obtenerPuntos(), 350, 30);
        g.drawString("Total pts: " + moneda.obtenerPuntosTotales(), 500, 30);
        g.drawString("Lives:", 10, 30);
        for (int i = 0; i < miNave.MisVidas(); i++) {
            g.drawImage(corazon.getImage(), 90 + (i*50), 5, 40, 40, this);
        }
        
        enemigo.paint(g);
        moneda.paint(g);
        diam.paint(g);
        miNave.paint(g);
        moneda.MoverMoneda();
        diam.moverDiamante();
        enemigo.moverEnemigo();
        
    }
    
    public static void reiniciar(){
        MiNave.x = 310;
        MiNave.y = 850;
        MiNave.vida = 3;
        NaveEnemiga.xEnemigo1 = 310;
        NaveEnemiga.xEnemigo2 = 100;
        NaveEnemiga.xEnemigo3 = 510;
        NaveEnemiga.yEnemigo1 = -100;
        NaveEnemiga.yEnemigo2 = -500;
        NaveEnemiga.yEnemigo3 = -800;
        NaveEnemiga.incrementoEnemigo = 10;
        choqueNaves = false;
        Modenas.xMoneda1 = 310;
        Modenas.yMoneda1 = -200;
        Modenas.puntos = 0;
        Modenas.puntosTotales = 0;
        Modenas.nivel = 1;
        diamante.xDiamante = 300;
        diamante.yDiamante = -1000;
    }
 
    public static void main(String[] args) {
        JFrame ventana = new JFrame("My First Game");
        Juego game = new Juego();
        ventana.add(game);
        ventana.setSize(700, 1000);
        ventana.setVisible(true);
        ventana.setResizable(false);
        ventana.setLocationRelativeTo(null);
        ventana.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        while (true) {
            if (Modenas.nivel >= 4) {
                int reinciarJuego = JOptionPane.showConfirmDialog(null, "WON!!\n"
                        + "Do you want to play again?", "You are a winner", JOptionPane.YES_NO_OPTION);
                if (reinciarJuego == 0) {
                    reiniciar();
                } else if (reinciarJuego == 1) {
                    System.out.println("Thanks for playing!");
                    System.exit(0);
                }
            }
            if (MiNave.vida == 0) {
                int reinciarJuego = JOptionPane.showConfirmDialog(null, "Loser!!\n"
                        + "Do you want to play again?", "Game Over", JOptionPane.YES_NO_OPTION);
                if (reinciarJuego == 0) {
                    reiniciar();
                } else if (reinciarJuego == 1) {
                    System.out.println("Thanks for playing!");
                    System.exit(0);
                }
            }

            try {
                Thread.sleep(10);
            } catch (InterruptedException ex) {
                System.out.println(ex.toString());
            }
            game.repaint();
        }
    }
}
