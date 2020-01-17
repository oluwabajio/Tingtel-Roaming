package tingtel.android.databases;

import android.content.Context;

import java.util.concurrent.Executors;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;
import androidx.room.Database;
import androidx.room.DatabaseConfiguration;
import androidx.room.InvalidationTracker;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;
import androidx.sqlite.db.SupportSQLiteDatabase;
import androidx.sqlite.db.SupportSQLiteOpenHelper;
import tingtel.android.dao.BalanceDao;
import tingtel.android.dao.NetworkCodesDao;
import tingtel.android.models.Balance;
import tingtel.android.models.DateConverter;
import tingtel.android.models.NetworksCode;

@Database(entities = {Balance.class, NetworksCode.class},version = 1, exportSchema = false)
@TypeConverters({DateConverter.class})
public abstract class AppDatabase extends RoomDatabase {

    private static AppDatabase mInstance;

    private static final String DATABASE_NAME = "production";


    //livedata to monitor when database is being populated
    private final MutableLiveData<String> mIsDatabaseCreated = new MutableLiveData<>();


    public abstract BalanceDao balanceDao();
    public abstract NetworkCodesDao networksCodesDao();

    public synchronized static AppDatabase getInstance(Context context) {
        if (mInstance == null) {
            mInstance = getDatabaseInstance(context);
        }
        return mInstance;
    }



    public static AppDatabase getDatabaseInstance(final Context context) {
        return Room.databaseBuilder(context.getApplicationContext(), AppDatabase.class, DATABASE_NAME)
                .allowMainThreadQueries()
                .fallbackToDestructiveMigration()
                .addCallback(new Callback() {
                    @Override
                    public void onCreate(@NonNull SupportSQLiteDatabase db) {
                        super.onCreate(db);
                        Executors.newSingleThreadScheduledExecutor().execute(() -> {
                            //   getInstance(context).databaseInterface().insertAll(BanksCode.populateBanksCodes());

                            getInstance(context).networksCodesDao().insertAll(NetworksCode.populateNetworksCodes());

                            AppDatabase database = AppDatabase.getInstance(context);

                            database.mIsDatabaseCreated.postValue("populated");
                        });
                    }
                })
                .build();

    }

    public static void destroyInstance() {
        mInstance = null;

    }

    @Override
    protected SupportSQLiteOpenHelper createOpenHelper(DatabaseConfiguration config) {
        return null;
    }
    @Override
    protected InvalidationTracker createInvalidationTracker() {
        return null;
    }

}

