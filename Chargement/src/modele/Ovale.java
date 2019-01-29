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
public class Ovale extends Forme {
    
    /**
     * Point situé en haut à gauche de l'ovale.
     */
    private Point pHautGauche;
    
    /**
     * Hauteur de l'ovale.
     */
    private int height;
    
    /**
     * Largeur de l'ovale.
     */
    private int width;

    
    /**
     * Constructeur par données de la classe Ovale.
     * @param couleurForme
     * @param pDebut
     * @param pFinal 
     */
    public Ovale( Color couleurForme,Point pDebut, Point pFinal) 
    {
        super(couleurForme);
        if( pDebut == null)
        {
            pDebut = new Point(0,0);
        }
        if( pFinal == null)
        {
            pFinal = new Point(0,0);
        }
       
        int diffX = pFinal.getX() - pDebut.getX();
        int diffY = pFinal.getY() - pDebut.getY();
        
        this.width = Math.abs(diffX);
        this.height = Math.abs(diffY);

        
        int x = pDebut.getX();
        int y = pDebut.getY();
       
        // le point de clic est à gauche du point de relachement.
        if( diffX < 0 )
            x = pFinal.getX();
        
        if( diffY < 0)
            y = pFinal.getY();
        
        this.pHautGauche = new Point(x,y); 
    }
    
    
    @Override
    public void seDessiner(Graphics g)
    {
        super.seDessiner(g);
        g.drawOval(this.pHautGauche.getX() , this.pHautGauche.getY(), this.width, this.height);
    }
    
}
