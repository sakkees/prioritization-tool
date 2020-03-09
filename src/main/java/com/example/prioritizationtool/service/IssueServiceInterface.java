package com.example.prioritizationtool.service;

import com.example.prioritizationtool.model.Issue;

import java.util.List;

public interface IssueServiceInterface {
    List<Issue> findAll();
    void addIssue(Issue issue);
    void createIssue(String title, String description);
    void updateIssue(Issue issue, String title, String description);
    void deleteIssue(String title);
    Issue getIssue(String title);
}
