package org.example.SearchIndex.service.Startegy;

import org.example.SearchIndex.Enums.SortOrderBY;
import org.example.SearchIndex.model.Documents;

import java.util.Collections;
import java.util.List;

public class PublishedDateOrderingStrategy implements  OrderingStrategy{
    @Override
    public List<Documents> sortResult(List<Documents> documents, SortOrderBY sortOrderBY) {

        documents.sort((a,b)  -> {
            return a.getPublishedDate().compareTo(b.getPublishedDate());
        });
        return documents;
    }
}
