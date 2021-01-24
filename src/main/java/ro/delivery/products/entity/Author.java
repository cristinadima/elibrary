package ro.delivery.products.entity;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "authors")
@FieldDefaults(level= AccessLevel.PRIVATE)
public class Author {


    @Id
    @Column(name = "id_author")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_authors_gen")
    @SequenceGenerator(name = "seq_authors_gen", allocationSize = 1, sequenceName = "seq_authors")
    Integer idAuthor;

    String name;
    String nationality;
}
