package fr.supinternet.maledictiondeguinoclelu;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.Settings;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.analytics.FirebaseAnalytics;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.InputStream;


public class PersoActivity extends AppCompatActivity {

    private DatabaseReference mDatabase;
    private View loading;
    private String perso;
    private String username;
    private InputStream avatar;
    private String gender;
    private String race;
    private Button btCreate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getProfile();
        mDatabase = FirebaseDatabase.getInstance().getReference();
        if(perso != null) {
            setContentView(R.layout.activity_perso);

            TextView usernameValue = (TextView) findViewById(R.id.etCreateUsername);

            //gender
            RadioGroup rgGender = (RadioGroup)findViewById(R.id.radioGender);
            String radioGenderValue = ((RadioButton)findViewById(rgGender.getCheckedRadioButtonId())).getText().toString();

            //race
            RadioGroup rgRace = (RadioGroup)findViewById(R.id.radioRace);
            String radioRaceValue = ((RadioButton)findViewById(rgRace.getCheckedRadioButtonId())).getText().toString();

            //avatar = (InputStream) findViewById(R.id.isavatar);
            //setDataToView();
        }else{
            setContentView(R.layout.activity_create_perso);
            /*
            btCreate = (Button) findViewById(R.id.btCreate);

            btCreate.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (checkIfEditTextEmpty(etUsername, "Specify a username")) return;
                    if (checkIfEditTextEmpty(etAvatar, "Specify an avatar")) return;
                    if (checkIfEditTextEmpty(etRace, "Specify a race")) return;
                    if (checkIfEditTextEmpty(etgender, "Specify a gender")) return;

                    createPerso(etUsername.getText().toString(),
                            etAvatar.getText().toString(),
                            etRace.getText().toString()),
                            etgender.getText().toString()),
                            ;

                    Bundle bundle = new Bundle();
                    bundle.putString(FirebaseAnalytics.Param.ITEM_NAME, "EVENTS_LOGIN_PAGE");
                    bundle.putString("ENTRY_POINT", "Create");
                    mFirebaseAnalytics.logEvent(FirebaseAnalytics.Event.SELECT_CONTENT, bundle);
                }
            });*/

        }
    }

    private void createPerso(String username, InputStream avatar, String gender, String race) {
        showLoading(true);
        FirebaseUtils.createPerso(username, avatar, gender, race, new OnCompleteListener() {
            @Override
            public void onComplete(@NonNull Task task) {
                if (task.isSuccessful()) {
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
                perso = u.getUsername() != null ? u.getUsername() : null;
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
