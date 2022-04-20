package beans;

import exceptions.ValueSetNotAllowed;

import java.util.List;

/**
 * Company bean to specify attributes and operations of any company that should log in the system
 */
public class Company {

    private int id;
    private final String name;
    private String email;
    private String password;
    private List<Coupon> coupons;


    /**
     * Constructs the company attributes when retrieving from database.
     */

    public Company(int id, String name, String email, String password, List<Coupon> coupons) {
        this(name, email, password);
        this.id = id;
        this.coupons = coupons;
    }

    /**
     * Required constructor for when initializing a new company.
     */
    public Company(String name, String email, String password) {
        this.name = name;
        this.email = email;
        this.password = password;
    }

    /**
     * returns company name
     *
     * @return Company name
     */
    public String getName() {
        return this.name;
    }

    /**
     * Can not change company name. will throw an exception
     */
    public void setName(String name) throws ValueSetNotAllowed {
        throw new ValueSetNotAllowed("You can not change company name");
    }

    /**
     * Returns company email address
     *
     * @return Company email address
     */
    public String getEmail() {
        return email;
    }

    /**
     * Sets company email address
     *
     * @param email new email address
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * Returns company password to log in
     *
     * @return Company password
     */
    public String getPassword() {
        return password;
    }

    /**
     * Sets company password to log in
     *
     * @param password new password
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * Returns company id number
     *
     * @return Company id number
     */
    public int getId() {
        return id;
    }

    /**
     * * Can not change company id. will throw exception
     *
     * @throws ValueSetNotAllowed
     */
    public void setId(int id) throws ValueSetNotAllowed {
        throw new ValueSetNotAllowed("You can not change company id");
    }

    /**
     * Returns a list of the company coupons
     */
    public List<Coupon> getCoupons() {
        return coupons;
    }
}
