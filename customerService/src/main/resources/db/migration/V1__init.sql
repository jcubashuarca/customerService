CREATE TABLE clients (
  id UUID PRIMARY KEY,
  first_name VARCHAR(250),
  last_name VARCHAR(250),
  age INT,
  birth_date TIMESTAMP,
  attempt_death_date TIMESTAMP
);