package com.aei.training;


import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;


public class lineStopsActivity extends AppCompatActivity {
    private CardDisplayLayout cardDisplayLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        cardDisplayLayout = new CardDisplayLayout(this, null);
        handleStops();

    }

    private void handleStops(){
        String line = getIntent().getStringExtra("TEXT");
        if(line != null){
            if(line.equals("Metro Blue Line")){
                cardDisplayLayout.createCardTextView("testStop",0xffffffff,false);
            } else{
                cardDisplayLayout.createCardTextView("No Stops To Display",0xffffffff,false);
            }

        } else{
            cardDisplayLayout.createCardTextView("No Stops To Display",0xffffffff,false);
        }

    }


}

