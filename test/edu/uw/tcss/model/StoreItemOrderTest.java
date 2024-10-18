package edu.uw.tcss.model;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.math.BigDecimal;
import org.junit.jupiter.api.Test;

class StoreItemOrderTest {
    /**
     * The name of the item.
     */
    private static final String ITEM_NAME = "Item";
    /**
     * The price of the item.
     */
    private static final BigDecimal ITEM_PRICE = new BigDecimal(10);
    /**
     * The quantity of the item.
     */
    private static final int QUANTITY = 1;
    /**
     * The zero quantity of the item.
     */
    private static final int ZERO_QUANTITY = 0;
    /**
     * The item object.
     */
    private static final Item ITEM = new StoreItem(ITEM_NAME, ITEM_PRICE);
    /**
     * The store item order.
     */
    private static final StoreItemOrder STORE_ITEM = new StoreItemOrder(ITEM, QUANTITY);
    /**
     * The store item order with a quantity of zero.
     */
    private static final StoreItemOrder STORE_ITEM_ZERO =
            new StoreItemOrder(ITEM, ZERO_QUANTITY);

    @Test
    void testConstructorNegativeOrderQuantity() {
        assertThrows(IllegalArgumentException.class,
                () -> new StoreItemOrder(ITEM, -1),
                "Constructor does not handle negative ints "
                        + "as an argument to theQuantity properly.");
    }

    @Test
    void testConstructorNullItem() {
        assertThrows(NullPointerException.class,
                () -> new StoreItemOrder(null, QUANTITY),
                "Constructor does not handle null Items "
                        + "as an argument to theItem properly.");
    }

    @Test
    void testConstructorQuantityZero() {
        assertAll("Valid arguments to constructor with zero quantity.",
                () -> assertEquals(ITEM, STORE_ITEM_ZERO.getItem(),
                        "The item should be: " + ITEM),
                () -> assertEquals(ZERO_QUANTITY, STORE_ITEM_ZERO.getQuantity(),
                        "The item quantity should be zero."));
    }

    @Test
    void testConstructorValidArguments() {
        assertAll("Valid arguments to constructor.",
                () -> assertEquals(ITEM, STORE_ITEM.getItem(),
                        "Item should be: " + STORE_ITEM),
                () -> assertEquals(QUANTITY, STORE_ITEM.getQuantity(),
                        "Item quantity should be: " + QUANTITY));
    }

    @Test
    void testGetItem() {
        assertEquals(ITEM, STORE_ITEM.getItem(),
                "Item should be: " + ITEM_NAME + ", $" + ITEM_PRICE);
    }

    @Test
    void testGetQuantity() {
        assertEquals(QUANTITY, STORE_ITEM.getQuantity(),
                "Item should be: " + QUANTITY);
    }

    @Test
    void testToString() {
        assertEquals("Item, $" + ITEM_PRICE + ", Quantity: "
                        + QUANTITY, STORE_ITEM.toString(),
                "toString() should be: \"Item, $10, Quantity: 1\"");
    }
}