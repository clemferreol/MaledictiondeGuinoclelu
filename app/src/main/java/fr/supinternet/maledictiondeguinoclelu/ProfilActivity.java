package fr.supinternet.maledictiondeguinoclelu;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;

public class ProfilActivity extends AppCompatActivity {

    private FirebaseAuth.AuthStateListener authListener;
    private FirebaseAuth auth;
   // private ImageView imageView;
    private TextView email;
    private TextView name;
    private View btnLogOut;
    private TextView userId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profil);

        name = (TextView) findViewById(R.id.displayed_name);
        email = (TextView) findViewById(R.id.email_field);
        btnLogOut = findViewById(R.id.logout);
        userId = (TextView) findViewById(R.id.user_id);

        //get firebase auth instance
        auth = FirebaseAuth.getInstance();

        //get current user
        setDataToView();

        //add a auth listener
        authListener = new FirebaseAuth.AuthStateListener() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                Log.d("MainActivity", "onAuthStateChanged");
                FirebaseUser user = firebaseAuth.getCurrentUser();
                if (user != null) {
                    setDataToView();
                } else {
                    //user auth state is not existed or closed, return to Login activity
                    startActivity(new Intent(ProfilActivity.this, MainActivity.class));
                    finish();
                }
            }
        };

        //Signing out
        btnLogOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                auth.signOut();
            }
        });
    }

    @SuppressLint("SetTextI18n")
    private void setDataToView() {
        getProfile();
    }

    @Override
    public void onStart() {
        super.onStart();
        auth.addAuthStateListener(authListener);
    }

    @Override
    public void onStop() {
        super.onStop();
        if (authListener != null) {
            auth.removeAuthStateListener(authListener);
        }
    }

    private void getProfile() {
        FirebaseUtils.getProfile(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                User u = dataSnapshot.getValue(User.class);
                email.setText("User Email: " + u.getEmail());
                name.setText("User name: " + u.getName());
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }
}

