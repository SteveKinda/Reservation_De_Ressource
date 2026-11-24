# Réservation de ressources

## Jalon 4 — Exceptions, tests, recette et dépôt final

### Membres du groupe (G06)

- KINDA Yiponeba Steve Regis
- OUEDRAOGO Anicet Azaria
- AKPO Okaté Nandja

## Présentation

Application Java en ligne de commande, en mémoire, pour réserver des salles et des équipements sur des créneaux qui ne se chevauchent pas.

## Modèle

- `Utilisateur` : identifiant, nom, adresse électronique. Égalité sur l'identifiant.
- `Creneau` : jour, heure de début et de fin. Les heures sont entières, comprises entre 0 et 24 (24 = minuit) et la fin est strictement après le début. `chevauche(Creneau)` : deux créneaux adjacents (8-10 et 10-12) ne se chevauchent pas. Mais deux créneaux non adjacents (8-10 et 9-11) se chevauchent.
- `Ressource` (abstraite), `Salle`, `Equipement` : `estCompatible(Creneau)` est redéfinie dans chaque sous-classe (horaires d'ouverture pour la salle, durée maximale pour l'équipement). Égalité sur l'identifiant.
- `Reservation` : objet immuable (utilisateur, ressource, créneau).
- `CalendrierReservations` (interface) et `CalendrierMemoire` : stockage des réservations.
- `ServiceReservation` : règles métier (`reserver`, `annuler`, `reservationsDe`, `ajouterRessource`).
- Exceptions : `ReservationException` (non vérifiée) et ses quatre sous-classes.

Le diagramme de classes final du jalon 4 qui constitue également le diagramme final du projet se trouve dans `diagramme_jalon4.png`.

## Dépendance du service envers l'interface

`ServiceReservation` ne connaît que l'interface `CalendrierReservations` : le calendrier lui est fourni par son constructeur (`new ServiceReservation(new CalendrierMemoire())`). Le service ne mentionne jamais `CalendrierMemoire` ni la `Map` qu'il utilise. On peut donc remplacer le stockage (autre structure, fichier, base de données) sans modifier le service, et tester le service avec n'importe quelle implémentation du contrat.

## Choix de collections

`CalendrierMemoire` utilise une `Map<Ressource, List<Reservation>>` (`LinkedHashMap`) :

- **Map** : accès direct aux réservations d'une ressource (clé = ressource, d'où `equals` / `hashCode` définis et testés).
- **List** : conserve l'ordre d'ajout et se parcourt pour détecter les chevauchements.
- **Set** non retenu : l'unicité ne suffit pas, il faut comparer les créneaux entre eux.

La collection interne n'est jamais exposée : les recherches retournent des copies non modifiables.

## Exceptions et contrat du service

| Situation | Exception | Calendrier |
|---|---|---|
| Argument nul ou vide ; ressource incompatible avec le créneau (hors horaires de salle, durée maximale d'équipement dépassée) ; identifiant de ressource déjà utilisé | `DonneeInvalideException` | inchangé |
| Identifiant de ressource inconnu | `RessourceAbsenteException` | inchangé |
| Créneau qui chevauche une réservation existante de la même ressource | `ConflitCreneauException` | inchangé |
| Annulation d'une réservation qui n'existe pas | `ReservationIntrouvableException` | inchangé |

**Atomicité** : `reserver` vérifie d'abord les arguments, l'existence de la ressource et sa compatibilité (horaires, durée), puis tente d'ajouter au calendrier en dernier. Le calendrier n'est modifié que si tout est valide.

## Tests

Les tests automatisés JUnit 5 sont dans `CreneauTest`, `CalendrierMemoireTest` et `ServiceReservationTest` : 27 tests (6 + 8 + 13), dont 6 cas d'échec qui vérifient aussi l'état du calendrier après l'exception. Ils couvrent les cas nominaux, limites (réservation jusqu'à la fermeture, durée maximale exacte, 24 h), conflits, annulations, recherches, listes protégées et arguments absents.

Résultat de l'exécution dans VS Code JUnit 6 : 27 tests exécutés, 27 réussis, 0 échec 

## Exécution

Le projet a été entièrement réalisé avec VS Code

## Organisation des fichiers

- `Main.java` : démonstration (recette).
- `Utilisateur.java`, `Creneau.java`, `Ressource.java`, `Salle.java`, `Equipement.java`, `Localisation.java` : modèle (`Localisation` n'est pas utilisée par le service).
- `Reservation.java`, `CalendrierReservations.java`, `CalendrierMemoire.java`, `ServiceReservation.java`.
- `ReservationException.java`, `DonneeInvalideException.java`, `RessourceAbsenteException.java`, `ConflitCreneauException.java`, `ReservationIntrouvableException.java`.
- `CreneauTest.java`, `CalendrierMemoireTest.java`, `ServiceReservationTest.java`.
- `diagramme_jalon4.png`.

NB : aide de l'intelligence artificielle (Claude) pour une partie du code (jalons 3 et 4), pour la collection ainsi que pour mieux comprendre comment fonctionne JUnit , le README et le diagramme.
