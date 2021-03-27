package daoxml;

import jakarta.persistence.*;

import java.util.Objects;

@Entity
@Table(name = "product_has_category", schema = "biddingschema", catalog = "")
@IdClass(ProductHasCategoryEntityPK.class)
public class ProductHasCategoryEntity {
    private int productId;
    private int categoryId;

    @Id
    @Column(name = "product_id")
    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    @Id
    @Column(name = "category_id")
    public int getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ProductHasCategoryEntity that = (ProductHasCategoryEntity) o;
        return productId == that.productId && categoryId == that.categoryId;
    }

    @Override
    public int hashCode() {
        return Objects.hash(productId, categoryId);
    }
}
