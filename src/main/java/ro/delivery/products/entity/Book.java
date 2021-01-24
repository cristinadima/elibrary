package ro.delivery.products.entity;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "books")
@FieldDefaults(level= AccessLevel.PRIVATE)
public class Book {

    @Id
    @Column(name = "id_book")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_books_gen")
    @SequenceGenerator(name = "seq_books_gen", allocationSize = 1, sequenceName = "seq_books")
    Integer idBook;

    String title;

    @ManyToOne
    @NotFound(action = NotFoundAction.IGNORE)
    @JoinColumn(name = "id_cat")
    Category category;

    @ManyToOne
    @NotFound(action = NotFoundAction.IGNORE)
    @JoinColumn(name = "id_author")
    Author author;

    String publisher;


}
