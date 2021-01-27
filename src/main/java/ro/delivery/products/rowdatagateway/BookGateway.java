package ro.delivery.products.rowdatagateway;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;
import ro.delivery.products.entity.Author;
import ro.delivery.products.entity.Category;

import javax.persistence.*;
import javax.transaction.Transactional;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "books")
@FieldDefaults(level= AccessLevel.PRIVATE)
public class BookGateway {

    @Id
    @Column(name = "id_book")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_books_gen")
    @SequenceGenerator(name = "seq_books_gen", allocationSize = 1, sequenceName = "seq_books")
    Integer idBook;
    String title;
    String publisher;

    @ManyToOne
    @NotFound(action = NotFoundAction.IGNORE)
    @JoinColumn(name = "id_cat")
    Category category;

    @ManyToOne
    @NotFound(action = NotFoundAction.IGNORE)
    @JoinColumn(name = "id_author")
    Author author;


    private Connection getConnection(){
         String dbURL = "jdbc:postgresql://localhost:5432/library";
         String username = "library";
         String password = "library";
        Connection conn = null;
        try {
             conn = DriverManager.getConnection(dbURL, username, password);
             if (conn != null) {
                 System.out.println("Connected");
             }
         } catch (SQLException ex) {
             ex.printStackTrace();
         }
         return conn;
     }

    @Transactional
    public void updateBook() {
        Connection connection = getConnection();
        try{
            String sql = "UPDATE books SET title=?, id_author=?, id_cat=?, publisher=? WHERE id_book=?";

            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, getTitle());
            statement.setInt(2, getAuthor().getIdAuthor());
            statement.setInt(3, getCategory().getIdCat());
            statement.setString(4, getPublisher());
            statement.setInt(5, getIdBook());

            int rowsUpdated = statement.executeUpdate();
            System.out.println("updated "+rowsUpdated);
            connection.close();
        }catch (SQLException e){
            e.printStackTrace();
        }
    }

    @Transactional
    public void insertBook() {
        Connection connection = getConnection();
        try{
            String sql =
                    "INSERT INTO books(\n" +
                            "id_book, title, id_author, id_cat, publisher)\n" +
                            "VALUES ((SELECT COALESCE ( max(id_book),  0)+1 FROM books), " +
                            "?,?,?,?)";


            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, title);
            statement.setInt(2, author.getIdAuthor());
            statement.setInt(3, category.getIdCat());
            statement.setString(4, publisher);
            int rowsInserted = statement.executeUpdate();
            System.out.println("inserted "+rowsInserted);
            connection.close();
        }catch (SQLException e){
            e.printStackTrace();
        }
    }

    @Transactional
    public void deleteBook() {
        Connection connection = getConnection();
        try{
            String sql = "DELETE FROM books WHERE id_book=?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, idBook);

            int rowsDeleted = statement.executeUpdate();

            System.out.println("deleted "+rowsDeleted);
            connection.close();
        }catch (SQLException e){
            e.printStackTrace();
        }
    }
}
