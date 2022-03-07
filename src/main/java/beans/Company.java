package beans;

import exceptions.CouponSystemException;
import exceptions.ErrMsg;

/**
 * Company bean to specify attributes and operations of any company that should log in the system
 */
public class Company {

    private final int id;
    private final String name;
    private String email;
    private String password;


    /**
     * Constructs the company by name, email and password.
     * id is included for when retrieving from database.
     */

    public Company(int id, String name, String email, String password) {
        this.id = id;
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
    public void setName(String name) throws CouponSystemException {
        throw new CouponSystemException(ErrMsg.COMPANY_NAME_CHANGE.getMsg());
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
    public void setId(int id) throws CouponSystemException {
        throw new CouponSystemException(ErrMsg.COMPANY_ID_CHANGE.getMsg());
    }

    /**
     * Overrides Object toString method to generate a String with company details.
     *
     * @return String of company details
     */

    @Override
    public String toString() {
        return "Company{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
