package ro.delivery.products.service;

import ro.delivery.products.dto.BookDto;

public interface IBookService {
    BookDto findBookDto(Integer bookId);
    void updateBook(BookDto bookDto);
    void insertBook(BookDto bookDto);
    void deleteBook(Integer id);
}
