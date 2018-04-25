package com.aei.training;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class AccountManageActivity extends AppCompatActivity {
    private Button logOut;
    private Button delete;
    private Button selectLine;
    private FirebaseAuth mFirebaseAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account_manage);
        logOut = (Button) findViewById(R.id.logOut);
        delete = (Button) findViewById(R.id.delete);
        selectLine = (Button) findViewById(R.id.LineSel);

        // Initialize
        mFirebaseAuth = FirebaseAuth.getInstance();

        //logs user out of firebase account
        //facebook?
        logOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseAuth.getInstance().signOut();
                Intent intent = new Intent(AccountManageActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });
        selectLine.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //FirebaseAuth.getInstance().signOut();
                Intent intent = new Intent(AccountManageActivity.this, LineSelectActivity.class);
                startActivity(intent);
            }
        });
        //deletes firebase account
        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //creates AlertDialog for deletion of user account when deletion button clicked
               final  AlertDialog.Builder alertDialog = new AlertDialog.Builder(AccountManageActivity.this);
                alertDialog.setTitle("DELETE ACCOUNT");
                alertDialog.setMessage("Enter FireBase Email to delete FireBase account");

                final EditText input = new EditText(AccountManageActivity.this);
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
                       String EmailFire = user.getEmail();



                           if(userIn.equals(EmailFire))
                           {
                               Toast toast = Toast.makeText(getApplicationContext(), "FireBase email was entered, deleting your account", Toast.LENGTH_LONG);
                               toast.show();
                               user.delete()
                                .addOnCompleteListener(new OnCompleteListener<Void>() {
                                    @Override
                                    public void onComplete(@NonNull Task<Void> task) {
                                        if (task.isSuccessful()) {
                                            Log.d("TAG ", "User account deleted.");
                                            Intent intent = new Intent(AccountManageActivity.this, MainActivity.class);
                                            startActivity(intent);
                                        } else {
                                            Log.d("TAG ", "User account not deleted.");
                                        }
                                    }
                                });
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
        });
    }
}
