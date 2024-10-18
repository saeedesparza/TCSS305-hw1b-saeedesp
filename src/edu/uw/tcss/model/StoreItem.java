package edu.uw.tcss.model;

import java.math.BigDecimal;
import java.util.Objects;

/**
 * Represents an item in a store.
 *
 * @author Saeed Esparza
 * @version Autumn 2024
 */
public class StoreItem implements Item {
    /**
     * The name of the store item.
     */
    private final String myName;
    /**
     * The price of the store item.
     */
    private final BigDecimal myPrice;
    /**
     * The bulk quantity of the store item.
     */
    private final int myBq;
    /**
     * The bulk price of the store item.
     */
    private final BigDecimal myBp;

    /**
     * Constructs a store item using the name and price from user input.
     * Note, this method removes the implicit call to super() and calls
     * this() instead while passing along the name, price, bulk quantity,
     * and bulk price to the constructor.
     *
     * @param theName the String that stores the name of the item.
     * @param thePrice the BigDecimal that stores the price of the item.
     * @throws IllegalArgumentException if the name or the price is blank.
     * @throws NullPointerException if the name or the price is null.
     */
    public StoreItem(final String theName, final BigDecimal thePrice) {
        this(theName, thePrice, 0, BigDecimal.ZERO);

    }

    /**
     * Constructs a store item using name, price, bulk quantity, and bulk price.
     * Note, this method utilizes the Objects Java utility class and requires that
     * the name, price, and bulk price are not null. By utilizing the Objects
     * class, this method will not have to redundantly use a second if statement to
     * check whether or not the parameters that can be null are null. More
     * specifically, this is done like so: Objects.requireNonNull(theParam).
     *
     * @param theName the String that stores the name of the item.
     * @param thePrice the BigDecimal that stores the price of the item.
     * @param theBq the int that stores the bulk quantity of the item.
     * @param theBp the BigDecimal that stores the bulk price of the item.
     * @throws IllegalArgumentException if the price, the bp, the bq, or the name is blank.
     * @throws NullPointerException if the price, the bp, the bq, or the name is null.
     */
    public StoreItem(final String theName, final BigDecimal thePrice, final int theBq,
                     final BigDecimal theBp) {
        super();
        if (Objects.requireNonNull(theName).isEmpty()
            || Objects.requireNonNull(thePrice).compareTo(BigDecimal.ZERO) < 0
            || theBq < 0
            || Objects.requireNonNull(theBp).compareTo(BigDecimal.ZERO) < 0) {
            throw new IllegalArgumentException();
        }
        myName = theName;
        myPrice = thePrice;
        myBq = theBq;
        myBp = theBp;
    }

    /**
     * This method gets the name of the store item by returning the String myName
     * that the store item is stored in. This is done to use the data in other
     * methods such as the toString() method. Note, the name cannot be null or empty
     * as the StoreItem() constructors have already ensured the String myName
     * cannot be either states.
     *
     * @return the String myName that stores the name of the store item.
     */
    @Override
    public String getName() {
        return myName;
    }

    /**
     * This method gets the price of the store item by returning the BigDecimal
     * myPrice. This is done to use the data in other methods such as the toString()
     * method. Note, the price cannot be null, empty, or negative as the StoreItem()
     * constructors have already ensured the BigDecimal myPrice cannot be either states.
     *
     * @return the BigDecimal myPrice that stores the price of the store item.
     */
    @Override
    public BigDecimal getPrice() {
        return myPrice;
    }

    /**
     * This method gets the bulk quantity of the store item by returning the
     * int myBq. This is done to use the data in other methods such as the isBulk()
     * method. Note, the bulk quantity cannot be negative as the StoreItem()
     * constructors have already ensured the int myBq cannot be negative.
     *
     * @return the int myBq that stores the bulk quantity of the store item.
     */
    @Override
    public int getBulkQuantity() {
        return myBq;
    }

    /**
     * This method gets the bulk price of the store item by returning the
     * BigDecimal myBp. This is done to use the data in other methods such
     * as the toString() method. Note, the bulk price cannot be null, empty,
     * or negative as the StoreItem() constructors have already ensured the
     * BigDecimal myBp cannot be either states.
     *
     * @return the BigDecimal myBp that stores the bulk price of the store item.
     */
    @Override
    public BigDecimal getBulkPrice() {
        return myBp;
    }

    /**
     * This method checks if the store item is a bulk store item to
     * use the bulk features for a store item by returning if the
     * bulk quantity is not equal to zero as a boolean true or false.
     * This is done to ensure that bulk features such as bulk pricing
     * are not applied to non-bulk store items. Note, this method utilizes
     * the getBulkQuantity() method by checking if the getBulkQuantity()
     * method returns zero.
     *
     * @return false if the getBulkQuantity() method returns zero, true if otherwise.
     */
    @Override
    public boolean isBulk() {
        return 0 < myBq;
    }

    /**
     * This method returns the name and price of a non-bulk store item
     * and returns the name, price, bulk quantity, and bulk price for a
     * bulk store item as a String. Note, this method utilizes the getName(),
     * getPrice(), getBulkQuantity(), and getBulkPrice() methods to retrieve
     * the necessary information to return the String.
     *
     * @return the StringBuilder sb with the store item information.
     */
    public String toString() {
        final StringBuilder sb = new StringBuilder();
        sb.append(myName);
        sb.append(", $");
        sb.append(myPrice);
        if (isBulk()) {
            sb.append(" (");
            sb.append(myBq);
            sb.append(" for $");
            sb.append(myBp);
            sb.append(")");
        }
        return sb.toString();
    }

    /**
     * This method checks if another store item Object is equal to
     * the store item stored in the class by returning a boolean true
     * or false. This is done to ensure that the store items are properly
     * and correctly initialized and contructed. Note, this method utilizes
     * a wrapper to ensure the Object theOther can be used as a StoreItem
     * Object for use in checking for equality.
     *
     * @param theOther the Object to be compared for equality against our store item.
     * @return false if theOther Object is not equal to the store item, true if otherwise.
     */
    @Override
    public boolean equals(final Object theOther) {
        final boolean fin;
        if (theOther == null || getClass() != theOther.getClass()) {
            fin = false;
        } else {
            final StoreItem other = (StoreItem) theOther;
            fin = myName.equals(other.myName)
                    && myPrice.equals(other.myPrice)
                    && myBq == other.myBq
                    && myBp.equals(other.myBp);
        }
        return fin;
    }

    /**
     * This method returns the hashcode of an object by utilizing the
     * Object's name, price, bulk quantity, and bulk price data. This is
     * done to ensure effecient data retrieval, ensuring object equality,
     * consistency in use of other collections, and debugging/testing.
     *
     * @return an int representation of the StoreItem object.
     */
    @Override
    public int hashCode() {
        return Objects.hash(myName, myPrice, myBq, myBp);
    }
}