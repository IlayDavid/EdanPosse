package groupon.edanpossey.groupon1.DataAccessLayer;

import android.content.Context;

/**
 * Created by IlayDavid on 17/05/2015.
 */
public class DataAccessLayerFactory {
    public enum DALType {
        LocalDB
    }

    private static DAL_LocalDB localDBInstance = null;

    public static IDAL getDAL(DALType dalType, Context context) {
        switch (dalType) {
            case LocalDB:
                return getLocalDB(context);
            default:
                return null;
        }
    }

    private static DAL_LocalDB getLocalDB(Context context){
        if(localDBInstance == null){
            localDBInstance = new DAL_LocalDB(context);
        }

        return localDBInstance;
    }
}
