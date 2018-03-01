package com.aei.training;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.aei.training.MetroAPI.MetroRetriever;
import com.aei.training.Objects.Estimate;

import com.aei.training.Objects.EstimateList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Null on 2/28/2018.
 */

public class StopInfoActivity extends AppCompatActivity {
    private CardDisplayLayout cardDisplayLayout;
    private MetroRetriever metroRetriever;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        cardDisplayLayout = new CardDisplayLayout(this, null);
        metroRetriever= new MetroRetriever();
        handleStopInfo();
    }
    private void handleStopInfo(){
        String line = getIntent().getStringExtra("ID");
        Callback callback = handleCallback();
        if(line != null){
            Log.d("stop",line.substring(0,3));
            Log.d("stop",line.substring(3,line.length()));
            metroRetriever.getEstimate(line.substring(0,3),line.substring(3,line.length()),callback);


        } else{
            cardDisplayLayout.createCardTextView("Nothing To Display",0xffffffff,false,null);
        }

    }

    private Callback handleCallback(){
        Callback callback = new Callback<EstimateList>(){
            @Override
            public void onResponse(Call<EstimateList> call, Response<EstimateList> response) {
                if(response.body().getEstimates().size()==0){
                    cardDisplayLayout.createCardTextView("Nothing To Display",0xffffffff,false,null);
                }
                for(Estimate estimate: response.body().getEstimates()){
                    int time = estimate.getSeconds();
                    int mins =(time / 60);
                    int seconds = time-(mins*60);
                    cardDisplayLayout.createCardTextView("Train Arriving ~: "+Integer.toString(mins)+"m "+Integer.toString(seconds)+"s",0xffffffff,false,null);
                }

            }

            @Override
            public void onFailure(Call<EstimateList> call, Throwable t) {
                cardDisplayLayout.createCardTextView("Nothing To Display",0xffffffff,false,null);
            }
        };
        return callback;
    }

}
