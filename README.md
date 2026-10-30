1.	Présentation du projet 
Ce projet vise à concevoir un programme informatique permettant de réserver des ressources mises à disposition par un établissement. Ces ressources peuvent être des salles ou du matériel partagé, tel qu'un vidéoprojecteur ou un ordinateur portable. Le programme offrira aux utilisateurs la possibilité de réserver une ressource sur un créneau déterminé, de consulter les informations relatives à leur réservation et de libérer la ressource lorsqu'elle n'est plus nécessaire. Le développement est effectué en Java.
2.	Le contexte et les problématiques
Au sein d'un établissement, plusieurs utilisateurs peuvent être amenés à utiliser les mêmes ressources. En effet en l’absence d'un système organisé, il est difficile de savoir si une salle ou un matériel est déjà occupé. Cette situation peut engendrer :
 -Des réservations simultanées portant sur une même ressource. 
- Des difficultés à connaître la disponibilité des ressources. · Des erreurs dans les informations des utilisateurs.
- Des conflits entre les utilisateurs. Le projet a donc pour but de mettre en place une application simple permettant de mieux organiser les réservations et d'éviter les conflits d'utilisation des ressources. 
3. Objectifs du projet 
3.1. Objectif général Concevoir un programme Java permettant de gérer les réservations de salles et de matériels partagés au sein d'un établissement. 
3.2. Objectifs spécifiques Le programme doit permettre de :
- Créer et identifier les ressources disponibles.
- Enregistrer les informations des utilisateurs. 
- Définir un créneau de réservation à partir d'un jour, d'une heure de début et d'une heure de fin. 
- Effectuer une réservation pour une ressource. 
- Afficher les informations d'une réservation. ·
-Libérer une ressource après son utilisation. 
- Empêcher une nouvelle réservation lorsque la ressource est déjà réservée. 
4. Description du fonctionnement du système Le système s'appuie sur trois éléments principaux :
4-1. Les ressources : en effet chaque ressource dispose de :  Un identifiant unique, un nom, une indication de disponibilité. Par exemple : salle de cours, vidéoprojecteur, ordinateur portable. 
4-2. Les utilisateurs : en effet chaque utilisateur est caractérisé par : · Un identifiant, un nom, une adresse électronique. 
4-3. Les créneaux : en effet un créneau correspond à une période durant laquelle une ressource peut être réservée. Il est défini par :  un jour, une heure de début, une heure de fin. Dans cette première version, une ressource ne peut faire l'objet que d'une seule réservation à la fois. Une seconde réservation est refusée tant que la première n'a pas été libérée.
 5. Classes principales du projet
 Le projet s'articule principalement autour de trois classes : 
 5.1. Classe Ressource : Cette classe représente les salles et les matériels mis à disposition par l'établissement. Son rôle est :
-d’identifier une ressource. 
-De conserver ses informations.  
-De connaître son état de disponibilité. 
-De permettre sa réservation et sa libération.
 5.2. Classe Utilisateur : Cette classe représente une personne autorisée à effectuer une réservation. Son rôle est de : 
 -Identifier un utilisateur. 
- Conserver son nom. 
-Enregistrer son adresse électronique.
 5.3. Classe Creneau
 Cette classe représente la période choisie pour une réservation. Son rôle est de : 
-Enregistrer le jour concerné.
-Définir l'heure de début. 
- Définir l'heure de fin. 
-Vérifier la cohérence des horaires. 
6. Règles de gestion et invariants 
Les invariants sont des règles qui doivent être respectées en permanence par le programme. Les principales règles retenues sont :
- Chaque ressource possède un identifiant unique et valide. · Chaque utilisateur possède un identifiant valide.
-Les informations obligatoires ne doivent être ni nulles ni vides. 
-Les heures de début et de fin doivent être cohérentes.
-capacité d'une ressource, lorsqu'elle est définie, doit respecter les limites prévues.
- Une ressource indisponible ne peut pas être réservée une seconde fois.
- La libération d'une ressource doit rétablir son état de disponibilité.
Les identifiants des objets ne doivent pas être modifiés après leur création. Ces règles vont permettre de gérer les incohérences dans les données et de garantir le bon fonctionnement du programme. 
7. Technologies et outils utilisés Les outils retenus pour le développement sont : Outil Utilisation Java Langage de programmation Git Gestion des versions du code GitHub Hébergement et partage du projet Draw.io Réalisation du diagramme de classes IDE Java Écriture et exécution du programme Le programme fonctionne en mémoire et en ligne de commande. Aucune base de données, interface graphique ou connexion réseau n'est utilisée dans cette première version. 
8. Organisation des fichiers
 Le dépôt GitHub est organisé de la manière suivante : 
Projet-Réservation/ Creneau.java
 Diagramme du jalon_1 
Main.java 
README.md 
Ressource.java
Utilisateur.java 
Description des fichiers : 
a-Creneau.java : contient la classe représentant les créneaux de réservation. 
b-Ressource.java : contient la classe représentant les ressources.
c-Utilisateur.java : contient la classe représentant les utilisateurs.
d-Main.java : permet de lancer le programme et de tester les différentes opérations.
e-Diagramme du jalon_1: contient le diagramme de classes réalisé pour le premier jalon.
f-README.md : présente le projet, ses objectifs et son fonctionnement. 
9. Tests et vérifications
Les essais reproductibles sont réalisés afin de vérifier le respect des règles définies. Les principaux cas à tester sont : A-La création d'une ressource avec des informations valides. 
B-La création d'un utilisateur avec des informations valides. 
C-La création d'un créneau avec des horaires cohérents.
D-Le refus d'un identifiant invalide.
E-Le refus d'un créneau dont l'heure de fin précède ou égale l'heure de début.
F-Le refus d'une seconde réservation lorsque la ressource est déjà occupée.
G-La libération correcte d'une ressource.
H-La vérification de la disponibilité après libération.
 Les tests permettent de vérifier que les constructeurs et les méthodes respectent les règles établies. 
10. Limites du Jalon 1
 Cette première étape se concentre sur la conception du modèle et le respect des invariants. Certaines fonctionnalités ne sont pas encore prises en charge :
-La gestion de plusieurs créneaux pour une même ressource. -La gestion complète d'un calendrier.
-Les réservations répétées.
-La gestion des conflits complexes.
-L'utilisation d'une base de données.
-La création d'une interface graphique.
Ces fonctionnalités pourront être introduites progressivement au cours des prochains jalons.
 11. Résultats attendus du Jalon 1
 À l'issue de cette première étape, le projet doit disposer : · Des classes Ressource, Utilisateur et Creneau. 
-Identifiants correctement définis.
-De constructeurs contrôlés.
-De méthodes permettant de gérer les opérations principales. -D'un diagramme de classes.
-D'au moins six essais reproductibles.
-D'un programme compilable et exécutable.
-D'une documentation README.md complète. 
12. Membres du groupe 
Ce projet est réalisé par un groupe de trois étudiants : 
-KINDA Steve
- AKPO Nandja 
- OUEDRAOGO Anicet 
13. Conclusion 
Ce premier jalon constitue la base du système de réservation de ressources. Il permet de définir les différentes classes, leurs responsabilités et les règles que chacune doit respecter. Cette organisation constitue les fondements aux prochaines étapes du projet, notamment l'introduction de la composition, du polymorphisme et de la gestion de plusieurs créneaux de réservation.
