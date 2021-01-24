package ro.delivery.products.repository;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ro.delivery.products.entity.Author;
import ro.delivery.products.entity.Category;

import javax.persistence.*;
import javax.transaction.Transactional;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "books")
@Repository
@Transactional
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

    @PersistenceContext
    @Transient
    private EntityManager entityManager;

    @Autowired
    public BookGateway(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Transactional
    public void insertBook() {
        Query query = entityManager.createNativeQuery(AppQuery.INSERT_BOOK);
        query.setParameter("title", getTitle());
        query.setParameter("id_cat",category.getIdCat());
        query.setParameter("id_author",author.getIdAuthor());
        query.setParameter("publisher", getPublisher());
        query.executeUpdate();
    }

    @Transactional
    public void deleteBook() {
        Query query = entityManager.createNativeQuery(AppQuery.DELETE_BOOK);
        query.setParameter("id_book", getIdBook());
        query.executeUpdate();
    }

    @Transactional
    public void updateBook() {
        Query query = entityManager.createNativeQuery(AppQuery.UPDATE_BOOK);
        query.setParameter("title", getTitle());
        query.setParameter("id_cat",category.getIdCat());
        query.setParameter("id_author", author.getIdAuthor());
        query.setParameter("publisher", getPublisher());
        query.setParameter("id_book", getIdBook());
        query.executeUpdate();
    }
}
