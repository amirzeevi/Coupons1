package DBdao;

import DB.DBmanager;
import DB.DBrunQuery;
import beans.Category;
import beans.Coupon;
import dao.CouponsDAO;
import exceptions.CouponSystemException;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class CouponDBDAO implements CouponsDAO {


    @Override
    public boolean isCouponCompanyExists(Coupon coupon) {

        Map<Integer, Object> values = Map.of(1, coupon.getCompanyID(), 2, coupon.getTitle());

        ResultSet resultSet = DBrunQuery.getResultSet(DBmanager.IS_COUPON_COMPANY_EXISTS, values);
        assert resultSet != null;
        try {
            resultSet.next();
            return resultSet.getInt(1) == 1;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return false;
        }
    }


    public void addCoupons(Coupon coupon) {

        Map<Integer, Object> values = Map.of(1, coupon.getCompanyID(), 2, coupon.getCategory().value,
                3, coupon.getTitle(),
                4, coupon.getDescription(),
                5, coupon.getStartDate(),
                6, coupon.getEndDate(),
                7, coupon.getAmount(),
                8, coupon.getPrice(),
                9, coupon.getImage());

        DBrunQuery.runQuery(DBmanager.ADD_COUPON, values);
    }


    @Override
    public void updateCoupon(Coupon coupon) {

        Map<Integer, Object> values = Map.of(1, coupon.getCategory().value, 2, coupon.getTitle(),
                3, coupon.getDescription(),
                4, coupon.getStartDate(),
                5, coupon.getEndDate(),
                6, coupon.getAmount(),
                7, coupon.getPrice(),
                8, coupon.getImage(),
                9, coupon.getId());

        DBrunQuery.runQuery(DBmanager.UPDATE_COUPON, values);
    }


    @Override
    public void deleteCoupon(int couponID) {

        Map<Integer, Object> values = Map.of(1, couponID);
        DBrunQuery.runQuery(DBmanager.DELETE_COUPON, values);
    }


    @Override
    public List<Coupon> getAllCoupons() {

        ArrayList<Coupon> coupons = new ArrayList<>();
        ResultSet resultSet = DBrunQuery.getResultSet(DBmanager.GET_ALL_COUPONS);

        assert resultSet != null;
        while (true) {
            try {
                if (!resultSet.next()) break;
                coupons.add(resultSetToCoupon(resultSet));
            } catch (SQLException | CouponSystemException e) {
                System.out.println(e.getMessage());
            }
        }
        return coupons;
    }


    @Override
    public List<Coupon> getCostumerCoupons(int customerId) {

        ArrayList<Coupon> coupons = new ArrayList<>();
        Map<Integer, Object> values = Map.of(1, customerId);

        ResultSet resultSet = DBrunQuery.getResultSet(DBmanager.GET_COSTUMER_COUPONS, values);

        assert resultSet != null;
        while (true) {
            try {
                if (!resultSet.next()) break;
                coupons.add(getOneCoupon(resultSet.getInt("coupon_id")));
            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }
        }
        return coupons;
    }


    @Override
    public Coupon getOneCoupon(int couponID) {

        Map<Integer, Object> values = Map.of(1, couponID);
        ResultSet resultSet = DBrunQuery.getResultSet(DBmanager.GET_ONE_COUPON, values);
        Coupon coupon = null;
        assert resultSet != null;
        try {
            resultSet.next();
            coupon = resultSetToCoupon(resultSet);
        } catch (SQLException | CouponSystemException e) {
            return null;
        }
        return coupon;
    }


    @Override
    public void addCouponsPurchase(int costumerID, int couponID) {

        Map<Integer, Object> values = Map.of(1, costumerID, 2, couponID);
        DBrunQuery.runQuery(DBmanager.ADD_COUPON_PURCHASE, values);
    }


    @Override
    public List<Coupon> getCompanyCoupons(int companyID) {

        List<Coupon> companyCoupons = new ArrayList<>();
        Map<Integer, Object> values = Map.of(1, companyID);

        ResultSet resultSet = DBrunQuery.getResultSet(DBmanager.GET_COMPANY_COUPONS, values);

        while (true) {
            assert resultSet != null;
            try {
                if (!resultSet.next()) break;
                companyCoupons.add(resultSetToCoupon(resultSet));
            } catch (SQLException | CouponSystemException e) {
                System.out.println(e.getMessage());
            }
        }
        return companyCoupons;
    }


    @Override
    public boolean isCostumerCouponExists(int costumerID, int couponID) {

        Map<Integer, Object> values = Map.of(1, costumerID, 2, couponID);

        ResultSet resultSet = DBrunQuery.getResultSet(DBmanager.IS_COSTUMER_COUPON_EXISTS, values);
        assert resultSet != null;
        try {
            resultSet.next();
            return resultSet.getInt(1) == 1;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return false;
        }
    }


    private Coupon resultSetToCoupon(ResultSet resultSet) throws SQLException, CouponSystemException {

        return new Coupon(resultSet.getInt("id"),
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
