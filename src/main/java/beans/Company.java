package beans;

import exceptions.ValueSetNotAllowed;
import java.util.List;

public class Company {

    private int id;
    private final String name;
    private String email;
    private String password;
    private List<Coupon> coupons;


    public Company(int id, String name, String email, String password, List<Coupon> coupons) {
        this(name, email, password);
        this.id = id;
        this.coupons = coupons;
    }

    public Company(String name, String email, String password) {
        this.name = name;
        this.email = email;
        this.password = password;
    }


    public String getName() {
        return this.name;
    }

    public void setName(String name) throws ValueSetNotAllowed {
        throw new ValueSetNotAllowed("You can not change company name");
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
    
    public int getId() {
        return id;
    }

    public void setId(int id) throws ValueSetNotAllowed {
        throw new ValueSetNotAllowed("You can not change company id");
    }

    public List<Coupon> getCoupons() {
        return coupons;
    }
}
