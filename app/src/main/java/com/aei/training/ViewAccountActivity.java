package com.aei.training;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.aei.training.CardDisplayLayout;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

/**
 * Created by Null on 4/25/2018.
 */

public class ViewAccountActivity  extends AppCompatActivity {
    private CardDisplayLayout cardDisplayLayout;
    private FirebaseUser user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        user = FirebaseAuth.getInstance().getCurrentUser();

        cardDisplayLayout = new CardDisplayLayout(this, null);

        cardDisplayLayout.createCardTextView("Display Name: "+ user.getDisplayName(),0xffffffff,false,null);
        cardDisplayLayout.createCardTextView("Email: "+ user.getEmail(),0xffffffff,false,null);


    }
}