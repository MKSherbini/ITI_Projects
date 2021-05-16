//package models;
//
//import lombok.Data;
//
//import javax.persistence.*;
//
//@Entity
//@Data
//@SecondaryTable(name = "secondTable")
//public class Customer2 {
//
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private Long id;
//    @Column(table = "secondTable")
//    private String firstName;
//    private String lastName;
//
////    @ManyToMany
////            (mappedBy = "customer")
////    @JoinColumn(name = "joinCol")
//
////    private Set<Seller> seller;
//
//    protected Customer2() {
//    }
//
//    public Customer2(String firstName, String lastName) {
//        this.firstName = firstName;
//        this.lastName = lastName;
//    }
//}