package com.aei.training;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.UserProfileChangeRequest;

import java.io.InputStream;


public class EditDisplayInfo extends AppCompatActivity {
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
        setContentView(R.layout.activity_edit_account);
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
