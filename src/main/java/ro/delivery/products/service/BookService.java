package ro.delivery.products.service;

import org.springframework.stereotype.Service;
import ro.delivery.products.dto.BookAssembler;
import ro.delivery.products.dto.BookDto;
import ro.delivery.products.entity.Author;
import ro.delivery.products.entity.Category;
import ro.delivery.products.rowdatagateway.BookFinder;
import ro.delivery.products.rowdatagateway.BookGateway;

@Service
public class BookService implements IBookService{

    private BookFinder finder;

    public BookService(BookFinder finder) {
        this.finder = finder;
    }

    @Override
    public BookDto findBookDto(Integer bookId) {
        BookGateway bookGateway = finder.findBookGateway(bookId);
        BookAssembler assembler = new BookAssembler();
        return assembler.getBookDto(bookGateway);
    }

    public void updateBook(BookDto bookDto){
        BookGateway bookGateway = new BookGateway();
        bookGateway.setIdBook(bookDto.getIdBook());
        bookGateway.setCategory(new Category().setIdCat(bookDto.getIdCategory()));
        bookGateway.setAuthor(new Author().setIdAuthor(bookDto.getIdAuthor()));
        bookGateway.setPublisher(bookDto.getPublisher());
        bookGateway.setTitle(bookDto.getTitle());
        bookGateway.updateBook();

    }

    public void insertBook(BookDto bookDto){
        BookGateway bookGateway = new BookGateway();

        bookGateway.setCategory(new Category().setIdCat(bookDto.getIdCategory()));
        bookGateway.setAuthor(new Author().setIdAuthor(bookDto.getIdAuthor()));
        bookGateway.setPublisher(bookDto.getPublisher());
        bookGateway.setTitle(bookDto.getTitle());
        bookGateway.insertBook();
    }

    public void deleteBook(Integer id){
        BookGateway bookGateway = new BookGateway();
        bookGateway.setIdBook(id);
        bookGateway.deleteBook();
    }
}
