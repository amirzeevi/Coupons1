package beans;

import exceptions.ValueSetNotAllowed;

import java.util.List;

/**
 * Customer bean to specify any costumer that should log in
 */
public class Customer {
    private final int id;
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private List<Coupon> coupons;

    /**
     * Constructs the costumers attributes.
     * id is for when retrieving from database.
     */
    public Customer(int id, String firstName, String lastName, String email, String password, List<Coupon> coupons) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
        this.coupons = coupons;
    }

    /**
     * Returns the customer id
     */
    public int getId() {
        return id;
    }

    /**
     * Can not change customer id.
     * will throw an exception.
     * @throws ValueSetNotAllowed
     */
    public void setId(int id) throws ValueSetNotAllowed {
        throw new ValueSetNotAllowed("You can not change customer id");
    }

    /**
     * Returns the customer first name
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     * Sets the customer first name
     */
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    /**
     * Returns the customer last name
     */
    public String getLastName() {
        return lastName;
    }

    /**
     * Sets the customer last name
     */
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    /**
     *  Returns the customer email
     */
    public String getEmail() {
        return email;
    }

    /**
     * Sets the customer email
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * Returns the customer password to log in
     */
    public String getPassword() {
        return password;
    }

    /**
     * Sets the customer password to log in to a new one
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * Returns a list of all coupons purchased by customer
     */
    public List<Coupon> getCoupons() {
        return coupons;
    }

    /**
     * Sets the list of coupons purchased by customer
     */
    public void setCoupons(List<Coupon> coupons) {
        this.coupons = coupons;
    }

    /**
     * Overrides toString method to display customer details
     */
    @Override
    public String toString() {
        return "Customer{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", coupons=" + coupons +
                '}';
    }
}
