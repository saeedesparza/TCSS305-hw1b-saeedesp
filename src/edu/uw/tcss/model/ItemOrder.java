package edu.uw.tcss.model;

/**
 * An item order interface.
 *
 * @author Charles Bryam
 * @version Autumn 2024
 */
public interface ItemOrder {

    /**
     * Returns the item.
     *
     * @return the Item.
     */
    Item getItem();

    /**
     * Returns the quantity of items.
     *
     * @return the quantity of items.
     */
    int getQuantity();
}