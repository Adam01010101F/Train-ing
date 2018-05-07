package com.aei.training;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.facebook.AccessToken;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.login.LoginManager;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FacebookAuthProvider;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.Arrays;

/**
 * A login screen that offers login via email/password.
 */
public class MainActivity extends AppCompatActivity {

    private LoginButton fbookButton;
    private EditText email;
    private CallbackManager cbManager;
    protected Button logIn;
    protected EditText Password;
    protected TextView CreateAccount, forgotPass;
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
        CreateAccount = (TextView) findViewById(R.id.textView2);
        logIn = (Button) findViewById(R.id.button);
        forgotPass = (TextView) findViewById(R.id.textView3);
        Password = (EditText) findViewById(R.id.passField);

        // Initialize

        mFirebaseAuth = FirebaseAuth.getInstance();

        //TEMP CODE TO DEMO LOGIN
  //      FirebaseAuth.getInstance().signOut();
        LoginManager.getInstance().logOut();

        //firebasse auto-log in
        if(mFirebaseAuth.getCurrentUser() != null)
        {
            Intent intent = new Intent(MainActivity.this, FireStoreTestActivity.class); //change back
            startActivity(intent);
        }
        forgotPass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (email.getText().toString().trim().isEmpty()) {
                    AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                    builder.setMessage(R.string.forgot_pw_msg)
                            .setTitle(R.string.forgot_pw_title)
                            .setPositiveButton(android.R.string.ok, null);
                    AlertDialog dialog = builder.create();
                    dialog.show();
                } else {
                    FirebaseAuth.getInstance().sendPasswordResetEmail(email.getText().toString().trim())
                            .addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {
                                    if (task.isSuccessful()) {
                                        AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                                        builder.setMessage(R.string.forgot_pw_success)
                                                .setTitle(R.string.forgot_pw_title_success)
                                                .setPositiveButton(android.R.string.ok, null);
                                        AlertDialog dialog = builder.create();
                                        dialog.show();
                                    } else {
                                        AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                                        builder.setMessage(R.string.forgot_pw_fail)
                                                .setTitle(R.string.forgot_pw_title)
                                                .setPositiveButton(android.R.string.ok, null);
                                        AlertDialog dialog = builder.create();
                                        dialog.show();
                                    }
                                }
                            });
                }
            }
        });

        CreateAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, RegisterActivity.class);
                startActivity(intent);
            }
        });

        fbookButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fbookButton.setReadPermissions("email", "public_profile");

                // Callback registration
                fbookButton.registerCallback(cbManager, new FacebookCallback<LoginResult>() {
                    @Override
                    public void onSuccess(LoginResult loginResult) {
                        Log.d("onSuccess", "Its getting here!");
                        facebookLoginToken(loginResult.getAccessToken());
                        Intent intent = new Intent(MainActivity.this, LineSelectActivity.class);
                        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                        startActivity(intent);

                    }

                    @Override
                    public void onCancel() {
                        Toast.makeText(getApplicationContext(), "Bad Facebook Credentials", Toast.LENGTH_LONG);
                        Log.d("onCancel", "Its getting here!");

                    }

                    @Override
                    public void onError(FacebookException exception) {
                        Log.d("onError", "Its getting here!");

                        Log.e("onError: Login", exception.toString());
                    }
                });
            }
        });


        logIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String mail = email.getText().toString();
                String password = Password.getText().toString();
                mail = mail.trim();
                password = password.trim();
                if (mail.isEmpty() || password.isEmpty()) {
                    AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                    builder.setMessage(R.string.login_error_message)
                            .setTitle(R.string.login_error_title)
                            .setPositiveButton(android.R.string.ok, null);
                    AlertDialog dialog = builder.create();
                    dialog.show();
                } else {
                    mFirebaseAuth.signInWithEmailAndPassword(mail, password)
                            .addOnCompleteListener(MainActivity.this, new OnCompleteListener<AuthResult>() {

                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task) {
                                    if (task.isSuccessful()) {
                                        Intent intent = new Intent(MainActivity.this, FireStoreTestActivity.class);
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

                                }
                            });
                }


            }

        });


    }

    @Override
    protected void onStart() {
        super.onStart();
        FirebaseUser currentUser = mFirebaseAuth.getCurrentUser();
        if (currentUser != null) {

        }
    }

    private void facebookLoginToken(AccessToken token) {
        Log.d("FacebookLogin", "handleFacebookAccessToken:" + token);

        AuthCredential credential = FacebookAuthProvider.getCredential(token.getToken());
        mFirebaseAuth.signInWithCredential(credential)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Log.d("FacebookLogin", "signInWithCredential:success");
                            FirebaseUser user = mFirebaseAuth.getCurrentUser();
                            Intent intent = new Intent(MainActivity.this, LineSelectActivity.class);
                            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                            startActivity(intent);
                        } else if(!task.isSuccessful()) {
                            // If sign in fails, display a message to the user.
                            Log.w("FacebookLogin", "signInWithCredential:failure", task.getException());
//                            Toast.makeText(MainActivity.this, "Authentication failed.",
//                                    Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        // Pass the activity result back to the Facebook SDK
        cbManager.onActivityResult(requestCode, resultCode, data);
    }

}

