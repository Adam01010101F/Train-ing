package com.aei.training;


import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.aei.training.MetroAPI.MetroRetriever;
import com.aei.training.Objects.Estimate;
import com.aei.training.Objects.EstimateList;
import com.aei.training.Objects.TrainLine;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class MainActivity extends AppCompatActivity {

    private CardDisplayLayout cardDisplayLayout;
    private MetroRetriever metroRetriever;

    protected void onCreate(Bundle savedInstanceState) {


        super.onCreate(savedInstanceState);
        metroRetriever= new MetroRetriever();
        Callback callback = new Callback<EstimateList>() {
            @Override
            public void onResponse(Call<EstimateList>call, Response<EstimateList>response) {
                Log.d("CallBack", " response is " + response.body().getEstimates());
            }

            @Override
            public void onFailure(Call<EstimateList>call, Throwable t) {
                Log.d("CallBack", " Throwable is " +t);
            }
        };
       metroRetriever.getEstimate(callback);

        //create a new card layout
        cardDisplayLayout = new CardDisplayLayout(this, new Intent(MainActivity.this, lineStopsActivity.class));

        //Testing create view function
        cardDisplayLayout.createCardTextView("Metro Blue Line",0xff004dac, true);
        cardDisplayLayout.createCardTextView("Metro Red Line",0xffee3a43, true);
        cardDisplayLayout.createCardTextView("Metro Green Line",0xff2eab00, true);
        cardDisplayLayout.createCardTextView("Metro Gold Line",0xffda7c20, true);
        cardDisplayLayout.createCardTextView("Metro Purple Line",0xff9561a9, true);
        cardDisplayLayout.createCardTextView("Metro Expo Line",0xff0177a5, true);
    }

}

