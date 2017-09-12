package br.com.andersonaltissimo.frasedodia;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    private Button btnFrase;
    private TextView txtfrase;

    private String[] frase = {
        "teste",
        "teste1",
        "teste2",
        "teste3",
        "teste4",
        "teste5"
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnFrase = (Button) findViewById(R.id.btnNovaFrase);
        txtfrase = (TextView) findViewById(R.id.lblFraseDia);

        btnFrase.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Random r = new Random();
                txtfrase.setText(frase[r.nextInt(frase.length)]);
            }
        });

    }
}
