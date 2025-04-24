package com.juan.movilfinal;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import com.google.android.material.navigation.NavigationView;

public class MenuActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    private DrawerLayout drawerLayout;
    private LinearLayout perfilLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        // Drawer
        drawerLayout = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        // Menú hamburguesa
        findViewById(R.id.menu_icon).setOnClickListener(view -> {
            drawerLayout.openDrawer(GravityCompat.START);
        });

        // Manejar clic en el perfil
        perfilLayout = findViewById(R.id.perfil_layout);
        perfilLayout.setOnClickListener(view -> mostrarMenuPerfil(view));
    }

    private void mostrarMenuPerfil(View anchor) {
        PopupMenu popup = new PopupMenu(this, anchor);
        popup.getMenuInflater().inflate(R.menu.menu_perfil, popup.getMenu());

        // Obtener el nombre desde SharedPreferences
        SharedPreferences prefs = getSharedPreferences("usuario", MODE_PRIVATE);
        String nombre = prefs.getString("nombre", "Usuario");
        popup.getMenu().findItem(R.id.menu_nombre_usuario).setTitle("Bienvenido\n" + nombre);

        popup.setOnMenuItemClickListener(item -> {
            if (item.getItemId() == R.id.menu_cerrar_sesion) {
                // Limpiar sesión y volver al login
                prefs.edit().clear().apply();
                //Intent intent = new Intent(MenuActivity.this, PantallaPrincipal.class);
              //  intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
               // startActivity(intent);
                return true;
            }
            return false;
        });

        popup.show();
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        // Menú lateral
        int id = item.getItemId();

        if (id == R.id.nav_buscar) {
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
