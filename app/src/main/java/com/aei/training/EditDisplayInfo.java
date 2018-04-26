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
    private InputStream photoUri;
    private String userName;
    final int PICK_PHOTO_FOR_AVATAR = 1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView();
//        upPic = (Button) findViewById();
        uName = (Button) findViewById();
        updateDisplayInfo = (Button) findViewById();
        nameField = (EditText) findViewById();

        photoUri = null;
        userName = "";

        user = FirebaseAuth.getInstance().getCurrentUser();

        upPic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pickImage();
            }
        });
        updateDisplayInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updateProfile();
            }
        });

    }


    private void pickImage(){
        Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
        intent.setType("image/*");
        startActivityForResult(intent, PICK_PHOTO_FOR_AVATAR);
    }

//    TODO:: SET DEFAULT USERNAME ON ACCOUNT CREATION ,"USER:" + RAND_NUM, default photo if possible.
    private void updateProfile(){
        profile = new UserProfileChangeRequest.Builder()
                .setDisplayName(userName.isEmpty() ? user.getDisplayName() : userName)
//                .setPhotoUri(photoUri==null ? user.getPhotoUrl() : photoUri)
                .build();


        user.updateProfile(profile)
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful()) {
                            Log.d("SUCCESS", "User profile updated.");
                        }
                    }
                });
        }


}
