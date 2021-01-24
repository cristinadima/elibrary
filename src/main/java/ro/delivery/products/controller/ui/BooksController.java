package ro.delivery.products.controller.ui;

import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ro.delivery.products.dto.BookAssembler;
import ro.delivery.products.dto.BookDto;
import ro.delivery.products.entity.Author;
import ro.delivery.products.entity.Category;
import ro.delivery.products.repository.AuthorRepository;
import ro.delivery.products.repository.CategoryRepository;
import ro.delivery.products.rowdatagateway.BookFinder;
import ro.delivery.products.rowdatagateway.BookGateway;

@Controller
@RequestMapping(path = "/")
@FieldDefaults(makeFinal = true, level= AccessLevel.PRIVATE)
public class BooksController {

    BookFinder finder;

    AuthorRepository authorRepository;

    CategoryRepository categoryRepository;


    public BooksController(BookFinder finder, AuthorRepository authorRepository,
                           CategoryRepository categoryRepository) {
        this.finder = finder;
        this.authorRepository = authorRepository;
        this.categoryRepository = categoryRepository;
    }

    @RequestMapping(value = "/books", method = RequestMethod.GET)
    public String getBooks(Model model) {
        model.addAttribute("books", new BookAssembler().getBooks(finder.findBookGateways()));
        return "books";
    }

    @RequestMapping(value = "/addBook", method = RequestMethod.GET)
    public String addBook(Model model) {
        model.addAttribute("authors", authorRepository.findAll());
        model.addAttribute("categories", categoryRepository.findAll());
        return "addBook";
    }

    @RequestMapping(value = "/editBook/{bookId}", method = RequestMethod.GET)
    public String editBook(@PathVariable Integer bookId, Model model) {
        BookGateway bookGateway = finder.findBookGateway(bookId);
        BookAssembler assembler = new BookAssembler();
        BookDto dto = assembler.getBookDto(bookGateway);
        model.addAttribute("bookDto", dto);
        model.addAttribute("categories", categoryRepository.findAll());
        model.addAttribute("authors", authorRepository.findAll());
        return "editBook";
    }

    @RequestMapping(value = "/updateBook", method = RequestMethod.POST)
    public String updateBook(/*Model model,*/ @ModelAttribute("bookDto") BookDto bookDto) {
        // model.addAttribute("authors", authorRepository.findAll());
        // model.addAttribute("categories", categoryRepository.findAll());
        BookGateway bookGateway = new BookGateway();

        bookGateway.setIdBook(bookDto.getIdBook());
        bookGateway.setCategory(new Category().setIdCat(bookDto.getIdCategory()));
        bookGateway.setAuthor(new Author().setIdAuthor(bookDto.getIdAuthor()));
        bookGateway.setPublisher(bookDto.getPublisher());
        bookGateway.setTitle(bookDto.getTitle());
        bookGateway.updateBook();
     System.out.println("book id "+bookDto.getIdBook());

        return "redirect:/books";
      //  model.addAttribute("books", new BookAssembler().getBooks(finder.findBookGateways()));
     ///   return "redirect:/books";
    }

    @RequestMapping(value = "/insertBook", method = RequestMethod.POST)
    public String insertBook(Model model, @RequestBody BookDto bookDto) {
       // model.addAttribute("authors", authorRepository.findAll());
       // model.addAttribute("categories", categoryRepository.findAll());
        BookGateway bookGateway = new BookGateway();

        bookGateway.setCategory(new Category().setIdCat(bookDto.getIdCategory()));
        bookGateway.setAuthor(new Author().setIdAuthor(bookDto.getIdAuthor()));
        bookGateway.setPublisher(bookDto.getPublisher());
        bookGateway.setTitle(bookDto.getTitle());
        bookGateway.insertBook();

        model.addAttribute("books", new BookAssembler().getBooks(finder.findBookGateways()));
        return "redirect:/books";
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
