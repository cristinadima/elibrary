package ro.delivery.products.controller;

import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ro.delivery.products.dto.BookAssembler;
import ro.delivery.products.dto.BookDto;
import ro.delivery.products.repository.AuthorRepository;
import ro.delivery.products.repository.CategoryRepository;
import ro.delivery.products.rowdatagateway.BookFinder;
import ro.delivery.products.service.IBookService;

@Controller
@RequestMapping(path = "/")
@FieldDefaults(makeFinal = true, level= AccessLevel.PRIVATE)
public class BooksController {

    BookFinder finder;

    AuthorRepository authorRepository;

    CategoryRepository categoryRepository;

    IBookService bookService;


    public BooksController(BookFinder finder, AuthorRepository authorRepository,
                           CategoryRepository categoryRepository, IBookService bookService) {
        this.finder = finder;
        this.authorRepository = authorRepository;
        this.categoryRepository = categoryRepository;
        this.bookService = bookService;
    }

    @RequestMapping(value = "/books", method = RequestMethod.GET)
    public String getBooks(Model model) {
        model.addAttribute("books", new BookAssembler().getBooks(finder.findBookGateways()));
        return "books";
    }

    @RequestMapping(value = "/addBook", method = RequestMethod.GET)
    public String addBook(Model model) {
        model.addAttribute("bookDto", new BookDto());
        model.addAttribute("authors", authorRepository.findAll());
        model.addAttribute("categories", categoryRepository.findAll());
        return "addBook";
    }

    @RequestMapping(value = "/editBook/{bookId}", method = RequestMethod.GET)
    public String editBook(@PathVariable Integer bookId, Model model) {
        model.addAttribute("bookDto", bookService.findBookDto(bookId));
        model.addAttribute("categories", categoryRepository.findAll());
        model.addAttribute("authors", authorRepository.findAll());
        return "editBook";
    }

    @RequestMapping(value = "/updateBook", method = RequestMethod.POST)
    public String updateBook(@ModelAttribute("bookDto") BookDto bookDto) {
        bookService.updateBook(bookDto);

        return "redirect:/books";
    }

    @RequestMapping(value = "/insertBook", method = RequestMethod.POST)
    public String insertBook(Model model, @ModelAttribute("bookDto") BookDto bookDto) {
        bookService.insertBook(bookDto);
        return "redirect:/books";
    }

    @DeleteMapping(value = "/deleteBook")
    @ResponseBody
    public ResponseEntity<Integer> deleteBook(@RequestParam("idBook") Integer id){
        try{
            bookService.deleteBook(id);
            return new ResponseEntity<>(id, HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(id, HttpStatus.NOT_FOUND);
        }

    }
}

