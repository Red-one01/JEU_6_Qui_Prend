import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Random;

/**
 * Petit programme montrant comment une liste (ArrayList ou LinkedList) et un
 * tableau peut �tre tri� ou m�lang�.
 */
public class TriEtMelange {
	private static final int TAILLE = 10;
	private static Random rd = new Random();

	public static void main(String[] args) {
		// listes (ArrayList ou LinkedList)
		System.out.println("-------- Liste");
		ArrayList<Integer> liste = new ArrayList<>();
		for (int i = 0; i < TAILLE; ++i)
			liste.add(rd.nextInt(TAILLE * TAILLE));
		System.out.println("avant le tri     : " + liste);
		
		// tri
		Collections.sort(liste);
		System.out.println("apr�s le tri     : " + liste);
		
		// m�lange
		Collections.shuffle(liste);
		System.out.println("apr�s le m�lange : " + liste);

		// tableaux
		System.out.println("-------- Tableau");
		int[] tableau = new int[TAILLE];
		for (int i = 0; i < TAILLE; ++i)
			tableau[i] = rd.nextInt(TAILLE * TAILLE);
		System.out.println("avant le tri     : " + Arrays.toString(tableau));
		
		// tri
		Arrays.sort(tableau);
		System.out.println("apr�s le tri     : " + Arrays.toString(tableau));
		
		// m�lange
		ArrayList<Integer> tmp = new ArrayList<>();
		for (int v : tableau)
			tmp.add(v);
		Collections.shuffle(tmp);
		for (int i = 0; i < TAILLE; ++i)
			tableau[i] = tmp.get(i);
		System.out.println("apr�s le m�lange : " + Arrays.toString(tableau));
	}
}
