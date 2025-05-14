package org.example.SearchIndex.model;

import lombok.Getter;

import java.util.Date;

@Getter
public class Documents {
      String name;
      String content;
      String publishedBy;
      Date lastSearch;
      Date lastUpdated;
      Date publishedDate;

    public Documents(String name, String content, String publishedBy) {
        this.name = name;
        this.content = content;
        this.publishedBy = publishedBy;
        this.lastUpdated = new Date();
        this.publishedDate = new Date();
    }


}
