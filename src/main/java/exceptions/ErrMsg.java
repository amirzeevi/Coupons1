package exceptions;

public enum ErrMsg {

    LOGIN(" LOGIN INCORRECT"),
    COMPANY_SAME_EMAIL_NAME("CAN NOT ADD COMPANY WITH EXISTING NAME OR EMAIL"),
    COMPANY_ID_CHANGE("Can not change company ID"),
    COMPANY_NAME_CHANGE("Can not change company name"),
    COMPANY_SAME_EMAIL("EXSITING EMAIL"),
    COMPANY_UPDATE("CANNOT UPDATE: "),
    COMPANY_NOT_FOUND("COMPANY NOT FOUND"),
    COMPANY_ADD("CANNOT ADD COMPANY: "),
    COMPANY_UPDATE_NAME("CAN NOT UPDATE COMPANY NAME OR ID"),
    COMPANY_DELETE_ERROR("CANNOT DELETE COMPANY: "),
    CUSTOMER_ID_CHANGE("Can not change customer id"),
    COSTUMER_UPDATE("CANNOT UPDATE COSTUMER: "),
    COSTUMER_DELETE("CANNOT DELETE COSTUMER: "),
    COSTUMER_SAME_EMAIL("EXISTING EMAIL"),
    COSTUMER_ADD("CANNOT ADD NEW COSTUMER: "),
    COSTUMER_EXISTS("COSTUMER ALLREADY EXISTS"),
    COSTUMER_WRONG_ID("COSTUMER NOT FOUND"),
    COUPON_ID_CHANGE("Can not change coupon id"),
    COUPON_COMPANY_ID_CHANGE("Can not change company id"),
    COUPON_ADD("CANNOT ADD COUPON: "),
    COUPON_DATE("COUPON DATE INCORRECT"),
    COUPON_TITLE_EXISTS("COUPON TITLE ALREADY EXISTS"),
    COUPON_UPDATE("CANNOT UPDATE COUPON : "),
    COUPON_NOT_EXISTS("COUPON ID DOES NOT EXIST"),
    COUPON_COMPANY_ID_NOT_SAME("COMPANY ID NOT SAME"),
    COUPON_EXISTS("COUPON ALREADY EXISTS"),
    COUPON_EXPIRED("CAN NOT MAKE PURCHASE. COUPON ALREADY EXPIRED"),
    COUPON_AMOUNT("COUPON IS OUT OF STOCK"),
    CATEGORY("Invalid category"),
    LIST("EMPTY LIST");

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
