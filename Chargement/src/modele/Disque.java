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
public class Disque extends Cercle{
    
    /**
     * @param centre s'il vaut null, le centre du cercle sera représenté par le point de coordonnées (0,0)
     * @param rayon s'il est négatif ou nul, il vaudra 1.
     * @param couleur 
     */
    public Disque(Point centre, int rayon, Color couleur)
    {
        super(centre ,rayon,couleur);
    }
    
    
    @Override
    public void seDessiner(Graphics g)
    {
        super.seDessiner(g);

        g.fillOval(super.getpHautGauche().getX() , super.getpHautGauche().getY(), super.getDiametre(), super.getDiametre());
    }
}
