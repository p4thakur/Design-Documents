package org.example;



import java.util.*;

class  BattleshipGame{

    int[][]  grid;
    static int row;
    static int col;
    static  int PlayerAShipCount;
    static int PlayerBShipCount;

    public BattleshipGame(int row, int col){
        this.row=row;
        this.col=col;
        grid= new int[row][col];

    }


    public static void addShip(int row, int col, int[][] grid, int player){
        if(isSafe(row, col, grid))
            grid[row][col]=1;

        if(player==1){
            PlayerAShipCount++;//
        } else{
            PlayerBShipCount++;
        }

    }

    public  static  boolean isSafe(int currRow, int currCol, int[][] grid){

        if(currRow<0 || currRow>=row || currCol<0 || currCol>=col ){
            return false;
        }
        return true;

    }

    public static boolean play(int rowX, int colX, int[][] grid){

        //check is ship presemt. if yes mark it as zero and return true;

        if(isSafe(rowX, colX, grid)  && grid[rowX][colX]==1){
            grid[rowX][colX]=0;
            return true;
        }

        return false;


    }
    public static  boolean  isGameOver(int  currenShipCount){

        currenShipCount--;
        if(currenShipCount==0)
            return true;

        return false ;
    }


    public static void main(String[]  str){

        Scanner sc = new Scanner (System.in);
     System.out.println("Size of grid");
        int n=  sc.nextInt();
        int gridSize=n/2;
        BattleshipGame  palyerOneGrid= new BattleshipGame(gridSize,gridSize);
        BattleshipGame  palyerTwoGrid= new BattleshipGame(gridSize,gridSize);

        //int input;
        int terminate=0;
        int[][]  gridA  = palyerOneGrid.grid;
        int[][]  gridB  = palyerTwoGrid.grid;
        System.out.println("Row x");
        while(terminate!=2){
            System.out.println("Row x");
            int  inputX= sc.nextInt();
            System.out.println("col y");
            int inputY= sc.nextInt();
            System.out.println("Player x");
            int currPlayer= sc.nextInt();
            if(currPlayer==1){


                palyerOneGrid.addShip(inputX,inputY,gridA,currPlayer);
            } else {

                palyerTwoGrid.addShip(inputX,inputY,gridB,currPlayer);
            }

            System.out.println("Terminate 10");
            terminate= sc.nextInt();
        }

        int currentPlayer=1;
        while(true){

            Random rand= new Random();
            int hitX=  rand.nextInt()%gridSize;
            int hitY=  rand.nextInt()%gridSize;
            Boolean result;
            if(currentPlayer==1) {
                result =  palyerOneGrid.play(hitX,hitY, gridA);
                if(result) {
                    if(palyerOneGrid.isGameOver(PlayerAShipCount)){
                        System.out.println("Winner : Player 1 " );
                    }
                    break;

                }
            }

            else {
                result = palyerTwoGrid.play(hitX,hitY, gridB);
                if(result) {
                    if(palyerTwoGrid.isGameOver(PlayerAShipCount))
                        System.out.println("Winner : Player 2 " );
                    break;

                }
            }

            currentPlayer = currentPlayer==1? 0: 1;



        }



    }
}

