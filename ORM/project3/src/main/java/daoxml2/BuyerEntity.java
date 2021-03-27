package daoxml2;

import jakarta.persistence.*;

import java.util.Collection;
import java.util.Objects;

@Entity
@Table(name = "buyer", schema = "biddingschema", catalog = "")
public class BuyerEntity {
    private int id;
    private String value;
    private int userId;
    private UserEntity userByUserId;
    private Collection<BuyerBidProductEntity> buyerBidProductsById;
    private Collection<BuyerBuyProductEntity> buyerBuyProductsById;

    @Id
    @Column(name = "id", nullable = false)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "value", nullable = false, length = 150)
    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    @Basic
    @Column(name = "user_id", nullable = false)
    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BuyerEntity that = (BuyerEntity) o;
        return id == that.id && userId == that.userId && Objects.equals(value, that.value);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, value, userId);
    }

    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id", nullable = false)
    public UserEntity getUserByUserId() {
        return userByUserId;
    }

    public void setUserByUserId(UserEntity userByUserId) {
        this.userByUserId = userByUserId;
    }

    @OneToMany(mappedBy = "buyerByBuyerId")
    public Collection<BuyerBidProductEntity> getBuyerBidProductsById() {
        return buyerBidProductsById;
    }

    public void setBuyerBidProductsById(Collection<BuyerBidProductEntity> buyerBidProductsById) {
        this.buyerBidProductsById = buyerBidProductsById;
    }

    @OneToMany(mappedBy = "buyerByBuyerId")
    public Collection<BuyerBuyProductEntity> getBuyerBuyProductsById() {
        return buyerBuyProductsById;
    }

    public void setBuyerBuyProductsById(Collection<BuyerBuyProductEntity> buyerBuyProductsById) {
        this.buyerBuyProductsById = buyerBuyProductsById;
    }
}
