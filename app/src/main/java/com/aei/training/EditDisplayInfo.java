package com.aei.training;

import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.UserProfileChangeRequest;

import java.io.InputStream;


public class EditDisplayInfo extends AppCompatActivity{
    Toolbar tb;
    private DrawerLayout drawerLayout;
    private FirebaseUser user;
    private UserProfileChangeRequest profile;
    private Button upPic;
    private Button uName;
    private Button updateDisplayInfo;
    private EditText nameField;
    private String userName;
    final int PICK_PHOTO_FOR_AVATAR = 1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dname_drawer);
        drawerLayout =findViewById(R.id.drawer_layout);

        tb = (Toolbar) findViewById(R.id.my_toolbar);
        tb.setLayoutParams(new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT, 168));

        tb.setPopupTheme(R.style.AppTheme);
        tb.setBackgroundColor(this.getResources().getColor(R.color.colorPrimary));
        tb.setTitle("This is the title");
        tb.setVisibility(View.VISIBLE);
        tb.setLogo(R.drawable.ic_menu);
        setSupportActionBar(tb);

        tb.setClickable(true);
        tb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                drawerLayout.openDrawer(GravityCompat.START);

            }
        });
        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(
                new NavigationView.OnNavigationItemSelectedListener() {
                    @Override
                    public boolean onNavigationItemSelected(MenuItem item) {
                        // set item as selected to persist highlight
                        item.setChecked(false);


                        // Add code here to update the UI based on the item selected
                        // For example, swap UI fragments here

                                if(item.getTitle().equals("View Profile")){
                                    Intent it = new Intent(EditDisplayInfo.this,ViewAccountActivity.class);
                                    startActivity(it);
                                }
                                if(item.getTitle().equals("Change Email")){
                                    Intent it = new Intent(EditDisplayInfo.this,ChangeEmail.class);
                                    startActivity(it);
                                }
                                if(item.getTitle().equals("Change Password")){
                                    Intent it = new Intent(EditDisplayInfo.this,ChangePassword.class);
                                    startActivity(it);
                                }
                                if(item.getTitle().equals("Change Display Name")){
                                    Intent it = new Intent(EditDisplayInfo.this,EditDisplayInfo.class);
                                    startActivity(it);
                                }
                                if(item.getTitle().equals("Line Information")){

                                    Intent it = new Intent(EditDisplayInfo.this,LineSelectActivity.class);
                                    startActivity(it);
                                }
                                if(item.getTitle().equals("Boards")){

                                }
                                if(item.getTitle().equals("Log Out")){
                                    FirebaseAuth.getInstance().signOut();
                                    Intent intent = new Intent(EditDisplayInfo.this, MainActivity.class);
                                    startActivity(intent);
                                }
                                if(item.getTitle().equals("Delete Account")){
                                    //creates AlertDialog for deletion of user account when deletion button clicked
                                    final  AlertDialog.Builder alertDialog = new AlertDialog.Builder(EditDisplayInfo.this);
                                    alertDialog.setTitle("DELETE ACCOUNT");
                                    alertDialog.setMessage("Enter FireBase Email to delete FireBase account");

                                    final EditText input = new EditText(EditDisplayInfo.this);
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

                                                Toast toast = Toast.makeText(getApplicationContext(), "Deleting your account", Toast.LENGTH_LONG);
                                                toast.show();
                                                try{

                                                    user.delete()
                                                            .addOnCompleteListener(new OnCompleteListener<Void>() {
                                                                @Override
                                                                public void onComplete(@NonNull Task<Void> task) {
                                                                    if (task.isSuccessful()) {
                                                                        Log.d("TAG ", "User account deleted.");
                                                                        Toast toast = Toast.makeText(getApplicationContext(), "Account was deleted", Toast.LENGTH_LONG);
                                                                        toast.show();
                                                                        Intent intent = new Intent(EditDisplayInfo.this, MainActivity.class);
                                                                        startActivity(intent);
                                                                    } else {
                                                                        Log.d("TAG ", "User account not deleted.");
                                                                    }
                                                                }
                                                            });

                                                }catch (Exception e){
                                                    toast = Toast.makeText(getApplicationContext(), "Account was not found", Toast.LENGTH_LONG);
                                                    toast.show();
                                                }
                                            } else {

                                                Toast toast = Toast.makeText(getApplicationContext(), "FireBase email was not entered, please click delete again", Toast.LENGTH_LONG);
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

                        return true;
                    }
                });
//        upPic = (Button) findViewById();
//        uName = (Button) findViewById(R.id.);
        updateDisplayInfo = (Button) findViewById(R.id.submitDisplayInfo);
        nameField = (EditText) findViewById(R.id.uNameField);

        userName = "";

        user = FirebaseAuth.getInstance().getCurrentUser();

//        upPic.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                pickImage();
//            }
//        });

        updateDisplayInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!nameField.getText().toString().isEmpty()) {
                    updateProfile();
                } else{
                    popToast("Enter a display name.");
                }
            }
        });

    }


    private void pickImage(){
        Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
        intent.setType("image/*");
        startActivityForResult(intent, PICK_PHOTO_FOR_AVATAR);
    }

//    TODO:: SET DEFAULT USERNAME ON ACCOUNT CREATION or chatboard use,"USER:" + RAND_NUM, default photo if possible.
    private void updateProfile(){
        profile = new UserProfileChangeRequest.Builder()
                .setDisplayName(nameField.getText().toString())
                .build();


        user.updateProfile(profile)
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful()) {
                            user = FirebaseAuth.getInstance().getCurrentUser();
                            popToast("Profile Updated!");
                        } else{
                            popToast("Profile not updated.");
                        }
                    }
                });
    }

    protected void popToast(String msg){
        Toast toast = Toast.makeText(getApplication().getBaseContext(), msg,
                Toast.LENGTH_LONG);
        toast.show();
    }



}
