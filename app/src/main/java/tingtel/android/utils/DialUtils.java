package tingtel.android.utils;

import android.Manifest;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Handler;
import android.telephony.SubscriptionInfo;
import android.telephony.SubscriptionManager;
import android.telephony.TelephonyManager;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.RequiresApi;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;

import com.google.android.material.snackbar.Snackbar;

import java.util.List;
import java.util.Objects;

import static tingtel.android.utils.AppUtils.dismissLoadingDialog;

public class DialUtils {

    /**
     * This method performs the ussd request for api 8 and higher
     *
     * @param activity
     * @param UssdCode
     * @param context
     * @param simno
     */
    public static void dialUssdCodeForNewAPI(final Activity activity, String UssdCode, final Context context,
                                             int simno) {


        TelephonyManager telephonyManager = (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);
        Handler handler = new Handler();
        TelephonyManager.UssdResponseCallback responseCallback;

        /*
          caters for click of sim 1
         */
        if (simno == 0) {
            if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
                responseCallback = new TelephonyManager.UssdResponseCallback() {
                    @Override
                    public void onReceiveUssdResponse(TelephonyManager telephonyManager, String request, CharSequence response) {
                        super.onReceiveUssdResponse(telephonyManager, request, response);
                        Log.e("logmessage", "success sim 1 : " + response.toString());
                        if (response.toString() != null) {

                            showResponseDialog(context, response.toString());

                        }
                        sendIntentThroughBroadcastReceiver(context, response.toString());
                        //Dismiss the dialog
                        dismissLoadingDialog();
                    }


                    @Override
                    public void onReceiveUssdResponseFailed(TelephonyManager telephonyManager, String request, int failureCode) {
                        super.onReceiveUssdResponseFailed(telephonyManager, request, failureCode);
                        //Dismiss the dialog
                        Toast.makeText(context, "Oops network error...please try again", Toast.LENGTH_SHORT).show();

                        dismissLoadingDialog();
                    }
                };

                if (ActivityCompat.checkSelfPermission(context, Manifest.permission.CALL_PHONE) == PackageManager.PERMISSION_GRANTED) {
                    Objects.requireNonNull(telephonyManager).sendUssdRequest(
                            UssdCode,
                            responseCallback,
                            handler
                    );
                } else {
                    Toast.makeText(activity, "not granted", Toast.LENGTH_SHORT).show();
                }
            }
            /*
              caters for click for sim 2
             */
        } else if (simno == 1) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                SubscriptionManager localSubscriptionManager = SubscriptionManager.from(context);
                if (localSubscriptionManager.getActiveSubscriptionInfoCount() > 1) {
                    //if there are two sims in dual sim mobile
                    List localList = localSubscriptionManager.getActiveSubscriptionInfoList();
                    SubscriptionInfo simInfo2 = (SubscriptionInfo) localList.get(1);

                    TelephonyManager manager2 = Objects.requireNonNull(telephonyManager).createForSubscriptionId(simInfo2.getSubscriptionId());
                    responseCallback = new TelephonyManager.UssdResponseCallback() {
                        @Override
                        public void onReceiveUssdResponse(TelephonyManager telephonyManager, String request, CharSequence response) {
                            super.onReceiveUssdResponse(telephonyManager, request, response);
                            Log.e("logmessage", "success sim 2" + response.toString());
                            if (response.toString() != null) {

                                showResponseDialog(context, response.toString());

                                sendIntentThroughBroadcastReceiver(context, response.toString());
                            }
                            //Dismiss the dialog
                            dismissLoadingDialog();
                        }

                        @Override
                        public void onReceiveUssdResponseFailed(TelephonyManager telephonyManager, String request, int failureCode) {
                            super.onReceiveUssdResponseFailed(telephonyManager, request, failureCode);
                            dismissLoadingDialog();
                            Toast.makeText(context, "Oops! Network error! Try again.", Toast.LENGTH_SHORT).show();
                        }
                    };
                    manager2.sendUssdRequest(UssdCode, responseCallback, new Handler());
                }
            }
        }
    }

    private static void showSnackBar(Activity activity) {
        Snackbar snackbar = Snackbar
                .make(Objects.requireNonNull(activity.getCurrentFocus()),
                        "Network Error...please try again",
                        Snackbar.LENGTH_LONG);
        snackbar.show();
    }

    /**
     * This method caters for version less than marshmallow
     *
     * @param activity
     * @param ussdCodeTodial
     * @param simNumber
     */
    public static void dialUssdCodeForOldAPI(Activity activity, String ussdCodeTodial, int simNumber) {
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.M) {
            //if android version doesnt require permission
            dialCode(ussdCodeTodial, activity, simNumber);
        } else {
            //if permission is not enabled, ask for permission
            if (ContextCompat.checkSelfPermission(activity, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                int REQUEST_PHONE_CALL = 101;
                ActivityCompat.requestPermissions(activity, new String[]{Manifest.permission.CALL_PHONE}, REQUEST_PHONE_CALL);
            } else {
                dialCode(ussdCodeTodial, activity, simNumber);
            }
        }
    }

    /**
     * this method dials the number received for old versions.
     *
     * @param ussdCodeTodial
     * @param activity
     * @param simNumber
     */
    private static void dialCode(String ussdCodeTodial, Activity activity, int simNumber) {
        //check if it begins with * and ends with #
        if (ussdCodeTodial.startsWith("*") && ussdCodeTodial.endsWith("#")) {
            ussdCodeTodial = ussdCodeTodial.substring(1, ussdCodeTodial.length() - 1);
        }
        //create your own
        String finalUssdCode = "*" + ussdCodeTodial + Uri.encode("#");
        //perform the action
        Intent intent = new Intent("android.intent.action.CALL", Uri.parse("tel:" + finalUssdCode));
        intent.putExtra("com.android.phone.extra.slot", simNumber); //For sim 1
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        activity.startActivity(intent);
    }

    /**
     * this method sends the received message to listening service receivers
     *
     * @param context
     * @param message
     */
    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    private static void sendIntentThroughBroadcastReceiver(Context context, String message) {
        Intent intent = new Intent("TingtelMessage");
        intent.putExtra("message", message);
        LocalBroadcastManager.getInstance(context).sendBroadcast(intent);
    }

    public static void showResponseDialog(Context context, String message) {
        new AlertDialog.Builder(context) // Context, this, etc.
                //  dialog.setContentView(R.layout.dialog_demo);
                .setTitle("Tingtel")
                .setMessage(message)
                .show();
    }
}
