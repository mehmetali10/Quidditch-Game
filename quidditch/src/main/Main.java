package main;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

import main.models.Game;
import main.models.Team;

public class Main {

    static Game game;
    static final String TEAMS_FILE = "src/main/teams.txt";
    static final String PLAYS_FILE = "src/main/plays.txt";

    public static void main(String[] args) throws InterruptedException {
      
      try
      {
         String[][] data = getData();
         game = new Game(
         new Team(data[0][0], data[0][1], data[0][2], new String[] {data[0][3], data[0][4], data[0][5]}),
         new Team(data[1][0], data[1][1], data[1][2], new String[] {data[1][3], data[1][4], data[1][5]}));
         startGame();
         System.out.println("\n\n");
         printResult();
      } catch(FileNotFoundException e)
      {
         System.out.println(e.getMessage());
      } 
      
    }

    //gets teams information from teams.txt
    public static String[][] getData() throws FileNotFoundException
    {
       FileInputStream fis = new FileInputStream(TEAMS_FILE);
       Scanner scan = new Scanner(fis);
       String[] lines = new String[]{scan.nextLine(), scan.nextLine()};
       scan.close();
       
       return new String[][] {lines[0].split(","), lines[1].split(",")};
    }

    //starts and simulates all game at the same time
    public static void startGame() throws FileNotFoundException, InterruptedException
    {
       FileInputStream fis = new FileInputStream(PLAYS_FILE);
       Scanner scan = new Scanner(fis);
       while(scan.hasNextLine()) {
          wait(5);
          System.out.println("\n" + game.simulate(scan.nextLine()) + "\n");
       }
       scan.close();
    }


    //prints result and winner
    public static void printResult()
    {
       Team gryffındor = game.getTeam("GRYFFINDOR");
       Team slytherin = game.getTeam("SLYTHERIN");
       Team winner = game.getScore(gryffındor) > game.getScore(slytherin) ? gryffındor : slytherin ;
      System.out.println("\nGRYFFINDOR: " + game.getScore(gryffındor) + " SLYTHERIN: " + game.getScore(slytherin));
      System.out.println("\n" + winner.getHouse() + " WINS!!");  
   }

   //when this func used the system will sleep @param sec duration..
    public static void wait(int sec) throws InterruptedException
    {
       TimeUnit.SECONDS.sleep(sec);
    }


  }