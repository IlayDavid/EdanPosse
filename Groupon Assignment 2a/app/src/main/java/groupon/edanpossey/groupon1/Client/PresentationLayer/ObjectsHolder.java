package groupon.edanpossey.groupon1.Client.PresentationLayer;

import android.content.Context;

import groupon.edanpossey.groupon1.Client.BusinessLogicLayer.BusinessLayerFacadeFactory;
import groupon.edanpossey.groupon1.Client.BusinessLogicLayer.BusinessLayerFacade;
import groupon.edanpossey.groupon1.Entities.User;

/**
 * Created by IlayDavid on 17/05/2015.
 */
public class ObjectsHolder {
    private static ObjectsHolder objectsHolder;
    private BusinessLayerFacade bl;
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
        bl = BusinessLayerFacadeFactory.getLayer(BusinessLayerFacadeFactory.BLType.LocalDB, context);
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

    public BusinessLayerFacade getBl() {
        return bl;
    }

    //==============================================================================================
    //endregion Accessors
    //==============================================================================================
}
