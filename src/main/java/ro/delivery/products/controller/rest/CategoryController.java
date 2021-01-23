package ro.delivery.products.controller.rest;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ro.delivery.products.entity.Category;
import ro.delivery.products.repository.CategoryRepository;

import java.util.List;

@RestController
@RequestMapping(path = "/api")
@RequiredArgsConstructor
@FieldDefaults(makeFinal = true, level= AccessLevel.PRIVATE)
public class CategoryController {

    CategoryRepository categoryRepository;

    @GetMapping("/categories")
    public List<Category> getAllCategories() {
        return categoryRepository.findAll();
    }
/*
    @GetMapping("/products/{idCategory}")
    public List<Product> getPoductsByCategory(@PathVariable Integer idCategory) {
        return productRepository.findAllByIdCat(idCategory);
    }*/


}
/**
 * book [bookid, title, categoryid, authorid, publisher]
 * category [categoryid, name, description]
 * author[authorid, name, nationality]
 * publisher[publisherid, name]
 * reader[readerid, name, birthday, address, phone]
 * borrow[borrowid, returndate, bookid, readerid]
 * admin[adminid, username, password]
 */
