import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.*;
import java.util.concurrent.atomic.AtomicLong;
public class Solution {
    enum Season{
        WINTER,
        SUMMER,
        SPRING,
        AUTUMN;

    }
    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class Product{
        AtomicLong id;
        double price;
        boolean isHighlighted;
        Season season;
    }
    public static void main(String[] args) {
        final AtomicLong idGenerator = new AtomicLong(1);

        List<Product> productList = List.of(new Product(
                        new AtomicLong(idGenerator.getAndIncrement()), 53.00, true, Season.WINTER),
                new Product(new AtomicLong(idGenerator.getAndIncrement()), 14.00, true, Season.SPRING),
                new Product(new AtomicLong(idGenerator.getAndIncrement()), 5.70, true, Season.WINTER),
                new Product(new AtomicLong(idGenerator.getAndIncrement()), 15.00, false, Season.WINTER),
                new Product(new AtomicLong(idGenerator.getAndIncrement()), 56.00, true, Season.SUMMER),
                new Product(new AtomicLong(idGenerator.getAndIncrement()), 2.00, true, Season.AUTUMN),
                new Product(new AtomicLong(idGenerator.getAndIncrement()), 40.00, true, Season.SUMMER));

        /**Getting all the products sorted by season, then by highlighted.*/
        final List<Product> orderedProducts =
                productList.stream()
                        .filter(el -> el.price > 9.99)
                        .sorted(Comparator.comparing(Product::getSeason,
                                        Comparator.comparing(el -> el.toString().equalsIgnoreCase("WINTER")))
                                                .thenComparing(el->el.isHighlighted).reversed())
                        .toList();

        orderedProducts.forEach(System.out::println);
    }
}
