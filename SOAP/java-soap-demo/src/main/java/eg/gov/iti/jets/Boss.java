package eg.gov.iti.jets;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlRootElement;
import lombok.Data;

@NamedQueries({
        @NamedQuery(name = "Boss.findByName", query = "select b from Boss b where b.firstName = :name") })

@Data
// @XmlRootElement(name = "root")
// @XmlAccessorType(XmlAccessType.FIELD)
@Entity
@Table(name = "bosses")
public class Boss {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(nullable = false)
    private String firstName;

    @OneToMany(mappedBy = "boss")
    private List<Employee> employees = new LinkedList<>();

    public Boss() {
        this.id = -1;
        this.firstName = "dummy";
    }
}
