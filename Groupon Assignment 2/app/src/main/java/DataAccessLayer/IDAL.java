package DataAccessLayer;

import java.util.Collection;

import Entities.Business;
import Entities.CatalogItem;
import Entities.Coupon;
import Entities.Order;
import Entities.User;

/**
 * Created by IlayDavid on 13/05/2015.
 */
public interface IDAL {
    //==============================================================================================
    //region Database Connection
    //==============================================================================================

    boolean connectDatabase();

    void closeDatabse();
    //==============================================================================================
    //endregion Database Connection
    //==============================================================================================

    //==============================================================================================
    //region Insertion
    //==============================================================================================

    long insertCoupon(Coupon coupon);

    long insertOrder(Order order);

    long insertCatalogItem(CatalogItem catalogItem);

    long insertBusiness(Business business);

    long insertUser(User user);

    //==============================================================================================
    //endregion Insertion
    //==============================================================================================

    //==============================================================================================
    //region Update
    //==============================================================================================

    long updateUser(User oldEntity, User newEntity);

    long updateBusiness(Business oldEntity, Business newEntity);

    long updateCatalogItem(CatalogItem oldEntity, CatalogItem newEntity);

    long updateOrder(Order oldEntity, Order newEntity);

    long updateCoupon(Coupon oldEntity, Coupon newEntity);

    //==============================================================================================
    //endregion Update
    //==============================================================================================

    //==============================================================================================
    //region Delete
    //==============================================================================================

    int deleteUser(User entity);

    int deleteBusiness(Business entity);

    int deleteCatalogItem(CatalogItem entity);

    int deleteOrder(Order entity);

    int deleteCoupon(Coupon entity);

    //==============================================================================================
    //endregion Delete
    //==============================================================================================

    //==============================================================================================
    //region Collection Getters
    //==============================================================================================

    Collection<User> getUsers();

    Collection<Business> getBusinesses();

    Collection<Order> getOrders();

    Collection<Coupon> getCoupons();

    Collection<CatalogItem> getCatalogItems();

    //==============================================================================================
    //endregion Collection Getters
    //==============================================================================================
}
