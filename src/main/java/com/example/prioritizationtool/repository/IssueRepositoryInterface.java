package com.example.prioritizationtool.repository;

import com.example.prioritizationtool.model.Issue;

import java.util.ArrayList;

public interface IssueRepositoryInterface{
    ArrayList<Issue> findAll();
    Issue findByTitle(String id);
    void put(Issue issue);
    void delete(Issue issue);
}
