package com.example.teste1;

import android.os.Bundle;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class agradecimentos extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_agradecimentos);

        // Exibir os agradecimentos
        TextView agradecimentosTexto = findViewById(R.id.agradecimentos_texto);
        if (agradecimentosTexto != null) {
            agradecimentosTexto.setText("Querida Idilvania,\n\nSomos imensamente gratos por tudo o que você fez por esta escola e por nós, sua família escolar. \n\nSua dedicação, empenho e compaixão foram fundamentais para o sucesso de nossos alunos e para a harmonia desta instituição. \n\nAgradecemos por sua liderança inspiradora, por estar sempre disponível para nos orientar e por criar um ambiente de aprendizado tão acolhedor. \n\nVocê será imensamente saudada e suas contribuições jamais serão esquecidas.");
        }
    }
}