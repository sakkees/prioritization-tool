package com.example.prioritizationtool;

import com.example.prioritizationtool.model.MyItem;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class IssueTest {
    @Test
    public void issueEquals(){
        MyItem issue = new MyItem("title","desc");
        MyItem issue1 = new MyItem("title","desc");
        assertEquals(issue1,issue);
    }
}
