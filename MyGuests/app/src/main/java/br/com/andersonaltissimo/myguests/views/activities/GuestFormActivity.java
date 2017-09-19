package br.com.andersonaltissimo.myguests.views.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

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

        if (!this.validateSave()){
            return;
        }


        Guest guest = new Guest();
        guest.setName(this.vh.nameGuest.getText().toString());

        if (this.vh.notConfirmed.isChecked()){
            guest.setConfirmed(GuestConstants.CONFIRMATION.NOT_CONFIRMED);
        } else if (this.vh.present.isChecked()){
            guest.setConfirmed(GuestConstants.CONFIRMATION.PRESENT);
        } else {
            guest.setConfirmed(GuestConstants.CONFIRMATION.ABSENT);
        }

        if (this.guestBusiness.Insert(guest)){
            Toast.makeText(this, R.string.guest_saved, Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, R.string.guest_error_save, Toast.LENGTH_SHORT).show();
        }

        finish();
    }

    private boolean validateSave() {
        if (this.vh.nameGuest.getText().toString().trim().equals("")){
            this.vh.nameGuest.setError(getString(R.string.name_obrigatorio));
            return false;
        }

        return true;
    }

    private static class ViewHolder {
        EditText nameGuest;
        RadioButton notConfirmed;
        RadioButton present;
        RadioButton absent;
        Button btnSave;
    }
}
