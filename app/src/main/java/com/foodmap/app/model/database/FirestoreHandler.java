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
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import com.google.firebase.firestore.DocumentSnapshot;

import java.util.HashMap;
import java.util.Map;

/**
 * Firestore Handler Class
 * Handles calls to Firebase Firestore database.
 */
public class FirestoreHandler {

    private FirebaseFirestore db;
    private String userID;

    /**
     * Callback methods
     */
    public interface FirestoreCallback {
        void isUserExist(boolean exist);
    }

    public interface FirestoreListCallback {
        ListsManager getLists(ListsManager lists);
    }

    /**
     * Constructors
     */
    public FirestoreHandler(String userID){
        this.userID = userID;
        db = FirebaseFirestore.getInstance();
    }

    public FirestoreHandler(String userID, FirebaseFirestore db){
        this.userID = userID;
        this.db = db;
    }

    /**
     * Get/modify user data from firestore
     */
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

    /**
     * Get/modify list data from firestore
     */
    public void addList(List list){
        Map<String, Object> listData = new HashMap<>();
        listData.put("id", list.getId());
        listData.put("name", list.getName());
        listData.put("description", list.getDescription());
        listData.put("color", list.getColorIndex());

        db.collection("users/" + userID + "/lists")
                .document(list.getId())
                .set(listData);
    }

    public ListsManager getListCollection(FirestoreListCallback callback){
        ListsManager lists = ListsManager.getInstance();
        db.collection("users/" + userID + "/lists")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                Log.d(TAG, document.getId() + " => " + document.getData());
                                Map<String, Object> data = document.getData();
                                lists.configureList(new List(
                                        data.get("id").toString(),
                                        data.get("name").toString(),
                                        data.get("description").toString(),
                                        Integer.parseInt(data.get("color").toString())
                                ));
                            }
                            callback.getLists(lists);
                        } else {
                            Log.d(TAG, "Error getting documents: ", task.getException());
                            callback.getLists(null);
                        }
                    }
                });
        return lists;
    }

    /**
     * Get/modify pin data from firestore
     */
    public void addPin(String id, PinnedLocation location){
        db.collection("users")
                .document(userID)
                .collection("lists")
                .document(id)
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
//    public ListsManager getListPinCollection(String id){
//        ListsManager lists = new ListsManager();
//        db.collection("users")
//                .document(userID)
//                .collection("lists")
//                .document(id)
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