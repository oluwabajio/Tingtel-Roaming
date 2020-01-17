package tingtel.android.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import tingtel.android.R;
import tingtel.android.adapters.BalanceAdapter;
import tingtel.android.databases.AppDatabase;
import tingtel.android.models.Balance;


public class HistoryFragment extends Fragment {

    private static RecyclerView.Adapter adapter;
    private AppDatabase appdatabase;
    private List<Balance> items = new ArrayList<>();
    private RecyclerView Rv_Balance;
    private String SimIccid;
    private String BalanceType;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_history, container, false);

        Toast.makeText(getActivity(), "" + getArguments().getString("SimSerial")
                + getArguments().getString("BalanceType"), Toast.LENGTH_SHORT).show();


        appdatabase = AppDatabase.getDatabaseInstance(getActivity());
        Rv_Balance = view.findViewById(R.id.rv_history);


        SimIccid = getArguments().getString("SimSerial");

        BalanceType = getArguments().getString("BalanceType");

        LoadDatabaseData(SimIccid, BalanceType, null);


        return view;


    }

    private void LoadDatabaseData(final String SimIccid, final String BalanceType, final String SimName) {
        //load saved room data to recyclerview
        Runnable r = () -> {
            items.clear();
            //items = appdatabase.balanceDao().getAirtimeOrDataList(SimIccid, BalanceType);
            items = appdatabase.balanceDao().getAllItems();
            getActivity().runOnUiThread(() -> {
                Rv_Balance.setLayoutManager(new LinearLayoutManager(getActivity()));
                //show latest items first
                Collections.reverse(items);
                adapter = new BalanceAdapter(getActivity(), items);
                //Toast.makeText(getActivity(), "Size is " + items.size(), Toast.LENGTH_SHORT).show();
                try {
                    Rv_Balance.setAdapter(adapter);
                    adapter.notifyDataSetChanged();
                } catch (Exception e) {
                    //Toast.makeText(getActivity(), "something amiss", Toast.LENGTH_SHORT).show();
                }
            });
        };

        Thread newThread = new Thread(r);
        newThread.start();
    }

    private void LoadDatabase() {
        Runnable r = () -> {
            items.clear();
            items = appdatabase.balanceDao().getAllItems();
            getActivity().runOnUiThread(() -> {
                Rv_Balance.setLayoutManager(new LinearLayoutManager(getActivity()));
                //show latest items first
                Collections.reverse(items);
                adapter = new BalanceAdapter(getActivity(), items);
                try {
                    Rv_Balance.setAdapter(adapter);
                    adapter.notifyDataSetChanged();
                    //Toast.makeText(getActivity(), "notifydone", Toast.LENGTH_SHORT).show();

                } catch (Exception e) {
                    Toast.makeText(getActivity(), "something amiss", Toast.LENGTH_SHORT).show();
                }
            });
        };
        Thread newThread = new Thread(r);
        newThread.start();
    }
}