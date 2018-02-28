package com.aei.training;


import android.graphics.PorterDuff;
import android.graphics.PorterDuffColorFilter;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.support.v4.content.ContextCompat;
import android.support.v4.content.res.ResourcesCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private LinearLayout linearLayout;
    private LinearLayout.LayoutParams params;
    private ScrollView scrollView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initLayout();

        //Testing create view function
        createTextView("Metro Blue Line",0xff004dac);
        createTextView("Metro Red Line",0xffee3a43);
        createTextView("Metro Green Line",0xff2eab00);
        createTextView("Metro Gold Line",0xffda7c20);
        createTextView("Metro Purple Line",0xff9561a9);
        createTextView("Metro Expo Line",0xff0177a5);

        setContentView(scrollView);

    }
    //init the layout
    private void initLayout(){
        scrollView = new ScrollView(this);

        params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        params.setMargins(0,0,0,0);

        linearLayout = new LinearLayout(this);
        linearLayout.setLayoutParams(params);
        linearLayout.setOrientation(LinearLayout.VERTICAL);
        scrollView.addView(linearLayout);
    }

    //Create a card
    private void createTextView(String text, int lineColor){

        Drawable drawable = ResourcesCompat.getDrawable(getResources(), R.drawable.square, null);
        drawable.setColorFilter(lineColor, PorterDuff.Mode.MULTIPLY);
        TextView textView = new TextView(this);
        textView.setLayoutParams(params);
        textView.setText(text);
        textView.setPadding(20,30,0,30);
        textView.setBackgroundResource(R.drawable.card_shadow);
        textView.setTypeface(Typeface.create("sans-serif-medium",Typeface.NORMAL));
        textView.setCompoundDrawablesWithIntrinsicBounds(null, null, drawable, null);
        textView.setTextColor(0xff4f4f4f);
        textView.setTextSize(30);
        linearLayout.addView(textView);
    }

}
