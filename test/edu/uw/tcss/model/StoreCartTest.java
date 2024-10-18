package edu.uw.tcss.model;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.math.BigDecimal;
import java.math.RoundingMode;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class StoreCartTest {
    /**
     * The name of the item.
     */
    private static final String ITEM_NAME = "Item";
    /**
     * The price of the item.
     */
    private static final BigDecimal ITEM_PRICE = BigDecimal.valueOf(10.01);
    /**
     *
     */
    private static final BigDecimal BULK_PRICE = BigDecimal.valueOf(5.01);
    /**
     * The quantity of the item.
     */
    private static final int QUANTITY = 1;
    /**
     *
     */
    private static final int BULK_QUANTITY = 6;
    /**
     * The store item object.
     */
    private static final Item ITEM = new StoreItem(ITEM_NAME, ITEM_PRICE);
    /**
     *
     */
    private static final Item BULK_ITEM = new StoreItem(ITEM_NAME, ITEM_PRICE,
            BULK_QUANTITY, BULK_PRICE);
    /**
     * The boolean representation of customer membership.
     */
    private static final boolean ORDER_MEMBER = false;
    /**
     * The store cart total.
     */
    private static final BigDecimal TOTAL = BigDecimal.ZERO;
    /**
     *
     */
    private StoreCart myCart;
    /**
     *
     */
    private ItemOrder myOrder;
    /**
     *
     */
    private ItemOrder myOrderZero;
    /**
     *
     */
    private ItemOrder myBulkOrder;
    /**
     *
     */
    private Cart.CartSize myCartOrderA;

    /**
     *
     */
    private Cart.CartSize myCartOrderB;

    @BeforeEach
    void setUp() {
        myCart = new StoreCart();
        myOrder = new StoreItemOrder(ITEM, QUANTITY);
        myOrderZero = new StoreItemOrder(ITEM, 0);
        myBulkOrder = new StoreItemOrder(BULK_ITEM, BULK_QUANTITY);
        myCartOrderA = new Cart.CartSize(1, 1);
        myCartOrderB = new Cart.CartSize(0, 0);
    }

    @Test
    void testAdd() {
        myCart.add(myOrder);
        assertEquals(myCartOrderA, myCart.getCartSize(),
                "Quantity should be: " + myCartOrderA);
    }

    @Test
    void testSetMembershipFalse() {
        myCart.setMembership(false);
        myCart.add(myBulkOrder);
        final BigDecimal totalNotMember = myCart.calculateTotal();
        assertEquals(totalNotMember, myCart.calculateTotal(),
                "Total should be: " + totalNotMember);
    }

    @Test
    void testSetMembershipTrue() {
        myCart.setMembership(true);
        myCart.add(myBulkOrder);
        final BigDecimal totalMember = myCart.calculateTotal();
        assertEquals(totalMember, myCart.calculateTotal(),
                "Total should be: " + totalMember);
    }

    @Test
    void testCalculateTotal() {
        myCart.add(myOrder);
        final BigDecimal total = ITEM_PRICE.multiply(BigDecimal.valueOf(QUANTITY));
        assertEquals(total.setScale(2, RoundingMode.HALF_UP), myCart.calculateTotal(),
                "Total should be: " + total.setScale(2, RoundingMode.HALF_UP));
    }

    @Test
    void testClear() {
        myCart.add(myOrder);
        myCart.clear();
        assertEquals(myCartOrderB, myCart.getCartSize(),
                "Cart size should be: " + myCartOrderB);
    }

    @Test
    void testGetCartSize() {
        myCart.add(myOrder);
        assertEquals(myCartOrderA, myCart.getCartSize(),
                "Cart size should be: " + myCartOrderA);
    }

    @Test
    void testToString() {
        myCart.add(myOrder);
        final String string = ITEM_NAME + ", Quantity: " + QUANTITY + ", Price: $"
                + ITEM_PRICE.setScale(2, RoundingMode.HALF_UP);
        assertEquals(string, myCart.toString(),
                "String should be: " + string);
    }

    @Test
    void testToStringBulkCart() {
        myCart.add(myBulkOrder);
        myCart.setMembership(true);
        final String string = ITEM_NAME + ", Quantity: " + BULK_QUANTITY + ", Price: $"
                + ITEM_PRICE.setScale(2, RoundingMode.HALF_UP) + ", (Bulk Quantity: "
                + BULK_QUANTITY + " for $" + BULK_PRICE + ")";
        assertEquals(string, myCart.toString(),
                "String should be: " + string);
    }

    @Test
    void testGetCartSizeItems() {
        myCart.add(myOrder);
        myCart.add(myBulkOrder);
        final Cart.CartSize fin = new Cart.CartSize(2, QUANTITY + BULK_QUANTITY);
        assertEquals(fin, myCart.getCartSize(),
                "Cart size should be: " + fin);
    }

    @Test
    void testGetCartSizeZeroQuantity() {
        myCart.add(myOrderZero);
        assertEquals(myCartOrderB, myCart.getCartSize(),
                "Cart size should be: " + myCartOrderB);
    }
}