package ro.delivery.products.controller.rest;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;
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
 *
 * ----
 * id map
 * cautam cartea pt edit in map, daca nu exista o luam din bd
 * comportamental ob relational
 * OK
 * ===
 * arhitectural de baza
 * gateway - clasa api pt comunicat cu serv extern: maps
 * ===
 * OK
 * arhitectural de date
 * row data gateway - book finder class , extinde book gateway [insert, update, delete 1, fielduri]
 * transaction script = metodele de insert, update, delete, select, cu grija la tranzactie [start, commit,
 * end]
 * ==
 * OK
 * strat 1. prezentare : js pt tratat cereri useri si html/thymeleaf pt ui [templates]
 * 2.domeniu: [modele]
 * 3: surse de date: comunicare cu bd [repository]
 * + [controller]
 * ===
 * OK
 * sabloane ale prezentarii web
 * mvc - model [2] , view [1] , controller
 * viewul depinde de model [in js avem instante de obiecte cu care populam pagina web], modelul nu are
 * referinte la paginile cu ui
 * controller spring, view cu thymeleaf si html, modele date
 * ===
 * OK
 * structural obiectual relationale
 * identity field/camp identitate - bookid face legatura cu acelasi id din bd
 * ==
 * client session state
 * pastram pe cookie putina informatie [e.g. last username]
 * ==
 * OK
 * distribuire
 * data transfer object  - dto class carte [idcarte, nume] , carte in baza de date cu clasa model [idcarte,
 * idautor etc]
 * 	-la unele apeluri in repository/mapper trimitem dto din pagina web la request
 * 	- clasa assembler cu metoda createDto , createDomainObject
 * ===
 */
