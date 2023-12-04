package com.example.newsreader;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.FragmentActivity;

import com.google.android.material.navigation.NavigationView;

import java.util.ArrayList;

public class MainActivity extends FragmentActivity implements View.OnClickListener {

    ArrayList<String> rssLinks = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.nav_activity_main);

        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(
                item -> {
                    int id = item.getItemId();
                    if (id == R.id.nav_home) {
                        Toast.makeText(MainActivity.this, "Home clicked",Toast.LENGTH_LONG).show();
                        return true;
                    } else if (id == R.id.nav_gallery) {
                        return true;
                    } else if (id == R.id.nav_slideshow) {
                        return true;
                    }
                    DrawerLayout drawer = findViewById(R.id.drawer_layout);
                    drawer.closeDrawer(GravityCompat.START);
                    return false;
                });

        Button bbcButton = findViewById(R.id.bbcReader);
        Button cbcButton = findViewById(R.id.cbcReader);
        Button myButton = findViewById(R.id.myFavourite);
        bbcButton.setOnClickListener(this);
        cbcButton.setOnClickListener(this);
        myButton.setOnClickListener(this);
        rssLinks.add("http://feeds.bbci.co.uk/news/world/us_and_canada/rss.xml");
        rssLinks.add("https://www.cbc.ca/rss/");
    }

    @Override
    public void onClick(View view) {
        Intent intent = new Intent(getApplicationContext(), RSSScrollActivity.class);

            if (view.getId() == R.id.bbcReader) {
                intent.putExtra("rssLink", rssLinks.get(0));
                startActivity(intent);
            }else if (view.getId() == R.id.cbcReader) {
                intent.putExtra("rssLink", rssLinks.get(1));
                startActivity(intent);
            }else if (view.getId() == R.id.myFavourite) {
                intent = new Intent(getApplicationContext(), MainActivity2.class);
                startActivity(intent);
            }
    }
    }
