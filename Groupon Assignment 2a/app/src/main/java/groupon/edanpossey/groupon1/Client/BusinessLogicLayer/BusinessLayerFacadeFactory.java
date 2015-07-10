package groupon.edanpossey.groupon1.Client.BusinessLogicLayer;

import android.content.Context;

/**
 * Created by IlayDavid on 17/05/2015.
 */
public class BusinessLayerFacadeFactory {
    public enum BLType {
        LocalDB
    }

    private static BusinessLayerFacadeImpl localDBInstance = null;

    public static BusinessLayerFacade getLayer(BLType dalType, Context context) {
        switch (dalType) {
            case LocalDB:
                return getLocalDB(context);
            default:
                return null;
        }
    }

    private static BusinessLayerFacadeImpl getLocalDB(Context context){
        if(localDBInstance == null){
            localDBInstance = new BusinessLayerFacadeImpl(context);
        }

        return localDBInstance;
    }
}
