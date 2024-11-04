package com.example.scar; // Substitua pelo seu pacote

import android.os.Bundle;
import android.view.MenuItem;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import com.google.android.material.navigation.NavigationView;

public class MainActivity extends AppCompatActivity {
    private DrawerLayout drawerLayout;
    private ImageButton menuButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Inicializar componentes
        drawerLayout = findViewById(R.id.drawer_layout);
        menuButton = findViewById(R.id.menuButton);
        NavigationView navView = findViewById(R.id.nav_view);

        // Configurar clique do botão de menu
        menuButton.setOnClickListener(view -> {
            if (!drawerLayout.isDrawerOpen(GravityCompat.END)) {
                drawerLayout.openDrawer(GravityCompat.END);
            }
        });

        // Configurar listener do menu
        navView.setNavigationItemSelectedListener(menuItem -> {
            int id = menuItem.getItemId();

            if (id == R.id.nav_personagens) {
                Toast.makeText(MainActivity.this, "Personagens", Toast.LENGTH_SHORT).show();
            } else if (id == R.id.nav_campanhas) {
                Toast.makeText(MainActivity.this, "Campanhas", Toast.LENGTH_SHORT).show();
            } else if (id == R.id.nav_livro_regras) {
                Toast.makeText(MainActivity.this, "Livro de Regras", Toast.LENGTH_SHORT).show();
            } else if (id == R.id.nav_informacoes) {
                Toast.makeText(MainActivity.this, "Informações", Toast.LENGTH_SHORT).show();
            } else if (id == R.id.nav_configuracoes) {
                Toast.makeText(MainActivity.this, "Configurações", Toast.LENGTH_SHORT).show();
            }

            drawerLayout.closeDrawer(GravityCompat.END);
            return true;
        });
    }

    @Override
    public void onBackPressed() {
        if (drawerLayout.isDrawerOpen(GravityCompat.END)) {
            drawerLayout.closeDrawer(GravityCompat.END);
        } else {
            super.onBackPressed();
        }
    }
}