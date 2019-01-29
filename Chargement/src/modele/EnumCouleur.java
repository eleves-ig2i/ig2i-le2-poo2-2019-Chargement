/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modele;

import java.awt.Color;

/**
 *
 * @author user
 */
public enum EnumCouleur {
    
    ROUGE(Color.RED),
    BLEU(Color.BLUE),
    VERT(Color.GREEN),
    JAUNE(Color.YELLOW),
    NOIR(Color.BLACK);
    
    private Color couleurAssocie;
    
    private EnumCouleur(Color c)
    {
        if( c != null )
        {
            this.couleurAssocie = c;
        }
    }

    public Color getCouleurAssocie() {
        return couleurAssocie;
    }
    
    
    
    
}
