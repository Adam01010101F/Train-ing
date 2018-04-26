package com.aei.training;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;


public class ChangePassword extends AppCompatActivity{
    private EditText oldPass;
    private EditText newPass;
    private EditText confPass;
    private Button changePass;
    private FirebaseUser user;
    private FirebaseAuth fireAuth;

    @Override
    protected  void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        fireAuth.getInstance();
        setContentView(R.layout.activity_change_password);
        newPass =(EditText) findViewById(R.id.passField);
        confPass = (EditText) findViewById(R.id.confPassField);
        changePass = (Button) findViewById(R.id.chngPassButton);

        changePass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!changePass.getText().toString().isEmpty()){
                    changePassword(changePass.getText().toString());
                }
            }
        });
    }

    protected void changePassword(String newPassword){
        user.updatePassword(newPassword)
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful()) {
                            Log.d("PsChg", "User password updated.");
                            verifyEmail();
                        }
                        else{
                            Log.d("PsChg", "User password not updated.");
                        }
                    }
                });
    }

    protected void verifyEmail(){
        fireAuth.sendPasswordResetEmail(user.getEmail())
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful()) {
                            Log.d("PsVfy", "Email sent.");
                        }
                    }
                });
    }
}
