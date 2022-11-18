package ATU.models;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PersonTest {

    Person person;

    @BeforeAll
    void setUp() {
        person = new Person("1", "002876492", "Blaise Liu", "blaiseLIU92@connect.ust.hk",
             "10", "12", "1", "1", "3");
    }

    @Test
    void getEmail() {
        assertEquals(
            person.getEmail(), "blaiseLIU92@connect.ust.hk"
        );
    }

    @Test
    void emailProperty() {
    }

    @Test
    void setEmail() {
    }

    @Test
    void getGroupID() {
    }

    @Test
    void setGroupID() {
    }

    @Test
    void getIndex() {
    }

    @Test
    void setIndex() {
    }

    @Test
    void getStudentid() {
    }

    @Test
    void setStudentid() {
    }

    @Test
    void getStudentname() {
    }

    @Test
    void setStudentname() {
    }

    @Test
    void getK1energy() {
    }

    @Test
    void setK1energy() {
    }

    @Test
    void getK2energy() {
    }

    @Test
    void setK2energy() {
    }

    @Test
    void getK3trick1() {
    }

    @Test
    void setK3trick1() {
    }

    @Test
    void getK3trick2() {
    }

    @Test
    void setK3trick2() {
    }

    @Test
    void getMypreference() {
    }

    @Test
    void setMypreference() {
    }

    @Test
    void getConcerns() {
    }

    @Test
    void setConcerns() {
    }
}