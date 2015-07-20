package groupon.edanpossey.groupon1.Entities;

import android.location.Location;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

/**
 * Created by Owner on 16/05/2015.
 */
public class Business {
    private User owner;
    private Location location;
    private String businessName, address, city, description;
    private Collection<CatalogItem> catalogItems;
    private Collection<Order> orders;

    //==============================================================================================
    //region Constructors
    //==============================================================================================

    public Business() {
        this.owner = null;
        this.businessName = "";
        this.address = "";
        this.city = "";
        this.description = "";
        this.catalogItems = new ArrayList<CatalogItem>();
        this.orders = new ArrayList<Order>();
    }

    public Business(User owner, String businessName, String address, String city, String description) {
        this.owner = owner;
        this.businessName = businessName;
        this.address = address;
        this.city = city;
        this.description = description;
        this.catalogItems = new ArrayList<CatalogItem>();
        this.orders = new ArrayList<Order>();
    }

    //==============================================================================================
    //endregion Constructors
    //==============================================================================================

    //==============================================================================================
    //region Accessors
    //==============================================================================================

    public User getOwner() {
        return owner;
    }

    public void setOwner(User owner) {
        this.owner = owner;
    }

    public String getBusinessName() {
        return businessName;
    }

    public void setBusinessName(String businessName) {
        this.businessName = businessName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Collection<CatalogItem> getCatalogItems() {
        return catalogItems;
    }

    //==============================================================================================
    //endregion Accessors
    //==============================================================================================

    //==============================================================================================
    //region Collections' Manipulators
    //==============================================================================================

    public boolean addCatalogItem(CatalogItem catalogItem) {
        return this.catalogItems.add(catalogItem);
    }

    public boolean removeCatalogItem(CatalogItem catalogItem) {
        return this.catalogItems.remove(catalogItem);
    }

    public boolean removeCatalogItem(long catalogNumber) {
        for (Iterator<CatalogItem> iter = this.catalogItems.iterator(); iter.hasNext(); ) {
            CatalogItem currentCatalogItem = iter.next();
            if (currentCatalogItem.getCatalogNumber() == catalogNumber)
                return removeCatalogItem(currentCatalogItem);
        }
        return false;
    }

    public boolean addOrder(Order order){
        return this.orders.add(order);
    }

    public Collection<Order> getOrders() {
        return orders;
    }

    //==============================================================================================
    //endregion Collections' Manipulators
    //==============================================================================================
}
