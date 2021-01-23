CREATE TABLE categories
(
  id_cat                          INTEGER NOT NULL,
  name                            CHARACTER VARYING(100) NOT NULL,
  description                            CHARACTER VARYING(100) NOT NULL,
  CONSTRAINT categories_pkey PRIMARY KEY (id_cat)
);

CREATE SEQUENCE seq_categories;

/*CREATE TABLE orders
(
  id_order                        INTEGER NOT NULL,
  address                         CHARACTER VARYING(100) NOT NULL,
  phone                           CHARACTER VARYING(15) NOT NULL,
  order_date                 TIMESTAMP NOT NULL,
  CONSTRAINT orders_pkey PRIMARY KEY (id_order)
);*/
