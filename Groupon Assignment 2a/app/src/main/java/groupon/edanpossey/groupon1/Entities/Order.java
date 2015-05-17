package groupon.edanpossey.groupon1.Entities;

/**
 * Created by Owner on 16/05/2015.
 */
public class Order {
    public enum OrderStatus {
        Pending, Complete, Rejected
    }

    private long orderCode;
    private OrderStatus orderStatus;
    private User orderedBy;
    private Coupon coupon;
    private CatalogItem catalogItem;

    //==============================================================================================
    //region Constructors
    //==============================================================================================

    public Order() {
        this.orderCode = 0;
        this.orderStatus = OrderStatus.Pending;
        this.orderedBy = null;
        this.coupon = null;
        this.catalogItem = null;
    }

    public Order(long orderCode, OrderStatus orderStatus, User orderedBy, CatalogItem catalogItem, Coupon coupon) {
        this.orderCode = orderCode;
        this.orderStatus = orderStatus;
        this.orderedBy = orderedBy;
        this.coupon = null;
        this.catalogItem = catalogItem;
        this.coupon = coupon;
    }

    //==============================================================================================
    //endregion Constructors
    //==============================================================================================

    //==============================================================================================
    //region Accessors
    //==============================================================================================

    public long getOrderCode() {
        return orderCode;
    }

    public void setOrderCode(long orderCode) {
        this.orderCode = orderCode;
    }

    public OrderStatus getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(OrderStatus orderStatus) {
        this.orderStatus = orderStatus;
    }

    public User getOrderedBy() {
        return orderedBy;
    }

    public void setOrderedBy(User orderedBy) {
        this.orderedBy = orderedBy;
    }

    public Coupon getCoupon() {
        return coupon;
    }

    public void setCoupon(Coupon coupon) {
        this.coupon = coupon;
    }

    public CatalogItem getCatalogItem() {
        return catalogItem;
    }

    public void setCatalogItem(CatalogItem catalogItem) {
        this.catalogItem = catalogItem;
    }

    //==============================================================================================
    //endregion Accessors
    //==============================================================================================

    //==============================================================================================
    //region Methods
    //==============================================================================================

    public boolean isComplete() {
        return this.coupon != null;
    }

    //==============================================================================================
    //endregion Methods
    //==============================================================================================
}
