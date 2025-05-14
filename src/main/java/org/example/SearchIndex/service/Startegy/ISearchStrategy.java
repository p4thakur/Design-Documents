package org.example.SearchIndex.service.Startegy;

import org.example.SearchIndex.Enums.StrategyType;
import org.example.SearchIndex.model.Category;
import org.example.SearchIndex.model.Documents;

import java.util.List;
import java.util.Set;

public interface ISearchStrategy {

    Set<Documents> search(Category category, String pattern, StrategyType searchType);
}
