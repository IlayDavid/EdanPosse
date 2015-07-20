package groupon.edanpossey.groupon1.Client.DataAccessLayer;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import groupon.edanpossey.groupon1.Entities.AccessLevel;


/**
 * Created by IlayDavid on 17/05/2015.
 */
public class GrouponDBHelper extends SQLiteOpenHelper {
    private static GrouponDBHelper instance;

    public static final String DATABASE_NAME = "GrouponDB.db";
    public static final int DATABASE_VERSION = 1;

    public static final String USERS_TABLE_NAME = "Users";
    public static final String USERS_COLUMN_ID = "id";                              // Primary Key
    public static final String USERS_COLUMN_PASSWORD = "password";
    public static final String USERS_COLUMN_EMAIL = "email";
    public static final String USERS_COLUMN_PHONE = "phone";
    public static final String USERS_COLUMN_PERMISSION = "permission";
    public static final String[] USER_COLUMNS = {USERS_COLUMN_ID, USERS_COLUMN_PASSWORD,
            USERS_COLUMN_EMAIL, USERS_COLUMN_PHONE, USERS_COLUMN_PERMISSION};

    public static final String BUSINESSES_TABLE_NAME = "Businesses";
    public static final String BUSINESSES_COLUMN_ID = "id";                         // References Users.id
    public static final String BUSINESSES_COLUMN_BUSINESSNAME = "businessName";     // Primary Key
    public static final String BUSINESSES_COLUMN_CITY = "city";
    public static final String BUSINESSES_COLUMN_ADDRESS = "address";
    public static final String BUSINESSES_COLUMN_DESCRIPTION = "description";
    public static final String BUSINESSES_COLUMN_COORDINATES = "coordinates";
    public static final String[] BUSINESS_COLUMNS =
            {BUSINESSES_COLUMN_BUSINESSNAME, BUSINESSES_COLUMN_ID,
                    BUSINESSES_COLUMN_CITY, BUSINESSES_COLUMN_ADDRESS, BUSINESSES_COLUMN_DESCRIPTION, BUSINESSES_COLUMN_COORDINATES};

    public static final String CATALOG_TABLE_NAME = "Catalog";
    public static final String CATALOG_COLUMN_BUSINESSNAME = "businessName";        // References Businesses.businessName
    public static final String CATALOG_COLUMN_CATALOGNUMBER = "catalogNumber";      // Primary Key
    public static final String CATALOG_COLUMN_CATALONGITEMNAME = "catalogItemName";
    public static final String CATALOG_COLUMN_CATEGORY = "category";
    public static final String CATALOG_COLUMN_DESCRIPTION = "description";
    public static final String CATALOG_COLUMN_STATUS = "status";
    public static final String CATALOG_COLUMN_RATINGS = "ratings";
    public static final String CATALOG_COLUMN_SUMOFRATINGS = "sumOfRatings";
    public static final String CATALOG_COLUMN_ORIGINALPRICE = "originalPrice";
    public static final String CATALOG_COLUMN_PRICEAFTERDISCOUNT = "priceAfterDiscount";
    public static final String CATALOG_COLUMN_EXPIRATIONDATE = "expirationDate";
    public static final String CATALOG_COLUMN_TYPE = "type";
    public static final String[] CATALOG_COLUMNS = {CATALOG_COLUMN_CATALOGNUMBER, CATALOG_COLUMN_BUSINESSNAME,
            CATALOG_COLUMN_CATEGORY,CATALOG_COLUMN_CATALONGITEMNAME, CATALOG_COLUMN_DESCRIPTION,
            CATALOG_COLUMN_STATUS, CATALOG_COLUMN_RATINGS, CATALOG_COLUMN_SUMOFRATINGS,
            CATALOG_COLUMN_ORIGINALPRICE, CATALOG_COLUMN_PRICEAFTERDISCOUNT,
            CATALOG_COLUMN_EXPIRATIONDATE, CATALOG_COLUMN_TYPE};

    public static final String ORDERS_TABLE_NAME = "Orders";
    public static final String ORDERS_COLUMN_ID = "id";                             // References Users.id
    public static final String ORDERS_COLUMN_ORDERCODE = "orderCode";               // Primary Key
    public static final String ORDERS_COLUMN_STATUS = "status";
    public static final String ORDERS_COLUMN_CATALOGNUMBER = "catalogNumber";       // References Catalog.catalogNumber
    public static final String ORDERS_COLUMN_COUPONCODE = "couponCode";             // References Coupon.couponCode
    public static final String[] ORDER_COLUMNS =
            {ORDERS_COLUMN_ID, ORDERS_COLUMN_ORDERCODE, ORDERS_COLUMN_STATUS,
                    ORDERS_COLUMN_CATALOGNUMBER, ORDERS_COLUMN_COUPONCODE};

    public static final String COUPONS_TABLE_NAME = "Coupons";
    public static final String COUPONS_COLUMN_CATALOGNUMBER = "catalogNumber";      // References Catalog.catalogNumber
    public static final String COUPONS_COLUMN_COUPONCODE = "couponCode";            // Primary Key
    public static final String COUPONS_COLUMN_STATUS = "status";
    public static final String[] COUPON_COLUMNS = {COUPONS_COLUMN_CATALOGNUMBER,
            COUPONS_COLUMN_COUPONCODE, COUPONS_COLUMN_STATUS};

    public static GrouponDBHelper getInstance(Context context) {
        if (instance == null) {
            instance = new GrouponDBHelper(context);
        }

        return instance;
    }

    private GrouponDBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }


    /**
     * Called when the database is created for the first time. This is where the
     * creation of tables and the initial population of the tables should happen.
     *
     * @param db The database.
     */
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(
                "CREATE TABLE Users " +
                        "(id TEXT PRIMARY KEY, password TEXT, email TEXT, phone TEXT, permission TEXT)"
        );
        ContentValues cv = new ContentValues();
        cv.put(USERS_COLUMN_ID, "admin");
        cv.put(USERS_COLUMN_PASSWORD, "adminpass");
        cv.put(USERS_COLUMN_EMAIL, "email@groupon.com");
        cv.put(USERS_COLUMN_PHONE, "0504442966");
        cv.put(USERS_COLUMN_PERMISSION, AccessLevel.Administrator.toString());
        db.insert(USERS_TABLE_NAME, null, cv);

        db.execSQL(
                "CREATE TABLE Businesses " +
                        "(" +
                        "businessName TEXT PRIMARY KEY, id TEXT, city TEXT, address TEXT, description TEXT, coordinates TEXT, " +
                        "FOREIGN KEY(id) REFERENCES Users(id)" +
                        ")"
        );
        db.execSQL(
                "CREATE TABLE Catalog " +
                        "(" +
                        "catalogNumber INTEGER PRIMARY KEY, businessName TEXT, category TEXT, catalogItemName TEXT, description TEXT, status TEXT, ratings INTEGER, sumOfRatings INTEGER, originalPrice REAL, priceAfterDiscount REAL, expirationDate DATE, type TEXT" +
                        ")"
        );
        db.execSQL(
                "CREATE TABLE Orders " +
                        "(" +
                        "orderCode INTEGER PRIMARY KEY, id TEXT, status TEXT, catalogNumber INTEGER, couponCode INTEGER, " +
                        "FOREIGN KEY(id) REFERENCES Users(id), " +
                        "FOREIGN KEY(catalogNumber) REFERENCES Catalog(catalogNumber), " +
                        "FOREIGN KEY(couponCode) REFERENCES Coupons(couponCode)" +
                        ")"
        );
        db.execSQL(
                "CREATE TABLE Coupons " +
                        "(" +
                        "couponCode INTEGER PRIMARY KEY, catalogNumber INTEGER, status TEXT, " +
                        "FOREIGN KEY(catalogNumber) REFERENCES Catalog(catalogNumber)" +
                        ")"
        );
    }

    /**
     * Called when the database needs to be upgraded. The implementation
     * should use this method to drop tables, add tables, or do anything else it
     * needs to upgrade to the new schema version.
     * <p/>
     * <p>
     * The SQLite ALTER TABLE documentation can be found
     * <a href="http://sqlite.org/lang_altertable.html">here</a>. If you add new columns
     * you can use ALTER TABLE to insert them into a live table. If you rename or remove columns
     * you can use ALTER TABLE to rename the old table, then create the new table and then
     * populate the new table with the contents of the old table.
     * </p><p>
     * This method executes within a transaction.  If an exception is thrown, all changes
     * will automatically be rolled back.
     * </p>
     *
     * @param db         The database.
     * @param oldVersion The old database version.
     * @param newVersion The new database version.
     */
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}