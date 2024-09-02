CREATE TABLE ressources(
                        id SERIAL PRIMARY KEY,
                        nom VARCHAR(255) ,
                        quantity DOUBLE PRECISION,
                        tache_id BIGINT
);