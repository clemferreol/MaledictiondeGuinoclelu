package fr.supinternet.maledictiondeguinoclelu;

import android.net.Uri;
import android.support.annotation.NonNull;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.Key;
import com.bumptech.glide.request.target.Target;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.squareup.picasso.Picasso;

import java.io.InputStream;


/**
 * Created by clementineferreol on 29/09/2017.
 */

public class FirebaseUtils {

    public static void signup(String name, final String email, String password, final OnCompleteListener listener) {

        final User user = new User(email, name);
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

    @NonNull
    private static String getUid() {
        FirebaseUser currentUser = FirebaseAuth.getInstance().getCurrentUser();
        return currentUser == null ? null : currentUser.getUid();
    }

    public static DatabaseReference getRef(){
        final FirebaseDatabase database = FirebaseDatabase.getInstance();
        return database.getReference();
    }


    private static void retrieveUsername(ValueEventListener listener){
        String uid = getUid();
        getRef().child("users").child(uid).child("name").addListenerForSingleValueEvent(listener);
    }

    public static void login(String email, String password, OnCompleteListener<AuthResult> listener) {
        FirebaseAuth.getInstance().signInWithEmailAndPassword(email, password).addOnCompleteListener(listener);
    }

    public static void getProfile(ValueEventListener listener) {
        getRef().child("users").child(getUid()).addListenerForSingleValueEvent(listener);
    }

}
