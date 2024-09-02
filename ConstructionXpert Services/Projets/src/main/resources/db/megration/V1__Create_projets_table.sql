CREATE TABLE projets (
                         id SERIAL PRIMARY KEY,
                         nom VARCHAR(255) ,
                         description VARCHAR(255),
                         date_debut DATE,
                         date_fin DATE,
                         budget DOUBLE PRECISION
);