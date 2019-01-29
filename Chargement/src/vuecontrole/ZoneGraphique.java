/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vuecontrole;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseListener;
import java.util.LinkedList;

import javax.swing.*;
import modele.Camion;
import modele.Cercle;
import modele.Droite;
import modele.EnumCouleur;
import modele.EnumForme;
import modele.Forme;
import modele.Gomme;
import modele.Ovale;
import modele.Point;
import modele.Rectangle;
import modele.Triangle;
import modele.TriangleEquilateral;


/**
 *
 * @author user
 */
public class ZoneGraphique extends JPanel implements MouseMotionListener, MouseListener {

    /**
     * Contient une référence sur la barre basse de la fenetre.
     */
    private BarreBasse barre;
    
    /**
     * Contient une référence sur la barre haute de la fenetre.
     */
    private BarreHaute choix;
    
    /**
     * représente le point lorsque l’utilisateur appuie sur la souris (mis à jour à chaque nouvel appui).
     */
    private Point pInit;
    
    /**
     * représente le point lorsque l’utilisateur relâche la souris (mis à jour à chaque relâchement de la souris).
     */
    private Point pFin;
    
    
    /**
     * indique si nous sommes en train de dessiner (glissé de la souris avec le bouton appuyé = true) 
     * ou si le dessin est terminé (bouton relâché = false).
     */
    private boolean drawing;
    
    
    /**
     * Indique si l'utilisateur gomme ou pas les dessins.
     */
    private boolean gommeEnCours;
    
    
    /**
     * Liste des dessins effectués sur la zone graphique.
     */
    private LinkedList<Forme> listeDessins;
    
    
    public ZoneGraphique(BarreBasse barre, BarreHaute choix) {
        super();
        
        this.gommeEnCours = false;
        
        this.addMouseMotionListener(this);
        this.addMouseListener(this);
        
        if( barre != null)
            this.barre = barre;
        else
            this.barre = new BarreBasse();
        
        if( choix != null)
            this.choix = choix;
        else
            this.barre = new BarreBasse();
        
        this.drawing = false;
        this.listeDessins = new LinkedList<>();
        
        this.setBackground(Color.WHITE);
    }

    /**
     * Cette méthode crée un nouvel objet selon la forme sélectionnée dans la liste déroulante 
     * et fait appel à la méthode seDessiner sur cet objet afin de visualiser la forme sur la zone graphique
     */
    private void dessin()
    {
        if( gommeEnCours )
        {
            gommer();
        }
        else
        {
            EnumForme forme = this.choix.getFormeSelectionnee();
            EnumCouleur couleur = this.choix.getCouleurSelectionnee();
            Forme formeDessin = null;

            switch(forme)
            {
                case DROITE : 
                    formeDessin = new Droite(couleur.getCouleurAssocie(), this.pInit, this.pFin);
                break;

                case RECTANGLE :
                    formeDessin = new Rectangle(couleur.getCouleurAssocie(), this.pInit, this.pFin );
                break;

                case CERCLE :
                    formeDessin = new Cercle(couleur.getCouleurAssocie(), this.pInit, this.pFin);
                break;

                case OVALE : 
                    formeDessin = new Ovale(couleur.getCouleurAssocie(), this.pInit, this.pFin);
                break;

                case TRIANGLE :
                    formeDessin = new Triangle(couleur.getCouleurAssocie(), this.pInit, this.pFin);
                break;

                case CAMION :
                    formeDessin = new Camion(couleur.getCouleurAssocie(), this.pInit, this.pFin);
                break;
                
                case TRIANGLE_EQUILATERAL :
                    formeDessin = new TriangleEquilateral(couleur.getCouleurAssocie(), this.pInit, this.pFin);
                break;
                
                default:
                    System.out.println("Cas non prévu !");
            }

            if( formeDessin != null)
            {
                if( this.drawing == true )
                {
                    this.listeDessins.removeLast();         
                }

                this.listeDessins.add(formeDessin); 
            }

            this.repaint();

        }     
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g); 
        for(Forme f : this.listeDessins )
        {
            f.seDessiner( g );
        }
    }  
    
    
    @Override
    public void mouseMoved(MouseEvent e) {
        //System.out.println("Entrée dans la méthode mouseMoved()");
        this.barre.deplacementSouris(e);
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        System.out.println("L'utilisateur effectue un glisser/déposer.");
        this.barre.deplacementSouris(e);
        this.pFin = new Point( e.getPoint().x, e.getPoint().y );
        this.dessin();
        if( this.drawing == false)
        {
            this.drawing = true;
        }
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        System.out.println("L'utilisateur a cliqué sur la zone graphique !");
    }

    @Override
    public void mousePressed(MouseEvent e) {
        //System.out.println("L'utilisateur a pressé le bouton de la souris sur la zone graphique !");
        System.out.println("Point : " + e.getPoint().toString()); // OK
        this.pInit = new Point( e.getPoint().x, e.getPoint().y);
        System.out.println("Relâcher pour voir la forme.");
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        //System.out.println("L'utilisateur a relâché le bouton de la souris sur la zone graphique !");
        this.drawing = false;
        this.pFin = new Point( e.getPoint().x, e.getPoint().y );
        System.out.println("Point : " + e.getPoint().toString()); // OK
        //this.dessin();
        System.out.println("Cliquez pour initier une forme.");
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        System.out.println("Le curseur de la souris est entré dans la zone graphique.");
    }

    @Override
    public void mouseExited(MouseEvent e) {
        System.out.println("Le curseur de la souris est sorti de la zone graphique.");
    }
    
    /**
     * Efface tous les dessins.
     */
    public void effacer()
    {
        if( !this.listeDessins.isEmpty() )
        {
            this.listeDessins.clear();
            this.repaint();
        }
    }
    
    
    /**
     * Efface le dernier dessin.
     */
    public void defaire()
    {
        if( !this.listeDessins.isEmpty() )
        {
            this.listeDessins.removeLast();
            this.repaint();
        }
    }
    
    
    /**
     * Ajoute la forme f dans la liste des dessins à dessiner.
     * @param f 
     */
    public void ajouterForme( Forme f )
    {
        if( f != null)
        {
            this.listeDessins.add(f);
        }
    }
    
    
    /**
     * crée un objet de type Gomme, le dessine dans la zone graphique, l’ajoute dans la liste des formes.
     */
    public void gommer()
    {
        Gomme g = new Gomme(Color.white, this.pFin , 3);
        g.seDessiner(this.getGraphics());
        this.listeDessins.add(g);
    }
    
    
    public void desactiverGomme()
    {
        gommeEnCours = false;
    }
    
    
    public void activerGomme()
    {
        gommeEnCours = true;
    }
    
   
    
    
    
    
    
}
