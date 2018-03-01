package com.aei.training;


import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.aei.training.MetroAPI.MetroRetriever;
import com.aei.training.Objects.Stop;
import com.aei.training.Objects.TrainLine;
import com.aei.training.Objects.StopList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class lineStopsActivity extends AppCompatActivity {
    private CardDisplayLayout cardDisplayLayout;
    private MetroRetriever metroRetriever;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        cardDisplayLayout = new CardDisplayLayout(this, null);
        metroRetriever= new MetroRetriever();
        handleStops();

    }

    private void handleStops(){
        String line = getIntent().getStringExtra("ID");
        Callback callback = handleCallback();
        if(line != null){
            metroRetriever.getStops(line,callback);


        } else{
            cardDisplayLayout.createCardTextView("Nothing To Display",0xffffffff,false,null);
        }

    }

    private Callback handleCallback(){
        Callback callback = new Callback<StopList>(){
            @Override
            public void onResponse(Call<StopList> call, Response<StopList> response) {
                //Log.d("stop",response.body().getStops().get(0).getDisplay_name());

                for(Stop stop: response.body().getStops()){
                    cardDisplayLayout.createCardTextView(stop.getDisplay_name(),0xffffffff,true,stop.getId());
                }

            }

            @Override
            public void onFailure(Call<StopList> call, Throwable t) {
                cardDisplayLayout.createCardTextView("Nothing To Display",0xffffffff,false,null);
            }
        };
        return callback;
    }

}

