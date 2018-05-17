package com.aei.training;

/**
 * Created by Adam on 5/16/2018.
 */
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;

import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;

import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;


import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreSettings;

import java.util.HashMap;
import java.util.Map;

public class PostActivity extends AppCompatActivity {
    Toolbar tb;
    private DrawerLayout drawerLayout;
    private FirebaseAuth fAuth;
    private FirebaseUser fUser;
    private FirebaseFirestore fStore;
    private Button genThread;
    private FloatingActionButton queryButton;
    private FloatingActionButton postButton;
    private String lineName;
    private static final String TAG = "DocSnippets";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.post_drawer);
        drawerLayout = findViewById(R.id.drawer_layout);

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
//        String threadId= getIntent().getStringExtra("ID");
        String threadId = "QDQenIbvuihORLoawCbE";   // Thread ID specifically for testing.
//        genThread = findViewById(R.id.genThread);
        postButton = findViewById(R.id.AddPost);
        queryButton = findViewById(R.id.query);
//        queryButton = findViewById(R);
        //retreives the string Thread passed by the bundle by using the intent
        lineName = getIntent().getStringExtra("ID");
        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(
                new NavigationView.OnNavigationItemSelectedListener() {
                    @Override
                    public boolean onNavigationItemSelected(MenuItem item) {
                        // set item as selected to persist highlight
                        item.setChecked(false);


                        // Add code here to update the UI based on the item selected
                        // For example, swap UI fragments here

                        if (item.getTitle().equals("View Profile")) {
                            Intent it = new Intent(PostActivity.this, ViewAccountActivity.class);
                            startActivity(it);
                        }
                        if (item.getTitle().equals("Change Email")) {
                            Intent it = new Intent(PostActivity.this, ChangeEmail.class);
                            startActivity(it);
                        }
                        if (item.getTitle().equals("Change Password")) {
                            Intent it = new Intent(PostActivity.this, ChangePassword.class);
                            startActivity(it);
                        }
                        if (item.getTitle().equals("Change Display Name")) {
                            Intent it = new Intent(PostActivity.this, ChangePassword.class);
                            startActivity(it);
                        }
                        if (item.getTitle().equals("Line Information")) {

                            Intent it = new Intent(PostActivity.this, LineSelectActivity.class);
                            startActivity(it);
                        }
                        if (item.getTitle().equals("Boards")) {
                            Intent it = new Intent(PostActivity.this, ThreadStartActivity.class);
                            startActivity(it);
                        }
                        if (item.getTitle().equals("Log Out")) {
                            FirebaseAuth.getInstance().signOut();
                            Intent intent = new Intent(PostActivity.this, MainActivity.class);
                            startActivity(intent);
                        }
                        if (item.getTitle().equals("Delete Account")) {
                            //creates AlertDialog for deletion of user account when deletion button clicked
                            final AlertDialog.Builder alertDialog = new AlertDialog.Builder(PostActivity.this);
                            alertDialog.setTitle("DELETE ACCOUNT");
                            alertDialog.setMessage("Enter FireBase Email to delete FireBase account");

                            final EditText input = new EditText(PostActivity.this);
                            LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(
                                    LinearLayout.LayoutParams.MATCH_PARENT,
                                    LinearLayout.LayoutParams.MATCH_PARENT);
                            input.setLayoutParams(lp);
                            alertDialog.setView(input);
                            alertDialog.setPositiveButton("ENTER", new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int whichButton) {

                                    String userIn = input.getText().toString();
                                    userIn = userIn.trim();
                                    FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
                                    String EmailFire = "";
                                    try {
                                        EmailFire = user.getEmail();
                                    } catch (Exception e) {

                                    }

                                    if (userIn.equals(EmailFire)) {

                                        Toast toast = Toast.makeText(getApplicationContext(), "Deleting your account", Toast.LENGTH_LONG);
                                        toast.show();
                                        try {

                                            user.delete()
                                                    .addOnCompleteListener(new OnCompleteListener<Void>() {
                                                        @Override
                                                        public void onComplete(@NonNull Task<Void> task) {
                                                            if (task.isSuccessful()) {
                                                                Log.d("TAG ", "User account deleted.");
                                                                Toast toast = Toast.makeText(getApplicationContext(), "Account was deleted", Toast.LENGTH_LONG);
                                                                toast.show();
                                                                Intent intent = new Intent(PostActivity.this, MainActivity.class);
                                                                startActivity(intent);
                                                            } else {
                                                                Log.d("TAG ", "User account not deleted.");
                                                            }
                                                        }
                                                    });

                                        } catch (Exception e) {
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
        instaFire();

        //Code for testing
//        genThread.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                generateTempThread();
//            }
//        });
//        queryButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                queryPosts();
//            }
//        });
        postButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View V) {
                showPostBox();
            }
        });
        queryButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View V) {
                queryPosts();
            }
        });

    }
    private void generateTempThread() {
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
    private void queryPosts() {
        final AlertDialog.Builder alertDialog = new AlertDialog.Builder(PostActivity.this);
        alertDialog.setTitle("Search Post by Topic");
        alertDialog.setMessage("");
        final EditText searchTopic = new EditText(PostActivity.this);
        searchTopic.setHint("Enter Topic");
        searchTopic.setTextColor(000000);

        alertDialog.setView(searchTopic);
        alertDialog.setPositiveButton("Search", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int whichButton) {
                //assume they have a display name by now
                String search = searchTopic.getText().toString();
                String displayName =  fUser.getDisplayName();

                Toast toast = Toast.makeText(getApplicationContext(), "Seaerching...", Toast.LENGTH_SHORT);
                toast.show();


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

    public void instaFire() {
        fAuth = FirebaseAuth.getInstance();
        fUser = fAuth.getCurrentUser();
        fStore = FirebaseFirestore.getInstance();

        FirebaseFirestoreSettings settings = new FirebaseFirestoreSettings.Builder()
                .setPersistenceEnabled(true)
                .build();
        fStore.setFirestoreSettings(settings);
    }


    public void showPostBox() {
        final AlertDialog.Builder alertDialog = new AlertDialog.Builder(PostActivity.this);
        alertDialog.setTitle("Add Post");
        alertDialog.setMessage("");

        final EditText topicInput = new EditText(PostActivity.this);
        topicInput.setHint("Enter Topic");
        topicInput.setTextColor(000000);
        final EditText comment = new EditText(PostActivity.this);
        comment.setHint("Enter message");
        comment.setTextColor(000000);
        LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.MATCH_PARENT);
        topicInput.setLayoutParams(lp);
        comment.setLayoutParams(lp);
        alertDialog.setView(topicInput);
        alertDialog.setView(comment);
        alertDialog.setPositiveButton("Post", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int whichButton) {
  //assume they have a display name by now
                String userTopic = topicInput.getText().toString();
                String userInput = comment.getText().toString();
                String displayName =  fUser.getDisplayName();
                String TrainThreadLine = lineName;
                Toast toast = Toast.makeText(getApplicationContext(), "Posting...", Toast.LENGTH_SHORT);
                toast.show();
                postThread(userTopic, userInput, displayName, TrainThreadLine);

                //create a thread

         /*           try {
//                        fStore.collection("Post").document(threadId).set

                    } catch (Exception e) {
                        toast = Toast.makeText(getApplicationContext(), "Account was not found", Toast.LENGTH_LONG);
                        toast.show();
                    }*/
        /*        try {


                } catch (Exception e) {
                    toast = Toast.makeText(getApplicationContext(), "Account was not found", Toast.LENGTH_LONG);
                    toast.show();
                }*/

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

    private void displayPosts(){

    }
    private void postThread(String userTopic, String userInput, String  displayName, String TrainThreadLine)
    {
        Map<String,Object> data =  new HashMap<>();
        data.put("Topic: ", userTopic);
        data.put("Comment: ", userInput);
        data.put("UserName: ", displayName);
        data.put("Thread: ", TrainThreadLine);

        fStore.collection(TrainThreadLine)
                .add(data)
                .addOnSuccessListener(new OnSuccessListener<DocumentReference>(){


                    @Override
                    public void onSuccess(DocumentReference documentReference) {
                        Log.d(TAG, "DoucmentSnapshot Written with ID: " + documentReference.getId());
                    }
                })
                .addOnFailureListener(new OnFailureListener(){
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.w(TAG, "Error adding document, ", e);
                    }
                });

    }

}