package org.example.SearchIndex.controller;

import org.example.SearchIndex.Enums.SortOrderBY;
import org.example.SearchIndex.Enums.StrategyType;
import org.example.SearchIndex.model.Category;
import org.example.SearchIndex.model.Documents;
import org.example.SearchIndex.service.Startegy.Factory.SearchFactory;
import org.example.SearchIndex.service.Startegy.ISearchStrategy;
import org.example.SearchIndex.service.Startegy.OrderingStrategy;
import org.example.SearchIndex.service.Startegy.PublishedDateOrderingStrategy;

import javax.swing.text.Document;
import java.util.*;

public class TechBlogController {

    Map<String, Category>  categories;
    //singl instanc eof this class
    //this will containlist of categories

    //add category.  Add Document   .  Search
    private static TechBlogController  techBlogController;

    private TechBlogController() {
        categories = new HashMap<>();
    }

    //singleton object
    public  synchronized  static TechBlogController creatTechBlogInstance(){
        if(techBlogController==null){
            techBlogController= new TechBlogController();
        }
        return techBlogController;
    }

    //Add Category
    public Category  addCategory(String name){
        Category category = new Category(name);
        categories.put(name, category);
        return category;
    }
    //Add Document
    public void  addDocuments(String categoryName, String name, String content, String publishedBy){

        Category category = categories.get(categoryName);
        Documents document = new Documents(name, content,publishedBy);
        category.addDocument(document);//this will also creat index
    }

    public List<Documents>  search(String categoryName, String pattern, StrategyType type){

        Category category = categories.get(categoryName);
        ISearchStrategy  searchStrategy =  SearchFactory.createStrategy(type);
        Set<Documents>  searchDocument = searchStrategy.search(category, pattern,type);
        return new ArrayList<>(searchDocument);
    }

    public List<Documents>  sortArticle(List<Documents>  documents, SortOrderBY type){

        PublishedDateOrderingStrategy publishedDateOrderingStrategy = new PublishedDateOrderingStrategy();
         return  publishedDateOrderingStrategy.sortResult(documents,type);

    }
}
