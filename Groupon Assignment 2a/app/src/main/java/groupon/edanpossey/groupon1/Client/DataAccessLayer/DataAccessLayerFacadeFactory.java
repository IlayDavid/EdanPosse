package groupon.edanpossey.groupon1.Client.DataAccessLayer;

import android.content.Context;

/**
 * Created by IlayDavid on 17/05/2015.
 */
public class DataAccessLayerFacadeFactory {
    public enum DALType {
        LocalDB
    }

    private static DataAccessLayerFacadeImpl localDBInstance = null;

    public static DataAccessLayerFacade getDAL(DALType dalType, Context context) {
        switch (dalType) {
            case LocalDB:
                return getLocalDB(context);
            default:
                return null;
        }
    }

    private static DataAccessLayerFacadeImpl getLocalDB(Context context){
        if(localDBInstance == null){
            localDBInstance = new DataAccessLayerFacadeImpl(context);
        }

        return localDBInstance;
    }
}
