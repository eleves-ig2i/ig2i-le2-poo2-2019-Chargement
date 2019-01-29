/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vuecontrole;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import modele.EnumCouleur;
import javax.swing.JComboBox;
import javax.swing.JPanel;
import modele.EnumForme;

/**
 *
 * @author user
 */
public class BarreHaute extends JPanel implements ActionListener {
    
    private Fenetre fenetre;
    
    private JComboBox couleurs;
    private JComboBox formes;
    
    private JButton effacer;   
    private JButton defaire;
    
    private JButton gomme;
   
    
    // Classe interne
    class EcouteurEffacer implements ActionListener
    {
        @Override
        public void actionPerformed(ActionEvent e) {
            System.out.println("Bouton appuyé : effacer");
            BarreHaute.this.fenetre.effacer();
        }
        
    }
    
    /**
     * Constructeur par données de la classe BarreHaute
     * Si fenetre vaut null, alors on appelle le constructeur par défaut.
     * @param fenetre 
     */
    public BarreHaute(Fenetre fenetre)
    {
        couleurs = new JComboBox( EnumCouleur.values() );
        formes = new JComboBox ( EnumForme.values());   
        effacer = new JButton("Effacer");
        defaire = new JButton("Defaire le dernier dessin");
        gomme = new JButton("Activer la gomme");
        EcouteurEffacer ecouteurE = new EcouteurEffacer();
        
        // Classe anonyme
        ActionListener ecouteurG = new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e) {
                if( gomme.getText() == "Activer la gomme")
                {
                    gomme.setText("Desactiver la gomme");
                    fenetre.activerGomme();
                }
                else
                {
                    gomme.setText("Activer la gomme");
                    fenetre.desactiverGomme();
                }
            }
        };
       
        // Classe interne locale
        class EcouteurDefaire implements ActionListener {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("Bouton appuyé : defaire");
                fenetre.defaire();
            }
        }        
        this.fenetre = fenetre;
        
        EcouteurDefaire ecouteurD = new EcouteurDefaire();
        
        couleurs.addActionListener(this);
        formes.addActionListener(this);
        effacer.addActionListener(ecouteurE);
        defaire.addActionListener(ecouteurD);
        gomme.addActionListener(ecouteurG);
        
        this.add(couleurs);
        this.add(formes);      
        this.add(effacer);
        this.add(defaire);
        this.add(gomme);
    }

  
    @Override
    public void actionPerformed(ActionEvent e) {
        //System.out.println("Action effectuée sur : " + e.getSource());    // OK !
        // OK !
        /*
        JComboBox x = (JComboBox)e.getSource();
        System.out.println("Item choisi : " + x.getSelectedItem()); 
        */
        
        if( e.getSource().equals(effacer))
        {
            System.out.println("L'utilisateur veut effacer la zone graphique.");
        }
        else if( e.getSource().equals(defaire) )
        {
            System.out.println("L'utilisateur veut defaire le dernier dessin réalisé.");
        }
    }

    
    
    
    public EnumCouleur getCouleurSelectionnee()
    {
       return (EnumCouleur)this.couleurs.getSelectedItem();        
    }
    
    
    public EnumForme getFormeSelectionnee()
    {
       return (EnumForme)this.formes.getSelectedItem();        
    }
    
    
    public static void main(String[] args) {
        
        // Q14
        /*
        JFrame fenetre = new JFrame();
        BarreHaute barre = new BarreHaute();
        fenetre.add(barre);
        fenetre.setSize(400,400);
        fenetre.setLocationRelativeTo(null);  // fenêtre au milieu de l'écran
        fenetre.setDefaultCloseOperation(EXIT_ON_CLOSE);
        fenetre.setVisible(true);
        */
       
    }
}
