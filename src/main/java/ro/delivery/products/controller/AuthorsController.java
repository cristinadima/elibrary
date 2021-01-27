package ro.delivery.products.controller;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ro.delivery.products.entity.Author;
import ro.delivery.products.repository.AuthorRepository;

@Controller
@RequestMapping(path = "/")
@RequiredArgsConstructor
@FieldDefaults(makeFinal = true, level= AccessLevel.PRIVATE)
public class AuthorsController {

    AuthorRepository authorRepository;

    @RequestMapping(value = "/authors", method = RequestMethod.GET)
    public String getAuthors(Model model) {
        model.addAttribute("authors", authorRepository.findAll());
        return "authors";
    }

    @RequestMapping(value = "/addAuthor", method = RequestMethod.GET)
    public String addAuthor(Model model) {
        model.addAttribute("author", new Author());
        return "addAuthor";
    }

    @RequestMapping(value = "/insertAuthor", method = RequestMethod.POST)
    public String insertAuthor(@ModelAttribute("author") Author author) {
        authorRepository.save(author);
        return "redirect:/authors";
    }

    @RequestMapping(value = "/editAuthor/{authorId}", method = RequestMethod.GET)
    public String editAuthor(@PathVariable Integer authorId, Model model) {
        model.addAttribute("author", authorRepository.findById(authorId).get());
        return "editAuthor";
    }

    @RequestMapping(value = "/updateAuthor", method = RequestMethod.POST)
    public String updateAuthor(@ModelAttribute("author") Author author) {
        authorRepository.save(author);
        return "redirect:/authors";
    }

    @DeleteMapping("/deleteAuthor")
    @ResponseBody
    public ResponseEntity<Integer> deleteAuthor(@RequestParam("idAuthor") Integer idAuthor) {
        try {
            authorRepository.deleteById(idAuthor);
            return new ResponseEntity<>(idAuthor, HttpStatus.OK);
        }catch (Exception e){
            e.printStackTrace();
            return new ResponseEntity<>(idAuthor, HttpStatus.NOT_FOUND);
        }
    }
}

