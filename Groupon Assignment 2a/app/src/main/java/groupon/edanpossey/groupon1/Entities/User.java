package groupon.edanpossey.groupon1.Entities;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedList;

/**
 * Created by Owner on 16/05/2015.
 */
public class User {
    private String id, password, email, phoneNumber;
    private AccessLevel accessLevel;
    private Business business;
    private Collection<Order> orders;
    private Collection<Coupon> coupons;


    //==============================================================================================
    //region Constructors
    //==============================================================================================

    public User() {
        this.id = null;
        this.password = null;
        this.email = null;
        this.phoneNumber = null;
        this.accessLevel = null;
        this.orders = new ArrayList<Order>();
        this.coupons = new ArrayList<Coupon>();
        business = null;
    }

    public User(String id, String password, String email, String phoneNumber, AccessLevel accessLevel, Business business) {
        this.id = id;
        this.password = password;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.accessLevel = accessLevel;
        this.business = business;
        this.orders = new ArrayList<Order>();
        this.coupons = new ArrayList<Coupon>();
    }

    //==============================================================================================
    //endregion Constructors
    //==============================================================================================

    //==============================================================================================
    //region Accessors
    //==============================================================================================

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public AccessLevel getAccessLevel() {
        return accessLevel;
    }

    public void setAccessLevel(AccessLevel accessLevel) {
        this.accessLevel = accessLevel;
    }

    public Business getBusiness() {
        return business;
    }

    public void setBusiness(Business business) {
        this.business = business;
    }

    public Collection<Order> getOrders() {
        return this.orders;
    }

    public Collection<Coupon> getCoupons() {
        return this.coupons;
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

    public boolean removeCoupon(int couponCode) {
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
    //region Orders
    public boolean addOrder(Order order) {
        return this.orders.add(order);
    }

    public boolean removeOrder(Order order) {
        return this.orders.remove(order);
    }

    public boolean removeOrder(int orderCode) {
        for (Iterator<Order> iter = this.orders.iterator(); iter.hasNext(); ) {
            Order currentOrder = iter.next();
            if (currentOrder.getOrderCode() == orderCode)
                return removeOrder(currentOrder);
        }

        return false;
    }
    //endregion Orders
    //----------------

    //==============================================================================================
    //endregion Collections Manipulators and Accessors
    //==============================================================================================
}

