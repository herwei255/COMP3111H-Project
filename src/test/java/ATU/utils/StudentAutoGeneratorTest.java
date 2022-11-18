package ATU.utils;

import ATU.models.Person;
import javafx.stage.Stage;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class StudentAutoGeneratorTest {


    // @BeforeEach
    // void setUp() {
    //
    // }

    @Test
    void generate() {
        ArrayList<Person> personArrayList = StudentAutoGenerator.Generate(
                new StudentAutoGenerator.StudentGeneratorStatistics(3, 1, 1, 100, 100, 100)
        );

        assertTrue(personArrayList.stream().allMatch(person -> person.getK3trick1() == "1"));
        assertFalse(personArrayList.stream().allMatch(person -> person.getK3trick1() == "0"));

        assertTrue(personArrayList.stream().allMatch(person -> person.getK3trick2() == "1"));
        assertTrue(personArrayList.stream().allMatch(person -> person.getMypreference() == "1"));


    }

    @Test
    void setKEnergy() {
    }
}