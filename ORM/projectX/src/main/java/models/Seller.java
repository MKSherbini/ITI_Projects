package models;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Set;

@Entity
@Data
public class Seller {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String firstName;
    private String lastName;

    @ElementCollection
    @JoinColumn(name = "joinCol")
    private Set<Customer> customer;

    protected Seller() {
    }

    public Seller(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }
}