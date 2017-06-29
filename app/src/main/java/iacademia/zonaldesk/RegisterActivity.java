package iacademia.zonaldesk;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.concurrent.ExecutionException;

import iacademia.zonaldesk.Fragments.NewRequestFragment;

public class RegisterActivity extends AppCompatActivity {

    Button registerButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        registerButton = (Button)findViewById(R.id.registration_submit);

       registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
        String res="";
                try {
                    res=updateUser();
                } catch (ExecutionException e) {
                    e.printStackTrace();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                if(res.equals("null[]")){
                    SharedPreferences p= PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
                    p.edit().putBoolean("VERIFICATION_STATUS",true).apply();
                    Intent s = new Intent(RegisterActivity.this,HomeActivity.class);
                    startActivity(s);
                }else{
                    Toast.makeText(getBaseContext(),"Registration Failed"+res,Toast.LENGTH_LONG).show();
                }

            }
        });

    }


    private String updateUser() throws ExecutionException, InterruptedException {
        EditText name = (EditText) findViewById(R.id.user_name);
        String _name=name.getText().toString();
        EditText password = (EditText) findViewById(R.id.password);
        String _password=password.getText().toString();
        EditText email = (EditText) findViewById(R.id.email);
        String _email=email.getText().toString();
        EditText locality = (EditText) findViewById(R.id.address);
        String _locality=locality.getText().toString();
        EditText pincode = (EditText) findViewById(R.id.pincode);
        String _pincode=pincode.getText().toString();
        EditText state = (EditText) findViewById(R.id.state);
        String _state=state.getText().toString();
        SharedPreferences p= PreferenceManager.getDefaultSharedPreferences(getBaseContext());
        String _phone = p.getString("Phone","");

        String query="{\"type\":\"insert\",\"into\":\"user\",\"columns\":\"name,mobile_no,email,password,address,pincode,state,country \",\"values\":\""+"'"+_name+"'"+","+"'"+_phone+"'"+","+"'"+_email+"'"+","+"'"+_password+"'"+","+"'"+_locality+"'"+","+_pincode+","+"'"+_state+"'"+","+"'"+"India"+"'"+"\"}";

        String res=new PostTask().execute(query).get();

        return res;
    }
}
