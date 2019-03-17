package com.kkaty.firebasedemo;

import android.app.Activity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class UserAuthenticator {

    public static final String TAG = UserAuthenticator.class.getSimpleName() + "_TAG";
    FirebaseAuth auth;
    Callback callback;
    Activity activity;
    CompleteListerner completeListener;

    public UserAuthenticator() {
        auth = FirebaseAuth.getInstance();
    }

    public UserAuthenticator(Activity activity, CompleteListerner completeListener) {
        auth = FirebaseAuth.getInstance();
        this.callback = (Callback) activity;
        this.activity = activity;
        this.completeListener = completeListener;
    }

    public void signIn(String userEmail, String password) {
        completeListener.setType(0);
        auth.signInWithEmailAndPassword(userEmail, password)
                .addOnCompleteListener(activity, (OnCompleteListener<AuthResult>) completeListener);
    }

    public void signUp(String userEmail, String password) {
        completeListener.setType(1);
        auth.createUserWithEmailAndPassword(userEmail, password)
                .addOnCompleteListener(activity, (OnCompleteListener<AuthResult>) completeListener);
    }

    public boolean checkSession() {
        FirebaseUser user = auth.getCurrentUser();
        return (user != null);
    }

    public void signOut() {
        auth.signOut();
    }

    interface Callback {


        void onUserValidated(FirebaseUser user);

        void onUserInvalidated();
    }
}
