package com.example.prioritizationtool.controller;

import com.example.prioritizationtool.model.MyItem;
import com.example.prioritizationtool.model.MyList;
import com.example.prioritizationtool.repository.IssueRepository;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.LinkedHashMap;
import java.util.Map;

@RestController
public class IssueController {
    private IssueRepository issueRepository;

    @Autowired
    public IssueController(IssueRepository issueRepository){
        this.issueRepository = issueRepository;
    }

    @PostMapping("/create")
    public ResponseEntity<String> add(@RequestBody MyList list){
        issueRepository.put(list);
        return ResponseEntity.status(HttpStatus.CREATED).body(list.getOwner() + " added.");
    }

    @DeleteMapping("/delete")
    public ResponseEntity<String> deleteList(@NotNull @RequestBody Map<String, Object> payload){
        for(Map.Entry<String, Object> entry : payload.entrySet()){
            if (entry.getKey().equals("listId")){
                issueRepository.deleteListById(entry.getValue().toString());
            }
        }
        return ResponseEntity.status(HttpStatus.OK).body("List deleted.");
    }

    @DeleteMapping("/deleteItem")
    public ResponseEntity<String> deleteIssue(@NotNull @RequestBody Map<String, String> payload){
        String issueId = null;
        String listId = null;

        for(Map.Entry<String, String> entry : payload.entrySet()){
            if(entry.getKey().equals("itemId")){
                issueId = entry.getValue();
            }
            if(entry.getKey().equals("listId")){
                listId = entry.getValue();
            }
        }
        String id = issueId;
        issueRepository.deleteItemById(issueId,listId);
        return ResponseEntity.status(HttpStatus.OK).body("(List Id): " + listId + ", (Issue Id): " +  id + " deleted!");
    }

    @PutMapping("/addIssue")
    public ResponseEntity<String> addIssueToList(@NotNull @RequestBody Map<String, Object> payload) {
        MyItem item = null;
        String listId = null;
        for(Map.Entry<String, Object> entry : payload.entrySet()){
            if(entry.getKey().equals("listId")) {
                listId = entry.getValue().toString();
            }
            if(entry.getKey().equals("issue")){
                LinkedHashMap<String, String> map = (LinkedHashMap<String, String>) entry.getValue();
                String title = map.get("title");
                String description = map.get("description");
                item = new MyItem(title,description);
            }
        }
        issueRepository.put(item, listId);
        return ResponseEntity.status(HttpStatus.OK).body("Added");
    }

    @GetMapping("/issue")
    public ResponseEntity<MyItem> getIssueById(@NotNull @RequestBody Map<String, String> payload) {
        String listId = null;
        String itemId = null;
        for(Map.Entry<String, String> entry : payload.entrySet()){
            if(entry.getKey().equals("listId")){
                listId = entry.getValue();
            }
            if(entry.getKey().equals("issueId")){
                itemId = entry.getValue();
            }
        }
        return ResponseEntity.status(HttpStatus.OK).body(issueRepository.getItemById(itemId, listId));
    }

    @GetMapping("/list")
    public ResponseEntity getAllIssues(@NotNull @RequestBody Map<String, String> payload) {
        for(Map.Entry<String, String> entry : payload.entrySet()){
            if(entry.getKey().equals("listId")){
               return ResponseEntity.status(HttpStatus.OK).body(issueRepository.getListById(entry.getValue()));
            }
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("No issues");
    }
}