package com.example.funcionapo;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import android.widget.TextView;
import android.widget.ImageView;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.LinearLayoutManager;
import java.util.ArrayList;
import java.util.List;
import androidx.appcompat.widget.Toolbar;

public class campanha extends AppCompatActivity {

    private static final int CREATE_CAMPAIGN_REQUEST = 1;
    private List<Campaign> campaigns;
    private LinearLayout campanhasContainer;
    private TextView contadorCampanhas;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_campanha);
        // Configura a Toolbar
        Toolbar toolbar = findViewById(R.id.a);
        setSupportActionBar(toolbar);

        // Inicializar a lista de campanhas
        campaigns = new ArrayList<>();

        // Inicializar views
        campanhasContainer = findViewById(R.id.campanhas_container);
        contadorCampanhas = findViewById(R.id.contador_campanhas);

        Button btnNovaCampanha = findViewById(R.id.btn_campanha);
        btnNovaCampanha.setOnClickListener(v -> {
            Intent intent = new Intent(campanha.this, criacao_campanha.class);
            startActivityForResult(intent, CREATE_CAMPAIGN_REQUEST);
        });

        updateCampanhasCounter();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == CREATE_CAMPAIGN_REQUEST && resultCode == RESULT_OK && data != null) {
            // Criar nova campanha com os dados recebidos
            Campaign newCampanha = new Campaign(
                    data.getStringExtra("nome"),
                    data.getStringExtra("descricao"),
                    data.getStringExtra("dataCriacao"),
                    data.getIntExtra("numeroAgentes", 0),
                    data.getBooleanExtra("escudoMestre", false)
            );

            // Adicionar à lista de campanhas
            campaigns.add(newCampanha);

            // Adicionar card da nova campanha
            addCampanhaCard(newCampanha);

            // Atualizar contador
            updateCampanhasCounter();
        }
    }

    private void addCampanhaCard(Campaign campaign) {
        // Inflar o layout do card
        View cardView = getLayoutInflater().inflate(R.layout.campanha_card, campanhasContainer, false);

        // Configurar os elementos do card
        ImageView imagemCampanha = cardView.findViewById(R.id.imagem_campanha);
        TextView nomeCampanha = cardView.findViewById(R.id.nome_campanha);
        TextView dataCriacao = cardView.findViewById(R.id.data_criacao);
        TextView numeroAgentes = cardView.findViewById(R.id.numero_agentes);
        TextView dmText = cardView.findViewById(R.id.dm_text);
        Button verCampanha = cardView.findViewById(R.id.ver_campanha);

        // Setar os dados da campanha
        nomeCampanha.setText(campaign.getNome());
        dataCriacao.setText(campaign.getDataCriacao());
        numeroAgentes.setText(String.valueOf(campaign.getNumeroAgentes()) + " agentes");
        dmText.setVisibility(campaign.isEscudoMestre() ? View.VISIBLE : View.GONE);

        // Configurar o clique no botão Ver Campanha
//        verCampanha.setOnClickListener(v -> {
//            // TODO: Implementar a navegação para a tela de detalhes da campanha
//            Intent intent = new Intent(campanha.this, DetalhesCampanhaActivity.class);
//            intent.putExtra("campanha_id", campaign.getId());
//            startActivity(intent);
//        });

        // Adicionar o card ao container
        campanhasContainer.addView(cardView);
    }

    private void updateCampanhasCounter() {
        String texto = "Campanhas: " + campaigns.size() + "/6";
        contadorCampanhas.setText(texto);
    }

    public static class Campaign {
        private String nome;
        private String descricao;
        private String dataCriacao;
        private int numeroAgentes;
        private boolean escudoMestre;
        private int id;
        private static int nextId = 1;

        public Campaign(String nome, String descricao, String dataCriacao, int numeroAgentes, boolean escudoMestre) {
            this.id = nextId++;
            this.nome = nome;
            this.descricao = descricao;
            this.dataCriacao = dataCriacao;
            this.numeroAgentes = numeroAgentes;
            this.escudoMestre = escudoMestre;
        }

        // Getters
        public String getNome() { return nome; }
        public String getDescricao() { return descricao; }
        public String getDataCriacao() { return dataCriacao; }
        public int getNumeroAgentes() { return numeroAgentes; }
        public boolean isEscudoMestre() { return escudoMestre; }
        public int getId() { return id; }
    }
}