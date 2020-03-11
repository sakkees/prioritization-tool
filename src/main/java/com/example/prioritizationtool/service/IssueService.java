package com.example.prioritizationtool.service;

import com.example.prioritizationtool.model.Issue;
import com.example.prioritizationtool.repository.IssueRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.validation.constraints.NotNull;
import java.util.ArrayList;

@Service
public class IssueService implements IssueServiceInterface {
    @Autowired
    private IssueRepository repository;

    @Override
    public ArrayList<Issue> findAll() {
        return repository.findAll();
    }

    @Override
    public void addIssue(Issue issue) {
        repository.put(issue);
    }

    @Override
    public void createIssue(String title, String desc) {
       repository.put(new Issue(title, desc));
    }

    @Override
    public void updateIssue(Issue issue, String description) {
        issue.setDescription(description);
    }

    @Override
    public Issue getIssue(String title) {
       return repository.findByTitle(title);
    }

    @Override
    public void deleteIssue(Issue issue) {
        repository.delete(issue);
    }
}
