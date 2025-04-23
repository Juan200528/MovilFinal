package com.juan.movilfinal;

import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import com.google.android.material.navigation.NavigationView;

public class MenuActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    private DrawerLayout drawerLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        // Configurar Navigation Drawer
        drawerLayout = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        // Configurar clic en el ícono de menú (hamburguesa)
        findViewById(R.id.menu_icon).setOnClickListener(view -> {
            drawerLayout.openDrawer(GravityCompat.START);
        });
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        // Manejar selección de items del menú
        int id = item.getItemId();

        if (id == R.id.nav_actividades) {
            showToast("Actividades solidarias");
        } else if (id == R.id.nav_buscar) {
            showToast("Buscar y Filtrar Actividades");
        } else if (id == R.id.nav_liga) {
            showToast("Liga de Actividades");
        } else if (id == R.id.nav_presenciales) {
            showToast("Actividades Presenciales");
        } else if (id == R.id.nav_notificaciones) {
            showToast("Configurar Notificaciones");
        } else if (id == R.id.nav_redes) {
            showToast("Publicar en Redes Sociales");
        } else if (id == R.id.nav_asistencia) {
            showToast("Gestionar Asistencia");
        }

        drawerLayout.closeDrawer(GravityCompat.START);
        return true;
    }

    private void showToast(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onBackPressed() {
        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }
}