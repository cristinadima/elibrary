package ro.delivery.products.controller;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ro.delivery.products.entity.Reader;
import ro.delivery.products.repository.ReaderRepository;

@Controller
@RequestMapping(path = "/")
@RequiredArgsConstructor
@FieldDefaults(makeFinal = true, level= AccessLevel.PRIVATE)
public class ReadersController {

    ReaderRepository readerRepository;

    @RequestMapping(value = "/readers", method = RequestMethod.GET)
    public String getReaders(Model model) {
        model.addAttribute("readers", readerRepository.findAll());
        return "readers";
    }

    @RequestMapping(value = "/addReader", method = RequestMethod.GET)
    public String addReader(Model model) {
        model.addAttribute("reader", new Reader());
        return "addReader";
    }

    @RequestMapping(value = "/insertReader", method = RequestMethod.POST)
    public String insertReader(@ModelAttribute("reader") Reader reader) {
        readerRepository.save(reader);
        return "redirect:/readers";
    }

    @RequestMapping(value = "/editReader/{readerId}", method = RequestMethod.GET)
    public String editCategory(@PathVariable Integer readerId, Model model) {
        model.addAttribute("reader", readerRepository.findById(readerId).get());
        return "editReader";
    }

    @RequestMapping(value = "/updateReader", method = RequestMethod.POST)
    public String updateCategory(@ModelAttribute("reader") Reader reader) {
        readerRepository.save(reader);
        return "redirect:/readers";
    }

    @DeleteMapping("/deleteReader")
    @ResponseBody
    public ResponseEntity<Integer> deleteReader(@RequestParam("idReader") Integer idReader) {
        try {
            readerRepository.deleteById(idReader);
            return new ResponseEntity<>(idReader, HttpStatus.OK);
        }catch (Exception e){
            e.printStackTrace();
            return new ResponseEntity<>(idReader, HttpStatus.NOT_FOUND);
        }
    }
}

