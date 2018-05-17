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
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreSettings;

public class CreateThreads extends AppCompatActivity {
    private FirebaseAuth fAuth;
    private FirebaseUser fUser;
    private FirebaseFirestore fStore;



    private LinearLayout linearLayout;
   private LinearLayout.LayoutParams params;
    private static AppCompatActivity appCompatActivity;
    private Intent intent;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.intent = intent;
        this.appCompatActivity =appCompatActivity;

//shouldn't need the commented outstuff
  //      drawerLayout = new DrawerLayout(this.appCompatActivity);
  //      dParams = new DrawerLayout.LayoutParams( DrawerLayout.LayoutParams.MATCH_PARENT ,  DrawerLayout.LayoutParams.MATCH_PARENT);
    //    dParams.gravity= Gravity.START;
     //   navigationView = new NavigationView(this.appCompatActivity);
       // navigationView.setLayoutParams(dParams);
       // navigationView.setBackgroundColor(Color.WHITE);
     //   navigationView.setFitsSystemWindows(true);
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
    public void createThreads(String text, int lineColor, boolean clickable,final String id){

        Drawable drawable = ResourcesCompat.getDrawable(this.appCompatActivity.getResources(), R.drawable.square, null);
        drawable.setColorFilter(lineColor, PorterDuff.Mode.MULTIPLY);
        TextView textView = new TextView(this.appCompatActivity);
        textView.setLayoutParams(params);
        textView.setText(text);
        textView.setPadding(20,30,0,30);
        textView.setBackgroundResource(R.drawable.card_shadow);
        textView.setTypeface(Typeface.create("sans-serif-medium",Typeface.NORMAL));
        textView.setCompoundDrawablesWithIntrinsicBounds(null, null, drawable, null);
        textView.setTextColor(0xff4f4f4f);
        textView.setTextSize(25);
        textView.setClickable(clickable);
        if(clickable){
        //this will do something else,
            textView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    // text gets passed through to the new activity so the new activity can choose
                    // what to display
                    intent.putExtra("ID", id);
                    CreateThreads.appCompatActivity.startActivity(intent);

                }
            });
        }

        linearLayout.addView(textView);
    }
}
