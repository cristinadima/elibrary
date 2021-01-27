package ro.delivery.products.controller;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ro.delivery.products.entity.Category;
import ro.delivery.products.repository.CategoryRepository;

@Controller
@RequestMapping(path = "/")
@RequiredArgsConstructor
@FieldDefaults(makeFinal = true, level= AccessLevel.PRIVATE)
public class CategoryController {

    CategoryRepository categoryRepository;

    @RequestMapping(value = "/categories", method = RequestMethod.GET)
    public String getCategories(Model model) {
        model.addAttribute("categories", categoryRepository.findAll());
        return "categories";
    }

    @RequestMapping(value = "/addCategory", method = RequestMethod.GET)
    public String addCategory(Model model) {
        model.addAttribute("category", new Category());
        return "addCategory";
    }

    @RequestMapping(value = "/insertCategory", method = RequestMethod.POST)
    public String insertCategory(@ModelAttribute("category") Category category) {
        categoryRepository.save(category);
        return "redirect:/categories";
    }

    @RequestMapping(value = "/editCategory/{categoryId}", method = RequestMethod.GET)
    public String editCategory(@PathVariable Integer categoryId, Model model) {
        model.addAttribute("category", categoryRepository.findById(categoryId).get());
        return "editCategory";
    }

    @RequestMapping(value = "/updateCategory", method = RequestMethod.POST)
    public String updateCategory(@ModelAttribute("category") Category category) {
        categoryRepository.save(category);
        return "redirect:/categories";
    }

    @DeleteMapping("/deleteCategory")
    @ResponseBody
    public ResponseEntity<Integer> deleteCategory(@RequestParam("idCat") Integer idCat) {
        try {
            categoryRepository.deleteById(idCat);
            return new ResponseEntity<>(idCat, HttpStatus.OK);
        }catch (Exception e){
            e.printStackTrace();
            return new ResponseEntity<>(idCat, HttpStatus.NOT_FOUND);
        }
    }
}

