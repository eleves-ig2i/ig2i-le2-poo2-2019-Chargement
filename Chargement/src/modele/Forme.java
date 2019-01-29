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
public abstract class Forme {
    
    private Color couleurForme;

    
    public Forme(Color couleurForme) {
        if( couleurForme != null)
            this.couleurForme = couleurForme;
        else
            couleurForme = Color.BLACK;
    }
    
    
    public void seDessiner(Graphics g)
    {
        if( g != null)
        {
            g.setColor(couleurForme);
        }
    }
}
