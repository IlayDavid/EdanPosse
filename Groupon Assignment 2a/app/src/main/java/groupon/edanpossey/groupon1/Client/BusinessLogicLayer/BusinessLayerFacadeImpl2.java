package groupon.edanpossey.groupon1.Client.BusinessLogicLayer;

import java.util.Collection;

import groupon.edanpossey.groupon1.Entities.Business;
import groupon.edanpossey.groupon1.Entities.CatalogItem;
import groupon.edanpossey.groupon1.Entities.Order;
import groupon.edanpossey.groupon1.Entities.User;
import groupon.edanpossey.groupon1.Server.ServiceLayer.ServiceLayerFacade;
import groupon.edanpossey.groupon1.Server.ServiceLayer.ServiceLayerFacadeImpl;

/**
 * Created by El on 20/07/2015.
 */
public class BusinessLayerFacadeImpl2 implements BusinessLayerFacade {
    private ServiceLayerFacade serviceLayerFacade;

    public BusinessLayerFacadeImpl2(){
        this.serviceLayerFacade = new ServiceLayerFacadeImpl();
    }

    @Override
    public User login(String username, String password) {
        return serviceLayerFacade.login(username, password);
    }

    @Override
    public boolean newUser(User user) {
        return serviceLayerFacade.newUser(user);
    }

    @Override
    public boolean newBusiness(Business business) {
        return serviceLayerFacade.newBusiness(business);
    }

    @Override
    public boolean newOrder(Order order) {
        return serviceLayerFacade.newOrder(order);
    }

    @Override
    public boolean newCatalogItem(CatalogItem catalogItem) {
        return serviceLayerFacade.newCatalogItem(catalogItem);
    }

    @Override
    public boolean approvePendingCatalogItem(CatalogItem catalogItem) {
        return serviceLayerFacade.approvePendingCatalogItem(catalogItem);
    }

    @Override
    public boolean updateBusiness(Business oldBus, Business newBus) {
        return serviceLayerFacade.updateBusiness(oldBus, newBus);
    }

    @Override
    public boolean updateCatalogItem(CatalogItem oldItem, CatalogItem newItem) {
        return serviceLayerFacade.updateCatalogItem(oldItem, newItem);
    }

    @Override
    public boolean deleteBusiness(Business business) {
        return serviceLayerFacade.deleteBusiness(business);
    }

    @Override
    public boolean deleteUser(User user) {
        return serviceLayerFacade.deleteUser(user);
    }

    @Override
    public boolean deleteCatalogItem(CatalogItem catalogItem) {
        return serviceLayerFacade.deleteCatalogItem(catalogItem);
    }

    @Override
    public boolean rateCatalogItem(CatalogItem catalogItem, long rating) {
        return serviceLayerFacade.rateCatalogItem(catalogItem, rating);
    }

    @Override
    public boolean updateUser(User userOld, User userNew) {
        return serviceLayerFacade.updateUser(userOld, userNew);
    }

    @Override
    public Collection<CatalogItem> getCatalogItemsByPreferences(String categories, String city, int radius, double longitude, double latitude) {
        return serviceLayerFacade.getCatalogItemsByPreferences(categories, city, radius, longitude, latitude);
    }

    @Override
    public Collection<Business> getBusinessesByPreference(String categories, String city, int radius, double longitude, double latitude) {
        return serviceLayerFacade.getBusinessesByPreference(categories, city, radius, longitude, latitude);
    }

    @Override
    public Collection<CatalogItem> getPendingCatalogItems() {
        return serviceLayerFacade.getPendingCatalogItems();
    }
}
