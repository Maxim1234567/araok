DROP TABLE IF EXISTS AGE_LIMIT;
CREATE TABLE AGE_LIMIT
(
    ID SERIAL PRIMARY KEY NOT NULL,
    DESCRIPTION VARCHAR(255),
    LIMITS BIGINT
);