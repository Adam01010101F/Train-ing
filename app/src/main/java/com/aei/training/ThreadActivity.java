package com.aei.training;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.UserProfileChangeRequest;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreSettings;

public class ThreadActivity extends AppCompatActivity{
    private FirebaseAuth fAuth;
    private FirebaseUser fUser;
    private FirebaseFirestore fStore;
    private Button genThread;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView();
//        genThread = findViewById(R.id.genThread);
        fAuth = FirebaseAuth.getInstance();
        fUser = fAuth.getCurrentUser();
        fStore =  FirebaseFirestore.getInstance();

        FirebaseFirestoreSettings settings = new FirebaseFirestoreSettings.Builder()
                .setPersistenceEnabled(true)
                .build();
        fStore.setFirestoreSettings(settings);

        //Code for testing
        genThread.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                generateTempThread();
            }
        });
    }

    private void generateTempThread(){
        fStore.collection("Thread").add()
    }
    /** Append to an existing document
     * db.collection("cities").document("BJ")
     *      .set(data, SetOptions.merge());
     */
}
