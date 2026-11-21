package com.tesco.delivery.catalog;

import com.tesco.delivery.exception.ProductNotFoundException;
import com.tesco.delivery.model.Product;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Read-only lookup of products by id. Built once, shared by all requests.
 */
public final class ProductCatalog {

    private final Map<Integer, Product> productsById;

    public ProductCatalog(List<Product> products) {
        if (products == null) {
            throw new IllegalArgumentException("products must not be null");
        }
        Map<Integer, Product> map = new HashMap<>();
        for (Product product : products) {
            if (product == null) {
                throw new IllegalArgumentException("products must not contain null");
            }
            if (map.put(product.getProductId(), product) != null) {
                throw new IllegalArgumentException("Duplicate productId: " + product.getProductId());
            }
        }
        this.productsById = Map.copyOf(map);
    }

    /** O(1) lookup. Throws if the product does not exist, instead of returning null. */
    public Product getProduct(int productId) {
        Product product = productsById.get(productId);
        if (product == null) {
            throw new ProductNotFoundException(productId);
        }
        return product;
    }
}