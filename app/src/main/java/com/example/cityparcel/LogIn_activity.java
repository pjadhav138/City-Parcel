package com.example.cityparcel;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Display;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.GoogleAuthProvider;

public class LogIn_activity extends AppCompatActivity {

    GoogleSignInClient googleSignInClient;
    SignInButton Google_login;
    FirebaseAuth firebaseAuth;
    private String TAG = getClass().getSimpleName();
    Button Get_otp;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_in);
        Google_login = findViewById(R.id.google_login);
        Get_otp = findViewById(R.id.Get_otp);

        Get_otp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(LogIn_activity.this,Verify_otp.class));
            }
        });


        // Configure Google Sign In
        GoogleSignInOptions gso = new GoogleSignInOptions
                .Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken("919095757829-8itqfrpmaimi48j07qeja1k4138kchba.apps.googleusercontent.com")
                .requestEmail()
                .build();

        googleSignInClient = GoogleSignIn.getClient(LogIn_activity.this, gso);

        Google_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = googleSignInClient.getSignInIntent();
                startActivityForResult(intent, 100);
            }
        });

        firebaseAuth = FirebaseAuth.getInstance();
    }

    @Override
    protected void onStart() {
        super.onStart();
        GoogleSignInAccount account = GoogleSignIn.getLastSignedInAccount(this);
        if (account == null) {
            Toast.makeText(this, "User Is Not Signed In", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "User Is Signed In", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == 100) {
            Task<GoogleSignInAccount> signInAccountTask = GoogleSignIn.getSignedInAccountFromIntent(data);

            try {
                if (signInAccountTask.isSuccessful()) {
                    Toast.makeText(LogIn_activity.this, "Google LogIn Successfull .", Toast.LENGTH_SHORT).show();

                    try {
                        GoogleSignInAccount googleSignInAccount = signInAccountTask.getResult(ApiException.class);

                        if (googleSignInAccount != null) {
                            AuthCredential authCredential = GoogleAuthProvider.getCredential(googleSignInAccount.getIdToken(), null);

                            firebaseAuth.signInWithCredential(authCredential).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task) {
                                    if (task.isSuccessful()) {
                                        Log.e(TAG, "onComplete: login success ");
                                        startActivity(new Intent(LogIn_activity.this,Profile_Activity.class).setFlags(Intent.FLAG_ACTIVITY_NEW_TASK));

                                    }

                                }
                            });

                        }

                    } catch (ApiException e) {
                        e.printStackTrace();
                    }

                }

            } catch (Exception e) {
                Log.e(TAG, "onActivityResult: "+e.getMessage() );
            }
        }
    }

}