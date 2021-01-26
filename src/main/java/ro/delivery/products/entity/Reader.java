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
@Table(name = "readers")
@FieldDefaults(level= AccessLevel.PRIVATE)
public class Reader {

    @Id
    @Column(name = "id_reader")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_readers_gen")
    @SequenceGenerator(name = "seq_readers_gen", allocationSize = 1, sequenceName = "seq_readers")
    Integer idReader;

    String name;
    String birthday;
    String address;
    String phone;
}
