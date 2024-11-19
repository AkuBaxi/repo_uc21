package com.example.funcionapo;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class login_main extends AppCompatActivity {
    private EditText edtEmail;
    private EditText edtPassword;
    private Button btnLogin;
    private TextView txtRegister;
    private UserManager userManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_main);

        userManager = new UserManager(this);
        initializeViews();
        setupClickListeners();
    }

    private void initializeViews() {
        edtEmail = findViewById(R.id.edt_email);
        edtPassword = findViewById(R.id.edt_password);
        btnLogin = findViewById(R.id.btn_login);
        txtRegister = findViewById(R.id.txt_register);
    }

    private void setupClickListeners() {
        btnLogin.setOnClickListener(v -> validateAndLogin());
        txtRegister.setOnClickListener(v -> {
            startActivity(new Intent(login_main.this, cadatro.class));
            finish();
        });
    }

    private void validateAndLogin() {
        String email = edtEmail.getText().toString().trim();
        String password = edtPassword.getText().toString().trim();

        if (email.isEmpty()) {
            edtEmail.setError("Email é obrigatório");
            edtEmail.requestFocus();
            return;
        }

        if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            edtEmail.setError("Email inválido");
            edtEmail.requestFocus();
            return;
        }

        if (password.isEmpty()) {
            edtPassword.setError("Senha é obrigatória");
            edtPassword.requestFocus();
            return;
        }

        if (password.length() < 6) {
            edtPassword.setError("Senha deve ter no mínimo 6 caracteres");
            edtPassword.requestFocus();
            return;
        }

        realizarLogin(email, password);
    }

    private void realizarLogin(String email, String password) {
        if (userManager.verifyLogin(email, password)) {
            Toast.makeText(this, "Login realizado com sucesso!", Toast.LENGTH_SHORT).show();
            startActivity(new Intent(login_main.this, tela_principal.class));
            finish();
        } else {
            Toast.makeText(this, "Email ou senha incorretos!", Toast.LENGTH_SHORT).show();
        }
    }
}