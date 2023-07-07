CREATE DATABASE origencorp_prueba;

\c origencorp_prueba

CREATE TABLE nasa_data (
  id SERIAL PRIMARY KEY,
  href TEXT,
  center TEXT,
  title TEXT,
  nasa_id TEXT,
  date_created TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

