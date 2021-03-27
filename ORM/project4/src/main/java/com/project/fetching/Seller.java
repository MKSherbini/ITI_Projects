package com.project.fetching;// default package
// Generated Mar 20, 2021, 2:25:48 PM by Hibernate Tools 6.0.0.Alpha2


import org.hibernate.annotations.*;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.Parameter;

import javax.persistence.*;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.HashSet;
import java.util.Set;

/**
 * Seller generated by hbm2java
 */
@Entity
@Table(name = "seller"
        , catalog = "fetch"
)
@Cacheable
@Cache(usage=CacheConcurrencyStrategy.READ_ONLY)
public class Seller implements java.io.Serializable {
    private int id;
    private String value;
    private Set<Product> products = new HashSet<Product>(0);

    public Seller() {
        value = "test";
    }


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    @Column(name = "id", unique = true, nullable = false)
    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }


    @Column(name = "value", nullable = false, length = 100)
    public String getValue() {
        return this.value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "seller")
//    @Fetch(FetchMode.SUBSELECT)
//    @BatchSize(size = 5)
//    @Fetch(FetchMode.JOIN)
//    @OneToMany(fetch = FetchType.EAGER, mappedBy = "seller")
    public Set<Product> getProducts() {
        return this.products;
    }

    public void setProducts(Set<Product> products) {
        this.products = products;
    }
}

