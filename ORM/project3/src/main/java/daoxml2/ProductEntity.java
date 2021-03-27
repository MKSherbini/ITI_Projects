package daoxml2;

import jakarta.persistence.*;

import java.sql.Date;
import java.util.Collection;
import java.util.Objects;

@Entity
@Table(name = "product", schema = "biddingschema", catalog = "")
public class ProductEntity {
    private int id;
    private String name;
    private String description;
    private String manufacturingName;
    private Date manufacturingDate;
    private Date expirationDate;
    private Integer sellerId;
    private int quantity;
    private Date offeredDate;
    private Date finishDate;
    private Collection<BuyerBidProductEntity> buyerBidProductsById;
    private Collection<BuyerBuyProductEntity> buyerBuyProductsById;
    private SellerEntity sellerBySellerId;
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
    @Column(name = "name", nullable = false, length = 45)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Basic
    @Column(name = "description", nullable = true, length = 150)
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Basic
    @Column(name = "manufacturing_name", nullable = false, length = 100)
    public String getManufacturingName() {
        return manufacturingName;
    }

    public void setManufacturingName(String manufacturingName) {
        this.manufacturingName = manufacturingName;
    }

    @Basic
    @Column(name = "manufacturing_date", nullable = false)
    public Date getManufacturingDate() {
        return manufacturingDate;
    }

    public void setManufacturingDate(Date manufacturingDate) {
        this.manufacturingDate = manufacturingDate;
    }

    @Basic
    @Column(name = "expiration_date", nullable = true)
    public Date getExpirationDate() {
        return expirationDate;
    }

    public void setExpirationDate(Date expirationDate) {
        this.expirationDate = expirationDate;
    }

    @Basic
    @Column(name = "seller_id", nullable = true)
    public Integer getSellerId() {
        return sellerId;
    }

    public void setSellerId(Integer sellerId) {
        this.sellerId = sellerId;
    }

    @Basic
    @Column(name = "quantity", nullable = false)
    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    @Basic
    @Column(name = "offered_date", nullable = false)
    public Date getOfferedDate() {
        return offeredDate;
    }

    public void setOfferedDate(Date offeredDate) {
        this.offeredDate = offeredDate;
    }

    @Basic
    @Column(name = "finish_date", nullable = false)
    public Date getFinishDate() {
        return finishDate;
    }

    public void setFinishDate(Date finishDate) {
        this.finishDate = finishDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ProductEntity that = (ProductEntity) o;
        return id == that.id && quantity == that.quantity && Objects.equals(name, that.name) && Objects.equals(description, that.description) && Objects.equals(manufacturingName, that.manufacturingName) && Objects.equals(manufacturingDate, that.manufacturingDate) && Objects.equals(expirationDate, that.expirationDate) && Objects.equals(sellerId, that.sellerId) && Objects.equals(offeredDate, that.offeredDate) && Objects.equals(finishDate, that.finishDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, description, manufacturingName, manufacturingDate, expirationDate, sellerId, quantity, offeredDate, finishDate);
    }

    @OneToMany(mappedBy = "productByProductId")
    public Collection<BuyerBidProductEntity> getBuyerBidProductsById() {
        return buyerBidProductsById;
    }

    public void setBuyerBidProductsById(Collection<BuyerBidProductEntity> buyerBidProductsById) {
        this.buyerBidProductsById = buyerBidProductsById;
    }

    @OneToMany(mappedBy = "productByProductId")
    public Collection<BuyerBuyProductEntity> getBuyerBuyProductsById() {
        return buyerBuyProductsById;
    }

    public void setBuyerBuyProductsById(Collection<BuyerBuyProductEntity> buyerBuyProductsById) {
        this.buyerBuyProductsById = buyerBuyProductsById;
    }

    @ManyToOne
    @JoinColumn(name = "seller_id", referencedColumnName = "id")
    public SellerEntity getSellerBySellerId() {
        return sellerBySellerId;
    }

    public void setSellerBySellerId(SellerEntity sellerBySellerId) {
        this.sellerBySellerId = sellerBySellerId;
    }

    @OneToMany(mappedBy = "productByProductId")
    public Collection<ProductHasCategoryEntity> getProductHasCategoriesById() {
        return productHasCategoriesById;
    }

    public void setProductHasCategoriesById(Collection<ProductHasCategoryEntity> productHasCategoriesById) {
        this.productHasCategoriesById = productHasCategoriesById;
    }
}
