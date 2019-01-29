/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package porteconteneurs;

/**
 *
 * @author user
 */
public class Conteneur {
    
    
    private int id;
    
    private static int lastId = 0;
    
    private String nomClient;
    
    /**
     * Poids en kilos (positif)
     */
    private int poids;

    
    /**
     * Constructeur par défaut
     */
    public Conteneur() {
        id = lastId++;
        nomClient = "TEST";
        poids = 0;
    }

    /**
     * Constructeur par données. 
     * Si nomClient vaut null, alors l'attribut associé vaudra "TEST".
     * Si poids négatif, alors l'attribut associé vaudra 0.
     * @param nomClient
     * @param poids 
     */
    public Conteneur(String nomClient, int poids) {
        this();
        if(nomClient != null )
            this.nomClient = nomClient;
        
        if( poids > 0)
            this.poids = poids;
    }

    /**
     * Constructeur par copie.
     * Si c vaut null, le conteneur est initialisé avec le constructeur par défaut.
     * @param c le conteneur à copier
     */
    public Conteneur(Conteneur c) {
        this();
        if( c!= null)
        {
            this.id = c.id;
            this.nomClient = c.nomClient;
            this.poids = c.poids;
        }
    }

    public int getPoids() {
        return poids;
    }

    @Override
    public String toString() {
        return "Conteneur{" + "id=" + id + ", nomClient=" + nomClient + ", poids=" + poids + '}';
    }
    
    
    public static void main(String[] args) {
        Conteneur c1 = new Conteneur();
        Conteneur c2 = new Conteneur("IG2I", 400);
        Conteneur c3 = new Conteneur(null, 200);
        Conteneur c4 = new Conteneur("IG2I", -20);
        Conteneur c5 = new Conteneur(c4);
        Conteneur c6 = new Conteneur(null);
        System.out.println(c1);
        System.out.println(c2);
        System.out.println(c3);
        System.out.println(c4);
        System.out.println(c5);
        System.out.println(c6);
    }
    
    
    
    
    
    
    
    
    
    
    
    
}
