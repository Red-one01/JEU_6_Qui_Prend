Le fichier 6QP-trace-analyseur.jar est une application java vérifiant qu'une trace d'exécution du jeu "6 qui prend !" respecte bien le format imposé du sujet.

Le programme fait l'hypothèse qu'il n'y a aucune erreur de saisie des joueurs. De plus, seule la syntaxe est vérifiée et aucune vérification logique n'est réalisée.

Usage :

- Lancer une invite de commande (cmd),
- se déplacer dans le dossier conteant le fichier 6QP-trace-analyseur.jar (commande cd),
- y placer un fichier texte contenant une trace d'exécution (nommé trace.txt dans la commande qui suit),
- taper la commande suivante (à adapter selon le nom du fichier contenant la trace d'exécution) :

java -jar 6QP-trace-analyseur.jar < trace.txt

Le programme affiche soit un message de succès :

"483 ligne(s) analysée(s) avec succès"

soit un message décrivant la première erreur rencontrée (numéro de ligne de l'erreur, commentaire, contenu incriminé) :

"ligne 26 : Le format de présentation des cartes du joueur n'est pas correct.
- Vos cartes : 9, 13, 18, 25 (2), 36, 37, 49, 54, 85 [2], 96"

Ici des crochets ont été employés là où des parenthèses sont attendues.

Bien entendu, il faut préalablement avoir constitué une trace d'exécution. 

Jouer une partie prend du temps. Le faire sans aucune erreur de saisie est périeux (mais rien ne vous empêche de corriger a posteriori). Copier/coller le contenu de la console dans un fichier texte est facile. 

Pour accélérer le processus dans mon propre projet, j'ai remplacé les saisies des joueurs par un choix aléatoire que j'affiche pour mimer la saisie dans la trace d'exécution.

En cas de problème, ne pas hésiter à me contacter (denis.poitrenaud@u-paris.fr).