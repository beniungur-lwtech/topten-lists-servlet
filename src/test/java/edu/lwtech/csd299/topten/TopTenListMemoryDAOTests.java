package edu.lwtech.csd299.topten;

import java.util.*;
import org.junit.*;
import static org.junit.Assert.*;

public class TopTenListMemoryDAOTests {

    private DAO<TopTenList> memoryDAO;

    private TopTenList romanList;
    private TopTenList planetsList;
    private TopTenList starWarsList;

    @Before
    public void setUp() {
        String description;
        List<String> items;
        int owner = 1;
    
        description = "Top 10 Favorite Roman Numerals";
        items = Arrays.asList(
            "X",
            "IX",
            "VIII",
            "VII",
            "VI",
            "V",
            "IV",
            "III",
            "II",
            "I"
        );
        romanList = new TopTenList(description, items, owner);

        description = "Top 10 Favorite Planets";
        items = Arrays.asList(
            "Hollywood",
            "Neptune",
            "Uranus",
            "Venus",
            "Mercury",
            "Earth",
            "Mars",
            "Jupiter",
            "Saturn",
            "Pluto!!!"
        );
        planetsList = new TopTenList(description, items, owner);
        
        description = "Top 10 Favorite Star Wars Movies";
        items = Arrays.asList(
            "III: Revenge of the Sith",
            "II: Attack of the Clones",
            "VIII: The Last Jedi",
            "IX: The Rise of Skywalker",
            "VI: Return of the Jedi",
            "I: The Phantom Menace",
            "VII: The Force Awakens",
            "The Mandelorian Compilation",
            "IV: A New Hope",
            "V: The Empire Strikes Back"
        );
        starWarsList = new TopTenList(description, items, owner);

        memoryDAO = new TopTenListMemoryDAO();
        memoryDAO.insert(romanList);
        memoryDAO.insert(planetsList);
        memoryDAO.insert(starWarsList);
    }

    @Test
    public void insertTest() {
        assertEquals(3, memoryDAO.size());
        int listID = memoryDAO.insert(romanList);        // Add a second copy of the roman list
        assertEquals(4, memoryDAO.size());
        assertTrue(listID > 0);
    }
    
    @Test
    public void getByIDTest() {
        TopTenList list = memoryDAO.getByID(1000);
        assertEquals(1000, list.getID());
        list = memoryDAO.getByID(1001);
        assertEquals(1001, list.getID());
    }
    
    @Test
    public void getByIndexTest() {
        TopTenList list = memoryDAO.getByIndex(0);
        assertEquals(1000, list.getID());
        list = memoryDAO.getByIndex(1);
        assertEquals(1001, list.getID());
    }
    
    @Test
    public void getAllTest() {
        List<TopTenList> allLists = new ArrayList<>();
        allLists = memoryDAO.getAll();
        assertEquals(3, allLists.size());
    }

    @Test
    public void updateTest() {
        TopTenList list = memoryDAO.getByID(1000);
        list.publish(1);
        memoryDAO.update(list);
        list = memoryDAO.getByID(1000);
        assertTrue(list.isPublished());
    }
    
}
