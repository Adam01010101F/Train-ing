package com.aei.training;


import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import com.aei.training.MetroAPI.MetroRetriever;
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
                    cardDisplayLayout.createCardTextView(trainLine.getDisplay_name(),0xff004dac, true,trainLine.getId());
                }
            }

            @Override
            public void onFailure(Call<TrainList> call, Throwable t) {
                cardDisplayLayout.createCardTextView("Nothing To Display",0xffffffff,false,null);
            }
        };
        metroRetriever.getLine(callback);

    }

}

