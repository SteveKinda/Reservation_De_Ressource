cet projet est une application qui met en œuvre un système permettant a un établissement de gérer la réservation de ses différentes ressources(salles, matériel mobil, ordinateur portable). Pour cette partie dénommée jalon 1 constitue la première étape du programme destinée à la gestion des différentes ressources. En effet
faces aux multiples erreurs de réservations et de pertes au sein de cet établissement, l'objectif principal de notre travail vise a poser les fondations de notre système(modele objet) et à garantir l'integrité des donnees c'est à dire nous allons définir les responsabilités des entités tels que Ressource; Utilisateur et Creneau.de ce fait nous allons d'abord:
D'abord spécifier( c'est à dire   ) les invariants et analyser les notions de gestion de mutabilités/alias. Ensuite implémenter les méthodes et coder les classes avec des identifiants immuables et des constructeurs .Enfin nous réaliserons les séries de tests. Afin  de mieux cerner notre projet , nous avons structurer et centraliser notre système autour de:
  A-Les classes
  1) la classe Ressource.java : en effet c'est le fichier dans lequel la classe Ressource avec pour attributs: Int numéro, String nom est définie
  2) la classe Utilisateur.java :en effet c'est le fichier dans lequel la classe Utilisateur avec pour attributs:int numero,String nom,String email est definie
  3) la classe Créneau.java: en effet c'est le fichier dans lequel la classe Creneau avec pour attibuts :int jour, int heureDebut,int heureFin est definie
     
  B- les methodes
  Elles sont entre autre:
  1) la methode liberer();
  2) la methode reserver();
  3) la methode afficher()
 En dernière position nous avons le fichier Main.java dans lequel se trouve tous les tests de vérifications de notre programme.
