package ro.delivery.products.controller.ui;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ro.delivery.products.entity.Borrow;
import ro.delivery.products.entity.Reader;
import ro.delivery.products.repository.BorrowRepository;
import ro.delivery.products.repository.ReaderRepository;
import ro.delivery.products.rowdatagateway.BookFinder;
import ro.delivery.products.service.BookService;

@Controller
@RequestMapping(path = "/")
@FieldDefaults(makeFinal = true, level= AccessLevel.PRIVATE)
public class BorrowsController {

    BorrowRepository borrowRepository;
    ReaderRepository readerRepository;
    BookFinder bookFinder;

    @Autowired
    public BorrowsController(BorrowRepository borrowRepository, ReaderRepository readerRepository, BookFinder finder) {
        this.borrowRepository = borrowRepository;
        this.readerRepository = readerRepository;
        this.bookFinder = finder;
    }

    @RequestMapping(value = "/borrows", method = RequestMethod.GET)
    public String getBorrows(Model model) {
        model.addAttribute("borrows", borrowRepository.findAll());
        return "borrows";
    }

    @RequestMapping(value = "/addBorrow", method = RequestMethod.GET)
    public String addBorrow(Model model) {
        model.addAttribute("borrow", new Borrow());
        model.addAttribute("readers", readerRepository.findAll());
        model.addAttribute("books", bookFinder.findBookGateways());
        return "addBorrow";
    }

    @RequestMapping(value = "/insertBorrow", method = RequestMethod.POST)
    public String insertBorrow(@ModelAttribute("borrow") Borrow borrow) {
        borrowRepository.save(borrow);
        return "redirect:/borrows";
    }

    @RequestMapping(value = "/editBorrow/{borrowId}", method = RequestMethod.GET)
    public String editBorrow(@PathVariable Integer borrowId, Model model) {
        model.addAttribute("borrow", borrowRepository.findById(borrowId).get());
        model.addAttribute("readers", readerRepository.findAll());
        model.addAttribute("books", bookFinder.findBookGateways());
        return "editBorrow";
    }

    @RequestMapping(value = "/updateBorrow", method = RequestMethod.POST)
    public String updateBorrow(@ModelAttribute("borrow") Borrow borrow) {
        borrowRepository.save(borrow);
        return "redirect:/borrows";
    }

    @DeleteMapping("/deleteBorrow")
    @ResponseBody
    public ResponseEntity<Integer> deleteBorrow(@RequestParam("idBorrow") Integer idBorrow) {
        try {
            borrowRepository.deleteById(idBorrow);
            return new ResponseEntity<>(idBorrow, HttpStatus.OK);
        }catch (Exception e){
            e.printStackTrace();
            return new ResponseEntity<>(idBorrow, HttpStatus.NOT_FOUND);
        }
    }
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
