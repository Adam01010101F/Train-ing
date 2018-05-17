package com.aei.training;

import android.os.Bundle;
import android.support.v4.view.GravityCompat;
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
import android.support.v7.widget.Toolbar;
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

public class CreateThreads implements NavigationView.OnNavigationItemSelectedListener {
    private DrawerLayout drawerLayout;
    private Menu menu;
    private NavigationView navigationView;
    private Toolbar tb;
    private FirebaseAuth fAuth;
    private FirebaseUser fUser;
    private FirebaseFirestore fStore;
    private LinearLayout linearLayout;
    private LinearLayout.LayoutParams params;
    private DrawerLayout.LayoutParams dParams;
    private static AppCompatActivity appCompatActivity;
    private Intent intent;
    private ScrollView scrollView;



    public CreateThreads(AppCompatActivity appCompatActivity, Intent intent) {

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
        menu.add("Change Email");
        menu.add("Change Password");
        menu.add("Change Display Name");
        menu.add("Line Information");
        menu.add("Boards");
        menu.add("Log Out");
        menu.add("Delete Account");



        navigationView.invalidate();

        navigationView.setNavigationItemSelectedListener(this);



        scrollView = new ScrollView(this.appCompatActivity);
        params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        params.setMargins(0,0,0,0);

        tb = new Toolbar(this.appCompatActivity);
        tb.setLayoutParams(new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT, 168));

        tb.setPopupTheme(R.style.AppTheme);
        tb.setBackgroundColor(this.appCompatActivity.getResources().getColor(R.color.colorPrimary));
        tb.setTitle("This is the title");
        tb.setVisibility(View.VISIBLE);
        tb.setLogo(R.drawable.ic_menu);
        this.appCompatActivity.setSupportActionBar(tb);

        linearLayout = new LinearLayout(this.appCompatActivity);
        linearLayout.setLayoutParams(params);
        linearLayout.setOrientation(LinearLayout.VERTICAL);
        linearLayout.addView(tb,0);

        scrollView.addView(linearLayout);
        drawerLayout.addView(scrollView);
        drawerLayout.addView(navigationView);


        this.appCompatActivity.setContentView(drawerLayout);
        tb.setClickable(true);
        tb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                drawerLayout.openDrawer(GravityCompat.START);

            }
        });

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
            final String textBuild = text +i;
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
                        intent.putExtra("ID", textBuild);
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

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        if(item.equals(menu.getItem(0))){
            Intent it = new Intent(this.appCompatActivity,ViewAccountActivity.class);
            this.appCompatActivity.startActivity(it);
        }
        if(item.equals(menu.getItem(1))){
            Intent it = new Intent(this.appCompatActivity,ChangeEmail.class);
            this.appCompatActivity.startActivity(it);
        }
        if(item.equals(menu.getItem(2))){
            Intent it = new Intent(this.appCompatActivity,ChangePassword.class);
            this.appCompatActivity.startActivity(it);
        }
        if(item.equals(menu.getItem(3))){
            Intent it = new Intent(this.appCompatActivity,EditDisplayInfo.class);
            this.appCompatActivity.startActivity(it);
        }
        if(item.equals(menu.getItem(4))){

            Intent it = new Intent(this.appCompatActivity,LineSelectActivity.class);
            this.appCompatActivity.startActivity(it);
        }
        if(item.equals(menu.getItem(5))){
            Intent intent = new Intent(this.appCompatActivity, ThreadStartActivity.class);
            this.appCompatActivity.startActivity(intent);
        }
        if(item.equals(menu.getItem(6))){
            FirebaseAuth.getInstance().signOut();
            Intent intent = new Intent(this.appCompatActivity, MainActivity.class);
            this.appCompatActivity.startActivity(intent);
        }
        if(item.equals(menu.getItem(7))){
            //creates AlertDialog for deletion of user account when deletion button clicked
            final  AlertDialog.Builder alertDialog = new AlertDialog.Builder(this.appCompatActivity);
            alertDialog.setTitle("DELETE ACCOUNT");
            alertDialog.setMessage("Enter FireBase Email to delete FireBase account");

            final EditText input = new EditText(this.appCompatActivity);
            LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.MATCH_PARENT,
                    LinearLayout.LayoutParams.MATCH_PARENT);
            input.setLayoutParams(lp);
            alertDialog.setView(input);
            alertDialog.setPositiveButton("ENTER",new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int whichButton) {

                    String userIn = input.getText().toString();
                    userIn =  userIn.trim();
                    FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
                    String EmailFire="";
                    try{
                        EmailFire = user.getEmail();
                    }catch(Exception e){

                    }

                    if(userIn.equals(EmailFire)) {

                        Toast toast = Toast.makeText(appCompatActivity.getApplicationContext(), "Deleting your account", Toast.LENGTH_LONG);
                        toast.show();
                        try{

                            user.delete()
                                    .addOnCompleteListener(new OnCompleteListener<Void>() {
                                        @Override
                                        public void onComplete(@NonNull Task<Void> task) {
                                            if (task.isSuccessful()) {
                                                Log.d("TAG ", "User account deleted.");
                                                Toast toast = Toast.makeText(appCompatActivity.getApplicationContext(), "Account was deleted", Toast.LENGTH_LONG);
                                                toast.show();
                                                Intent intent = new Intent(appCompatActivity, MainActivity.class);
                                                appCompatActivity.startActivity(intent);
                                            } else {
                                                Log.d("TAG ", "User account not deleted.");
                                            }
                                        }
                                    });

                        }catch (Exception e){
                            toast = Toast.makeText(appCompatActivity.getApplicationContext(), "Account was not found", Toast.LENGTH_LONG);
                            toast.show();
                        }
                    } else {

                        Toast toast = Toast.makeText(appCompatActivity.getApplicationContext(), "FireBase email was not entered, please click delete again", Toast.LENGTH_LONG);
                        toast.show();

                    }

                }
            });
            alertDialog.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int whichButton) {
                    //pass
                }
            });
            AlertDialog b = alertDialog.create();
            b.show();
        }

        return false;
    }
}
