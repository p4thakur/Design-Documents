package org.example.SearchIndex.model;

import lombok.Getter;

import java.util.*;

@Getter
public class IndexSearch {
    //Word to Document
    Map<String, Set<Documents>> searchIndex;

    public IndexSearch() {
        this.searchIndex = new HashMap<>();
    }


     public void addIndex(Documents documents){

         String[] words=documents.getContent().toLowerCase().split("\\W+");
         for(String word: words){

             searchIndex.computeIfAbsent(word,k-> new HashSet<>()).add(documents);
         }
     }


}
