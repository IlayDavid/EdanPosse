package groupon.edanpossey.groupon1.Server.ServiceLayer;

import java.util.Collection;

import groupon.edanpossey.groupon1.Entities.Business;
import groupon.edanpossey.groupon1.Entities.CatalogItem;
import groupon.edanpossey.groupon1.Entities.Order;
import groupon.edanpossey.groupon1.Entities.User;
import groupon.edanpossey.groupon1.Server.BusinessLayer.BusinessLayerFacade;
import groupon.edanpossey.groupon1.Server.BusinessLayer.BusinessLayerFacadeImpl;

/**
 * Created by El on 20/07/2015.
 */
public class ServiceLayerFacadeImpl implements ServiceLayerFacade {
    BusinessLayerFacade businessLayerFacade;
    public ServiceLayerFacadeImpl(){
        this.businessLayerFacade = new BusinessLayerFacadeImpl();
    }

    @Override
    public User login(String username, String password) {
        return businessLayerFacade.login(username, password);
    }

    @Override
    public boolean newUser(User user) {
        return businessLayerFacade.newUser(user);
    }

    @Override
    public boolean newBusiness(Business business) {
        return businessLayerFacade.newBusiness(business);
    }

    @Override
    public boolean newOrder(Order order) {
        return businessLayerFacade.newOrder(order);
    }

    @Override
    public boolean newCatalogItem(CatalogItem catalogItem) {
        return businessLayerFacade.newCatalogItem(catalogItem);
    }

    @Override
    public boolean approvePendingCatalogItem(CatalogItem catalogItem) {
        return businessLayerFacade.approvePendingCatalogItem(catalogItem);
    }

    @Override
    public boolean updateBusiness(Business oldBus, Business newBus) {
        return businessLayerFacade.updateBusiness(oldBus, newBus);
    }

    @Override
    public boolean updateCatalogItem(CatalogItem oldItem, CatalogItem newItem) {
        return businessLayerFacade.updateCatalogItem(oldItem, newItem);
    }

    @Override
    public boolean deleteBusiness(Business business) {
        return businessLayerFacade.deleteBusiness(business);
    }

    @Override
    public boolean deleteUser(User user) {
        return businessLayerFacade.deleteUser(user);
    }

    @Override
    public boolean deleteCatalogItem(CatalogItem catalogItem) {
        return businessLayerFacade.deleteCatalogItem(catalogItem);
    }

    @Override
    public boolean rateCatalogItem(CatalogItem catalogItem, long rating) {
        return businessLayerFacade.rateCatalogItem(catalogItem, rating);
    }

    @Override
    public boolean updateUser(User userOld, User userNew) {
        return businessLayerFacade.updateUser(userOld, userNew);
    }

    @Override
    public Collection<CatalogItem> getCatalogItemsByPreferences(String categories, String city, int radius, double longitude, double latitude) {
        return businessLayerFacade.getCatalogItemsByPreferences(categories, city, radius, longitude, latitude);
    }

    @Override
    public Collection<Business> getBusinessesByPreference(String categories, String city, int radius, double longitude, double latitude) {
        return businessLayerFacade.getBusinessesByPreference(categories, city, radius, longitude, latitude);
    }

    @Override
    public Collection<CatalogItem> getPendingCatalogItems() {
        return businessLayerFacade.getPendingCatalogItems();
    }
}
