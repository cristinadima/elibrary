package ro.delivery.products.rowdatagateway;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ro.delivery.products.entity.Author;
import ro.delivery.products.entity.Category;

import javax.persistence.*;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class BookFinder {

    @PersistenceContext
    private final EntityManager entityManager;

    @Autowired
    public BookFinder(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    private Map<Integer, BookGateway> booksRetrieved = new HashMap<>();//identity map

    public BookGateway findBookGateway(int bookId) {
        if (booksRetrieved.containsKey(bookId)){
            return booksRetrieved.get(bookId);
        }else{
            CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
            CriteriaQuery query = criteriaBuilder.createQuery();
            Root userRoot = query.from(BookGateway.class);
            Predicate nameRestriction =
                    criteriaBuilder.equal( userRoot.get( "idBook" ), bookId );
            query.select(userRoot);
            query.where( nameRestriction );
            Query q = entityManager.createQuery(query);
            if (q.getResultList() == null || q.getResultList().isEmpty()) {
                return new BookGateway();
            }else{
                BookGateway result = (BookGateway) q.getResultList().get(0);
                booksRetrieved.put(result.getIdBook(), result);
                return result;
            }
        }

    }

    public List<BookGateway> findBookGateways() {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery query = criteriaBuilder.createQuery();
        Root userRoot = query.from(BookGateway.class);

        query.select(userRoot);
        Query q = entityManager.createQuery(query);
        if (q.getResultList() == null || q.getResultList().isEmpty()) {
            return new ArrayList<>();
        }else{
            List<BookGateway> list = new ArrayList<>();
            for(BookGateway b: (List<BookGateway>)q.getResultList() ){
                b.setAuthor(findAuthor(b.getAuthor().getIdAuthor()));
                b.setCategory(findCategory(b.getCategory().getIdCat()));
                list.add(b);
            }

            return list;
        }
    }

    public Author findAuthor(int authorId) {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery query = criteriaBuilder.createQuery();
        Root authorRoot = query.from(Author.class);
        Predicate nameRestriction =
                criteriaBuilder.equal( authorRoot.get( "idAuthor" ), authorId );
        query.select(authorRoot);
        query.where( nameRestriction );
        Query q = entityManager.createQuery(query);
        if (q.getResultList() == null || q.getResultList().isEmpty()) {
            return new Author();
        }else{
            return (Author) q.getResultList().get(0);
        }
    }

    public Category findCategory(int categoryId) {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery query = criteriaBuilder.createQuery();
        Root categoryRoot = query.from(Category.class);
        Predicate nameRestriction =
                criteriaBuilder.equal( categoryRoot.get( "idCat" ), categoryId );
        query.select(categoryRoot);
        query.where( nameRestriction );
        Query q = entityManager.createQuery(query);
        if (q.getResultList() == null || q.getResultList().isEmpty()) {
            return new Category();
        }else{
            return (Category) q.getResultList().get(0);
        }
    }
}
