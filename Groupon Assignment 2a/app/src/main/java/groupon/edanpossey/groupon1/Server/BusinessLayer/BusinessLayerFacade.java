package groupon.edanpossey.groupon1.Server.BusinessLayer;

import groupon.edanpossey.groupon1.Entities.Business;
import groupon.edanpossey.groupon1.Entities.CatalogItem;
import groupon.edanpossey.groupon1.Entities.Coupon;
import groupon.edanpossey.groupon1.Entities.Order;
import groupon.edanpossey.groupon1.Entities.User;

/**
 * Created by IlayDavid on 17/05/2015.
 */
public interface BusinessLayerFacade {
    //================================
    //region New
    //================================
    public boolean newUser(User user);
    public boolean newBusiness(Business business);
    public boolean newOrder(Order order);
    public boolean newCatalogItem(CatalogItem catalogItem);
    public boolean newCoupon(Coupon coupon);
    //================================
    //endregion New
    //================================

    //================================
    //region Update
    //================================
    public boolean updateUser(User oldUser, User newUser);
    public boolean updateBusiness(Business oldBusiness, Business newBusiness);
    public boolean updateOrder(Order oldOrder, Order newOrder);
    public boolean updateCatalogItem(CatalogItem oldCatalogItem, CatalogItem newCatalogItem);
    public boolean updateCoupon(Coupon oldCoupon, Coupon newCoupon);
    //================================
    //endregion Update
    //================================

    //================================
    //region Delete
    //================================
    public boolean deleteUser(User user);
    public boolean deleteBusiness(Business business);
    public boolean deleteOrder(Order order);
    public boolean deleteCatalogItem(CatalogItem catalogItem);
    public boolean deleteCoupon(Coupon coupon);
    //================================
    //endregion Delete
    //================================

    //================================
    //region Use Cases
    //================================
    public User login(String userName, String password);
    public boolean approvePendingOrder(Order order);
    public boolean approvePendingCatalogItem(CatalogItem catalogItem);
    public boolean rateCatalogItem(CatalogItem catalogItem, long rating);
    //================================
    //endregion Use Cases
    //================================
}
