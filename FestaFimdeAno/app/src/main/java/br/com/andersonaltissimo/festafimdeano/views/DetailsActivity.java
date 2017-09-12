package br.com.andersonaltissimo.festafimdeano.views;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.CheckBox;

import br.com.andersonaltissimo.festafimdeano.R;
import br.com.andersonaltissimo.festafimdeano.constants.FimdeAnoContants;
import br.com.andersonaltissimo.festafimdeano.util.SecurityPreferences;

public class DetailsActivity extends AppCompatActivity implements View.OnClickListener {

    private CheckBox chkParticipate;
    private SecurityPreferences securityPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);

        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setIcon(R.mipmap.ic_launcher);

        securityPreferences = new SecurityPreferences(this);

        chkParticipate = (CheckBox) findViewById(R.id.chkParticipate);
        chkParticipate.setOnClickListener(this);

        this.LoadDataFromActivity();

    }

    private void LoadDataFromActivity() {
        Bundle bundle = getIntent().getExtras();

        if (bundle != null) {
            String presence = bundle.getString(FimdeAnoContants.PRESENCE);

            if (presence.equals(FimdeAnoContants.PRESENCE_WILL_GO)){
                chkParticipate.setChecked(true);
            } else {
                chkParticipate.setChecked(false);
            }
        }
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();

        if (id == R.id.chkParticipate){
            if (this.chkParticipate.isChecked()){
                securityPreferences.storedString(FimdeAnoContants.PRESENCE, FimdeAnoContants.PRESENCE_WILL_GO);
            } else{
                securityPreferences.storedString(FimdeAnoContants.PRESENCE, FimdeAnoContants.PRESENCE_WONT_GO);
            }
        }
    }
}
