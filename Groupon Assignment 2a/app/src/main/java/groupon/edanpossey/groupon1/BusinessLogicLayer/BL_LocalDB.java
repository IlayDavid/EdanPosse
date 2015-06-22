package groupon.edanpossey.groupon1.BusinessLogicLayer;

import android.content.Context;

import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.Map;

import groupon.edanpossey.groupon1.DataAccessLayer.DAL_LocalDB;
import groupon.edanpossey.groupon1.DataAccessLayer.DataAccessLayerFactory;
import groupon.edanpossey.groupon1.DataAccessLayer.IDAL;
import groupon.edanpossey.groupon1.Entities.AccessLevel;
import groupon.edanpossey.groupon1.Entities.Business;
import groupon.edanpossey.groupon1.Entities.CatalogItem;
import groupon.edanpossey.groupon1.Entities.Coupon;
import groupon.edanpossey.groupon1.Entities.Order;
import groupon.edanpossey.groupon1.Entities.User;

/**
 * Created by IlayDavid on 17/05/2015.
 */
public class BL_LocalDB implements IBL{
    private IDAL myDal;
    private Map<String, User> userMap;
    private Map<String, Business> businessMap;
    private Map<Long, CatalogItem> catalogItemMap;
    private Map<Long, Coupon> couponMap;
    private Map<Long, Order> orderMap;

    //==============================================================================================
    //region Constructors
    //==============================================================================================

    protected BL_LocalDB(Context context) {
        myDal = DataAccessLayerFactory.getDAL(DataAccessLayerFactory.DALType.LocalDB , context);
        userMap = new LinkedHashMap<String, User>();
        businessMap = new LinkedHashMap<String, Business>();
        catalogItemMap = new LinkedHashMap<Long, CatalogItem>();
        couponMap = new LinkedHashMap<Long, Coupon>();
        orderMap = new LinkedHashMap<Long, Order>();

        initialize();
    }

    //==============================================================================================
    //endregion Constructors
    //==============================================================================================

    //==============================================================================================
    //region Initializers
    //==============================================================================================

    private void initialize() {
        myDal.connectDatabase();
        //User user = new User("admin", "adminpass", "admin@groupon.com", "0504442966", AccessLevel.Administrator, null);
        //myDal.insertUser(user);
        initializeMaps();
    }

    private void initializeMaps() {
        initializeUserMap();
        initializeBusinessMap();
        initializeCatalogMap();
        initializeCouponMap();
        initializeOrderMap();
    }

    private void initializeOrderMap() {
        Collection<Order> orders = myDal.getOrders();
        for (Order order : orders) {
            orderMap.put(order.getOrderCode(), order);

            order.setCatalogItem(catalogItemMap.get(order.getCatalogItem().getCatalogNumber()));
            if (order.getOrderStatus() == Order.OrderStatus.Pending)
                order.getCatalogItem().addOrder(order);

            if (order.getCoupon().getCouponCode() != -1)
                order.setCoupon(couponMap.get(order.getCoupon().getCouponCode()));

            order.setOrderedBy(userMap.get(order.getOrderedBy().getId()));
            order.getOrderedBy().addOrder(order);
        }
    }

    private void initializeCouponMap() {
        Collection<Coupon> coupons = myDal.getCoupons();
        for (Coupon coupon : coupons) {
            couponMap.put(coupon.getCouponCode(), coupon);
            CatalogItem catalogItem = catalogItemMap.get(coupon.getCatalogItem().getCatalogNumber());
            catalogItem.addCoupon(coupon);
            coupon.setCatalogItem(catalogItem);
        }
    }

    private void initializeCatalogMap() {
        Collection<CatalogItem> catalogItems = myDal.getCatalogItems();
        for (CatalogItem catalogItem : catalogItems) {
            catalogItemMap.put(catalogItem.getCatalogNumber(), catalogItem);
            Business business = businessMap.get(catalogItem.getPublishedBy().getBusinessName());
            catalogItem.setPublishedBy(business);
            business.addCatalogItem(catalogItem);
        }
    }

    private void initializeBusinessMap() {
        Collection<Business> businesses = myDal.getBusinesses();
        for (Business business : businesses) {
            businessMap.put(business.getBusinessName(), business);
            User user = userMap.get(business.getOwner().getId());
            user.setBusiness(business);
            business.setOwner(user);
        }
    }

    private void initializeUserMap() {
        Collection<User> users = myDal.getUsers();
        for (User user : users) {
            userMap.put(user.getId(), user);
        }
    }
    //==============================================================================================
    //endregion Initializers
    //==============================================================================================

    //==============================================================================================
    //region Methods
    //==============================================================================================
    public User login(String username, String password) {
        User user = userMap.get(username);
        if (user != null)                                               // Username doesn't exist
            if (user.getPassword().equals(password) == false)           // Password is wrong
                user = null;

        return user;
    }

    public boolean newUser(User user) {
        if (myDal.insertUser(user) == -1) {                             // Insertion failed
            return false;
        }

        userMap.put(user.getId(), user);

        return true;
    }

    public boolean newBusiness(Business business){
        if(myDal.insertBusiness(business) == -1){                       // Insertion failed
            return false;
        }

        businessMap.put(business.getBusinessName(), business);

        return true;
    }

    public boolean newOrder(Order order) {
        order.setOrderCode(myDal.insertOrder(order));
        if (order.getOrderCode() == -1)                                 // Insertion failed
            return false;

        order.getOrderedBy().addOrder(order);
        order.getCatalogItem().addOrder(order);
        orderMap.put(order.getOrderCode(), order);

        return true;
    }

    public boolean newCatalogItem(CatalogItem catalogItem) {
        catalogItem.setCatalogNumber(myDal.insertCatalogItem(catalogItem));
        if (catalogItem.getCatalogNumber() == -1) {                      // Insertion failed
            return false;
        }

        catalogItem.getPublishedBy().addCatalogItem(catalogItem);
        catalogItemMap.put(catalogItem.getCatalogNumber(), catalogItem);

        return true;
    }

    public boolean approvePendingOrder(Order order) {
        if (order.getCoupon() == null) {
            Coupon coupon = new Coupon(-1, Coupon.CouponStatus.NotUsed, order.getCatalogItem());
            coupon.setCouponCode(myDal.insertCoupon(coupon));
            if (coupon.getCouponCode() == -1) {                         // Insertion failed
                return false;
            }
            order.setCoupon(coupon);
        }
        order.setOrderStatus(Order.OrderStatus.Complete);
        if (myDal.updateOrder(order, order) == 0) {                     // Update failed
            order.setCoupon(null);
            order.setOrderStatus(Order.OrderStatus.Pending);
            return false;
        }

        couponMap.put(order.getCoupon().getCouponCode(), order.getCoupon());

        return true;
    }

    public boolean approvePendingCatalogItem(CatalogItem catalogItem) {
        catalogItem.setStatus(CatalogItem.CatalogItemStatus.Approved);
        if (myDal.updateCatalogItem(catalogItem, catalogItem) == 0) {     // Update failed
            catalogItem.setStatus(CatalogItem.CatalogItemStatus.PendingApproval);
            return false;
        }
        return true;
    }

    public boolean updateBusiness(Business oldBus, Business newbus){
        if(myDal.updateBusiness(oldBus, newbus) == 0){                   // Update failed
            return false;
        }
        businessMap.remove(oldBus.getBusinessName());

        oldBus.setBusinessName(newbus.getBusinessName());
        oldBus.setCity(newbus.getCity());
        oldBus.setAddress(newbus.getAddress());
        oldBus.setDescription(newbus.getDescription());

        businessMap.put(oldBus.getBusinessName(), oldBus);

        return true;
    }

    public boolean updateCatalogItem(CatalogItem oldItem, CatalogItem newItem){
        if(myDal.updateCatalogItem(oldItem, newItem) == 0){             // Update failed
            return false;
        }
        catalogItemMap.remove(oldItem.getCatalogNumber());

        oldItem.setDescription(newItem.getDescription());
        oldItem.setStatus(newItem.getStatus());
        oldItem.setCategory(newItem.getCategory());
        oldItem.setRatings(newItem.getRatings());
        oldItem.setSumOfRatings(newItem.getSumOfRatings());
        oldItem.setOriginalPrice(newItem.getOriginalPrice());
        oldItem.setPriceAfterDiscount(newItem.getPriceAfterDiscount());
        oldItem.setExpirationDate(newItem.getExpirationDate());
        oldItem.setCatalogNumber(newItem.getCatalogNumber());

        catalogItemMap.put(oldItem.getCatalogNumber(), oldItem);

        return true;
    }

    public boolean deleteBusiness(Business business){
        if(myDal.deleteBusiness(business) == 0) {                        // Deletion failed
            return false;
        }
        businessMap.remove(business.getBusinessName());

        User owner = business.getOwner();
        Collection<Order> ownerOrders = owner.getOrders();
        Collection<Coupon> ownerCoupons = owner.getCoupons();
        Collection<CatalogItem> businessCatalogItems = business.getCatalogItems();

        for(Order order: ownerOrders){
            order.getCatalogItem().removeOrder(order);
            order.getOrderedBy().removeOrder(order);
            orderMap.remove(order.getOrderCode());
        }

        for(Coupon coupon: ownerCoupons){
            coupon.getCatalogItem().removeCoupon(coupon);
            couponMap.remove(coupon.getCouponCode());
        }

        for(CatalogItem catalogItem: businessCatalogItems){
            Collection<Order> catalogOrders = catalogItem.getOrders();
            Collection<Coupon> catlogCoupons = catalogItem.getCoupons();

            for(Order order: catalogOrders){
                order.getCatalogItem().removeOrder(order);
                order.getOrderedBy().removeOrder(order);
                orderMap.remove(order.getOrderCode());
            }

            for(Coupon coupon: catlogCoupons){
                coupon.getCatalogItem().removeCoupon(coupon);
                couponMap.remove(coupon.getCouponCode());
            }

            catalogItemMap.remove(catalogItem);

        }

        return true;
    }

    public boolean rateCatalogItem(CatalogItem catalogItem, long rating){
        catalogItem.setRatings(catalogItem.getRatings() + 1);
        catalogItem.setSumOfRatings(catalogItem.getSumOfRatings() + rating);

        if(myDal.updateCatalogItem(catalogItem, catalogItem) == 0){     // Update failed
            catalogItem.setRatings(catalogItem.getRatings() - 1);
            catalogItem.setSumOfRatings(catalogItem.getSumOfRatings() - rating);
            return false;
        }

        return true;
    }


    //==============================================================================================
    //endregion Methods
    //==============================================================================================
}
