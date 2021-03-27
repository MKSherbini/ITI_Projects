import java.util.Date;

public class AbstractBuyedProduct {
    String name;
    int size;
    Date manufacturingDate;

    public AbstractBuyedProduct(String name, int size, Date manufacturingDate) {
        this.name = name;
        this.size = size;
        this.manufacturingDate = manufacturingDate;
    }
}
