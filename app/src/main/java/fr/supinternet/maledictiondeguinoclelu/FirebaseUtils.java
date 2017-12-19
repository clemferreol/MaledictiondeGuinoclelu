package fr.supinternet.maledictiondeguinoclelu;

import android.net.Uri;
import android.support.annotation.NonNull;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.InputStream;

/**
 * Created by clementineferreol on 29/09/2017.
 */

public class FirebaseUtils {

    public static void signup(String name, final String email, String password, final OnCompleteListener listener) {

        final User user = new User();
        user.setEmail(email);
        user.setName(name);

        FirebaseAuth.getInstance().createUserWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                getRef().child("users").child(getUid()).setValue(user);
                listener.onComplete(task);
            }
        });

    }

    public static void createPerso(String username, /*final InputStream avatar,*/ String gender, String race, final OnCompleteListener listener) {
            final User user = new User();
            user.setUsername(username);
            user.setGender(gender);
            user.setRace(race);
            String key = getRef().child("users").push().getKey();
            //sendAvatarStream(avatar, key);

            getRef().child("users").child(getUid()).setValue(user).addOnCompleteListener(new OnCompleteListener<Void>() {
                @Override
                public void onComplete(@NonNull Task<Void> task) {
                    getRef().child("users").child(getUid()).setValue(user);
                    listener.onComplete(task);
                }
            });

    }

    public static void updatePerso(User u, String username, /*final InputStream avatar,*/ String gender, String race, final OnCompleteListener listener) {
            final User user = new User();
            user.setName(u.name);
            user.setEmail(u.email);
            user.setUsername(username);
            user.setGender(gender);
            user.setRace(race);
            String key = getRef().child("users").push().getKey();
            //sendAvatarStream(avatar, key);

            getRef().child("users").child(getUid()).setValue(user).addOnCompleteListener(new OnCompleteListener<Void>() {
                @Override
                public void onComplete(@NonNull Task<Void> task) {
                    getRef().child("users").child(getUid()).setValue(user);
                    listener.onComplete(task);
                }
            });

    }

    private static void sendAvatarStream(InputStream image, final String key){
        StorageReference imageref = FirebaseStorage.getInstance().getReference().child("images/" + key + ".jpg");

        imageref.putStream(image).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                Uri url = taskSnapshot.getDownloadUrl();
                getRef().child("users").child(key).child("avatar").setValue(url.toString());
            }
        });
    }

    @NonNull
    private static String getUid() {
        FirebaseUser currentUser = FirebaseAuth.getInstance().getCurrentUser();
        return currentUser == null ? null : currentUser.getUid();
    }

    public static DatabaseReference getRef(){
        final FirebaseDatabase database = FirebaseDatabase.getInstance();
        return database.getReference();
    }

    public static void login(String email, String password, OnCompleteListener<AuthResult> listener) {
        FirebaseAuth.getInstance().signInWithEmailAndPassword(email, password).addOnCompleteListener(listener);
    }

    public static void getProfile(ValueEventListener listener) {
        getRef().child("users").child(getUid()).addListenerForSingleValueEvent(listener);
    }

}
