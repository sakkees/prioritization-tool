package com.example.prioritizationtool.model;

import java.util.ArrayList;

public class IssueList{
    private ArrayList<Issue> issueList;
    private String owner;

    public IssueList(String owner){
        this.issueList = new ArrayList<Issue>();
        this.owner = owner;
    }

    public void addIssue(Issue issue){
        issueList.add(issue);
    }

    public void deleteIssue(Issue issue){
        issueList.remove(issue);
    }

    public Issue getIssue(String title){
        for(Issue issue : issueList){
            if(issue.getTitle().equals(title)){
                return issue;
            }
        }
      return null;
    }

    public String getOwner(){
        return this.owner;
    }

    public boolean containsIssue(Issue issue){
        return issueList.contains(issue);
    }
}
