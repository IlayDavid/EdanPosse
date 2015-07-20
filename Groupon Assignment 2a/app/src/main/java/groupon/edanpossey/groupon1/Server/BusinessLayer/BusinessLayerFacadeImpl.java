package groupon.edanpossey.groupon1.Server.BusinessLayer;

import java.util.ArrayList;
import java.util.Collection;

import groupon.edanpossey.groupon1.Entities.Business;
import groupon.edanpossey.groupon1.Entities.CatalogItem;
import groupon.edanpossey.groupon1.Entities.Coupon;
import groupon.edanpossey.groupon1.Entities.Order;
import groupon.edanpossey.groupon1.Entities.User;
import groupon.edanpossey.groupon1.Server.DataAccessLayer.DataAccessLayerFacade;
import groupon.edanpossey.groupon1.Server.DataAccessLayer.DataAccessLayerFacadeImpl;

/**
 * Created by El on 20/07/2015.
 */
public class BusinessLayerFacadeImpl implements BusinessLayerFacade {

    DataAccessLayerFacade dataAccessLayerFacade;
    public BusinessLayerFacadeImpl(){
        this.dataAccessLayerFacade = new DataAccessLayerFacadeImpl();
    }

    @Override
    public User login(String username, String password) {
        User user = dataAccessLayerFacade.getUser(username);
        if(user != null && user.getPassword().equals(password))
            return user;
        return null;
    }

    @Override
    public boolean newUser(User user) {
        return dataAccessLayerFacade.insertUser(user) > -1;
    }

    @Override
    public boolean newBusiness(Business business) {
        boolean ans = dataAccessLayerFacade.insertBusiness(business) > -1;
        if(ans)
            dataAccessLayerFacade.getUser(business.getOwner().getId()).setBusiness(business);
        return ans;
    }

    @Override
    public boolean newOrder(Order order) {
        boolean ans = dataAccessLayerFacade.insertOrder(order) > -1;
        if(ans){
            order.getCatalogItem().getOrders().add(order);
            order.getOrderedBy().addOrder(order);
            Coupon coupon = new Coupon();
            dataAccessLayerFacade.insertCoupon(coupon);
            coupon.setStatus(Coupon.CouponStatus.NotUsed);
            coupon.setCatalogItem(order.getCatalogItem());
            order.setCoupon(coupon);
            if(order.getCatalogItem().getType() == CatalogItem.CatalogItemType.NotSocial)
                order.getCatalogItem().getPublishedBy().addOrder(order);
        }
        return ans;
    }

    @Override
    public boolean newCatalogItem(CatalogItem catalogItem) {
        boolean ans = dataAccessLayerFacade.insertCatalogItem(catalogItem) > -1;
        if(ans){
            if(catalogItem.getType() == CatalogItem.CatalogItemType.NotSocial){
                catalogItem.getPublishedBy().addCatalogItem(catalogItem);
            }
        }
        return ans;
    }

    @Override
    public boolean approvePendingCatalogItem(CatalogItem catalogItem) {
        catalogItem.setStatus(CatalogItem.CatalogItemStatus.Approved);
        return true;
    }

    @Override
    public boolean updateBusiness(Business oldBus, Business newBus) {
        return dataAccessLayerFacade.updateBusiness(oldBus, newBus) > 0;
    }

    @Override
    public boolean updateCatalogItem(CatalogItem oldItem, CatalogItem newItem) {
        return dataAccessLayerFacade.updateCatalogItem(oldItem, newItem) > 0;
    }

    @Override
    public boolean deleteBusiness(Business business) {
        return dataAccessLayerFacade.deleteBusiness(business) > 0;
    }

    @Override
    public boolean deleteUser(User user) {
        return dataAccessLayerFacade.deleteUser(user) > 0;
    }

    @Override
    public boolean deleteCatalogItem(CatalogItem catalogItem) {
        return dataAccessLayerFacade.deleteCatalogItem(catalogItem) > 0;
    }

    @Override
    public boolean rateCatalogItem(CatalogItem catalogItem, long rating) {
        catalogItem.setSumOfRatings(catalogItem.getSumOfRatings() + rating);
        catalogItem.setRatings(catalogItem.getRatings() + 1);
        return true;
    }

    @Override
    public boolean updateUser(User userOld, User userNew) {
        return dataAccessLayerFacade.updateUser(userOld,userNew) > 0;
    }

    @Override
    public Collection<CatalogItem> getCatalogItemsByPreferences(String categories, String city, int radius, double longitude, double latitude) {
        Collection<CatalogItem> catalogItems = dataAccessLayerFacade.getCatalogItems();
        ArrayList<CatalogItem> byPreference = new ArrayList<CatalogItem>();
        for(CatalogItem catalogItem: catalogItems){
           if((int) Math.ceil(Math.random()*100)%2 == 0)
               byPreference.add(catalogItem);
        }
        return byPreference;
    }

    @Override
    public Collection<Business> getBusinessesByPreference(String categories, String city, int radius, double longitude, double latitude) {
        Collection<Business> businesses = dataAccessLayerFacade.getBusinesses();
        ArrayList<Business> byPreference = new ArrayList<Business>();
        for(Business business: businesses){
            if(((int) Math.ceil(Math.random()*100))%2 == 0)
                byPreference.add(business);
        }
        return byPreference;
    }

    @Override
    public Collection<CatalogItem> getPendingCatalogItems() {
        Collection<CatalogItem> catalogItems = dataAccessLayerFacade.getCatalogItems();
        ArrayList<CatalogItem> byPreference = new ArrayList<CatalogItem>();
        for(CatalogItem catalogItem: catalogItems){
            if(catalogItem.getStatus() == CatalogItem.CatalogItemStatus.PendingApproval)
                byPreference.add(catalogItem);
        }
        return byPreference;
    }
}
