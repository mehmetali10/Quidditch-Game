package main.models;

import java.util.HashMap;

public class Game{

    HashMap<Team, Integer> scoreBoard;
    private static int gameCount;
    private static final int QUAFLLE_POİNT = 10;
    private static final int SLYTHERIN_POİNT = 150;

    public Game(Team home, Team away)
    {
        scoreBoard = new HashMap<Team, Integer>();
        scoreBoard.put(new Team(home), 0);
        scoreBoard.put(new Team(away), 0);
        gameCount++;
    }


    public Integer getScore(Team team)
    {
        return scoreBoard.get(team); //returns score of the param team
    }


    public static int getQuafllePoint()
    {
        return QUAFLLE_POİNT;
    }


    public static int getSlytherinPoint()
    {
        return SLYTHERIN_POİNT;
    }


    public void setScore(Team team, Integer score)
    {
        if(team == null) {
            throw new IllegalArgumentException("Team cannot be null");
        }
        scoreBoard.put(team, score); 
    }


    public static int getGameCount()
    {
        return gameCount;
    }


    public Team getTeam(String name)
    {
        return scoreBoard.keySet().stream()
        .filter((key) -> key.getHouse().equals(name))
        .findFirst()
        .orElse(null);
    }

    
    public String getPlaceholder(String play)
    {
        return play.substring(play.indexOf("<") + 1, play.indexOf(">") );
    }

   
    public String replacePlaceholder(String play, String placeholder, String value)
    {
        return play.replace("<" + placeholder + ">", value);
    }

    
    public void quaffleScore(Team team)
    {
        this.setScore(team, this.getScore(team) + QUAFLLE_POİNT);
    }

    
    public void catchSnitch(Team team)
    {
        this.setScore(team, this.getScore(team) + SLYTHERIN_POİNT);
    }

    public Team getRandomTeam()
    {
         Object[] teams = scoreBoard.keySet().toArray();
         return (Team) teams[random(teams.length)];    
    }

    public int random(int range)
    {
        return (int) (Math.random()*range);
    }


    public String simulate(String play)
    {
        String placeholder = getPlaceholder(play);
        Team team = getRandomTeam();
        String value = "";

        if(placeholder.equals(Team.getPositionChaser()))
        {
            quaffleScore(team);
            String chaser = team.getChasers()[random(team.getChasers().length)];
            value = chaser;
        } 
        else if(placeholder.equals(Team.getPositionSeeker()))
        {
            catchSnitch(team);
            value = Team.getPositionSeeker();
        } 
        else if(placeholder.equals(team.getKeeper()))
        {
           value = team.getKeeper();
        } 

        return replacePlaceholder(play, placeholder, value);
    }

}