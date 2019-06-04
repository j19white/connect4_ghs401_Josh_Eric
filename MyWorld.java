import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.List;
/**
 * Write a description of class MyWorld here.
 * 
 * @author (your name) 
 * @version (a version number or a date
 */
public class MyWorld extends World
{
    private String[][] grid;
    private int playerTurn;
    private String playerBlack;
    private String playerRed;
    private String playerBlackInitial;
    private String playerRedInitial;
    private int playerBlackWinTotal;
    private int playerRedWinTotal;
    private double playerBlackWinPercentage;
    private double playerRedWinPercentage;
    private int goalInARow;
    private int firstPlayerTurn;
    private boolean firstPlay;
    private String lastPlayedInitial;
    private int tieCounter;
    private int gameCounter;
    
    
    
    private int turnCounter;
    private int col1x, col1y, colDiff;
    private int actCounter;
    
    /**
     * Constructor for objects of class MyWorld.
     * 
     */
    public MyWorld() 
    {    
        // Create a new world with 600x400 cells with a cell size of 1x1 pixels.
        super(600, 400, 1); 
        grid = new String[6][7];    //to keep track of the game board
        firstPlayerTurn = (int)(Math.random()*2+1);   //randomize who starts the game
        playerBlack = "PlayerBlack";
        playerRed = "PlayerRed";
        playerBlackInitial = "b";  //to fill the 2d array grid to know which checker is where
        playerRedInitial = "r";   //to fill the 2d array grid to know which checker is where
        playerBlackWinTotal = 0;  //scoreboard
        playerRedWinTotal = 0;    //scoreboard
        goalInARow = 5;//because it is Connect 4
        actCounter = 20;//sets the speed of the game
        firstPlay = true; //determines if it's first play of new game, so we can alternate first turn each game
        lastPlayedInitial = ""; //keeps track of who played last
        tieCounter = 0;
        gameCounter = 0;
        
        
        
        turnCounter = 1; //used to keep track of number of plays made
        col1x=176; //to place checkers in the correct location
        col1y=72; //to place checkers in the correct location
        colDiff=41; //to place checkers in the correct location
        
        addObject(new Board(),300,200); //adds game board
        
        
        
        
        
    }
    
    public void act(){
        
        
        String winner = checkForWinner();  //see if there is a winner
        if(winner != null || tie()){ //if there is a winner or a tie
                
                
            
                if(winner != null && winner.equals("r"))  //red is the winner
                {
                    showPlayer2Winner();
                    
                }
                else if(winner != null && winner.equals("b")) //black is the winner
                {
                    showPlayer1Winner();
                    
                }
                //resetGrid();
                
                if(turnCounter % (actCounter) == 0) //after a win,allows last checker to fall before the grid is cleared
                {
                    Greenfoot.delay(500); //allows the highlighted winner more time to be seen
                    resetGame();  //resets game
                    
                }
                
            }
            
            
            if(turnCounter % actCounter == 0){  //this controls the speed of the game
                if(firstPlay && firstPlayerTurn == 1){ //runs only first time of game, to alternate first play of each game between players

                    playerBlackStrategy();   //runs player1 code
                    firstPlayerTurn = 2;    //alternates who plays first next game
                    playerTurn = 2;  //switches to other player
                    firstPlay = false;  //will be set back to true when next game starts
                    
                }
                else if(firstPlay && firstPlayerTurn == 2){//runs only first time of game, to alternate first play of each game between players
                    
                    playerRedStrategy();    //runs player2 code
                    firstPlayerTurn = 1;    //alternates who plays first next game
                    playerTurn = 1;   //switches to other player
                    firstPlay = false;  //will be set back to true when next game starts
                    
                }
                else if(playerTurn==1){  //alternates players
                    playerBlackStrategy();   //runs player1 code
                    
                    playerTurn = 2;  //switches to other player
                }
                else if(playerTurn==2){  //alternates players
                    playerRedStrategy();    //runs player2 code
                    
                    playerTurn = 1;   //switches to other player
                }
                
               }
        
      
            
                turnCounter++;    //adds to turn counter
              
                    
    }
    
    public void playerBlackStrategy(){
        
        //leave these top 4 lines at the top
        playerBlack = "Tito";  //put player1 name here
        showText(playerBlack,70,100); //shows your name
        GreenfootImage image1 = new GreenfootImage("black.png"); //sets image to black checker
        String colorInitial = new String("b"); //fills the 2d array grid with this value
        
        
        
        
        //replace the following code with your strategy
        //but the second line shows you how to place your checker
        int colToPlace = (int)(Math.random()*7);
        
     
        
        /*if(playerBlackWinTotal>playerRedWinTotal)
        {
            playerBlackWinTotal++;
            playerRedWinTotal--;
        }*/
        //placeCol(3, colorInitial, image1);
        
        placeCol(colToPlace,colorInitial,image1);  //plays in the specified col and fills the 2D array with the 2nd parameter String
        
        /*
        String[][] map = new String[6][7];
        for(int r=0; r<grid.length; r++)
        {
            for(int c=0; c<grid[0].length; c++)
            {
                
                if(grid[r][c]!=null && grid[r][c].equals(lastPlayedInitial))
                {                    
                    map[r][c] = colorInitial;
                }
                else if(grid[r][c]!=null && grid[r][c].equals(colorInitial))
                {
                    map[r][c] = lastPlayedInitial;
                }    
            }
        }
        
        grid = map;
        
        */
        
        //placeCol(colToPlace, colorInitial, image1);
        
        
    }
    
    
    public void playerRedStrategy(){
        //leave these top 3 lines at the top
        playerRed = "Janet";  //put your name here
        showText(playerRed,getWidth()-70,100); //shows your name
        GreenfootImage image2 = new GreenfootImage("red.png");  //sets image to red checker
        String colorInitial = new String("r");  //fills the 2d array grid with this value
        
        
        if(firstPlay)
        {
            placeCol(3, colorInitial, image2);
        }
        else
        {
           if(getObjects(Checker.class).size()==1)
           {
               if(grid[5][1]!=null)
               {
                 placeCol(2, colorInitial, image2);  
                }
                else if(grid[5][5]!=null)
                {
                 placeCol(4, colorInitial, image2);   
                }
                else
                {
                    placeCol(3, colorInitial, image2);
                }
            }
        }
        
        int colToPlace = (int)(Math.random()*7);
        
        //replace the following code with your strategy
        //but the second line shows you how to place your checker
        
        
        int[][] map = new int[6][7]; //creates gameboard
        for(int r=0; r<map.length; r++)
        {
            for(int c=0; c<map[0].length; c++)
            {
                if(grid[r][c]!=null)
                {
                    if(grid[r][c].equals(colorInitial))
                    {
                        map[r][c] = 1;
                    }
                    else
                    {
                        map[r][c] = -1;
                    }
                }
                else
                {
                 map[r][c] = 0;   
                }
            }
        }
       
        
        //checks possible winning move
        int col=0;
        int round=0;
        boolean posWin = false;
        while(col<grid[0].length && posWin==false && round<2)
        {   
            if(grid[0][col]==null)
            {
                if(round==1)
                {
                    grid[0][col] = "b";
                }
                else
                {
                    grid[0][col] = colorInitial;
                }
                dropGrid();
                if(checkForWinner()!=null)
                {
                  posWin = true;
                  for(int r=0; r<grid.length; r++)
                  {
                     for(int c=0; c<grid[0].length; c++)
                     {
                         if(map[r][c]==0)
                            grid[r][c] = null;
                     }
                  }
                  placeCol(col, colorInitial, image2); //wins 
                }
                else
                {
                  if(col<6)
                  {
                      col++;                      
                  }
                  else
                  {
                     col=0;
                     round++;
                  }
                    for(int r=0; r<grid.length; r++)
                  {
                     for(int c=0; c<grid[0].length; c++)
                     {
                         if(map[r][c]==0)
                            grid[r][c] = null;
                     }
                  }
                }
            }
            else
            {
             col++;   
            }
        }
        //checks possible losing moves
        removeObjects(getObjects(Ring.class));
        
        boolean[] playable = new boolean[7];
        
        int col3 = 0;
        for(int i=0; i<playable.length; i++)
        {
            playable[i] = false;
            if(posWin==false && grid[0][col3]==null)
            {
                playable[i] = true;
                grid[0][col3] = colorInitial;
                dropGrid();
                int col2 = 0;
                while(col2<grid[0].length)
                {
                    if(grid[0][col2]==null)
                    {
                        grid[0][col2] = "b";
                        dropGrid();
                        if(checkForWinner()!=null)
                        {
                         playable[i] = false; 
                         for(int r=0; r<grid.length; r++)
                            {
                             for(int c=0; c<grid[0].length; c++)
                             {
                                 if(map[r][c]==0)
                                    grid[r][c] = null;
                             }
                            }
                        }
                        col2++;
                    }
                    else
                    {
                     col2++;   
                    }
                    
                }
                for(int r=0; r<grid.length; r++)
                {
                 for(int c=0; c<grid[0].length; c++)
                 {
                     if(map[r][c]==0)
                        grid[r][c] = null;
                 }
                }
            }
            
             col3++;   
            
        }
        
        //resets grid
        removeObjects(getObjects(Ring.class));
        for(int r=0; r<grid.length; r++)
        {
            for(int c=0; c<grid[0].length-4; c++)
            {
                if(map[r][c]==0 && r==5)
                {
                    int post = map[r][c+1] + map[r][c+2] + map[r][c+3];
                    if(post==2 || post==-2)
                    {
                        for(int q=1; q<4; q++)
                        {
                            if(map[r][c+q]==0)
                            {
                                placeCol(c+q, colorInitial,image2);
                            }
                        }
                    }
                    
                }
                
                if(map[r][c]==0 && r!=5)
                {
                    int post = map[r][c+1] + map[r][c+2] + map[r][c+3];
                    if(post==2 || post==-2)
                    {
                        boolean works = true;
                        for(int w=0; w<4; w++)
                        {
                            if(map[r+1][c+w]==0)
                            {
                                works = false;
                            }
                        }
                        
                        if(works)
                        {
                            for(int q=1; q<4; q++)
                            {
                                if(map[r][c+q]==0)
                                {
                                    placeCol(c+q, colorInitial,image2);
                                }
                            }
                        }
                    }
                    
                    
                }
            }
        }
        
        
        
        int maxPos = 0;
        for(int x=0; x<playable.length; x++)
        {
            if(playable[x])
            {
                maxPos++;
            }
        }
        int[] moves = new int[maxPos];
        if(maxPos==0)
        {
            placeCol(colToPlace,colorInitial,image2); //failsafe
        }
        else
        {
            int j=0;
            for(int x=0; x<playable.length; x++)
            {
             if(playable[x])
             {
                 moves[j] = x;
                 j++;
                }
            }
            colToPlace = (int) (Math.random()*maxPos);
            placeCol(moves[colToPlace],colorInitial,image2);  //plays in the specified col and fills the 2D array with the 2nd parameter String
        }
        

        
        //placeCol(colToPlace,colorInitial,image2);  //plays in the specified col and fills the 2D array with the 2nd parameter String
         
    }
    
    public void placeCol(int column,String teamInitial, GreenfootImage color){
        
        
            
        if(!lastPlayedInitial.equals(teamInitial)){//ensures placeCol() can only be called once per player per turn
        
            //places checker and updates grid
            if(grid[0][column] == null){//makes sure there is a place to play, if not a random col is selected
                addObject(new Checker(column,color),col1x+(column * colDiff),col1y);
                grid[0][column] = teamInitial; //adds initial to 2d grid
                dropGrid(); //drops teamInitial down the column to bottom
            }
            else{//a random column is selected
                
                placeCol((int)(Math.random()*7),teamInitial, color);
                
            }
        
        }
    
        
        
        
        
        lastPlayedInitial = teamInitial;
        
    }
    
    private void dropGrid()
    {
        
        //drops new "b" or "r" to its new location in grid
        int moveToRow = 0;
        for(int c = 0; c < grid[0].length;c++)
        {
            for(int r = 0; r < grid.length - 1;r++)
            {
                if(grid[r][c] != null && grid[r + 1][c] == null)
                {
                    grid[r+1][c] = grid[r][c];
                    grid[r][c] = null;
                }
                else if(grid[r][c] != null && grid[r + 1][c] != null)
                {
                    r+=8;
                }
            }
        }
        
        
        
        
    }
    
    public String checkForWinner(){
        
        //easier to build a String from the 2d array grid, and then check for consecutive initials
        String checkersAsAString = new String();
       
        //check each row for a winner, starting at bottom
        for(int r = grid.length - 1; r >= 0; r--){
            checkersAsAString = ""; //reset String for each row
            for(int c = 0; c < grid[0].length; c++){
                if(grid[r][c] == null)
                    checkersAsAString+="-";//adds dash to the String if cell is empty
                else
                    checkersAsAString+= grid[r][c]; //adds player initial to String
            
                if(checkersAsAString.contains(playerBlackInitial+playerBlackInitial+playerBlackInitial+playerBlackInitial))
                {
                    circleRow(r,checkersAsAString, playerBlackInitial);  //calls the winning row to be circled
                    return playerBlackInitial;
                }
                else if(checkersAsAString.contains(playerRedInitial+playerRedInitial+playerRedInitial+playerRedInitial))
                {
                    circleRow(r,checkersAsAString, playerRedInitial); //calls the winning row to be circled
                    return playerRedInitial;
                }
            }
        }
        
        
        //check each column for a winner
        for(int c = 0; c < grid[0].length; c++){
            checkersAsAString = "";  //reset String for each column
            for(int r = grid.length - 1; r >= 0; r--){
                if(grid[r][c] == null)
                    checkersAsAString+="-"; //adds dash to the String if cell is empty
                else
                    checkersAsAString+= grid[r][c]; //adds the initial to the String
            
                if(checkersAsAString.contains(playerBlackInitial+playerBlackInitial+playerBlackInitial+playerBlackInitial))
                {
                    circleColumn(c,checkersAsAString,playerBlackInitial);   //highlights winning column
                    return playerBlackInitial;
                }
                else if(checkersAsAString.contains(playerRedInitial+playerRedInitial+playerRedInitial+playerRedInitial))
                {
                    circleColumn(c,checkersAsAString,playerRedInitial);  //highlights winning column
                    return playerRedInitial;
                }
            }
        }
        
        
        //checks for diagonal winner
        //this checks for diagonal starting at bottom right, moving to top left
        String diag1 = new String();  //Six new Strings to be built
        String diag2 = new String();  //for each possible diagonal to win 
        String diag3 = new String();
        String diag4 = new String();
        String diag5 = new String();
        String diag6 = new String();
        for(int r = 0; r < grid.length; r++){
            for(int c = 0; c < grid[0].length; c++){
                
                if(r-c==2){ //checks two diagonals below the middle diagonal
                    if(grid[r][c] == null)
                         diag1 += "-"; //adds a dash if cell is blank
                    else
                         diag1 += grid[r][c]; //adds the player initial to String
                }
                
                if(r-c==1){ //checks one diagonal below the middle diagonal
                    if(grid[r][c] == null)
                         diag2 += "-"; //adds a dash if cell is blank
                    else
                         diag2 += grid[r][c];  //adds the player initial to String
                }
                
                if(r-c==0){//checks the middle diagonal
                    if(grid[r][c] == null)
                         diag3 += "-";
                    else
                         diag3 += grid[r][c];
                }
                
                if(r-c==-1){ //checks one diagonal above the middle diagonal
                    if(grid[r][c] == null)
                         diag4 += "-";
                    else
                         diag4 += grid[r][c];
                }
                
                if(r-c==-2){ //checks two diagonals above the middle diagonal
                    if(grid[r][c] == null)
                         diag5 += "-";
                    else
                         diag5 += grid[r][c];
                }
                
                if(r-c==-3){  //checks three diagonals above the middle diagonal
                    if(grid[r][c] == null)
                         diag6 += "-";
                    else
                         diag6 += grid[r][c];
                }
                
                // //check each concatenated diag String to see if it contains a winning streak for black
                // //if so, call the correct diagonal to be highlighted
                if(containsWinningString(diag1, playerBlackInitial)){
                    circleDiagonal(1,diag1,playerBlackInitial);  //highlight the winning diagonal
                    return playerBlackInitial;
                }
                else if(containsWinningString(diag2, playerBlackInitial)){
                    circleDiagonal(2,diag2,playerBlackInitial);
                    return playerBlackInitial;
                }
                else if(containsWinningString(diag3, playerBlackInitial)){
                    circleDiagonal(3,diag3,playerBlackInitial);
                    return playerBlackInitial;
                }
                else if(containsWinningString(diag4, playerBlackInitial)){
                    circleDiagonal(4,diag4,playerBlackInitial);
                    return playerBlackInitial;
                }
                else if(containsWinningString(diag5, playerBlackInitial)){
                    circleDiagonal(5,diag5,playerBlackInitial);
                    return playerBlackInitial;
                }
                else if(containsWinningString(diag6, playerBlackInitial)){
                    circleDiagonal(6,diag6,playerBlackInitial);
                    return playerBlackInitial;
                }
                
                //same as above but checking to see if red is the winner
                if(containsWinningString(diag1, playerRedInitial)){
                    circleDiagonal(1,diag1,playerRedInitial);
                    return playerRedInitial;
                }
                else if(containsWinningString(diag2, playerRedInitial)){
                    circleDiagonal(2,diag2,playerRedInitial);
                    return playerRedInitial;
                }
                else if(containsWinningString(diag3, playerRedInitial)){
                    circleDiagonal(3,diag3,playerRedInitial);
                    return playerRedInitial;
                }
                else if(containsWinningString(diag4, playerRedInitial)){
                    circleDiagonal(4,diag4,playerRedInitial);
                    return playerRedInitial;
                }
                else if(containsWinningString(diag5, playerRedInitial)){
                    circleDiagonal(5,diag5,playerRedInitial);
                    return playerRedInitial;
                }
                else if(containsWinningString(diag6, playerBlackInitial)){
                    circleDiagonal(6,diag6,playerRedInitial);
                    return playerRedInitial;
                }
                
            }
            
            
        }
        
        
        //reset diagonal Strings so we can now check for diagonal wins starting
        //at bottom left moving up to top right
        diag1="";
        diag2="";
        diag3="";
        diag4="";
        diag5="";
        diag6="";
        //check for diagonal wins starting
        //at bottom left moving up to top right
        for(int r = 0; r < grid.length; r++){
            for(int c = 0; c < grid[0].length; c++){
                
                if(r+c==3){ //checks the diagonal 2 diagonals above the middle diagonal
                    if(grid[r][c] == null)
                         diag1 += "-";
                    else
                         diag1 += grid[r][c];
                }
                
                if(r+c==4){  //checks the diagonal 1 diagonal above the middle diagonal
                    if(grid[r][c] == null)
                         diag2 += "-";
                    else
                         diag2 += grid[r][c];
                }
                
                if(r+c==5){  ////checks the middle diagonal 
                    if(grid[r][c] == null)
                         diag3 += "-";
                    else
                         diag3 += grid[r][c];
                }
                
                if(r+c==6){ //checks the diagonal 1 diagonal below the middle diagonal
                    if(grid[r][c] == null)
                         diag4 += "-";
                    else
                         diag4 += grid[r][c];
                }
                
                if(r+c==7){ //checks the diagonal 2 diagonals below the middle diagonal
                    if(grid[r][c] == null)
                         diag5 += "-";
                    else
                         diag5 += grid[r][c];
                }
                
                if(r+c==8){  //checks the diagonal 3 diagonals below the middle diagonal
                    if(grid[r][c] == null)
                         diag6 += "-";
                    else
                         diag6 += grid[r][c];
                }
                
                
                //checks each concatenated String to see if it contains a win 
                //streak for black
                if(containsWinningString(diag1, playerBlackInitial)){
                    circleBackwardsDiagonal(1,diag1,playerBlackInitial);
                    return playerBlackInitial;
                }
                else if(containsWinningString(diag2, playerBlackInitial)){
                    circleBackwardsDiagonal(2,diag2,playerBlackInitial);
                    return playerBlackInitial;
                }
                else if(containsWinningString(diag3, playerBlackInitial)){
                    circleBackwardsDiagonal(3,diag3,playerBlackInitial);
                    return playerBlackInitial;
                }
                else if(containsWinningString(diag4, playerBlackInitial)){
                    circleBackwardsDiagonal(4,diag4,playerBlackInitial);
                    return playerBlackInitial;
                }
                else if(containsWinningString(diag5, playerBlackInitial)){
                    circleBackwardsDiagonal(5,diag5,playerBlackInitial);
                    return playerBlackInitial;
                }
                else if(containsWinningString(diag6, playerBlackInitial)){
                    circleBackwardsDiagonal(6,diag6,playerBlackInitial);
                    return playerBlackInitial;
                }
                
                //checks each concatenated String to see if it contains a win 
                //streak for red
                if(containsWinningString(diag1, playerRedInitial)){
                    circleBackwardsDiagonal(1,diag1,playerRedInitial);
                    return playerRedInitial;
                }
                else if(containsWinningString(diag2, playerRedInitial)){
                    circleBackwardsDiagonal(2,diag2,playerRedInitial);
                    return playerRedInitial;
                }
                else if(containsWinningString(diag3, playerRedInitial)){
                    circleBackwardsDiagonal(3,diag3,playerRedInitial);
                    return playerRedInitial;
                }
                else if(containsWinningString(diag4, playerRedInitial)){
                    circleBackwardsDiagonal(4,diag4,playerRedInitial);
                    return playerRedInitial;
                }
                else if(containsWinningString(diag5, playerRedInitial)){
                    circleBackwardsDiagonal(5,diag5,playerRedInitial);
                    return playerRedInitial;
                }
                else if(containsWinningString(diag6, playerBlackInitial)){
                    circleBackwardsDiagonal(6,diag6,playerRedInitial);
                    return playerRedInitial;
                }
                
            }
        
        
         }
    
        return null; //returns null if there is no winner
    
    }

    public void circleDiagonal(int diag,String str, String initial){
        //adds the rainbow rings to the correct locations after a win
        
        int c = 0; //dictates the first column in the diagonal to be circled
        int r = 0; //dicates the first row in the diagonal to be circled
        
        int start = str.indexOf(initial+initial+initial+initial);
        
        
        //the steps below set the starting row and column,
        //depending on which diagonal and which indexOf the streak was found
        if(diag==1){
            r=2;
            c=0;
        }
        else if(diag==2 && start == 0){
            r=1;
            c=0;
        }
        else if(diag==2 && start == 1){
            r=2;
            c=1;
        }
        else if(diag==3 && start == 0){
            r=0;
            c=0;
        }
        else if(diag==3 && start == 1){
            r=1;
            c=1;
        }
        else if(diag==3 && start == 2){
            r=2;
            c=2;
        }
        else if(diag==4 && start == 0){
            r=0;
            c=1;
        }
        else if(diag==4 && start == 1){
            r=1;
            c=2;
        }
        else if(diag==4 && start == 2){
            r=2;
            c=3;
        }
        else if(diag==5 && start == 0){
            r=0;
            c=2;
        }
        else if(diag==5 && start == 1){
            r=1;
            c=3;
        }
        else if(diag==6){
            r=0;
            c=3;
        }
        
        
        
        for(int i=start; i < start + 4; i++){
            //places a ring at each correct x,y location
            //according to the rows and columns needed
            addObject(new Ring(),col1x+(c * colDiff),col1y+(r+1)*38);
            c++;
            r++;
        }
        //}
    }
    
    
    public void circleBackwardsDiagonal(int diag, String str, String initial){
        //adds the rainbow rings to the correct locations after a win
        
        int c = 0; //dictates the first column in the diagonal to be circled
        int r = 5; //dicates the first row in the diagonal to be circled
        int repeat = 6; //dicates how many rainbow rings are needed
        int start = str.indexOf(initial+initial+initial+initial);
        
        
        
        //the steps below set the starting row and column,
        //depending on which diagonal and which indexOf the streak was found
        if(diag==1){
            r=3;
            c=0;
        }
        else if(diag==2 && start==0){
            r=3;
            c=1;
        }
        else if(diag==2 && start==1){
            r=4;
            c=0;
        }
        else if(diag==3 && start==0){
            r=3;
            c=2;
        }
        else if(diag==3 && start==1){
            r=4;
            c=1;
        }
        else if(diag==3 && start==2){
            r=5;
            c=0;
        }
        else if(diag==4 && start==2){
            r=5;
            c=1;
        }
        else if(diag==4 && start==1){
            r=4;
            c=2;
        }
        else if(diag==4 && start==0){
            r=3;
            c=3;
        }
        else if(diag==5 && start==0){
            r=4;
            c=3;
        }
        else if(diag==5 && start==1){
            r=5;
            c=2;
        }
        else if(diag==6){
            r=5;
            c=3;
        }
        
        
         
        for(int i=start; i < start+4; i++){
            //places a ring at each correct x,y location
            //according to the rows and columns needed
            addObject(new Ring(),col1x+(c * colDiff),col1y+(r+1)*38);
            c++;
            r--;
        }
        //}
    }
    
    
    
    
    public void circleRow(int r, String str, String initial){
        //adds the rainbow rings to the correct locations after a win
        
        //check the indexOf so we know where the winner rings should start
        int start = str.indexOf(initial+initial+initial+initial);
        
        for(int c = start; c < start+4; c++){
            addObject(new Ring(),col1x+(c * colDiff),col1y+(r+1)*38);
            
        }
    }
    
    
    public void circleColumn(int c, String str, String initial){
        
        //adds the rainbow rings to the correct locations after a win
        
        int start = str.indexOf(initial+initial+initial+initial);
        if(start==2)
            start = 0;
        else if(start==0)
            start = 2;
        
        
            
        for(int r = start; r < start + 4; r++){
            addObject(new Ring(),col1x+(c * colDiff),col1y+(r+1)*38);
            
        }
    }
    
    
    
    public boolean containsWinningString(String str, String playInit){
        
        //checks if a string contains four identical initials in a row
        return str.contains(playInit+playInit+playInit+playInit);
        
    }
    
    
    public boolean tie()
    {
        //if there is a null in the grid, there is not a tie
        for(int i = 0;i<grid[0].length;i++)
        {
            if(grid[0][i]==null)
            {
                return false;
            }
        }
        tieCounter++;
        
        showText("Ties: " + tieCounter/actCounter,getWidth()/2,370); //shows number of ties
        
        
        
        
        
        return true;
    }
    
    public void showPlayer1Winner(){
        //adds 1 to player1 total wins
        playerBlackWinTotal+=1;
        
        //divides by actCounter since this code 
        //runs actCounter times before game is cleared
        int wins = playerBlackWinTotal/actCounter;
        int wins1 = playerRedWinTotal/actCounter;
        int games = wins + wins1 ;
        
        
    
        //calculates and shows all the stats
        showText("Wins: " + wins,70,130); //shows the total wins
        double winPercentage = (double)(wins)/(games);
        double winPercentageRounded = 100*Math.round(winPercentage*1000)/1000.0;
        showText(winPercentageRounded + "%",70,160); //shows the total win %
        double opponentPercentage = Math.round((100.0-winPercentageRounded)*1000)/1000.0;
        showText(opponentPercentage + "%",getWidth()-70,160); //shows the total win % for other player
    
        addObject(new BigRing(),70,130); //highlights winning name
        
        
    
        
        
    }
    
    public void showPlayer2Winner(){
        
        //adds 1 to player2 total wins
        playerRedWinTotal+=1;
        
        //divides by actCounter since this code 
        //runs actCounter times before game is cleared
        int wins = playerBlackWinTotal/actCounter;
        int wins1 = playerRedWinTotal/actCounter;
        int games = wins + wins1 ;
        
        
        
        //calculates and shows all the stats
        showText("Wins: " + wins1,getWidth()-70,130); //shows the total wins
        double winPercentage = (double)(wins1)/(games);
        double winPercentageRounded = 100*Math.round(winPercentage*1000)/1000.0;
        showText(winPercentageRounded + "%",getWidth()-70,160); //shows the total win %
        double opponentPercentage = Math.round((100.0-winPercentageRounded)*1000)/1000.0;
        showText(opponentPercentage + "%",70,160); //shows the total win % for other player
    
        addObject(new BigRing(),getWidth()-70,130); //highlights winning name
        
        
    
    }
    
    public void resetGrid(){
        //resets 2d array after a victory
         for(int r = 0; r < grid.length; r++){
            for(int c = 0; c < grid[0].length; c++){
            
            grid[r][c] = null;
            
            
            }
            
        }
        
        
    }
    
    public void resetGame(){
        
        gameCounter++;
        showText("Games played : " + gameCounter,getWidth()/2,15); //shows number of games
        
        
        //clears all checkers, rings, and board
        List objects = getObjects(null);
        removeObjects(objects);
        
        addObject(new Board(),300,200); //adds game board
        
        resetGrid(); //clears 2d array
        firstPlay = true; //resets firstPlay to true
        lastPlayedInitial = ""; //allows the first player of the next game to alternate
        playerTurn = 0;
        //so the first player of each game will alternate
        
    }
    
    
    public String[][] getGrid(){
        return this.grid;
    }
}
