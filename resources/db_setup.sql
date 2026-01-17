-- Création de la table livre
CREATE TABLE IF NOT EXISTS livre (
    id SERIAL PRIMARY KEY,
    titre VARCHAR(255) NOT NULL,
    auteur VARCHAR(255) NOT NULL,
    categorie VARCHAR(100),
    isbn VARCHAR(20) UNIQUE,
    nombre_exemplaires INT DEFAULT 1
);

-- Création de la table membre
CREATE TABLE IF NOT EXISTS membre (
    id SERIAL PRIMARY KEY,
    nom VARCHAR(100) NOT NULL,
    prenom VARCHAR(100) NOT NULL,
    email VARCHAR(150) UNIQUE,
    date_adhesion DATE DEFAULT CURRENT_DATE
);

-- Création de la table emprunt
CREATE TABLE IF NOT EXISTS emprunt (
    id_emprunt SERIAL PRIMARY KEY,
    membre_id INT REFERENCES membre(id) ON DELETE CASCADE,
    livre_id INT REFERENCES livre(id) ON DELETE CASCADE,
    date_emprunt DATE NOT NULL,
    date_retour_prevue DATE NOT NULL,
    date_retour_effective DATE
);