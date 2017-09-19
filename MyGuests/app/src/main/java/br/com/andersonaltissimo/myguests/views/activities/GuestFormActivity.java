package br.com.andersonaltissimo.myguests.views.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;

import br.com.andersonaltissimo.myguests.R;
import br.com.andersonaltissimo.myguests.business.GuestBusiness;
import br.com.andersonaltissimo.myguests.constants.GuestConstants;
import br.com.andersonaltissimo.myguests.entities.Guest;

public class GuestFormActivity extends AppCompatActivity implements View.OnClickListener {

    private ViewHolder vh = new ViewHolder();
    private GuestBusiness guestBusiness;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_guest_form);

        guestBusiness = new GuestBusiness(this);

        this.vh.nameGuest = (EditText) findViewById(R.id.ed_name_guest);
        this.vh.notConfirmed = (RadioButton) findViewById(R.id.rg_not_confirmed);
        this.vh.present = (RadioButton) findViewById(R.id.rg_present);
        this.vh.absent = (RadioButton) findViewById(R.id.rg_absent);
        this.vh.btnSave = (Button) findViewById(R.id.btn_save);

        this.setListeners();
    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.btn_save){
            this.handleSave();
        }
    }

    private void setListeners() {
        this.vh.btnSave.setOnClickListener(this);
    }

    private void handleSave() {
        Guest guest = new Guest();
        guest.setName(this.vh.nameGuest.getText().toString());

        if (this.vh.notConfirmed.isChecked()){
            guest.setConfirmed(GuestConstants.CONFIRMATION.NOT_CONFIRMED);
        } else if (this.vh.present.isChecked()){
            guest.setConfirmed(GuestConstants.CONFIRMATION.PRESENT);
        } else {
            guest.setConfirmed(GuestConstants.CONFIRMATION.ABSENT);
        }

        this.guestBusiness.Insert(guest);   
    }

    private static class ViewHolder {
        EditText nameGuest;
        RadioButton notConfirmed;
        RadioButton present;
        RadioButton absent;
        Button btnSave;
    }
}
