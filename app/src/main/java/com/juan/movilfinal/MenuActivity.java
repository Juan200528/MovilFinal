package com.juan.movilfinal;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.PopupMenu;
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

        // Configuración del Drawer Layout
        drawerLayout = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        // Configuración del ícono de menú hamburguesa
        findViewById(R.id.menu_icon).setOnClickListener(view -> {
            if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
                drawerLayout.closeDrawer(GravityCompat.START);
            } else {
                drawerLayout.openDrawer(GravityCompat.START);
            }
        });

        // Configuración del clic en el área de perfil
        perfilLayout = findViewById(R.id.perfil_layout);
        perfilLayout.setOnClickListener(this::mostrarMenuPerfil);
    }

    private void mostrarMenuPerfil(View anchor) {
        PopupMenu popup = new PopupMenu(this, anchor);
        popup.getMenuInflater().inflate(R.menu.menu_perfil, popup.getMenu());

        // Obtener datos del usuario
        SharedPreferences prefs = getSharedPreferences("usuario", MODE_PRIVATE);
        String nombre = prefs.getString("nombre", "Usuario");
        popup.getMenu().findItem(R.id.menu_nombre_usuario).setTitle("Bienvenido\n" + nombre);

        popup.setOnMenuItemClickListener(item -> {
            if (item.getItemId() == R.id.menu_cerrar_sesion) {
                // Cerrar sesión y volver al login
                prefs.edit().clear().apply();
               // Intent intent = new Intent(MenuActivity.this, PantallaPrincipal.class);
                //intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
               // startActivity(intent);
                finish();
                return true;
            }
            return false;
        });

        popup.show();
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();

        // Navegación del menú lateral
        if (id == R.id.nav_buscar) {
         //   iniciarActividad(BuscarActividades.class);
        } else if (id == R.id.nav_liga) {
            //iniciarActividad(LigaActividades.class);
        } else if (id == R.id.nav_presenciales) {
            //iniciarActividad(ActividadesPresenciales.class);
        } else if (id == R.id.nav_notificaciones) {
            //iniciarActividad(ConfigNotificaciones.class);
        } else if (id == R.id.nav_redes) {
         //   iniciarActividad(RedesSociales.class);
        } else if (id == R.id.nav_asistencia) {
           // iniciarActividad(GestionAsistencia.class);
        }

        drawerLayout.closeDrawer(GravityCompat.START);
        return true;
    }

    private void iniciarActividad(Class<?> cls) {
        startActivity(new Intent(this, cls));
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