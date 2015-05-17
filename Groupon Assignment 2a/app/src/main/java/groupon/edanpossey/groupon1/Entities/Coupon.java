package groupon.edanpossey.groupon1.Entities;

/**
 * Created by Owner on 16/05/2015.
 */
public class Coupon {
    public enum CouponStatus {
        NotUsed, Used, Expired;
    }

    private long couponCode;
    private CouponStatus status;
    private CatalogItem catalogItem;

    //==============================================================================================
    //region Constructors
    //==============================================================================================

    public Coupon() {
        this.couponCode = -1;
        this.status = null;
        this.catalogItem = null;
    }

    public Coupon(long couponCode, CouponStatus status, CatalogItem catalogItem) {
        this.couponCode = couponCode;
        this.status = status;
        this.catalogItem = catalogItem;
    }

    //==============================================================================================
    //endregion Constructors
    //==============================================================================================

    //==============================================================================================
    //region Accessors
    //==============================================================================================

    public long getCouponCode() {
        return couponCode;
    }

    public void setCouponCode(long couponCode) {
        this.couponCode = couponCode;
    }

    public CatalogItem getCatalogItem() {
        return catalogItem;
    }

    public void setCatalogItem(CatalogItem catalogItem) {
        this.catalogItem = catalogItem;
    }

    public CouponStatus getStatus() {
        return status;
    }

    public void setStatus(CouponStatus status) {
        this.status = status;
    }

    //==============================================================================================
    //endregion Accessors
    //==============================================================================================

}
