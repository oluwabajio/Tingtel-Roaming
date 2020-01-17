package tingtel.android;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import tingtel.android.utils.NetworkCarrierUtils;
import tingtel.android.utils.SessionManager;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import static tingtel.android.utils.AppUtils.getSessionManagerInstance;

public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

SessionManager sessionManager = getSessionManagerInstance();
sessionManager.setReceiverStatus("NotStarted");



    }



}
