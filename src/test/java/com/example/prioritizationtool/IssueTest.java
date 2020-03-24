package com.example.prioritizationtool;

import com.example.prioritizationtool.model.Issue;
import com.example.prioritizationtool.model.Item;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class IssueTest {
    @Test
    public void issueEquals(){
        Issue issue = new Issue("title","desc");
        Issue issue1 = new Issue("title","desc");
        assertEquals(issue1,issue);
    }
}
