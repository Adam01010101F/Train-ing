package com.aei.training;

import android.content.Intent;
import android.graphics.PorterDuff;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.support.v4.content.res.ResourcesCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;

/**
 * Created by Null on 2/27/2018.
 */

public class CardDisplayLayout {
    private LinearLayout linearLayout;
    private LinearLayout.LayoutParams params;
    private ScrollView scrollView;
    private static AppCompatActivity appCompatActivity;
    private Intent intent;

    public CardDisplayLayout(AppCompatActivity appCompatActivity, Intent intent){
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
}
