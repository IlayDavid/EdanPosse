package DataAccessLayer;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.sql.Date;
import java.util.Collection;
import java.util.LinkedList;

import Entities.AccessLevel;
import Entities.Business;
import Entities.CatalogItem;
import Entities.Coupon;
import Entities.Order;
import Entities.User;

/**
 * Created by IlayDavid on 13/05/2015.
 */
public class DAL_LocalDB implements IDAL {
    private GrouponDBHelper dbHelper;
    private SQLiteDatabase myDb;


    public DAL_LocalDB(Context context) {
        dbHelper = GrouponDBHelper.getInstance(context.getApplicationContext());
        myDb = null;
    }

    //==============================================================================================
    //region Database Connection
    //==============================================================================================

    public boolean connectDatabase() {
        myDb = dbHelper.getWritableDatabase();
        return myDb.isOpen();
    }

    public void closeDatabse() {
        myDb.close();
    }
    //==============================================================================================
    //endregion Database Connection
    //==============================================================================================

    //==============================================================================================
    //region Insertion
    //==============================================================================================

    public long insertCoupon(Coupon coupon) {
        ContentValues cv = new ContentValues();

        if (coupon.getCatalogItem() != null)
            cv.put(GrouponDBHelper.COUPONS_COLUMN_CATALOGNUMBER, coupon.getCatalogItem().getCatalogNumber());
        if (coupon.getStatus() != null)
            cv.put(GrouponDBHelper.COUPONS_COLUMN_STATUS, coupon.getStatus().toString());
        if (coupon.getCouponCode() > -1)
            cv.put(GrouponDBHelper.COUPONS_COLUMN_COUPONCODE, coupon.getCouponCode());

        return myDb.insert(GrouponDBHelper.COUPONS_TABLE_NAME, null, cv);
    }

    public long insertOrder(Order order) {
        ContentValues cv = new ContentValues();

        if (order.getOrderedBy() != null)
            cv.put(GrouponDBHelper.ORDERS_COLUMN_ID, order.getOrderedBy().getId());
        if (order.getOrderStatus() != null)
            cv.put(GrouponDBHelper.ORDERS_COLUMN_STATUS, order.getOrderStatus().toString());
        if (order.getCatalogItem() != null)
            cv.put(GrouponDBHelper.ORDERS_COLUMN_CATALOGNUMBER, order.getCatalogItem().getCatalogNumber());
        if (order.getCoupon() != null)
            cv.put(GrouponDBHelper.ORDERS_COLUMN_COUPONCODE, order.getCoupon().getCouponCode());
        if (order.getOrderCode() > -1)
            cv.put(GrouponDBHelper.ORDERS_COLUMN_ORDERCODE, order.getOrderCode());

        return myDb.insert(GrouponDBHelper.ORDERS_TABLE_NAME, null, cv);

    }

    public long insertCatalogItem(CatalogItem catalogItem) {
        ContentValues cv = new ContentValues();

        if (catalogItem.getPublishedBy() != null)
            cv.put(GrouponDBHelper.CATALOG_COLUMN_BUSINESSNAME, catalogItem.getPublishedBy().getBusinessName());
        if (catalogItem.getCategory() != null)
            cv.put(GrouponDBHelper.CATALOG_COLUMN_CATEGORY, catalogItem.getCategory());
        if (catalogItem.getDescription() != null)
            cv.put(GrouponDBHelper.CATALOG_COLUMN_DESCRIPTION, catalogItem.getDescription());
        if (catalogItem.getStatus() != null)
            cv.put(GrouponDBHelper.CATALOG_COLUMN_STATUS, catalogItem.getStatus().toString());
        if (catalogItem.getRatings() > -1)
            cv.put(GrouponDBHelper.CATALOG_COLUMN_RATINGS, catalogItem.getRatings());
        if (catalogItem.getSumOfRatings() > -1)
            cv.put(GrouponDBHelper.CATALOG_COLUMN_SUMOFRATINGS, catalogItem.getSumOfRatings());
        if (catalogItem.getOriginalPrice() > -1)
            cv.put(GrouponDBHelper.CATALOG_COLUMN_ORIGINALPRICE, catalogItem.getOriginalPrice());
        if (catalogItem.getPriceAfterDiscount() > -1)
            cv.put(GrouponDBHelper.CATALOG_COLUMN_PRICEAFTERDISCOUNT, catalogItem.getPriceAfterDiscount());
        if (catalogItem.getExpirationDate() != null)
            cv.put(GrouponDBHelper.CATALOG_COLUMN_EXPIRATIONDATE, catalogItem.getExpirationDate().toString());
        if (catalogItem.getCatalogNumber() > -1)
            cv.put(GrouponDBHelper.CATALOG_COLUMN_CATALOGNUMBER, catalogItem.getCatalogNumber());

        return myDb.insert(GrouponDBHelper.CATALOG_TABLE_NAME, null, cv);
    }

    public long insertBusiness(Business business) {
        ContentValues cv = new ContentValues();

        if (business.getOwner() != null)
            cv.put(GrouponDBHelper.BUSINESSES_COLUMN_ID, business.getOwner().getId());
        if (business.getBusinessName() != null)
            cv.put(GrouponDBHelper.BUSINESSES_COLUMN_BUSINESSNAME, business.getBusinessName());
        if (business.getAddress() != null)
            cv.put(GrouponDBHelper.BUSINESSES_COLUMN_ADDRESS, business.getAddress());
        if (business.getCity() != null)
            cv.put(GrouponDBHelper.BUSINESSES_COLUMN_CITY, business.getCity());
        if (business.getDescription() != null)
            cv.put(GrouponDBHelper.BUSINESSES_COLUMN_DESCRIPTION, business.getDescription());

        return myDb.insert(GrouponDBHelper.BUSINESSES_TABLE_NAME, null, cv);
    }

    public long insertUser(User user) {
        ContentValues cv = new ContentValues();

        if (user.getId() != null)
            cv.put(GrouponDBHelper.USERS_COLUMN_ID, user.getId());
        if (user.getPassword() != null)
            cv.put(GrouponDBHelper.USERS_COLUMN_PASSWORD, user.getPassword());
        if (user.getEmail() != null)
            cv.put(GrouponDBHelper.USERS_COLUMN_EMAIL, user.getEmail());
        if (user.getPhoneNumber() != null)
            cv.put(GrouponDBHelper.USERS_COLUMN_PHONE, user.getPhoneNumber());
        if (user.getAccessLevel() != null)
            cv.put(GrouponDBHelper.USERS_COLUMN_PERMISSION, user.getAccessLevel().ordinal());

        return myDb.insert(GrouponDBHelper.USERS_TABLE_NAME, null, cv);
    }

    //==============================================================================================
    //endregion Insertion
    //==============================================================================================

    //==============================================================================================
    //region Update
    //==============================================================================================

    public long updateUser(User oldEntity, User newEntity) {
        ContentValues cv = new ContentValues();

        if (newEntity.getId() != null)
            cv.put(GrouponDBHelper.USERS_COLUMN_ID, newEntity.getId());
        if (newEntity.getPassword() != null)
            cv.put(GrouponDBHelper.USERS_COLUMN_PASSWORD, newEntity.getPassword());
        if (newEntity.getEmail() != null)
            cv.put(GrouponDBHelper.USERS_COLUMN_EMAIL, newEntity.getEmail());
        if (newEntity.getPhoneNumber() != null)
            cv.put(GrouponDBHelper.USERS_COLUMN_PHONE, newEntity.getPhoneNumber());
        if (newEntity.getAccessLevel() != null)
            cv.put(GrouponDBHelper.USERS_COLUMN_PERMISSION, newEntity.getAccessLevel().ordinal());

        return myDb.update(GrouponDBHelper.USERS_TABLE_NAME, cv, GrouponDBHelper.USERS_COLUMN_ID + "=" + oldEntity.getId(), null);
    }

    public long updateBusiness(Business oldEntity, Business newEntity) {
        ContentValues cv = new ContentValues();

        if (newEntity.getOwner() != null)
            cv.put(GrouponDBHelper.BUSINESSES_COLUMN_ID, newEntity.getOwner().getId());
        if (newEntity.getBusinessName() != null)
            cv.put(GrouponDBHelper.BUSINESSES_COLUMN_BUSINESSNAME, newEntity.getBusinessName());
        if (newEntity.getAddress() != null)
            cv.put(GrouponDBHelper.BUSINESSES_COLUMN_ADDRESS, newEntity.getAddress());
        if (newEntity.getCity() != null)
            cv.put(GrouponDBHelper.BUSINESSES_COLUMN_CITY, newEntity.getCity());
        if (newEntity.getDescription() != null)
            cv.put(GrouponDBHelper.BUSINESSES_COLUMN_DESCRIPTION, newEntity.getDescription());

        return myDb.update(GrouponDBHelper.BUSINESSES_TABLE_NAME, cv, GrouponDBHelper.BUSINESSES_COLUMN_BUSINESSNAME + "=" + oldEntity.getBusinessName(), null);
    }

    public long updateCatalogItem(CatalogItem oldEntity, CatalogItem newEntity) {
        ContentValues cv = new ContentValues();

        if (newEntity.getPublishedBy() != null)
            cv.put(GrouponDBHelper.CATALOG_COLUMN_BUSINESSNAME, newEntity.getPublishedBy().getBusinessName());
        if (newEntity.getCategory() != null)
            cv.put(GrouponDBHelper.CATALOG_COLUMN_CATEGORY, newEntity.getCategory());
        if (newEntity.getDescription() != null)
            cv.put(GrouponDBHelper.CATALOG_COLUMN_DESCRIPTION, newEntity.getDescription());
        if (newEntity.getStatus() != null)
            cv.put(GrouponDBHelper.CATALOG_COLUMN_STATUS, newEntity.getStatus().toString());
        if (newEntity.getRatings() > -1)
            cv.put(GrouponDBHelper.CATALOG_COLUMN_RATINGS, newEntity.getRatings());
        if(newEntity.getSumOfRatings() > -1)
            cv.put(GrouponDBHelper.CATALOG_COLUMN_SUMOFRATINGS, newEntity.getSumOfRatings());
        if (newEntity.getOriginalPrice() > -1)
            cv.put(GrouponDBHelper.CATALOG_COLUMN_ORIGINALPRICE, newEntity.getOriginalPrice());
        if (newEntity.getPriceAfterDiscount() > -1)
            cv.put(GrouponDBHelper.CATALOG_COLUMN_PRICEAFTERDISCOUNT, newEntity.getPriceAfterDiscount());
        if (newEntity.getExpirationDate() != null)
            cv.put(GrouponDBHelper.CATALOG_COLUMN_EXPIRATIONDATE, newEntity.getExpirationDate().toString());
        if (newEntity.getCatalogNumber() > -1)
            cv.put(GrouponDBHelper.CATALOG_COLUMN_CATALOGNUMBER, newEntity.getCatalogNumber());

        return myDb.update(GrouponDBHelper.CATALOG_TABLE_NAME, cv, GrouponDBHelper.CATALOG_COLUMN_CATALOGNUMBER + "=" + oldEntity.getCatalogNumber(), null);
    }

    public long updateOrder(Order oldEntity, Order newEntity) {
        ContentValues cv = new ContentValues();

        if (newEntity.getOrderedBy() != null)
            cv.put(GrouponDBHelper.ORDERS_COLUMN_ID, newEntity.getOrderedBy().getId());
        if (newEntity.getOrderStatus() != null)
            cv.put(GrouponDBHelper.ORDERS_COLUMN_STATUS, newEntity.getOrderStatus().toString());
        if (newEntity.getCatalogItem() != null)
            cv.put(GrouponDBHelper.ORDERS_COLUMN_CATALOGNUMBER, newEntity.getCatalogItem().getCatalogNumber());
        if (newEntity.getCoupon() != null)
            cv.put(GrouponDBHelper.ORDERS_COLUMN_COUPONCODE, newEntity.getCoupon().getCouponCode());
        if (newEntity.getOrderCode() > -1)
            cv.put(GrouponDBHelper.ORDERS_COLUMN_ORDERCODE, newEntity.getOrderCode());

        return myDb.update(GrouponDBHelper.ORDERS_TABLE_NAME, cv, GrouponDBHelper.ORDERS_COLUMN_ORDERCODE + "=" + oldEntity.getOrderCode(), null);
    }

    public long updateCoupon(Coupon oldEntity, Coupon newEntity) {
        ContentValues cv = new ContentValues();

        if (newEntity.getCatalogItem() != null)
            cv.put(GrouponDBHelper.COUPONS_COLUMN_CATALOGNUMBER, newEntity.getCatalogItem().getCatalogNumber());
        if (newEntity.getStatus() != null)
            cv.put(GrouponDBHelper.COUPONS_COLUMN_STATUS, newEntity.getStatus().toString());
        if (newEntity.getCouponCode() > -1)
            cv.put(GrouponDBHelper.COUPONS_COLUMN_COUPONCODE, newEntity.getCouponCode());

        return myDb.update(GrouponDBHelper.COUPONS_TABLE_NAME, cv, GrouponDBHelper.COUPONS_COLUMN_COUPONCODE + "=" + oldEntity.getCouponCode(), null);
    }

    //==============================================================================================
    //endregion Update
    //==============================================================================================

    //==============================================================================================
    //region Delete
    //==============================================================================================

    public int deleteUser(User entity) {
        return myDb.delete(GrouponDBHelper.USERS_TABLE_NAME, GrouponDBHelper.USERS_COLUMN_ID + "=" + entity.getId(), null);
    }

    public int deleteBusiness(Business entity) {
        return myDb.delete(GrouponDBHelper.BUSINESSES_TABLE_NAME, GrouponDBHelper.BUSINESSES_COLUMN_BUSINESSNAME + "=" + entity.getBusinessName(), null);
    }

    public int deleteCatalogItem(CatalogItem entity) {
        return myDb.delete(GrouponDBHelper.CATALOG_TABLE_NAME, GrouponDBHelper.CATALOG_COLUMN_CATALOGNUMBER + "=" + entity.getCatalogNumber(), null);
    }

    public int deleteOrder(Order entity) {
        return myDb.delete(GrouponDBHelper.ORDERS_TABLE_NAME, GrouponDBHelper.ORDERS_COLUMN_ORDERCODE + "=" + entity.getOrderCode(), null);
    }

    public int deleteCoupon(Coupon entity) {
        return myDb.delete(GrouponDBHelper.COUPONS_TABLE_NAME, GrouponDBHelper.COUPONS_COLUMN_COUPONCODE + "=" + entity.getCouponCode(), null);
    }

    //==============================================================================================
    //endregion Delete
    //==============================================================================================

    //==============================================================================================
    //region Collection Getters
    //==============================================================================================

    public Collection<User> getUsers() {
        Collection<User> users = new LinkedList<User>();
        Cursor cursorUsers = myDb.query(GrouponDBHelper.USERS_TABLE_NAME, GrouponDBHelper.USER_COLUMNS, null, null, null, null, GrouponDBHelper.USERS_COLUMN_ID);
        if (cursorUsers.moveToFirst()) {
            while (cursorUsers.isAfterLast() == false) {
                String id = cursorUsers.getString(cursorUsers.getColumnIndex(GrouponDBHelper.USERS_COLUMN_ID));
                String password = cursorUsers.getString(cursorUsers.getColumnIndex(GrouponDBHelper.USERS_COLUMN_PASSWORD));
                String email = cursorUsers.getString(cursorUsers.getColumnIndex(GrouponDBHelper.USERS_COLUMN_EMAIL));
                String phone = cursorUsers.getString(cursorUsers.getColumnIndex(GrouponDBHelper.USERS_COLUMN_PHONE));
                AccessLevel accessLevel = AccessLevel.valueOf(cursorUsers.getString(cursorUsers.getColumnIndex(GrouponDBHelper.USERS_COLUMN_PERMISSION)));

                User user = new User(id, password, email, phone, accessLevel, null);
                users.add(user);
                cursorUsers.moveToNext();
                cursorUsers.moveToNext();
            }
        }

        return users;
    }

    public Collection<Business> getBusinesses() {
        Collection<Business> businesses = new LinkedList<Business>();
        Cursor cursorBusiness = myDb.query(GrouponDBHelper.BUSINESSES_TABLE_NAME, GrouponDBHelper.BUSINESS_COLUMNS, null, null, null, null, GrouponDBHelper.BUSINESSES_COLUMN_BUSINESSNAME);
        if (cursorBusiness.moveToFirst()) {
            while (cursorBusiness.isAfterLast() == false) {
                String businessName = cursorBusiness.getString(cursorBusiness.getColumnIndex(GrouponDBHelper.BUSINESSES_COLUMN_BUSINESSNAME));
                String id = cursorBusiness.getString(cursorBusiness.getColumnIndex(GrouponDBHelper.BUSINESSES_COLUMN_ID));
                String address = cursorBusiness.getString(cursorBusiness.getColumnIndex(GrouponDBHelper.BUSINESSES_COLUMN_ADDRESS));
                String city = cursorBusiness.getString(cursorBusiness.getColumnIndex(GrouponDBHelper.BUSINESSES_COLUMN_CITY));
                String description = cursorBusiness.getString(cursorBusiness.getColumnIndex(GrouponDBHelper.BUSINESSES_COLUMN_DESCRIPTION));

                User user = new User(id, null, null, null, null, null);
                Business business = new Business(user, businessName, address, city, description);
                businesses.add(business);
            }
        }

        return businesses;
    }

    public Collection<Order> getOrders() {
        Collection<Order> orders = new LinkedList<Order>();
        Cursor cursorOrders = myDb.query(GrouponDBHelper.ORDERS_TABLE_NAME, GrouponDBHelper.ORDER_COLUMNS, null, null, null, null, GrouponDBHelper.ORDERS_COLUMN_ORDERCODE);
        if (cursorOrders.moveToFirst()) {
            while (cursorOrders.isAfterLast() == false) {
                long orderCode = cursorOrders.getLong(cursorOrders.getColumnIndex(GrouponDBHelper.ORDERS_COLUMN_ORDERCODE));
                long catalogNumber = cursorOrders.getLong(cursorOrders.getColumnIndex(GrouponDBHelper.ORDERS_COLUMN_CATALOGNUMBER));
                long couponCode;
                if(cursorOrders.isNull(cursorOrders.getColumnIndex(GrouponDBHelper.ORDERS_COLUMN_COUPONCODE)))
                    couponCode = -1;
                else
                    couponCode = cursorOrders.getLong(cursorOrders.getColumnIndex(GrouponDBHelper.ORDERS_COLUMN_COUPONCODE));
                String id = cursorOrders.getString(cursorOrders.getColumnIndex(GrouponDBHelper.ORDERS_COLUMN_ID));
                Order.OrderStatus status = Order.OrderStatus.valueOf(cursorOrders.getString(cursorOrders.getColumnIndex(GrouponDBHelper.ORDERS_COLUMN_STATUS)));

                CatalogItem catalogItem = new CatalogItem(catalogNumber, null, null, null, null, -1, -1, -1, -1, null);
                User user = new User(id, null, null, null, null, null);
                Coupon coupon = new Coupon(couponCode, null, null);
                Order order = new Order(orderCode, status, user, catalogItem, coupon);
                orders.add(order);
            }
        }

        return orders;
    }

    public Collection<Coupon> getCoupons() {
        Collection<Coupon> coupons = new LinkedList<Coupon>();
        Cursor cursorCoupons = myDb.query(GrouponDBHelper.COUPONS_TABLE_NAME, GrouponDBHelper.COUPON_COLUMNS, null, null, null, null, GrouponDBHelper.COUPONS_COLUMN_COUPONCODE);
        if (cursorCoupons.moveToFirst()) {
            while (cursorCoupons.isAfterLast() == false) {
                long couponCode = cursorCoupons.getLong(cursorCoupons.getColumnIndex(GrouponDBHelper.COUPONS_COLUMN_COUPONCODE));
                long catalogNumber = cursorCoupons.getLong(cursorCoupons.getColumnIndex(GrouponDBHelper.COUPONS_COLUMN_CATALOGNUMBER));
                Coupon.CouponStatus status = Coupon.CouponStatus.valueOf(cursorCoupons.getString(cursorCoupons.getColumnIndex(GrouponDBHelper.COUPONS_COLUMN_STATUS)));

                CatalogItem catalogItem = new CatalogItem(catalogNumber, null, null, null, null, -1, -1, -1, -1, null);
                Coupon coupon = new Coupon(couponCode, status, catalogItem);
                coupons.add(coupon);
            }
        }

        return coupons;
    }

    public Collection<CatalogItem> getCatalogItems() {
        Collection<CatalogItem> catalogItems = new LinkedList<CatalogItem>();
        Cursor cursorCatalog = myDb.query(GrouponDBHelper.CATALOG_TABLE_NAME, GrouponDBHelper.CATALOG_COLUMNS, null, null, null, null, GrouponDBHelper.CATALOG_COLUMN_RATINGS + " DESC");
        if (cursorCatalog.moveToFirst()) {
            while (cursorCatalog.isAfterLast() == false) {
                long catalogNumber = cursorCatalog.getLong(cursorCatalog.getColumnIndex(GrouponDBHelper.CATALOG_COLUMN_CATALOGNUMBER));
                String businessName = cursorCatalog.getString(cursorCatalog.getColumnIndex(GrouponDBHelper.CATALOG_COLUMN_BUSINESSNAME));
                String category = cursorCatalog.getString(cursorCatalog.getColumnIndex(GrouponDBHelper.CATALOG_COLUMN_CATEGORY));
                String description = cursorCatalog.getString(cursorCatalog.getColumnIndex(GrouponDBHelper.CATALOG_COLUMN_DESCRIPTION));
                double originalPrice = cursorCatalog.getDouble(cursorCatalog.getColumnIndex(GrouponDBHelper.CATALOG_COLUMN_ORIGINALPRICE));
                double priceAfterDiscount = cursorCatalog.getDouble(cursorCatalog.getColumnIndex(GrouponDBHelper.CATALOG_COLUMN_PRICEAFTERDISCOUNT));
                long rating = cursorCatalog.getLong(cursorCatalog.getColumnIndex(GrouponDBHelper.CATALOG_COLUMN_RATINGS));
                long averageRating = cursorCatalog.getLong(cursorCatalog.getColumnIndex(GrouponDBHelper.CATALOG_COLUMN_SUMOFRATINGS));
                CatalogItem.CatalogItemStatus status = CatalogItem.CatalogItemStatus.valueOf(cursorCatalog.getString(cursorCatalog.getColumnIndex(GrouponDBHelper.CATALOG_COLUMN_STATUS)));
                Date date = Date.valueOf(cursorCatalog.getString(cursorCatalog.getColumnIndex(GrouponDBHelper.CATALOG_COLUMN_EXPIRATIONDATE)));

                Business business = new Business(null, businessName, null, null, null);
                CatalogItem catalogItem = new CatalogItem(catalogNumber, business, category, description, status, rating, averageRating, originalPrice, priceAfterDiscount, date);
                catalogItems.add(catalogItem);
            }
        }

        return catalogItems;
    }

    //==============================================================================================
    //endregion Collection Getters
    //==============================================================================================

    //==============================================================================================
    //region Single Value Getters
    //==============================================================================================

    //==============================================================================================
    //endregion Single Value Getters
    //==============================================================================================
}
