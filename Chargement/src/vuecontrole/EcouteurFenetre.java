/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vuecontrole;

import java.awt.event.*;

/**
 *
 * @author user
 */
public class EcouteurFenetre implements WindowListener{

    @Override
    public void windowOpened(WindowEvent e) {
        System.out.println("On ouvre la fenêtre pour la première fois.");
    }

    @Override
    public void windowClosing(WindowEvent e) {
        System.out.println("L'utilisateur ferme la fenêtre avec le menu système.");
    }

    @Override
    public void windowClosed(WindowEvent e) {
        System.out.println("Fenêtre fermée avec la méthode dispose().");
    }

    @Override
    public void windowIconified(WindowEvent e) {
        System.out.println("Changement d'état : Fenêtre => Icône.");
    }

    @Override
    public void windowDeiconified(WindowEvent e) {
        System.out.println("Changement d'état : Icône => Fenêtre.");
    }

    @Override
    public void windowActivated(WindowEvent e) {
        System.out.println("La fenêtre devient active.");
    }

    @Override
    public void windowDeactivated(WindowEvent e) {
        System.out.println("La fenêtre devient inactive.");
    }
    
    
}
