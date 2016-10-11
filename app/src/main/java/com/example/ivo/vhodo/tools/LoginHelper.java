package com.example.ivo.vhodo.tools;

import com.example.ivo.vhodo.GlobalData;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;

/**
 * Created by Ivo on 11.10.2016 г..
 */
public class LoginHelper {

    public static Status checkForValidLogin(String username, String password){
        String toastMsg = "Login successful";
        boolean isShallPass = true;

        if (username.length() < 5 && password.length() < PasswordHelper.PASSWORD_MIN_LENGTH){
            toastMsg = "Please,enter a valid username and password";
            isShallPass = false;
        }else if (username.length() < 5 || !GlobalData.isUserExisting(username)){
            toastMsg = "Please,enter a valid username";
            isShallPass = false;
        }else if (password.length() < 6){
            toastMsg = "Please,enter a valid password";
            isShallPass = false;
        }

        try {
            if (!PasswordHelper.md5get(password).equals(GlobalData.getUserPass(username))){
                isShallPass = false;
                toastMsg  = "Password is not valid";
            }
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }


        return new Status(toastMsg,isShallPass);
    }
}
