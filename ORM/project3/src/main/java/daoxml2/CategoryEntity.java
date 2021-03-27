package daoxml2;

import jakarta.persistence.*;

import java.util.Collection;
import java.util.Objects;

@Entity
@Table(name = "category", schema = "biddingschema", catalog = "")
public class CategoryEntity {
    private int id;
    private String value;
    private String description;
    private Collection<ProductHasCategoryEntity> productHasCategoriesById;

    @Id
    @Column(name = "id", nullable = false)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "value", nullable = false, length = 45)
    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    @Basic
    @Column(name = "description", nullable = true, length = 150)
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CategoryEntity that = (CategoryEntity) o;
        return id == that.id && Objects.equals(value, that.value) && Objects.equals(description, that.description);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, value, description);
    }

    @OneToMany(mappedBy = "categoryByCategoryId")
    public Collection<ProductHasCategoryEntity> getProductHasCategoriesById() {
        return productHasCategoriesById;
    }

    public void setProductHasCategoriesById(Collection<ProductHasCategoryEntity> productHasCategoriesById) {
        this.productHasCategoriesById = productHasCategoriesById;
    }
}
