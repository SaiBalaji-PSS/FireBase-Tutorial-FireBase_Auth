package com.example.firebase_auth;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;

public class profileactivity extends AppCompatActivity {
    private FirebaseAuth mAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profileactivity);
        mAuth = FirebaseAuth.getInstance();
        AlertDialog.Builder  builder = new AlertDialog.Builder(this);
        builder.setMessage("Welcome::"+mAuth.getCurrentUser().getEmail());
        builder.setPositiveButton("Ok",null);
        builder.setIcon(R.drawable.loginlogo);
        builder.setTitle("Login success");

        AlertDialog dialog = builder.create();
        dialog.show();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
         getMenuInflater().inflate(R.menu.optionmenu,menu);

        return true;

    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        switch (item.getItemId())
        {

            case R.id.logout:
            {
                FirebaseAuth.getInstance().signOut();
                finish();
                Intent i2 = new Intent(this,MainActivity.class);
                startActivity(i2);
                break;

            }
            case R.id.github:
            {
                Intent i2 = new Intent(this,githubactivity.class);
                startActivity(i2);
                break;
            }
        }
        return true;
    }
}
