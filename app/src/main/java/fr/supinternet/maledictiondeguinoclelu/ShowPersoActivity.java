package fr.supinternet.maledictiondeguinoclelu;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class ShowPersoActivity extends AppCompatActivity {

    protected String username;
    //protected String avatar;
    protected String gender;
    protected TextView tvUsername;
    protected TextView tvGender;
    protected TextView tvRace;
    protected String race;
    private View loading;
    private Button play;
    private Button update;
    private TextView tvInventory;
    private ArrayList<String> inventory;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_perso);
        loading = findViewById(R.id.loading);
        tvUsername = (TextView) findViewById(R.id.profil_username);
        tvGender = (TextView) findViewById(R.id.profil_gender);
        tvRace = (TextView) findViewById(R.id.profil_race);
        tvInventory = (TextView) findViewById(R.id.profil_inventory);
        play = (Button) findViewById(R.id.play);
        update = (Button) findViewById(R.id.update);
        setDataToView();

        play.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent intent = new Intent(ShowPersoActivity.this, GameActivity.class);
                startActivity(intent);
            }
        });

        update.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent intent = new Intent(ShowPersoActivity.this, UpdatePersoActivity.class);
                startActivity(intent);
            }
        });
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
                tvInventory.setText("");
                if(u.getInventory() != null){
                    for (int j = 0; j < u.getInventory().size(); j++){
                        tvInventory.append("\n" + u.getInventory().get(j) + "\n");
                    }
                }else{
                    tvInventory.append("Inventory is empty");
                }
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
