package com.example.teste1;

import android.os.Bundle;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class despedida extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_despedida);

        // Exibir a mensagem de despedida
        TextView despedidaTexto = findViewById(R.id.despedida_texto);
        if (despedidaTexto != null) {
            despedidaTexto.setText(
                    "# Mensagem de Despedida para Nossa Querida Coordenadora, Idilvania\n\n" +
                            "Querida Idilvania,\n\n" +
                            "Chegou o momento de nos despedirmos de você, nossa amada coordenadora, após anos de dedicação e liderança exemplar nesta escola. Este é seu último ano conosco, e não há palavras suficientes para expressar nossa profunda gratidão e carinho por tudo o que você fez por nós.\n\n" +
                            "Desde o primeiro dia em que chegou a esta instituição, sua presença iluminou os corredores e salas de aula. Sua paixão pelo ensino, seu compromisso inabalável com o desenvolvimento de cada aluno e sua compaixão inesgotável marcaram a vida de todos nós, professores e estudantes.\n\n" +
                            "Lembraremos com saudade de sua disposição em resolver qualquer problema, sua atenção aos detalhes e sua capacidade de manter a equipe unida, mesmo nos momentos mais desafiadores. Sua liderança nos inspirou a sermos melhores profissionais e a colocarmos o bem-estar dos estudantes em primeiro lugar.\n\n" +
                            "Seu legado será eternamente lembrado nas vidas que você tocou, nas carreiras que você ajudou a impulsionar e nas memórias que você ajudou a criar. Sua dedicação e amor por esta escola e por todos nós serão inesquecíveis.\n\n" +
                            "À medida que você se aposenta e inicia uma nova jornada, desejamos que você encontre a paz, a felicidade e o merecido descanso. Você merece desfrutar plenamente desta fase da sua vida, livre das responsabilidades do cargo, mas carregando consigo a gratidão e o carinho de todos nós.\n\n" +
                            "Idilvania, você será imensamente saudada. Sua marca indelével ficará para sempre em nossos corações. Que Deus a abençoe abundantemente nesta nova etapa de sua vida.\n\n" +
                            "Com amor e gratidão,\n" +
                            "Sua família escolar"
            );
        }
    }
}