package com.example.ivo.vhodo.tools;

import com.example.ivo.vhodo.GlobalData;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;

/**
 * Created by Ivo on 11.10.2016 г..
 */
public class LoginHelper {
    public static final int USERNAME_MIN_LENGTH = 5;

    public static Status checkForValidLogin(String username, String password) throws UnsupportedEncodingException, NoSuchAlgorithmException {
        String toastMsg = "Login successful";
        boolean isShallPass = true;

        if (username.length() < LoginHelper.USERNAME_MIN_LENGTH && password.length() < PasswordHelper.PASSWORD_MIN_LENGTH){
            toastMsg = "Please,enter a valid username and password";
            isShallPass = false;
        }else if (username.length() < LoginHelper.USERNAME_MIN_LENGTH){
            toastMsg = "Please,enter a valid username";
            isShallPass = false;
        }else if (password.length() < PasswordHelper.PASSWORD_MIN_LENGTH){
            toastMsg = "Please,enter a valid password";
            isShallPass = false;
        }

        if (GlobalData.isUserExisting(username)){
                if (!PasswordHelper.md5get(password).equals(GlobalData.getUserPass(username))){
                    toastMsg = "Password is not correct";
                    isShallPass = false;
                }
        }else {
            toastMsg = "Please, enter a valid username";
            isShallPass = false;
        }

        return new Status(toastMsg,isShallPass);
    }
}
