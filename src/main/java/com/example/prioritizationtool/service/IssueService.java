package com.example.prioritizationtool.service;

import com.example.prioritizationtool.model.Issue;
import com.example.prioritizationtool.model.Item;
import com.example.prioritizationtool.repository.IssueRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class IssueService implements ItemService<Item> {
    private IssueRepository repository;

    @Autowired
    public IssueService(IssueRepository repository){
        this.repository = repository;
    }
    public ArrayList<Item> findAll() {
        return repository.findAll();
    }

    public void add(Item issue) {
        repository.put(issue);
    }

    @Override
    public void create(String title, String desc) {
       repository.put(new Issue(title, desc));
    }

    public void updateDescription(Item issue, String description) {
        issue.setDescription(description);
    }

    @Override
    public Issue getByTitle(String title) {
       return repository.findByTitle(title);
    }

    public void delete(Item issue) {
        repository.delete(issue);
    }
}
