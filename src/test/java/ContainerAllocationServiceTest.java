package com.tesco.delivery.service;

import com.tesco.delivery.catalog.ProductCatalog;
import com.tesco.delivery.exception.ProductNotFoundException;
import com.tesco.delivery.model.Container;
import com.tesco.delivery.model.Dimensions;
import com.tesco.delivery.model.Order;
import com.tesco.delivery.model.OrderLine;
import com.tesco.delivery.model.Product;
import com.tesco.delivery.strategy.SmallestFitByVolumeStrategy;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

class ContainerAllocationServiceTest {

    private final ContainerAllocationService service = new ContainerAllocationService(
            new ProductCatalog(List.of(
                    new Product(1, new Dimensions(2, 4, 10)),     // 80
                    new Product(2, new Dimensions(10, 30, 4)),    // 1,200
                    new Product(3, new Dimensions(5, 6, 7)))),    // 210
            List.of(
                    new Container(1, "SMALL", new Dimensions(10, 20, 30)),
                    new Container(2, "MEDIUM", new Dimensions(50, 60, 70)),
                    new Container(3, "LARGE", new Dimensions(100, 200, 300))),
            new SmallestFitByVolumeStrategy());

    @Test
    void exampleFromProblemStatementNeeds1710AndGetsSmall() {
        Order order = new Order(List.of(new OrderLine(1, 3), new OrderLine(3, 7)));
        assertEquals(1710L, service.calculateRequiredVolume(order));       // 3*80 + 7*210
        assertEquals("SMALL", service.findContainer(order).get().getName());
    }

    @Test
    void biggerOrderGetsMedium() {
        Order order = new Order(List.of(new OrderLine(2, 10)));            // 12,000
        assertEquals("MEDIUM", service.findContainer(order).get().getName());
    }

    @Test
    void tooBigOrderGetsNoContainer() {
        Order order = new Order(List.of(new OrderLine(2, 6000)));          // 7,200,000
        assertTrue(service.findContainer(order).isEmpty());
    }

    @Test
    void unknownProductInOrderThrows() {
        Order order = new Order(List.of(new OrderLine(42, 1)));
        assertThrows(ProductNotFoundException.class, () -> service.findContainer(order));
    }

    @Test
    void rejectsEmptyOrder() {
        assertThrows(IllegalArgumentException.class, () -> new Order(List.of()));
    }
}