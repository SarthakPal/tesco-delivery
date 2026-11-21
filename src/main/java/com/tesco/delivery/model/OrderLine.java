package com.tesco.delivery.model;

/**
 * One line of an order: which product and how many.
 */
public final class OrderLine {

    private final int productId;
    private final int quantity;

    public OrderLine(int productId, int quantity) {
        if (quantity <= 0) {
            throw new IllegalArgumentException("quantity must be positive, was " + quantity);
        }
        this.productId = productId;
        this.quantity = quantity;
    }

    public int getProductId() {
        return productId;
    }

    public int getQuantity() {
        return quantity;
    }

    @Override
    public String toString() {
        return "OrderLine{productId=" + productId + ", quantity=" + quantity + "}";
    }
}