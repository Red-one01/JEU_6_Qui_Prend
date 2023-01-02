import java.util.Scanner;
import java.util.ArrayList;
import java.util.Collections;
import static util.Console.clearScreen;
import static util.Console.pause;

class Main {
  public static void starting_screen(Partie p) {
    p.lecture_noms();
    System.out.print("Les " + p.getNb_joueurs() + " joueurs sont ");
    for (int i = 0; i < p.getNb_joueurs() - 2; ++i) {
      System.out.print(p.nom_joueur(i) + ", ");
    }
    System.out.print(p.nom_joueur(p.getNb_joueurs() - 2));
    System.out.print(" et " + p.nom_joueur(p.getNb_joueurs() - 1) + ".");
    System.out.println(" Merci de jouer à 6 qui prend !");
  }

  public static void afficher_deck(int joueur, Partie partie) {
    System.out.print("- Vos cartes : ");
    String tmp = "";
    String tmp2 = ", ";
    for (int i = 0; i < partie.get_deck(joueur).Taille_Deck(); i++) {
      tmp = "";
      if (partie.get_deck(joueur).get_Carte(i).getNbTeteDeBoeuf() > 1) {
        tmp = tmp + " (" + partie.get_deck(joueur).get_Carte(i).getNbTeteDeBoeuf() + ")";
      }
      if (i == partie.get_deck(joueur).Taille_Deck() - 1) {
        tmp2 = "";
      }
      System.out.print(partie.get_deck(joueur).get_Carte(i).getNumeroCarte() + tmp + tmp2);
    }
    System.out.println("");
  }

  public static void afficher_series(Partie partie) {
    String tmp = "";
    for (int a = 0; a < Partie.NB_SERIE; a++) {
      String tmp2 = ",";
      System.out.print("- série n° " + (a + 1) + " :");
      for (int b = 0; b < partie.get_serie(a).getTaille(); b++) {
        tmp = "";
        if (partie.get_serie(a).getAnyCarte(b).getNbTeteDeBoeuf() > 1) {
          tmp = tmp + " (" + partie.get_serie(a).getAnyCarte(b).getNbTeteDeBoeuf() + ")";
        }
        if (b == partie.get_serie(a).getTaille() - 1) {
          tmp2 = "";
        }
        
        System.out.print(" " + partie.get_serie(a).getAnyCarte(b).getNumeroCarte() + tmp + tmp2);
      }
      System.out.println("");
    }
  }

  public static void recuperer_choix_joueurs(Partie partie, ArrayList<Carte> carte_choisies_tmp, ArrayList<String> noms_tmp){
    for (int i = 0; i < partie.getNb_joueurs(); i++) {
      System.out.println("A " + partie.nom_joueur(i) + " de jouer.");
      pause();
      afficher_series(partie);
      afficher_deck(i, partie);

      System.out.print("Saisissez votre choix : ");
      boolean ok = false;
      Scanner sc = new Scanner(System.in);
      while (!ok) {
        boolean est_nombre = true;
        if (!sc.hasNextInt()) {

          System.out.print("Vous n'avez pas cette carte, saisissez votre choix :");
          sc.nextLine();
          est_nombre = false;

        }

        if (est_nombre) {
          int choix = sc.nextInt() ;
          if (partie.get_deck(i).est_dans_deck(choix)) {
            noms_tmp.add(partie.get_deck(i).getNomJoueur());
            carte_choisies_tmp.add(partie.get_deck(i).selectioner_carte(choix));
            ok = true;
            break;
          }
          System.out.print("Vous n'avez pas cette carte, saisissez votre choix : ");
          sc.nextLine();
        }
      }
      clearScreen();
    }
    
  }

    public static void tri_carte ( ArrayList<String> noms_tmp, ArrayList<Carte> carte_choisies_tmp){
    
    int cpt = 0;
    Carte stockage_carte;
    String stockage_String;
    
    for(int z = 0 ; z < carte_choisies_tmp.size() ; ++z){
      
      int Carte_plus_petite = 104, indice_carte_plus_petite = 0;
      for (int i = 0; i < carte_choisies_tmp.size() - cpt ;++i){
        if(Carte_plus_petite > carte_choisies_tmp.get(i).getNumeroCarte() ){
          Carte_plus_petite = carte_choisies_tmp.get(i).getNumeroCarte() ;
          indice_carte_plus_petite = i;
        }
      }
      ++cpt;
      stockage_carte = carte_choisies_tmp.get(indice_carte_plus_petite);
      stockage_String = noms_tmp.get(indice_carte_plus_petite);
      
      carte_choisies_tmp.add(stockage_carte);
      noms_tmp.add(stockage_String);

      noms_tmp.remove(indice_carte_plus_petite);
      carte_choisies_tmp.remove(indice_carte_plus_petite);
    }
  }

  public static void tri_nom(Partie partie ,ArrayList<Integer>tab_boeuf, ArrayList<String>tab_noms){
    Collections.sort(tab_noms);
    tab_boeuf.clear() ;
    for(int z = 0; z < partie.getNb_joueurs();++z){
      for (int i = 0 ; i < partie.getNb_joueurs() ;++i){
        if( tab_noms.get(z) == partie.get_deck(i).getNomJoueur()){
          tab_boeuf.add(partie.get_deck(i).get_total_boeuf()) ;
        }
      }
    }

  }

  public static void tri_boeufs(Partie partie, ArrayList<Integer>tab_boeuf, ArrayList<String>tab_noms){
    
    final int NB_BOEUF_MAX = 154;//7+ 4*3 nb max de boeuf ramasser en une partie par joueur

    int cpt = 0,boeuf_min = NB_BOEUF_MAX,stockage = 0;
    String stockage_str = "";
    
    
    for(int z = 0; z < tab_boeuf.size();++z){
      int index_petit = 0;
      boeuf_min = NB_BOEUF_MAX;
      for(int i = 0 ; i < tab_boeuf.size() - cpt ; ++i){
        if(tab_boeuf.get(i) < boeuf_min){
          index_petit = i;
          boeuf_min = tab_boeuf.get(i);
        }
      }
      ++cpt;
      stockage = boeuf_min;
      stockage_str = tab_noms.get(index_petit);
      
      tab_boeuf.remove(index_petit);
      tab_noms.remove(index_petit);
      
      tab_boeuf.add(stockage);
      tab_noms.add(stockage_str);
    }
  }

  public static void poser_carte(Partie partie,
                                 ArrayList<Carte> carte_choisies_tmp,
                                 ArrayList<Integer> tetes_boeuf_ramasser_tmp,
                                ArrayList<String> nom_tmp){
    
    int nombre_de_carte_a_jouer = carte_choisies_tmp.size();
    for (int i = 0; i < nombre_de_carte_a_jouer ; i++){
      if(partie.coup_Normal(carte_choisies_tmp.get(0)) ){
        partie.jouer_une_carte(carte_choisies_tmp.get(0));
        carte_choisies_tmp.remove(0);
        tetes_boeuf_ramasser_tmp.add(0);
      }
      else {
        tetes_boeuf_ramasser_tmp.add(partie.serie_pleine(carte_choisies_tmp.get(0)));
        carte_choisies_tmp.remove(0);
      }
    } 
    for(int z = 0;z < partie.getNb_joueurs();++z){
      partie.ajouter_tete_de_boeuf(nom_tmp.get(z),tetes_boeuf_ramasser_tmp.get(z)); 
    }
  }

  public static void bilan_fin_de_manche(ArrayList<Integer> tetes_boeuf_ramasser_tmp,ArrayList<String>noms_tmp ){
    int compteur = 0;
      for (int i = 0; i < tetes_boeuf_ramasser_tmp.size() ; ++i ){
        if(tetes_boeuf_ramasser_tmp.get(i) > 0){
           System.out.println(noms_tmp.get(i) +" a ramassé " + tetes_boeuf_ramasser_tmp.get(i) + " tête de boeufs");
        }
        else{
          ++compteur;
        }
      }
      if (compteur == tetes_boeuf_ramasser_tmp.size() ){
        System.out.println("Aucun joueur ne ramasse de tête de boeufs.");
      }
  }

  public static boolean poser_carte_trop_petite(Partie partie,
                                               ArrayList<Integer>tetes_boeuf_ramasser_tmp,
                                               ArrayList<String>noms_tmp,
                                               ArrayList<Carte>carte_choisies_tmp){
    if(partie.carte_trop_petite(carte_choisies_tmp.get(0))){
      int serie_a_ramasser = -1;
      System.out.print("Les cartes");
      for (int i = 0; i < partie.getNb_joueurs(); ++i ){
        String tmp = " et";
        if (i != partie.getNb_joueurs() - 2){
          tmp = ",";
        }
        if(i == partie.getNb_joueurs() - 1) {
        	tmp = "";
        }
        System.out.print( " " + carte_choisies_tmp.get(i).getNumeroCarte()+" " +"(" + noms_tmp.get(i) + ")" + tmp );
      }
      System.out.println(" vont être posées.");
      System.out.println("Pour poser la carte "+carte_choisies_tmp.get(0).getNumeroCarte()+
                         ", "+noms_tmp.get(0) +" doit choisir la série qu'il va ramasser." );
      afficher_series(partie);
      System.out.print("Saisissez votre choix : ");
      Scanner sc = new Scanner(System.in);
      
      boolean ok = false;
      while (!ok) {
        boolean est_nombre = true;
        if (!sc.hasNextInt()) {

          System.out.print("Ce n'est pas une série valide, saisissez votre choix : ");
          sc.nextLine();
          est_nombre = false;
        }

        if (est_nombre) {
          int choix = sc.nextInt() ;
          if (choix >= 1 && choix <= 4) {
            serie_a_ramasser = choix -1;
            break;
          }
          System.out.print("Ce n'est pas une série valide, saisissez votre choix : ");
          sc.nextLine();
        }
      }
      bilan_debut_manche( partie, carte_choisies_tmp, noms_tmp);
      
      tetes_boeuf_ramasser_tmp.add(partie.get_serie(serie_a_ramasser).SeriePleine(carte_choisies_tmp.get(0)));
      carte_choisies_tmp.remove(0);
      poser_carte(partie, carte_choisies_tmp, tetes_boeuf_ramasser_tmp,noms_tmp);
      tri_boeufs(partie,tetes_boeuf_ramasser_tmp, noms_tmp );
      afficher_series(partie);
      bilan_fin_de_manche(tetes_boeuf_ramasser_tmp, noms_tmp);
      return true;
    }
    return false;
  }
  
  public static void bilan_debut_manche(Partie partie,ArrayList<Carte> carte_choisies_tmp,ArrayList<String> noms_tmp){
    System.out.print("Les cartes");
    for (int i = 0; i < partie.getNb_joueurs(); ++i ){
      String tmp = " et";
      if (i != partie.getNb_joueurs() - 2){
        tmp = ",";
      }
      if(i == partie.getNb_joueurs() - 1) {
      	tmp = "";
      }
      System.out.print( " " + carte_choisies_tmp.get(i).getNumeroCarte()+" " +"(" +noms_tmp.get(i) + ")" + tmp );
    }
    System.out.println(" ont été posées.");
  }
   public static void affichage_final(Partie partie, 
                                      ArrayList<Carte>carte_choisies_tmp, 
                                      ArrayList<Integer>tetes_boeuf_ramasser_tmp, 
                                      ArrayList<String> noms_tmp){
      System.out.println("** Score final");
      for (int i = 0; i < partie.getNb_joueurs(); i++){
        tetes_boeuf_ramasser_tmp.add(partie.get_deck(i).get_total_boeuf());
        noms_tmp.add(partie.get_deck(i).getNomJoueur());
      }

      tri_nom(partie,tetes_boeuf_ramasser_tmp,noms_tmp);
      tri_boeufs(partie,tetes_boeuf_ramasser_tmp,noms_tmp);
      for(int k = 0;k < partie.getNb_joueurs();++k){
        System.out.println( noms_tmp.get(k)+" a ramassé " + tetes_boeuf_ramasser_tmp.get(k)+ " têtes de boeufs");
      }
   }

  public static void main(String[] args) {
    ArrayList<String> noms_tmp = new ArrayList<String>();
    ArrayList<Carte> carte_choisies_tmp = new ArrayList<Carte>();
    ArrayList<Integer> tetes_boeuf_ramasser_tmp = new ArrayList<Integer>();
    Pioche.creation_pioche();
    Partie partie = new Partie();
    starting_screen(partie);

    for (int i = 0; i < Partie.NB_MANCHE ; ++i){
      recuperer_choix_joueurs(partie,carte_choisies_tmp,noms_tmp);
      tri_carte(noms_tmp,carte_choisies_tmp);              
      if(poser_carte_trop_petite(partie,tetes_boeuf_ramasser_tmp,noms_tmp,carte_choisies_tmp)){}
      else{
        bilan_debut_manche(partie, carte_choisies_tmp, noms_tmp);
        poser_carte(partie,carte_choisies_tmp,tetes_boeuf_ramasser_tmp, noms_tmp);
        afficher_series(partie);
        bilan_fin_de_manche(tetes_boeuf_ramasser_tmp, noms_tmp);
      }
      tetes_boeuf_ramasser_tmp.clear();
      noms_tmp.clear();
    }
    
    affichage_final(partie, carte_choisies_tmp,tetes_boeuf_ramasser_tmp,noms_tmp);
      
  }
}