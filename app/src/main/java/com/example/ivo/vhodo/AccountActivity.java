package com.example.ivo.vhodo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.example.ivo.vhodo.tools.DBHelper;

public class AccountActivity extends AppCompatActivity {
    private boolean isLandlord;
    private DBHelper database;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account);

        //Checking for admin/landlord account
        //TODO get the user type to determine the accesability
        isLandlord = isSuperUser(1); // get userType from the database.
        if (isLandlord) {
            landlordVisible();
        }
    }

    private boolean isSuperUser(int usertype) {
        if (usertype == 1) {
            return true;
        } else {
            return false;
        }
    }

    private void landlordVisible() {
        Button button = (Button) findViewById(R.id.landlord_button);
        button.setVisibility(Button.VISIBLE);
    }

    public void onAccountButtonClicked(View view) {
        switch (view.getId()) {
            case R.id.debts_button:
                //TODO get the user debts.
                break;
            case R.id.payments_button:
                //TODO get the user payments
                break;
            case R.id.balance_button:
                //TODO get the user balance
                break;
            case R.id.logout_button:
                //TODO log out the user.
                break;
            case R.id.landlord_button:
                //TODO expand this activity with landlord funtionalities on another activity
                break;
        }
    }
}
