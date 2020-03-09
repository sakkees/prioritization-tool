package com.example.prioritizationtool.service;

import com.example.prioritizationtool.model.Issue;
import com.example.prioritizationtool.repository.IssueRepository;
import org.springframework.stereotype.Service;

import javax.validation.constraints.NotNull;
import java.util.List;

@Service
public class IssueService implements IssueServiceInterface {
    private IssueRepository repository;

    @Override
    public List<Issue> findAll() {
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
    public void updateIssue(@NotNull Issue issue, String title, String description) {
        issue.setDescription(description);
    }

    @Override
    public Issue getIssue(String title) {
       return repository.findByTitle(title);
    }

    @Override
    public void deleteIssue(String title) {
        repository.delete(getIssue(title));
    }
}
