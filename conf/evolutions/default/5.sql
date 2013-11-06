# Tasks schema
 
# --- !Ups

CREATE SEQUENCE users_id_seq;
CREATE TABLE users (
    id integer NOT NULL DEFAULT nextval('users_id_seq'),
    name varchar(255),
    email varchar(255)
);
 
# --- !Downs
 
DROP TABLE users;
DROP SEQUENCE users_id_seq;
