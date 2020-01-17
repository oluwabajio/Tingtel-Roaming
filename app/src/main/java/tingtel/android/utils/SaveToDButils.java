package tingtel.android.utils;

import android.content.Context;
import android.os.AsyncTask;

import java.util.Calendar;
import java.util.Date;

import tingtel.android.databases.AppDatabase;
import tingtel.android.models.Balance;

public class SaveToDButils {
    public static void saveAirtimeOrDataToDatabase(final Context context, final String simiccid,
                                                   final String simName, final String phoneNumber, final String message, final String serviceType) {
        class SaveTask extends AsyncTask<Void, Void, Void> {

            @Override
            protected Void doInBackground(Void... voids) {
/*
  in the background, save the data to roomdb using the balance model
 */
                Date currentDate = Calendar.getInstance().getTime();
                AppDatabase appdatabase = AppDatabase.getDatabaseInstance(context);

                //creating a task
                Balance balance = new Balance();
                balance.setSimName(simName);
                balance.setSimUuid(simiccid);
                balance.setType(serviceType);
                balance.setDate(currentDate);
                balance.setMessage(message);
                balance.setPhoneNumber(phoneNumber);

                //adding to database
                appdatabase.balanceDao().insert(balance);

                return null;
            }
        }
/*
  instantiate class and call execute
 */
        SaveTask st = new SaveTask();
        st.execute();
    }
}
