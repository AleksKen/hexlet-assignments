package exercise.model;



import static jakarta.persistence.GenerationType.IDENTITY;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.GeneratedValue;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

// BEGIN
@Entity
@Table(name = "persons")
@Setter
@Getter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Person {
    @Id
    @GeneratedValue(strategy = IDENTITY)
    @EqualsAndHashCode.Include
    private Long id;

    private String firstName;
    private String lastName;
}
// END
