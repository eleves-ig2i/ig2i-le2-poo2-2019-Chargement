/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package porteconteneurs;

import java.awt.Color;
import java.util.ArrayList;
import java.util.Collection;
import modele.Disque;
import modele.Forme;
import modele.Point;
import vuecontrole.Fenetre;

/**
 *
 * @author user
 */
public class PorteConteneurs {
    
    private int nbMaxLigne;
    
    private int nbMaxColonne;    
    
    private int nbMaxConteneurs;
    
    private Empilement [][] matriceEmpilements;
    
    
    /**
     * Constructeur par données.
     * Les attributs associés à n, m et nbMaxC vaudront respectivement (au minimum) 1.
     * @param n le nombre de lignes de la matrice qui caractérise le porte-conteneurs.
     * @param m le nombre de colonnes de la matrice qui caractérise le porte-conteneurs.
     * @param nbMaxC représente le nombre maximal de conteneurs qui peuvent être mis sur chaque pile de la matrice.
     */
    public PorteConteneurs(int n, int m, int nbMaxC)
    {
        if( n <= 0)
            n = 1;
        
        if( m <= 0)
            m = 1;
        
        if( nbMaxC <= 0)
            nbMaxC = 1;
        
        nbMaxLigne = n;
        nbMaxColonne = m;
        nbMaxConteneurs = nbMaxC;
        matriceEmpilements = new Empilement[n][m];
    }
    
    
    /**
     * 
     * @param i la ligne
     * @param j la colonne
     * @return true si les coordonnées (i,j) sont valide.
     */
    public boolean coordonneesValides(int i, int j)
    {
        return ( ( 0 <= j ) && ( j < nbMaxColonne ) && ( 0 <= i ) && ( i < nbMaxLigne) );
    }
    
    
    /**
     * Initialise l'empilement à la ligne i et à la colonne j, à l'aide du constructeur par données.
     * @param i la ligne
     * @param j la colonne
     * @return true si les coordonnées i et j sont valides, false sinon.
     */
    private boolean initialiserEmplacement(int i, int j)
    {
        if( coordonneesValides(i, j) )
        {
            matriceEmpilements[i][j] = new Empilement(this.nbMaxConteneurs,i,j);
            return true;
        }
        else
        {
            return false;
        }
    }
    
    
    /**
     * permettant de charger le conteneur c sur l’empilement situé à la ligne i et la colonne j si cela est possible. 
     * En cas de chargement impossible, la méthode affiche dans la console : "chargement impossible". 
     * Si les coordonnées passées en paramètre ne sont pas correctes, la méthode affiche dans la console : "coordonnées non valides".
     * @param c le conteneur à ajouter
     * @param i la ligne de la matrice d'empilements, compris entre 0 et nbMaxLignes - 1
     * @param j la colonne de la matrice d'empilements, compris entre 0 et nbMaxColonnes - 1
     */
    public void charger(Conteneur c, int i, int j)
    {
        if( ! coordonneesValides(i, j) )
        {
            System.out.println("Coordonnées non valides.");
        }
        else if( c == null)
        {
            System.out.println("Chargement impossible.");
        }
        else 
        {
            if( matriceEmpilements[i][j] == null )
                initialiserEmplacement(i, j);

            if( !matriceEmpilements[i][j].ajouterConteneur(c) )
                System.out.println("Chargement impossible");
        }
    }
    
    
    /**
     * 
     * @return le poids total de tous les empilements
     */
    public int poidsTotal()
    {
        int poidsTotalMatrice = 0;
        for(int i =0; i < nbMaxLigne; i++)
        {
            for(int j =0; j < nbMaxColonne; j++)
            {
                if( matriceEmpilements[i][j] != null)
                {
                    poidsTotalMatrice += matriceEmpilements[i][j].poids();
                }
            }
        }
        return poidsTotalMatrice;
    }
    
    
    /**
     * @return le centre de gravité de la grille (la matrice)d’empilements.
     */
    public Point centreGravite()
    {
        int sommeProduitAbscissePoids = 0;
        int sommeProduitOrdonneePoids = 0;
        
        for(int i =0; i < nbMaxLigne; i++)
        {
            for(int j = 0; j < nbMaxColonne; j++)
            {
                Empilement eActuel = matriceEmpilements[i][j];
                if( eActuel != null)
                {
                    sommeProduitAbscissePoids += eActuel.poids()*eActuel.getAbscisseCentre();
                    sommeProduitOrdonneePoids += eActuel.poids()*eActuel.getOrdonneeCentre();
                }
            }
        }
        int poidsTotal = poidsTotal();
        if( poidsTotal == 0)
            return this.getCDGIdeal();
        else
            return new Point( sommeProduitAbscissePoids/poidsTotal, sommeProduitOrdonneePoids/poidsTotal  );
    }
    
    /**
     * @return le centre de gravité idéal du chargement.
     */
    private Point getCDGIdeal()
    {
        return new Point( (nbMaxColonne*100)/2, (nbMaxLigne*100)/2 );
    }
    
    
    /**
     * @return la distance euclidienne entre le centre de gravité correspondant à 
     * un équilibrage parfait de la grille et son centre de gravité réel (correspondant au chargement courant)
     */
    public double qualiteDuChargement()
    {
        Point pCDGIdeal = getCDGIdeal();
        return pCDGIdeal.getDistance( centreGravite() );
    }
    
    
    /**
     * 
     * @return le nombre d'empilements dans la grille
     */
    public int getNombreEmpilements()
    {
        int nbEmpilements = 0;
        for(int i =0; i < nbMaxLigne; i++)
        {
            for(int j = 0; j < nbMaxColonne; j++)
            {
                if( matriceEmpilements[i][j] != null )
                    nbEmpilements++;
            }
        }
        
        return nbEmpilements;
    }
    
    public Collection<Forme> getDessin()
    {
        // On sait qu'on aura un cercle pour chaque case de la grile.
        ArrayList<Forme> listeDessins = new ArrayList<>( nbMaxColonne*nbMaxLigne + 2);
        for(int i =0; i < nbMaxLigne; i++)
        {
            for(int j = 0; j < nbMaxColonne; j++)
            {
                Empilement eActuel = matriceEmpilements[i][j]; 
                if( eActuel != null )
                {
                    listeDessins.add( eActuel.getDessin() );
                }
                else
                {
                    Empilement eImaginaire = new Empilement(0, i, j);
                    listeDessins.add(eImaginaire.getDessin());
                }
            }
        }
        
        listeDessins.add( new Disque(centreGravite(),20,Color.red) );   // centre de gravité du chargement
        listeDessins.add( new Disque(getCDGIdeal(),10,Color.blue));     // centre de gravité idéal
        
        return listeDessins;
    }
    
    
    /**
     * Cette méthode ne modifie pas le porte conteneurs
     * @param p1 L'empilement de départ.
     * @param p2 L'empilement de destination.
     * @return la qualité du chargement si on transférait le conteneur situé en haut de l’empilement p1 dans p2 ou infini si le transfert est impossible.
     */
    public double evaluerQualiteDeplacement(Empilement p1, Empilement p2)
    {  
        double qualiteChargement = 0;
        
        if( !p1.deplacerConteneurVers(p2) )
        {
            return Double.POSITIVE_INFINITY;
        }
        qualiteChargement = this.qualiteDuChargement();
        p2.deplacerConteneurVers(p1);
        return qualiteChargement;
    }
    
    public static void main(String[] args) {
        // Q5
        /*
        PorteConteneurs p1 = new PorteConteneurs(2, 3, 1);
        Conteneur c1 = new Conteneur("NATHAN", 20);
        Conteneur c2 = new Conteneur("CLEMENT", 20);
        
        p1.charger(null, 0, 0); // chargement impossible
        p1.charger(c1, 2, 2);   // coordonnées non valides
        p1.charger(c1, 1, 2);   // CORRECT
        System.out.println("Chargement effectué");
        p1.charger(c2, 1, 2);   // chargement impossible
        */
        
        
        // Q6
        /*
        PorteConteneurs p1 = new PorteConteneurs(2, 3, 3);
        p1.charger( new Conteneur("NATHAN",20), 1, 0);
        p1.charger( new Conteneur("NATHAN",10), 1, 0);
        p1.charger( new Conteneur("NATHAN",15), 1, 0);
        
        p1.charger( new Conteneur("NATHAN",10), 1, 2);
        p1.charger( new Conteneur("NATHAN",30), 1, 2);
        
        System.out.println(p1.poidsTotal());
        */
        
        
        // Q7 - 13
        /*
        PorteConteneurs pcont = new PorteConteneurs(4,5,2);
        pcont.charger(new Conteneur("IG2I",10), 0, 0);
        pcont.charger(new Conteneur("IG2I",10), 0, 0);
        pcont.charger(new Conteneur("IG2I",15), 0, 3);
        pcont.charger(new Conteneur("IG2I",15), 0, 3);
        pcont.charger(new Conteneur("IG2I",15), 3, 0);
        pcont.charger(new Conteneur("IG2I",10), 3, 0);
        pcont.charger(new Conteneur("IG2I",10), 2, 1);
        System.out.println("Centre de gravite : "+pcont.centreGravite());
        System.out.println("Qualite chargement :"+pcont.qualiteDuChargement());
        System.out.println("Nombre d'empilements :" +pcont.getNombreEmpilements());
        Fenetre f = new Fenetre();
        f.dessinerPorteConteneurs(pcont);
        */
        
        
        // Q14
        /*
        PorteConteneurs pcont = new PorteConteneurs(4,5,2);
        pcont.charger(new Conteneur("IG2I",10), 0, 0);
        pcont.charger(new Conteneur("IG2I",10), 0, 0);
        pcont.charger(new Conteneur("IG2I",15), 0, 3);
        pcont.charger(new Conteneur("IG2I",15), 0, 3);
        pcont.charger(new Conteneur("IG2I",15), 3, 0);
        pcont.charger(new Conteneur("IG2I",10), 3, 0);
        pcont.charger(new Conteneur("IG2I",10), 2, 1);
        System.out.println("Centre de gravite : "+pcont.centreGravite());
        System.out.println("Qualite chargement :"+pcont.qualiteDuChargement());
        pcont.initialiserEmplacement(3, 3);
        System.out.println("Evaluation qualité chargement : " + pcont.evaluerQualiteDeplacement(pcont.matriceEmpilements[0][0], pcont.matriceEmpilements[3][3]));
        System.out.println("Centre de gravite : "+pcont.centreGravite());
        System.out.println("Qualite chargement :"+pcont.qualiteDuChargement());
        */
        
        
        
    }
}
