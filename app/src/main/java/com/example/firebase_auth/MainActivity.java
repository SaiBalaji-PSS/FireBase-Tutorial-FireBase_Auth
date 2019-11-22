package com.example.firebase_auth;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthUserCollisionException;

import java.util.regex.Pattern;

public class MainActivity extends AppCompatActivity implements View.OnClickListener, OnCompleteListener<AuthResult> {
EditText username,password;
TextView olduser;
Button signup;
ProgressBar prog;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        username=(EditText)findViewById(R.id.editText);
        password=(EditText)findViewById(R.id.editText2);
        signup=(Button)findViewById(R.id.button);
        olduser=(TextView)findViewById(R.id.textView3);
        prog=(ProgressBar)findViewById(R.id.progressBar);
        mAuth = FirebaseAuth.getInstance();
        olduser.setOnClickListener(this);
        signup.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        if(v.getId()==R.id.textView3)
        {
            Intent i = new Intent(this,Signinpage.class);
            startActivity(i);
            return;
        }
        registeruser();
    }

    void registeruser()
    {
       String email=username.getText().toString().trim();
       String pass=password.getText().toString().trim();
       if(email.isEmpty())
       {
           username.setError("Email should not be empty");
           username.requestFocus();
           return;
       }
       if(!Patterns.EMAIL_ADDRESS.matcher(email).matches())
       {
           username.setError("Enter a valid email");
           username.requestFocus();
           return;
       }

       if(pass.isEmpty())
       {
           password.setError("Password should not be empty");
           password.requestFocus();
           return;
       }

       if(pass.length()<8)
       {
           password.setError("Minimum length of password should be 8");
           password.requestFocus();
           return;
       }

        prog.setVisibility(View.VISIBLE);
       mAuth.createUserWithEmailAndPassword(email,pass).addOnCompleteListener(this);

    }

    @Override
    public void onComplete(@NonNull Task<AuthResult> task) {

       if(task.isSuccessful())
       {  prog.setVisibility(View.GONE);
           Toast.makeText(this, "Account created successfully", Toast.LENGTH_SHORT).show();
       }
        else if(task.getException() instanceof FirebaseAuthUserCollisionException)
       {   prog.setVisibility(View.GONE);
           Toast.makeText(this, "This user is already registered", Toast.LENGTH_SHORT).show();
       }


    }
}
