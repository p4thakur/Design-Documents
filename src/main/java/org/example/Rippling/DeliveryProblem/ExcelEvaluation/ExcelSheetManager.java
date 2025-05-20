package org.example.Rippling.DeliveryProblem.ExcelEvaluation;


import lombok.Getter;

import java.util.*;

@Getter
class Cell{
      private String name;
      private  String rawValue;
      private String calclvalue;

      public Cell(String name, String rawValue){
          this.rawValue= rawValue;
          this.name=name;
      }


}
public class ExcelSheetManager {

    Map<String, Cell> cells;

    public ExcelSheetManager(){
        cells= new HashMap<>();
    }
    public void setCell(String cellNam,String value){
        Cell cell  = new Cell(cellNam, value);;
        cells.put(cellNam, cell);

    }

     public void printValue(){

        for(Cell cell : cells.values()){

            String rawValue = cell.getRawValue();
            Set<String> visited = new HashSet<>();
            String calcValue = calcExpresssion(cell.getName(), visited);

            System.out.println("Rowa value "+ rawValue + " evalute= "+ calcValue);

        }

     }
     //cellname
    private String calcExpresssion(String name,Set<String> visited){

         if(visited.contains(name)){
             throw new IllegalArgumentException("Cyclic Depenendcy  for colums");
         };
         visited.add(name);
        String raw=cells.get(name).getRawValue();
        //if  empty return. 2: if its not stating with = than retunr
        if(raw==null || raw.isEmpty() || raw.charAt(0)!='='){
            return raw;
        }
        String result;
        String temp = raw.substring(1);

        //tokenize the string 2+3 -- 2 ,+, 3
        List<String>  tokens = tokenize(temp);
         int sign=1;
         int ans=0;

        for(String token : tokens){
           // System.out.println("token "+ token + " size"+ tokens.size());
            if(token.equals("+")){
                sign=1;
            } else if(token.equals("-")){
                sign=-1;
            } //now letter. just check first character
            else if(Character.isLetter(token.charAt(0))){
                String tempString =calcExpresssion(token, visited);
                ans = ans + sign*(Integer.valueOf(tempString));
            }else{
                System.out.println("hello");
                ans = ans + sign*(Integer.valueOf(token));
            }
        }

        return String.valueOf(ans);

    }
    //   2 + 3
    private List<String> tokenize(String rawString){

        int len= rawString.length();
        int start= 0;
        List<String> token= new ArrayList<>();
        while(start<len){

            StringBuilder sb = new StringBuilder();
            char c = rawString.charAt(start);
            //System.out.println("Charct "+ c);
            //check for spaces--> just ask them inittail only if space can come in between
           if(rawString.charAt(start)=='+' || rawString.charAt(start)=='-') {
               token.add(Character.toString(c));
               //sb.append()
               start++;
               continue;
           }else if(Character.isLetter(c)){
               //This will be neeed for referenc
               while(start<len && (Character.isDigit(rawString.charAt(start))
                       || Character.isLetter(rawString.charAt(start))) )
               {
                  //
                   // System.out.println("string added chatc "+ c);

                   sb.append(c);
                   start++;
               }
            } else if(Character.isDigit(c)){

               while(start<len && Character.isDigit(rawString.charAt(start)))
               {
                   sb.append(c);
                   //System.out.println("string added "+ c);
                   start++;
               }

           }
           token.add(sb.toString());

        }

        return token;
    }

}
