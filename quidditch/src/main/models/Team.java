package main.models;

import java.util.Arrays;
import java.util.Objects;

public class Team {

    private static final String POSITION_CHASER = "chaser";
    private static final String POSITION_SEEKER = "seeker";
    private static final String POSITION_KEEPER = "keeper";
    private String house;
    private String keeper;
    private String seeker;
    private String[] chasers;



    public Team(String house, String keeper, String seeker, String[] chasers)
    {
        checkParameter(house);
        checkParameter(seeker);
        checkParameter(keeper);
        if(chasers.length != 3) {
            throw new IllegalArgumentException("the chasers array must sized 3");
        }
        if(hasBlank(chasers) || hasNull(chasers)) {
            throw new IllegalArgumentException("Illegal elements");
        }
        this.house = house;
        this.keeper = keeper;
        this.seeker = seeker;
        this.chasers = Arrays.copyOf(chasers, chasers.length);
    }


    public Team(Team source)
    {
        this.house = source.house;
        this.keeper = source.keeper;
        this.seeker = source.seeker;
        this.chasers = Arrays.copyOf(source.chasers, source.chasers.length);
    }


    public String[] getChasers()
    {
        return Arrays.copyOf(this.chasers, this.chasers.length);
    }


    public String getHouse()
    {
        return house;
    }


    public String getKeeper()
    {
        return keeper;
    }


    public String getSeeker()
    {
        return seeker;
    }


    public void setHouse(String house)
    {
        checkParameter(house);
        this.house = house;
    }


    public void setKeeper(String keeper)
    {
        checkParameter(keeper);
        this.keeper = keeper;
    }


    public void setSeeker(String seeker)
    {
        checkParameter(seeker);
        this.seeker = seeker;
    }


    public void setChasers(String[] chasers)
    {
        if(chasers.length != 3) {
            throw new IllegalArgumentException("the chasers array must sized 3");
        }
        if(hasBlank(chasers) || hasNull(chasers)) {
            throw new IllegalArgumentException("Illegal elements");
        }
        this.chasers = Arrays.copyOf(chasers, chasers.length);
    }


    public static String getPositionChaser()
    {
         return POSITION_CHASER;
    }


    public static String getPositionSeeker()
    {
         return POSITION_SEEKER;
    }


    public static String getPositionKeeper()
    {
         return POSITION_KEEPER;
    }


    public String toString()
    {
        return "House: " + this.house + "\n" +
        "Keeper: " + this.keeper + "\n" +         
        "Seeker: "  + this.seeker + "\n" +         
        "Chasers: " + Arrays.toString(this.chasers) + "\n";
    }


     public void checkParameter(String param)
     {
         if(param == null || param.isBlank()) {
             throw new IllegalArgumentException(param + " canot be null/blank");
         }
     }


     public static boolean hasNull(String[] array)
     {
        return Arrays.stream(array).anyMatch((word) -> word == null);
     }


     public static boolean hasBlank(String[] array)
     {
         return Arrays.stream(array).anyMatch((word) -> word.isBlank());
     }


     public boolean equals(Object obj)
     {
         if(obj == null || !(obj instanceof Team)) 
            return false;
        Team team = (Team) obj;

        return this.house.equals(team.house) &&
               this.keeper.equals(team.keeper) &&
               this.seeker.equals(team.seeker) &&
               Arrays.toString(this.chasers).equals(Arrays.toString(team.chasers));
     }


     public int hashCode()
     {
         return Objects.hash(house, keeper, seeker, Arrays.toString(chasers));
     }
     

}