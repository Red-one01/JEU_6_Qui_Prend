
import java.util.ArrayList;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Partie {

  private int nb_joueurs = 0;
  public final static int NB_SERIE = 4;
  public final static int NB_MANCHE = 10;
  private ArrayList<Deck> decks_joueur = new ArrayList<Deck>();
  private ArrayList<Serie> series = new ArrayList<Serie>();
  
  public Partie(){
    for (int i = 0; i < NB_SERIE; i++){
     series.add(new Serie()); 
    }
    
  }

  public boolean coup_Normal(Carte choix) {
    int index = this.Carte_proche(choix,"pleine");
    if (choix.getNumeroCarte() > series.get(index).getDerniere_carte().getNumeroCarte()) {
      if (series.get(index).getTaille() < 5) {
        return true;
      }
    }
    return false; 
  }

  public boolean carte_trop_petite(Carte choix){
 
      for(int b = 0; b < NB_SERIE; ++b){
        if (choix.getNumeroCarte() > series.get(b).getDerniere_carte().getNumeroCarte()){
          return false;
        }
      }
    return true;
  }

  public int Carte_proche(Carte choix, String option){
    if(option == "normal"){
      final int ECART_MAX = 103;//ecart maximum possible donc la carte 104 - la carte 1
      int ecart = ECART_MAX;
      int index = -1;//pour etre sur que cela creera une erreur si les conditions ne sont pas remplies
      for (int i = 0; i < NB_SERIE; ++i){
        if(choix.getNumeroCarte() > series.get(i).getDerniere_carte().getNumeroCarte()){
          if(choix.getNumeroCarte() - series.get(i).getDerniere_carte().getNumeroCarte() < ecart){
            ecart = choix.getNumeroCarte() - series.get(i).getDerniere_carte().getNumeroCarte();
            index = i;
          }
        }
      }
      return index;
    }
    else if(option == "pleine"){
      final int ECART_MAX = 103;//ecart maximum possible donc la carte 104 - la carte 1
      int ecart = ECART_MAX;
      int index = -1;//pour etre sur que cela creera une erreur si les conditions ne sont pas remplies
      for (int i = 0; i < NB_SERIE; ++i){
        if(choix.getNumeroCarte() > series.get(i).getDerniere_carte().getNumeroCarte()){
          if(choix.getNumeroCarte() - series.get(i).getDerniere_carte().getNumeroCarte() < ecart){

              ecart = choix.getNumeroCarte() - series.get(i).getDerniere_carte().getNumeroCarte();
              index = i;
            
          }
        }
      }
      return index;
    }
    return -1;//en cas de mauvais deroulement
  }

  public int serie_pleine(Carte choix){
    int nb_boeuf = 0;
    if (!coup_Normal(choix) && !carte_trop_petite(choix)){
      int index = Carte_proche(choix,"pleine");
      if(index != -1){
        nb_boeuf = series.get(index).SeriePleine(choix);
      } 
    }
    return nb_boeuf;
  }

  public void jouer_une_carte(Carte choix){
    if (coup_Normal(choix)){
      series.get(Carte_proche(choix,"normal")).ajouter(choix);
    }
    else if(!coup_Normal(choix)){

      serie_pleine(choix);
    }
  }

  public void lecture_noms(){
    final String NOM_FICHIER = "config.txt";
    int cpt = 0;
    try {
      Scanner in = new Scanner(new FileInputStream(NOM_FICHIER));
      while (in.hasNextLine())  {
    	decks_joueur.add(new Deck());
        decks_joueur.get(cpt).save_Prenom(in.nextLine()) ;
        ++cpt; 
      }
    }
    catch(FileNotFoundException e) {
      System.err.println("Fichier inconnu : "+ NOM_FICHIER);
    }
    nb_joueurs = cpt;

  }

  public void ajouter_tete_de_boeuf (String prenom, int tete_boeuf) {
    for (int i = 0 ; i < this.nb_joueurs ;++i){
      if(decks_joueur.get(i).getNomJoueur() == prenom){
        decks_joueur.get(i).add_boeuf(tete_boeuf);
      }
    }
  }


  public int getNb_joueurs(){
    return this.nb_joueurs;
  }

  public String nom_joueur(int indice){
    return this.decks_joueur.get(indice).getNomJoueur();
  }

  public Serie get_serie(int indice){
    return this.series.get(indice);
  }

  public Deck get_deck(int indice){
    return this.decks_joueur.get(indice);
  }
 
}
