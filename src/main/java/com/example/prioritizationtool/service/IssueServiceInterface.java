package com.example.prioritizationtool.service;

import com.example.prioritizationtool.model.Issue;

import java.util.ArrayList;

public interface IssueServiceInterface {
    ArrayList<Issue> findAll();
    void addIssue(Issue issue);
    void createIssue(String title, String description);
    void updateIssue(Issue issue, String description);
    void deleteIssue(Issue issue);
    Issue getIssue(String title);
}
