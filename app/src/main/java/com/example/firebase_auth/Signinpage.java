package com.example.firebase_auth;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class Signinpage extends AppCompatActivity implements View.OnClickListener, OnCompleteListener<AuthResult> {
   Button signinbtn;
   EditText email,pass;
    private FirebaseAuth mAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signinpage);
        signinbtn=(Button)findViewById(R.id.button2);
        mAuth = FirebaseAuth.getInstance();
        email=(EditText)findViewById(R.id.editText3);
        pass=(EditText)findViewById(R.id.editText4);
        signinbtn.setOnClickListener(this);
    }


    @Override
    public void onClick(View v) {
       if(v.getId()==R.id.button2)
       {
           siginuser();




       }

    }

    void siginuser()
    {   String emailid=email.getText().toString().trim();
        String password=pass.getText().toString().trim();


        if(emailid.isEmpty())
        {
            email.setError("Email should not be empty");
            email.requestFocus();
            return;
        }

        if(!Patterns.EMAIL_ADDRESS.matcher(emailid).matches())
        {
            email.setError("Enter a valid email id");
            email.requestFocus();
            return;
        }


        if(password.length()<8)
        {
            pass.setError("Enter a valid password");
            pass.requestFocus();
            return;
        }

        if(password.isEmpty())
        {
            pass.setError("Password should not be empty");
            pass.requestFocus();
            return;
        }

        mAuth.signInWithEmailAndPassword(emailid,password).addOnCompleteListener(this);





    }

    @Override
    public void onComplete(@NonNull Task<AuthResult> task) {
        if(task.isSuccessful())
        {   finish();
            Intent i = new Intent(this,profileactivity.class);

            startActivity(i);




        }
        else
        {
            Toast.makeText(this, task.getException().toString(), Toast.LENGTH_SHORT).show();
        }
    }
}
