package fr.supinternet.maledictiondeguinoclelu;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.analytics.FirebaseAnalytics;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.io.InputStream;


public class UpdatePersoActivity extends AppCompatActivity {

    private DatabaseReference mDatabase;
    private View loading;
    private String username;
    private InputStream avatar;
    private String gender;
    private String race;
    private Button btUpdate;
    private EditText etUsername;
    private RadioGroup rgGender;
    private RadioGroup rgRace;
    private String radioGenderValue;
    private String radioRaceValue;
    private User user;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getProfile();
        mDatabase = FirebaseDatabase.getInstance().getReference();
        setContentView(R.layout.activity_update_perso);

        loading = findViewById(R.id.loading);
        etUsername = (EditText) findViewById(R.id.etUpdateUsername);
        rgGender = (RadioGroup)findViewById(R.id.radioGender);
        rgRace = (RadioGroup)findViewById(R.id.radioRace);
        //avatar = (InputStream) findViewById(R.id.isavatar);

        btUpdate = (Button) findViewById(R.id.btUpdate);

        btUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    /*if (checkIfEditTextEmpty(etUsername, "Specify a username")) return;
                    if (checkIfEditTextEmpty(etAvatar, "Specify an avatar")) return;
                    if (checkIfEditTextEmpty(etRace, "Specify a race")) return;
                    if (checkIfEditTextEmpty(etgender, "Specify a gender")) return;*/

                updatePerso(etUsername.getText().toString(),
                        //etAvatar.getText().toString(),
                        ((RadioButton)findViewById(rgGender.getCheckedRadioButtonId())).getText().toString(),
                        ((RadioButton)findViewById(rgRace.getCheckedRadioButtonId())).getText().toString());

                Bundle bundle = new Bundle();
                bundle.putString(FirebaseAnalytics.Param.ITEM_NAME, "EVENTS_LOGIN_PAGE");
                bundle.putString("ENTRY_POINT", "Create");
                //mFirebaseAnalytics.logEvent(FirebaseAnalytics.Event.SELECT_CONTENT, bundle);
            }
        });

    }


    private void updatePerso(String username, /*InputStream avatar,*/ String gender, String race) {
        showLoading(true);
        FirebaseUtils.updatePerso(user, username, /*avatar,*/ gender, race, new OnCompleteListener() {
            @Override
            public void onComplete(@NonNull Task task) {
                if (task.isSuccessful()) {
                    goToPersoShowActivity();
                } else {
                    Toast.makeText(getBaseContext(), task.getException().getLocalizedMessage(), Toast.LENGTH_SHORT).show();
                    showLoading(false);
                }
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
                user = u;
                username = u.getUsername();
                //avatar = u.getAvatar();
                gender = u.getGender();
                race = u.getRace();
                if(username != null && gender != null && race != null){
                    updateViews();
                }

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

    private void updateViews() {

        etUsername.setText(username);

        int count = rgGender.getChildCount();
        for (int i=0;i<count;i++) {
            RadioButton o = (RadioButton) rgGender.getChildAt(i);
            if (o.getId() == R.id.gender_male) {
                o.setChecked(gender.equals("Male"));
            }
            if (o.getId() == R.id.gender_female) {
                o.setChecked(gender.equals("Female"));
            }
        }
        count = rgRace.getChildCount();
        for (int i=0;i<count;i++) {
            RadioButton o = (RadioButton) rgRace.getChildAt(i);
            if (o.getId() == R.id.race_humain) {
                o.setChecked(race.equals("Human"));
            }
            if (o.getId() == R.id.race_orc) {
                o.setChecked(race.equals("Orc"));
            }
            if (o.getId() == R.id.race_elfe) {
                o.setChecked(race.equals("Elfe"));
            }
            if (o.getId() == R.id.race_dwarf) {
                o.setChecked(race.equals("Dwarf"));
            }
        }

    }

    private void showLoading(boolean show) {
        loading.setVisibility(show ? View.VISIBLE : View.GONE);
    }

    private void goToPersoShowActivity() {
        showLoading(false);
        Intent intent = new Intent(getBaseContext(), ShowPersoActivity.class);
        startActivity(intent);
    }
}
