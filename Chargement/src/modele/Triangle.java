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
public class Triangle extends Forme {
    
    /**
     * Attribut contenant les 3 sommets du triangle isocèle.
     * 
     * Le 1er point représente le clic de la souris, associé à la base.
     * Le 2e point représente l'autre point associé à la base.
     * Le 3e point représente le sommet opposé à la base.
     */
    private Point points[];
    
    
    /**
     * Constructeur par données de la classe Triangle
     * @param c
     * @param pDebut
     * @param pFin 
     */
    public Triangle(Color c, Point pDebut, Point pFin)
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
        
        this.points = new Point[3];
        
        // On insère le point de début
        this.points[0] = new Point(pDebut);
        
        // On insère l'autre point associé à la base, dont l'abscisse correspond à celle de pFin et dont l'ordonné correspond à celle de pDebut.
        this.points[1] = new Point( pFin.getX(), pDebut.getY() );
        
        // On insère le sommet, dont l'ordonnée correspond à celle de pFin et dont l'abscisse correspond au milieu de pDebut et pBase.
        this.points[2] = new Point( (pDebut.getX() + pFin.getX())/2  , pFin.getY());
        
        
        
    }
    
    
    @Override
    public void seDessiner(Graphics g)
    {
        super.seDessiner(g);

        int x[] = new int[this.points.length];
        int y[] = new int[this.points.length];
        
        for(int i = 0; i < this.points.length; i++)
        {
            x[i] = this.points[i].getX();
            y[i] = this.points[i].getY();
        }
        
        g.drawPolygon(x, y, 3);
    }
    
}
