package org.example.SearchIndex.model;

import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Getter
public class Category {
    String name;
    List<Documents>  documents;
    //Let's build index for our search
    IndexSearch    index;

    public Category(String name) {
        this.name = name;
        this.documents = new ArrayList<>();
        this.index = new IndexSearch();
    }

    //Adding the document
    public void  addDocument(Documents document){
        //Add validation checks later
        documents.add(document);
        addIndexForDoc(document);
    }

    public void  addIndexForDoc(Documents document){

        index.addIndex(document);
    }
}
