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
        int price;
        boolean isHighlighted;
        Season season;
    }
    public static void main(String[] args) {
        final AtomicLong idGenerator = new AtomicLong(1);

        List<Product> productList = List.of(new Product(
                new AtomicLong(idGenerator.getAndIncrement()),50,true, Season.WINTER),
                new Product(new AtomicLong(idGenerator.getAndIncrement()),1,true, Season.SPRING),
                new Product(new AtomicLong(idGenerator.getAndIncrement()),23,true, Season.WINTER),
                new Product(new AtomicLong(idGenerator.getAndIncrement()),15,false, Season.WINTER),
                new Product(new AtomicLong(idGenerator.getAndIncrement()),56,true, Season.SUMMER),
                new Product(new AtomicLong(idGenerator.getAndIncrement()),12,true, Season.AUTUMN),
                new Product(new AtomicLong(idGenerator.getAndIncrement()),40,true, Season.SUMMER));

        List<Product> orderedProducts =
        productList.stream()
                .filter(el -> el.price > 10)
                .sorted(Comparator.comparing(Product::getSeason, Comparator.comparing(Enum::toString)).reversed()
                        .thenComparing(el -> !el.isHighlighted))
                .toList();
    }
}
