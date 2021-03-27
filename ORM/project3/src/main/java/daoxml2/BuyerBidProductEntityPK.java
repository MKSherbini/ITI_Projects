package daoxml2;

import jakarta.persistence.Column;
import jakarta.persistence.Id;

import java.io.Serializable;
import java.util.Objects;

public class BuyerBidProductEntityPK implements Serializable {
    private int buyerId;
    private int productId;

    @Column(name = "buyer_id", nullable = false)
    @Id
    public int getBuyerId() {
        return buyerId;
    }

    public void setBuyerId(int buyerId) {
        this.buyerId = buyerId;
    }

    @Column(name = "product_id", nullable = false)
    @Id
    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BuyerBidProductEntityPK that = (BuyerBidProductEntityPK) o;
        return buyerId == that.buyerId && productId == that.productId;
    }

    @Override
    public int hashCode() {
        return Objects.hash(buyerId, productId);
    }
}
