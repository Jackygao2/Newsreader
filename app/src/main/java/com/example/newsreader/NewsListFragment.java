package com.example.newsreader;

import android.app.AlertDialog;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;


public class NewsListFragment extends Fragment implements AdapterView.OnItemClickListener {
    private FragmentManager fManager;
    private ArrayList<NewsItem> newsItems;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fg_newlist, container, false);
        ListView listNews = view.findViewById(R.id.listNews);
        MyAdapter myAdapter = new MyAdapter(newsItems, getActivity());
        listNews.setAdapter(myAdapter);
        listNews.setOnItemClickListener(this);
        return view;
    }


    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle(newsItems.get(position).getNew_title())
                .setNegativeButton("Delete from favourite", (dialog, buttonId) -> {
                    SharedPreferences sp = getActivity().getSharedPreferences("newsTitle", Context.MODE_PRIVATE);
                    SharedPreferences.Editor editor = sp.edit();
                    editor.remove(newsItems.get(position).getNew_title());
                    editor.apply();
                })
                .setPositiveButton("Go to new content", (dialog, buttonId) -> {
                    FragmentTransaction fTransaction = fManager.beginTransaction();
                    NewsContentFragment ncFragment = new NewsContentFragment();
                    Bundle bd = new Bundle();
                    bd.putString("content", newsItems.get(position).getNew_content());
                    ncFragment.setArguments(bd);
                    TextView title = getActivity().findViewById(R.id.txt_title);
                    title.setText(newsItems.get(position).getNew_content());
                    fTransaction.replace(R.id.fl_content, ncFragment);
                    fTransaction.commit();
                })
                .create()
                .show();




    }

    public void setflManager(FragmentManager fManager) {
        this.fManager = fManager;
    }

    public void setNewsItems(ArrayList<NewsItem> newsItems) {
        this.newsItems = newsItems;
    }
}
