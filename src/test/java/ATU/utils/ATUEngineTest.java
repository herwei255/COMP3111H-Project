package ATU;

import ATU.models.Person;
import ATU.utils.ATUEngine;

import java.util.Random;
import java.io.PrintStream;
import java.io.ByteArrayOutputStream;
import java.util.concurrent.ThreadLocalRandom;

import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.*;

class ATUEngineTest {

    ATUEngine atuEngine;
    Person[] persons;
    Person[] hundredPersons = new Person[100];
    Person[] fourPerson = new Person[4];


    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final ByteArrayOutputStream errContent = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;
    private final PrintStream originalErr = System.err;

    @BeforeEach
    public void setUpStreams() {
        System.setOut(new PrintStream(outContent));
        System.setErr(new PrintStream(errContent));
    }

    @AfterEach
    public void restoreStreams() {
        System.setOut(originalOut);
        System.setErr(originalErr);
    }

    @BeforeEach
    void setUp() {
        persons = new Person[3];
        persons[0] = new Person("00000001", "Blaise Liu", "blaiseLIU92@connect.ust.hk", "30", "20", "", "1", "1", "");
        persons[1] = new Person("00000002", "Jesus Liu", "jesus92@connect.ust.hk", "50", "40", "1", "1", "0", "");
        persons[2] = new Person("00000003", "Dose Liu", "doiseLIU92@connect.ust.hk", "70", "60", "1", "0", "0", "");
        atuEngine = new ATUEngine(persons);
    }

    @Test
    void testWorkingModifiedCout() {
        System.out.print("hello");
        assertEquals("hello", outContent.toString());
    }

    @Test
    void testPrintArray() {
        atuEngine.printArray(persons);
        String out = "0 00000003 70 60\n1 00000002 50 40\n2 00000001 30 20\n";

        assertEquals(outContent.toString().replaceAll("\n", "").replaceAll("\r", ""), 
                    out.toString().replaceAll("\n", "").replaceAll("\r", ""));
    }
    @Test
    void testCreateGroup() {
        //test if 3 person will result in one group
        Person[][] groups = atuEngine.createGroups();
        assertEquals(groups.length, 1);
    }

    //test if 100 person will result in 33 groups
    @Test
    void testCreateGroups1() {
        int num = 100;
        for (int i = 0; i < num; i++) {
            Random r = new Random();
            String leader = String.valueOf(r.nextInt(30) < 25 ? 1 : 0);
            int randomBool1 = r.nextInt(0,1);
            int randomBool2 = r.nextInt(0,1);
            int randomIntK1 = r.nextInt(100) + 1;
            int randomIntK2 = r.nextInt(100) + 1;

            String k3Tick1 = String.valueOf(randomBool1);
            String k3Tick2 = String.valueOf(randomBool2);
            String k1 = String.valueOf(randomIntK1);
            String k2 = String.valueOf(randomIntK2);
            
            hundredPersons[i] = new Person(String.valueOf(k1+k2+leader), "Blaise Liu", "blaiseLIU92@connect.ust.hk", k1, k2, k3Tick1, k3Tick2, leader, "");
        }
        atuEngine = new ATUEngine(hundredPersons);
        Person[][] groups = atuEngine.createGroups();
        assertEquals(groups.length, 33);
    }

    @Test
    void testCreateGroups2() {
         //test if 4 person will result in 1 group
        int num = 4; 
        for (int i = 0; i < num; i++) {
            Random r = new Random();
            String leader = String.valueOf(r.nextInt(30) < 25 ? 1 : 0);
            int randomBool1 = r.nextInt(0,1);
            int randomBool2 = r.nextInt(0,1);
            int randomIntK1 = r.nextInt(100) + 1;
            int randomIntK2 = r.nextInt(100) + 1;

            String k3Tick1 = String.valueOf(randomBool1);
            String k3Tick2 = String.valueOf(randomBool2);
            String k1 = String.valueOf(randomIntK1);
            String k2 = String.valueOf(randomIntK2);
            
            fourPerson[i] = new Person(String.valueOf(k1+k2+leader), "Blaise Liu", "blaiseLIU92@connect.ust.hk", k1, k2, k3Tick1, k3Tick2, leader, "");
        }
  
        atuEngine = new ATUEngine(fourPerson);
        Person[][] groups = atuEngine.createGroups();
        assertEquals(groups.length, 1);
    }

    // stress test for every number of people between 200-500
    @Test
    void testCreateGroups3() {
        Random r = new Random();
        for (int num = 200; num < 501; num++) {
            Person[] Morepersons = new Person[num];
            for (int i = 0; i < num; i++) {
                Morepersons[i] = new Person("00000003", "Dose Liu", "doiseLIU92@connect.ust.hk", "70", "60", "1", "0", "0", "");
            }
            atuEngine = new ATUEngine(Morepersons);
            Person[][] groups = atuEngine.createGroups();
            assertEquals(groups.length, num/3);
        }
    }
}