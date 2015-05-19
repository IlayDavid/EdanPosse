package groupon.edanpossey.groupon1;

import android.content.Context;

import groupon.edanpossey.groupon1.BusinessLogicLayer.BusinessLogicLayerFactory;
import groupon.edanpossey.groupon1.BusinessLogicLayer.IBL;
import groupon.edanpossey.groupon1.Entities.User;

/**
 * Created by IlayDavid on 17/05/2015.
 */
public class ObjectsHolder {
    private static ObjectsHolder objectsHolder;
    private IBL bl;
    private User currentUser;

    //==============================================================================================
    //region Constructors + Instance getter
    //==============================================================================================
    public static ObjectsHolder getInstance(Context context) {
        if (objectsHolder == null)
            objectsHolder = new ObjectsHolder(context);
        return objectsHolder;
    }

    private ObjectsHolder(Context context) {
        bl = BusinessLogicLayerFactory.getLayer(BusinessLogicLayerFactory.BLType.LocalDB, context);
        currentUser = null;
    }
    //==============================================================================================
    //endregion Constructors + Instance getter
    //==============================================================================================

    //==============================================================================================
    //region Accessors
    //==============================================================================================

    public User getCurrentUser() {
        return currentUser;
    }

    public void setCurrentUser(User currentUser) {
        this.currentUser = currentUser;
    }

    public IBL getBl() {
        return bl;
    }

    //==============================================================================================
    //endregion Accessors
    //==============================================================================================
}
