package org.example.SearchIndex.service.Startegy;

import org.example.SearchIndex.Enums.StrategyType;
import org.example.SearchIndex.model.Category;
import org.example.SearchIndex.model.Documents;

import java.util.List;
import java.util.Set;

public  class SimpleSearchStrategy implements  ISearchStrategy{
    @Override
    public Set<Documents> search(Category category, String pattern, StrategyType searchType) {

        //get Words from pattern and find document from categry
        String[] words= pattern.toLowerCase().split(" ");
        Set<Documents>  documents = category.getIndex().getSearchIndex().get(words[0]);
        for(String word: words){
            //Adding nulll to retailAll give error so I need to handle that
            documents.retainAll(category.getIndex().getSearchIndex().get(word));
        }

        return documents;
    }
}
