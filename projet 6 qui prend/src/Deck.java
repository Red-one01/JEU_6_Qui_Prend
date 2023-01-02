import java.util.ArrayList;



public class Deck {
  private String Nom_Joueur;
  private int Nb_Tete_de_boeuf = 0;
  private ArrayList<Carte> deck_du_Joueur = new ArrayList<Carte>();
  
  public Deck(){
    assert(deck_du_Joueur.size() == 0);
    
    for(int i = 0; i < 10;++i){
      deck_du_Joueur.add(Pioche.Carte_Piochee());
    }
  }

  public int get_total_boeuf(){
    return this.Nb_Tete_de_boeuf;
  }

  public String getNomJoueur(){
    return Nom_Joueur;
  }
  

  public int Taille_Deck(){
    return this.deck_du_Joueur.size();
  }

  public void save_Prenom(String nom){
     this.Nom_Joueur = nom;
  }

  public boolean est_dans_deck(int choix){
    assert(deck_du_Joueur.size() > 1);


    for(int z = 0; z< deck_du_Joueur.size(); ++z){
      if(this.deck_du_Joueur.get(z).getNumeroCarte() == choix){
        return true;
      }
    }
    return false;
  }

  public Carte get_Carte(int index){

    return this.deck_du_Joueur.get(index);
  }

  public Carte selectioner_carte(int numero){
    Carte tmp;
    for(int z = 0; z< deck_du_Joueur.size(); ++z){
      if(this.deck_du_Joueur.get(z).getNumeroCarte() == numero){
        tmp = this.deck_du_Joueur.get(z);
        this.deck_du_Joueur.remove(z);
        return tmp;
      }
    }
    return this.deck_du_Joueur.get(-1);
    //car il faut return qlq chose et pour etre sur que le programme plante en cas d'anomalie 
  }

  public void add_boeuf(int nb){
    Nb_Tete_de_boeuf += nb;
  }
}