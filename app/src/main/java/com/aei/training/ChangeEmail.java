package com.aei.training;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
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

public class ChangeEmail extends AppCompatActivity {
        private EditText currentEmail;
        private EditText newEmail;
        private Button changeEmail;



        @Override
        protected void onCreate(Bundle savedInstanceState) {
                super.onCreate(savedInstanceState);
                setContentView(R.layout.email_drawer);
                changeEmail = (Button) findViewById(R.id.EmailChange);
                currentEmail = (EditText) findViewById(R.id.Current_Email);
                newEmail = (EditText) findViewById(R.id.New_Email);
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
                                Intent it = new Intent(ChangeEmail.this,ViewAccountActivity.class);
                                startActivity(it);
                            }
                            if(item.getTitle().equals("Change Email")){
                                Intent it = new Intent(ChangeEmail.this,ChangeEmail.class);
                                startActivity(it);
                            }
                            if(item.getTitle().equals("Change Password")){
                                Intent it = new Intent(ChangeEmail.this,ChangePassword.class);
                                startActivity(it);
                            }
                            if(item.getTitle().equals("Change Display Name")){
                                Intent it = new Intent(ChangeEmail.this,ChangeEmail.class);
                                startActivity(it);
                            }
                            if(item.getTitle().equals("Line Information")){

                                Intent it = new Intent(ChangeEmail.this,LineSelectActivity.class);
                                startActivity(it);
                            }
                            if(item.getTitle().equals("Boards")){

                            }
                            if(item.getTitle().equals("Log Out")){
                                FirebaseAuth.getInstance().signOut();
                                Intent intent = new Intent(ChangeEmail.this, MainActivity.class);
                                startActivity(intent);
                            }
                            if(item.getTitle().equals("Delete Account")){
                                //creates AlertDialog for deletion of user account when deletion button clicked
                                final  AlertDialog.Builder alertDialog = new AlertDialog.Builder(ChangeEmail.this);
                                alertDialog.setTitle("DELETE ACCOUNT");
                                alertDialog.setMessage("Enter FireBase Email to delete FireBase account");

                                final EditText input = new EditText(ChangeEmail.this);
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
                                                                    Intent intent = new Intent(ChangeEmail.this, MainActivity.class);
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





                changeEmail.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            String currentEmailS =  currentEmail.getText().toString();
                            String newEmailS =  newEmail.getText().toString();

                            FirebaseUser  user = FirebaseAuth.getInstance().getCurrentUser();
                            user.updateEmail(newEmailS);
                            Toast.makeText(getApplicationContext(), "Firebase email updated", Toast.LENGTH_LONG).show();
//                            String email = null;
//                            int x = 0;
//                            int y = 0;
//                            int x2 = 0;
//                            int y2 = 0;
//                            for(int i = 0; i < currentEmailS.length(); i++)
//                            {
//                                if(currentEmailS.charAt(i) == '@')
//                                {
//                                    x = x +1;
//
//                                }
//                                if(currentEmailS.charAt(i) == '.')
//                                {
//                                    y = y +1;
//                                }
//                            }
//                            for(int j = 0; j < newEmailS.length(); j++)
//                            {
//                                if(newEmailS.charAt(j) == '@')
//                                {
//                                    x2 = x2 +1;
//
//                                }
//                                if(newEmailS.charAt(j) == '.')
//                                {
//                                    y2 = y2 +1;
//                                }
//                            }
//
//
//
//                              if(user == null)
//                              {
//                                 //figure out for future improvements
//                              } else {
//                                   email = user.getEmail();
//                              }
//
//                          if(currentEmailS.equals("") && newEmailS.equals("")) {
//                              AlertDialog.Builder builder = new AlertDialog.Builder(ChangeEmail.this);
//                              builder.setMessage("Please enter the current email and a new email")
//                                      .setTitle("Error: Empty Spaces")
//                                      .setPositiveButton(android.R.string.ok, null);
//                              AlertDialog dialog = builder.create();
//                              dialog.show();
//                          } else if(newEmailS.equals(""))
//                            {
//                                AlertDialog.Builder builder = new AlertDialog.Builder(ChangeEmail.this);
//                                builder.setMessage("Please enter a new email")
//                                        .setTitle("Error: Blank Space")
//                                        .setPositiveButton(android.R.string.ok, null);
//                                AlertDialog dialog = builder.create();
//                                dialog.show();
//
//
//                            }else if(currentEmailS.equals("")) {
//                                AlertDialog.Builder builder = new AlertDialog.Builder(ChangeEmail.this);
//                                builder.setMessage("Please enter the current email")
//                                        .setTitle("Error: Blank Space")
//                                        .setPositiveButton(android.R.string.ok, null);
//                                AlertDialog dialog = builder.create();
//                                dialog.show();
//
//                            }
//
//                            else if((x > 1 || y > 1 || x2 > 1|| y2 > 1) && (!currentEmailS.equals("") && !newEmailS.equals("")))
//                              {
//                                  AlertDialog.Builder builder = new AlertDialog.Builder(ChangeEmail.this);
//                                  builder.setMessage("Enter one:  @ and .")
//                                          .setTitle("Error: Improper Email")
//                                          .setPositiveButton(android.R.string.ok, null);
//                                  AlertDialog dialog = builder.create();
//                                  dialog.show();
//
//                            } else if(!currentEmailS.contains(".")  || !currentEmailS.contains("@") || !newEmailS.contains(".") || !newEmailS.contains("@"))
//                            {
//                                AlertDialog.Builder builder = new AlertDialog.Builder(ChangeEmail.this);
//                                builder.setMessage("Please include @ and . in both emails")
//                                        .setTitle("Error: Improper Email")
//                                        .setPositiveButton(android.R.string.ok, null);
//                                AlertDialog dialog = builder.create();
//                                dialog.show();
//
//                            }else if(email.equals(newEmailS))
//                                {
//                                    AlertDialog.Builder builder = new AlertDialog.Builder(ChangeEmail.this);
//                                    builder.setMessage("Please enter a new email from the current one entered")
//                                            .setTitle("Error: Email already used")
//                                            .setPositiveButton(android.R.string.ok, null);
//                                    AlertDialog dialog = builder.create();
//                                    dialog.show();
//                                } else
//                                if(email.equals(currentEmailS))
//                                {
//                                        Toast toast = Toast.makeText(getApplicationContext(), "Email entered matches Firebase email", Toast.LENGTH_LONG);
//                                        toast.show();
////                                        user.updateEmail(newEmailS);
//                                        toast = Toast.makeText(getApplicationContext(), "Firebase email updated", Toast.LENGTH_LONG);
//                                        toast.show();
//
//
//                                 }else if(!currentEmailS.equals(email))
//                                {
//                                    AlertDialog.Builder builder = new AlertDialog.Builder(ChangeEmail.this);
//                                    builder.setMessage("Please retry current Email")
//                                            .setTitle("Error: Wrong Current Email")
//                                            .setPositiveButton(android.R.string.ok, null);
//                                    AlertDialog dialog = builder.create();
//                                    dialog.show();
//                                }



                        }
                });
        }




}
