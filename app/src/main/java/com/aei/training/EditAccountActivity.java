package com.aei.training;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.EditText;

/**
 * Created by Null on 4/25/2018.
 */

public class EditAccountActivity extends AppCompatActivity {
    private EditText email;
    private EditText name;
    private EditText password;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_account);


    }
}
