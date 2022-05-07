package com.example.mayo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.appevents.ml.ModelManager;
import com.facebook.login.LoginManager;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.Task;

import java.util.Arrays;

public class MainActivity extends AppCompatActivity {
Button button;
private LoginButton login;
GoogleSignInOptions gso;
GoogleSignInClient gsc;
ImageView google_btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        google_btn = findViewById(R.id.google_btn);

        gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN).requestEmail().build();
        gsc = GoogleSignIn.getClient(this, gso);

        google_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                signIn();
            }
        });

        button = findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this,MainActivity3.class);
                startActivity(intent);
                Toast.makeText(MainActivity.this, "sign up", Toast.LENGTH_SHORT).show();

            }
        });
    }
    void signIn(){
        Intent signInIntent = gsc.getSignInIntent();
        startActivityForResult(signInIntent, 1000);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);


        LoginManager.getInstance().logInWithReadPermissions(this, Arrays.asList("public_profile"));
        finish();

        Task<GoogleSignInAccount> task = null;
        if (requestCode == 1000) {
        }task = GoogleSignIn.getSignedInAccountFromIntent(data);
        try {
            task.getResult(ApiException.class);
        } catch (ApiException e) {
            e.printStackTrace();
        }

        Toast.makeText(this, "something went wrong", Toast.LENGTH_SHORT).show();
    }
          void navigateToMainActivity2(){
              Intent intent = new Intent(MainActivity.this, MainActivity2.class);
              startActivity(intent);
        }


    }
