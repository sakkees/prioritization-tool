package com.example.prioritizationtool.controller;

import com.example.prioritizationtool.model.MyItem;
import com.example.prioritizationtool.model.MyList;
import com.example.prioritizationtool.repository.IssueRepository;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
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
    public String add(@RequestBody MyList list){
        issueRepository.put(list);
        return "List with owner: " + list.getOwner() + " added." ;
    }

    @DeleteMapping("/delete")
    public String deleteList(@NotNull @RequestBody Map<String, Object> payload){
        for(Map.Entry<String, Object> entry : payload.entrySet()){
            if (entry.getKey().equals("listId")){
                issueRepository.deleteListById(entry.getValue().toString());
            }
        }
        return "List deleted!";
    }

    @DeleteMapping("/deleteItem")
    public String deleteIssue(@NotNull @RequestBody Map<String, String> payload){
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
        return "(List Id): " + listId + ", (Issue Id): " +  id + " deleted!";
    }

    /* Working method but kind of a hack solution */
    @PutMapping("/addIssue")
    public void addIssueToList(@NotNull @RequestBody Map<String, Object> payload) {
        MyItem item = null;
        String listId = null;
        for(Map.Entry<String, Object> entry : payload.entrySet()){
            if(entry.getKey().equals("listId")) {
                listId = entry.getValue().toString();
            }
            if(entry.getKey().equals("issue")){
                LinkedHashMap<String, String> map = (LinkedHashMap<String, String>) entry.getValue();
                String t = map.get("title");
                String d = map.get("description");
                item = new MyItem(t,d);
            }
        }
        issueRepository.put(item, listId);
    }

    @RequestMapping("/issue")
    public MyItem getIssueById(@NotNull @RequestBody Map<String, String> payload) {
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
        return issueRepository.getItemById(itemId, listId);
    }

    @RequestMapping("/list")
    public MyList getAllIssues(@RequestBody Map<String, String> payload) {
        for(Map.Entry<String, String> entry : payload.entrySet()){
            if(entry.getKey().equals("listId")){
               return issueRepository.getListById(entry.getValue());
            }
        }
        return null;
    }
}