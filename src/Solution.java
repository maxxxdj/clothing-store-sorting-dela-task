import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicLong;
import java.util.function.Function;
import java.util.stream.Collectors;

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
    public class Jeans extends Product {
        String denimType;
    }
    public class Sweater extends Product {
        boolean isLongSleeved;
    }
    public static void main(String[] args) {

        List<Product> productList = List.of(new Product(new AtomicLong(1),50,true,Season.WINTER));

        Map<AtomicLong, Product> orderedProducts =
        productList.stream()
                .filter(el -> el.price > 10)
                .sorted(Comparator.comparing(Product::getSeason,
                        Comparator.comparing(Enum::toString))
                        .thenComparing(Product::isHighlighted))
                .collect(Collectors.toMap(Product::getId, Function.identity()));

    }
}
