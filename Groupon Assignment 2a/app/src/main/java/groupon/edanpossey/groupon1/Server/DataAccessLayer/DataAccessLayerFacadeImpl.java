package groupon.edanpossey.groupon1.Server.DataAccessLayer;

import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.Map;

import groupon.edanpossey.groupon1.Entities.AccessLevel;
import groupon.edanpossey.groupon1.Entities.Business;
import groupon.edanpossey.groupon1.Entities.CatalogItem;
import groupon.edanpossey.groupon1.Entities.Coupon;
import groupon.edanpossey.groupon1.Entities.Order;
import groupon.edanpossey.groupon1.Entities.User;

/**
 * Created by IlayDavid on 17/05/2015.
 */
public class DataAccessLayerFacadeImpl implements DataAccessLayerFacade {
    private Map<String, User> userMap;
    private Map<String, Business> businessMap;
    private Map<Long, CatalogItem> catalogItemMap;
    private Map<Long, Coupon> couponMap;
    private Map<Long, Order> orderMap;
    private long orderCode, catalogNumber, couponCode;

    public DataAccessLayerFacadeImpl(){
        userMap = new LinkedHashMap<String, User>();
        businessMap = new LinkedHashMap<String, Business>();
        catalogItemMap = new LinkedHashMap<Long, CatalogItem>();
        couponMap = new LinkedHashMap<Long, Coupon>();
        orderMap = new LinkedHashMap<Long, Order>();
        User admin = new User("admin","adminpass","admin@email","1234567890", AccessLevel.Administrator, null);
        User user = new User("business","businesspass","business@email","0987654321", AccessLevel.Business, null);
        Business business = new Business(user, "businessname", "address", "city", "hartabuna");
        user.setBusiness(business);
        businessMap.put(business.getBusinessName(),business);
        userMap.put(user.getId(),user);
        userMap.put(admin.getId(), admin);
        orderCode = 0;
        catalogNumber = 0;
        couponCode = 0;
    }

    @Override
    public boolean connectDatabase() {
        return true;
    }

    @Override
    public void closeDatabse() {

    }

    @Override
    public User getUser(String id) {
        return userMap.get(id);
    }

    @Override
    public Business getBusiness(String businessName) {
        return businessMap.get(businessName);
    }

    @Override
    public CatalogItem getCatalogItem(long catalogNumber) {
        return catalogItemMap.get(catalogNumber);
    }

    @Override
    public Coupon getCoupon(long couponCode) {
        return couponMap.get(couponCode);
    }

    @Override
    public Order getOrder(long orderCode) {
        return orderMap.get(orderCode);
    }

    @Override
    public long insertCoupon(Coupon coupon) {
        if(couponMap.get(coupon.getCouponCode()) == null){
            coupon.setCouponCode(couponCode);
            couponMap.put(couponCode, coupon);
            couponCode++;
            return couponCode - 1;
        }
        return -1;
    }

    @Override
    public long insertOrder(Order order) {
        if(orderMap.get(order.getOrderCode()) == null){
            order.setOrderCode(orderCode);
            orderMap.put(orderCode, order);
            orderCode++;
            return orderCode - 1;
        }
        return -1;
    }

    @Override
    public long insertCatalogItem(CatalogItem catalogItem) {
        if(catalogItemMap.get(catalogItem.getCatalogNumber()) == null){
            catalogItem.setCatalogNumber(catalogNumber);
            catalogItemMap.put(catalogNumber, catalogItem);
            catalogNumber++;
            return catalogNumber - 1;
        }
        return -1;
    }

    @Override
    public long insertBusiness(Business business) {
        if(businessMap.get(business.getBusinessName()) == null){
            businessMap.put(business.getBusinessName(), business);
            return 1;
        }
        return -1;
    }

    @Override
    public long insertUser(User user) {
        if(userMap.get(user.getId()) == null){
            userMap.put(user.getId(), user);
            return 1;
        }
        return -1;
    }

    @Override
    public long updateUser(User oldEntity, User newEntity) {
        if(userMap.get(newEntity.getId()) == null){

            userMap.remove(oldEntity.getId());
            userMap.put(newEntity.getId(), oldEntity);

            oldEntity.setEmail(newEntity.getEmail());
            oldEntity.setId(newEntity.getId());
            oldEntity.setPassword(newEntity.getPassword());
            oldEntity.setPhoneNumber(newEntity.getPhoneNumber());
            oldEntity.setAccessLevel(newEntity.getAccessLevel());

            return 1;
        }
        return -1;
    }

    @Override
    public long updateBusiness(Business oldEntity, Business newEntity) {
        if(businessMap.get(newEntity.getBusinessName()) == null){

            businessMap.remove(oldEntity.getBusinessName());
            businessMap.put(newEntity.getBusinessName(), oldEntity);

            oldEntity.setAddress(newEntity.getAddress());
            oldEntity.setBusinessName(newEntity.getBusinessName());
            oldEntity.setCity(newEntity.getCity());
            oldEntity.setDescription(newEntity.getDescription());

            return 1;
        }
        return -1;
    }

    @Override
    public long updateCatalogItem(CatalogItem oldEntity, CatalogItem newEntity) {
        if(catalogItemMap.get(newEntity.getCatalogNumber()) == null){

            catalogItemMap.remove(oldEntity.getCatalogNumber());
            catalogItemMap.put(newEntity.getCatalogNumber(), oldEntity);

            oldEntity.setCatalogNumber(newEntity.getCatalogNumber());
            oldEntity.setDescription(newEntity.getDescription());
            oldEntity.setCategory(newEntity.getCategory());
            oldEntity.setExpirationDate(newEntity.getExpirationDate());
            oldEntity.setName(newEntity.getName());
            oldEntity.setOriginalPrice(newEntity.getOriginalPrice());
            oldEntity.setPriceAfterDiscount(newEntity.getPriceAfterDiscount());
            oldEntity.setRatings(newEntity.getRatings());
            oldEntity.setSumOfRatings(newEntity.getSumOfRatings());
            oldEntity.setStatus(newEntity.getStatus());

            return 1;
        }
        return -1;
    }

    @Override
    public long updateOrder(Order oldEntity, Order newEntity) {
        if(orderMap.get(newEntity.getOrderCode()) == null){

            orderMap.remove(oldEntity.getOrderCode());
            orderMap.put(newEntity.getOrderCode(), oldEntity);

            oldEntity.setOrderCode(newEntity.getOrderCode());
            oldEntity.setOrderStatus(newEntity.getOrderStatus());

            return 1;
        }
        return -1;
    }

    @Override
    public long updateCoupon(Coupon oldEntity, Coupon newEntity) {
        if(couponMap.get(newEntity.getCouponCode()) == null){

            couponMap.remove(oldEntity.getCouponCode());
            couponMap.put(newEntity.getCouponCode(), oldEntity);

            oldEntity.setCouponCode(newEntity.getCouponCode());
            oldEntity.setStatus(newEntity.getStatus());

            return 1;
        }
        return -1;
    }

    @Override
    public int deleteUser(User entity) {
        userMap.remove(entity.getId());
        return 1;
    }

    @Override
    public int deleteBusiness(Business entity) {
        businessMap.remove(entity.getBusinessName());
        return 1;
    }

    @Override
    public int deleteCatalogItem(CatalogItem entity) {
        catalogItemMap.remove(entity.getCatalogNumber());
        return 1;
    }

    @Override
    public int deleteOrder(Order entity) {
        orderMap.remove(entity.getOrderCode());
        deleteCoupon(entity.getCoupon());
        return 1;
    }

    @Override
    public int deleteCoupon(Coupon entity) {
        couponMap.remove(entity.getCouponCode());
        return 1;
    }

    @Override
    public Collection<User> getUsers() {
        return userMap.values();
    }

    @Override
    public Collection<Business> getBusinesses() {
        return businessMap.values();
    }

    @Override
    public Collection<Order> getOrders() {
        return orderMap.values();
    }

    @Override
    public Collection<Coupon> getCoupons() {
        return couponMap.values();
    }

    @Override
    public Collection<CatalogItem> getCatalogItems() {
        return catalogItemMap.values();
    }
}
