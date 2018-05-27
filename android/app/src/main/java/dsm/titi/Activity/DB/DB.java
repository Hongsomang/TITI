package dsm.titi.Activity.DB;

import io.realm.Realm;
import io.realm.RealmConfiguration;

/**
 * Created by ghdth on 2018-05-27.
 */

public class DB {
    Realm mRealm;
    public void Realm(){
        try {
            mRealm= Realm.getDefaultInstance();

        }catch (Exception e){
            RealmConfiguration config=new RealmConfiguration.Builder()
                    .deleteRealmIfMigrationNeeded()
                    .build();
            mRealm=Realm.getInstance(config);
        }
        mRealm.beginTransaction();
    }
}
