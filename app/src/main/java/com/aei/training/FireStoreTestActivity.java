package com.aei.training;

/**
 * Created by Eric on 5/1/2018.
 */



import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;


import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.UserProfileChangeRequest;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;


import java.util.HashMap;
import java.util.Map;

public class FireStoreTestActivity extends AppCompatActivity {


    private FirebaseFirestore FireStore;
    private FirebaseAuth mFirebaseAuth;
    private UserProfileChangeRequest profile;
    private FirebaseUser user;
    private EditText profileName;
    private Button submitName;
    private Button FireStoreS;

    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.fires_store);
        profileName = findViewById(R.id.ProfileName);
        submitName = findViewById(R.id.SubmitName);
        FireStoreS = findViewById(R.id.SubmitToFireStore);

        submitName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FireStore =  FirebaseFirestore.getInstance();
                mFirebaseAuth = FirebaseAuth.getInstance();
                user = FirebaseAuth.getInstance().getCurrentUser();

                profile = new UserProfileChangeRequest.Builder()
                        .setDisplayName(profileName.getText().toString())
                        .build();
            }
        });

        FireStoreS.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){
                user = FirebaseAuth.getInstance().getCurrentUser();
                String Username = user.getDisplayName();
                Map<String, Object> user = new HashMap<>();
                user.put("Username",Username);
                FireStore =  FirebaseFirestore.getInstance();

//               Add a new document with a generated ID
                FireStore.collection("Users")
                        .add(user)
                        .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                            @Override
                            public void onSuccess(DocumentReference documentReference) {
                              Log.d("Success","DocumentSnapshot added with ID: " + documentReference.getId());
                            }
                        })
                        .addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                Log.d("Error", "Error");

                            }
                        });
            }
        });


    }


}


