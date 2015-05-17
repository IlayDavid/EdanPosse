package groupon.edanpossey.groupon1.Entities;

import java.sql.Date;
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedList;

/**
 * Created by Owner on 16/05/2015.
 */
public class CatalogItem {
    public enum CatalogItemStatus{
        PendingApproval, Approved
    }
    private Business publishedBy;
    private long catalogNumber, ratings, sumOfRatings;
    private String category, description;
    private CatalogItemStatus status;
    private double originalPrice, priceAfterDiscount;
    private Date expirationDate;
    private Collection<Coupon> coupons;
    private Collection<Order> orders;

    //==============================================================================================
    //region Constructors
    //==============================================================================================

    public CatalogItem() {
        publishedBy = null;
        catalogNumber = -1;
        category = null;
        description = null;
        status = null;
        ratings = -1;
        sumOfRatings = -1;
        originalPrice = -1;
        priceAfterDiscount = -1;
        expirationDate = null;
        coupons = null;
        orders = null;
    }

    public CatalogItem(long catalogNumber, Business publishedBy, String category, String description,
                       CatalogItemStatus status, long ratings, long sumOfRatings, double originalPrice, double priceAfterDiscount, Date expirationDate) {
        this.publishedBy = publishedBy;
        this.catalogNumber = catalogNumber;
        this.category = category;
        this.description = description;
        this.status = status;
        this.ratings = ratings;
        this.sumOfRatings = sumOfRatings;
        this.originalPrice = originalPrice;
        this.priceAfterDiscount = priceAfterDiscount;
        this.expirationDate = expirationDate;
        this.coupons = new LinkedList<Coupon>();
        this.orders = new LinkedList<Order>();
    }

    //==============================================================================================
    //endregion Constructors
    //==============================================================================================

    //==============================================================================================
    //region Accessors
    //==============================================================================================

    public Business getPublishedBy() {
        return publishedBy;
    }

    public void setPublishedBy(Business publishedBy) {
        this.publishedBy = publishedBy;
    }

    public long getCatalogNumber() {
        return catalogNumber;
    }

    public void setCatalogNumber(long catalogNumber) {
        this.catalogNumber = catalogNumber;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public CatalogItemStatus getStatus() {
        return status;
    }

    public void setStatus(CatalogItemStatus status) {
        this.status = status;
    }

    public double getOriginalPrice() {
        return originalPrice;
    }

    public long getRatings() {
        return ratings;
    }

    public void setRatings(long ratings) {
        this.ratings = ratings;
    }

    public long getSumOfRatings() {
        return sumOfRatings;
    }

    public void setSumOfRatings(long sumOfRatings) {
        this.sumOfRatings = sumOfRatings;
    }

    public void setOriginalPrice(double originalPrice) {
        this.originalPrice = originalPrice;
    }

    public double getPriceAfterDiscount() {
        return priceAfterDiscount;
    }

    public void setPriceAfterDiscount(double priceAfterDiscount) {
        this.priceAfterDiscount = priceAfterDiscount;
    }

    public Date getExpirationDate() {
        return expirationDate;
    }

    public void setExpirationDate(Date expirationDate) {
        this.expirationDate = expirationDate;
    }

    public Collection<Coupon> getCoupons() {
        return coupons;
    }

    public Collection<Order> getOrders() {
        return orders;
    }

    //==============================================================================================
    //endregion Accessors
    //==============================================================================================

    //==============================================================================================
    //region Collections' Manipulators
    //==============================================================================================

    //----------------
    //region Coupons
    public boolean addCoupon(Coupon coupon) {
        return this.coupons.add(coupon);
    }

    public boolean removeCoupon(Coupon coupon) {
        return this.coupons.remove(coupon);
    }

    public boolean removeCoupon(long couponCode) {
        for (Iterator<Coupon> iter = this.coupons.iterator(); iter.hasNext(); ) {
            Coupon currentCoupon = iter.next();
            if (currentCoupon.getCouponCode() == couponCode)
                return removeCoupon(currentCoupon);
        }
        return false;
    }
    //endregion Coupons
    //----------------

    //----------------
    //region Pending Orders
    public boolean addOrder(Order order) {
        return this.orders.add(order);
    }

    public boolean removeOrder(Order order) {
        return this.orders.remove(order);
    }

    public boolean removeOrder(long orderCode) {
        for (Iterator<Order> iter = this.orders.iterator(); iter.hasNext(); ) {
            Order currentOrder = iter.next();
            if (currentOrder.getOrderCode() == orderCode)
                return removeOrder(currentOrder);
        }
        return false;
    }

    public Collection<Order> getPendingOrders(){
        Collection<Order> pendingOrders = new LinkedList<Order>();
        for(Order order: orders){
            if(order.getOrderStatus() == Order.OrderStatus.Pending)
                pendingOrders.add(order);
        }

        return pendingOrders;
    }
    //endregion Pending Orders
    //----------------

    //==============================================================================================
    //endregion Collections' Manipulators
    //==============================================================================================
}
