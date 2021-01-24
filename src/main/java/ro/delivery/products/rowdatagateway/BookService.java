package ro.delivery.products.rowdatagateway;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import ro.delivery.products.entity.Author;
import ro.delivery.products.entity.Category;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
@Repository
public class BookService {

    @PersistenceContext
    private final EntityManager entityManager;

    @Autowired
    public BookService(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Transactional
    public void insertBook(BookGateway book) {
        Query query = entityManager.createNativeQuery(AppQuery.INSERT_BOOK);
        query.setParameter("title",book. getTitle());
        query.setParameter("id_cat",book.getCategory().getIdCat());
        query.setParameter("id_author",book.getAuthor().getIdAuthor());
        query.setParameter("publisher", book.getPublisher());
        query.executeUpdate();
    }

    @Transactional
    public void deleteBook(BookGateway book) {
        Query query = entityManager.createNativeQuery(AppQuery.DELETE_BOOK);
        query.setParameter("id_book", book.getIdBook());
        query.executeUpdate();
    }

    @Transactional
    public void updateBook(BookGateway book) {
        Query query = entityManager.createNativeQuery(AppQuery.UPDATE_BOOK);
        query.setParameter("title", book.getTitle());
        query.setParameter("id_cat",book.getCategory().getIdCat());
        query.setParameter("id_author", book.getAuthor().getIdAuthor());
        query.setParameter("publisher", book.getPublisher());
        query.setParameter("id_book", book.getIdBook());
        query.executeUpdate();
    }
}
