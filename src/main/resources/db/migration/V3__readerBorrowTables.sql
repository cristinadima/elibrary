CREATE TABLE readers
(
  id_reader                          INTEGER NOT NULL,
  name                            CHARACTER VARYING(100) NOT NULL,
  birthday                            CHARACTER VARYING(100) NOT NULL,
  address                            CHARACTER VARYING(100) NOT NULL,
  phone                            CHARACTER VARYING(100) NOT NULL,
  CONSTRAINT readers_pkey PRIMARY KEY (id_reader)
);

CREATE SEQUENCE seq_readers;

CREATE TABLE borrows
(
    id_borrow                         INTEGER NOT NULL,
    returndate                            CHARACTER VARYING(100) NOT NULL,
    id_book                            INTEGER NOT NULL,
    id_reader                            INTEGER NOT NULL,
    CONSTRAINT borrows_pkey PRIMARY KEY (id_borrow)
);

CREATE SEQUENCE seq_borrows;