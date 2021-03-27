package daoxml2;

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
    private BuyerEntity buyerByBuyerId;
    private ProductEntity productByProductId;

    @Id
    @Column(name = "buyer_id", nullable = false)
    public int getBuyerId() {
        return buyerId;
    }

    public void setBuyerId(int buyerId) {
        this.buyerId = buyerId;
    }

    @Id
    @Column(name = "product_id", nullable = false)
    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    @Basic
    @Column(name = "payment_date", nullable = false)
    public Date getPaymentDate() {
        return paymentDate;
    }

    public void setPaymentDate(Date paymentDate) {
        this.paymentDate = paymentDate;
    }

    @Basic
    @Column(name = "amount", nullable = false, precision = 0)
    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    @Basic
    @Column(name = "quantity", nullable = false)
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

    @ManyToOne
    @JoinColumn(name = "buyer_id", referencedColumnName = "id", nullable = false)
    public BuyerEntity getBuyerByBuyerId() {
        return buyerByBuyerId;
    }

    public void setBuyerByBuyerId(BuyerEntity buyerByBuyerId) {
        this.buyerByBuyerId = buyerByBuyerId;
    }

    @ManyToOne
    @JoinColumn(name = "product_id", referencedColumnName = "id", nullable = false)
    public ProductEntity getProductByProductId() {
        return productByProductId;
    }

    public void setProductByProductId(ProductEntity productByProductId) {
        this.productByProductId = productByProductId;
    }
}
