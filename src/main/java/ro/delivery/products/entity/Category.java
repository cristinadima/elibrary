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
@Table(name = "categories")
@FieldDefaults(level= AccessLevel.PRIVATE)
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_categories_gen")
    @SequenceGenerator(name = "seq_categories_gen", allocationSize = 1, sequenceName = "seq_categories")
    Integer idCat;

    String name;
    String description;
}
