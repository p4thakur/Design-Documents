package org.example.SearchIndex;

import org.example.SearchIndex.Enums.SortOrderBY;
import org.example.SearchIndex.Enums.StrategyType;
import org.example.SearchIndex.controller.TechBlogController;
import org.example.SearchIndex.model.Category;
import org.example.SearchIndex.model.Documents;

import java.util.List;

public class TecBlogClient {

    public static void main(String [] str){

        TechBlogController techBlogController = TechBlogController.creatTechBlogInstance();

       Category Category1=  techBlogController.addCategory("AI Books");
       Category Category2 = techBlogController.addCategory("AI and ML Books Books");

        techBlogController.addDocuments(Category1.getName(),"Doc1","AI and ML Books","Prateek");
        techBlogController.addDocuments(Category1.getName(),"Doc1","AI and ML Books2","Prateek");
        techBlogController.addDocuments(Category1.getName(),"Doc1","AI  Books","Prateek");

        List<Documents> documentsList =techBlogController.search(Category1.getName(),"ML", StrategyType.UNORDERED);

        for(Documents documents :documentsList){
            System.out.println("doc "+ documents.getContent());
        }

        List<Documents> sortedDoocumentsList = techBlogController.sortArticle(documentsList, SortOrderBY.PUBLISH_DATE);

        for(Documents documents :sortedDoocumentsList){
            System.out.println("doc 2"+ documents.getContent());
        }
    }
}
