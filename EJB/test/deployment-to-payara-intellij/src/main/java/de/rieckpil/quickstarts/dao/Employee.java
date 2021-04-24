package de.rieckpil.quickstarts.dao;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Transient;

import jakarta.json.bind.annotation.JsonbTransient;
import jakarta.ws.rs.core.Link;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlRootElement;
import jakarta.xml.bind.annotation.XmlTransient;
import jakarta.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import lombok.Data;
import lombok.ToString;

@NamedQueries({
        @NamedQuery(name = "Employee.findByName", query = "select e from Employee e where e.firstName = :name") })

@Data
// @XmlRootElement(name = "root")
// @XmlAccessorType(XmlAccessType.FIELD)
@Entity
@Table(name = "employees")
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(nullable = false)
    private String firstName;

    @ManyToOne
    @ToString.Exclude
    @JsonbTransient
    @XmlTransient
    Boss boss;

    @Transient
    private List<CustomLink> links = new ArrayList<>();

    public Employee() {
        this.id = -1;
        this.firstName = "dummy";
    }
}
