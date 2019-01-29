/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vuecontrole;

import java.awt.Color;
import java.awt.event.MouseEvent;
import javax.swing.*;

/**
 *
 * @author user
 */
public class BarreBasse extends JPanel {
    private JLabel message;
    private JLabel x;
    private JLabel y;

    public BarreBasse() {
        
        message = new JLabel("Paint by SALEZ Nathan");
        x = new JLabel("x -");
        y = new JLabel("y -");
        message.setForeground(Color.RED);
        
        this.add(message);
        this.add(x);
        this.add(y);
        
        this.setBackground(Color.LIGHT_GRAY);
    }

    
    /**
     * modifie l’affichage de la barre basse (les valeurs de x et y) en fonction des coordonnées contenues dans l’objetevt.
     * @param evt 
     */
    public void deplacementSouris (MouseEvent evt)
    {
        //System.out.println("Entrée dans la fonction deplacementSouris().");
        if( evt != null)
        {
            this.setX(" x - " + Integer.toString( evt.getX() ) );
            this.setY(" y - " + Integer.toString( evt.getY() ) );
        }
    }
        
    
    
    
    public void setMessage(String message) {
        if( message != null)
            this.message.setText(message);
    }

    
    public void setX(String x) {
        if( x != null)
            this.x.setText(x);
    }

    public void setY(String y) {
        if( y != null)
            this.y.setText(y);
    }
    
    
    public static void main(String[] args) {
        JFrame maFenetre = new JFrame();
        BarreBasse barre = new BarreBasse();
        maFenetre.setSize(400, 200);
        maFenetre.add(barre);
        maFenetre.setVisible(true);
    }
    
    
    
    
    
}
