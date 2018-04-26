package com.aei.training;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class ChangeEmail extends AppCompatActivity {
        private EditText currentEmail;
        private EditText newEmail;
        private Button ChangeEmail;



        @Override
        protected void onCreate(Bundle savedInstanceState) {
                super.onCreate(savedInstanceState);
                setContentView(R.layout.change_email);
                ChangeEmail = (Button) findViewById(R.id.EmailChange);
                currentEmail = (EditText) findViewById(R.id.Current_Email);
                newEmail = (EditText) findViewById(R.id.New_Email);





                ChangeEmail.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            String currentEmailS;
                            currentEmailS =  currentEmail.getText().toString();
                            String newEmailS;
                            newEmailS = newEmail.getText().toString();
                            FirebaseUser  user = FirebaseAuth.getInstance().getCurrentUser();
                            String email = null;
                            int x = 0;
                            int y = 0;
                            int x2 = 0;
                            int y2 = 0;
                            for(int i = 0; i < currentEmailS.length(); i++)
                            {
                                if(currentEmailS.charAt(i) == '@')
                                {
                                    x = x +1;

                                }
                                if(currentEmailS.charAt(i) == '.')
                                {
                                    y = y +1;
                                }
                            }
                            for(int j = 0; j < newEmailS.length(); j++)
                            {
                                if(newEmailS.charAt(j) == '@')
                                {
                                    x2 = x2 +1;

                                }
                                if(newEmailS.charAt(j) == '.')
                                {
                                    y2 = y2 +1;
                                }
                            }



                              if(user == null)
                              {
                                 //figure out for future improvements
                              } else {
                                   email = user.getEmail();
                              }

                          if(currentEmailS.equals("") && newEmailS.equals("")) {
                              AlertDialog.Builder builder = new AlertDialog.Builder(ChangeEmail.this);
                              builder.setMessage("Please enter the current email and a new email")
                                      .setTitle("Error: Empty Spaces")
                                      .setPositiveButton(android.R.string.ok, null);
                              AlertDialog dialog = builder.create();
                              dialog.show();
                          } else if(newEmailS.equals(""))
                            {
                                AlertDialog.Builder builder = new AlertDialog.Builder(ChangeEmail.this);
                                builder.setMessage("Please enter a new email")
                                        .setTitle("Error: Blank Space")
                                        .setPositiveButton(android.R.string.ok, null);
                                AlertDialog dialog = builder.create();
                                dialog.show();


                            }else if(currentEmailS.equals("")) {
                                AlertDialog.Builder builder = new AlertDialog.Builder(ChangeEmail.this);
                                builder.setMessage("Please enter the current email")
                                        .setTitle("Error: Blank Space")
                                        .setPositiveButton(android.R.string.ok, null);
                                AlertDialog dialog = builder.create();
                                dialog.show();

                            }

                            else if((x > 1 || y > 1 || x2 > 1|| y2 > 1) && (!currentEmailS.equals("") && !newEmailS.equals("")))
                              {
                                  AlertDialog.Builder builder = new AlertDialog.Builder(ChangeEmail.this);
                                  builder.setMessage("Enter one:  @ and .")
                                          .setTitle("Error: Improper Email")
                                          .setPositiveButton(android.R.string.ok, null);
                                  AlertDialog dialog = builder.create();
                                  dialog.show();

                            } else if(!currentEmailS.contains(".")  || !currentEmailS.contains("@") || !newEmailS.contains(".") || !newEmailS.contains("@"))
                            {
                                AlertDialog.Builder builder = new AlertDialog.Builder(ChangeEmail.this);
                                builder.setMessage("Please include @ and . in both emails")
                                        .setTitle("Error: Improper Email")
                                        .setPositiveButton(android.R.string.ok, null);
                                AlertDialog dialog = builder.create();
                                dialog.show();

                            }else if(email.equals(newEmailS))
                                {
                                    AlertDialog.Builder builder = new AlertDialog.Builder(ChangeEmail.this);
                                    builder.setMessage("Please enter a new email from the current one entered")
                                            .setTitle("Error: Email already used")
                                            .setPositiveButton(android.R.string.ok, null);
                                    AlertDialog dialog = builder.create();
                                    dialog.show();
                                } else
                                if(email.equals(currentEmailS))
                                {
                                        Toast toast = Toast.makeText(getApplicationContext(), "Email entered matches Firebase email", Toast.LENGTH_LONG);
                                        toast.show();
                                        user.updateEmail(newEmailS);
                                        toast = Toast.makeText(getApplicationContext(), "Firebase email updated", Toast.LENGTH_LONG);
                                        toast.show();


                                 }else if(!currentEmailS.equals(email))
                                {
                                    AlertDialog.Builder builder = new AlertDialog.Builder(ChangeEmail.this);
                                    builder.setMessage("Please retry current Email")
                                            .setTitle("Error: Wrong Current Email")
                                            .setPositiveButton(android.R.string.ok, null);
                                    AlertDialog dialog = builder.create();
                                    dialog.show();
                                }



                        }
                });
        }




}
