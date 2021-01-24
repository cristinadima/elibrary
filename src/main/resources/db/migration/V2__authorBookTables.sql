CREATE TABLE authors
(
  id_author                          INTEGER NOT NULL,
  name                            CHARACTER VARYING(100) NOT NULL,
  nationality                            CHARACTER VARYING(100) NOT NULL,
  CONSTRAINT authors_pkey PRIMARY KEY (id_author)
);

CREATE SEQUENCE seq_authors;

CREATE TABLE books
(
    id_book                          INTEGER NOT NULL,
    title                            CHARACTER VARYING(100) NOT NULL,
    id_author                            INTEGER NOT NULL,
    id_cat                            INTEGER NOT NULL,
    publisher                            CHARACTER VARYING(100) NOT NULL,
    CONSTRAINT books_pkey PRIMARY KEY (id_book)
);

CREATE SEQUENCE seq_books;