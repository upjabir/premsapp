package prem.raj.data;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import prem.raj.R;

import org.eazegraph.lib.charts.PieChart;
import org.eazegraph.lib.models.PieModel;

import java.text.NumberFormat;
import java.util.Objects;

import prem.raj.MainActivity;
import prem.raj.activities.DistrictwiseDataActivity;
import prem.raj.activities.precuation;

import static prem.raj.activities.StatewiseDataActivity.STATE_ACTIVE;
import static prem.raj.activities.StatewiseDataActivity.STATE_CONFIRMED;
import static prem.raj.activities.StatewiseDataActivity.STATE_DECEASED;
import static prem.raj.activities.StatewiseDataActivity.STATE_LAST_UPDATE;
import static prem.raj.activities.StatewiseDataActivity.STATE_NAME;
import static prem.raj.activities.StatewiseDataActivity.STATE_NEW_CONFIRMED;
import static prem.raj.activities.StatewiseDataActivity.STATE_NEW_DECEASED;
import static prem.raj.activities.StatewiseDataActivity.STATE_NEW_RECOVERED;
import static prem.raj.activities.StatewiseDataActivity.STATE_RECOVERED;

public class PerStateData extends AppCompatActivity {

    TextView perStateConfirmed, perStateActive, perStateDeceased, perStateNewConfirmed, perStateNewRecovered, perStateNewDeceased, perStateUpdate, perStateRecovered, perstateName,covid_status;
    PieChart mPieChart;
    String stateName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_per_state_data);

        Intent intent = getIntent();
        stateName = intent.getStringExtra(STATE_NAME);
        String stateConfirmed = intent.getStringExtra(STATE_CONFIRMED);
        String stateActive = intent.getStringExtra(STATE_ACTIVE);
        String stateDeceased = intent.getStringExtra(STATE_DECEASED);
        String stateNewConfirmed = intent.getStringExtra(STATE_NEW_CONFIRMED);
        String stateNewRecovered = intent.getStringExtra(STATE_NEW_RECOVERED);
        String stateNewDeceased = intent.getStringExtra(STATE_NEW_DECEASED);
        String stateLastUpdate = intent.getStringExtra(STATE_LAST_UPDATE);
        String stateRecovery = intent.getStringExtra(STATE_RECOVERED);

        Objects.requireNonNull(getSupportActionBar()).setTitle(stateName);
        perStateConfirmed = findViewById(R.id.perstate_confirmed_textview);
        perStateActive = findViewById(R.id.perstate_active_textView);
        perStateRecovered = findViewById(R.id.perstate_recovered_textView);
        perStateDeceased = findViewById(R.id.perstate_death_textView);
        //perStateUpdate = findViewById(R.id.perstate_lastupdate_textView);
        perStateNewConfirmed = findViewById(R.id.perstate_confirmed_new_textView);
        perStateNewRecovered = findViewById(R.id.perstate_recovered_new_textView);
        perStateNewDeceased = findViewById(R.id.perstate_death_new_textView);
        perstateName = findViewById(R.id.district_data_title);
        mPieChart = findViewById(R.id.piechart_perstate);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        covid_status=findViewById(R.id.textView2);
        getWindow().setNavigationBarColor(ContextCompat.getColor(this, R.color.colorDark));

        String activeCopy = "10";
        String recoveredCopy = "5";
        String deceasedCopy = "0";

        int stateActiveInt = Integer.parseInt(stateActive);
        stateActive = NumberFormat.getInstance().format(stateActiveInt);

        if (stateActiveInt > 5000){
            mPieChart.addPieSlice(new PieModel("High", Integer.parseInt(activeCopy), Color.parseColor("#F6404F")));
            mPieChart.addPieSlice(new PieModel("Medium", Integer.parseInt(recoveredCopy), Color.parseColor("#007afe")));
            mPieChart.addPieSlice(new PieModel("Low", Integer.parseInt(deceasedCopy), Color.parseColor("#08a045")));

            mPieChart.startAnimation();

            covid_status.setText("Covid-19 cases are high and spreading");
        }
        else if(stateActiveInt < 5000 && stateActiveInt==1000){
            mPieChart.addPieSlice(new PieModel("High", Integer.parseInt(activeCopy), Color.parseColor("#007afe")));
            mPieChart.addPieSlice(new PieModel("Medium", Integer.parseInt(recoveredCopy), Color.parseColor("#F6404F")));
            mPieChart.addPieSlice(new PieModel("Low", Integer.parseInt(deceasedCopy), Color.parseColor("#08a045")));

            mPieChart.startAnimation();
            covid_status.setText("Covid-19 cases are medium");
        }
        else{
            mPieChart.addPieSlice(new PieModel("High", Integer.parseInt(activeCopy), Color.parseColor("#08a045")));
            mPieChart.addPieSlice(new PieModel("Medium", Integer.parseInt(recoveredCopy), Color.parseColor("#007afe")));
            mPieChart.addPieSlice(new PieModel("Low", Integer.parseInt(deceasedCopy), Color.parseColor("#F6404F")));

            mPieChart.startAnimation();
            covid_status.setText("Covid-19 cases are low");
        }

        int stateDeceasedInt = Integer.parseInt(stateDeceased);
        stateDeceased = NumberFormat.getInstance().format(stateDeceasedInt);

        int stateRecoveredInt = Integer.parseInt(stateRecovery);
        stateRecovery = NumberFormat.getInstance().format(stateRecoveredInt);

        //assert stateActive != null;


        MainActivity object = new MainActivity();
        String formatDate = object.formatDate(stateLastUpdate, 0);
        perStateConfirmed.setText(stateConfirmed);
        perStateActive.setText(stateActive);
        perStateDeceased.setText(stateDeceased);
        //perStateUpdate.setText(formatDate);
        perStateNewConfirmed.setText("+" + stateNewConfirmed);
        perStateNewRecovered.setText("+" + stateNewRecovered);
        perStateNewDeceased.setText("+" + stateNewDeceased);
        perStateRecovered.setText(stateRecovery);
        perstateName.setText("District data of "+stateName);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == android.R.id.home)
            finish();
        return super.onOptionsItemSelected(item);
    }

    public void openDistrictData(View view){
        Intent intent = new Intent(this, DistrictwiseDataActivity.class);
        intent.putExtra(STATE_NAME, stateName);
        startActivity(intent);
    }

    public void openPrecaution(View view) {
        startActivity(new Intent(this, precuation.class));
    }
}
