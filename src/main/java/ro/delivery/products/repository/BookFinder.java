package ro.delivery.products.repository;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import ro.delivery.products.entity.Author;
import ro.delivery.products.entity.Category;

import javax.persistence.*;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Repository
public class BookFinder {

    //EntityManagerFactory emf = Persistence.createEntityManagerFactory("ro.delivery.products");
    @PersistenceContext
    private final EntityManager entityManager;

    @Autowired
    public BookFinder(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

   /* public EntityManager entityManager {
        if (entityManager == null){
            entityManager = emf.createEntityManager();
        }
        return entityManager;
    }*/

    public BookGateway findBookGateway(int bookId) {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery query = criteriaBuilder.createQuery();
        Root userRoot = query.from(BookGateway.class);
        Predicate nameRestriction =
                criteriaBuilder.equal( userRoot.get( "id_book" ), bookId );
        query.select(userRoot);
        query.where( nameRestriction );
        Query q = entityManager.createQuery(query);
        if (q.getResultList() == null || q.getResultList().isEmpty()) {
            return new BookGateway();
        }else{
            BookGateway result = (BookGateway) q.getResultList().get(0);
           // result.setAuthorName(findAuthor(result.getIdAuthor()).getName());
          //  result.setCategoryName(findCategory(result.getIdCat()).getName());
            return result;
        }
    }

    public List<BookGateway> findBookGateways() {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery query = criteriaBuilder.createQuery();
        Root userRoot = query.from(BookGateway.class);
       /* Predicate nameRestriction =
                criteriaBuilder.equal( userRoot.get( "id_book" ), bookId );*/
        query.select(userRoot);
      //  query.where( nameRestriction );
        Query q = entityManager.createQuery(query);
        if (q.getResultList() == null || q.getResultList().isEmpty()) {
            return new ArrayList<>();
        }else{
           /* List<BookGateway> list = new ArrayList<>();
            for(BookGateway b: (List<BookGateway>)q.getResultList() ){
            //    b.setAuthorName(findAuthor(b.getIdAuthor()).getName());
            //    b.setCategoryName(findCategory(b.getIdCat()).getName());
                list.add(b);
            }

            return list;*/
           return (List<BookGateway>)q.getResultList();
        }
    }

    public Author findAuthor(int authorId) {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery query = criteriaBuilder.createQuery();
        Root authorRoot = query.from(Author.class);
        Predicate nameRestriction =
                criteriaBuilder.equal( authorRoot.get( "id_author" ), authorId );
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
                criteriaBuilder.equal( categoryRoot.get( "id_cat" ), categoryId );
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
