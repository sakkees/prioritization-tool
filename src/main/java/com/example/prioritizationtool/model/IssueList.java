package com.example.prioritizationtool.model;

public class IssueList<E extends Item> extends List<E> {

    public IssueList(String owner) {
        super(owner);
    }

    public E get(String id) {
        for (E e : super.list) {
            if (e.getTitle().equals(id)) {
                return e;
            }
        }
        return null;
    }
}
