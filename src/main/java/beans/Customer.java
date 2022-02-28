package beans;

import exceptions.CouponSystemException;
import exceptions.ErrMsg;

import java.util.ArrayList;
import java.util.List;

/**
 * Customer bean to specify any costumer that should log in
 */
public class Customer {
    private int id;
    private String firstName;
    private String lastName;
    private String email;
    private String password;

    /**
     * Constructs the costumers attributes. id is generated automatically in database.
     */
    public Customer(int id, String firstName, String lastName, String email, String password) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) throws CouponSystemException {
       throw new CouponSystemException(ErrMsg.CUSTOMER_ID_CHANGE.getMsg());
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "Costumer{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
