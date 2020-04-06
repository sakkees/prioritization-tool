package com.example.prioritizationtool.repository;

import com.example.prioritizationtool.model.MyItem;
import com.example.prioritizationtool.model.MyList;
import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.*;
import org.springframework.stereotype.Repository;

import java.util.concurrent.ExecutionException;

@Repository
public class IssueRepository implements ItemRepository {
    private CollectionReference collectionRef;

    public IssueRepository(){
        String projectId = "named-sunset-265213";
        FirestoreOptions firestoreOptions = FirestoreOptions.getDefaultInstance().toBuilder()
                .setProjectId(projectId)
                .build();
        Firestore db = firestoreOptions.getService();
        this.collectionRef = db.collection("issues");
    }

    @Override
    public MyList getListById(String listId) {
        MyList list = null;
        DocumentReference docRef = collectionRef.document(listId);
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
            list = document.toObject(MyList.class);
        } else {
            System.out.println("No such document!");
        }
        return list;
    }

    private DocumentSnapshot getDocSnap(String listId){
        ApiFuture<DocumentSnapshot> future = collectionRef.document(listId).get();
        DocumentSnapshot document = null;
        try {
            document = future.get();
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
        return document;
    }

    @Override
    public MyItem getItemById(String itemId, String listId) {
        DocumentSnapshot document = getDocSnap(listId);
        MyList list;
        MyItem item = null;
        if (document.exists()) {
            // convert document to POJO
            list = document.toObject(MyList.class);
            item = list.getItemById(itemId);
        } else {
            System.out.println("No such document / item!");
        }
        return item;
    }

    @Override
    public void deleteItemById(String itemId, String listId) {
        DocumentSnapshot document = getDocSnap(listId);
        MyList list;
        MyItem item;
        if (document.exists()) {
            // convert document to POJO
            list = document.toObject(MyList.class);
            item = list.getItemById(itemId);
            list.remove(item);
            this.put(list);
        } else {
            System.out.println("No such list / item!");
        }
    }

    public void put(MyList list) {
        collectionRef.document(list.getId()).set(list);
    }

    @Override
    public void put(MyItem item, String listId) {
        collectionRef.document(listId).update("list", FieldValue.arrayUnion(item));
    }

    @Override
    public void deleteListById(String listId) {
        collectionRef.document(listId).delete();
    }
}
