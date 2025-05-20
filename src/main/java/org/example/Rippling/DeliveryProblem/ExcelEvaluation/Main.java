package org.example.Rippling.DeliveryProblem.ExcelEvaluation;

public class Main {
    public static void main(String[] arg){

        ExcelSheetManager manager= new ExcelSheetManager();

//        manager.setCell("A","10");
 //       manager.setCell("B","20");
        //manager.setCell("C","30");
//        manager.setCell("C","=5+9");
//        manager.setCell("D","=A+9");
//        manager.setCell("E","=A+B");

        //Cycline dependency check
        manager.setCell("A","=B+9");
        manager.setCell("B","=C+9");
        manager.setCell("C","=A+9");
        manager.printValue();
    }
}
