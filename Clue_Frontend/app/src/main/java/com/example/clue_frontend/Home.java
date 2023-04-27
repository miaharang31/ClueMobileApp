package com.example.clue_frontend;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.clue_frontend.Lobbies.HostLobby;
import com.example.clue_frontend.Lobbies.JoinLobby;
import com.example.clue_frontend.NavBar.HomeFragment;
import com.example.clue_frontend.NavBar.LogoutFragment;
import com.example.clue_frontend.NavBar.ProfileFragment;
import com.example.clue_frontend.NavBar.RulesFragment;
import com.example.clue_frontend.NavBar.SettingsFragment;
import com.google.android.material.navigation.NavigationView;

public class Home extends AppCompatActivity {

    private DrawerLayout drawerLayout;
//    NavigationView navigationView;
    ActionBarDrawerToggle drawerToggle;
    Button join;
    Button host;

//    @Override
//    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
//        if(drawerToggle.onOptionsItemSelected(item)){
//            return true;
//        }
//        return super.onOptionsItemSelected(item);
//    }

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        join = (Button) findViewById(R.id.join);
        host = (Button) findViewById(R.id.host);

        Toolbar toolbar = findViewById(R.id.toolbar);
 //       setSupportActionBar(toolbar);

        drawerLayout = findViewById(R.id.drawer_layout);
//        navigationView = findViewById(R.id.nav_view);
//        navigationView.setNavigationItemSelectedListener(this);

      //  drawerToggle = new ActionBarDrawerToggle(this, drawerLayout, R.string.open, R.string.close);
      //  drawerLayout.addDrawerListener(drawerToggle);
        //drawerToggle.syncState();

        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new HomeFragment()).commit();
//            navigationView.setCheckedItem(R.id.nav_home);
        }
        join.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent intent = new Intent(Home.this, JoinLobby.class);
                startActivity(intent);
            }
        });
        host.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent intent = new Intent(Home.this, HostLobby.class);
                startActivity(intent);
            }
        });


    }
//            @Override
//            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
//                if(item.getItemId() == R.id.nav_home){
//                    getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new HomeFragment()).commit();
//                }
//                if(item.getItemId() == R.id.nav_profile){
//                    getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new ProfileFragment()).commit();
//                }
//                if(item.getItemId() == R.id.nav_settings){
//                    getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new SettingsFragment()).commit();
//                }
//                if(item.getItemId() == R.id.nav_howToPlay){
//                    getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new RulesFragment()).commit();
//                }
//                if(item.getItemId() == R.id.nav_logout){
//                    getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new LogoutFragment()).commit();
//
//                    //Toast.makeText(this, "logout", Toast.LENGTH_SHORT).show();
//                }
//                drawerLayout.closeDrawer(GravityCompat.START);
//                return true;
//            }; //}

    @Override
    public void onBackPressed() {
        if(drawerLayout.isDrawerOpen(GravityCompat.START)){
            drawerLayout.closeDrawer(GravityCompat.START);
        }else{
            super.onBackPressed();
        }
    }
}