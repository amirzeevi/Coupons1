package dbdao;

import db.DBmanager;
import db.DBrunQuery;
import beans.Category;
import beans.Coupon;
import dao.CouponsDAO;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * The CouponDBDAO is the class that should access the database and update the company table.
 */
public class CouponDBDAO implements CouponsDAO {
    /**
     * Will return true if the specified coupon exist based on its title and company id
     */
    @Override
    public boolean isCompanyCouponExist(Coupon coupon) {
        try {
            return DBrunQuery.getResultSet(DBmanager.IS_COUPON_COMPANY_EXISTS, Map.of(
                            1, coupon.getCompanyID(),
                            2, coupon.getTitle()))
                    .next();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return false;
    }

    /**
     * When updating a coupon's title we need to make sure the title does not already exist for the same company.
     * Will return true if it finds another coupon with the same title.
     */
    @Override
    public boolean isCostumerCouponExist(int costumerID, int couponID) {
        try {
            return DBrunQuery.getResultSet
                            (DBmanager.IS_CUSTOMER_COUPON_EXISTS, Map.of(
                                    1, costumerID,
                                    2, couponID))
                    .next();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return false;
    }

    /**
     * Adds the specified coupon to the coupons table.
     */
    @Override
    public void addCoupon(Coupon coupon) {
        DBrunQuery.runQuery(DBmanager.ADD_COUPON, Map.of(
                1, coupon.getCompanyID(),
                2, coupon.getCategory().value,
                3, coupon.getTitle(),
                4, coupon.getDescription(),
                5, coupon.getStartDate(),
                6, coupon.getEndDate(),
                7, coupon.getAmount(),
                8, coupon.getPrice(),
                9, coupon.getImage()));
    }

    /**
     * Updates the specified coupon to the coupons table.
     */
    @Override
    public void updateCoupon(Coupon coupon) {
        DBrunQuery.runQuery(DBmanager.UPDATE_COUPON, Map.of(
                1, coupon.getCategory().value,
                2, coupon.getTitle(),
                3, coupon.getDescription(),
                4, coupon.getStartDate(),
                5, coupon.getEndDate(),
                6, coupon.getAmount(),
                7, coupon.getPrice(),
                8, coupon.getImage(),
                9, coupon.getId()));
    }

    /**
     * Deletes the specified coupon from the coupons table.
     */
    @Override
    public void deleteCoupon(int couponID) {
        DBrunQuery.runQuery(DBmanager.DELETE_COUPON, Map.of(1, couponID));
    }

    /**
     * Retrieves all the specified company's coupons that are from the specified category and returns a list.
     */
    @Override
    public List<Coupon> getCompanyCouponsByCategory(Category category, int companyId) {
        List<Coupon> categoryCoupons = new ArrayList<>();
        ResultSet resultSet = DBrunQuery.getResultSet
                (DBmanager.GET_COMPANY_COUPON_CATEGORY, Map.of(1, category.value, 2, companyId));
        try {
            while (resultSet.next()) {
                categoryCoupons.add(resultSetToCoupon(resultSet));
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return categoryCoupons;
    }

    /**
     * Retrieves all the specified company's coupons that are from the specified maximum price and returns a list.
     */
    @Override
    public List<Coupon> getCompanyCouponsByMaxPrice(double maxPrice, int companyId) {
        List<Coupon> maxPriceCoupons = new ArrayList<>();
        ResultSet resultSet = DBrunQuery.getResultSet
                (DBmanager.GET_COMPANY_COUPON_MAX_PRICE, Map.of(1, maxPrice, 2, companyId));
        try {
            while (resultSet.next()) {
                maxPriceCoupons.add(resultSetToCoupon(resultSet));
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return maxPriceCoupons;
    }

    /**
     * Retrieves all the specified customer's coupons that are from the specified category and returns a list.
     */
    @Override
    public List<Coupon> getCustomerCouponsByCategory(Category category, int customerId) {
        List<Coupon> categoryCoupons = new ArrayList<>();
        ResultSet resultSet = DBrunQuery.getResultSet
                (DBmanager.GET_CUSTOMER_COUPON_CATEGORY, Map.of(1, category.value, 2, customerId));
        try {
            while (resultSet.next()) {
                categoryCoupons.add(resultSetToCoupon(resultSet));
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return categoryCoupons;
    }

    /**
     * Retrieves all the specified customer's coupons that are from the specified maximum price and returns a list.
     */
    @Override
    public List<Coupon> getCustomerCouponsByMaxPrice(double maxPrice, int customerId) {
        List<Coupon> maxPriceCoupons = new ArrayList<>();
        ResultSet resultSet = DBrunQuery.getResultSet
                (DBmanager.GET_CUSTOMER_COUPON_MAX_PRICE, Map.of(1, maxPrice, 2, customerId));
        try {
            while (resultSet.next()) {
                maxPriceCoupons.add(resultSetToCoupon(resultSet));
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return maxPriceCoupons;
    }

    /**
     * Deletes all expired coupons from the coupons table.
     */
    @Override
    public void deleteExpiredCoupons() {
        DBrunQuery.runQuery(DBmanager.DELETE_EXPIRED_COUPONS);
    }

    /**
     * Retrieves all the specified customer's coupons from the database and returns a list.
     */
    @Override
    public List<Coupon> getCostumerCoupons(int customerId) {
        ArrayList<Coupon> coupons = new ArrayList<>();
        ResultSet resultSet = DBrunQuery.getResultSet(DBmanager.GET_CUSTOMER_COUPONS, Map.of(1, customerId));
        try {
            while (resultSet.next()) {
                coupons.add(resultSetToCoupon(resultSet));
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return coupons;
    }

    /**
     * Retrieves the specified coupon from the database based on its id.
     */
    @Override
    public Coupon getOneCoupon(int couponID) {
        ResultSet resultSet = DBrunQuery.getResultSet(DBmanager.GET_ONE_COUPON, Map.of(1, couponID));
        try {
            return resultSet.next() ? resultSetToCoupon(resultSet) : null;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    /**
     * Adds to the coupon vs customers table if the customer does not already own this coupon, and
     * if it is in stock and did not expire.
     * Will also decrease the coupon's amount by 1.
     */
    @Override
    public void addCouponsPurchase(int costumerID, int couponID) {
        DBrunQuery.runQuery(DBmanager.ADD_COUPON_PURCHASE, Map.of(1, costumerID, 2, couponID));
    }

    /**
     * Retrieves all the specified company's coupons from the database and returns a list.
     */
    @Override
    public List<Coupon> getCompanyCoupons(int companyID) {
        List<Coupon> companyCoupons = new ArrayList<>();
        ResultSet resultSet = DBrunQuery.getResultSet(DBmanager.GET_COMPANY_COUPONS, Map.of(1, companyID));
        try {
            while (resultSet.next()) {
                companyCoupons.add(resultSetToCoupon(resultSet));
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return companyCoupons;
    }

    /**
     * Adds the specified coupon to the coupons table.
     */
    @Override
    public boolean updateCouponTitleExist(Coupon coupon) {
        try {
            ResultSet resultSet = DBrunQuery.getResultSet(DBmanager.UPDATE_COUPON_TITLE_EXIST, Map.of(
                    1, coupon.getId(),
                    2, coupon.getCompanyID(),
                    3, coupon.getTitle()));
            return resultSet.next();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return false;
    }

    /**
     * A private service method to be used in multiple method that will convert the result set into a coupon.
     */
    private Coupon resultSetToCoupon(ResultSet resultSet) throws SQLException {
        return new Coupon(
                resultSet.getInt("id"),
                resultSet.getInt("company_id"),
                Category.values()[resultSet.getInt("category_id") - 1],
                resultSet.getString("title"),
                resultSet.getString("description"),
                resultSet.getDate("start_date"),
                resultSet.getDate("end_date"),
                resultSet.getInt("amount"),
                resultSet.getInt("price"),
                resultSet.getString("image")
        );
    }
}
