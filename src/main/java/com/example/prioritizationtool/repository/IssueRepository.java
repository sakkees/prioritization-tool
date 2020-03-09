package com.example.prioritizationtool.repository;

import com.example.prioritizationtool.model.Issue;
import org.springframework.stereotype.Repository;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

@Repository
public interface IssueRepository extends CrudRepository<Issue, String>{
    List<Issue> findAll();
    Issue findByTitle(String id);
    void put(Issue issue);
}
