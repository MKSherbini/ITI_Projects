package daoxml;

import jakarta.persistence.*;

import java.sql.Date;
import java.util.Objects;

@Entity
@Table(name = "buyer_buy_product", schema = "biddingschema", catalog = "")
@IdClass(BuyerBuyProductEntityPK.class)
public class BuyerBuyProductEntity {
    private int buyerId;
    private int productId;
    private Date paymentDate;
    private double amount;
    private int quantity;

    @Id
    @Column(name = "buyer_id")
    public int getBuyerId() {
        return buyerId;
    }

    public void setBuyerId(int buyerId) {
        this.buyerId = buyerId;
    }

    @Id
    @Column(name = "product_id")
    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    @Basic
    @Column(name = "payment_date")
    public Date getPaymentDate() {
        return paymentDate;
    }

    public void setPaymentDate(Date paymentDate) {
        this.paymentDate = paymentDate;
    }

    @Basic
    @Column(name = "amount")
    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    @Basic
    @Column(name = "quantity")
    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BuyerBuyProductEntity that = (BuyerBuyProductEntity) o;
        return buyerId == that.buyerId && productId == that.productId && Double.compare(that.amount, amount) == 0 && quantity == that.quantity && Objects.equals(paymentDate, that.paymentDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(buyerId, productId, paymentDate, amount, quantity);
    }
}
