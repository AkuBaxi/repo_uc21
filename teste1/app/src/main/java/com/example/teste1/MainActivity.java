package com.example.teste1;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Exibir mensagem de agradecimento
        TextView mensagemTexto = findViewById(R.id.mensagem_texto);
        if (mensagemTexto != null) {
            mensagemTexto.setText("Agradecemos por ter você como nossa coordenadora, Idilvania. \n\nVocê sempre foi uma fonte de inspiração para todos nós, com sua dedicação, compaixão e liderança exemplar. \n\nEste é seu último ano conosco, mas suas contribuições estarão eternamente gravadas em nossos corações. \n\nSentiremos muita falta do seu apoio, carinho e atenção. \n\n\nFeliz aniversário!");
        }

        // Navegar para a tela de agradecimentos
        Button verAgradecimentosButton = findViewById(R.id.ver_agradecimentos_button);
        if (verAgradecimentosButton != null) {
            verAgradecimentosButton.setOnClickListener(v -> {
                startActivity(new Intent(this, agradecimentos.class));
            });
        }

        // Navegar para a tela de despedida
        Button verDespedidaButton = findViewById(R.id.ver_despedida_button);
        if (verDespedidaButton != null) {
            verDespedidaButton.setOnClickListener(v -> {
                startActivity(new Intent(this, despedida.class));
            });
        }
    }
}