package models;

import lombok.Data;

import javax.persistence.*;
import java.nio.channels.Selector;
import java.util.Set;

//@Entity
@Embeddable
@Data
public class Customer {

//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private Long id;
    private String firstName;
    private String lastName;

//    @ManyToMany
//            (mappedBy = "customer")
//    @JoinColumn(name = "joinCol")

//    private Set<Seller> seller;

    protected Customer() {
    }

    public Customer(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }
}