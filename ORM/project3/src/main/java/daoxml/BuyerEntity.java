package daoxml;

import jakarta.persistence.*;

import java.util.Objects;

@Entity
@Table(name = "buyer", schema = "biddingschema", catalog = "")
public class BuyerEntity {
    private int id;
    private String value;
    private int userId;

    @Id
    @Column(name = "id")
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "value")
    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    @Basic
    @Column(name = "user_id")
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
}
