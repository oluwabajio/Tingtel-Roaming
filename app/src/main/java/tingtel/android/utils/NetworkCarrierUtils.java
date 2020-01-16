package tingtel.android.utils;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.telephony.SubscriptionInfo;
import android.telephony.SubscriptionManager;

import java.util.ArrayList;
import java.util.List;

import androidx.core.app.ActivityCompat;

public class NetworkCarrierUtils {

    public static void getCarrierOfSim(Context context, Activity activity) {
        SessionManager sessionManager = AppUtils.getSessionManagerInstance();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP_MR1) {
            if (ActivityCompat.checkSelfPermission(context, Manifest.permission.READ_PHONE_STATE) == PackageManager.PERMISSION_GRANTED) {
                final SubscriptionManager subscriptionManager = SubscriptionManager.from(context);

                final List<SubscriptionInfo> activeSubscriptionInfoList = subscriptionManager.getActiveSubscriptionInfoList();

                List<CharSequence> carrierNameList = new ArrayList<>();
                int numberOfSimsOnTheDevice = 0;
                for (SubscriptionInfo subscriptionInfo : activeSubscriptionInfoList) {
                    final CharSequence carrierName = subscriptionInfo.getCarrierName();
                    final CharSequence displayName = subscriptionInfo.getDisplayName();
                    final int mcc = subscriptionInfo.getMcc();
                    final int mnc = subscriptionInfo.getMnc();
                    final String iccid = subscriptionInfo.getIccId();
                    final String subscriptionInfoNumber = subscriptionInfo.getNumber();
                    // Toast.makeText(activity, "" + mnc + mcc, Toast.LENGTH_SHORT).show();
//todo: check

                        carrierNameList.add(carrierName);
                        numberOfSimsOnTheDevice += 1;
                        if (numberOfSimsOnTheDevice == 1) {
                            sessionManager.setNetworkName(displayName.toString());
                            sessionManager.setNumberOfSimsOnTheDevice(numberOfSimsOnTheDevice);
                            sessionManager.setSimSerialICCID(iccid);
                        } else if (numberOfSimsOnTheDevice == 2) {
                            sessionManager.setNetworkName1(displayName.toString());
                            sessionManager.setNumberOfSimsOnTheDevice1(numberOfSimsOnTheDevice);
                            sessionManager.setSimSerialICCID1(iccid);
                        }

                }
                if (numberOfSimsOnTheDevice == 0) {
                    sessionManager.setSimStatus("NO SIM");
                    sessionManager.setTotalNumberOfSimsDetectedOnDevice(numberOfSimsOnTheDevice);
                } else if (numberOfSimsOnTheDevice == 1) {
                    sessionManager.setTotalNumberOfSimsDetectedOnDevice(numberOfSimsOnTheDevice);
                    sessionManager.setSimStatus("SIM1");
                } else if (numberOfSimsOnTheDevice == 2) {
                    sessionManager.setTotalNumberOfSimsDetectedOnDevice(numberOfSimsOnTheDevice);
                    sessionManager.setSimStatus("SIM1 SIM2");
                }
            } else {
                // No explanation needed; request the permission
                ActivityCompat.requestPermissions(activity,
                        new String[]{Manifest.permission.READ_PHONE_STATE},
                        109);
            }
        } else {

        }
    }

}
