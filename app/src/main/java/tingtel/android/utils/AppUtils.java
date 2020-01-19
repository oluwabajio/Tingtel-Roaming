package tingtel.android.utils;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.provider.Settings;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;

import androidx.appcompat.app.AlertDialog;

import java.util.Objects;

import tingtel.android.R;
import tingtel.android.services.UssdMessageReceiver;

public class AppUtils {
    private static ProgressDialog progress;
    private static SessionManager sessionManager;

    public static SessionManager getSessionManagerInstance() {
        if (sessionManager == null) {
            sessionManager = new SessionManager();
        }
        return sessionManager;
    }

    //To check Accessibility Service Status
    public static boolean isAccessServiceEnabled(Context context, Class accessibilityServiceClass) {
        String prefString = Settings.Secure.getString(context.getContentResolver(), Settings.Secure.ENABLED_ACCESSIBILITY_SERVICES);

        return prefString != null && prefString.contains(context.getPackageName() + "/" + accessibilityServiceClass.getName());
    }

    public static void checkAccessibilityServiceStatus(Context context) {
        //check if android version is less than android 0 (versions that dont have official ussd api)
        if (android.os.Build.VERSION.SDK_INT < android.os.Build.VERSION_CODES.O) {

            boolean accessibilityStatus;
            if (isAccessServiceEnabled(context, UssdMessageReceiver.class)) {
                accessibilityStatus = true;
                // Toast.makeText(MainActivity.this, "accessibility is true", Toast.LENGTH_SHORT).show();
                Log.e("logmessage", "accessibility is true");

            } else {
                accessibilityStatus = false;
                //Toast.makeText(MainActivity.this, "accessibility is false", Toast.LENGTH_SHORT).show();
                Log.e("logmessage", "accessibility is false");

                //request user to enable accesibilty service
                enableAccessibilityService(context);
            }
        }
    }

    //method to enable accessibility service
    private static void enableAccessibilityService(final Context context) {
        // load the customized_dialog.xml layout and inflate to view
        LayoutInflater layoutinflater = LayoutInflater.from(context);
        View customizedUserView = layoutinflater.inflate(R.layout.customized_dialog, null);
        AlertDialog.Builder alert = new AlertDialog.Builder(context);
        alert.setView(customizedUserView);
        alert.setTitle("Tingtel");
        alert.setMessage("To Use Tingtel USSD App, Please Enable Accessibility Service in Settings");
        alert.setNegativeButton("Cancel", (dialog, whichButton) -> {
            // Canceled.
            dialog.dismiss();
        });
        alert.setPositiveButton("Go To Settings", (dialog, whichButton) -> {
            Intent intent = new Intent(Settings.ACTION_ACCESSIBILITY_SETTINGS);
            context.startActivity(intent);
        });
        alert.show();

    }

    public static void dismissLoadingDialog() {
        progress.dismiss();
    }


    public static void initLoadingDialog(Context context) {
        progress = new ProgressDialog(context);
        progress.setMessage("Running Ussd Code");
        progress.setTitle("Tingtel");
        progress.setCancelable(false);
        progress.setCanceledOnTouchOutside(false);
        progress.show();
    }

}
