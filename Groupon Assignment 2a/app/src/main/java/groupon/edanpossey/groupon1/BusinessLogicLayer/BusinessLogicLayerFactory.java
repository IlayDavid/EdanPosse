package groupon.edanpossey.groupon1.BusinessLogicLayer;

import android.content.Context;

/**
 * Created by IlayDavid on 17/05/2015.
 */
public class BusinessLogicLayerFactory {
    public enum BLType {
        LocalDB
    }

    private static BL_LocalDB localDBInstance = null;

    public static IBL getLayer(BLType dalType, Context context) {
        switch (dalType) {
            case LocalDB:
                return getLocalDB(context);
            default:
                return null;
        }
    }

    private static BL_LocalDB getLocalDB(Context context){
        if(localDBInstance == null){
            localDBInstance = new BL_LocalDB(context);
        }

        return localDBInstance;
    }
}
