package com.mordor.app;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.mordor.app.databinding.ActivityResultBinding;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

public class RankingResultActivity extends AppCompatActivity {
    private ActivityResultBinding activityResultBinding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityResultBinding = ActivityResultBinding.inflate(getLayoutInflater());
        View view = activityResultBinding.getRoot();
        setContentView(view);
        googleSearchResult = getIntent().getStringArrayListExtra("list");
        HttpPresenter httpPresenter = new HttpPresenter(this);
        httpPresenter.getDomainList();
    }
    List<String> googleSearchResult;
    private void addView(String name, int rank){
        View layoutItem = LayoutInflater.from(this).inflate(R.layout.ranking_domain_layout, activityResultBinding.itemList, false);
        TextView domainName = layoutItem.findViewById(R.id.domainName);
        TextView rankingText = layoutItem.findViewById(R.id.rankingText);
        domainName.setText(name);
        rankingText.setText(String.valueOf(rank));
        activityResultBinding.itemList.addView(layoutItem);
    }
    public void onGetDomain(List<Domain> domainList){
        for(Domain domain : domainList){
            for(String domainName : googleSearchResult){
                if(domainName.contains(domain.getName())){
                    domain.setRank(googleSearchResult.indexOf(domainName));
                }
            }
            addView(domain.getName(), domain.getRank());
        }
    }
}