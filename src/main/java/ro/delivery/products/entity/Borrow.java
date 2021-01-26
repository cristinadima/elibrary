package ro.delivery.products.entity;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;

import javax.persistence.*;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "borrows")
@FieldDefaults(level= AccessLevel.PRIVATE)
public class Borrow {

    @Id
    @Column(name = "id_borrow")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_borrows_gen")
    @SequenceGenerator(name = "seq_borrows_gen", allocationSize = 1, sequenceName = "seq_borrows")
    Integer idBorrow;

    String returnDate;

    @ManyToOne
    @NotFound(action = NotFoundAction.IGNORE)
    @JoinColumn(name = "id_reader")
    Reader reader;

    @ManyToOne
    @NotFound(action = NotFoundAction.IGNORE)
    @JoinColumn(name = "id_book")
    Book book;
}
