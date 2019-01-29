/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package porteconteneurs;

import java.awt.Color;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Iterator;
import modele.Cercle;
import modele.Disque;
import modele.Forme;
import modele.Point;

/**
 *
 * @author user
 */
public class Empilement {
    
    private Deque<Conteneur> pileConteneurs;
    
    /**
     * Centre de l'empilement
     */
    private Point pCentre;
    
    
    private int nbMaxConteneurs;
    
    /**
     * Constructeur par données
     * @param nbMaxC représente le nombre maximal de conteneurs qui peuvent être mis sur la pile.
     * @param i représente la ligne de la grille sur laquelle est situé l’empilement.
     * @param j représente la colonne de la grille sur laquelle est situé l’empilement.
     */
    Empilement(int nbMaxC, int i, int j)
    {
        if( nbMaxC < 0)
            nbMaxC = 0;
        
        pCentre = new Point(100*j+50,100*i+50);
        nbMaxConteneurs = nbMaxC;
        pileConteneurs = new ArrayDeque<>(nbMaxC);
        
    }
    
    public int getAbscisseCentre()
    {
        return pCentre.getX();
    }
    
    
    public int getOrdonneeCentre()
    {
        return pCentre.getY();
    }
    
    /**
     * 
     * @return true si la pile de conteneurs ne peut plus accepter d'éléments, false sinon.
     */
    public boolean estPlein()
    {
        return (this.pileConteneurs.size() >= this.nbMaxConteneurs);
    }
    
    
    /**
     * ajoute le conteneur c sur le haut de la pile si cela est possible, renvoie un booléen indiquant si l’ajout a pu être effectué ou non ;
     * @param c le conteneur à ajouter
     * @return true si l'ajout a été effectué, false sinon.
     */
    public boolean ajouterConteneur(Conteneur c)
    {
        if( c != null && !this.estPlein())
        {
            pileConteneurs.push(c);
            return true;
        }
        else
            return false;
    }
    
    
    /** 
     * @return le poids total de la pile de conteneurs
     */
    public int poids()
    {
        int poidsTotal = 0;
        Iterator <Conteneur> itConteneur = pileConteneurs.iterator();
        while( itConteneur.hasNext() )
        {
            Conteneur c = itConteneur.next();
            poidsTotal += c.getPoids();
        }
        
        return poidsTotal;
    }
    
    
    /**
     * transfère le conteneur situé en haut de l’empilement courant dans l’empilement e si cela est possible.
     * cette méthode renvoie un booléen indiquant si le transfert a été effectué ou non.
     * @param e l'empilement de destination
     * @return true si le transfert a été effectué, false sinon.
     */
    public boolean deplacerConteneurVers(Empilement e)
    {
        if( e != null 
                && ( !e.estPlein() ) 
                && ( !pileConteneurs.isEmpty() )
                )
        {
            e.pileConteneurs.push(  pileConteneurs.pop() );           
            return true;
        }
        else
            return false;
    }
    
    
    /**
     * renvoie :
     * si le poids de l’empilement est strictement positif : 
     *      un disque de couleur noire dont le centre est celui de l’empilement et le rayon est égal au poids de l’empilement ;
     * 
     * si le poids de l’empilement est nul : 
     *      un cercle de couleur noire dont le centre est celui de l’empilement et le rayon est égal à 10.
     */
    public Forme getDessin()
    {
        int rayon = poids();
        if( rayon == 0)
        {
            return new Cercle(pCentre, 10, Color.black);
        }
        else
            return new Disque(pCentre, rayon, Color.black);
    }
    
    
    
    public static void main(String[] args) {
        Conteneur c1 = new Conteneur("IG2I", 10);
        Conteneur c2 = new Conteneur("IG2I", 10);
        Conteneur c3 = new Conteneur("IG2I", 15);
        Conteneur c4 = new Conteneur("IG2I", 20);
        Conteneur c5 = new Conteneur("IG2I", 20);
        Empilement e1 = new Empilement(3, 0, 0);
        Empilement e2 = new Empilement(3, 1, 0);
        if (! e1.ajouterConteneur(null))
             System.out.println("null pas ajoute a e1");
        if (! e1.ajouterConteneur(c1))
             System.out.println("c1 pas ajoute a e1");
        if (! e1.ajouterConteneur(c2))
             System.out.println("c2 pas ajoute a e1");
        if (! e1.ajouterConteneur(c3))
             System.out.println("c3 pas ajoute a e1");
        if (! e1.ajouterConteneur(c4))
             System.out.println("c4 pas ajoute a e1");
        System.out.println("poids e1 : "+e1.poids());
        if (! e2.deplacerConteneurVers(e1))
             System.out.println("e2 -> e1 imp.");
        if (! e2.ajouterConteneur(c4))
             System.out.println("c4 pas ajoute a e2");
        if (! e2.ajouterConteneur(c5))
             System.out.println("c5 pas ajoute a e2");
        if (! e1.deplacerConteneurVers(e2))
             System.out.println("e1 -> e2 imp.");
        if (! e1.deplacerConteneurVers(e2))
             System.out.println("e1 -> e2 imp.");
        System.out.println("poids e1 : "+e1.poids());
        System.out.println("poids e2 : "+e2.poids());
    }
}
