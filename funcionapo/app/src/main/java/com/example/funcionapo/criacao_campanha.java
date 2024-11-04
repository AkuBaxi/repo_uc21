package com.example.funcionapo;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.text.Spannable;
import android.text.SpannableStringBuilder;
import android.text.style.StyleSpan;
import android.text.style.UnderlineSpan;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import android.view.View;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

public class criacao_campanha extends AppCompatActivity {
    private EditText editTextName;
    private EditText editTextDescription;
    private Button btnDesligado;
    private Button btnLigado;
    private Button btnBold;
    private Button btnItalic;
    private Button btnUnderline;
    private Button btnCancelar;
    private Button btnCriar;
    private boolean escudoMestreAtivo = false;
    private SpannableStringBuilder spannableDescription;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_criacao_campanha);

        initializeViews();
        setupListeners();
    }

    private void initializeViews() {
        editTextName = findViewById(R.id.editTextName);
        editTextDescription = findViewById(R.id.editTextDescription);
        btnDesligado = findViewById(R.id.btnDesligado);
        btnLigado = findViewById(R.id.btnLigado);
        btnBold = findViewById(R.id.btnBold);
        btnItalic = findViewById(R.id.btnItalic);
        btnUnderline = findViewById(R.id.btnUnderline);
        btnCancelar = findViewById(R.id.btnCancelar);
        btnCriar = findViewById(R.id.btnCriar);

        spannableDescription = new SpannableStringBuilder();
        editTextDescription.setText(spannableDescription);
    }

    private void setupListeners() {
        btnDesligado.setOnClickListener(v -> toggleEscudoMestre(false));
        btnLigado.setOnClickListener(v -> toggleEscudoMestre(true));

        btnBold.setOnClickListener(v -> formatText(Typeface.BOLD));
        btnItalic.setOnClickListener(v -> formatText(Typeface.ITALIC));
        btnUnderline.setOnClickListener(v -> applyUnderline());

        btnCancelar.setOnClickListener(v -> onCancelarClicked());
        btnCriar.setOnClickListener(v -> onCriarClicked());
    }

    private void toggleEscudoMestre(boolean ativo) {
        escudoMestreAtivo = ativo;
        btnDesligado.setSelected(!ativo);
        btnLigado.setSelected(ativo);

        btnLigado.setAlpha(ativo ? 1.0f : 0.5f);
        btnDesligado.setAlpha(ativo ? 0.5f : 1.0f);
    }

    private void formatText(int style) {
        int start = editTextDescription.getSelectionStart();
        int end = editTextDescription.getSelectionEnd();

        if (start < end) {
            SpannableStringBuilder spannable = new SpannableStringBuilder(editTextDescription.getText());

            StyleSpan[] spans = spannable.getSpans(start, end, StyleSpan.class);
            for (StyleSpan span : spans) {
                if (span.getStyle() == style) {
                    spannable.removeSpan(span);
                }
            }

            spannable.setSpan(new StyleSpan(style), start, end, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
            editTextDescription.setText(spannable);
            editTextDescription.setSelection(start, end);
        }
    }

    private void applyUnderline() {
        int start = editTextDescription.getSelectionStart();
        int end = editTextDescription.getSelectionEnd();

        if (start < end) {
            SpannableStringBuilder spannable = new SpannableStringBuilder(editTextDescription.getText());

            UnderlineSpan[] spans = spannable.getSpans(start, end, UnderlineSpan.class);
            for (UnderlineSpan span : spans) {
                spannable.removeSpan(span);
            }

            spannable.setSpan(new UnderlineSpan(), start, end, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
            editTextDescription.setText(spannable);
            editTextDescription.setSelection(start, end);
        }
    }

    private void onCancelarClicked() {
        new AlertDialog.Builder(this)
                .setTitle("Cancelar criação")
                .setMessage("Tem certeza que deseja cancelar? Todas as alterações serão perdidas.")
                .setPositiveButton("Sim", (dialog, which) -> finish())
                .setNegativeButton("Não", null)
                .show();
    }

    private void onCriarClicked() {
        String nome = editTextName.getText().toString().trim();
        String descricao = editTextDescription.getText().toString().trim();

        if (nome.isEmpty()) {
            editTextName.setError("Nome é obrigatório");
            return;
        }

        Campaign campaign = new Campaign();
        campaign.setNome(nome);
        campaign.setDescricao(descricao);
        campaign.setEscudoMestre(escudoMestreAtivo);
        campaign.setDataCriacao(getCurrentDate());
        campaign.setNumeroAgentes(0);

        // Aqui você deve implementar a lógica para salvar a campanha (banco de dados, etc.)
        salvarCampanha(campaign);

        // Retorna para a tela anterior com os dados da nova campanha
        Intent intent = new Intent();
        intent.putExtra("nome", campaign.getNome());
        intent.putExtra("descricao", campaign.getDescricao());
        intent.putExtra("dataCriacao", campaign.getDataCriacao());
        intent.putExtra("numeroAgentes", campaign.getNumeroAgentes());
        intent.putExtra("escudoMestre", campaign.isEscudoMestre());

        setResult(RESULT_OK, intent);
        finish();

        Toast.makeText(this, "Campanha criada com sucesso!", Toast.LENGTH_SHORT).show();
    }

    private String getCurrentDate() {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault());
        return sdf.format(new Date());
    }

    private void salvarCampanha(Campaign campaign) {
        // TODO: Implementar a lógica de salvamento no banco de dados
        // Você precisará criar um banco de dados SQLite ou usar outra forma de persistência
        // Por exemplo:
        // DatabaseHelper db = new DatabaseHelper(this);
        // db.addCampanha(campaign);
    }

    private static class Campaign {
        private String nome;
        private String descricao;
        private boolean escudoMestre;
        private String dataCriacao;
        private int numeroAgentes;

        public String getNome() { return nome; }
        public void setNome(String nome) { this.nome = nome; }

        public String getDescricao() { return descricao; }
        public void setDescricao(String descricao) { this.descricao = descricao; }

        public boolean isEscudoMestre() { return escudoMestre; }
        public void setEscudoMestre(boolean escudoMestre) { this.escudoMestre = escudoMestre; }

        public String getDataCriacao() { return dataCriacao; }
        public void setDataCriacao(String dataCriacao) { this.dataCriacao = dataCriacao; }

        public int getNumeroAgentes() { return numeroAgentes; }
        public void setNumeroAgentes(int numeroAgentes) { this.numeroAgentes = numeroAgentes; }
    }
}