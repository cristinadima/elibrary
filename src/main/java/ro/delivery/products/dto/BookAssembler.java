package ro.delivery.products.dto;

import ro.delivery.products.rowdatagateway.BookGateway;

import java.util.ArrayList;
import java.util.List;

public class BookAssembler {
    public BookDto getBookDto(BookGateway book){
        BookDto bookDto = new BookDto();
        bookDto.setAuthorName(book.getAuthor().getName());
        bookDto.setCategoryName(book.getCategory().getName());
        bookDto.setIdAuthor(book.getAuthor().getIdAuthor());
        bookDto.setIdBook(book.getIdBook());
        bookDto.setIdCategory(book.getCategory().getIdCat());
        bookDto.setPublisher(book.getPublisher());
        bookDto.setTitle(book.getTitle());
        return  bookDto;
    }

   /* public Book getBook(BookDto dto){
        Book book  = new Book();
        book.setAuthor(new Author().setIdAuthor(dto.getIdAuthor()).setName(dto.getAuthorName()));
        book.setCategory(new Category().setIdCat(dto.getIdCategory()).setName(dto.getCategoryName()));
        book.setIdBook(dto.getIdBook());
        book.setPublisher(dto.getPublisher());
        book.setTitle(dto.getTitle());
        return book;
    }*/

    public List<BookDto> getBooks(List<BookGateway> books){
        List<BookDto> list = new ArrayList<>();
        for(BookGateway book : books){
            list.add(getBookDto(book));
        }
        return list;
    }
}
