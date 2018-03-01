package com.aei.training;


import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.aei.training.MetroAPI.MetroRetriever;
import com.aei.training.Objects.Color;
import com.aei.training.Objects.TrainLine;
import com.aei.training.Objects.TrainList;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class MainActivity extends AppCompatActivity {

    private CardDisplayLayout cardDisplayLayout;
    private MetroRetriever metroRetriever;

    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        cardDisplayLayout = new CardDisplayLayout(this, new Intent(MainActivity.this, lineStopsActivity.class));
        metroRetriever= new MetroRetriever();

        handleLines();

    }

    private void handleLines(){
        Callback callback = new Callback<TrainList>(){
            @Override
            public void onResponse(Call<TrainList> call, Response<TrainList>response) {


                for(TrainLine trainLine: response.body().getTrainLine()){
                    handleColor(trainLine);

                }
            }

            @Override
            public void onFailure(Call<TrainList> call, Throwable t) {
                cardDisplayLayout.createCardTextView("Nothing To Display",0xffffffff,false,null);
            }
        };
        metroRetriever.getLine(callback);

    }
    private void handleColor(final TrainLine trainLine){
        Callback callback = new Callback<Color>(){
            @Override
            public void onResponse(Call<Color> call, Response<Color>response) {

                String color = response.body().getBg_color();
                int clr = (int)Long.parseLong(color.replace("#","ff").toUpperCase(),16);
                cardDisplayLayout.createCardTextView(trainLine.getDisplay_name(),clr, true,trainLine.getId());

            }

            @Override
            public void onFailure(Call<Color> call, Throwable t) {
                cardDisplayLayout.createCardTextView("Nothing To Display",0xffffffff,false,null);
            }
        };
        metroRetriever.getColor(trainLine.getId(), callback);
    }

}

