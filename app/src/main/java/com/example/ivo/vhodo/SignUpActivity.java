package com.example.ivo.vhodo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.InputType;
import android.text.method.PasswordTransformationMethod;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.Toast;

import com.example.ivo.vhodo.models.PasswordHelper;

public class SignUpActivity extends AppCompatActivity {
    private final String SHOW_PASS = "Show pass";
    private final String HIDE_PASS = "Hide pass";
    private EditText nameText;
    private EditText userNameText;
    private EditText passwordText;
    private EditText confirmPassText;
    private EditText emailText;
    private EditText phoneText;
    private Button showPass;
    private boolean isShown;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        nameText = (EditText) findViewById(R.id.su_name);
        userNameText = (EditText) findViewById(R.id.su_username);
        passwordText = (EditText) findViewById(R.id.su_pass);
        confirmPassText = (EditText) findViewById(R.id.su_passConfirm);
        emailText = (EditText) findViewById(R.id.su_email);
        phoneText = (EditText) findViewById(R.id.su_phone);
        showPass = (Button) findViewById(R.id.showPassButton);
        showPass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showPassClicked(view);
            }
        });
        showPass.setText(SHOW_PASS);
        isShown = false;
    }

    private void showPassClicked(View view) {
        Button btn = (Button) view;
        if (isShown){
            isShown = false;
            btn.setText(SHOW_PASS);
            passwordText.setTransformationMethod(new PasswordTransformationMethod());
        }else {
            isShown = true;
            passwordText.setTransformationMethod(null);
            btn.setText(HIDE_PASS);
        }
    }


    public void onSignUpClickTwo(View view) {
        String name, username, password, confirmPassword, email, phone;

        name = nameText.getText().toString();
        username = userNameText.getText().toString();
        password = passwordText.getText().toString();
        confirmPassword = confirmPassText.getText().toString();
        email = emailText.getText().toString();
        phone = phoneText.getText().toString();

        if (!validation(name, username, password, confirmPassword, email, phone)){
            return;
        }

        //// TODO: add acc to DB
    }

    private boolean validation(String name, String username, String password, String confirmPassword, String email, String phone) {

        if (!checkPassword(password, confirmPassword) || !checkUsername(username)) {
            reset();
            return false;
        }

        return true;
    }

    private void reset() {
        passwordText.setText("");
        confirmPassText.setText("");
    }

    private boolean checkPassword(String password, String confirm) {
        if (password.length() < PasswordHelper.PASSWORD_MIN_LENGTH) {
            Toast.makeText(this, "Password is must be at least 6 characters", Toast.LENGTH_LONG).show();
            return false;
        } else if (!password.equals(confirm)) {
            Toast.makeText(this, "Passwords does not match", Toast.LENGTH_LONG).show();
            return false;
        }
        return true;
    }

    private boolean checkUsername(String username) {
        //// TODO: check DB if name is already taken
        return true;
    }


}