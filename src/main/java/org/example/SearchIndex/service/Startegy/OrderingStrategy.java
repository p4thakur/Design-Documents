package org.example.SearchIndex.service.Startegy;

import org.example.SearchIndex.Enums.SortOrderBY;
import org.example.SearchIndex.model.Documents;

import java.util.List;

public interface OrderingStrategy {

    List<Documents>   sortResult(List<Documents>  documents, SortOrderBY sortOrderBY);
}
