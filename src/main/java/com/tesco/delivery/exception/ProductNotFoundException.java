package com.tesco.delivery.exception;

/**
 * Thrown when an order refers to a product that is not in the catalogue.
 */
public class ProductNotFoundException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    private final int productId;

    public ProductNotFoundException(int productId) {
        super("Product not found in catalogue: " + productId);
        this.productId = productId;
    }

    public int getProductId() {
        return productId;
    }
}