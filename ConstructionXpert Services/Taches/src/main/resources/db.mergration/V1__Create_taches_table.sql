CREATE TYPE statut AS ENUM ('A_FAIRE', 'EN_COURS', 'TERMINE');

CREATE TABLE taches (
                        id SERIAL PRIMARY KEY,
                        description VARCHAR(255) ,
                        date_debut DATE ,
                        date_fin DATE ,
                        status statut,
                        projet_id BIGINT
);