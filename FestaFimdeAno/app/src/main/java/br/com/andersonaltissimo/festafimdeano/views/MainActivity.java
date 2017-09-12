package br.com.andersonaltissimo.festafimdeano.views;

import android.annotation.TargetApi;
import android.content.Intent;
import android.icu.text.SimpleDateFormat;
import android.icu.util.Calendar;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import br.com.andersonaltissimo.festafimdeano.R;
import br.com.andersonaltissimo.festafimdeano.constants.FimdeAnoContants;
import br.com.andersonaltissimo.festafimdeano.util.SecurityPreferences;

@TargetApi(Build.VERSION_CODES.N)
public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    private TextView txtToday;
    private TextView txtDaysLeft;
    private Button btnConfirm;

    private SecurityPreferences securityPreferences;
    private static final SimpleDateFormat SIMPLE_DATE_FORMAT = new SimpleDateFormat("dd/MM/yyyy");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setIcon(R.mipmap.ic_launcher);

        securityPreferences = new SecurityPreferences(this);

        txtToday = (TextView) findViewById(R.id.txt_today);
        txtDaysLeft = (TextView) findViewById(R.id.txt_days_left);
        btnConfirm = (Button) findViewById(R.id.btnConfirm);

        btnConfirm.setOnClickListener(this);

        txtToday.setText(SIMPLE_DATE_FORMAT.format(Calendar.getInstance().getTime()));

        String daysLeft = String.format("%s %s", String.valueOf(getDaysLeftToEndOfYear()), getString(R.string.dias));
        txtDaysLeft.setText(daysLeft);
    }

    @Override
    protected void onResume() {
        super.onResume();
        this.verifyPresence();
    }

    private int getDaysLeftToEndOfYear(){
        Calendar calendar = Calendar.getInstance();
        int today = calendar.get(Calendar.DAY_OF_YEAR);
        int LastDayofYear = calendar.getActualMaximum(Calendar.DAY_OF_YEAR);

        return LastDayofYear - today;
    }

    private void verifyPresence() {
        String preference = securityPreferences.getStoredString(FimdeAnoContants.PRESENCE);

        if (preference.equals("")){
            btnConfirm.setText(R.string.nao_confirmado);
        } else if (preference.equals(FimdeAnoContants.PRESENCE_WILL_GO)){
            btnConfirm.setText(R.string.sim);
        } else {
            btnConfirm.setText(R.string.nao);
        }
    }

    @Override
    public void onClick(View v) {

        String presence = this.securityPreferences.getStoredString(FimdeAnoContants.PRESENCE);

        int id = v.getId();

        if (id == R.id.btnConfirm) {
            Intent intent = new Intent(this, DetailsActivity.class);
            intent.putExtra(FimdeAnoContants.PRESENCE, presence);
            startActivity(intent);
        }
    }
}
