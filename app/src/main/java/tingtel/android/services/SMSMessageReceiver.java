package tingtel.android.services;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.telephony.SmsMessage;
import android.util.Log;

import androidx.annotation.RequiresApi;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;

import java.util.Objects;

import tingtel.android.models.ApplicationModel;

public class SMSMessageReceiver extends BroadcastReceiver {
    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    public void onReceive(Context context, Intent intent) {
        // Retrieves a map of extended data from the intent.
        final Bundle bundle = intent.getExtras();

        try {
            if (bundle != null) {
                final Object[] pdusObj = (Object[]) bundle.get("pdus");
                String phoneNumber = "";
                String messageBody = "";
                final SmsMessage[] messages = new SmsMessage[Objects.requireNonNull(pdusObj).length];
                //sms are in pdus blocks, we need to join them together
                for (int i = 0; i < pdusObj.length; i++) {
                    messages[i] = SmsMessage.createFromPdu((byte[]) pdusObj[i]);
                    phoneNumber = messages[0].getDisplayOriginatingAddress();
                } // end for loop

                for (int i = 0; i < pdusObj.length; i++) {
                    messageBody += (messages[i].getDisplayMessageBody());
                }

                //check if app is in foreground or background
                ApplicationModel applicationModel = new ApplicationModel();
                String Appstate = applicationModel.getAppstate();

                /*if (Appstate.equalsIgnoreCase("background")) {
                    Log.e("logmessage", "App is in background, wont respond to sms");
                    return;
                }*/
                sendIntentThroughBroadcastReceiver(context, messageBody, phoneNumber);
            }

        } catch (Exception e) {
            Log.e("logmessage", "Exception smsReceiver" + e);

        }
    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    private static void sendIntentThroughBroadcastReceiver(Context context, String messageBody, String phoneNumber) {
        Intent myIntent = new Intent("SmsMessage");
        myIntent.putExtra("senderNum", phoneNumber);
        myIntent.putExtra("message", messageBody);
        LocalBroadcastManager.getInstance(context).sendBroadcast(myIntent);
    }
}
