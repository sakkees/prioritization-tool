package com.example.prioritizationtool.repository;

import com.example.prioritizationtool.model.Issue;
import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.*;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

import java.util.concurrent.ExecutionException;

@Repository
public class IssueRepository implements IssueRepositoryInterface{
    private String projectId;
    FirestoreOptions firestoreOptions =
            FirestoreOptions.getDefaultInstance().toBuilder()
                    .setProjectId(projectId)
                    .build();
    private Firestore db = firestoreOptions.getService();
    private CollectionReference collectionRef = db.collection("issues");
    ArrayList<Issue> issues;

    public IssueRepository(){
        issues = new ArrayList<>();
        fillArray();
    }

    private void fillArray(){
        // asynchronously retrieve all issues
        ApiFuture<QuerySnapshot> query = collectionRef.get();
        // query.get() blocks on response
        QuerySnapshot querySnapshot = null;
        try {
            querySnapshot = query.get();
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
        List<QueryDocumentSnapshot> documents = querySnapshot.getDocuments();
        Issue issue = null;
        for (QueryDocumentSnapshot document : documents){
            issue = document.toObject(Issue.class);
            issues.add(issue);
        }
    }

    @Override
    public ArrayList<Issue> findAll() {
        return issues;
    }

    @Override
    public Issue findByTitle(String id) {
        Issue issue = null;
        DocumentReference docRef = collectionRef.document(id);
        // asynchronously retrieve the document
        ApiFuture<DocumentSnapshot> future = docRef.get();
        // block on response
        DocumentSnapshot document = null;
        try {
            document = future.get();
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
        if (document.exists()) {
            // convert document to POJO (Plain Old Java Object)
            issue = document.toObject(Issue.class);
        } else {
            System.out.println("No such document!");
        }
        return issue;
    }

    @Override
    public void put(Issue issue) {
        collectionRef.document(issue.getTitle()).set(issue);
        issues.add(issue);
    }

    @Override
    public void delete(Issue issue) {
        collectionRef.document(issue.getTitle()).delete();
        issues.remove(issue);
    }
}
