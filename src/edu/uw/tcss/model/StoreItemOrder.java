package edu.uw.tcss.model;

import java.util.Objects;

/**
 * Represents an item order in a store.
 *
 * @author Saeed Esparza
 * @version Autumn 2024
 */
public final class StoreItemOrder implements ItemOrder {

    /**
     * The Item of the item order.
     */
    private final Item myItem;

    /**
     * The quantity of items in the item order.
     */
    private final int myQuantity;

    /**
     * Constructs a store item order using the item and quantity from user input.
     * Note, this method utilizes the Objects Java utility class and requires that
     * the Item is not null. By utilizing the Objects class, this method will not
     * have to redundantly use a second if statement to check whether or not the
     * parameter that can be null is null. More specifically, this is done like
     * so: Objects.requireNonNull(theParam).
     *
     * @param theItem the Item to be used in the store order.
     * @param theQuantity the quantity of items in the store order.
     */
    public StoreItemOrder(final Item theItem, final int theQuantity) {
        super();
        Objects.requireNonNull(theItem);
        if (theQuantity < 0) {
            throw new IllegalArgumentException();
        }
        myItem = theItem;
        myQuantity = theQuantity;
    }

    /**
     * This method gets the item of the store item order by returning the Item myItem
     * that the store item is stored in. This is done to use the data in other
     * methods such as the toString() method, and in other classes such as the
     * StoreCart class. Note, the name cannot be null as the StoreItemOrder()
     * constructor has already ensured the Item myItem cannot be null.
     *
     * @return the Item myItem that stores the Item of the store item.
     */
    @Override
    public Item getItem() {
        return myItem;
    }

    /**
     * This method gets the quantity of the store item by returning the int
     * myQuantity. This is done to use the data in other methods such as the toString()
     * method, and in other classes such as the StoreCart class. Note, the quantity
     * cannot be negative as the StoreItemOrder() constructor has already ensured that
     * the int myQuantity cannot be negative.
     *
     * @return the int myQuantity that stores the quantity of items of the store item.
     */
    @Override
    public int getQuantity() {
        return myQuantity;
    }

    /**
     * This method returns the name and price of a non-bulk store item
     * and returns the name, price, bulk quantity, and bulk price for a
     * bulk store item as a String. Note, this method utilizes the storeItem
     * class to retrieve the necessary information to return the String. Also,
     * a local variable of type StringBuilder is used to create and append
     * the necessary String and information associated with the Item.
     *
     * @return the StringBuilder sb with the store item information.
     */
    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder();
        sb.append(getItem().toString());
        sb.append(", Quantity: ");
        sb.append(getQuantity());
        return sb.toString();
    }
}