package edu.uw.tcss.model;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.HashMap;
import java.util.Map;

/**
 * Represents a cart in the store.
 *
 * @author Saeed Esparza
 * @version Autumn 2024
 */
public class StoreCart implements Cart {

    /**
     * The Map of the cart with Item as a key and an Integer for quantity.
     */
    private final Map<Item, Integer> myCart;

    /**
     * The boolean representation of customer membership.
     */
    private boolean myMember;

    /**
     * Constructs a new store cart for use in the calculation of the
     * customer's order.
     */
    public StoreCart() {
        super();
        myCart = new HashMap<>();
        myMember = false;
    }

    /**
     * This method puts an ItemOrder in the cart myCart as a key and
     * utilizes the quantity of the ItemOrder as the value of the key.
     * Note, this method utilizes the put() method for the Map collection,
     * effectively replacing any prior quantity of the ItemOrder key with
     * the new quantity.
     *
     * @param theOrder the ItemOrder to be used as a key in myCart.
     */
    @Override
    public void add(final ItemOrder theOrder) {
        myCart.put(theOrder.getItem(), theOrder.getQuantity());
    }

    /**
     * This method sets the membership of the current customer by
     * utilizing user input in the GUI checkbox and setting the
     * instance varible myMember equal to theMembership.
     *
     * @param theMembership the boolean representation of customer membership.
     */
    @Override
    public void setMembership(final boolean theMembership) {
        myMember = theMembership;
    }

    /**
     * This method calculates the total price of the StoreItemOrder
     * by utilizing each Map entry in myCart and examining it's keys
     * (quantities) and conducting BigDecimal math with the data.
     * Note, this method checks for customer membership and applies
     * appropriate bulk pricing and quantity options available to
     * customers whom fall under the membership program.
     */
    @Override
    public BigDecimal calculateTotal() {
        BigDecimal total = BigDecimal.ZERO;
        for (final Map.Entry<Item, Integer> ele : myCart.entrySet()) {
            final Item item = ele.getKey();
            final int quantity = ele.getValue();
            final BigDecimal thisTotal;
            if (item.isBulk() && myMember) {
                final int bulkQuantity = item.getBulkQuantity();
                final BigDecimal bulkPrice = item.getBulkPrice();
                final int isBulk = quantity / bulkQuantity;
                final int bulkRemainder = quantity % bulkQuantity;
                thisTotal = bulkPrice.multiply(BigDecimal.valueOf(isBulk)).
                        add(item.getPrice().multiply(BigDecimal.valueOf(bulkRemainder)));
            } else {
                thisTotal = item.getPrice().multiply(BigDecimal.valueOf(quantity));
            }
            total = total.add(thisTotal);
        }
        return total.setScale(2, RoundingMode.HALF_EVEN);
    }

    /**
     * This method clears the store cart of any and all Items and
     * their quantities.
     */
    @Override
    public void clear() {
        myCart.clear();
    }

    /**
     * This method calculates the cart size of the store cart by
     * utilizing the keys and values located in myCart. This is
     * done by checking if the value (quantity) of the key (item)
     * is greater than zero, as a value equal to or less than zero
     * would result in zero. Note, this method utilizes two local
     * varibales of type int to track the count of items and the
     * total of each item it contains. The instance varibles are
     * set to equal zero (0) as a base case before checking for
     * greater than zero properties.
     *
     * @return cartsize the CartSize of the store cart.
     */
    @Override
    public CartSize getCartSize() {
        final long cnt = myCart.values().stream().filter(quantity -> quantity > 0).count();
        final int total = myCart.values().stream().filter(quantity -> quantity > 0).
                mapToInt(Integer::intValue).sum();
        return new CartSize((int) cnt, total);
    }

    /**
     * This method constructs and returns a String representation of
     * each store item order located in myCart. Note, this method
     * utilizes the storeItem class to retrieve the necessary information
     * to return the String. Also, a local variable of type StringBuilder
     * is used to create and append the necessary String and information
     * associated with the Item.
     *
     * @return the StringBuilder sb with the store cart information.
     */
    public String toString() {
        final StringBuilder sb = new StringBuilder();
        for (final Map.Entry<Item, Integer> item : myCart.entrySet()) {
            sb.append(item.getKey().getName());
            sb.append(", Quantity: ");
            sb.append(item.getValue());
            sb.append(", Price: $");
            sb.append(item.getKey().getPrice());
            if (myMember) {
                sb.append(", (Bulk Quantity: ");
                sb.append(item.getKey().getBulkQuantity());
                sb.append(" for $");
                sb.append(item.getKey().getBulkPrice());
                sb.append(")");
            }
        }
        return sb.toString();
    }
}