package com.aei.training;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.annotation.TargetApi;
import android.content.pm.PackageManager;
import android.support.annotation.NonNull;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.app.AlertDialog;
import android.app.LoaderManager.LoaderCallbacks;


import android.content.CursorLoader;
import android.content.Loader;
import android.database.Cursor;
import android.net.Uri;
import android.os.AsyncTask;

import android.os.Build;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.text.TextUtils;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.inputmethod.EditorInfo;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.appevents.AppEventsLogger;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static android.Manifest.permission.READ_CONTACTS;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
/**
 * A login screen that offers login via email/password.
 */
public class MainActivity extends AppCompatActivity {
    private LoginButton fbookButton;
    private EditText email;
    private CallbackManager cbManager;
    protected Button logIn;
    protected EditText Password;
    protected TextView CreateAccount;
    private FirebaseAuth mFirebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//        FacebookSdk.sdkInitialize(getApplicationContext());
//        AppEventsLogger.activateApp(this);
        // Set up the login form.
        cbManager = CallbackManager.Factory.create();
        email = findViewById(R.id.emailField);
        fbookButton = findViewById(R.id.fbButton);
        CreateAccount = (TextView)findViewById(R.id.textView2);
        logIn = (Button)findViewById(R.id.button);
        Password = (EditText) findViewById(R.id.editText2);
        
        // Initialize 
        FirebaseAuthmFirebaseAuth = FirebaseAuth.getInstance();
        
        CreateAccount.setOnClickListener(new View.OnClickListener() {   
            @Override    
            public void onClick(View v) {       
                Intent intent = new Intent(MainActivity.this, RegisterActivity.class);        
                startActivity(intent);    
            }
        });
        
        logIn.setOnClickListener(new View.OnClickListener() {   
            @Override   
            public void onClick(View v) {       
                String email = email.getText().toString();       
                String password = Password.getText().toString();       
                email = email.trim();        
                password = password.trim();        
                if (email.isEmpty() || password.isEmpty()) {           
                    AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);           
                    builder.setMessage(R.string.login_error_message)                  
                        .setTitle(R.string.login_error_title)                    
                        .setPositiveButton(android.R.string.ok, null);          
                    AlertDialog dialog = builder.create();            
                    dialog.show();     
                } 
                else {           
                    mFirebaseAuth.signInWithEmailAndPassword(email, password)                   
                        .addOnCompleteListener(MainActivity.this, new OnCompleteListener<AuthResult>() {                     
                            .addOnCompleteListener(MainActivity.this, new OnCompleteListener<AuthResult>() {  
                                @Override    
                                public void onComplete(@NonNull Task<AuthResult> task)           
                                if (task.isSuccessful()) {                               
                                    Intent intent = new Intent(MainActivity.this, LineSelectActivity.class);                               
                                    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);                             
                                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);                               
                                    startActivity(intent);                           
                                } else {                            
                                    
                                    AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);                               
                                    builder.setMessage(task.getException().getMessage())                                      
                                        .setTitle(R.string.login_error_title)                                       
                                        .setPositiveButton(android.R.string.ok, null);                              
                                    AlertDialog dialog = builder.create();                             
                                    dialog.show();                         
                                }                                      
                                                   
                            });     
                        }    
                    });

        fbookButton.setReadPermissions(Arrays.asList(email.getText().toString()));

        // Callback registration
        fbookButton.registerCallback(cbManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {

            }

            @Override
            public void onCancel() {
                // App code
            }

            @Override
            public void onError(FacebookException exception) {
                Log.e("onError: Login", exception.toString());
            }
        });


    }


    
}

