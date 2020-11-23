package com.user.user.service;

import java.io.FileInputStream;
import java.io.InputStream;

import javax.annotation.PostConstruct;

import org.springframework.stereotype.Service;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.cloud.firestore.CollectionReference;
import com.google.cloud.firestore.Firestore;

import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;

import com.google.firebase.cloud.FirestoreClient;


@Service
public class FirebaseInitializer {

    @PostConstruct
    public void initialize() {
        try {
        	InputStream serviceAccount = new FileInputStream("./key/ecommerce-user-9ac9b-firebase-adminsdk-lecir-2a88834f5a.json");
        	GoogleCredentials credentials = GoogleCredentials.fromStream(serviceAccount);
        	@SuppressWarnings("deprecation")
			FirebaseOptions options = new FirebaseOptions.Builder()
        	    .setCredentials(credentials)
        	    .setDatabaseUrl("https://ecommerce-user-9ac9b.firebaseio.com")
        	    .build();
        	FirebaseApp.initializeApp(options);
            
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
	public Firestore getFirebase() {
    	Firestore db = FirestoreClient.getFirestore();
    	return db;
	}
	public CollectionReference collection(String string) {
		// TODO Auto-generated method stub
		return null;
	}
}