package kata.supermarket;

import org.junit.Test;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.List;

import static java.util.Arrays.asList;
import static org.junit.Assert.assertEquals;

public class DiscountCalculatorTest {


    @Test
    public void calculateDiscount_noItems_returnZero() {
        //GIVEN
        List<Item> items = Collections.emptyList();


        //WHEN
        DiscountCalculator discountCalculator = new DiscountCalculator();
        BigDecimal discount = discountCalculator.calculateDiscount(items);

        //THEN
        assertEquals("0.00", discount.toString());
    }

    @Test
    public void calculateDiscount_multipleItemsWithNoDiscount_returnZero() {
        //GIVEN
        List<Item> items = asList(
                new Product(BigDecimal.ONE).oneOf(),
                new Product(BigDecimal.ONE).oneOf()
        );

        //WHEN
        DiscountCalculator discountCalculator = new DiscountCalculator();
        BigDecimal discount = discountCalculator.calculateDiscount(items);

        //THEN
        assertEquals("0.00", discount.toString());
    }

    @Test
    public void calculateDiscount_multipleItemsWithOneItemWithBuyOneGetOneFreeDiscount_returnCorrectDiscount() {
        //GIVEN
        Item itemWithDiscount = new Product(BigDecimal.ONE).oneOf();
        itemWithDiscount.setDiscount(Discount.BUY_ONE_GET_ONE_FREE);

        List<Item> items = asList(
                itemWithDiscount,
                itemWithDiscount,
                new Product(BigDecimal.TEN).oneOf()
        );

        //WHEN
        DiscountCalculator discountCalculator = new DiscountCalculator();
        BigDecimal discount = discountCalculator.calculateDiscount(items);

        //THEN
        assertEquals("1.00", discount.toString());
    }
}
