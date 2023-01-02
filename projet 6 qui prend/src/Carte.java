// Nombre de cartes: 104
//Cas Particuliers � 5 t�tes de boeuf: 11;22;33;44;66;77;88;99
//Cas Particuliers � 2 t�tes de boeuf: 5;15;25;35;45;65;75;85;95
//Cas Particuliers � 3 t�tes de boeuf: 10;20;30;40;50;60;70;80;90;100
//Cas Particulier � 7 t�tes de boeuf: 55

public class Carte {
  private int NbTete_de_boeuf = 1;
  private int Numero_Carte = 0;
  private static int compteur = 0;

  public Carte(){
    assert(compteur >104);
    ++ compteur;
    Numero_Carte = compteur;


    //boeuf
    if(compteur % 10 == 0 ){
      NbTete_de_boeuf = 3;
    }
    
    else if(compteur % 5 == 0 ){
      NbTete_de_boeuf = 2;
    }

    else if(compteur % 11 == 0){
      NbTete_de_boeuf = 5;
    }
    
    else{
      NbTete_de_boeuf = 1;
    }
    
    if (compteur == 55){
      NbTete_de_boeuf = 7;
    }

  }

  public int getNumeroCarte(){
    return this.Numero_Carte;
  }
  public int getNbTeteDeBoeuf(){
    return this.NbTete_de_boeuf;
  }
  

//Scanner h = new Scanner(System.in);
//int j = h.nextInt();

}