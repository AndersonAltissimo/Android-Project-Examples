package br.com.andersonaltissimo.idadecachorro;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private Button btnCalcular;
    private EditText edIdade;
    private TextView lblResultado;

    int $age=24;
    Double _height = 123.5;





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnCalcular = (Button) findViewById(R.id.btnDescobrirIdade);
        edIdade = (EditText) findViewById(R.id.edIdade);
        lblResultado = (TextView) findViewById(R.id.lblInforme);


        btnCalcular.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String idade = edIdade.getText().toString();

                if (idade.isEmpty()){
                    lblResultado.setText("Nenhum número difitado!");
                } else {
                    int idadeConv = Integer.parseInt(idade) * 7;
                    lblResultado.setText("A Idade do Cachorro em Anos Humanos é de :" + idadeConv + " anos !");
                }
            }
        });
    }
}
