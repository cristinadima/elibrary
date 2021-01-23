package ro.delivery.products.controller.ui;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import ro.delivery.products.entity.Category;
import ro.delivery.products.repository.CategoryRepository;

import java.util.List;

@Controller
@RequestMapping(path = "/")
@RequiredArgsConstructor
@FieldDefaults(makeFinal = true, level= AccessLevel.PRIVATE)
public class DashboardController {


    @RequestMapping(value = "/home", method = RequestMethod.GET)
    public String displayAppointments(Model model) {
       // model.addAttribute("trainers", trainerRepository.findAll())
        System.out.println("called home endp");
        return "dashboard";
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
