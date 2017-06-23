package iacademia.zonaldesk;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import iacademia.zonaldesk.Fragments.NewRequestFragment;

public class RegisterActivity extends AppCompatActivity {

    Button registerButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        registerButton = (Button)findViewById(R.id.registration_submit);
       // registerButton.setOnClickListener(this);
       registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SharedPreferences p= PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
                p.edit().putBoolean("VERIFICATION_STATUS",true).apply();
                Intent s = new Intent(RegisterActivity.this,HomeActivity.class);
                startActivity(s);
            }
        });

    }
}
