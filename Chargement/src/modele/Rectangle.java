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
public class Rectangle extends Forme {
    
    private Point pHautGauche;
    private int width;
    private int height;
    
    
    /**
     * Constructeur par données de la classe Rectangle
     * @param c
     * @param pDebut
     * @param pFin 
     */
    public Rectangle(Color c, Point pDebut, Point pFin)
    {
        super(c);

        if( pDebut == null)
        {
            pDebut = new Point(0,0);
        }
        if( pFin == null)
        {
            pFin = new Point(0,0);
        }
        
        int diffX = pFin.getX() - pDebut.getX();
        int diffY = pFin.getY() - pDebut.getY();
        
        this.width = Math.abs(diffX);
        this.height = Math.abs(diffY);
        
        int x = pDebut.getX();
        int y = pDebut.getY();
       
        // le point de clic est à gauche du point de relachement.
        if( diffX < 0 )
            x = pFin.getX();
        
        if( diffY < 0)
            y = pFin.getY();
        
        this.pHautGauche = new Point(x,y); 
    }
    
    @Override
    public void seDessiner(Graphics g)
    {
        super.seDessiner(g);

        g.drawRect(this.pHautGauche.getX(), this.pHautGauche.getY(), this.width , this.height);
    }
    
}
