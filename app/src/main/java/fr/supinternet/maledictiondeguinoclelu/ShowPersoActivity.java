package fr.supinternet.maledictiondeguinoclelu;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;

import static fr.supinternet.maledictiondeguinoclelu.R.id.loading;

public class ShowPersoActivity extends AppCompatActivity {

    protected String username;
    //protected String avatar;
    protected String gender;
    protected String race;
    private View loading;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_perso);
        loading = findViewById(R.id.loading);
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
                username = u.getUsername();
                //avatar = u.getAvatar();
                gender = u.getGender();
                race = u.getRace();
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
