package com.example.prioritizationtool.controller;

import com.example.prioritizationtool.model.Issue;
import com.example.prioritizationtool.service.IssueService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Map;

@RestController
public class Controller {
    private IssueService issueService;

    @Autowired
    public Controller(IssueService issueService){
        this.issueService = issueService;
    }

    @PostMapping("/create")
    public Issue addIssue1(@RequestBody Issue issue){
        issueService.addIssue(issue);
        return issue;
    }

    @DeleteMapping("/delete")
    public String deleteIssue(@RequestBody Map<String, Object> payload){
        Issue issue;
        for(Map.Entry<String, Object> entry : payload.entrySet()){
            if(entry.getKey().equals("title")){
                issue = issueService.getIssue(entry.getValue().toString());
                issueService.deleteIssue(issue);
            }
        }
        return "Deleted!";
    }

    /* Uses same method as create issue */
    @PutMapping("/update")
    public Issue updateIssue(@RequestBody Issue issue) {
        issueService.addIssue(issue);
        return issue;
    }

    @RequestMapping("/issue")
    public Issue getIssueById(@RequestBody Map<String, Object> payload) {
        Issue issue = null;
        for(Map.Entry<String, Object> entry : payload.entrySet()){
            if(entry.getKey().equals("title")){
                issue = issueService.getIssue(entry.getValue().toString());
            }
        }
        return issue;
    }

    @RequestMapping("/list")
    public ArrayList<Issue> getAllIssues() {
        return issueService.findAll();
    }
}
