# Système de Gestion de Bibliothèque (Java/PostgreSQL)

## Description
Application de gestion de bibliothèque permettant le suivi des livres, des membres et des emprunts avec calcul automatique des pénalités de retard. Ce projet valide la communication entre une application Java et une base de données relationnelle.

## Installation et Configuration
1. **Base de données** : 
   - Créer une base nommée `gestion_bibliotheque` sous PostgreSQL.
   - Exécuter le script SQL situé dans `/resources/db_setup.sql` pour générer les tables.
2. **JDBC** : Le driver `postgresql-42.7.2.jar` doit être présent dans le dossier `/lib`.
3. **Configuration** : Mettre à jour les identifiants de connexion dans `src/DBConnection.java` (url, user, password).

## Utilisation
Pour compiler et lancer l'application depuis la racine :

```bash
# Compilation
javac -d bin -cp "lib/*" src/*.java

# Exécution du Menu Principal
java -cp "bin;lib/*" Main

