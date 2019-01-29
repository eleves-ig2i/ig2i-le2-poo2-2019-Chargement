/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vuecontrole;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Collection;
import javax.swing.*;
import modele.Forme;
import porteconteneurs.PorteConteneurs;

/**
 *
 * @author user
 */
public class Fenetre extends JFrame {
    
    /**
     * Gestion de l'affichage des coordonnées de la souris.
     */
    private BarreBasse barre;
    
    /**
     * Gestion des choix de dessinage (coleur + forme)
     */
    private BarreHaute choix;
    
    
    private ZoneGraphique graphe;
    private EcouteurFenetre objetEcouteur;
    
    private JMenuBar fichier;
    
    public Fenetre()
    { 
        super("POO2 TP8");
        
        this.barre = new BarreBasse();
        this.choix = new BarreHaute(this);
        this.graphe = new ZoneGraphique(barre,choix);
        this.objetEcouteur = new EcouteurFenetre();
        
        // Construction de l'item Quitter
        JMenuItem itemQuitter = new JMenuItem("Quitter");   
        
        // Construction de l'action de l'item Quitter
        itemQuitter.addActionListener(
            new ActionListener()
            {
                @Override
                public void actionPerformed(ActionEvent e) {
                    System.exit(0);
                }
            }    
        );
        
        // Construction du menu fichier
        JMenu menuFichier = new JMenu("Fichier");
        menuFichier.add(itemQuitter);
        
        // Construction de la barre de menu
        fichier = new JMenuBar();
        fichier.add(menuFichier);
        this.setJMenuBar(fichier);
        
        
        this.addWindowListener(objetEcouteur);
        
        this.setVisible(true);
        this.setSize(400,400);
        this.setLocationRelativeTo(null);   // fenêtre au milieu de l'écran
        
        this.setDefaultCloseOperation(EXIT_ON_CLOSE); // fermeture de l'app lorsqu'on clique sur la croix rouge.
        
        this.setLayout( new BorderLayout() );   // A AJOUTER POUR LES JPANEL
        this.add(this.barre,BorderLayout.SOUTH); 
        this.add(graphe);
        this.add(choix,BorderLayout.NORTH);

    }
    
    
    public void effacer()
    {
        graphe.effacer();
    }
    
    
    public void defaire()
    {
        graphe.defaire();
    }
    
    public void activerGomme()
    {
        graphe.activerGomme();
    }
    
    
    public void desactiverGomme()
    {
        graphe.desactiverGomme();
    }
    
    
    
    public void dessinerPorteConteneurs (PorteConteneurs p)
    {
        Collection<Forme> listeDessins = p.getDessin();
        for( Forme f : listeDessins)
        {
            this.graphe.ajouterForme(f);
        }
        
    }
    
    
    public static void main(String[] args) {
        
        // TP8 Q1
        /*
        JFrame maFenetre = new JFrame("TP8 POO2 Q1");
        maFenetre.setVisible(true);
        maFenetre.setSize(300,150); // dimensions de la fenetre
        maFenetre.setLocationRelativeTo(null);  // fenêtre au milieu de l'écran
        */ 
        
        
        // TP8 Q2
        Fenetre maFenetre = new Fenetre();
        
    }
}
