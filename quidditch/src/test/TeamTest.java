package test;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.Before;
import org.junit.Test;

import main.models.Team;

public class TeamTest {
    Team team;



    @Test
    public void hasNullTest()
    {
        assertTrue(Team.hasNull(new String[] {null, "Ginny", "Katie"}));
    }

    @Test
    public void hasBlakTest()
    {
        assertTrue(Team.hasBlank(new String[] {"  ", "Ginny", "Katie"}));
    }

    
}
