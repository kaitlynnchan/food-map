package com.foodmap.app.model.database;

import static android.content.ContentValues.TAG;

import android.util.Log;

import androidx.annotation.NonNull;

import com.foodmap.app.model.List;
import com.foodmap.app.model.ListsManager;
import com.foodmap.app.model.PinnedLocation;
import com.foodmap.app.model.User;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestoreSettings;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import com.google.firebase.firestore.DocumentSnapshot;

import java.util.HashMap;
import java.util.Map;

public class Firestore {
    private FirebaseFirestore db;
    private String userID;

    public interface FirestoreCallback {
        void isUserExist(boolean exist);
    }

    public Firestore(String userID){
        this.userID = userID;
        db = FirebaseFirestore.getInstance();
    }

    public Firestore(String userID, FirebaseFirestore db){
        this.userID = userID;
        this.db = db;
    }

    public void addNewUserCollection(User user){
        // Add a new document with a auth0 ID
        db.collection("users")
                .document(userID)
                .set(user)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void unused) {
                        // success
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {

                    }
                });
    }

    public void getUserCollection(User user){
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
    }

    public void doesUserExist(FirestoreCallback callback){
        db.collection("users")
                .document(userID)
                .get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                        if (task.isSuccessful()){
                            callback.isUserExist(true);
                        }else{
                            callback.isUserExist(false);
                        }
                    }
                });
    }

    public void addList(List list){
        Map<String, Object> listData = new HashMap<>();
        listData.put("listID", list.getListId());
        listData.put("name", list.getName());
        listData.put("description", list.getDescription());
        listData.put("color", list.getColorIndex());

        db.collection("users")
                .document(userID)
                .collection("lists")
                .document(list.getListId())
                .set(listData);
//        db.collection("users/" + userID + "/lists")
//                .document(list.getListId())
//                .set(listData);
    }

//    public ListsManager getListCollection(){
//        ListsManager lists = new ListsManager();
//        db.collection("users")
//                .document(userID)
//                .collection("lists")
//                .get()
//                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
//                    @Override
//                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
//                        if (task.isSuccessful()) {
//                            for (QueryDocumentSnapshot document : task.getResult()) {
//                                Log.d(TAG, document.getId() + " => " + document.getData());
//                            }
//                        } else {
//                            Log.d(TAG, "Error getting documents: ", task.getException());
//                        }
//                    }
//                });
//        return lists;
//    }

    public void addPin(String listID, PinnedLocation location){
        db.collection("users")
                .document(userID)
                .collection("lists")
                .document(listID)
                .collection("pins")
                .add(location)
                .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                    @Override
                    public void onSuccess(DocumentReference documentReference) {

                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {

                    }
                });
    }
//
//    public ListsManager getListPinCollection(String listID){
//        ListsManager lists = new ListsManager();
//        db.collection("users")
//                .document(userID)
//                .collection("lists")
//                .document(listID)
//                .collection("pins")
//                .get()
//                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
//                    @Override
//                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
//                        if (task.isSuccessful()) {
//                            for (QueryDocumentSnapshot document : task.getResult()) {
//                                Log.d(TAG, document.getId() + " => " + document.getData());
//                            }
//                        } else {
//                            Log.d(TAG, "Error getting documents: ", task.getException());
//                        }
//                    }
//                });
//        return lists;
//    }

}
