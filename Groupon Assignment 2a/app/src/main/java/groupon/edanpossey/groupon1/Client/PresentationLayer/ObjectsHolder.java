package groupon.edanpossey.groupon1.Client.PresentationLayer;

import android.content.Context;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;

import java.util.ArrayList;
import java.util.Collection;

import groupon.edanpossey.groupon1.Client.BusinessLogicLayer.BusinessLayerFacadeFactory;
import groupon.edanpossey.groupon1.Client.BusinessLogicLayer.BusinessLayerFacade;
import groupon.edanpossey.groupon1.Client.BusinessLogicLayer.BusinessLayerFacadeImpl2;
import groupon.edanpossey.groupon1.Entities.Business;
import groupon.edanpossey.groupon1.Entities.CatalogItem;
import groupon.edanpossey.groupon1.Entities.Coupon;
import groupon.edanpossey.groupon1.Entities.Order;
import groupon.edanpossey.groupon1.Entities.User;

/**
 * Created by IlayDavid on 17/05/2015.
 */
public class ObjectsHolder {
    private static ObjectsHolder objectsHolder;
    private static BusinessLayerFacade bl;

    private static User currentUser;
    private static Business currentBusiness;
    private static CatalogItem currentCatalogItem;
    private static Order currentOrder;
    private static Coupon currentCoupon;

    private static Collection<Order> currentOrdersList;
    private static Collection<CatalogItem> currentCatalogItemsList;
    private static Collection<Business> currentBusinessesList;

    private static LocationManager locationManager;

    //==============================================================================================
    //region Constructors + Instance getter
    //==============================================================================================
    public static void initialize(Context context) {
        if (objectsHolder == null)
            objectsHolder = new ObjectsHolder(context);
    }

    private ObjectsHolder(Context context) {
        //bl = BusinessLayerFacadeFactory.getLayer(BusinessLayerFacadeFactory.BLType.LocalDB, context);
        bl = new BusinessLayerFacadeImpl2();
        currentUser = null;
        currentBusiness = null;
        currentCatalogItem = null;
        currentCoupon = null;
        currentOrder = null;
        currentOrdersList = null;
        currentCatalogItemsList = null;
        currentBusinessesList = null;

        locationManager = (LocationManager) context.getSystemService(Context.LOCATION_SERVICE);
        LocationListener locationListener = new LocationListener() {
            public void onLocationChanged(Location location) {
                // Called when a new location is found by the network location provider.
                makeUseOfNewLocation(location);
            }

            public void onStatusChanged(String provider, int status, Bundle extras) {}

            public void onProviderEnabled(String provider) {}

            public void onProviderDisabled(String provider) {}
        };

        locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, locationListener);
        //locationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 0, 0, locationListener);

    }

    private void makeUseOfNewLocation(Location location) {

    }
    //==============================================================================================
    //endregion Constructors + Instance getter
    //==============================================================================================

    //==============================================================================================
    //region Accessors
    //==============================================================================================

    public static User getCurrentUser() {
        return currentUser;
    }

    public static void setCurrentUser(User currentUser) {
        ObjectsHolder.currentUser = currentUser;
    }

    public static Collection<Business> getCurrentBusinessesList() {
        return currentBusinessesList;
    }

    public static void setCurrentBusinessesList(Collection<Business> currentBusinessesList) {
        ObjectsHolder.currentBusinessesList = currentBusinessesList;
    }

    public static Collection<CatalogItem> getCurrentCatalogItemsList() {
        return currentCatalogItemsList;
    }

    public static void setCurrentCatalogItemsList(Collection<CatalogItem> currentCatalogItemsList) {
        ObjectsHolder.currentCatalogItemsList = currentCatalogItemsList;
    }

    public static CatalogItem getCurrentCatalogItem() {
        return currentCatalogItem;
    }

    public static void setCurrentCatalogItem(CatalogItem currentCatalogItem) {
        ObjectsHolder.currentCatalogItem = currentCatalogItem;
    }

    public static Order getCurrentOrder() {
        return currentOrder;
    }

    public static void setCurrentOrder(Order currentOrder) {
        ObjectsHolder.currentOrder = currentOrder;
    }

    public static Coupon getCurrentCoupon() {
        return currentCoupon;
    }

    public static void setCurrentCoupon(Coupon currentCoupon) {
        ObjectsHolder.currentCoupon = currentCoupon;
    }

    public static Collection<Order> getCurrentOrdersList() {
        return currentOrdersList;
    }

    public static void setCurrentOrdersList(Collection<Order> currentOrdersList) {
        ObjectsHolder.currentOrdersList = currentOrdersList;
    }

    public static Business getCurrentBusiness() {
        return currentBusiness;
    }

    public static void setCurrentBusiness(Business currentBusiness) {
        ObjectsHolder.currentBusiness = currentBusiness;
    }

    public static BusinessLayerFacade getBl() {
        return bl;
    }

    public static LocationManager getLocationManager(){
        return locationManager;
    }

    //==============================================================================================
    //endregion Accessors
    //==============================================================================================
}
