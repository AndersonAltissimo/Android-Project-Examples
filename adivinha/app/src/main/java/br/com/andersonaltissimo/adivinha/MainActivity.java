package br.com.andersonaltissimo.adivinha;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    private Button button;
    private TextView txtResultado;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        button = (Button) findViewById(R.id.btnJogar);
        txtResultado = (TextView) findViewById(R.id.lblResultado);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                txtResultado.setText("Novo Valor");

                Random random = new Random();
                int randomNumber = random.nextInt(10);
                txtResultado.setText("Número Gerado :" + randomNumber);
            }
        });
    }
}
