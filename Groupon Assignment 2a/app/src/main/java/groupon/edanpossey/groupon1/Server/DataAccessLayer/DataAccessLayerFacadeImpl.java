package groupon.edanpossey.groupon1.Server.DataAccessLayer;

import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.Map;

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

    public DataAccessLayerFacadeImpl(){
        userMap = new LinkedHashMap<String, User>();
        businessMap = new LinkedHashMap<String, Business>();
        catalogItemMap = new LinkedHashMap<Long, CatalogItem>();
        couponMap = new LinkedHashMap<Long, Coupon>();
        orderMap = new LinkedHashMap<Long, Order>();
    }

    @Override
    public boolean connectDatabase() {
        return true;
    }

    @Override
    public void closeDatabse() {

    }

    @Override
    public long insertCoupon(Coupon coupon) {
        if(couponMap.get(coupon.getCouponCode()) == null){

        }
        return 0;
    }

    @Override
    public long insertOrder(Order order) {
        return 0;
    }

    @Override
    public long insertCatalogItem(CatalogItem catalogItem) {
        return 0;
    }

    @Override
    public long insertBusiness(Business business) {
        return 0;
    }

    @Override
    public long insertUser(User user) {
        return 0;
    }

    @Override
    public long updateUser(User oldEntity, User newEntity) {
        return 0;
    }

    @Override
    public long updateBusiness(Business oldEntity, Business newEntity) {
        return 0;
    }

    @Override
    public long updateCatalogItem(CatalogItem oldEntity, CatalogItem newEntity) {
        return 0;
    }

    @Override
    public long updateOrder(Order oldEntity, Order newEntity) {
        return 0;
    }

    @Override
    public long updateCoupon(Coupon oldEntity, Coupon newEntity) {
        return 0;
    }

    @Override
    public int deleteUser(User entity) {
        return 0;
    }

    @Override
    public int deleteBusiness(Business entity) {
        return 0;
    }

    @Override
    public int deleteCatalogItem(CatalogItem entity) {
        return 0;
    }

    @Override
    public int deleteOrder(Order entity) {
        return 0;
    }

    @Override
    public int deleteCoupon(Coupon entity) {
        return 0;
    }

    @Override
    public Collection<User> getUsers() {
        return null;
    }

    @Override
    public Collection<Business> getBusinesses() {
        return null;
    }

    @Override
    public Collection<Order> getOrders() {
        return null;
    }

    @Override
    public Collection<Coupon> getCoupons() {
        return null;
    }

    @Override
    public Collection<CatalogItem> getCatalogItems() {
        return null;
    }
}
