import java.util.ArrayList;
import java.util.Collections;

 public class Pioche {

  private static ArrayList<Carte> paquet = new ArrayList<Carte>();
   
  static public void creation_pioche() {
    for (int i = 0; i < 104; ++i) {
      paquet.add(new Carte());
    }
    Collections.shuffle(paquet);
  }
  
  static public int Taille_Paquet(){
    return paquet.size();
  }

  static public Carte Carte_Piochee(){
    assert(paquet.size() > 0);

    Carte CarteTmp = paquet.get(0);
    paquet.remove(0); 
    return CarteTmp;
  }
}