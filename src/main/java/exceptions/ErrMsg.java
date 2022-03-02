package exceptions;

public enum ErrMsg {

    LOGIN(" login incorrect"),
    COMPANY_SAME_EMAIL_NAME("Can not add company with existing name or email"),
    COMPANY_ID_CHANGE("Can not change company ID"),
    COMPANY_NAME_CHANGE("Can not change company name"),
    SAME_EMAIL("Existing email"),
    COMPANY_UPDATE("Can not update: "),
    COMPANY_NOT_FOUND("Company not found"),
    COMPANY_ADD("Can not add company: "),
    COMPANY_DELETE_ERROR("Can not delete company: "),
    CUSTOMER_ID_CHANGE("Can not change customer id"),
    CUSTOMER_UPDATE("Can not update customer: "),
    CUSTOMER_DELETE("Can not delete customer: "),
    CUSTOMER_ADD("Can not add customer: "),
    CUSTOMER_EXISTS("Customer already exists"),
    CUSTOMER_WRONG_ID("Customer not found"),
    COUPON_ID_CHANGE("Can not change coupon id"),
    COUPON_PRICE("coupon should have positive price"),
    COUPON_AMOUNT_NEGATIVE("coupon should have positive amount"),
    COUPON_COMPANY_ID_CHANGE("Can not change company id"),
    COUPON_ADD("Can not add coupon: "),
    COUPON_DATE("Coupon date incorrect"),
    COUPON_TITLE_EXISTS("Coupon title already exists"),
    COUPON_UPDATE("Can not update coupon: "),
    COUPON_NOT_EXISTS("Coupon id not found"),
    COUPON_COMPANY_ID_NOT_SAME("Company id not same"),
    COUPON_EXISTS("Coupon already exists"),
    COUPON_EXPIRED("Can not make purchase. coupon expired"),
    COUPON_AMOUNT("Coupon out of stock"),
    CATEGORY("Invalid category"),
    LIST("Empty list");

    String msg;

    ErrMsg(String msg) {
        this.msg = msg;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
