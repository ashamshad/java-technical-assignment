package kata.supermarket;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import static java.util.stream.Collectors.groupingBy;

public class DiscountCalculator {

    private static BigDecimal ZERO = BigDecimal.ZERO.setScale(2, RoundingMode.HALF_UP);
    private List<DiscountRule> applicableDiscounts = Collections.unmodifiableList(Collections.singletonList(new BuyOneGetOneFreeRule()));

    BigDecimal calculateDiscount(List<Item> items) {
        return applicableDiscounts.stream()
                .map(d -> d.apply(items))
                .reduce(BigDecimal::add)
                .orElse(ZERO)
                .setScale(2, RoundingMode.HALF_UP);
    }


    private interface DiscountRule {
        BigDecimal apply(List<Item> items);
    }

    private static class BuyOneGetOneFreeRule implements DiscountRule {

        @Override
        public BigDecimal apply(List<Item> items) {
            Map<BigDecimal, List<Item>> itemsPerPriceMap = items.stream()
                    .filter(item -> item.getDiscount() == Discount.BUY_ONE_GET_ONE_FREE)
                    .collect(groupingBy(Item::price));

            return itemsPerPriceMap.entrySet().stream()
                    .filter(entry -> entry.getValue().size() == 2)
                    .map(Map.Entry::getKey)
                    .reduce(BigDecimal::add)
                    .orElse(ZERO)
                    .setScale(2, RoundingMode.HALF_UP);
        }
    }
}

