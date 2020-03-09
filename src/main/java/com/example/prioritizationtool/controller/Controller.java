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

    @PostMapping("/addIsac")
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

    @GetMapping("/collection")
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
            collection.append(document.getString("title"))
                    .append(" : ")
                    .append(document.getString("description"))
                    .append(", ");
        }
        return collection.toString();
    }

}
