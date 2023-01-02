import java.util.ArrayList;

public class Serie {

  private ArrayList<Carte> serie = new ArrayList<Carte>();

  public Serie(){
    this.ajouter(Pioche.Carte_Piochee());
  }

  public void ajouter(Carte a) {
    assert(a.getNumeroCarte() > this.getDerniere_carte().getNumeroCarte());
    assert(this.serie.size() < 6);

    serie.add(a);
  }

  public boolean estVide() {
    return this.serie.size() == 0;
  }

  public int getTaille() {
    return this.serie.size();
  }

  public int SeriePleine(Carte choix) {

    int compteur_tete_de_boeuf = 0;
    int taille = this.serie.size();
    for (int i = 0; i < taille; ++i) {
      assert (!this.estVide());
      compteur_tete_de_boeuf += this.serie.get(0).getNbTeteDeBoeuf();
      serie.remove(0);
    }
    this.ajouter(choix);
    return compteur_tete_de_boeuf;
  }

  public Carte getDerniere_carte(){
    return this.serie.get(serie.size() -1);
  }

  public Carte getAnyCarte(int index){
    assert(index < this.serie.size());
    return this.serie.get(index);
  }

}