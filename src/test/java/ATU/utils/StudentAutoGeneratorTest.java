package ATU.utils;

import ATU.models.Person;
import ATU.utils.StudentAutoGenerator;
import ATU.utils.StudentAutoGenerator.StudentGeneratorStatistics;

import java.util.ArrayList;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class StudentAutoGeneratorTest {
    StudentGeneratorStatistics stats;
    int numberOfStudents = 500;
    int k1AvgEnergy = 80;
    int k2AvgEnergy = 80;
    int probK3Tick1 = 20;
    int probK3Tick2 = 20;
    int myPref = 5;
    @BeforeEach
    void setUp() {
        stats = new StudentAutoGenerator.StudentGeneratorStatistics(numberOfStudents,k1AvgEnergy,k2AvgEnergy,probK3Tick1,probK3Tick2,myPref);
    }

    @Test
    void testGenerate() {
        ArrayList<Person> students = StudentAutoGenerator.Generate(stats);
        int k1AvgCounter = 0;
        int k2AvgCounter = 0;
        int K3Tick1Counter = 0;
        int K3Tick2Counter = 0;
        int myPrefCounter = 0;
        //looping through students
        for (Person student : students) {
                k1AvgCounter += Integer.parseInt(student.getK1energy());
                k2AvgCounter += Integer.parseInt(student.getK2energy());
                K3Tick1Counter += Integer.parseInt(student.getK3trick1());
                K3Tick2Counter += Integer.parseInt(student.getK3trick2());
                myPrefCounter += Integer.parseInt(student.getMypreference());
        }

        assert(students.size() == numberOfStudents);
        //asserting that the average of each preference is within 5% of the expected value
        assert(Math.abs(k1AvgCounter/numberOfStudents - k1AvgEnergy) <= 5);
        assert(Math.abs(k2AvgCounter/numberOfStudents - k2AvgEnergy) < 5);
        assertEquals(Math.abs(K3Tick1Counter - (double)probK3Tick1/100 * numberOfStudents) <= 15, true);
        assertEquals(Math.abs(K3Tick2Counter - (double)probK3Tick2/100 * numberOfStudents) <= 15, true);
        assertEquals(Math.abs(myPrefCounter  * 100 -  myPref * numberOfStudents) <= 5, true);
    }
}