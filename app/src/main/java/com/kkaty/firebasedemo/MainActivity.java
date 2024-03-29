package com.kkaty.firebasedemo;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseUser;

public class MainActivity extends AppCompatActivity implements UserAuthenticator.Callback{

    private EditText etUserEmail;
    private EditText etPassword;
    private UserAuthenticator userAuthenticator;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etUserEmail=findViewById(R.id.etUserEmail);
        etPassword=findViewById(R.id.etPassword);

        userAuthenticator = new UserAuthenticator(this, new CompleteListerner(this));
    }


    @Override
    public void onUserValidated(FirebaseUser user) {

        Toast.makeText(this,"Welcome"+user.getEmail(),Toast.LENGTH_SHORT).show();
        startPeopleActivity();


    }

    @Override
    public void onUserInvalidated() {
        Toast.makeText(this,"Failure",Toast.LENGTH_SHORT).show();

    }

    public void onSignIn(View view) {

        userAuthenticator.signIn(etUserEmail.getText().toString(),
                etPassword.getText().toString());

    }

    public void onSignUp(View view) {

        userAuthenticator.signUp(etUserEmail.getText().toString(),
                etPassword.getText().toString());

    }

    @Override
    protected void onStart(){
        super.onStart();


        if(userAuthenticator.checkSession()){
            startPeopleActivity();
        }
    }

    private void startPeopleActivity (){
        Intent intent = new Intent(getApplicationContext(),PeopleActivity.class);
        startActivity(intent);
    }
}
