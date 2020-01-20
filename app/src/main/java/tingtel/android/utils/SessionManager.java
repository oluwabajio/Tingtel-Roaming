package tingtel.android.utils;

import android.content.SharedPreferences;

import static tingtel.android.utils.MyApplication.getSharedPreferencesCustomer;

public class SessionManager {

    private static final String INTRO_STATUS = "IsIntroOpened";
    private static final String LOGIN_STATUS = "LoginStatus";
    private static final String NETWORK_NAME = "NETWORK NAME";
    private static final String NUMBER_OF_SIMS_ON_DEVICE = "NUMBER_OF_SIMS_ON_DEVICE";
    private static final String SIM_SERIAL = "SIM_SERIAL";
    private static final String SIM_STATUS = "SIM_STATUS";
    private static final String NETWORK_NAME1 = "NETWORK_NAME1";
    private static final String NUMBER_OF_SIMS_ON_DEVICE1 = "NUMBER_OF_SIMS_ON_DEVICE1";
    private static final String SIM_SERIAL1 = "SIM_SERIAL1";
    private static final String WHICH_CLICKED = "WHICH_CLICKED";
    private static final String IS_SIM1_AIRTIME_CLICKED = "IS_SIM1_AIRTIME_CLICKED";
    private static final String IS_SIM1_DATA_CLICKED = "IS_SIM1_DATA_CLICKED";
    private static final String IS_SIM2_AIRTIME_CLICKED = "IS_SIM2_AIRTIME_CLICKED";
    private static final String IS_SIM2_DATA_CLICKED = "IS_SIM2_DATA_CLICKED";
    private static final String CLICKED_NETWORK_SERIAL = "CLICKED_NETWORK_SERIAL";
    private static final String CLICKED_BALANCE_TYPE = "CLICKED_BALANCE_TYPE";
    private static final String HAS_DATA_SIM1_COME_YET = "HAS_DATA_SIM1_COME_YET";
    private static final String HAS_DATA_SIM2_COME_YET = "HAS_DATA_SIM2_COME_YET";
    private static final String HAS_AIRTIME_SIM1_COME_YET = "HAS_AIRTIME_SIM1_COME_YET";
    private static final String HAS_AIRTIME_SIM2_COME_YET = "HAS_AIRTIME_SIM2_COME_YET";

    private static final String BANK_NAME = "BANK_NAME";
    private static final String BANK_NAME1 = "BANK_NAME1";

    private static final String BANK_ACC = "BANK_ACC";
    private static final String BANK_ACC1 = "BANK_ACC1";

    private static final String BANK_ACC_NAME = "BANK_ACC_NAME";
    private static final String BANK_ACC_NAME1 = "BANK_ACC_NAME1";
    private static final String NUM_OF_SIMS_FOUND = "NUM_OF_SIMS_FOUND";
    private static final String IS_IT_FROM_TRANSFER_ACTIVITY = "IS_IT_FROM_TRANSFER_ACTIVITY";

    private static final String RECEIVER_STATUS = "RECEIVER_STATUS";

    private final SharedPreferences pref = getSharedPreferencesCustomer();


    private static final String OnBoardStatus = "ONBOARD_STATUS";
    private static final String COUNTRY = "COUNTRY";
    private static final String COUNTRYSIM1 = "COUNTRY_SIM1";
    private static final String COUNTRYSIM2 = "COUNTRY_SIM2";
    private static final String APPSTATE = "APP_STATE";


    private void setIntPreference(String name, int value) {
        SharedPreferences.Editor editor = pref.edit();
        editor.putInt(name, value);
        editor.apply();
    }

    private void setBooleanPreference(String name, boolean value) {
        SharedPreferences.Editor editor = pref.edit();
        editor.putBoolean(name, value);
        editor.apply();
    }

    private long getLongPreference(String name) {
        if (pref.contains(name)) {
            return pref.getLong(name, 0);
        } else {
            return 0;
        }
    }

    private void setLongPreference(String name, long value) {
        SharedPreferences.Editor editor = pref.edit();
        editor.putLong(name, value);
        editor.apply();
    }


    private void setStringPreference(String name, String value) {
        SharedPreferences.Editor editor = pref.edit();
        editor.putString(name, value);
        editor.apply();
    }

    private void setFloatPreference(String name, float value) {
        SharedPreferences.Editor editor = pref.edit();
        editor.putFloat(name, value);
        editor.apply();
    }

    private Integer getIntPreference(String name) {
        if (pref.contains(name)) {
            return pref.getInt(name, 0);
        } else {
            return 0;
        }
    }

    private boolean getBooleanPreference(String name) {
        return pref.contains(name) && pref.getBoolean(name, false);
    }

    private float getFloatPreference(String name) {
        if (pref.contains(name)) {
            return pref.getFloat(name, 0);
        } else {
            return 0;
        }
    }

    private String getStringPreference(String name) {
        if (pref.contains(name)) {
            return pref.getString(name, "");
        } else {
            return null;
        }
    }

    public String getNetworkName() {
        return getStringPreference(NETWORK_NAME);
    }

    public void setNetworkName(String networkname) {
        setStringPreference(NETWORK_NAME, networkname);
    }

    public int getNumberOfSimsOnTheDevice() {
        return getIntPreference(NUMBER_OF_SIMS_ON_DEVICE);
    }

    public void setNumberOfSimsOnTheDevice(int numberOfSimsOnTheDevice) {
        setIntPreference(NUMBER_OF_SIMS_ON_DEVICE, numberOfSimsOnTheDevice);
    }

    public String getSimSerialICCID() {
        return getStringPreference(SIM_SERIAL);
    }

    public void setSimSerialICCID(String iccid) {
        setStringPreference(SIM_SERIAL, iccid);
    }


    public String getNetworkName1() {
        return getStringPreference(NETWORK_NAME1);
    }

    public void setNetworkName1(String networkname) {
        setStringPreference(NETWORK_NAME1, networkname);
    }

    public int getNumberOfSimsOnTheDevice1() {
        return getIntPreference(NUMBER_OF_SIMS_ON_DEVICE1);
    }

    public void setNumberOfSimsOnTheDevice1(int numberOfSimsOnTheDevice) {
        setIntPreference(NUMBER_OF_SIMS_ON_DEVICE1, numberOfSimsOnTheDevice);
    }

    public String getSimSerialICCID1() {
        return getStringPreference(SIM_SERIAL1);
    }

    public void setSimSerialICCID1(String iccid) {
        setStringPreference(SIM_SERIAL1, iccid);
    }


    public String getSimStatus() {
        return getStringPreference(SIM_STATUS);
    }

    public void setSimStatus(String simstatus) {
        setStringPreference(SIM_STATUS, simstatus);
    }

    public void isSim1AirtimeClicked(boolean isClicked) {
        setBooleanPreference(IS_SIM1_AIRTIME_CLICKED, isClicked);
    }

    public boolean getIsSim1AirtimeClicked() {
        return getBooleanPreference(IS_SIM1_AIRTIME_CLICKED);
    }

    public void isSim1DataClicked(boolean isClicked) {
        setBooleanPreference(IS_SIM1_DATA_CLICKED, isClicked);
    }

    public boolean getIsSim1DataClicked() {
        return getBooleanPreference(IS_SIM1_DATA_CLICKED);
    }

    public void isSim2AirtimeClicked(boolean isClicked) {
        setBooleanPreference(IS_SIM2_AIRTIME_CLICKED, isClicked);
    }

    public boolean getIsSim2AirtimeClicked() {
        return getBooleanPreference(IS_SIM2_AIRTIME_CLICKED);
    }

    public void isSim2DataClicked(boolean isClicked) {
        setBooleanPreference(IS_SIM2_DATA_CLICKED, isClicked);
    }

    public boolean getIsSim2DataClicked() {
        return getBooleanPreference(IS_SIM2_DATA_CLICKED);
    }

    public void setIntroStatus(boolean b) {
        setBooleanPreference(INTRO_STATUS, b);
    }

    public boolean getIntroStatus() {
        return getBooleanPreference(INTRO_STATUS);
    }

    public void setLoginStatus(boolean b) {
        setBooleanPreference(LOGIN_STATUS, b);
    }

    public boolean getLoginStatus() {
        return getBooleanPreference(LOGIN_STATUS);
    }


    public String getClickedNetworkSerial() {
        return getStringPreference(CLICKED_NETWORK_SERIAL);
    }

    public void setClickedNetworkSerial(String serial) {
        setStringPreference(CLICKED_NETWORK_SERIAL, serial);
    }

    public String getClickedBalanceType() {
        return getStringPreference(CLICKED_BALANCE_TYPE);
    }

    public void setClickedBalanceType(String balance) {
        setStringPreference(CLICKED_BALANCE_TYPE, balance);
    }

    public boolean getHasDataResultSIM1ComeYet() {
        return getBooleanPreference(HAS_DATA_SIM1_COME_YET);
    }

    public void setHasDataResultSIM1ComeYet(boolean hasDataResultComeYet) {
        setBooleanPreference(HAS_DATA_SIM1_COME_YET, hasDataResultComeYet);
    }

    public boolean getHasDataResultSIM2ComeYet() {
        return getBooleanPreference(HAS_DATA_SIM2_COME_YET);
    }

    public void setHasDataResultSIM2ComeYet(boolean hasDataResultComeYet) {
        setBooleanPreference(HAS_DATA_SIM2_COME_YET, hasDataResultComeYet);
    }


    public boolean getOnboardStatus() {

        return getBooleanPreference(OnBoardStatus);
    }

    public void setOnBoardStatus(Boolean status) {
        setBooleanPreference(OnBoardStatus, status);
    }

    public boolean getHasAirtimeResultSIM1ComeYet() {
        return getBooleanPreference(HAS_AIRTIME_SIM1_COME_YET);
    }

    public void setHasAirtimeResultSIM1ComeYet(boolean hasDataResultComeYet) {
        setBooleanPreference(HAS_AIRTIME_SIM1_COME_YET, hasDataResultComeYet);
    }

    public boolean getHasAirtimeResultSIM2ComeYet() {
        return getBooleanPreference(HAS_AIRTIME_SIM2_COME_YET);
    }

    public void setHasAirtimeResultSIM2ComeYet(boolean hasDataResultComeYet) {
        setBooleanPreference(HAS_AIRTIME_SIM2_COME_YET, hasDataResultComeYet);
    }


    public void setBankName(String bankName) {

        setStringPreference(BANK_NAME, bankName);

    }


    public String getBankName() {
        return getStringPreference(BANK_NAME);
    }


    public void setBankName1(String bankName) {

        setStringPreference(BANK_NAME1, bankName);

    }
    public String getBankName1() {
        return getStringPreference(BANK_NAME);
    }



    public void setBankAcc(String bankAcc) {

        setStringPreference(BANK_ACC, bankAcc);

    }


    public String getBankAcc() {
        return getStringPreference(BANK_ACC);
    }


    public void setBankAcc1(String bankAcc) {

        setStringPreference(BANK_ACC1, bankAcc);

    }


    public String getBankAcc1() {
        return getStringPreference(BANK_ACC1);
    }




    public void setBankAccName(String bankAccName) {

        setStringPreference(BANK_ACC_NAME, bankAccName);

    }


    public String getBankAccName() {
        return getStringPreference(BANK_ACC_NAME);
    }


    public void setBankAccName1(String bankAcc) {

        setStringPreference(BANK_ACC_NAME1, bankAcc);

    }


    public String getBankAccName1() {
        return getStringPreference(BANK_ACC_NAME1);
    }

    public int getTotalNumberOfSimsDetectedOnDevice(){
        return getIntPreference(NUM_OF_SIMS_FOUND);
    }

    public void setTotalNumberOfSimsDetectedOnDevice(int numberOfSimsOnTheDevice) {
        setIntPreference(NUM_OF_SIMS_FOUND,numberOfSimsOnTheDevice);
    }

    public boolean getIsRequestFromTransferActivity(){
        return getBooleanPreference(IS_IT_FROM_TRANSFER_ACTIVITY);
    }

    public void setIsRequestFromTransferActivity(boolean b) {
        setBooleanPreference(IS_IT_FROM_TRANSFER_ACTIVITY,b);
    }

    public String getCountry() {
        return getStringPreference(COUNTRY);
    }
    public void setCountry(String Country) {
        setStringPreference(COUNTRY, Country);
    }

    public String getCountrysim1() {
        return getStringPreference(COUNTRYSIM1);
    }

    public void setCountrysim1(String Country) {
        setStringPreference(COUNTRYSIM1, Country);
    }

    public String getCountrysim2() {
        return getStringPreference(COUNTRYSIM2);
    }

    public void setCountrysim2(String Country) {
        setStringPreference(COUNTRYSIM2, Country);
    }

    public String getReceiverStatus() {
        return getStringPreference(RECEIVER_STATUS);
    }

    public void setReceiverStatus(String msg) {
        setStringPreference(RECEIVER_STATUS, msg);
    }

    public String getAppState() {

        return getStringPreference(APPSTATE);
    }
    public void setAppstate(String AppState) {
        setStringPreference(APPSTATE, AppState);
    }

}
