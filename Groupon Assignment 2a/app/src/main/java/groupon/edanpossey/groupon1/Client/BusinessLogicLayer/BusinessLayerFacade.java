package groupon.edanpossey.groupon1.Client.BusinessLogicLayer;

import groupon.edanpossey.groupon1.Entities.Business;
import groupon.edanpossey.groupon1.Entities.CatalogItem;
import groupon.edanpossey.groupon1.Entities.Order;
import groupon.edanpossey.groupon1.Entities.User;

/**
 * Created by IlayDavid on 17/05/2015.
 */
public interface BusinessLayerFacade {
    //==============================================================================================
    //region Methods
    //==============================================================================================
    User login(String username, String password);

    boolean newUser(User user);

    boolean newBusiness(Business business);

    boolean newOrder(Order order);

    boolean newCatalogItem(CatalogItem catalogItem);

    boolean approvePendingOrder(Order order);

    boolean approvePendingCatalogItem(CatalogItem catalogItem);

    boolean updateBusiness(Business oldBus, Business newBus);

    boolean updateCatalogItem(CatalogItem oldItem, CatalogItem newItem);

    boolean deleteBusiness(Business business);

    boolean deleteUser(User user);

    boolean deleteCatalogItem(CatalogItem catalogItem);

    boolean rateCatalogItem(CatalogItem catalogItem, long rating);


    //==============================================================================================
    //endregion Methods
    //==============================================================================================
}
