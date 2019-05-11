package br.com.andersonaltissimo.conversormoedas;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {


    private ViewHolder viewHolder = new ViewHolder();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        this.viewHolder.edRealValue = findViewById(R.id.ed_real);
        this.viewHolder.edDolarValue = findViewById(R.id.ed_dolar);
        this.viewHolder.edEuroValue = findViewById(R.id.ed_euro);

        this.viewHolder.btnCalculate = findViewById(R.id.btn_calculate);
        this.viewHolder.btnCalculate.setOnClickListener(this);

        this.clearValues();

    }

    private void clearValues() {
        this.viewHolder.edDolarValue.setText("");
        this.viewHolder.edEuroValue.setText("");
    }

    @Override
    public void onClick(View v) {

        if (v.getId() == R.id.btn_calculate) {
            String value = this.viewHolder.edRealValue.getText().toString();

            if (value.equals("")) {
                Toast.makeText(this, getString(R.string.informe_um_valor), Toast.LENGTH_LONG).show();
            } else {
                Double doubleReal = Double.valueOf(value);

                this.viewHolder.edDolarValue.setText(String.format("%.2f", (doubleReal/4)));
                this.viewHolder.edEuroValue.setText(String.format("%.2f", (doubleReal/5)));
            }
        }

    }


    private static class ViewHolder {
        EditText edRealValue;
        EditText edDolarValue;
        EditText edEuroValue;

        Button btnCalculate;
    }
}
