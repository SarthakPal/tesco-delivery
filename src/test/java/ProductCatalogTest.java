package com.tesco.delivery.catalog;

import com.tesco.delivery.exception.ProductNotFoundException;
import com.tesco.delivery.model.Dimensions;
import com.tesco.delivery.model.Product;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class ProductCatalogTest {

    private final ProductCatalog catalog = new ProductCatalog(List.of(
            new Product(1, new Dimensions(2, 4, 10))));

    @Test
    void findsExistingProduct() {
        assertEquals(80L, catalog.getProduct(1).getVolume());
    }

    @Test
    void unknownProductThrows() {
        assertThrows(ProductNotFoundException.class, () -> catalog.getProduct(99));
    }

    @Test
    void rejectsDuplicateProductIds() {
        assertThrows(IllegalArgumentException.class, () -> new ProductCatalog(List.of(
                new Product(1, new Dimensions(1, 1, 1)),
                new Product(1, new Dimensions(2, 2, 2)))));
    }
}