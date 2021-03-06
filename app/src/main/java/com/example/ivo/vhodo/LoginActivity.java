package com.example.ivo.vhodo;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.ivo.vhodo.models.User;
import com.example.ivo.vhodo.tools.LoginHelper;
import com.example.ivo.vhodo.tools.Status;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;

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

    public void onLoginClick(View view) throws UnsupportedEncodingException, NoSuchAlgorithmException {
        String usernameStr = userText.getText().toString();
        String userPassStr = passwordText.getText().toString();
        if (checkLogin(usernameStr, userPassStr)) {
            // TODO: 11.10.2016 г. Fix the starting of the intent
  //          GlobalData.setCurrentUser(GlobalData.getUser(usernameStr));

            Intent intent = new Intent(getApplicationContext(), MainActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);

            startActivity(intent);
        } else {
            passwordText.setText("");
        }

        //// TODO: make intents and start them.

    }

    private boolean checkLogin(String usernameStr, String userPassStr) throws UnsupportedEncodingException, NoSuchAlgorithmException {
        Status status = LoginHelper.checkForValidLogin(usernameStr, userPassStr);

        if (status.isShallPass()) {
            Toast.makeText(this, status.getMsg(), Toast.LENGTH_LONG).show();
            return true;
        } else {

            Toast.makeText(this, status.getMsg(), Toast.LENGTH_SHORT).show();
            return false;
        }
    }

    public void onSignUpClick(View view) {
        Intent intent = new Intent(LoginActivity.this, SignUpActivity.class);
        startActivity(intent);
    }
}