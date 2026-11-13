# Réservation de ressources

## Jalon 3 — Interfaces et collections

### Membres du groupe

- KINDA Yiponeba Steve Regis
- OUEDRAOGO Anicet Azaria
- AKPO Okaté Nandja

## Présentation

Application Java en ligne de commande pour réserver des salles et des équipements sur des créneaux qui ne se chevauchent pas. Le jalon 3 ajoute la notion de réservation, le contrat `CalendrierReservations`, son implémentation en mémoire et les méthodes `equals` / `hashCode`.

## Modèle actuel

- `Utilisateur` : identifiant, nom, adresse électronique. Égalité sur l'identifiant.
- `Creneau` : jour, heure de début et de fin. `chevauche(Creneau)` indique si deux créneaux se recoupent (même jour et plages d'heures qui se croisent ; des créneaux adjacents, comme 8-10 et 10-12, ne se chevauchent pas). Égalité sur jour + heures.
- `Ressource` (abstraite), `Salle`, `Equipement` : `estCompatible(Creneau)` est redéfinie dans chaque sous-classe. Égalité sur l'identifiant, unique pour toutes les ressources.
- `Reservation` : objet immuable qui associe un utilisateur, une ressource et un créneau.
- `CalendrierReservations` (interface) : contrat du calendrier (`ajouter`, `annuler`, `estLibre`, `rechercherParRessource`, `rechercherParPeriode`, `taille`).
- `CalendrierMemoire` : implémentation du contrat.

## Choix de collections

`CalendrierMemoire` utilise une `Map<Ressource, List<Reservation>>` (`LinkedHashMap`) :

- **Map** : accès direct aux réservations d'une ressource, la ressource servant de clé (d'où `equals` / `hashCode` testés avant tout hachage).
- **List** : conserve l'ordre d'ajout et se parcourt pour détecter les chevauchements.
- **Set** non retenu : l'unicité ne suffit pas, il faut comparer les créneaux entre eux.

La collection interne n'est jamais exposée : les recherches retournent des copies non modifiables.

## Règles du calendrier

- Une ressource ne peut pas avoir deux réservations dont les créneaux se chevauchent.
- Un conflit est refusé (`ajouter` retourne `false`) et ne modifie pas le calendrier.
- Un argument nul est refusé par une `IllegalArgumentException`.
- La vérification `estCompatible` et les exceptions métier seront intégrées au `ServiceReservation` au jalon 4.

## Organisation des fichiers

- `Main.java` : essais reproductibles (26 essais cumulés).
- `Utilisateur.java`, `Creneau.java`, `Ressource.java`, `Salle.java`, `Equipement.java` : modèle.
- `Reservation.java` : réservation immuable.
- `CalendrierReservations.java` : interface.
- `CalendrierMemoire.java` : implémentation en mémoire.
- `Localisation.java`
- `diagramme.png` : diagramme du jalon 2 

## État du projet

Le service de réservation, les exceptions métier, les tests JUnit et la contrainte attribuée au groupe seront traités au jalon 4.

NB: Aide de l'intelligence artificielle pour la conception du ReadMe ainsi que du Diagramme.
