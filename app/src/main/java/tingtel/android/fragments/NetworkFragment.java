package tingtel.android.fragments;


import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;


import com.ybs.countrypicker.CountryPicker;
import com.ybs.countrypicker.CountryPickerListener;

import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.fragment.NavHostFragment;
import tingtel.android.MainActivity;
import tingtel.android.R;
import tingtel.android.adapters.CountrySpinnerAdapter;
import tingtel.android.utils.NetworkCarrierUtils;
import tingtel.android.utils.SessionManager;

import static tingtel.android.utils.AppUtils.getSessionManagerInstance;

/**
 * A simple {@link Fragment} subclass.
 */
public class NetworkFragment extends Fragment {

    SessionManager sessionManager = getSessionManagerInstance();
    private CardView fullLayoutSIM1, fullLayoutSIM2;
    private ImageView img_History1, img_History2;
    private Spinner countrySpinner;
    private TextView tvCountry;
    private TextView tvSim1Network, tvSim2Network;
    private Button  btnCheckBalance1, btnCheckBalance2;
    NavController navController;
    String Sim1Serial, Sim2Serial;


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

       // initCountrySpinner();

        getDataFromCarrier(view);

        loadSavedData();
        return view;
    }

    private void loadSavedData() {
        tvCountry.setText(sessionManager.getCountry());
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

        Sim1Serial = sessionManager.getSimSerialICCID();
        Sim2Serial = sessionManager.getSimSerialICCID1();
    }

    private void initListeners(View view) {

        img_History1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Bundle bundle = new Bundle();
                bundle.putString("SimSerial", "bn4y8u4");

                navController.navigate(R.id.action_networkFragment_to_historyFragment, bundle);
            }
        });

        img_History2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Bundle bundle = new Bundle();
                bundle.putString("SimSerial", ",jjdhwuuhjw");

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
            }
        });

        btnCheckBalance2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
    }

    private void initViews(View view) {

        fullLayoutSIM1 = view.findViewById(R.id.layoutView1);
        fullLayoutSIM2 = view.findViewById(R.id.layoutView2);

        img_History1 = view.findViewById(R.id.img_History1);
        img_History2 = view.findViewById(R.id.img_History2);

        btnCheckBalance1 = view.findViewById(R.id.btnCheckBalance1);
        btnCheckBalance2 = view.findViewById(R.id.btnCheckBalance2);

        tvSim1Network = view.findViewById(R.id.tvSim1Network);
        tvSim2Network = view.findViewById(R.id.tvSim2Network);
//
//        countrySpinner = view.findViewById(R.id.countrySpinner);

        tvCountry = view.findViewById(R.id.tvSelectCountry);


        Fragment navhost = getActivity().getSupportFragmentManager().findFragmentById(R.id.nav_host_fragment);
        navController = NavHostFragment.findNavController(navhost);

    }

    private void getDataFromCarrier() {
        NetworkCarrierUtils.getCarrierOfSim(getContext(), getActivity());


        String NoOfSIm = sessionManager.getSimStatus();
        String Sim1Network = sessionManager.getNetworkName();
        String Sim2Network = sessionManager.getNetworkName1();
        String Sim1Iccid = sessionManager.getSimSerialICCID();
        String Sim2Iccid = sessionManager.getSimSerialICCID1();



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


}
