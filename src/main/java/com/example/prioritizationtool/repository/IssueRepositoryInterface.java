package com.example.prioritizationtool.repository;

import com.example.prioritizationtool.model.Issue;
import org.springframework.stereotype.Repository;
import org.springframework.data.repository.CrudRepository;

import java.util.ArrayList;

public interface IssueRepositoryInterface{
    ArrayList<Issue> findAll();
    Issue findByTitle(String id);
    void put(Issue issue);
    void delete(Issue issue);
}
