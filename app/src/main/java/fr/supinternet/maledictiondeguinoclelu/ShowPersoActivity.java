package fr.supinternet.maledictiondeguinoclelu;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;

public class ShowPersoActivity extends AppCompatActivity {

    protected String username;
    //protected String avatar;
    protected String gender;
    protected TextView tvUsername;
    protected TextView tvGender;
    protected TextView tvRace;
    protected String race;
    private View loading;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_perso);
        loading = findViewById(R.id.loading);
        tvUsername = (TextView) findViewById(R.id.profil_username);
        tvGender = (TextView) findViewById(R.id.profil_gender);
        tvRace = (TextView) findViewById(R.id.profil_race);
        setDataToView();
    }

    @SuppressLint("SetTextI18n")
    private void setDataToView() {
        getProfile();
    }


    private void getProfile() {
        FirebaseUtils.getProfile(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                User u = dataSnapshot.getValue(User.class);
                tvUsername.setText(u.getUsername());
                tvGender.setText(u.getGender());
                tvRace.setText(u.getRace());
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

    private void showLoading(boolean show) {
        loading.setVisibility(show ? View.VISIBLE : View.GONE);
    }
}
