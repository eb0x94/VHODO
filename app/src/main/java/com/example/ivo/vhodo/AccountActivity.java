package com.example.ivo.vhodo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

public class AccountActivity extends AppCompatActivity {
    private boolean isLandlord;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account);

        //Checking for admin/landlord account
        isLandlord = true;//for programming sake
        if (isLandlord = true) {
            landlordVisible();
        }
    }

    private void landlordVisible() {
        Button button = (Button) findViewById(R.id.landlord_button);
        button.setVisibility(Button.VISIBLE);
    }

    public void onAccountButtonClicked(View view) {
        switch (view.getId()) {
            case R.id.debts_button:
                break;
            case R.id.payments_button:
                break;
            case R.id.balance_button:
                break;
            case R.id.logout_button:
                break;
            case R.id.landlord_button:
                break;
        }
    }
}
