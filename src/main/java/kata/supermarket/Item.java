package kata.supermarket;

import java.math.BigDecimal;

public abstract class Item {
    protected Discount discount;

    abstract BigDecimal price();

    /*
        TODO: if an Item is eligible to multiple discounts, this could be a list
     */
    protected Discount getDiscount() {
        return this.discount;
    }

    /*
        TODO: if an Item is eligible to multiple discounts, this could be a list
     */
    protected void setDiscount(Discount discount) {
        this.discount = discount;
    }
}
