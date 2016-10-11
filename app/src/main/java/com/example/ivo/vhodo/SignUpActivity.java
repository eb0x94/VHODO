package com.example.ivo.vhodo;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.method.PasswordTransformationMethod;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import com.example.ivo.vhodo.tools.PasswordHelper;
import com.example.ivo.vhodo.tools.DBHelper;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;

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

    //// TODO: eventually redesign the showPass Button...


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
            confirmPassText.setTransformationMethod(new PasswordTransformationMethod());
        }else {
            isShown = true;
            passwordText.setTransformationMethod(null);
            confirmPassText.setTransformationMethod(null);
            btn.setText(HIDE_PASS);
        }
    }


    public void onSignUpClickTwo(View view) throws UnsupportedEncodingException, NoSuchAlgorithmException {

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
        GlobalData.addUser(GlobalData.getNumberOfRowsInTable(DBHelper.USERS_TABLE_NAME),
                username,
                PasswordHelper.md5get(password),
                name,
                0);

        Toast.makeText(this,"Success",Toast.LENGTH_LONG).show();
        Intent intent = new Intent(this,LoginActivity.class);
        startActivity(intent);
    }

    private boolean validation(String name, String username, String password, String confirmPassword, String email, String phone) {
        // TODO: 11.10.16 Check for avaiability of the username -- done
        if (GlobalData.isUserExisting(username)){
            return false;
        }
        if (!checkPassword(password, confirmPassword) || !checkUsername(username)) {
            reset();
            return false;
        }
        if (email.length() == 0  || email == null){
            email = "No email";
        }
        if (phone.length() == 0 || phone == null){
            phone = "No phone";
        }
        return true;
    }

    private void reset() {
        passwordText.setText("");
        confirmPassText.setText("");
    }

    private boolean checkPassword(String password, String confirm) {
        if (password.length() < PasswordHelper.PASSWORD_MIN_LENGTH) {
            Toast.makeText(this, "Password must be at least 6 characters", Toast.LENGTH_LONG).show();
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