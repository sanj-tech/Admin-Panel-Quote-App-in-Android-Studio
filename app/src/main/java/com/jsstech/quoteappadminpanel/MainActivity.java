package com.jsstech.quoteappadminpanel;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;

import com.google.android.material.navigation.NavigationView;
import com.jsstech.quoteappadminpanel.Insert.AboutInsert;
import com.jsstech.quoteappadminpanel.Insert.ContactInsert;
import com.jsstech.quoteappadminpanel.Insert.DeveloperInfo;
import com.jsstech.quoteappadminpanel.Insert.QuotesInsert;
import com.jsstech.quoteappadminpanel.View.InquiryView;


public class MainActivity extends AppCompatActivity  {
    private Toolbar toolbar;
    DrawerLayout drawerLayout;
    NavigationView navigationView;
    ActionBarDrawerToggle toggle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        toolbar=findViewById(R.id.toolbar);
        drawerLayout=findViewById(R.id.drawer);
        navigationView=findViewById(R.id.navView);

        setSupportActionBar(toolbar);
        toggle = new ActionBarDrawerToggle(this,drawerLayout,toolbar,R.string.open,R.string.close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();

        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                switch (item.getItemId()){
                    case R.id.feed:
                Intent intent = new Intent(MainActivity.this,ContactInsert.class);
                startActivity(intent);
                break;

            case R.id.share:
                Intent intent1 = new Intent(MainActivity.this,AboutInsert.class);
                startActivity(intent1);
                break;
                    case R.id.rate:
                Intent intent2 = new Intent(MainActivity.this,QuotesInsert.class);
                startActivity(intent2);
                break;
            case R.id.developer:
                Intent intent3 = new Intent(MainActivity.this,DeveloperInfo.class);
                startActivity(intent3);
                break;

                }


                return true;
            }
        });


    }



    public void contactUS(View view) {
        startActivity(new Intent(MainActivity.this, ContactInsert.class));
    }

    public void openAboutUs(View view) {
        startActivity(new Intent(MainActivity.this, AboutInsert.class));
    }

    public void openQuotes(View view) {
        startActivity(new Intent(MainActivity.this, QuotesInsert.class));
    }

    public void openImageInsertActivity(View view) {
        startActivity(new Intent(MainActivity.this, ImageInsert.class));
    }

    public void openViewInquiryActivity(View view) {
        startActivity(new Intent(MainActivity.this, InquiryView.class));
    }
}
