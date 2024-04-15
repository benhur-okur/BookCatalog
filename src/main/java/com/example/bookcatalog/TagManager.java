package com.example.bookcatalog;

import java.util.ArrayList;

public class TagManager {
    private ArrayList<String> tags;

    public TagManager() {
        tags = new ArrayList<>();
    }

    public void addTag(String tag) {
        if (!tags.contains(tag)) {
            tags.add(tag);
        }
    }

    public ArrayList<String> getTags() {
        return tags;
    }
}
