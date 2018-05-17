package com.aei.training;

/**
 * Created by Adam on 5/16/2018.
 */
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.PorterDuff;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.constraint.ConstraintLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.v4.content.res.ResourcesCompat;
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
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;


import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreSettings;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.HashMap;
import java.util.Map;

public class CommentActivity extends AppCompatActivity {
    private LinearLayout linearLayout;
    private LinearLayout.LayoutParams params;
    private ScrollView scrollView;
    Toolbar tb;
    private ConstraintLayout cl;
    private DrawerLayout drawerLayout;
    private FirebaseAuth fAuth;
    private FirebaseUser fUser;
    private FirebaseFirestore fStore;
    private FloatingActionButton genThreadPost;
    private FloatingActionButton queryButton;
    private FloatingActionButton postButton;
    private String docName,lineName;
    private static final String TAG = "DocSnippets";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.post_drawer);
        drawerLayout = findViewById(R.id.drawer_layout);
        cl= findViewById(R.id.const_layout);
        scrollView = new ScrollView(this);
        params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        params.setMargins(0,0,0,0);
        linearLayout = new LinearLayout(this);
        linearLayout.setLayoutParams(params);
        linearLayout.setOrientation(LinearLayout.VERTICAL);

        scrollView.addView(linearLayout);
        cl.addView(scrollView);
        //drawerLayout.addView(scrollView);


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
//        queryButton = findViewById(R.id.query);
//        genThreadPost = findViewById(R.id.allOfALine);
//        queryButton = findViewById(R);
        //retreives the string Thread passed by the bundle by using the intent
        String[] parsed = getIntent().getStringExtra("ID").split(":");
        docName = parsed[0];
        lineName=parsed[1];
        //docName ="Metro Blue Line (801)1";
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
                            Intent it = new Intent(CommentActivity.this, ViewAccountActivity.class);
                            startActivity(it);
                        }
                        if (item.getTitle().equals("Change Email")) {
                            Intent it = new Intent(CommentActivity.this, ChangeEmail.class);
                            startActivity(it);
                        }
                        if (item.getTitle().equals("Change Password")) {
                            Intent it = new Intent(CommentActivity.this, ChangePassword.class);
                            startActivity(it);
                        }
                        if (item.getTitle().equals("Change Display Name")) {
                            Intent it = new Intent(CommentActivity.this, EditDisplayInfo.class);
                            startActivity(it);
                        }
                        if (item.getTitle().equals("Line Information")) {

                            Intent it = new Intent(CommentActivity.this, LineSelectActivity.class);
                            startActivity(it);
                        }
                        if (item.getTitle().equals("Boards")) {
                            Intent it = new Intent(CommentActivity.this, ThreadStartActivity.class);
                            startActivity(it);
                        }
                        if (item.getTitle().equals("Log Out")) {
                            FirebaseAuth.getInstance().signOut();
                            Intent intent = new Intent(CommentActivity.this, MainActivity.class);
                            startActivity(intent);
                        }
                        if (item.getTitle().equals("Delete Account")) {
                            //creates AlertDialog for deletion of user account when deletion button clicked
                            final AlertDialog.Builder alertDialog = new AlertDialog.Builder(CommentActivity.this);
                            alertDialog.setTitle("DELETE ACCOUNT");
                            alertDialog.setMessage("Enter FireBase Email to delete FireBase account");

                            final EditText input = new EditText(CommentActivity.this);
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
                                                                Intent intent = new Intent(CommentActivity.this, MainActivity.class);
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
        displayPosts();





//        genThreadPost.setOnClickListener(new View.OnClickListener()
//        {
//            @Override
//            public void onClick(View v)
//            {
//                displayPosts();
//            }
//        });
        postButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View V) {
                showPostBox();
            }
        });
//        queryButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View V) {
//                queryPosts();
//            }
//        });

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
        final AlertDialog.Builder alertDialog = new AlertDialog.Builder(CommentActivity.this);
        alertDialog.setTitle("Search Post by Topic");
        alertDialog.setMessage("");
        final EditText searchTopic = new EditText(CommentActivity.this);
        searchTopic.setHint("Enter Topic");
        searchTopic.setTextColor(0xff000000);

        alertDialog.setView(searchTopic);
        alertDialog.setPositiveButton("Search", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int whichButton) {
                //assume they have a display name by now
                String search = searchTopic.getText().toString();
                String displayName =  fUser.getDisplayName();

                Toast toast = Toast.makeText(getApplicationContext(), "Seaerching...", Toast.LENGTH_SHORT);
                toast.show();
                CollectionReference TopicSearched = fStore.collection(lineName).document(docName).collection("Comments");
                Query query = TopicSearched.whereEqualTo("Topic", search);


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
        final AlertDialog.Builder alertDialog = new AlertDialog.Builder(CommentActivity.this);
        alertDialog.setTitle("Add Post");
        alertDialog.setMessage("");

        final EditText topicInput = new EditText(CommentActivity.this);
        topicInput.setHint("Enter Topic");
        topicInput.setTextColor(0xff000000);
        final EditText comment = new EditText(CommentActivity.this);
        comment.setHint("Enter message");
        comment.setTextColor(0xff000000);
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
                String displayName =  fUser.getDisplayName().toString();
                Log.d("nam", displayName);
                String TrainThreadLine = docName;
                Toast toast = Toast.makeText(getApplicationContext(), "Posting...", Toast.LENGTH_SHORT);
                toast.show();
                postThread(userTopic, userInput, displayName);

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
        linearLayout.removeAllViews();
        fStore.collection(lineName).document(docName).collection("Comments")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                Log.d(TAG, document.getId() + " =>" + document.getData());
                                createCommentCard(document.getData().toString(),0xffffffff,false);
                            }
                        } else {
                            Log.d(TAG, "Error getting documents: ", task.getException());
                        }

                    }
                });
    }
    private void postThread(String userTopic, String userInput, String  displayName)
    {
        Map<String,Object> data =  new HashMap<>();
        data.put("UserName", displayName);
        data.put("Comment", userInput);



        fStore.collection(lineName).document(docName).collection("Comments")
                .add(data)
                .addOnSuccessListener(new OnSuccessListener<DocumentReference>(){


                    @Override
                    public void onSuccess(DocumentReference documentReference) {
                        Log.d(TAG, "DoucmentSnapshot Written with ID: " + documentReference.getId());
                        displayPosts();
                    }
                })
                .addOnFailureListener(new OnFailureListener(){
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.w(TAG, "Error adding document, ", e);
                    }
                });

    }

    public void createCommentCard(String text, int lineColor, boolean clickable){
        text = text.substring(1,text.length()-1);
        if(!text.isEmpty()){
            text = text.replace("=",": ");
            text= text.replace("UserName: ","");
            Drawable drawable = ResourcesCompat.getDrawable(this.getResources(), R.drawable.square, null);
            drawable.setColorFilter(lineColor, PorterDuff.Mode.MULTIPLY);
            TextView textView = new TextView(this);
            textView.setLayoutParams(params);
            textView.setText(text);
            textView.setPadding(20,30,0,30);
            textView.setBackgroundResource(R.drawable.card_shadow);
            textView.setTypeface(Typeface.create("sans-serif-medium",Typeface.NORMAL));
            textView.setCompoundDrawablesWithIntrinsicBounds(null, null, drawable, null);
            textView.setTextColor(0xff4f4f4f);
            textView.setTextSize(14);
            textView.setClickable(clickable);

            if(clickable){

                textView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        // text gets passed through to the new activity so the new activity can choose
                        // what to display
                        //linearLayout.removeAllViews();


                    }
                });
            }

            linearLayout.addView(textView);
        }
    }


}