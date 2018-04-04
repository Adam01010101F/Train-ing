package com.aei.training;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class AccountManageActivity extends AppCompatActivity {
    private Button logOut;
    private Button delete;
    private FirebaseAuth mFirebaseAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account_manage);
        logOut = (Button) findViewById(R.id.logOut);
        delete = (Button) findViewById(R.id.delete);

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

        //deletes firebase account
        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
                user.delete()
                        .addOnCompleteListener(new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull Task<Void> task) {
                                if (task.isSuccessful()) {
                                    Log.d("TAG ","User account deleted.");
                                    Intent intent = new Intent(AccountManageActivity.this, MainActivity.class);
                                    startActivity(intent);
                                } else
                                  {
                                      Log.d("TAG ","User account not deleted.");
                                  }
                            }
                        });

            }
        });
    }
}
