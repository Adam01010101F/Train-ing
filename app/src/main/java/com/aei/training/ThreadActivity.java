package com.aei.training;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.UserProfileChangeRequest;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreSettings;

import java.util.HashMap;

import io.grpc.Server;

public class ThreadActivity extends AppCompatActivity{
    private FirebaseAuth fAuth;
    private FirebaseUser fUser;
    private FirebaseFirestore fStore;
    private Button genThread;
    private Button queryButton;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView();
//        genThread = findViewById(R.id.genThread);
//        String line = getIntent().getStringExtra("ID");
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
        queryButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                queryPosts();
            }
        });
    }

    private void generateTempThread(){
        HashMap<String, Object> tThread = new HashMap<>();
        tThread.put("Title", "This is a test");
        tThread.put("Text", "So I need to create a thread, so here is the thread body.");
        String tester = "Test";
        fStore.collection("Thread").document(String.valueOf(new String("Test")
                .hashCode())).set(tThread);
        DocumentReference tDoc = new 
        fStore.collection("Post").document(String.valueOf
    }
    /**
     * TODO: Retrieve all posts from server.
     * Find a way to order them(Server Timestamp as posts are created?)
     */
    private void queryPosts(){

    }
}
