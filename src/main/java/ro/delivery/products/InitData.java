package ro.delivery.products;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Component;
import ro.delivery.products.entity.Author;
import ro.delivery.products.entity.Category;
import ro.delivery.products.repository.AuthorRepository;
import ro.delivery.products.repository.CategoryRepository;
import ro.delivery.products.rowdatagateway.BookGateway;
/*import ro.delivery.products.repository.OrderRepository;
import ro.delivery.products.repository.OrderedProductRepository;*/

import javax.annotation.PostConstruct;

@Component
@RequiredArgsConstructor
@FieldDefaults(makeFinal = true, level= AccessLevel.PRIVATE)
public class InitData {

    CategoryRepository categoryRepository;
    AuthorRepository authorRepository;

    @PostConstruct
    public void initData() {
        if (categoryRepository.count() == 0) {
            categoryRepository.save(new Category().setName("Science Fiction").setDescription("Unrealistic description of reality"));
            categoryRepository.save(new Category().setName("Poetry").setDescription("Dreamy vision of reality"));
            categoryRepository.save(new Category().setName("Thriller").setDescription("Scary scars"));
            categoryRepository.save(new Category().setName("Cooking").setDescription("Poison vs poisson"));
            categoryRepository.save(new Category().setName("Police").setDescription("Escape"));
        }
        if (authorRepository.count() == 0){
            authorRepository.save(new Author().setIdAuthor(1).setName("Cineva").setNationality("ceva"));
        }
       /* BookGateway b = new BookGateway()
                .setAuthor(new Author().setIdAuthor(1).setName("Cineva")).setCategory(new Category().setIdCat(2).setName("Poetry"))
                .setPublisher("an").setTitle("titlu");
        b.insertBook();*/

    }

}
