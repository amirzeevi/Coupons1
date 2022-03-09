package beans;

import exceptions.ChangedValueNotAllowed;

import java.util.List;

/**
 * Company bean to specify attributes and operations of any company that should log in the system
 */
public class Company {

    private final int id;
    private final String name;
    private String email;
    private String password;
    private List<Coupon> coupons;


    /**
     * Constructs the company by name, email and password.
     * id is included for when retrieving from database.
     */

    public Company(int id, String name, String email, String password, List<Coupon> coupons) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.password = password;
        this.coupons = coupons;
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
    public void setName(String name) throws ChangedValueNotAllowed {
        throw new ChangedValueNotAllowed("You can not change company name");
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
     * Can not set company id. will throw Coupon exception
     */
    public void setId(int id) throws ChangedValueNotAllowed {
        throw new ChangedValueNotAllowed("You can not change company id");
    }

    public List<Coupon> getCoupons() {
        return coupons;
    }

    public void setCoupons(List<Coupon> coupons) {
        this.coupons = coupons;
    }

}
