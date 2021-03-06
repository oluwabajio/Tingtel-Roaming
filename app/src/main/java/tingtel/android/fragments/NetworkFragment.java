package tingtel.android.fragments;


import android.app.Dialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Build;
import android.os.Bundle;

import androidx.annotation.RequiresApi;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;


import com.ybs.countrypicker.CountryPicker;
import com.ybs.countrypicker.CountryPickerListener;

import java.util.Objects;

import androidx.localbroadcastmanager.content.LocalBroadcastManager;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;

import tingtel.android.OnBoardActivity;
import tingtel.android.R;
import tingtel.android.models.ApplicationModel;
import tingtel.android.services.UssdMessageReceiver;
import tingtel.android.utils.SessionManager;

import static tingtel.android.utils.AppUtils.ShowMessageDialog;
import static tingtel.android.utils.AppUtils.ShowNoUssdFoundToast;
import static tingtel.android.utils.AppUtils.checkAccessibilityServiceStatus;
import static tingtel.android.utils.AppUtils.getSessionManagerInstance;
import static tingtel.android.utils.AppUtils.initLoadingDialog;
import static tingtel.android.utils.AppUtils.isAccessServiceEnabled;
import static tingtel.android.utils.Constants.*;
import static tingtel.android.utils.DialUtils.dialUssdCodeForNewAPI;
import static tingtel.android.utils.DialUtils.dialUssdCodeForOldAPI;
import static tingtel.android.utils.NetworkCarrierUtils.getCarrierOfSim;
import static tingtel.android.utils.SaveToDButils.saveAirtimeOrDataToDatabase;

/**
 * A simple {@link Fragment} subclass.
 */
public class NetworkFragment extends Fragment {

    SessionManager sessionManager = getSessionManagerInstance();
    private CardView fullLayoutSIM1, fullLayoutSIM2;
    private ImageView img_History1, img_History2;
    private TextView tvSim1Country, tvSim2Country;
    private Spinner countrySpinner;
    private TextView tvCountry;
    private TextView tvSim1Network, tvSim2Network;
    private Button btnCheckBalance1, btnCheckBalance2;
    NavController navController;
    String Sim1Serial, Sim2Serial;
    String Sim1Network, Sim2Network;
    TextView tvCopyright;


    private ApplicationModel applicationModel;




//    CountryPicker.Builder builder =
//            new CountryPicker.Builder().with(getActivity())
//                    .listener(this);

//    String[] fruits={"Apple","Grapes","Mango","Pineapple","Strawberry"};
//    int images[] = {R.drawable.flag_ac,R.drawable.flag_ad, R.drawable.flag_ae, R.drawable.flag_ai, R.drawable.flag_de };


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_network, container, false);


        initViews(view);

        initListeners(view);
        getCarrierOfSim(getContext(), getActivity());
        // initCountrySpinner();

        getDataFromCarrier(view);


        loadSavedData();
        initBroadcastReceivers();
        return view;
    }



    private void loadSavedData() {
        tvCountry.setText(sessionManager.getCountry());
        tvSim1Country.setText(sessionManager.getCountry());
        tvSim2Country.setText(sessionManager.getCountry());


        tvCopyright.setText("Copyright \u00a9 2020, Tingtel.");

    }

//    private void initCountrySpinner() {
//
//
//        countrySpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
//            @Override
//            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
//                Toast.makeText(getActivity(), "You Select Position: "+position+" "+ fruits[position], Toast.LENGTH_SHORT).show();
//
//            }
//
//            @Override
//            public void onNothingSelected(AdapterView<?> parent) {
//
//            }
//        });
//
//        CountrySpinnerAdapter customAdapter=new CountrySpinnerAdapter(getContext(),images,fruits);
//        countrySpinner.setAdapter(customAdapter);
//    }

    private void getDataFromCarrier(View view) {
        tvSim1Network.setText(sessionManager.getNetworkName());
        tvSim2Network.setText(sessionManager.getNetworkName1());

        Sim1Network = sessionManager.getNetworkName();
        Sim2Network = sessionManager.getNetworkName1();

        Sim1Serial = sessionManager.getSimSerialICCID();
        Sim2Serial = sessionManager.getSimSerialICCID1();


        String NoOfSIm = sessionManager.getSimStatus();
        Log.e("getDefaultCarrier", "No of sim is "+NoOfSIm);

        switch (NoOfSIm) {
            case "NO SIM":
                Toast.makeText(getActivity(), "Please insert a sim card", Toast.LENGTH_SHORT).show();
                break;
            case "SIM1":

                fullLayoutSIM1.setVisibility(View.VISIBLE);
                fullLayoutSIM2.setVisibility(View.GONE);
                break;
            case "SIM1 SIM2":

                fullLayoutSIM1.setVisibility(View.VISIBLE);
                fullLayoutSIM2.setVisibility(View.VISIBLE);
                break;
        }
    }

    private void initListeners(View view) {

        img_History1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Bundle bundle = new Bundle();
                bundle.putString("SimSerial", Sim1Serial);
                bundle.putString("BalanceType", "Data");
                navController.navigate(R.id.action_networkFragment_to_historyFragment, bundle);
            }
        });

        img_History2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Bundle bundle = new Bundle();
                bundle.putString("SimSerial", Sim2Serial);

                navController.navigate(R.id.action_networkFragment_to_historyFragment, bundle);
            }
        });

        tvCountry.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                final CountryPicker picker = CountryPicker.newInstance("Select Country");  // dialog title
                picker.setListener(new CountryPickerListener() {
                    @Override
                    public void onSelectCountry(String name, String code, String dialCode, int flagDrawableResID) {
                        // Implement your code here

                        tvCountry.setText(name);
                        sessionManager.setCountry(name);
                        sessionManager.setCountrysim1(name);
                        sessionManager.setCountrysim2(name);

                        tvSim1Country.setText(name);
                        tvSim2Country.setText(name);

                        picker.dismiss();
                    }
                });
                picker.show(getActivity().getSupportFragmentManager(), "COUNTRY_PICKER");

            }
        });

        tvSim1Country.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final CountryPicker picker = CountryPicker.newInstance("Select Country");  // dialog title
                picker.setListener(new CountryPickerListener() {
                    @Override
                    public void onSelectCountry(String name, String code, String dialCode, int flagDrawableResID) {
                        // Implement your code here


                        sessionManager.setCountrysim1(name);

                        tvSim1Country.setText(name);

                        picker.dismiss();
                    }
                });
                picker.show(getActivity().getSupportFragmentManager(), "COUNTRY_PICKER");
            }
        });

        tvSim2Country.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final CountryPicker picker = CountryPicker.newInstance("Select Country");  // dialog title
                picker.setListener(new CountryPickerListener() {
                    @Override
                    public void onSelectCountry(String name, String code, String dialCode, int flagDrawableResID) {
                        // Implement your code here


                        sessionManager.setCountrysim2(name);

                        tvSim2Country.setText(name);

                        picker.dismiss();
                    }
                });
                picker.show(getActivity().getSupportFragmentManager(), "COUNTRY_PICKER");
            }
        });

        btnCheckBalance1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //TODO; compare the country and network, then run ussd code.


                if (sessionManager.getCountrysim1() == null) {
                    Toast.makeText(getActivity(), "Kindly Select A Country", Toast.LENGTH_SHORT).show();
                    return;
                }


                countrySelectValidation(sessionManager.getCountrysim1());


                if (checkToDisplayBalanceDialog(sessionManager.getCountrysim1())) {
                    displayBalanceDialog(Sim1Network, 0, Sim1Serial, sessionManager.getCountrysim1());
                    return;
                }
                checkCountryUssdData(Sim1Network, 0, Sim1Serial);
                applicationModel.setSimname(Sim1Network);
                applicationModel.setServiceType("Data");
            }
        });

        btnCheckBalance2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (sessionManager.getCountrysim2() == null) {
                    Toast.makeText(getActivity(), "Kindly Select A Country", Toast.LENGTH_SHORT).show();
                    return;
                }


                countrySelectValidation(sessionManager.getCountrysim2());


                if (checkToDisplayBalanceDialog(sessionManager.getCountrysim2())) {
                    displayBalanceDialog(Sim2Network, 1, Sim2Serial, sessionManager.getCountrysim2());
                    return;
                }

                checkCountryUssdData(Sim2Network, 1, Sim2Serial);
                applicationModel.setSimname(Sim2Network);
                applicationModel.setServiceType("Data");
            }
        });
    }

    private boolean checkToDisplayBalanceDialog(String countrysim) {

        if (countrysim.equalsIgnoreCase("Nigeria") || countrysim.equalsIgnoreCase("Ghana")
        || (countrysim.equalsIgnoreCase("Benin")) || (countrysim.equalsIgnoreCase("Chad"))){
            return true;
        } else {
            return false;
        }

    }

    private void displayBalanceDialog(String simNetwork, int simNo, String simSerial, String countrysim) {

        final Dialog dialog = new Dialog(getActivity());
        dialog.setCancelable(true);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.balance_ask_dialog);



        Button airtimeButton = (Button) dialog.findViewById(R.id.btn_airtime);
        airtimeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                checkCountryUssdAirtime(simNetwork, simNo, simSerial);
                applicationModel.setSimname(simNetwork);
                applicationModel.setServiceType("Airtime");
                dialog.dismiss();
            }
        });

        Button dataButton = (Button) dialog.findViewById(R.id.btn_data);
        dataButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkCountryUssdData(simNetwork, simNo, simSerial);
                applicationModel.setSimname(simNetwork);
                applicationModel.setServiceType("Data");
                dialog.dismiss();
            }
        });

        dialog.show();

    }

    private void countrySelectValidation(String countrysim) {

        if (countrysim == null || countrysim.equalsIgnoreCase("")){
            Toast.makeText(getActivity(), "Kindly Select A Country", Toast.LENGTH_LONG).show();
            return;
        }

    }

    private void checkCountryUssdData(String NetworkName, int simNo, String SimSerical) {

        String Country = tvCountry.getText().toString();

        String NetworkStartString = NetworkName.substring(0, 3);
        String NetworkStartStringLong = NetworkName.substring(0, 4);
        String NetworkStartStringShort = NetworkName.substring(0, 2);

        String UssdCode = "";

        int SimNo = simNo;

        switch (Country) {


            case "United Arab Emirates":

                Toast.makeText(getActivity(), "Country not Supported Yet", Toast.LENGTH_LONG).show();

                break;


            case "Afghanistan":

                Toast.makeText(getActivity(), "Country not Supported Yet", Toast.LENGTH_LONG).show();

                break;


            case "Antigua and Barbuda":

                Toast.makeText(getActivity(), "Country not Supported Yet", Toast.LENGTH_LONG).show();
                break;


            case "Anguilla":

                Toast.makeText(getActivity(), "Country not Supported Yet", Toast.LENGTH_LONG).show();
                break;


            case "Albania":

                Toast.makeText(getActivity(), "Country not Supported Yet", Toast.LENGTH_LONG).show();
                break;


            case "Armenia":

                Toast.makeText(getActivity(), "Country not Supported Yet", Toast.LENGTH_LONG).show();
                break;


            case "Angola":




                if (NetworkStartString.equalsIgnoreCase("uni")) { //unitel

                    UssdCode = UNITEL_AO_DATA_BALANCE;

                } else if (NetworkStartString.equalsIgnoreCase("mov")) { //movicell

                    UssdCode = MOVICELL_AO_DATA_BALANCE;

                } else {

                    Toast.makeText(getActivity(), "Ussd Code not Available For This Network, Ensure you Select The Correct Country!", Toast.LENGTH_LONG).show();
                    return;

                }
                requestForDataBalance(SimNo, NetworkName, SimSerical, UssdCode);

                break;


            case "Antarctica":

                Toast.makeText(getActivity(), "Country not Supported Yet", Toast.LENGTH_LONG).show();

                break;


            case "Argentina":

                Toast.makeText(getActivity(), "Country not Supported Yet", Toast.LENGTH_LONG).show();
                break;


            case "AmericanSamoa":

                Toast.makeText(getActivity(), "Country not Supported Yet", Toast.LENGTH_LONG).show();
                break;


            case "Austria":

                Toast.makeText(getActivity(), "Country not Supported Yet", Toast.LENGTH_LONG).show();
                break;


            case "Australia":

                Toast.makeText(getActivity(), "Country not Supported Yet", Toast.LENGTH_LONG).show();
                break;


            case "Aruba":

                Toast.makeText(getActivity(), "Country not Supported Yet", Toast.LENGTH_LONG).show();
                break;


            case "Åland Islands":

                Toast.makeText(getActivity(), "Country not Supported Yet", Toast.LENGTH_LONG).show();
                break;


            case "Bosnia and Herzegovina":

                Toast.makeText(getActivity(), "Country not Supported Yet", Toast.LENGTH_LONG).show();
                break;


            case "Barbados":

                Toast.makeText(getActivity(), "Country not Supported Yet", Toast.LENGTH_LONG).show();
                break;


            case "Bangladesh":

                Toast.makeText(getActivity(), "Country not Supported Yet", Toast.LENGTH_LONG).show();
                break;


            case "Belgium":

                Toast.makeText(getActivity(), "Country not Supported Yet", Toast.LENGTH_LONG).show();
                break;


            case "Burkina Faso": //Code: BF
                if (NetworkStartString.equalsIgnoreCase("ora")) { //Orange

                    UssdCode = ORANGE_BF_DATA_BALANCE;

                } else if (NetworkStartStringLong.equalsIgnoreCase("telm")) { //telmob

                    UssdCode = TELMOB_BF_DATA_BALANCE;

                } else if (NetworkStartStringLong.equalsIgnoreCase("tele")) { //telecell

                    UssdCode = TELECEL_BF_DATA_BALANCE;

                }
                else {

                    Toast.makeText(getActivity(), "Ussd Code not Available For This Network, Ensure you Select The Correct Country!", Toast.LENGTH_LONG).show();
                    return;

                }
                requestForDataBalance(SimNo, NetworkName, SimSerical, UssdCode);


                break;


            case "Bulgaria":

                Toast.makeText(getActivity(), "Country not Supported Yet", Toast.LENGTH_LONG).show();
                break;


            case "Bahrain":

                Toast.makeText(getActivity(), "Country not Supported Yet", Toast.LENGTH_LONG).show();
                break;


            case "Burundi": //Code: BI

                if (NetworkStartString.equalsIgnoreCase("eco")) { //econet

                    UssdCode = ECONET_BI_DATA_BALANCE;

                } else if (NetworkStartString.equalsIgnoreCase("lum")) { //lumitel

                    UssdCode = LUMITEL_BI_DATA_BALANCE;

                }
                else {

                    Toast.makeText(getActivity(), "Ussd Code not Available For This Network, Ensure you Select The Correct Country!", Toast.LENGTH_LONG).show();
                    return;

                }
                requestForDataBalance(SimNo, NetworkName, SimSerical, UssdCode);


                break;


            case "Saint Barthélemy":

                Toast.makeText(getActivity(), "Country not Supported Yet", Toast.LENGTH_LONG).show();
                break;


            case "Benin":  //Code: BJ

                if (NetworkStartString.equalsIgnoreCase("mtn")) { //mtn

                    UssdCode = MTN_BJ_DATA_BALANCE;

                } else if (NetworkStartString.equalsIgnoreCase("mov")) { //moov

                    UssdCode = MOOV_BJ_DATA_BALANCE;

                }
                else {

                    Toast.makeText(getActivity(), "Ussd Code not Available For This Network, Ensure you Select The Correct Country!", Toast.LENGTH_LONG).show();
                    return;

                }
                requestForDataBalance(SimNo, NetworkName, SimSerical, UssdCode);
                break;


            case "Bermuda":

                Toast.makeText(getActivity(), "Country not Supported Yet", Toast.LENGTH_LONG).show();
                break;


            case "Brunei Darussalam":

                Toast.makeText(getActivity(), "Country not Supported Yet", Toast.LENGTH_LONG).show();
                break;


            case "Bolivia, Plurinational State of":

                Toast.makeText(getActivity(), "Country not Supported Yet", Toast.LENGTH_LONG).show();
                break;


            case "Bonaire":

                Toast.makeText(getActivity(), "Country not Supported Yet", Toast.LENGTH_LONG).show();
                break;


            case "Brazil":

                Toast.makeText(getActivity(), "Country not Supported Yet", Toast.LENGTH_LONG).show();
                break;


            case "Bahamas":

                Toast.makeText(getActivity(), "Country not Supported Yet", Toast.LENGTH_LONG).show();
                break;


            case "Bhutan":

                Toast.makeText(getActivity(), "Country not Supported Yet", Toast.LENGTH_LONG).show();
                break;


            case "Bouvet Island":

                Toast.makeText(getActivity(), "Country not Supported Yet", Toast.LENGTH_LONG).show();
                break;


            case "Botswana": //Code: BW

                if (NetworkStartString.equalsIgnoreCase("mas")) { //mascom

                    UssdCode = MASCOM_BW_DATA_BALANCE;

                } else if (NetworkStartString.equalsIgnoreCase("ora")) { //orange

                    UssdCode = ORANGE_BW_DATA_BALANCE;

                }
                else {

                    Toast.makeText(getActivity(), "Ussd Code not Available For This Network, Ensure you Select The Correct Country!", Toast.LENGTH_LONG).show();
                    return;

                }
                requestForDataBalance(SimNo, NetworkName, SimSerical, UssdCode);

                break;


            case "Belarus":

                Toast.makeText(getActivity(), "Country not Supported Yet", Toast.LENGTH_LONG).show();
                break;


            case "Belize":

                Toast.makeText(getActivity(), "Country not Supported Yet", Toast.LENGTH_LONG).show();
                break;


            case "Canada":

                Toast.makeText(getActivity(), "Country not Supported Yet", Toast.LENGTH_LONG).show();
                break;


            case "Cocos (Keeling) Islands":

                Toast.makeText(getActivity(), "Country not Supported Yet", Toast.LENGTH_LONG).show();
                break;


            case "Congo, The Democratic Republic of the": //Code: CG


                if (NetworkStartString.equalsIgnoreCase("mtn")) { //mtn //

                    UssdCode = MTN_CG_DATA_BALANCE;

                } else if (NetworkStartString.equalsIgnoreCase("air")) { //Airtel

                    UssdCode = AIRTEL_CG_DATA_BALANCE;

                }
                else {

                    Toast.makeText(getActivity(), "Ussd Code not Available For This Network, Ensure you Select The Correct Country!", Toast.LENGTH_LONG).show();
                    return;

                }
                requestForDataBalance(SimNo, NetworkName, SimSerical, UssdCode);
                break;


            case "Central African Republic": //Code: CF

                if (NetworkStartString.equalsIgnoreCase("moo")) { //moov

                    UssdCode = MOOV_CF_DATA_BALANCE;

                } else if (NetworkStartString.equalsIgnoreCase("ora")) { ////orange

                    UssdCode = ORANGE_CF_DATA_BALANCE;

                }
                else {

                    Toast.makeText(getActivity(), "Ussd Code not Available For This Network, Ensure you Select The Correct Country!", Toast.LENGTH_LONG).show();
                    return;

                }
                requestForDataBalance(SimNo, NetworkName, SimSerical, UssdCode);

                break;


            case "Congo":

                Toast.makeText(getActivity(), "Country not Supported Yet", Toast.LENGTH_LONG).show();
                break;


            case "Switzerland":

                Toast.makeText(getActivity(), "Country not Supported Yet", Toast.LENGTH_LONG).show();
                break;


            case "Ivory Coast":

                if (NetworkStartString.equalsIgnoreCase("mtn")) { //mtn

                    UssdCode = MTN_CI_DATA_BALANCE;

                } else if (NetworkStartString.equalsIgnoreCase("ora")) { //orange

                    UssdCode = ORANGE_CI_DATA_BALANCE;

                }  else if (NetworkStartString.equalsIgnoreCase("moo")) { //moov

                    UssdCode = MOOV_CI_DATA_BALANCE;

                }
                else {

                    Toast.makeText(getActivity(), "Ussd Code not Available For This Network, Ensure you Select The Correct Country!", Toast.LENGTH_LONG).show();
                    return;

                }
                requestForDataBalance(SimNo, NetworkName, SimSerical, UssdCode);

                break;


            case "Cook Islands":

                Toast.makeText(getActivity(), "Country not Supported Yet", Toast.LENGTH_LONG).show();
                break;


            case "Chile":

                Toast.makeText(getActivity(), "Country not Supported Yet", Toast.LENGTH_LONG).show();
                break;


            case "Cameroon": //Code: CM

                if (NetworkStartString.equalsIgnoreCase("mtn")) { //

                    UssdCode = MTN_CM_DATA_BALANCE;

                } else if (NetworkStartString.equalsIgnoreCase("ora")) { //

                    UssdCode = ORANGE_CM_DATA_BALANCE;

                } else if (NetworkStartString.equalsIgnoreCase("nex")) { //

                    UssdCode = NEXTTEL_CM_DATA_BALANCE;

                } else if (NetworkStartString.equalsIgnoreCase("cam")) { //

                    UssdCode = CAMTEL_CM_DATA_BALANCE;

                }
                else {

                    Toast.makeText(getActivity(), "Ussd Code not Available For This Network, Ensure you Select The Correct Country!", Toast.LENGTH_LONG).show();
                    return;

                }
                requestForDataBalance(SimNo, NetworkName, SimSerical, UssdCode);


                break;


            case "China":

                Toast.makeText(getActivity(), "Country not Supported Yet", Toast.LENGTH_LONG).show();
                break;


            case "Colombia":

                Toast.makeText(getActivity(), "Country not Supported Yet", Toast.LENGTH_LONG).show();
                break;


            case "Costa Rica":

                Toast.makeText(getActivity(), "Country not Supported Yet", Toast.LENGTH_LONG).show();
                break;


            case "Cuba":

                Toast.makeText(getActivity(), "Country not Supported Yet", Toast.LENGTH_LONG).show();
                break;


            case "Cape Verde": //Code: CV

                if (NetworkStartStringShort.equalsIgnoreCase("cv")) { //Cv Movel

                    UssdCode = CV_MOVELL_DATA_BALANCE;

                } else if (NetworkStartString.equalsIgnoreCase("uni")) { //unitel

                    UssdCode = UNITELL_CV_DATA_BALANCE;

                }
                else {

                    Toast.makeText(getActivity(), "Ussd Code not Available For This Network, Ensure you Select The Correct Country!", Toast.LENGTH_LONG).show();
                    return;

                }
                requestForDataBalance(SimNo, NetworkName, SimSerical, UssdCode);

                break;


            case "Curacao":

                Toast.makeText(getActivity(), "Country not Supported Yet", Toast.LENGTH_LONG).show();
                break;


            case "Christmas Island":

                Toast.makeText(getActivity(), "Country not Supported Yet", Toast.LENGTH_LONG).show();
                break;


            case "Cyprus":

                Toast.makeText(getActivity(), "Country not Supported Yet", Toast.LENGTH_LONG).show();
                break;


            case "Czech Republic":

                Toast.makeText(getActivity(), "Country not Supported Yet", Toast.LENGTH_LONG).show();
                break;


            case "Germany":

                Toast.makeText(getActivity(), "Country not Supported Yet", Toast.LENGTH_LONG).show();
                break;


            case "Djibouti": //Code: DJ

                if (NetworkStartString.equalsIgnoreCase("eva")) { //Evatis

                    UssdCode = EVATIS_DJ_DATA_BALANCE;

                }
                else {

                    Toast.makeText(getActivity(), "Ussd Code not Available For This Network, Ensure you Select The Correct Country!", Toast.LENGTH_LONG).show();
                    return;

                }
                requestForDataBalance(SimNo, NetworkName, SimSerical, UssdCode);

                break;


            case "Denmark":

                Toast.makeText(getActivity(), "Country not Supported Yet", Toast.LENGTH_LONG).show();
                break;


            case "Dominica":

                Toast.makeText(getActivity(), "Country not Supported Yet", Toast.LENGTH_LONG).show();
                break;


            case "Dominican Republic":

                Toast.makeText(getActivity(), "Country not Supported Yet", Toast.LENGTH_LONG).show();
                break;


            case "Algeria":

                Toast.makeText(getActivity(), "Country not Supported Yet", Toast.LENGTH_LONG).show();
                break;


            case "Ecuador":

                Toast.makeText(getActivity(), "Country not Supported Yet", Toast.LENGTH_LONG).show();
                break;


            case "Estonia":

                Toast.makeText(getActivity(), "Country not Supported Yet", Toast.LENGTH_LONG).show();
                break;


            case "Egypt":

                Toast.makeText(getActivity(), "Country not Supported Yet", Toast.LENGTH_LONG).show();
                break;


            case "Western Sahara":

                Toast.makeText(getActivity(), "Country not Supported Yet", Toast.LENGTH_LONG).show();
                break;


            case "Eritrea":



                Toast.makeText(getActivity(), "Country not Supported Yet", Toast.LENGTH_LONG).show();
                break;


            case "Spain":

                Toast.makeText(getActivity(), "Country not Supported Yet", Toast.LENGTH_LONG).show();
                break;


            case "Ethiopia":

                if (NetworkStartString.equalsIgnoreCase("eth")) { //ethio-Telecom

                    UssdCode = ETHIO_TELECOM_ET_DATA_BALANCE;

                }
                else {

                    Toast.makeText(getActivity(), "Ussd Code not Available For This Network, Ensure you Select The Correct Country!", Toast.LENGTH_LONG).show();
                    return;

                }
                requestForDataBalance(SimNo, NetworkName, SimSerical, UssdCode);
                break;


            case "Finland":

                Toast.makeText(getActivity(), "Country not Supported Yet", Toast.LENGTH_LONG).show();
                break;


            case "Fiji":

                Toast.makeText(getActivity(), "Country not Supported Yet", Toast.LENGTH_LONG).show();
                break;


            case "Falkland Islands":

                Toast.makeText(getActivity(), "Country not Supported Yet", Toast.LENGTH_LONG).show();
                break;


            case "Micronesia, Federated States of":

                Toast.makeText(getActivity(), "Country not Supported Yet", Toast.LENGTH_LONG).show();
                break;

            case "Faroe Islands":

                Toast.makeText(getActivity(), "Country not Supported Yet", Toast.LENGTH_LONG).show();
                break;


            case "France": //FR

//                Orange (by France Télécom)
//                SFR (by Numericable)
//                Bouygues Télécom
//                Free Mobile (by Iliad)

                if (NetworkStartString.equalsIgnoreCase("ora")) { //Orange

                    UssdCode = ORANGE_FR_DATA_BALANCE;

                } else if (NetworkStartString.equalsIgnoreCase("sfr")) { //SFR

                    ShowMessageDialog(getActivity(), getResources().getString(R.string.sfr_balance_title), getResources().getString(R.string.sfr_balance_instruction));
                    return;

                } else if (NetworkStartString.equalsIgnoreCase("bou")) { //Bouygues Télécom

                    ShowNoUssdFoundToast(getActivity());
                    return;

                } else if (NetworkStartString.equalsIgnoreCase("fre")) { //Free Mobile

                    ShowNoUssdFoundToast(getActivity());
                    return;

                }
                else {

                    Toast.makeText(getActivity(), "Ussd Code not Available For This Network, Ensure you Select The Correct Country!", Toast.LENGTH_LONG).show();
                    return;

                }
                requestForDataBalance(SimNo, NetworkName, SimSerical, UssdCode);
                break;

            case "Gabon":
                if (NetworkStartString.equalsIgnoreCase("air")) { //Airtel

                    UssdCode = AIRTEL_GA_DATA_BALANCE;

                } else if (NetworkStartString.equalsIgnoreCase("gab")) { //Gabon Telecom

                    UssdCode = GABON_TELECOM_GA_DATA_BALANCE;

                }
                else {

                    Toast.makeText(getActivity(), "Ussd Code not Available For This Network, Ensure you Select The Correct Country!", Toast.LENGTH_LONG).show();
                    return;

                }
                requestForDataBalance(SimNo, NetworkName, SimSerical, UssdCode);
                break;

            case "United Kingdom":

                Toast.makeText(getActivity(), "Country not Supported Yet", Toast.LENGTH_LONG).show();
                break;

            case "Grenada":

                Toast.makeText(getActivity(), "Country not Supported Yet", Toast.LENGTH_LONG).show();
                break;

            case "Georgia":

                Toast.makeText(getActivity(), "Country not Supported Yet", Toast.LENGTH_LONG).show();
                break;

            case "French Guiana":

                Toast.makeText(getActivity(), "Country not Supported Yet", Toast.LENGTH_LONG).show();
                break;

            case "Guernsey":

                Toast.makeText(getActivity(), "Country not Supported Yet", Toast.LENGTH_LONG).show();
                break;

            case "Ghana":

                if (NetworkStartString.equalsIgnoreCase("mtn")) { //Mtn

                    UssdCode = MTN_GH_DATA_BALANCE;

                } else if (NetworkStartString.equalsIgnoreCase("vod")) { //Vodafone

                    UssdCode = VODAFONE_GH_DATA_BALANCE;

                } else if (NetworkStartString.equalsIgnoreCase("air")) { //Airtel

                    UssdCode = AIRTELTIGO_GH_DATA_BALANCE;

                } else if (NetworkStartString.equalsIgnoreCase("glo")) { //Glo

                    UssdCode = GLO_GH_DATA_BALANCE;

                }
                else {

                    Toast.makeText(getActivity(), "Ussd Code not Available For This Network, Ensure you Select The Correct Country!", Toast.LENGTH_LONG).show();
                    return;

                }
                requestForDataBalance(SimNo, NetworkName, SimSerical, UssdCode);


                break;
            case "Gibraltar":

                Toast.makeText(getActivity(), "Country not Supported Yet", Toast.LENGTH_LONG).show();
                break;

            case "Greenland":

                Toast.makeText(getActivity(), "Country not Supported Yet", Toast.LENGTH_LONG).show();
                break;

            case "Gambia":

                if (NetworkStartString.equalsIgnoreCase("afr")) { //Africell

                    UssdCode = AFRICELL_GM_DATA_BALANCE;

                } else if (NetworkStartString.equalsIgnoreCase("gam")) { //Gambia

                    UssdCode = GAMBIA_TELECOM_GM_DATA_BALANCE;

                } else if (NetworkStartString.equalsIgnoreCase("com")) { //Comium

                    UssdCode = COMIUM_GM_DATA_BALANCE;

                } else if (NetworkStartString.equalsIgnoreCase("qce")) { //Qcell

                    UssdCode = QCELL_GM_DATA_BALANCE;

                }
                else {

                    Toast.makeText(getActivity(), "Ussd Code not Available For This Network, Ensure you Select The Correct Country!", Toast.LENGTH_LONG).show();
                    return;

                }
                requestForDataBalance(SimNo, NetworkName, SimSerical, UssdCode);

                break;

            case "Guinea":

                Toast.makeText(getActivity(), "Country not Supported Yet", Toast.LENGTH_LONG).show();
                break;

            case "Guadeloupe":

                Toast.makeText(getActivity(), "Country not Supported Yet", Toast.LENGTH_LONG).show();
                break;

            case "Equatorial Guinea":

                if (NetworkStartString.equalsIgnoreCase("ora")) { //orrange

                    UssdCode = ORANGE_GQ_DATA_BALANCE;

                } else if (NetworkStartString.equalsIgnoreCase("mtn")) { //mtn

                    UssdCode = MTN_GQ_DATA_BALANCE;

                } else if (NetworkStartString.equalsIgnoreCase("cel")) { //Cellcom

                    Toast.makeText(getActivity(), "Call 8003099911", Toast.LENGTH_LONG).show();

                }
                else {

                    Toast.makeText(getActivity(), "Ussd Code not Available For This Network, Ensure you Select The Correct Country!", Toast.LENGTH_LONG).show();
                    return;

                }
                requestForDataBalance(SimNo, NetworkName, SimSerical, UssdCode);

                break;

            case "Greece":

                Toast.makeText(getActivity(), "Country not Supported Yet", Toast.LENGTH_LONG).show();
                break;

            case "South Georgia and the South Sandwich Islands":

                Toast.makeText(getActivity(), "Country not Supported Yet", Toast.LENGTH_LONG).show();
                break;

            case "Guatemala":

                Toast.makeText(getActivity(), "Country not Supported Yet", Toast.LENGTH_LONG).show();
                break;

            case "Guam":

                Toast.makeText(getActivity(), "Country not Supported Yet", Toast.LENGTH_LONG).show();
                break;

            case "Guinea-Bissau":

                Toast.makeText(getActivity(), "Country not Supported Yet", Toast.LENGTH_LONG).show();
                break;

            case "Guyana":

                Toast.makeText(getActivity(), "Country not Supported Yet", Toast.LENGTH_LONG).show();
                break;

            case "Hong Kong":

                Toast.makeText(getActivity(), "Country not Supported Yet", Toast.LENGTH_LONG).show();
                break;

            case "Heard Island and McDonald Islands":

                Toast.makeText(getActivity(), "Country not Supported Yet", Toast.LENGTH_LONG).show();
                break;

            case "Honduras":

                Toast.makeText(getActivity(), "Country not Supported Yet", Toast.LENGTH_LONG).show();
                break;

            case "Croatia":

                Toast.makeText(getActivity(), "Country not Supported Yet", Toast.LENGTH_LONG).show();
                break;

            case "Haiti":

                Toast.makeText(getActivity(), "Country not Supported Yet", Toast.LENGTH_LONG).show();
                break;

            case "Hungary":

                Toast.makeText(getActivity(), "Country not Supported Yet", Toast.LENGTH_LONG).show();
                break;

            case "Indonesia":

                Toast.makeText(getActivity(), "Country not Supported Yet", Toast.LENGTH_LONG).show();
                break;

            case "Ireland":

                Toast.makeText(getActivity(), "Country not Supported Yet", Toast.LENGTH_LONG).show();
                break;

            case "Israel":

                Toast.makeText(getActivity(), "Country not Supported Yet", Toast.LENGTH_LONG).show();
                break;

            case "Isle of Man":

                Toast.makeText(getActivity(), "Country not Supported Yet", Toast.LENGTH_LONG).show();
                break;

            case "India":

                Toast.makeText(getActivity(), "Country not Supported Yet", Toast.LENGTH_LONG).show();
                break;

            case "British Indian Ocean Territory":

                Toast.makeText(getActivity(), "Country not Supported Yet", Toast.LENGTH_LONG).show();
                break;

            case "Iraq":

                Toast.makeText(getActivity(), "Country not Supported Yet", Toast.LENGTH_LONG).show();
                break;

            case "Iran, Islamic Republic of":

                Toast.makeText(getActivity(), "Country not Supported Yet", Toast.LENGTH_LONG).show();
                break;

            case "Iceland":

                Toast.makeText(getActivity(), "Country not Supported Yet", Toast.LENGTH_LONG).show();
                break;

            case "Italy":

                Toast.makeText(getActivity(), "Country not Supported Yet", Toast.LENGTH_LONG).show();
                break;

            case "Jersey":

                Toast.makeText(getActivity(), "Country not Supported Yet", Toast.LENGTH_LONG).show();
                break;

            case "Jamaica":

                Toast.makeText(getActivity(), "Country not Supported Yet", Toast.LENGTH_LONG).show();
                break;

            case "Jordan":

                Toast.makeText(getActivity(), "Country not Supported Yet", Toast.LENGTH_LONG).show();
                break;

            case "Japan":

                Toast.makeText(getActivity(), "Country not Supported Yet", Toast.LENGTH_LONG).show();
                break;

            case "Kenya": //Code: KE


                if (NetworkStartString.equalsIgnoreCase("air")) { //Airtel

                    UssdCode = AIRTELL_KE_DATA_BALANCE;

                } else if (NetworkStartString.equalsIgnoreCase("saf")) { //Safaricom

                    UssdCode = SAFARICOM_TELECOM_KE_DATA_BALANCE;

                }  else if (NetworkStartString.equalsIgnoreCase("tel")) { //Telkom

                    UssdCode = TELKOM_KE_DATA_BALANCE;

                }
                else {

                    Toast.makeText(getActivity(), "Ussd Code not Available For This Network, Ensure you Select The Correct Country!", Toast.LENGTH_LONG).show();
                    return;

                }
                requestForDataBalance(SimNo, NetworkName, SimSerical, UssdCode);
                break;

            case "Kyrgyzstan":

                Toast.makeText(getActivity(), "Country not Supported Yet", Toast.LENGTH_LONG).show();
                break;

            case "Cambodia":

                Toast.makeText(getActivity(), "Country not Supported Yet", Toast.LENGTH_LONG).show();
                break;

            case "Kiribati":

                Toast.makeText(getActivity(), "Country not Supported Yet", Toast.LENGTH_LONG).show();
                break;

            case "Comoros":

                Toast.makeText(getActivity(), "Country not Supported Yet", Toast.LENGTH_LONG).show();
                break;

            case "Saint Kitts and Nevis":

                Toast.makeText(getActivity(), "Country not Supported Yet", Toast.LENGTH_LONG).show();
                break;

            case "North Korea":

                Toast.makeText(getActivity(), "Country not Supported Yet", Toast.LENGTH_LONG).show();
                break;

            case "South Korea":

                Toast.makeText(getActivity(), "Country not Supported Yet", Toast.LENGTH_LONG).show();
                break;

            case "Kuwait":

                Toast.makeText(getActivity(), "Country not Supported Yet", Toast.LENGTH_LONG).show();
                break;

            case "Cayman Islands":

                Toast.makeText(getActivity(), "Country not Supported Yet", Toast.LENGTH_LONG).show();
                break;

            case "Kazakhstan":

                Toast.makeText(getActivity(), "Country not Supported Yet", Toast.LENGTH_LONG).show();
                break;

            case "Lao People\'s Democratic Republic":

                Toast.makeText(getActivity(), "Country not Supported Yet", Toast.LENGTH_LONG).show();
                break;

            case "Lebanon":

                Toast.makeText(getActivity(), "Country not Supported Yet", Toast.LENGTH_LONG).show();
                break;

            case "Saint Lucia":

                Toast.makeText(getActivity(), "Country not Supported Yet", Toast.LENGTH_LONG).show();
                break;

            case "Liechtenstein":

                Toast.makeText(getActivity(), "Country not Supported Yet", Toast.LENGTH_LONG).show();
                break;

            case "Sri Lanka":

                Toast.makeText(getActivity(), "Country not Supported Yet", Toast.LENGTH_LONG).show();
                break;

            case "Liberia":

                if (NetworkStartString.equalsIgnoreCase("mtn")) { //Mtn

                    UssdCode = MTN_LR_DATA_BALANCE;

                } else if (NetworkStartString.equalsIgnoreCase("ora")) { //

                    UssdCode = ORANGE_LR_DATA_BALANCE;


                }
                else {

                    Toast.makeText(getActivity(), "Ussd Code not Available For This Network, Ensure you Select The Correct Country!", Toast.LENGTH_LONG).show();
                    return;

                }
                requestForDataBalance(SimNo, NetworkName, SimSerical, UssdCode);

                break;
            case "Lesotho":

                if (NetworkStartString.equalsIgnoreCase("ECO")) { //Econet

                    UssdCode = ECONET_LS_DATA_BALANCE;

                } else if (NetworkStartString.equalsIgnoreCase("vod")) { //Vodacom

                    UssdCode = VODACOM_LS_DATA_BALANCE;

                }
                else {

                    Toast.makeText(getActivity(), "Ussd Code not Available For This Network, Ensure you Select The Correct Country!", Toast.LENGTH_LONG).show();
                    return;

                }
                requestForDataBalance(SimNo, NetworkName, SimSerical, UssdCode);
                break;

            case "Lithuania":

                Toast.makeText(getActivity(), "Country not Supported Yet", Toast.LENGTH_LONG).show();
                break;

            case "Luxembourg":

                Toast.makeText(getActivity(), "Country not Supported Yet", Toast.LENGTH_LONG).show();
                break;

            case "Latvia":

                Toast.makeText(getActivity(), "Country not Supported Yet", Toast.LENGTH_LONG).show();
                break;

            case "Libyan Arab Jamahiriya":

                Toast.makeText(getActivity(), "Country not Supported Yet", Toast.LENGTH_LONG).show();
                break;

            case "Morocco":

                Toast.makeText(getActivity(), "Country not Supported Yet", Toast.LENGTH_LONG).show();
                break;

            case "Monaco":

                Toast.makeText(getActivity(), "Country not Supported Yet", Toast.LENGTH_LONG).show();
                break;

            case "Moldova":

                Toast.makeText(getActivity(), "Country not Supported Yet", Toast.LENGTH_LONG).show();
                break;

            case "Montenegro":

                Toast.makeText(getActivity(), "Country not Supported Yet", Toast.LENGTH_LONG).show();
                break;

            case "Saint Martin":

                Toast.makeText(getActivity(), "Country not Supported Yet", Toast.LENGTH_LONG).show();
                break;

            case "Madagascar":

                if (NetworkStartString.equalsIgnoreCase("air")) { //Airtel

                    UssdCode = AIRTEL_MG_DATA_BALANCE;

                } else if (NetworkStartString.equalsIgnoreCase("ora")) { //Orangw

                    UssdCode = ORANGE_MG_DATA_BALANCE;

                }
                else if (NetworkStartString.equalsIgnoreCase("tel")) { //Telma

                    UssdCode = TELMA_MG_DATA_BALANCE;

                }
                else {

                    Toast.makeText(getActivity(), "Ussd Code not Available For This Network, Ensure you Select The Correct Country!", Toast.LENGTH_LONG).show();
                    return;

                }
                requestForDataBalance(SimNo, NetworkName, SimSerical, UssdCode);

                break;

            case "Marshall Islands":

                Toast.makeText(getActivity(), "Country not Supported Yet", Toast.LENGTH_LONG).show();
                break;

            case "Macedonia, The Former Yugoslav Republic of":

                Toast.makeText(getActivity(), "Country not Supported Yet", Toast.LENGTH_LONG).show();
                break;

            case "Mali":

                if (NetworkStartString.equalsIgnoreCase("ora")) { //Orange

                    UssdCode = ORANGE_ML_DATA_BALANCE;

                } else if (NetworkStartString.equalsIgnoreCase("sot")) { //Sotelma

                    UssdCode = SOTELMA_ML_DATA_BALANCE;

                }
                else {

                    Toast.makeText(getActivity(), "Ussd Code not Available For This Network, Ensure you Select The Correct Country!", Toast.LENGTH_LONG).show();
                    return;

                }
                requestForDataBalance(SimNo, NetworkName, SimSerical, UssdCode);

                break;

            case "Myanmar":

                Toast.makeText(getActivity(), "Country not Supported Yet", Toast.LENGTH_LONG).show();
                break;

            case "Mongolia":

                Toast.makeText(getActivity(), "Country not Supported Yet", Toast.LENGTH_LONG).show();
                break;

            case "Macao":

                Toast.makeText(getActivity(), "Country not Supported Yet", Toast.LENGTH_LONG).show();
                break;

            case "Northern Mariana Islands":

                Toast.makeText(getActivity(), "Country not Supported Yet", Toast.LENGTH_LONG).show();
                break;

            case "Martinique":

                Toast.makeText(getActivity(), "Country not Supported Yet", Toast.LENGTH_LONG).show();
                break;

            case "Mauritania":

                if (NetworkStartString.equalsIgnoreCase("mau")) { //mauritell

                    UssdCode = MAURITEL_MR_DATA_BALANCE;

                } else if (NetworkStartString.equalsIgnoreCase("chi")) { //Chinguitell

                    UssdCode = CHINGUITEL_MR_DATA_BALANCE;

                } else if (NetworkStartString.equalsIgnoreCase("mat")) { //

                    UssdCode = MATTEL_MR_DATA_BALANCE;

                }
                else {

                    Toast.makeText(getActivity(), "Ussd Code not Available For This Network, Ensure you Select The Correct Country!", Toast.LENGTH_LONG).show();
                    return;

                }
                requestForDataBalance(SimNo, NetworkName, SimSerical, UssdCode);

                break;

            case "Montserrat":

                Toast.makeText(getActivity(), "Country not Supported Yet", Toast.LENGTH_LONG).show();
                break;

            case "Malta":

                Toast.makeText(getActivity(), "Country not Supported Yet", Toast.LENGTH_LONG).show();
                break;

            case "Mauritius":

                if (NetworkStartString.equalsIgnoreCase("ora")) { //Orange

                    UssdCode = ORANGE_MU_DATA_BALANCE;

                } else if (NetworkStartString.equalsIgnoreCase("emt")) { //Emtel

                    UssdCode = EMTEL_MU_DATA_BALANCE;

                } else if (NetworkStartString.equalsIgnoreCase("chi")) { //Chili

                    UssdCode = CHILI_MU_DATA_BALANCE;

                }
                else {

                    Toast.makeText(getActivity(), "Ussd Code not Available For This Network, Ensure you Select The Correct Country!", Toast.LENGTH_LONG).show();
                    return;

                }
                requestForDataBalance(SimNo, NetworkName, SimSerical, UssdCode);

                break;

            case "Maldives":

                Toast.makeText(getActivity(), "Country not Supported Yet", Toast.LENGTH_LONG).show();
                break;

            case "Malawi":

                if (NetworkStartString.equalsIgnoreCase("air")) { //Airtel

                    UssdCode = AIRTEL_MW_DATA_BALANCE;

                } else if (NetworkStartString.equalsIgnoreCase("tnm")) { //TNM

                    UssdCode = TNM_MW_DATA_BALANCE;

                }
                else {

                    Toast.makeText(getActivity(), "Ussd Code not Available For This Network, Ensure you Select The Correct Country!", Toast.LENGTH_LONG).show();
                    return;

                }
                requestForDataBalance(SimNo, NetworkName, SimSerical, UssdCode);

                break;

            case "Mexico":

                Toast.makeText(getActivity(), "Country not Supported Yet", Toast.LENGTH_LONG).show();
                break;

            case "Malaysia":

                Toast.makeText(getActivity(), "Country not Supported Yet", Toast.LENGTH_LONG).show();
                break;

            case "Mozambique":

                if (NetworkStartString.equalsIgnoreCase("mce")) { //

                    UssdCode = MCEL_MZ_DATA_BALANCE;

                } else if (NetworkStartString.equalsIgnoreCase("vod")) { //

                    UssdCode = VODACOM_MZ_DATA_BALANCE;

                } else if (NetworkStartString.equalsIgnoreCase("mov")) { //Movitel

                    UssdCode = MOVITEL_MZ_DATA_BALANCE;

                }
                else {

                    Toast.makeText(getActivity(), "Ussd Code not Available For This Network, Ensure you Select The Correct Country!", Toast.LENGTH_LONG).show();
                    return;

                }
                requestForDataBalance(SimNo, NetworkName, SimSerical, UssdCode);

                break;

            case "Namibia":


                if (NetworkStartString.equalsIgnoreCase("air")) { //Airtel

                    UssdCode = AIRTEL_NA_DATA_BALANCE;

                } else if (NetworkStartString.equalsIgnoreCase("moo")) { //moov

                    UssdCode = MOOV_NA_DATA_BALANCE;

                }
                else {

                    Toast.makeText(getActivity(), "Ussd Code not Available For This Network, Ensure you Select The Correct Country!", Toast.LENGTH_LONG).show();
                    return;

                }
                requestForDataBalance(SimNo, NetworkName, SimSerical, UssdCode);

                break;

            case "New Caledonia":
                Toast.makeText(getActivity(), "Country not Supported Yet", Toast.LENGTH_LONG).show();

                break;

            case "Niger":
                Toast.makeText(getActivity(), "Country not Supported Yet", Toast.LENGTH_LONG).show();

                break;

            case "Norfolk Island":
                Toast.makeText(getActivity(), "Country not Supported Yet", Toast.LENGTH_LONG).show();

                break;

            case "Nigeria":

                if (NetworkStartString.equalsIgnoreCase("air")) { //airtel

                    UssdCode = AIRTEL_NG_DATA_BALANCE;

                } else if (NetworkStartString.equalsIgnoreCase("mtn")) { //mtn

                    UssdCode = MTN_NG_DATA_BALANCE;

                } else if (NetworkStartString.equalsIgnoreCase("glo")) { //glo

                    UssdCode = GLO_NG_DATA_BALANCE;

                } else if (NetworkStartString.equalsIgnoreCase("9mo") || (NetworkStartString.equalsIgnoreCase("eti"))) { //9mobile or etisalat

                    UssdCode = ETISALAT_NG_DATA_BALANCE;

                }
                else {

                    Toast.makeText(getActivity(), "Ussd Code not Available For This Network, Ensure you Select The Correct Country!", Toast.LENGTH_LONG).show();
                    return;

                }
                requestForDataBalance(SimNo, NetworkName, SimSerical, UssdCode);

                break;

            case "Nicaragua":
                Toast.makeText(getActivity(), "Country not Supported Yet", Toast.LENGTH_LONG).show();

                break;

            case "Netherlands":
                Toast.makeText(getActivity(), "Country not Supported Yet", Toast.LENGTH_LONG).show();

                break;

            case "Norway":

                Toast.makeText(getActivity(), "Country not Supported Yet", Toast.LENGTH_LONG).show();

                break;

            case "Nepal":

                Toast.makeText(getActivity(), "Country not Supported Yet", Toast.LENGTH_LONG).show();

                break;

            case "Nauru":

                Toast.makeText(getActivity(), "Country not Supported Yet", Toast.LENGTH_LONG).show();

                break;

            case "Niue":

                Toast.makeText(getActivity(), "Country not Supported Yet", Toast.LENGTH_LONG).show();

                break;

            case "New Zealand":

                Toast.makeText(getActivity(), "Country not Supported Yet", Toast.LENGTH_LONG).show();
                break;

            case "Oman":

                Toast.makeText(getActivity(), "Country not Supported Yet", Toast.LENGTH_LONG).show();
                break;

            case "Panama":

                Toast.makeText(getActivity(), "Country not Supported Yet", Toast.LENGTH_LONG).show();
                break;

            case "Peru":

                Toast.makeText(getActivity(), "Country not Supported Yet", Toast.LENGTH_LONG).show();
                break;

            case "French Polynesia":

                Toast.makeText(getActivity(), "Country not Supported Yet", Toast.LENGTH_LONG).show();
                break;

            case "Papua New Guinea":

                Toast.makeText(getActivity(), "Country not Supported Yet", Toast.LENGTH_LONG).show();
                break;

            case "Philippines":

                Toast.makeText(getActivity(), "Country not Supported Yet", Toast.LENGTH_LONG).show();
                break;

            case "Pakistan":

                Toast.makeText(getActivity(), "Country not Supported Yet", Toast.LENGTH_LONG).show();
                break;

            case "Poland":

                Toast.makeText(getActivity(), "Country not Supported Yet", Toast.LENGTH_LONG).show();
                break;

            case "Saint Pierre and Miquelon":

                Toast.makeText(getActivity(), "Country not Supported Yet", Toast.LENGTH_LONG).show();
                break;

            case "Pitcairn":

                Toast.makeText(getActivity(), "Country not Supported Yet", Toast.LENGTH_LONG).show();
                break;

            case "Puerto Rico":

                Toast.makeText(getActivity(), "Country not Supported Yet", Toast.LENGTH_LONG).show();
                break;

            case "Palestinian Territory, Occupied":

                Toast.makeText(getActivity(), "Country not Supported Yet", Toast.LENGTH_LONG).show();
                break;

            case "Portugal":

                Toast.makeText(getActivity(), "Country not Supported Yet", Toast.LENGTH_LONG).show();
                break;

            case "Palau":

                Toast.makeText(getActivity(), "Country not Supported Yet", Toast.LENGTH_LONG).show();
                break;

            case "Paraguay":

                Toast.makeText(getActivity(), "Country not Supported Yet", Toast.LENGTH_LONG).show();
                break;

            case "Qatar":

                Toast.makeText(getActivity(), "Country not Supported Yet", Toast.LENGTH_LONG).show();
                break;

            case "Réunion":

                Toast.makeText(getActivity(), "Country not Supported Yet", Toast.LENGTH_LONG).show();
                break;

            case "Romania":

                Toast.makeText(getActivity(), "Country not Supported Yet", Toast.LENGTH_LONG).show();
                break;

            case "Serbia":

                Toast.makeText(getActivity(), "Country not Supported Yet", Toast.LENGTH_LONG).show();

                break;

            case "Russia":

                Toast.makeText(getActivity(), "Country not Supported Yet", Toast.LENGTH_LONG).show();
                break;

            case "Rwanda":

                if (NetworkStartString.equalsIgnoreCase("mtn")) { //mtn

                    UssdCode = MTN_RW_DATA_BALANCE;

                } else if (NetworkStartString.equalsIgnoreCase("air")) { //Airtel-Tigo

                    UssdCode = AIRTEL_TIGO_RW_DATA_BALANCE;

                }
                else {

                    Toast.makeText(getActivity(), "Ussd Code not Available For This Network, Ensure you Select The Correct Country!", Toast.LENGTH_LONG).show();
                    return;

                }
                requestForDataBalance(SimNo, NetworkName, SimSerical, UssdCode);

                break;

            case "Saudi Arabia":

                Toast.makeText(getActivity(), "Country not Supported Yet", Toast.LENGTH_LONG).show();
                break;

            case "Solomon Islands":

                Toast.makeText(getActivity(), "Country not Supported Yet", Toast.LENGTH_LONG).show();
                break;

            case "Seychelles":

                if (NetworkStartString.equalsIgnoreCase("air")) { //Airte;

                    UssdCode = AIRTEL_SC_DATA_BALANCE;

                } else if (NetworkStartString.equalsIgnoreCase("cab")) { //cable

                    UssdCode = CABLE_SC_DATA_BALANCE;

                }
                else {

                    Toast.makeText(getActivity(), "Ussd Code not Available For This Network, Ensure you Select The Correct Country!", Toast.LENGTH_LONG).show();
                    return;

                }
                requestForDataBalance(SimNo, NetworkName, SimSerical, UssdCode);

                break;

            case "Sudan":

                if (NetworkStartString.equalsIgnoreCase("mtn")) { //mtn

                    UssdCode = MTN_SD_DATA_BALANCE;

                } else if (NetworkStartString.equalsIgnoreCase("zai")) { //zain

                    UssdCode = ZAIN_SD_DATA_BALANCE;

                }
                else {

                    Toast.makeText(getActivity(), "Ussd Code not Available For This Network, Ensure you Select The Correct Country!", Toast.LENGTH_LONG).show();
                    return;

                }
                requestForDataBalance(SimNo, NetworkName, SimSerical, UssdCode);

                break;

            case "Sweden":

                Toast.makeText(getActivity(), "Country not Supported Yet", Toast.LENGTH_LONG).show();
                break;

            case "Singapore":

                Toast.makeText(getActivity(), "Country not Supported Yet", Toast.LENGTH_LONG).show();
                break;

            case "Saint Helena, Ascension and Tristan Da Cunha":

                Toast.makeText(getActivity(), "Country not Supported Yet", Toast.LENGTH_LONG).show();
                break;

            case "Slovenia":

                Toast.makeText(getActivity(), "Country not Supported Yet", Toast.LENGTH_LONG).show();
                break;

            case "Svalbard and Jan Mayen":

                Toast.makeText(getActivity(), "Country not Supported Yet", Toast.LENGTH_LONG).show();
                break;

            case "Slovakia":

                Toast.makeText(getActivity(), "Country not Supported Yet", Toast.LENGTH_LONG).show();
                break;

            case "Sierra Leone":

                if (NetworkStartString.equalsIgnoreCase("afr")) { //Africell

                    UssdCode = AFRICELL_SL_DATA_BALANCE;

                } else if (NetworkStartString.equalsIgnoreCase("ora")) { //Orange

                    UssdCode = ORANGE_SL_DATA_BALANCE;

                } else if (NetworkStartString.equalsIgnoreCase("sma")) { //Smart

                    UssdCode = SMART_SL_DATA_BALANCE;

                }
                else {

                    Toast.makeText(getActivity(), "Ussd Code not Available For This Network, Ensure you Select The Correct Country!", Toast.LENGTH_LONG).show();
                    return;

                }
                requestForDataBalance(SimNo, NetworkName, SimSerical, UssdCode);

                break;

            case "San Marino":

                Toast.makeText(getActivity(), "Country not Supported Yet", Toast.LENGTH_LONG).show();
                break;

            case "Senegal":

                if (NetworkStartString.equalsIgnoreCase("Exp")) { //Expresso

                    UssdCode = EXPRESSO_SN_DATA_BALANCE;

                } else if (NetworkStartString.equalsIgnoreCase("ora")) { //Orange

                    UssdCode = ORANGE_SN_DATA_BALANCE;

                } else if (NetworkStartString.equalsIgnoreCase("tig")) { //Tigo

                    UssdCode = TIGO_SN_DATA_BALANCE;

                }
                else {

                    Toast.makeText(getActivity(), "Ussd Code not Available For This Network, Ensure you Select The Correct Country!", Toast.LENGTH_LONG).show();
                    return;

                }
                requestForDataBalance(SimNo, NetworkName, SimSerical, UssdCode);

                break;

            case "Somalia":

                Toast.makeText(getActivity(), "Country not Supported Yet", Toast.LENGTH_LONG).show();
                break;

            case "Suriname":

                Toast.makeText(getActivity(), "Country not Supported Yet", Toast.LENGTH_LONG).show();
                break;

            case "South Sudan":

                Toast.makeText(getActivity(), "Country not Supported Yet", Toast.LENGTH_LONG).show();
                break;

            case "Sao Tome and Principe":

                if (NetworkStartString.equalsIgnoreCase("uni")) { //Unitel

                    UssdCode = UNITEL_ST_DATA_BALANCE;

                } else if (NetworkStartString.equalsIgnoreCase("cst")) { //CST

                    UssdCode = CST_ST_DATA_BALANCE;

                }
                else {

                    Toast.makeText(getActivity(), "Ussd Code not Available For This Network, Ensure you Select The Correct Country!", Toast.LENGTH_LONG).show();
                    return;

                }
                requestForDataBalance(SimNo, NetworkName, SimSerical, UssdCode);

                break;

            case "El Salvador":

                Toast.makeText(getActivity(), "Country not Supported Yet", Toast.LENGTH_LONG).show();
                break;

            case "Sint Maarten":

                Toast.makeText(getActivity(), "Country not Supported Yet", Toast.LENGTH_LONG).show();
                break;

            case "Syrian Arab Republic":

                Toast.makeText(getActivity(), "Country not Supported Yet", Toast.LENGTH_LONG).show();
                break;
            case "Swaziland":

                if (NetworkStartString.equalsIgnoreCase("mtn")) { //MTN

                    UssdCode = MTN_SZ_DATA_BALANCE;

                }
                else {

                    Toast.makeText(getActivity(), "Ussd Code not Available For This Network, Ensure you Select The Correct Country!", Toast.LENGTH_LONG).show();
                    return;

                }
                requestForDataBalance(SimNo, NetworkName, SimSerical, UssdCode);

                break;

            case "Turks and Caicos Islands":

                Toast.makeText(getActivity(), "Country not Supported Yet", Toast.LENGTH_LONG).show();
                break;

            case "Chad": //TD

                if (NetworkStartString.equalsIgnoreCase("tig")) { //tigo

                    UssdCode = TIGO_TD_DATA_BALANCE;

                } else if (NetworkStartString.equalsIgnoreCase("air")) { //airrtel

                    UssdCode = AIRTEL_TD_DATA_BALANCE;

                }
                else {

                    Toast.makeText(getActivity(), "Ussd Code not Available For This Network, Ensure you Select The Correct Country!", Toast.LENGTH_LONG).show();
                    return;

                }
                requestForDataBalance(SimNo, NetworkName, SimSerical, UssdCode);
                break;

            case "French Southern Territories":

                Toast.makeText(getActivity(), "Country not Supported Yet", Toast.LENGTH_LONG).show();
                break;

            case "Togo":

                if (NetworkStartString.equalsIgnoreCase("tog")) { //Togocell

                    UssdCode = TOGOCEL_TG_DATA_BALANCE;

                } else if (NetworkStartString.equalsIgnoreCase("moo")) { //moov

                    UssdCode = MOOV_TG_DATA_BALANCE;

                }
                else {

                    Toast.makeText(getActivity(), "Ussd Code not Available For This Network, Ensure you Select The Correct Country!", Toast.LENGTH_LONG).show();
                    return;

                }
                requestForDataBalance(SimNo, NetworkName, SimSerical, UssdCode);

                break;

            case "Thailand":

                Toast.makeText(getActivity(), "Country not Supported Yet", Toast.LENGTH_LONG).show();
                break;

            case "Tajikistan":

                Toast.makeText(getActivity(), "Country not Supported Yet", Toast.LENGTH_LONG).show();
                break;

            case "Tokelau":

                Toast.makeText(getActivity(), "Country not Supported Yet", Toast.LENGTH_LONG).show();
                break;

            case "East Timor":

                Toast.makeText(getActivity(), "Country not Supported Yet", Toast.LENGTH_LONG).show();
                break;

            case "Turkmenistan":

                Toast.makeText(getActivity(), "Country not Supported Yet", Toast.LENGTH_LONG).show();
                break;

            case "Tunisia":

                Toast.makeText(getActivity(), "Country not Supported Yet", Toast.LENGTH_LONG).show();
                break;

            case "Tonga":

                Toast.makeText(getActivity(), "Country not Supported Yet", Toast.LENGTH_LONG).show();
                break;

            case "Turkey":

                Toast.makeText(getActivity(), "Country not Supported Yet", Toast.LENGTH_LONG).show();
                break;

            case "Trinidad and Tobago":

                Toast.makeText(getActivity(), "Country not Supported Yet", Toast.LENGTH_LONG).show();
                break;

            case "Tuvalu":

                Toast.makeText(getActivity(), "Country not Supported Yet", Toast.LENGTH_LONG).show();
                break;

            case "Taiwan":

                Toast.makeText(getActivity(), "Country not Supported Yet", Toast.LENGTH_LONG).show();
                break;

            case "Tanzania, United Republic of":

                if (NetworkStartString.equalsIgnoreCase("vod")) { //vodacom

                    UssdCode = VODACOM_TZ_DATA_BALANCE;

                } else if (NetworkStartString.equalsIgnoreCase("air")) { //Airtel

                    UssdCode = AIRTEL_TZ_DATA_BALANCE;

                } else if (NetworkStartString.equalsIgnoreCase("tig")) { //Tigo

                    UssdCode = TIGO_TZ_DATA_BALANCE;

                } else if (NetworkStartString.equalsIgnoreCase("zan")) { //Zantel

                    UssdCode = ZANTEL_TZ_DATA_BALANCE;

                }
                else {

                    Toast.makeText(getActivity(), "Ussd Code not Available For This Network, Ensure you Select The Correct Country!", Toast.LENGTH_LONG).show();
                    return;

                }
                requestForDataBalance(SimNo, NetworkName, SimSerical, UssdCode);


                break;

            case "Ukraine":

                Toast.makeText(getActivity(), "Country not Supported Yet", Toast.LENGTH_LONG).show();
                break;

            case "Uganda":

                if (NetworkStartString.equalsIgnoreCase("air")) { //Airtel

                    UssdCode = AIRTEL_UG_DATA_BALANCE;

                } else if (NetworkStartString.equalsIgnoreCase("mtn")) { //,tm

                    UssdCode = MTN_UG_DATA_BALANCE;

                } else if (NetworkStartString.equalsIgnoreCase("afr")) { //Africell

                    UssdCode = AFRICELL_UG_DATA_BALANCE;

                } else if (NetworkStartString.equalsIgnoreCase("sma")) { //Smart

                    UssdCode = SMART_UG_DATA_BALANCE;

                } else if (NetworkStartString.equalsIgnoreCase("utl")) { //

                    UssdCode = UTL_UG_DATA_BALANCE;

                }
                else {

                    Toast.makeText(getActivity(), "Ussd Code not Available For This Network, Ensure you Select The Correct Country!", Toast.LENGTH_LONG).show();
                    return;

                }
                requestForDataBalance(SimNo, NetworkName, SimSerical, UssdCode);

                break;

            case "U.S. Minor Outlying Islands":

                Toast.makeText(getActivity(), "Country not Supported Yet", Toast.LENGTH_LONG).show();
                break;

            case "United States":


                if (NetworkStartString.equalsIgnoreCase("t-m")) { //Tmobile

                    UssdCode = "#225#";


               } else if (NetworkStartString.equalsIgnoreCase("ver")) { //Verizon

                    UssdCode = "##";

                } else if (NetworkStartString.equalsIgnoreCase("bou")) { //Bouygues Télécom

                    ShowNoUssdFoundToast(getActivity());
                    return;

                } else if (NetworkStartString.equalsIgnoreCase("fre")) { //Free Mobile

                    ShowNoUssdFoundToast(getActivity());
                    return;

                }
                else {

                    Toast.makeText(getActivity(), "Ussd Code not Available For This Network, Ensure you Select The Correct Country!", Toast.LENGTH_LONG).show();
                    return;

                }
                requestForDataBalance(SimNo, NetworkName, SimSerical, UssdCode);





                break;

            case "Uruguay":

                Toast.makeText(getActivity(), "Country not Supported Yet", Toast.LENGTH_LONG).show();
                break;

            case "Uzbekistan":

                Toast.makeText(getActivity(), "Country not Supported Yet", Toast.LENGTH_LONG).show();
                break;

            case "Holy See (Vatican City State)":

                Toast.makeText(getActivity(), "Country not Supported Yet", Toast.LENGTH_LONG).show();
                break;

            case "Saint Vincent and the Grenadines":

                Toast.makeText(getActivity(), "Country not Supported Yet", Toast.LENGTH_LONG).show();
                break;

            case "Venezuela, Bolivarian Republic of":

                Toast.makeText(getActivity(), "Country not Supported Yet", Toast.LENGTH_LONG).show();
                break;

            case "Virgin Islands, British":

                Toast.makeText(getActivity(), "Country not Supported Yet", Toast.LENGTH_LONG).show();
                break;

            case "Virgin Islands, U.S.":

                Toast.makeText(getActivity(), "Country not Supported Yet", Toast.LENGTH_LONG).show();
                break;
            case "Viet Nam":

                Toast.makeText(getActivity(), "Country not Supported Yet", Toast.LENGTH_LONG).show();
                break;

            case "Vanuatu":

                Toast.makeText(getActivity(), "Country not Supported Yet", Toast.LENGTH_LONG).show();
                break;

            case "Wallis and Futuna":

                Toast.makeText(getActivity(), "Country not Supported Yet", Toast.LENGTH_LONG).show();
                break;

            case "Samoa":

                Toast.makeText(getActivity(), "Country not Supported Yet", Toast.LENGTH_LONG).show();
                break;

            case "Kosovo":

                Toast.makeText(getActivity(), "Country not Supported Yet", Toast.LENGTH_LONG).show();
                break;

            case "Yemen":

                Toast.makeText(getActivity(), "Country not Supported Yet", Toast.LENGTH_LONG).show();
                break;

            case "Mayotte":

                Toast.makeText(getActivity(), "Country not Supported Yet", Toast.LENGTH_LONG).show();
                break;

            case "South Africa":

                if (NetworkStartString.equalsIgnoreCase("vod")) { //Vodacom

                    UssdCode = VODACOM_ZA_DATA_BALANCE;

                } else if (NetworkStartString.equalsIgnoreCase("mtn")) { //mtn

                    UssdCode = MTN_ZA_DATA_BALANCE;

                } else if (NetworkStartString.equalsIgnoreCase("cel")) { //Cellc

                    UssdCode = CELLC_ZA_DATA_BALANCE;

                }
                else {

                    Toast.makeText(getActivity(), "Ussd Code not Available For This Network, Ensure you Select The Correct Country!", Toast.LENGTH_LONG).show();
                    return;

                }
                requestForDataBalance(SimNo, NetworkName, SimSerical, UssdCode);

                break;
            case "Zambia":

                if (NetworkStartString.equalsIgnoreCase("air")) { //Airtel

                    UssdCode = AIRTEL_ZM_DATA_BALANCE;

                } else if (NetworkStartString.equalsIgnoreCase("mtn")) { //mtn

                    UssdCode = MTN_ZM_DATA_BALANCE;

                } else if (NetworkStartString.equalsIgnoreCase("zam")) { //zamtell

                    UssdCode = ZAMTELL_ZM_DATA_BALANCE;

                }
                else {

                    Toast.makeText(getActivity(), "Ussd Code not Available For This Network, Ensure you Select The Correct Country!", Toast.LENGTH_LONG).show();
                    return;

                }
                requestForDataBalance(SimNo, NetworkName, SimSerical, UssdCode);

                break;

            case "Zimbabwe":


                if (NetworkStartString.equalsIgnoreCase("eco")) { //EConet

                    UssdCode = ECONET_ZW_DATA_BALANCE;

                } else if (NetworkStartString.equalsIgnoreCase("tel")) { //Telecel

                    UssdCode = TELECEL_ZW_DATA_BALANCE;

                } else if (NetworkStartString.equalsIgnoreCase("net")) { //

                    UssdCode = NETONE_ZW_DATA_BALANCE;

                }
                else {

                    Toast.makeText(getActivity(), "Ussd Code not Available For This Network, Ensure you Select The Correct Country!", Toast.LENGTH_LONG).show();
                    return;

                }
                requestForDataBalance(SimNo, NetworkName, SimSerical, UssdCode);


                break;


        }


    }

    private void checkCountryUssdAirtime(String NetworkName, int simNo, String SimSerical) {

        String Country = tvCountry.getText().toString();

        String NetworkStartString = NetworkName.substring(0, 3);
        String NetworkStartStringLong = NetworkName.substring(0, 4);
        String NetworkStartStringShort = NetworkName.substring(0, 2);

        String UssdCode = "";

        int SimNo = simNo;

        switch (Country) {


            case "United Arab Emirates":

                break;


            case "Afghanistan":

                break;


            case "Antigua and Barbuda":

                break;


            case "Anguilla":

                break;


            case "Albania":

                break;


            case "Armenia":

                break;


            case "Angola":




                if (NetworkStartString.equalsIgnoreCase("uni")) { //unitel

                    UssdCode = UNITEL_AO_DATA_BALANCE;

                } else if (NetworkStartString.equalsIgnoreCase("mov")) { //movicell

                    UssdCode = MOVICELL_AO_DATA_BALANCE;

                } else {

                    Toast.makeText(getActivity(), "Ussd Code not Available For This Network, Ensure you Select The Correct Country!", Toast.LENGTH_LONG).show();
                    return;

                }
                requestForDataBalance(SimNo, NetworkName, SimSerical, UssdCode);

                break;


            case "Antarctica":


                break;


            case "Argentina":

                break;


            case "AmericanSamoa":

                break;


            case "Austria":

                break;


            case "Australia":

                break;


            case "Aruba":

                break;


            case "Åland Islands":

                break;


            case "Bosnia and Herzegovina":

                break;


            case "Barbados":

                break;


            case "Bangladesh":

                break;


            case "Belgium":

                break;


            case "Burkina Faso": //Code: BF
                if (NetworkStartString.equalsIgnoreCase("ora")) { //Orange

                    UssdCode = ORANGE_BF_DATA_BALANCE;

                } else if (NetworkStartStringLong.equalsIgnoreCase("telm")) { //telmob

                    UssdCode = TELMOB_BF_DATA_BALANCE;

                } else if (NetworkStartStringLong.equalsIgnoreCase("tele")) { //telecell

                    UssdCode = TELECEL_BF_DATA_BALANCE;

                }
                else {

                    Toast.makeText(getActivity(), "Ussd Code not Available For This Network, Ensure you Select The Correct Country!", Toast.LENGTH_LONG).show();
                    return;

                }
                requestForDataBalance(SimNo, NetworkName, SimSerical, UssdCode);


                break;


            case "Bulgaria":

                break;


            case "Bahrain":

                break;


            case "Burundi": //Code: BI

                if (NetworkStartString.equalsIgnoreCase("eco")) { //econet

                    UssdCode = ECONET_BI_DATA_BALANCE;

                } else if (NetworkStartString.equalsIgnoreCase("lum")) { //lumitel

                    UssdCode = LUMITEL_BI_DATA_BALANCE;

                }
                else {

                    Toast.makeText(getActivity(), "Ussd Code not Available For This Network, Ensure you Select The Correct Country!", Toast.LENGTH_LONG).show();
                    return;

                }
                requestForDataBalance(SimNo, NetworkName, SimSerical, UssdCode);


                break;


            case "Saint Barthélemy":

                break;


            case "Benin":  //Code: BJ

                if (NetworkStartString.equalsIgnoreCase("mtn")) { //mtn

                    UssdCode = MTN_BJ_AIRTIME_BALANCE;

                } else if (NetworkStartString.equalsIgnoreCase("mov")) { //moov

                    UssdCode = MOOV_BJ_AIRTIME_BALANCE;

                }
                else {

                    Toast.makeText(getActivity(), "Ussd Code not Available For This Network, Ensure you Select The Correct Country!", Toast.LENGTH_LONG).show();
                    return;

                }
                requestForDataBalance(SimNo, NetworkName, SimSerical, UssdCode);
                break;


            case "Bermuda":

                break;


            case "Brunei Darussalam":

                break;


            case "Bolivia, Plurinational State of":

                break;


            case "Bonaire":

                break;


            case "Brazil":

                break;


            case "Bahamas":

                break;


            case "Bhutan":

                break;


            case "Bouvet Island":

                break;


            case "Botswana": //Code: BW

                if (NetworkStartString.equalsIgnoreCase("mas")) { //mascom

                    UssdCode = MASCOM_BW_DATA_BALANCE;

                } else if (NetworkStartString.equalsIgnoreCase("ora")) { //orange

                    UssdCode = ORANGE_BW_DATA_BALANCE;

                }
                else {

                    Toast.makeText(getActivity(), "Ussd Code not Available For This Network, Ensure you Select The Correct Country!", Toast.LENGTH_LONG).show();
                    return;

                }
                requestForDataBalance(SimNo, NetworkName, SimSerical, UssdCode);

                break;


            case "Belarus":

                break;


            case "Belize":

                break;


            case "Canada":

                break;


            case "Cocos (Keeling) Islands":

                break;


            case "Congo, The Democratic Republic of the": //Code: CG


                if (NetworkStartString.equalsIgnoreCase("mtn")) { //mtn //

                    UssdCode = MTN_CG_DATA_BALANCE;

                } else if (NetworkStartString.equalsIgnoreCase("air")) { //Airtel

                    UssdCode = AIRTEL_CG_DATA_BALANCE;

                }
                else {

                    Toast.makeText(getActivity(), "Ussd Code not Available For This Network, Ensure you Select The Correct Country!", Toast.LENGTH_LONG).show();
                    return;

                }
                requestForDataBalance(SimNo, NetworkName, SimSerical, UssdCode);
                break;


            case "Central African Republic": //Code: CF

                if (NetworkStartString.equalsIgnoreCase("moo")) { //moov

                    UssdCode = MOOV_CF_DATA_BALANCE;

                } else if (NetworkStartString.equalsIgnoreCase("ora")) { ////orange

                    UssdCode = ORANGE_CF_DATA_BALANCE;

                }
                else {

                    Toast.makeText(getActivity(), "Ussd Code not Available For This Network, Ensure you Select The Correct Country!", Toast.LENGTH_LONG).show();
                    return;

                }
                requestForDataBalance(SimNo, NetworkName, SimSerical, UssdCode);

                break;


            case "Congo":

                break;


            case "Switzerland":

                break;


            case "Ivory Coast":

                if (NetworkStartString.equalsIgnoreCase("mtn")) { //mtn

                    UssdCode = MTN_CI_DATA_BALANCE;

                } else if (NetworkStartString.equalsIgnoreCase("ora")) { //orange

                    UssdCode = ORANGE_CI_DATA_BALANCE;

                }  else if (NetworkStartString.equalsIgnoreCase("moo")) { //moov

                    UssdCode = MOOV_CI_DATA_BALANCE;

                }
                else {

                    Toast.makeText(getActivity(), "Ussd Code not Available For This Network, Ensure you Select The Correct Country!", Toast.LENGTH_LONG).show();
                    return;

                }
                requestForDataBalance(SimNo, NetworkName, SimSerical, UssdCode);

                break;


            case "Cook Islands":

                break;


            case "Chile":

                break;


            case "Cameroon": //Code: CM

                if (NetworkStartString.equalsIgnoreCase("mtn")) { //

                    UssdCode = MTN_CM_DATA_BALANCE;

                } else if (NetworkStartString.equalsIgnoreCase("ora")) { //

                    UssdCode = ORANGE_CM_DATA_BALANCE;

                } else if (NetworkStartString.equalsIgnoreCase("nex")) { //

                    UssdCode = NEXTTEL_CM_DATA_BALANCE;

                } else if (NetworkStartString.equalsIgnoreCase("cam")) { //

                    UssdCode = CAMTEL_CM_DATA_BALANCE;

                }
                else {

                    Toast.makeText(getActivity(), "Ussd Code not Available For This Network, Ensure you Select The Correct Country!", Toast.LENGTH_LONG).show();
                    return;

                }
                requestForDataBalance(SimNo, NetworkName, SimSerical, UssdCode);


                break;


            case "China":

                break;


            case "Colombia":

                break;


            case "Costa Rica":

                break;


            case "Cuba":

                break;


            case "Cape Verde": //Code: CV

                if (NetworkStartStringShort.equalsIgnoreCase("cv")) { //Cv Movel

                    UssdCode = CV_MOVELL_DATA_BALANCE;

                } else if (NetworkStartString.equalsIgnoreCase("uni")) { //unitel

                    UssdCode = UNITELL_CV_DATA_BALANCE;

                }
                else {

                    Toast.makeText(getActivity(), "Ussd Code not Available For This Network, Ensure you Select The Correct Country!", Toast.LENGTH_LONG).show();
                    return;

                }
                requestForDataBalance(SimNo, NetworkName, SimSerical, UssdCode);

                break;


            case "Curacao":

                break;


            case "Christmas Island":

                break;


            case "Cyprus":

                break;


            case "Czech Republic":

                break;


            case "Germany":

                break;


            case "Djibouti": //Code: DJ

                if (NetworkStartString.equalsIgnoreCase("eva")) { //Evatis

                    UssdCode = EVATIS_DJ_DATA_BALANCE;

                }
                else {

                    Toast.makeText(getActivity(), "Ussd Code not Available For This Network, Ensure you Select The Correct Country!", Toast.LENGTH_LONG).show();
                    return;

                }
                requestForDataBalance(SimNo, NetworkName, SimSerical, UssdCode);

                break;


            case "Denmark":

                break;


            case "Dominica":

                break;


            case "Dominican Republic":

                break;


            case "Algeria":

                break;


            case "Ecuador":

                break;


            case "Estonia":

                break;


            case "Egypt":

                break;


            case "Western Sahara":

                break;


            case "Eritrea":



                break;


            case "Spain":

                break;


            case "Ethiopia":

                if (NetworkStartString.equalsIgnoreCase("eth")) { //ethio-Telecom

                    UssdCode = ETHIO_TELECOM_ET_DATA_BALANCE;

                }
                else {

                    Toast.makeText(getActivity(), "Ussd Code not Available For This Network, Ensure you Select The Correct Country!", Toast.LENGTH_LONG).show();
                    return;

                }
                requestForDataBalance(SimNo, NetworkName, SimSerical, UssdCode);
                break;


            case "Finland":

                break;


            case "Fiji":

                break;


            case "Falkland Islands":

                break;


            case "Micronesia, Federated States of":

                break;

            case "Faroe Islands":

                break;
            case "France":

                break;

            case "Gabon":
                if (NetworkStartString.equalsIgnoreCase("air")) { //Airtel

                    UssdCode = AIRTEL_GA_DATA_BALANCE;

                } else if (NetworkStartString.equalsIgnoreCase("gab")) { //Gabon Telecom

                    UssdCode = GABON_TELECOM_GA_DATA_BALANCE;

                }
                else {

                    Toast.makeText(getActivity(), "Ussd Code not Available For This Network, Ensure you Select The Correct Country!", Toast.LENGTH_LONG).show();
                    return;

                }
                requestForDataBalance(SimNo, NetworkName, SimSerical, UssdCode);
                break;

            case "United Kingdom":

                break;
            case "Grenada":

                break;
            case "Georgia":

                break;
            case "French Guiana":

                break;
            case "Guernsey":

                break;
            case "Ghana":

                break;
            case "Gibraltar":

                break;
            case "Greenland":

                break;
            case "Gambia":

                if (NetworkStartString.equalsIgnoreCase("afr")) { //Africell

                    UssdCode = AFRICELL_GM_DATA_BALANCE;

                } else if (NetworkStartString.equalsIgnoreCase("gam")) { //Gambia

                    UssdCode = GAMBIA_TELECOM_GM_DATA_BALANCE;

                } else if (NetworkStartString.equalsIgnoreCase("com")) { //Comium

                    UssdCode = COMIUM_GM_DATA_BALANCE;

                } else if (NetworkStartString.equalsIgnoreCase("qce")) { //Qcell

                    UssdCode = QCELL_GM_DATA_BALANCE;

                }
                else {

                    Toast.makeText(getActivity(), "Ussd Code not Available For This Network, Ensure you Select The Correct Country!", Toast.LENGTH_LONG).show();
                    return;

                }
                requestForDataBalance(SimNo, NetworkName, SimSerical, UssdCode);

                break;
            case "Guinea":

                break;
            case "Guadeloupe":

                break;
            case "Equatorial Guinea":

                if (NetworkStartString.equalsIgnoreCase("ora")) { //orrange

                    UssdCode = ORANGE_GQ_DATA_BALANCE;

                } else if (NetworkStartString.equalsIgnoreCase("mtn")) { //mtn

                    UssdCode = MTN_GQ_DATA_BALANCE;

                } else if (NetworkStartString.equalsIgnoreCase("cel")) { //Cellcom

                    Toast.makeText(getActivity(), "Call 8003099911", Toast.LENGTH_LONG).show();

                }
                else {

                    Toast.makeText(getActivity(), "Ussd Code not Available For This Network, Ensure you Select The Correct Country!", Toast.LENGTH_LONG).show();
                    return;

                }
                requestForDataBalance(SimNo, NetworkName, SimSerical, UssdCode);

                break;
            case "Greece":

                break;
            case "South Georgia and the South Sandwich Islands":

                break;
            case "Guatemala":

                break;
            case "Guam":

                break;
            case "Guinea-Bissau":

                break;
            case "Guyana":

                break;
            case "Hong Kong":

                break;
            case "Heard Island and McDonald Islands":

                break;
            case "Honduras":

                break;
            case "Croatia":

                break;
            case "Haiti":

                break;
            case "Hungary":

                break;
            case "Indonesia":

                break;
            case "Ireland":

                break;
            case "Israel":

                break;
            case "Isle of Man":

                break;
            case "India":

                break;
            case "British Indian Ocean Territory":

                break;
            case "Iraq":

                break;
            case "Iran, Islamic Republic of":

                break;
            case "Iceland":

                break;
            case "Italy":

                break;
            case "Jersey":

                break;
            case "Jamaica":

                break;
            case "Jordan":

                break;
            case "Japan":

                break;
            case "Kenya": //Code: KE


                if (NetworkStartString.equalsIgnoreCase("air")) { //Airtel

                    UssdCode = AIRTELL_KE_DATA_BALANCE;

                } else if (NetworkStartString.equalsIgnoreCase("saf")) { //Safaricom

                    UssdCode = SAFARICOM_TELECOM_KE_DATA_BALANCE;

                }  else if (NetworkStartString.equalsIgnoreCase("tel")) { //Telkom

                    UssdCode = TELKOM_KE_DATA_BALANCE;

                }
                else {

                    Toast.makeText(getActivity(), "Ussd Code not Available For This Network, Ensure you Select The Correct Country!", Toast.LENGTH_LONG).show();
                    return;

                }
                requestForDataBalance(SimNo, NetworkName, SimSerical, UssdCode);
                break;
            case "Kyrgyzstan":

                break;
            case "Cambodia":

                break;
            case "Kiribati":

                break;
            case "Comoros":

                break;
            case "Saint Kitts and Nevis":

                break;
            case "North Korea":

                break;
            case "South Korea":

                break;
            case "Kuwait":

                break;
            case "Cayman Islands":

                break;
            case "Kazakhstan":

                break;
            case "Lao People\'s Democratic Republic":

                break;
            case "Lebanon":

                break;
            case "Saint Lucia":

                break;
            case "Liechtenstein":

                break;
            case "Sri Lanka":

                break;
            case "Liberia":

                if (NetworkStartString.equalsIgnoreCase("mtn")) { //Mtn

                    UssdCode = MTN_LR_DATA_BALANCE;

                } else if (NetworkStartString.equalsIgnoreCase("ora")) { //

                    UssdCode = ORANGE_LR_DATA_BALANCE;


                }
                else {

                    Toast.makeText(getActivity(), "Ussd Code not Available For This Network, Ensure you Select The Correct Country!", Toast.LENGTH_LONG).show();
                    return;

                }
                requestForDataBalance(SimNo, NetworkName, SimSerical, UssdCode);

                break;
            case "Lesotho":

                if (NetworkStartString.equalsIgnoreCase("ECO")) { //Econet

                    UssdCode = ECONET_LS_DATA_BALANCE;

                } else if (NetworkStartString.equalsIgnoreCase("vod")) { //Vodacom

                    UssdCode = VODACOM_LS_DATA_BALANCE;

                }
                else {

                    Toast.makeText(getActivity(), "Ussd Code not Available For This Network, Ensure you Select The Correct Country!", Toast.LENGTH_LONG).show();
                    return;

                }
                requestForDataBalance(SimNo, NetworkName, SimSerical, UssdCode);
                break;
            case "Lithuania":

                break;
            case "Luxembourg":

                break;
            case "Latvia":

                break;
            case "Libyan Arab Jamahiriya":

                break;
            case "Morocco":

                break;
            case "Monaco":

                break;
            case "Moldova":

                break;
            case "Montenegro":

                break;
            case "Saint Martin":

                break;
            case "Madagascar":

                if (NetworkStartString.equalsIgnoreCase("air")) { //Airtel

                    UssdCode = AIRTEL_MG_DATA_BALANCE;

                } else if (NetworkStartString.equalsIgnoreCase("ora")) { //Orangw

                    UssdCode = ORANGE_MG_DATA_BALANCE;

                }
                else if (NetworkStartString.equalsIgnoreCase("tel")) { //Telma

                    UssdCode = TELMA_MG_DATA_BALANCE;

                }
                else {

                    Toast.makeText(getActivity(), "Ussd Code not Available For This Network, Ensure you Select The Correct Country!", Toast.LENGTH_LONG).show();
                    return;

                }
                requestForDataBalance(SimNo, NetworkName, SimSerical, UssdCode);

                break;
            case "Marshall Islands":

                break;
            case "Macedonia, The Former Yugoslav Republic of":

                break;
            case "Mali":

                if (NetworkStartString.equalsIgnoreCase("ora")) { //Orange

                    UssdCode = ORANGE_ML_DATA_BALANCE;

                } else if (NetworkStartString.equalsIgnoreCase("sot")) { //Sotelma

                    UssdCode = SOTELMA_ML_DATA_BALANCE;

                }
                else {

                    Toast.makeText(getActivity(), "Ussd Code not Available For This Network, Ensure you Select The Correct Country!", Toast.LENGTH_LONG).show();
                    return;

                }
                requestForDataBalance(SimNo, NetworkName, SimSerical, UssdCode);

                break;

            case "Myanmar":

                break;

            case "Mongolia":

                break;
            case "Macao":

                break;
            case "Northern Mariana Islands":

                break;
            case "Martinique":

                break;
            case "Mauritania":

                if (NetworkStartString.equalsIgnoreCase("mau")) { //mauritell

                    UssdCode = MAURITEL_MR_DATA_BALANCE;

                } else if (NetworkStartString.equalsIgnoreCase("chi")) { //Chinguitell

                    UssdCode = CHINGUITEL_MR_DATA_BALANCE;

                } else if (NetworkStartString.equalsIgnoreCase("mat")) { //

                    UssdCode = MATTEL_MR_DATA_BALANCE;

                }
                else {

                    Toast.makeText(getActivity(), "Ussd Code not Available For This Network, Ensure you Select The Correct Country!", Toast.LENGTH_LONG).show();
                    return;

                }
                requestForDataBalance(SimNo, NetworkName, SimSerical, UssdCode);

                break;
            case "Montserrat":

                break;
            case "Malta":

                break;
            case "Mauritius":

                if (NetworkStartString.equalsIgnoreCase("ora")) { //Orange

                    UssdCode = ORANGE_MU_DATA_BALANCE;

                } else if (NetworkStartString.equalsIgnoreCase("emt")) { //Emtel

                    UssdCode = EMTEL_MU_DATA_BALANCE;

                } else if (NetworkStartString.equalsIgnoreCase("chi")) { //Chili

                    UssdCode = CHILI_MU_DATA_BALANCE;

                }
                else {

                    Toast.makeText(getActivity(), "Ussd Code not Available For This Network, Ensure you Select The Correct Country!", Toast.LENGTH_LONG).show();
                    return;

                }
                requestForDataBalance(SimNo, NetworkName, SimSerical, UssdCode);

                break;
            case "Maldives":

                break;
            case "Malawi":

                if (NetworkStartString.equalsIgnoreCase("air")) { //Airtel

                    UssdCode = AIRTEL_MW_DATA_BALANCE;

                } else if (NetworkStartString.equalsIgnoreCase("tnm")) { //TNM

                    UssdCode = TNM_MW_DATA_BALANCE;

                }
                else {

                    Toast.makeText(getActivity(), "Ussd Code not Available For This Network, Ensure you Select The Correct Country!", Toast.LENGTH_LONG).show();
                    return;

                }
                requestForDataBalance(SimNo, NetworkName, SimSerical, UssdCode);

                break;
            case "Mexico":

                break;
            case "Malaysia":

                break;
            case "Mozambique":

                if (NetworkStartString.equalsIgnoreCase("mce")) { //

                    UssdCode = MCEL_MZ_DATA_BALANCE;

                } else if (NetworkStartString.equalsIgnoreCase("vod")) { //

                    UssdCode = VODACOM_MZ_DATA_BALANCE;

                } else if (NetworkStartString.equalsIgnoreCase("mov")) { //Movitel

                    UssdCode = MOVITEL_MZ_DATA_BALANCE;

                }
                else {

                    Toast.makeText(getActivity(), "Ussd Code not Available For This Network, Ensure you Select The Correct Country!", Toast.LENGTH_LONG).show();
                    return;

                }
                requestForDataBalance(SimNo, NetworkName, SimSerical, UssdCode);

                break;
            case "Namibia":


                if (NetworkStartString.equalsIgnoreCase("air")) { //Airtel

                    UssdCode = AIRTEL_NA_DATA_BALANCE;

                } else if (NetworkStartString.equalsIgnoreCase("moo")) { //moov

                    UssdCode = MOOV_NA_DATA_BALANCE;

                }
                else {

                    Toast.makeText(getActivity(), "Ussd Code not Available For This Network, Ensure you Select The Correct Country!", Toast.LENGTH_LONG).show();
                    return;

                }
                requestForDataBalance(SimNo, NetworkName, SimSerical, UssdCode);

                break;
            case "New Caledonia":

                break;
            case "Niger":

                break;
            case "Norfolk Island":

                break;
            case "Nigeria":
               // Toast.makeText(getActivity(), "came here", Toast.LENGTH_LONG).show();
                if (NetworkStartString.equalsIgnoreCase("air")) { //airtel

                    UssdCode = AIRTEL_NG_AIRTIME_BALANCE;

                } else if (NetworkStartString.equalsIgnoreCase("mtn")) { //mtn

                    UssdCode = MTN_NG_AIRTIME_BALANCE;

                } else if (NetworkStartString.equalsIgnoreCase("glo")) { //glo

                    UssdCode = GLO_NG_AIRTIME_BALANCE;

                } else if (NetworkStartString.equalsIgnoreCase("9mo") || (NetworkStartString.equalsIgnoreCase("eti"))) { //9mobile or etisalat

                    UssdCode = ETISALAT_NG_AIRTIME_BALANCE;

                }
                else {

                    Toast.makeText(getActivity(), "Ussd Code not Available For This Network, Ensure you Select The Correct Country!", Toast.LENGTH_LONG).show();
                    return;

                }
                requestForDataBalance(SimNo, NetworkName, SimSerical, UssdCode);

                break;

            case "Nicaragua":

                break;
            case "Netherlands":

                break;
            case "Norway":

                break;
            case "Nepal":

                break;
            case "Nauru":

                break;
            case "Niue":

                break;
            case "New Zealand":

                break;
            case "Oman":

                break;
            case "Panama":

                break;
            case "Peru":

                break;
            case "French Polynesia":

                break;
            case "Papua New Guinea":

                break;
            case "Philippines":

                break;
            case "Pakistan":

                break;
            case "Poland":

                break;
            case "Saint Pierre and Miquelon":

                break;
            case "Pitcairn":

                break;
            case "Puerto Rico":

                break;
            case "Palestinian Territory, Occupied":

                break;
            case "Portugal":

                break;
            case "Palau":

                break;
            case "Paraguay":

                break;
            case "Qatar":

                break;
            case "Réunion":

                break;
            case "Romania":

                break;
            case "Serbia":

                break;
            case "Russia":

                break;
            case "Rwanda":

                if (NetworkStartString.equalsIgnoreCase("mtn")) { //mtn

                    UssdCode = MTN_RW_DATA_BALANCE;

                } else if (NetworkStartString.equalsIgnoreCase("air")) { //Airtel-Tigo

                    UssdCode = AIRTEL_TIGO_RW_DATA_BALANCE;

                }
                else {

                    Toast.makeText(getActivity(), "Ussd Code not Available For This Network, Ensure you Select The Correct Country!", Toast.LENGTH_LONG).show();
                    return;

                }
                requestForDataBalance(SimNo, NetworkName, SimSerical, UssdCode);

                break;
            case "Saudi Arabia":

                break;
            case "Solomon Islands":

                break;
            case "Seychelles":

                if (NetworkStartString.equalsIgnoreCase("air")) { //Airte;

                    UssdCode = AIRTEL_SC_DATA_BALANCE;

                } else if (NetworkStartString.equalsIgnoreCase("cab")) { //cable

                    UssdCode = CABLE_SC_DATA_BALANCE;

                }
                else {

                    Toast.makeText(getActivity(), "Ussd Code not Available For This Network, Ensure you Select The Correct Country!", Toast.LENGTH_LONG).show();
                    return;

                }
                requestForDataBalance(SimNo, NetworkName, SimSerical, UssdCode);

                break;
            case "Sudan":

                if (NetworkStartString.equalsIgnoreCase("mtn")) { //mtn

                    UssdCode = MTN_SD_DATA_BALANCE;

                } else if (NetworkStartString.equalsIgnoreCase("zai")) { //zain

                    UssdCode = ZAIN_SD_DATA_BALANCE;

                }
                else {

                    Toast.makeText(getActivity(), "Ussd Code not Available For This Network, Ensure you Select The Correct Country!", Toast.LENGTH_LONG).show();
                    return;

                }
                requestForDataBalance(SimNo, NetworkName, SimSerical, UssdCode);

                break;
            case "Sweden":

                break;
            case "Singapore":

                break;
            case "Saint Helena, Ascension and Tristan Da Cunha":

                break;
            case "Slovenia":

                break;
            case "Svalbard and Jan Mayen":

                break;
            case "Slovakia":

                break;
            case "Sierra Leone":

                if (NetworkStartString.equalsIgnoreCase("afr")) { //Africell

                    UssdCode = AFRICELL_SL_DATA_BALANCE;

                } else if (NetworkStartString.equalsIgnoreCase("ora")) { //Orange

                    UssdCode = ORANGE_SL_DATA_BALANCE;

                } else if (NetworkStartString.equalsIgnoreCase("sma")) { //Smart

                    UssdCode = SMART_SL_DATA_BALANCE;

                }
                else {

                    Toast.makeText(getActivity(), "Ussd Code not Available For This Network, Ensure you Select The Correct Country!", Toast.LENGTH_LONG).show();
                    return;

                }
                requestForDataBalance(SimNo, NetworkName, SimSerical, UssdCode);

                break;
            case "San Marino":

                break;
            case "Senegal":

                if (NetworkStartString.equalsIgnoreCase("Exp")) { //Expresso

                    UssdCode = EXPRESSO_SN_DATA_BALANCE;

                } else if (NetworkStartString.equalsIgnoreCase("ora")) { //Orange

                    UssdCode = ORANGE_SN_DATA_BALANCE;

                } else if (NetworkStartString.equalsIgnoreCase("tig")) { //Tigo

                    UssdCode = TIGO_SN_DATA_BALANCE;

                }
                else {

                    Toast.makeText(getActivity(), "Ussd Code not Available For This Network, Ensure you Select The Correct Country!", Toast.LENGTH_LONG).show();
                    return;

                }
                requestForDataBalance(SimNo, NetworkName, SimSerical, UssdCode);

                break;
            case "Somalia":

                break;
            case "Suriname":

                break;
            case "South Sudan":

                break;
            case "Sao Tome and Principe":

                if (NetworkStartString.equalsIgnoreCase("uni")) { //Unitel

                    UssdCode = UNITEL_ST_DATA_BALANCE;

                } else if (NetworkStartString.equalsIgnoreCase("cst")) { //CST

                    UssdCode = CST_ST_DATA_BALANCE;

                }
                else {

                    Toast.makeText(getActivity(), "Ussd Code not Available For This Network, Ensure you Select The Correct Country!", Toast.LENGTH_LONG).show();
                    return;

                }
                requestForDataBalance(SimNo, NetworkName, SimSerical, UssdCode);

                break;

            case "El Salvador":

                break;

            case "Sint Maarten":

                break;

            case "Syrian Arab Republic":

                break;
            case "Swaziland":

                if (NetworkStartString.equalsIgnoreCase("mtn")) { //MTN

                    UssdCode = MTN_SZ_DATA_BALANCE;

                }
                else {

                    Toast.makeText(getActivity(), "Ussd Code not Available For This Network, Ensure you Select The Correct Country!", Toast.LENGTH_LONG).show();
                    return;

                }
                requestForDataBalance(SimNo, NetworkName, SimSerical, UssdCode);

                break;

            case "Turks and Caicos Islands":

                break;

            case "Chad": //TD

                if (NetworkStartString.equalsIgnoreCase("tig")) { //tigo

                    UssdCode = TIGO_TD_AIRTIME_BALANCE;

                } else if (NetworkStartString.equalsIgnoreCase("air")) { //airrtel

                    UssdCode = AIRTEL_TD_AIRTIME_BALANCE;

                }
                else {

                    Toast.makeText(getActivity(), "Ussd Code not Available For This Network, Ensure you Select The Correct Country!", Toast.LENGTH_LONG).show();
                    return;

                }
                requestForDataBalance(SimNo, NetworkName, SimSerical, UssdCode);
                break;

            case "French Southern Territories":

                break;

            case "Togo":

                if (NetworkStartString.equalsIgnoreCase("tog")) { //Togocell

                    UssdCode = TOGOCEL_TG_DATA_BALANCE;

                } else if (NetworkStartString.equalsIgnoreCase("moo")) { //moov

                    UssdCode = MOOV_TG_DATA_BALANCE;

                }
                else {

                    Toast.makeText(getActivity(), "Ussd Code not Available For This Network, Ensure you Select The Correct Country!", Toast.LENGTH_LONG).show();
                    return;

                }
                requestForDataBalance(SimNo, NetworkName, SimSerical, UssdCode);

                break;

            case "Thailand":

                break;

            case "Tajikistan":

                break;

            case "Tokelau":

                break;

            case "East Timor":

                break;

            case "Turkmenistan":

                break;

            case "Tunisia":

                break;

            case "Tonga":

                break;
            case "Turkey":

                break;

            case "Trinidad and Tobago":

                break;

            case "Tuvalu":

                break;

            case "Taiwan":

                break;

            case "Tanzania, United Republic of":

                if (NetworkStartString.equalsIgnoreCase("vod")) { //vodacom

                    UssdCode = VODACOM_TZ_DATA_BALANCE;

                } else if (NetworkStartString.equalsIgnoreCase("air")) { //Airtel

                    UssdCode = AIRTEL_TZ_DATA_BALANCE;

                } else if (NetworkStartString.equalsIgnoreCase("tig")) { //Tigo

                    UssdCode = TIGO_TZ_DATA_BALANCE;

                } else if (NetworkStartString.equalsIgnoreCase("zan")) { //Zantel

                    UssdCode = ZANTEL_TZ_DATA_BALANCE;

                }
                else {

                    Toast.makeText(getActivity(), "Ussd Code not Available For This Network, Ensure you Select The Correct Country!", Toast.LENGTH_LONG).show();
                    return;

                }
                requestForDataBalance(SimNo, NetworkName, SimSerical, UssdCode);


                break;

            case "Ukraine":

                break;

            case "Uganda":

                if (NetworkStartString.equalsIgnoreCase("air")) { //Airtel

                    UssdCode = AIRTEL_UG_DATA_BALANCE;

                } else if (NetworkStartString.equalsIgnoreCase("mtn")) { //,tm

                    UssdCode = MTN_UG_DATA_BALANCE;

                } else if (NetworkStartString.equalsIgnoreCase("afr")) { //Africell

                    UssdCode = AFRICELL_UG_DATA_BALANCE;

                } else if (NetworkStartString.equalsIgnoreCase("sma")) { //Smart

                    UssdCode = SMART_UG_DATA_BALANCE;

                } else if (NetworkStartString.equalsIgnoreCase("utl")) { //

                    UssdCode = UTL_UG_DATA_BALANCE;

                }
                else {

                    Toast.makeText(getActivity(), "Ussd Code not Available For This Network, Ensure you Select The Correct Country!", Toast.LENGTH_LONG).show();
                    return;

                }
                requestForDataBalance(SimNo, NetworkName, SimSerical, UssdCode);

                break;

            case "U.S. Minor Outlying Islands":

                break;
            case "United States":


                if (NetworkStartString.equalsIgnoreCase("t-m")) { //Tmobile

                    UssdCode = "#225#";

                } else {

                    Toast.makeText(getActivity(), "Ussd Code not Available For This Network, Ensure you Select The Correct Country!", Toast.LENGTH_LONG).show();
                    return;

                }
                requestForDataBalance(SimNo, NetworkName, SimSerical, UssdCode);


                break;

            case "Uruguay":

                break;

            case "Uzbekistan":

                break;

            case "Holy See (Vatican City State)":

                break;

            case "Saint Vincent and the Grenadines":

                break;

            case "Venezuela, Bolivarian Republic of":

                break;

            case "Virgin Islands, British":

                break;

            case "Virgin Islands, U.S.":

                break;
            case "Viet Nam":

                break;

            case "Vanuatu":

                break;

            case "Wallis and Futuna":

                break;

            case "Samoa":

                break;

            case "Kosovo":

                break;

            case "Yemen":

                break;

            case "Mayotte":

                break;

            case "South Africa":

                if (NetworkStartString.equalsIgnoreCase("vod")) { //Vodacom

                    UssdCode = VODACOM_ZA_DATA_BALANCE;

                } else if (NetworkStartString.equalsIgnoreCase("mtn")) { //mtn

                    UssdCode = MTN_ZA_DATA_BALANCE;

                } else if (NetworkStartString.equalsIgnoreCase("cel")) { //Cellc

                    UssdCode = CELLC_ZA_DATA_BALANCE;

                }
                else {

                    Toast.makeText(getActivity(), "Ussd Code not Available For This Network, Ensure you Select The Correct Country!", Toast.LENGTH_LONG).show();
                    return;

                }
                requestForDataBalance(SimNo, NetworkName, SimSerical, UssdCode);

                break;
            case "Zambia":

                if (NetworkStartString.equalsIgnoreCase("air")) { //Airtel

                    UssdCode = AIRTEL_ZM_DATA_BALANCE;

                } else if (NetworkStartString.equalsIgnoreCase("mtn")) { //mtn

                    UssdCode = MTN_ZM_DATA_BALANCE;

                } else if (NetworkStartString.equalsIgnoreCase("zam")) { //zamtell

                    UssdCode = ZAMTELL_ZM_DATA_BALANCE;

                }
                else {

                    Toast.makeText(getActivity(), "Ussd Code not Available For This Network, Ensure you Select The Correct Country!", Toast.LENGTH_LONG).show();
                    return;

                }
                requestForDataBalance(SimNo, NetworkName, SimSerical, UssdCode);

                break;

            case "Zimbabwe":


                if (NetworkStartString.equalsIgnoreCase("eco")) { //EConet

                    UssdCode = ECONET_ZW_DATA_BALANCE;

                } else if (NetworkStartString.equalsIgnoreCase("tel")) { //Telecel

                    UssdCode = TELECEL_ZW_DATA_BALANCE;

                } else if (NetworkStartString.equalsIgnoreCase("net")) { //

                    UssdCode = NETONE_ZW_DATA_BALANCE;

                }
                else {

                    Toast.makeText(getActivity(), "Ussd Code not Available For This Network, Ensure you Select The Correct Country!", Toast.LENGTH_LONG).show();
                    return;

                }
                requestForDataBalance(SimNo, NetworkName, SimSerical, UssdCode);


                break;


        }


    }



    private void requestForDataBalance(int sim, String SimName, String SimIccid, String UssdCode) {
        //check if android version is below android o
        if (android.os.Build.VERSION.SDK_INT <= android.os.Build.VERSION_CODES.O) {
            //check Accessibility Service Status whether enable or not
            if (!isAccessServiceEnabled(Objects.requireNonNull(getContext()), UssdMessageReceiver.class)) {
                checkAccessibilityServiceStatus(getActivity());
                return;
            }
        }


        if (!(UssdCode.equalsIgnoreCase(""))) {
                    /*
                      check version and perform request for airtime balance
                     */
            if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
                applicationModel.setIccid(SimIccid);
                initLoadingDialog(getActivity());
                dialUssdCodeForNewAPI(
                        getActivity(),
                        UssdCode,
                        getActivity(),
                        sim

                );
            } else {
                dialUssdCodeForOldAPI(
                        getActivity(),
                        UssdCode,
                        sim
                );

                applicationModel.setIccid(SimIccid);
            }
        }

    }

    private void initViews(View view) {

        fullLayoutSIM1 = view.findViewById(R.id.layoutView1);
        fullLayoutSIM2 = view.findViewById(R.id.layoutView2);

        img_History1 = view.findViewById(R.id.img_History1);
        img_History2 = view.findViewById(R.id.img_History2);

        btnCheckBalance1 = view.findViewById(R.id.btnCheckBalance1);
        btnCheckBalance2 = view.findViewById(R.id.btnCheckBalance2);

        tvSim1Country = view.findViewById(R.id.tvSim1Country);
        tvSim2Country = view.findViewById(R.id.tvSim2Country);


        tvSim1Network = view.findViewById(R.id.tvSim1Network);
        tvSim2Network = view.findViewById(R.id.tvSim2Network);

        tvCopyright = view.findViewById(R.id.tv_copyright);
//
//        countrySpinner = view.findViewById(R.id.countrySpinner);

        tvCountry = view.findViewById(R.id.tvSelectCountry);


        Fragment navhost = getActivity().getSupportFragmentManager().findFragmentById(R.id.nav_host_fragment);
        navController = NavHostFragment.findNavController(navhost);

        applicationModel = new ApplicationModel();

    }


    private final BroadcastReceiver broadcastReceiver = new BroadcastReceiver() {
        @RequiresApi(api = Build.VERSION_CODES.KITKAT)
        @Override
        public void onReceive(Context context, Intent intent) {
            if (Objects.requireNonNull(intent.getAction()).equalsIgnoreCase("TingtelMessage")) {
                String msg = intent.getStringExtra("message");
                //analyze the message content
                   checkPopupMessageForUssdHighVersion(msg);
                Log.e("logtingtel", "High Version");

            } else if (Objects.requireNonNull(intent.getAction()).equalsIgnoreCase("TintelIntentMessage")) {
                String message = intent.getStringExtra("TingtelMessage");

                checkPopupMessageForUssdLowVersion(message);
                Log.e("logtingtel", "Low Version");

            }
//            else if (Objects.requireNonNull(intent.getAction()).equalsIgnoreCase("SmsMessage")) {
//                String message = intent.getStringExtra("message");
//                String senderNum = intent.getStringExtra("senderNum");
//
//                Log.e("logtingtel", "sms");
////                checkMessageForSMS(message, senderNum);
//            }
        }
    };

    private void checkPopupMessageForUssdLowVersion(String message) {
        saveToDatabase(
                applicationModel.getSimname(),
                message,
                applicationModel.getServiceType()
        );
    }

    private void checkPopupMessageForUssdHighVersion(String msg) {
        saveToDatabase(
                applicationModel.getSimname(),
                msg,
                applicationModel.getServiceType()
        );
    }

    private void saveToDatabase(String simname, String message, String ServiceType) {
        String simiccid = applicationModel.getIccid();
        if (applicationModel.getIccid() == null) {
            Log.e("logmessage", "sim iccid not available");
            return;
        }

        Log.e("logmessage", simiccid);

        try {
            saveAirtimeOrDataToDatabase(getActivity(), simiccid, simname, "", message, ServiceType);
        } catch (Exception e) {
            e.printStackTrace();
        }


    }

    @Override
    public void onResume() {
        LocalBroadcastManager.getInstance(getActivity()).registerReceiver(SmsReceiver, new IntentFilter("SmsMessage"));
        Log.e("tingtelmessage", "started broadcast again2");
        super.onResume();
    }

    @Override
    public void onPause() {
        super.onPause();
        LocalBroadcastManager.getInstance(getActivity()).unregisterReceiver(SmsReceiver);
        // LocalBroadcastManager.getInstance(this).unregisterReceiver(UssdReceiver);
    }

    @Override
    public void onDestroy() {

        LocalBroadcastManager.getInstance(getActivity()).unregisterReceiver(UssdReceiver);
        LocalBroadcastManager.getInstance(getActivity()).unregisterReceiver(UssdReceiverHighVersion);
        sessionManager.setReceiverStatus("NotStarted");
        Log.e("logmessage", "ondestroy");
        super.onDestroy();
    }
    private void initBroadcastReceivers() {
        Log.e("tingtelmessage", "started broadcast again");

        if (!(sessionManager.getReceiverStatus().equalsIgnoreCase("Started"))) {
            LocalBroadcastManager.getInstance(getActivity()).registerReceiver(UssdReceiver, new IntentFilter("TintelIntentMessage"));
            LocalBroadcastManager.getInstance(getActivity()).registerReceiver(UssdReceiverHighVersion, new IntentFilter("TingtelMessage"));
        Log.e("logmessage", "is false");
        }
        sessionManager.setReceiverStatus("Started");
    }





    private BroadcastReceiver SmsReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            if (intent.getAction().equalsIgnoreCase("SmsMessage")) {


//                myIntent.putExtra("senderNum", phoneNumber);
//                myIntent.putExtra("message", messageBody);
                String message = intent.getStringExtra("message");



                //Toast.makeText(getActivity(),  sessionManager.getAppState(), Toast.LENGTH_SHORT).show();
              //  Toast.makeText(getActivity(), ""+ sessionManager.getAppState(), Toast.LENGTH_SHORT).show();
                if (sessionManager.getAppState().equalsIgnoreCase("background")) { //nullpointer crash
                    Log.e("logmessage", "App is in background, wont respond to sms");
                    return;
                } else {
                    Log.e("logmessage", "App is in foreground, wont respond to sms");
                }


                saveToDatabase(
                        applicationModel.getSimname(),
                        message,
                        applicationModel.getServiceType()
                );


            }
        }
    };

    //Broadcast receiver to receive ussd code
    public BroadcastReceiver UssdReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            Log.e("logmessage", "2222222"  + " Low Version");
            String message = intent.getStringExtra("TingtelMessage");
            //  Toast.makeText(context, "received", Toast.LENGTH_SHORT).show();

            Log.e("logmessage", "2222222" + message + " Low Version");

            checkPopupMessageForUssdLowVersion(message);





        }
    };


    //Broadcast receiver to receive ussd code for android version above 8
    public BroadcastReceiver UssdReceiverHighVersion = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            Log.e("logmessage", "2222222"  + " High Version");
            String message = intent.getStringExtra("message");
            //  Toast.makeText(context, "received", Toast.LENGTH_SHORT).show();

            Log.e("logmessage", "2222222" + message + " High Version");

            checkPopupMessageForUssdHighVersion(message);





        }
    };

}
