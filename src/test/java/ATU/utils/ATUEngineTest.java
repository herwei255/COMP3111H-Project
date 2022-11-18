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
    void testCreateGroups() {
        //test if 3 person will result in one group
        Person[][] groups = atuEngine.createGroups();
        assertEquals(groups.length, 1);
        //test if 30 person will result in 10 groups
        int num = 100;
        Person[] Morepersons = new Person[num];
        for (int i = 0; i < num; i++) {
            String leader = String.valueOf(i % 2);
            Random r = new Random();
            int randomIntK1 = r.nextInt(100) + 1;
            int randomIntK2 = r.nextInt(100) + 1;
            String k1 = String.valueOf(randomIntK1);
            String k2 = String.valueOf(randomIntK2);
            Morepersons[i] = new Person(String.valueOf(k1+k2+leader), "Blaise Liu", "blaiseLIU92@connect.ust.hk", k1, k2, "", "1", leader, "");
        }
        atuEngine = new ATUEngine(Morepersons);
        groups = atuEngine.createGroups();
        assertEquals(groups.length, 33);


        //more leaders
        for (int i = 0; i < num; i++) {
            String leader = String.valueOf(i % 10 > 1 ? 1 : 0);
            Random r = new Random();
            int randomIntK1 = r.nextInt(100) + 1;
            int randomIntK2 = r.nextInt(100) + 1;
            String k1 = String.valueOf(randomIntK1);
            String k2 = String.valueOf(randomIntK2);
            Morepersons[i] = new Person(String.valueOf(k1+k2+leader), "Blaise Liu", "blaiseLIU92@connect.ust.hk", k1, k2, "", "1", leader, "");
        }
        atuEngine = new ATUEngine(Morepersons);
        groups = atuEngine.createGroups();
        assertEquals(groups.length, 33);

        //test if 4 person will result in 1 group
        Person[] fourPerson = new Person[4];
        fourPerson[0] = new Person("00000001", "Blaise Liu", "blaiseLIU92@connect.ust.hk", "30", "20", "", "1", "1", "");
        fourPerson[1] = new Person("00000002", "Jesus Liu", "jesus92@connect.ust.hk", "50", "40", "1", "1", "0", "");
        fourPerson[2] = new Person("00000003", "Dose Liu", "doiseLIU92@connect.ust.hk", "70", "60", "1", "0", "0", "");
        fourPerson[3] = new Person("00000004", "Chara Liu", "doifdLIU92@connect.ust.hk", "30", "50", "1", "0", "0", "");
        atuEngine = new ATUEngine(fourPerson);
        groups = atuEngine.createGroups();
        assertEquals(groups.length, 1);
    }
}