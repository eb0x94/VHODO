package com.example.ivo.vhodo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity {
    private EditText userText;
    private EditText passwordText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        userText = (EditText) findViewById(R.id.userNameTextBox);
        passwordText = (EditText) findViewById(R.id.passwordTextBox);
    }

    public void onLoginClick(View view) {
        String usernameStr = userText.getText().toString();
        String userPassStr = passwordText.getText().toString();
        checkLogin(usernameStr, userPassStr);

        //// TODO: make intents and start them.

    }

    private void checkLogin(String usernameStr, String userPassStr) {
        String toastText = "";
        boolean isFail = false;

        if (usernameStr.length() < 5 && userPassStr.length() < 6){
            toastText = "Please,enter a valid username and password";
            isFail = true;
        }else if (usernameStr.length() < 5){
            toastText = "Please,enter a valid username";
            isFail = true;
        }else if (userPassStr.length() < 6){
            toastText = "Please,enter a valid password";
            isFail = true;
        }

        if (isFail){
            passwordText.setText("");
        }

        //// TODO: if all is valid,LoginHelper to check the user name and pass

        Toast.makeText(this,toastText,Toast.LENGTH_SHORT).show();
    }
}