package ro.delivery.products.rowdatagateway;

public class AppQuery {
    public static final String INSERT_BOOK = "INSERT INTO book(\n" +
            "id_book, title, id_author, id_cat, publisher)\n" +
            "VALUES ((SELECT COALESCE ( max(id_book),  0)+1 FROM book), :title, :id_author, :id_cat, :publisher)";
    //se cauta idul max sau se pune 0+1 val default

    public static final String DELETE_BOOK = "DELETE FROM book where id_book = :id_book";
    public static final String UPDATE_BOOK = "UPDATE book(\n" +
            "set title = :title, id_author = :id_author, id_cat = :id_cat, publisher = :publisher)\n" +
            "WHERE id_book = :id_book";
}
