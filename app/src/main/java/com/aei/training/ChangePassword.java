package com.aei.training;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

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
        //Setting Firebase vars
        fireAuth = FirebaseAuth.getInstance();
        user = fireAuth.getCurrentUser();
        //Setting UI cars
        setContentView(R.layout.activity_change_password);
        newPass =(EditText) findViewById(R.id.passField);
        confPass = (EditText) findViewById(R.id.confPassField);
        changePass = (Button) findViewById(R.id.chngPassButton);



        changePass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!newPass.getText().toString().isEmpty()
                        && newPass.getText().toString().equals(confPass.getText().toString())){
                    changePassword(changePass.getText().toString());
//                    Log.d("EDITFIELDS:", newPass.getText().toString() + confPass.getText().toString());
                }
                else{
                    if(newPass.getText().toString().isEmpty()){
                        popToast("Enter a password.");
                    } else{
                        popToast("Passwords do not match.");
                    }
                }
            }
        });
    }
//  TODO: KNOWN BUG - If JWT is expired, password can't be changed.
    protected void changePassword(String newPassword){
        user.updatePassword(newPassword)
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful()) {
                            Log.d("PsChg", "User password updated.");
                            verifyEmail();
                            Toast toast = Toast.makeText(getApplication().getBaseContext(), "Password Changed"
                                    , Toast.LENGTH_LONG);
                            toast.show();
                        }
                        else{
                            Log.d("PsChg", task.getException().toString());
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

    protected void popToast(String msg){
        Toast toast = Toast.makeText(getApplication().getBaseContext(), msg,
                Toast.LENGTH_LONG);
        toast.show();
    }
}
