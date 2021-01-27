package ro.delivery.products.dto;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;


@Data
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level= AccessLevel.PRIVATE)
public class BookDto {

    Integer idBook;
    String title;
    Integer idCategory;
    String categoryName;
    Integer idAuthor;
    String authorName;
    String publisher;

}
