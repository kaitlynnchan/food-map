package com.foodmap.app.model.database;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyString;

import com.foodmap.app.model.User;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

@Tag("Unit")
class FirestoreTest {

    User newUser;
    FirestoreHandler database;

    @Mock
    FirebaseFirestore mockFirestore;

    @Mock
    CollectionReference mockCollectionReference;

    @Mock
    DocumentReference mockDocumentReference;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        Mockito.when(mockFirestore.collection("users")).thenReturn(mockCollectionReference);
        Mockito.when(mockCollectionReference.document(anyString())).thenReturn(mockDocumentReference);
//
//        TaskCompletionSource<DocumentSnapshot> taskCompletionSource = new TaskCompletionSource<>();
//        taskCompletionSource.setResult(mockDocumentSnapshot);
//        Task<DocumentSnapshot> mockTask = taskCompletionSource.getTask();
//
//        when(mockDocumentReference.get()).thenReturn(mockTask);
//        when(mockDocumentSnapshot.toObject(User.class)).thenReturn(newUser);

        // create user
        String id = "user1";
        String email = "user1@email.com";
        String profilepic = "http://someprofile.com";
        newUser = new User(id, email, profilepic);

        database = new FirestoreHandler(id, mockFirestore);
        database.addNewUserCollection(newUser);
        Mockito.verify(mockFirestore).collection("users").document(id).set(newUser);
    }

    @AfterEach
    void tearDown() {
        // remove user
    }

    void addNewUserCollection() {
        String id = "user1";
        String email = "user1@email.com";
        String profilepic = "http://someprofile.com";
        newUser = new User(id, email, profilepic);

        database = new FirestoreHandler(newUser.getId(), mockFirestore);
        database.addNewUserCollection(newUser);
    }

    @Test
    void getUserCollection() {
//        User result = database.getUserCollection();
//        assertAll(
//                () -> assertEquals(newUser.getId(), result.getId()),
//                () -> assertEquals(newUser.getEmail(), result.getEmail()),
//                () -> assertEquals(newUser.getProfilePic(), result.getProfilePic())
//        );
    }

    @Test
    void addList() {
    }

    @Test
    void getListCollection() {
    }

    @Test
    void addPin() {
    }

    @Test
    void getListPinCollection() {
    }
}