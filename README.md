# ENAA-SUPPORT
Contexte du projet
En tant que développeur full stack Java/Angular dans la société ITSolutions, vous êtes chargé de concevoir et de développer un système de gestion des équipements informatiques.

​

​

User Stories

​

Gestion des Équipements Informatiques

En tant qu'administrateur IT, je veux pouvoir ajouter de nouveaux équipements informatiques au système afin de suivre leur état et leur utilisation.

En tant qu'administrateur IT, je veux pouvoir modifier les informations des équipements existants pour maintenir des données à jour.

En tant qu'administrateur IT, je veux pouvoir supprimer des équipements obsolètes ou hors service pour garder le système organisé.

En tant qu'administrateur IT, je veux pouvoir voir une liste de tous les équipements avec leur état actuel pour une gestion efficace.

​

Suivi des Pannes

En tant qu'utilisateur, je veux pouvoir signaler une panne sur un équipement pour que le service IT puisse la prendre en charge.

En tant qu'administrateur IT, je veux pouvoir enregistrer les pannes détectées pour un suivi et une réparation efficace.

En tant qu'administrateur IT, je veux pouvoir suivre l'état des réparations pour chaque panne signalée pour assurer une résolution rapide. En tant qu'administrateur IT, je veux pouvoir consulter l'historique des pannes pour chaque équipement afin d'identifier les équipements problématiques.

​

Gestion des Tickets de Support

En tant qu'utilisateur, je veux pouvoir créer un ticket de support pour signaler un problème technique afin de recevoir de l'aide.

En tant qu'administrateur IT, je veux pouvoir attribuer les tickets de support aux techniciens disponibles pour une résolution rapide.

En tant qu'technicien IT, je veux pouvoir voir les tickets qui me sont attribués pour les traiter efficacement.

En tant qu'utilisateur, je veux pouvoir suivre l'état de mon ticket de support pour savoir quand mon problème sera résolu.

--Bonus--

Rapports et Statistiques

En tant qu'administrateur IT, je veux recevoir des notifications pour les tickets en attente afin de ne pas manquer de demandes importantes.

En tant qu'administrateur IT, je veux pouvoir voir des statistiques sur les pannes pour identifier les tendances et les problèmes récurrents. En tant qu'administrateur IT, je veux pouvoir générer des rapports sur l'état des équipements pour une meilleure gestion.

En tant qu'administrateur IT, je veux pouvoir générer des rapports sur les performances du service de support pour améliorer l'efficacité.

​

Fonctionnement:

Un Équipement peut être associé à plusieurs Pannes. Chaque fois qu'une panne est détectée ou signalée, une nouvelle entrée est créée dans l'entité Panne, liée à l'équipement concerné. Cela permet de suivre toutes les pannes d'un équipement particulier.
L'Historique des Pannes conserve un enregistrement détaillé de toutes les pannes passées pour chaque équipement. Chaque entrée dans l'historique est liée à un Équipement, permettant ainsi de garder une trace des problèmes rencontrés et des réparations effectuées.
Les Tickets de Support sont créés par les Utilisateurs lorsqu'ils rencontrent des problèmes avec les équipements. Chaque ticket est donc lié à un utilisateur spécifique, ce qui permet de suivre qui a signalé le problème et de maintenir une communication efficace.
Une fois un Ticket de Support créé, il est attribué à un Technicien pour résolution. Cette relation permet de suivre quel technicien est responsable de la résolution du problème et d'évaluer les performances des techniciens en fonction des tickets résolus.
La génèration des rapports incluant des informations sur les Équipements, les Pannes, et les Tickets deSupport.Les rapports sont essentiels pour évaluer l'état des équipements, la fréquence et la nature des pannes, ainsi que les performances des techniciens et du service de support.
​

Technologies Utilisées

Backend : Spring Boot, Spring Data JPA, Spring Security

Frontend : Angular 16

Base de données : PostgreSQL /MySQL

Test unitaire: JUnit

Conteneurisation: Docker

​

Modalités pédagogiques
● Travail : individuel

● Durée de travail :7 jours

● Date de lancement du brief: 02/08/2024 à 17h

● Date limite de soumission : 12/08/2024 avant 17h30

Modalités d'évaluation
Vous présenterez votre travail:
15 minutes : Présentation (Planification , journal de commit...).
15 minutes : Explication du code + Mise en situation.
10 minutes : Question culture web.

Livrables
•	Lien de la planification des tâches (Trello, ).
•	Support de présentation.
•	Lien vers le repository GitHub contenant :
    -- README (description de l'application).
    -- Les Trois Diagrammes UML en format image (PNG, JPEG…)
    -- Le code source de l'application avec tous les fichiers nécessaires.

Critères de performance
-Fonctionnalités complètes et fonctionnelles conformes aux spécifications.
-Vous devez gérer les exceptions.
-Tests des API avec Postman
-Tests unitaires pour assurer la qualité et la fiabilité de l’application.(JUnit)
-Respect des délais de livraison.
