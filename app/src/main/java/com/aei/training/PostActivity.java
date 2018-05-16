package com.aei.training;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreSettings;

import java.util.HashMap;

public class PostActivity extends AppCompatActivity{
    private FirebaseAuth fAuth;
    private FirebaseUser fUser;
    private FirebaseFirestore fStore;
    private Button genThread;
    private Button queryButton;
    private Button postButton;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_thread_posts);
//        String threadId= getIntent().getStringExtra("ID");
        String threadId = "QDQenIbvuihORLoawCbE";
//        genThread = findViewById(R.id.genThread);
        postButton = findViewById(R.id.AddPost);
//        queryButton = findViewById(R);

        instaFire();

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
        postButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View V){
                showPostBox();
            }
        });
        getQuery(threadId);
    }

    private void generateTempThread(){
        HashMap<String, Object> tThread = new HashMap<>();
        tThread.put("Title", "This is a test");
        tThread.put("Text", "So I need to create a thread, so here is the thread body.");
        String tester = "Test";
        fStore.collection("Thread").document(String.valueOf(new String("Test")
                .hashCode())).set(tThread);
//        DocumentReference tDoc = new
//        fStore.collection("Post").document(String.valueOf
    }

    /**
     * TODO: Retrieve all posts from server.
     * Find a way to order them(Server Timestamp as posts are created?)
     */
    private void queryPosts(){

    }

    public void instaFire(){
        fAuth = FirebaseAuth.getInstance();
        fUser = fAuth.getCurrentUser();
        fStore =  FirebaseFirestore.getInstance();

        FirebaseFirestoreSettings settings = new FirebaseFirestoreSettings.Builder()
                .setPersistenceEnabled(true)
                .build();
        fStore.setFirestoreSettings(settings);
    }

    public void getQuery(String id){

    }

    public void showPostBox(){
        final  AlertDialog.Builder alertDialog = new AlertDialog.Builder(PostActivity.this);
        alertDialog.setTitle("Add Post");
        alertDialog.setMessage("");

        final EditText input = new EditText(PostActivity.this);
        LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.MATCH_PARENT);
        input.setLayoutParams(lp);
        alertDialog.setView(input);
        alertDialog.setPositiveButton("Post",new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int whichButton) {

                String userIn = input.getText().toString();

//                if(userIn.equals(EmailFire)) {

                    Toast toast = Toast.makeText(getApplicationContext(), "Posting...", Toast.LENGTH_SHORT);
                    toast.show();
                    try{
//                        fStore.collection("Post").document(threadId).set

                    }catch (Exception e){
                        toast = Toast.makeText(getApplicationContext(), "Account was not found", Toast.LENGTH_LONG);
                        toast.show();
                    }
//                } else {
//
//                    Toast toast = Toast.makeText(getApplicationContext(), "FireBase email was not entered, please click delete again", Toast.LENGTH_LONG);
//                    toast.show();
//
//                }

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
}
