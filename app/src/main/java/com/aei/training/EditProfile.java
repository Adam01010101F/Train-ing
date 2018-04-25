package com.aei.training;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

public class EditProfile extends AppCompatActivity {
    private Button changeEmail;
    private Button changePassword;
    private Button changeDisplayInfo;
    @Override
    protected  void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
//        setContentView();
        changeEmail = (Button) findViewById(R.id.logOut);
        changePassword = (Button) findViewById(R.id.logOut);
        changeDisplayInfo = (Button) findViewById(R.id.logOut);

        changeEmail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(EditProfile.this, ChangeEmail.class);
                startActivity(intent);
            }
        });
        changePassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(EditProfile.this, ChangePassword.class);
                startActivity(intent);
            }
        });
        changeDisplayInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(EditProfile.this, ChangeIcon.class);
                startActivity(intent);
            }
        });
    }
}
