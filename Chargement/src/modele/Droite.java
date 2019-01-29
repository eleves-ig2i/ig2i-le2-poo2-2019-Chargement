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
public class Droite extends Forme {
    
    private Point pA;
    private Point pB;
    
    public Droite(Color c, Point p1, Point p2)
    {
        super(c);
        pA = new Point(p1);
        pB = new Point(p2);
    }
    
    
    @Override
    public void seDessiner(Graphics g)
    {
        super.seDessiner(g);
        g.drawLine(pA.getX(), pA.getY(), pB.getX(), pB.getY());
    }
}
