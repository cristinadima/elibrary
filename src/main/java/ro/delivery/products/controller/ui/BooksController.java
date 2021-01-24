package ro.delivery.products.controller.ui;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import ro.delivery.products.dto.BookAssembler;
import ro.delivery.products.entity.Author;
import ro.delivery.products.entity.Book;
import ro.delivery.products.entity.Category;
import ro.delivery.products.repository.BookFinder;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping(path = "/")
@FieldDefaults(makeFinal = true, level= AccessLevel.PRIVATE)
public class BooksController {

    BookFinder finder;

    public BooksController(BookFinder finder) {
        this.finder = finder;
    }

    @RequestMapping(value = "/books", method = RequestMethod.GET)
    public String getBooks(Model model) {
       // model.addAttribute("trainers", trainerRepository.findAll())
       /* books.add(new Book().setAuthor(new Author().setName("Unknown")).setTitle("Arta manipularii").setIdBook(1)
                .setCategory(new Category().setName("Psychology")).setPublisher("ABC"));
        books.add(new Book().setAuthor(new Author().setName("Known")).setTitle("hfxbcxgsd").setIdBook(2)
                .setCategory(new Category().setName("Art")).setPublisher("ABCDAR"));*/
      //  BookFinder finder = new BookFinder();
        model.addAttribute("books", new BookAssembler().getBooks(finder.findBookGateways()));
        return "books";
    }
/*
    @GetMapping("/products/{idCategory}")
    public List<Product> getPoductsByCategory(@PathVariable Integer idCategory) {
        return productRepository.findAllByIdCat(idCategory);
    }*/


}
/*
 * book [bookid, title, categoryid, authorid, publisher]
 * category [categoryid, name, description]
 * author[authorid, name, nationality]
 * publisher[publisherid, name]
 * reader[readerid, name, birthday, address, phone]
 * borrow[borrowid, returndate, bookid, readerid]
 * admin[adminid, username, password]
 */
