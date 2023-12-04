package com.example.newsreader;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class MainActivity2 extends AppCompatActivity {

    private TextView title;
    private FragmentManager fManager = null;
    private long exitTime = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.nav_second_activity);
        fManager = getFragmentManager();
        bindViews();

        ArrayList<NewsItem> newsItems = new ArrayList<>();
        newsItems.add(new NewsItem("Cbc news", "Canada Economy"));
        newsItems.add(new NewsItem("Bbc news", "Us politics"));
        newsItems.add(new NewsItem("Cnn news", "British population"));
        SharedPreferences sp = getSharedPreferences("newsTitle", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();
        editor.putString(newsItems.get(0).getNew_title(), newsItems.get(0).getNew_content());
        editor.putString(newsItems.get(1).getNew_title(), newsItems.get(1).getNew_content());
        editor.putString(newsItems.get(1).getNew_title(), newsItems.get(1).getNew_content());
        editor.apply();

        NewsListFragment nlFragment = new NewsListFragment();
        nlFragment.setflManager(fManager);
        nlFragment.setNewsItems(newsItems);
        FragmentTransaction ft = fManager.beginTransaction();
        ft.replace(R.id.fl_content, nlFragment);
        ft.commit();
    }


    private void bindViews() {
        title = findViewById(R.id.txt_title);
    }

    @Override
    public void onBackPressed() {
        if (fManager.getBackStackEntryCount() == 0) {
            if ((System.currentTimeMillis() - exitTime) > 2000) {
                Toast.makeText(getApplicationContext(), "enter again to exit",
                        Toast.LENGTH_SHORT).show();
                exitTime = System.currentTimeMillis();
            } else {
                super.onBackPressed();
            }
        } else {
            fManager.popBackStack();
            title.setText("News Title");
        }
    }
}
