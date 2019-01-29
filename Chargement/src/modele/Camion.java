/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modele;

import java.awt.Color;
import java.awt.Graphics;

/**
 *
 * @author user
 */
public class Camion extends Forme {
    
    
    /**
     * Rectangle représentant la remorque du camion (situé à l'arrière du corps).
     */
    private Rectangle remorque;
    
    /**
     * Rectangle représentant le corps du camion (situé à l'avant de la remorque).
     */
    private Rectangle corps;

    /**
     * Rectangle représentant la fenêtre situé dans le corps.
     */
    private Rectangle fenetre;
    
    /**
     * Roue représentant la roue arrière du camion 2D
     */
    private Cercle roueArriere;
    
    /**
     * Roue représentant la roue avant du camion 2D
     */
    private Cercle roueAvant;
    
    
    /**
     * Constructeur par données de la classe Camion.
     * @param couleurForme
     * @param pInit
     * @param pFin 
     */
    public Camion(Color couleurForme, Point pInit, Point pFin) {
        super(couleurForme);

        int xMin = Math.min(pInit.getX(), pFin.getX());
        int yMin = Math.min(pInit.getY(), pFin.getY());
       
        int xMax = Math.max(pInit.getX(), pFin.getX());
        int yMax = Math.max(pInit.getY(), pFin.getY());
        
        int width = xMax - xMin;
        int height = yMax - yMin;
        
        // Remorque
        this.remorque = new Rectangle(couleurForme, new Point(xMin,yMin), new Point(xMin+(int)(0.77*width),yMin+(int)(0.85*height)));
        
        // Roue arrière
        // g.drawOval(xMin - (int)(width*0.19), yMin + (int)(height*0.95), width/4, height/3);
        this.roueArriere = new Cercle(couleurForme, new Point( xMin - (int)(width*0.2), yMin + (int)(height*0.95) ), new Point( xMin - (int)(width*0.05), yMin + (int)(height*0.65) ));

    }
    
    
    @Override
    public void seDessiner(Graphics g)
    {
        System.out.println("oui");
        super.seDessiner(g);
        remorque.seDessiner(g);
        //corps.seDessiner(g);
        roueArriere.seDessiner(g);
        //roueAvant.seDessiner(g);
        //fenetre.seDessiner(g);
    }
    
    
    
}
