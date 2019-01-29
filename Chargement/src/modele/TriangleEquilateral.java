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
public class TriangleEquilateral extends Forme {
    
    /**
     * Attribut contenant les 3 sommets du triangle équilatéral
     * 
     * Le 1er point représente le clic de la souris.
     * Le 2e point a pour abscisse l'abscisse du point de relachement de la souris et pour ordonnée l'ordonnée du point de clic de la souris.
     * Le 3e point est le sommet restant.
     * 
     * Le triangle sera orienté vers le bas lorsque l'ordonnée de pFin est plus grande.
     */
    private Point points[];
    
    
    public TriangleEquilateral(Color c, Point pDebut, Point pFin)
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
                
        this.points[1] = new Point( pFin.getX(), pDebut.getY());
        
        int distancePts = points[1].getDistance(points[0]);
        
        // distances 1D ar rapport à pInit
        int distanceXPt2 = (int)(distancePts*Math.cos( Math.PI/3));
        int distanceYPt2 = (int)(distancePts*Math.sin( Math.PI/3));
        
        int orientation = 1; // orientation = 1 <==> orientation vers le bas
        if( pFin.getY() < pDebut.getY())
            orientation = -1;
        
        this.points[2] = new Point( Math.min(pDebut.getX(),pFin.getX()) + distanceXPt2, pDebut.getY() + distanceYPt2*orientation );
        
        
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
        
        g.drawPolygon(x, y, this.points.length);
    }
    
}
