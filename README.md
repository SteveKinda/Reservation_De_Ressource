# Réservation de ressources

## Jalon 2 — Composition et polymorphisme

### Membres du groupe

- KINDA Yiponeba Steve Regis
- OUEDRAOGO Anicet Azaria
- AKPO Okaté Nandja

## Présentation

Ce projet est une application Java en ligne de commande pour modéliser des utilisateurs, des créneaux et des ressources réservables. Cette version correspond au jalon 2 : elle met en place une classe abstraite `Ressource` et deux sous-classes, `Salle` et `Equipement`.

## Modèle actuel

- `Utilisateur` représente un utilisateur avec un identifiant, un nom et une adresse électronique.
- `Creneau` représente un jour et une heure de début et de fin. Sa méthode `getDuree()` calcule la durée en heures.
- `Ressource` contient les informations communes aux ressources : identifiant, nom et localisation. La localisation est stockée sous forme de `String`.
- `Salle` hérite de `Ressource` et ajoute une capacité ainsi que des heures d'ouverture. Elle vérifie qu'un créneau est compris dans ces horaires.
- `Equipement` hérite de `Ressource` et ajoute une catégorie ainsi qu'une durée maximale de réservation. Il vérifie que le créneau ne dépasse pas cette durée.

`Salle` et `Equipement` redéfinissent `estCompatible(Creneau)`. Dans `Main`, cette méthode est appelée via des variables de type `Ressource`, sans test du type concret.

> Le fichier `Localisation.java` est présent dans le dossier, mais il n'est pas utilisé par `Ressource` dans cette version : l'attribut `localisation` est une chaîne de caractères (`String`).

## Organisation des fichiers

- `Main.java` : démonstration et essais reproductibles.
- `Ressource.java` : classe abstraite commune.
- `Salle.java` : sous-classe de `Ressource` pour les salles.
- `Equipement.java` : sous-classe de `Ressource` pour les équipements.
- `Creneau.java` : créneau avec validation des heures.
- `Utilisateur.java` : utilisateur avec validation des champs.
- `Localisation.java` : classe présente dans le dossier, non utilisée dans le modèle actuel.


## État du projet

Le projet couvre actuellement le modèle de base et le polymorphisme du jalon 2. Le calendrier de réservations, le service de réservation, les conflits entre réservations et les tests automatisés JUnit ne sont pas encore implémentés.
