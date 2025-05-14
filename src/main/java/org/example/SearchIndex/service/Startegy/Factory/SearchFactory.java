package org.example.SearchIndex.service.Startegy.Factory;

import org.example.SearchIndex.Enums.StrategyType;
import org.example.SearchIndex.service.Startegy.ISearchStrategy;

import org.example.SearchIndex.service.Startegy.SimpleSearchStrategy;;

public class SearchFactory {


    public static ISearchStrategy  createStrategy(StrategyType type){

        switch (type){

            case UNORDERED:
                return  new SimpleSearchStrategy();
            case  ORDERED:
                return  new SimpleSearchStrategy();
            default:
                return  null;


        }
    }
}
