package edu.uw.tcss.model;

import java.math.BigDecimal;

/**
 * Cart interface.
 *
 * @author Charles Bryam
 * @version Autumn 2024
 */
public interface Cart {

    /**
     * Adds an item to the cart.
     *
     * @param theOrder the Item Order.
     */
    void add(ItemOrder theOrder);

    /**
     * Sets the membership of the order.
     *
     * @param theMembership the boolean true or false of customer membership.
     */
    void setMembership(boolean theMembership);

    /**
     * Returns the total of the cart.
     *
     * @return BigDecimal total of the cart.
     */
    BigDecimal calculateTotal();

    /**
     * Clears the cart of all items.
     */
    void clear();

    /**
     * Returns the size of the cart as a Record.
     *
     * @return the size of the cart as a Record.
     */
    CartSize getCartSize();

    /**
     * A Record named CartSize.
     *
     * @param itemOrderCount the amount of item orders in the cart.
     * @param itemCount the amount of item types in the cart.
     */
    record CartSize(int itemOrderCount, int itemCount) { }
    // https://docs.oracle.com/en/java/javase/17/language/records.html
}