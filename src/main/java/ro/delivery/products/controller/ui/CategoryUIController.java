package ro.delivery.products.controller.ui;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import ro.delivery.products.entity.Category;
import ro.delivery.products.repository.AuthorRepository;
import ro.delivery.products.repository.CategoryRepository;

import java.util.List;

@Controller
@RequestMapping(path = "/")
@RequiredArgsConstructor
@FieldDefaults(makeFinal = true, level= AccessLevel.PRIVATE)
public class CategoryUIController {

    CategoryRepository categoryRepository;

    @RequestMapping(value = "/categories", method = RequestMethod.GET)
    public String getAllCategories(Model model) {
        model.addAttribute("categories", categoryRepository.findAll());
        return "categories";
    }

    @RequestMapping(value = "/addCategory", method = RequestMethod.GET)
    public String addCategory(Model model) {

        model.addAttribute("categories", categoryRepository.findAll());
        return "addCategory";
    }

    @RequestMapping(value = "/insertCategory", method = RequestMethod.POST)
    public String insertCategory(@RequestBody Category category) {
        //model.addAttribute("books", new BookAssembler().getBooks(finder.findBookGateways()));
        categoryRepository.save(category);
        return "redirect:/categories";
    }

    @RequestMapping(value = "/editCategory/{categoryId}", method = RequestMethod.GET)
    public String editCategory(@PathVariable Integer categoryId, Model model) {
        model.addAttribute("category", categoryRepository.findById(categoryId));
        return "editCategory";
    }

    @RequestMapping(value = "/updateCategory", method = RequestMethod.POST)
    public String updateCategory(@RequestBody  Category category) {
        categoryRepository.save(category);
        return "redirect:/categories";
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
