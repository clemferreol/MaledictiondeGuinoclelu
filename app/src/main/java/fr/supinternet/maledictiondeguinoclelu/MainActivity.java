package fr.supinternet.maledictiondeguinoclelu;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseApp;
import com.google.firebase.analytics.FirebaseAnalytics;
import com.google.firebase.auth.AuthResult;

public class MainActivity extends AppCompatActivity {

    private FirebaseAnalytics mFirebaseAnalytics;
    private static final String TAG = "LoginActivity";

    private EditText etSignupName;
    private EditText etSignupEmail;
    private EditText etSignupPassword;

    private EditText etLoginEmail;
    private EditText etLoginPassword;

    private Button btLogin;
    private Button btSignup;

    private View loading;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mFirebaseAnalytics = FirebaseAnalytics.getInstance(this);

        FirebaseApp.initializeApp(this);

        findViews();
        setListeners();



    }

    private void findViews() {

        etSignupName = (EditText) findViewById(R.id.etSignupName);
        etSignupEmail = (EditText) findViewById(R.id.etSignupEmail);
        etSignupPassword = (EditText) findViewById(R.id.etSignupPassword);

        btSignup = (Button) findViewById(R.id.btSignup);

        etLoginEmail = (EditText) findViewById(R.id.etLoginEmail);
        etLoginPassword = (EditText) findViewById(R.id.etLoginPassword);

        btLogin = (Button) findViewById(R.id.btLogin);

        loading = findViewById(R.id.loading);
    }

    private void setListeners() {
        btLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (checkIfEditTextEmpty(etLoginEmail, "Specify an email")) return;
                if (checkIfEditTextEmpty(etLoginPassword, "Specify a password")) return;

                login(etLoginEmail.getText().toString(),
                        etLoginPassword.getText().toString());

                Bundle bundle = new Bundle();
                bundle.putString(FirebaseAnalytics.Param.ITEM_NAME, "EVENTS_LOGIN_PAGE");
                bundle.putString("ENTRY_POINT", "SIGNIN");
                mFirebaseAnalytics.logEvent(FirebaseAnalytics.Event.SELECT_CONTENT, bundle);

            }
        });

        btSignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (checkIfEditTextEmpty(etSignupName, "Specify a name")) return;
                if (checkIfEditTextEmpty(etSignupEmail, "Specify an email")) return;
                if (checkIfEditTextEmpty(etSignupPassword, "Specify a password")) return;

                signup(etSignupName.getText().toString(),
                        etSignupEmail.getText().toString(),
                        etSignupPassword.getText().toString());

                Bundle bundle = new Bundle();
                bundle.putString(FirebaseAnalytics.Param.ITEM_NAME, "EVENTS_LOGIN_PAGE");
                bundle.putString("ENTRY_POINT", "SIGNUP");
                bundle.putString("EMAIL_LENGTH", etSignupPassword.length() + "");
                bundle.putString("PASSWORD_LENGTH", etSignupPassword.length() + "");
                mFirebaseAnalytics.logEvent(FirebaseAnalytics.Event.SELECT_CONTENT, bundle);

            }
        });
    }

    private boolean checkIfEditTextEmpty(EditText editText, String toastText) {
        String text = editText.getText().toString();
        if (text.isEmpty()) {
            Toast.makeText(MainActivity.this, toastText, Toast.LENGTH_SHORT).show();
            return true;
        }
        return false;
    }

    private void login(String email, String password) {
        showLoading(true);
        FirebaseUtils.login(email, password, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()) {
                    goToMessagesActivity();
                } else {
                    Toast.makeText(getBaseContext(), task.getException().getLocalizedMessage(), Toast.LENGTH_SHORT).show();
                    showLoading(false);
                }
            }
        });
    }

    private void signup(String name, String email, String password) {
        showLoading(true);
        FirebaseUtils.signup(name, email, password, new OnCompleteListener() {
            @Override
            public void onComplete(@NonNull Task task) {
                if (task.isSuccessful()) {
                    goToMessagesActivity();
                } else {
                    Toast.makeText(getBaseContext(), task.getException().getLocalizedMessage(), Toast.LENGTH_SHORT).show();
                    showLoading(false);
                }
            }
        });
    }

    private void showLoading(boolean show) {

        loading.setVisibility(show ? View.VISIBLE : View.GONE);

    }

    private void goToMessagesActivity() {
        showLoading(false);
        Intent intent = new Intent(getBaseContext(), ProfilActivity.class);
        startActivity(intent);
    }
}
