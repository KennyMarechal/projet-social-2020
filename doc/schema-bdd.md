# Schéma de la base de données

La base de données est construite avec le SGBD PostgreSQL

## Tables

- autorisation
    - **salon_id** : bigint PRIMARY_KEY
    - **utilisateur_id** : bigint PRIMARY_KEY
- salon
    - **id** : serial PRIMARY_KEY
    - **titre** : text
- message
    - **id** : serial PRIMARY_KEY
    - **auteur** : text
    - **moment** : timestamp without timezone
    - **texte** : text
    - **salon_id** : bigint
- utilisateur
    - **id** : serial PRIMARY_KEY
    - **pseudo** : text
