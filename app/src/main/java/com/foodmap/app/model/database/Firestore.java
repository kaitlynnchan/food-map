package com.foodmap.app.model.database;

import static android.content.ContentValues.TAG;

import android.util.Log;

import androidx.annotation.NonNull;

import com.foodmap.app.model.User;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.Firebase;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import com.google.firebase.firestore.DocumentSnapshot;

import java.util.HashMap;
import java.util.Map;

public class Firestore {
    private FirebaseFirestore db;

    public Firestore(){
        db = FirebaseFirestore.getInstance();

    }

//    public User checkUserExists(String userID){
//        User user = getUserCollection(userID);
//        if (user == null){
//            // create new user
//            // add user
//        } else{
//            return user;
//        }
//    }

    public void addNewUserCollection(User user){
        // Add a new document with a auth0 ID
        db.collection("users")
                .document(user.getId())
                .set(user);

        // initiate default collections for new user
    }

    public User getUserCollection(String userID){
        User user = null;
        db.collection("users")
                .document(userID)
                .get()
                .addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
                    @Override
                    public void onSuccess(DocumentSnapshot documentSnapshot) {
                        User userData = documentSnapshot.toObject(User.class);
                        user.setUser(userData);
                    }
                });
        return user;
    }
}
