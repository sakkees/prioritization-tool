package com.example.prioritizationtool.controller;

import com.example.prioritizationtool.model.Issue;
import com.example.prioritizationtool.service.IssueService;
import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.*;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutionException;

@RestController
public class Controller {
    private String projectId;
    FirestoreOptions firestoreOptions =
            FirestoreOptions.getDefaultInstance().toBuilder()
                    .setProjectId(projectId)
                    .build();
    Firestore db = firestoreOptions.getService();

    DocumentReference docRef;
/*
    @Autowired
    IssueService issueService;

    @RequestMapping("/issues")
    public ResponseEntity<Object> getAllIssues() {
        return new ResponseEntity<Issue>(IssueService.findAll(), HttpStatus.OK);
    }
    @RequestMapping("/issue")
    public ResponseEntity<Object> getIssueById() {
        return new ResponseEntity<Issue>(IssueService.getIssue(), HttpStatus.OK);
    }
    @PutMapping("/update")
    public ResponseEntity<Object> updateIssue() {
        return new ResponseEntity<Issue>(IssueService.updateIssue(), HttpStatus.OK);
    }
    @PostMapping("/create")
    public ResponseEntity<Object> createIssue(){
        return new ResponseEntity<Issue>(IssueService.createIssue(), HttpStatus.OK);
    }
    @DeleteMapping("/delete/issue")
    public ResponseEntity<Object> deleteIssue(){
        return new ResponseEntity<Issue>(IssueService.deleteIssue(), HttpStatus.OK);
    }
    */
    @PostMapping(path = "/api/add")
    public String addIssue(@RequestBody Map<String, Object> payload) {
        String title = "";
        for(Map.Entry<String, Object> entry : payload.entrySet()){
           // Issue issue = new Issue(entry.getKey().toString(), entry.getValue().toString());
            if(entry.getKey().equals("title")){
                title = entry.getValue().toString();
            }
        }
        docRef = db.collection("issues").document(title);
        //asynchronously write data
        ApiFuture<WriteResult> result = docRef.set(payload);
        return title + " added in database.";
    }

    // Get and convert the document into an issue object
    @GetMapping("/api/get")
    public Issue getIssue(){
        Issue issue = null;
        DocumentReference docRef = db.collection("issues").document("issue");
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
            System.out.println(issue.getTitle());
        } else {
            System.out.println("No such document!");
        }
        return issue;
    }
    private IssueService issueService;
    @GetMapping("/api/issues")
    public List<Issue> getIssues(){
        //asynchronously retrieve multiple documents
        ApiFuture<QuerySnapshot> future =
                db.collection("cities").whereEqualTo("capital", true).get();
        // future.get() blocks on response
        List<QueryDocumentSnapshot> documents = null;
        try {
            documents = future.get().getDocuments();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        for (DocumentSnapshot document : documents) {
            //listOfIssues.add(document.toObject(Issue.class));
            System.out.println(document.getId() + " => " + document.toObject(Issue.class));
        }
        return issueService.findAll();
    }

    // Get collection and returns a string of all the documents key and value
    @GetMapping("/api/collection")
    public String getCollection() {
        StringBuilder collection = new StringBuilder();
        // asynchronously retrieve all issues
        ApiFuture<QuerySnapshot> query = db.collection("issues").get();
        // query.get() blocks on response
        QuerySnapshot querySnapshot = null;
        try {
            querySnapshot = query.get();
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
        List<QueryDocumentSnapshot> documents = querySnapshot.getDocuments();
        for (QueryDocumentSnapshot document : documents) {
           // issueService.addIssue(document.toObject(Issue.class));
            collection.append(document.getString("title"))
                    .append(" : ")
                    .append(document.getString("description"))
                    .append(", ");
        }
        //return issueService.findAll();
        return collection.toString();
    }

    @PostMapping("/api/addIsac")
    public String addData() {
        String firstName = "Isac";
        String lastName = "Larsson";
        docRef = db.collection("users").document(firstName);
        Map<String, Object> data = new HashMap<>();
        data.put("name", firstName);
        data.put("description", lastName);
        //asynchronously write data
        ApiFuture<WriteResult> result = docRef.set(data);
        return firstName + " " + lastName + " added in database.";
    }
}
