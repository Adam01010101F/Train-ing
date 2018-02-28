package com.aei.training;


import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;


public class MainActivity extends AppCompatActivity {

    private CardDisplayLayout cardDisplayLayout;

    protected void onCreate(Bundle savedInstanceState) {


        super.onCreate(savedInstanceState);

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

