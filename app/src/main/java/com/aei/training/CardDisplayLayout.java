package com.aei.training;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.content.res.ResourcesCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;

/**
 * Created by Null on 2/27/2018.
 */

public class CardDisplayLayout implements NavigationView.OnNavigationItemSelectedListener {
    private DrawerLayout drawerLayout;
    private Menu menu;
    private NavigationView navigationView;
    private LinearLayout linearLayout;
    private LinearLayout.LayoutParams params;
    private DrawerLayout.LayoutParams dParams;
    private ScrollView scrollView;
    private static AppCompatActivity appCompatActivity;
    private Intent intent;

    public CardDisplayLayout(AppCompatActivity appCompatActivity, Intent intent){
        this.intent = intent;
        this.appCompatActivity =appCompatActivity;


        drawerLayout = new DrawerLayout(this.appCompatActivity);


        dParams = new DrawerLayout.LayoutParams( DrawerLayout.LayoutParams.MATCH_PARENT ,  DrawerLayout.LayoutParams.MATCH_PARENT);
        dParams.gravity= Gravity.START;

        navigationView = new NavigationView(this.appCompatActivity);
        navigationView.setLayoutParams(dParams);
        navigationView.setBackgroundColor(Color.WHITE);
        navigationView.setFitsSystemWindows(true);
        menu = navigationView.getMenu();

        menu.add("View Profile");
        menu.add("Edit Profile");
        menu.add("Line Information");
        menu.add("Boards");
        menu.add("Log Out");
        menu.add("Delete Account");



        navigationView.invalidate();

        navigationView.setNavigationItemSelectedListener(this);

        scrollView = new ScrollView(this.appCompatActivity);

        params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        params.setMargins(0,0,0,0);

        linearLayout = new LinearLayout(this.appCompatActivity);
        linearLayout.setLayoutParams(params);
        linearLayout.setOrientation(LinearLayout.VERTICAL);
        scrollView.addView(linearLayout);
        drawerLayout.addView(scrollView);
        drawerLayout.addView(navigationView);


        this.appCompatActivity.setContentView(drawerLayout);




    }

    public void createCardTextView(String text, int lineColor, boolean clickable,final String id){

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

            textView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    // text gets passed through to the new activity so the new activity can choose
                    // what to display
                    intent.putExtra("ID", id);
                    CardDisplayLayout.appCompatActivity.startActivity(intent);

                }
            });
        }

        linearLayout.addView(textView);
    }


    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        if(item.equals(menu.getItem(0))){

        }
        if(item.equals(menu.getItem(1))){

        }
        if(item.equals(menu.getItem(2))){

            Intent it = new Intent(this.appCompatActivity,LineSelectActivity.class);
            this.appCompatActivity.startActivity(it);
        }
        if(item.equals(menu.getItem(3))){

        }
        if(item.equals(menu.getItem(4))){

        }
        if(item.equals(menu.getItem(5))){

        }

        return false;
    }
}
