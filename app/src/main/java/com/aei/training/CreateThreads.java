package com.aei.training;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

/**
 * Created by Eric on 5/16/2018.
 */
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.content.res.ResourcesCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreSettings;

import java.util.HashMap;
import java.util.Map;

public class CreateThreads extends AppCompatActivity {
    private FirebaseAuth fAuth;
    private FirebaseUser fUser;
    private FirebaseFirestore fStore;
    private LinearLayout linearLayout;
    private LinearLayout.LayoutParams params;
    private static AppCompatActivity appCompatActivity;
    private Intent intent;
    private ScrollView scrollView;



    public CreateThreads(AppCompatActivity appCompatActivity, Intent intent) {

        this.intent = intent;
        this.appCompatActivity =appCompatActivity;




        scrollView = new ScrollView(this.appCompatActivity);
        params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        params.setMargins(0,0,0,0);

        linearLayout = new LinearLayout(this.appCompatActivity);
        linearLayout.setLayoutParams(params);
        linearLayout.setOrientation(LinearLayout.VERTICAL);

        scrollView.addView(linearLayout);
        this.appCompatActivity.setContentView(scrollView);
        instaFire();
    }


    public void instaFire() {
        fAuth = FirebaseAuth.getInstance();
        fUser = fAuth.getCurrentUser();
        fStore = FirebaseFirestore.getInstance();

        FirebaseFirestoreSettings settings = new FirebaseFirestoreSettings.Builder()
                .setPersistenceEnabled(true)
                .build();
        fStore.setFirestoreSettings(settings);
    }
    public void createThreads(String text, int lineColor, boolean clickable,final String id) {

        for (int i = 1; i <= 5; i++) {
            //need to create blank documents since FireStore automically creates a collection if it does
            // already exist in FireStore
            createThread(i, text);
            Drawable drawable = ResourcesCompat.getDrawable(this.appCompatActivity.getResources(), R.drawable.square, null);
            drawable.setColorFilter(lineColor, PorterDuff.Mode.MULTIPLY);
            TextView textView = new TextView(this.appCompatActivity);
            textView.setLayoutParams(params);
            final String textBuild = text +"i";
            textView.setText(textBuild);
            textView.setPadding(20, 30, 0, 30);
            textView.setBackgroundResource(R.drawable.card_shadow);
            textView.setTypeface(Typeface.create("sans-serif-medium", Typeface.NORMAL));
            textView.setCompoundDrawablesWithIntrinsicBounds(null, null, drawable, null);
            textView.setTextColor(0xff4f4f4f);
            textView.setTextSize(25);
            textView.setClickable(clickable);
            if (clickable) {
                //this will do something else,
                textView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        // text gets passed through to the new activity so the new activity can choose
                        // what to display
                        //pretty sure you can pass a string throught intent, this allow us to know which
                        //thread user selected based on line.

//                        Intent intent = new Intent( CreateThreads.this, PostActivity.class);
//                        Bundle bundle1 = new Bundle();
//                        bundle1.putString("TrainLineThread", textBuild);
//                        intent.putExtras(bundle1);
                        CreateThreads.appCompatActivity.startActivity(intent);



                    }
                });
            }

            linearLayout.addView(textView);
        }
    }
    public void createThread(int i, String text)
    {
        String textThread = text + "" + i;
        Map<String , Object> data = new HashMap();
        DocumentReference newTrainLineThread = fStore.collection(textThread).document();
        newTrainLineThread.set(data);
    }
}
