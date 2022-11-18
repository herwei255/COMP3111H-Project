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
    void setEmail() {
        String email = "newEmail@connect.ust.hk";
        person.setEmail(email);
        assertEquals(
            person.getEmail(), email
        );
    }

    @Test
    void getIndex() {
        assertEquals(
            person.getIndex(), "1"
        );
    }

    @Test
    void setIndex() {
        String index = "2";
        person.setIndex(index);
        assertEquals(
            person.getIndex(), index
        );
    }

    @Test
    void getStudentid() {
        assertEquals(
            person.getStudentid(), "002876492"
        );
    }

    @Test
    void setStudentid() {
        String studentid = "002876493";
        person.setStudentid(studentid);
        assertEquals(
            person.getStudentid(), studentid
        );
    }

    @Test
    void getStudentname() {
        assertEquals(
            person.getStudentname(), "Blaise Liu"
        );
    }

    @Test
    void setStudentname() {
        String studentname = "blza liu";
        person.setStudentname(studentname);
        assertEquals(
            person.getStudentname(), studentname
        );
    }

    @Test
    void getK1energy() {
        assertEquals(
            person.getK1energy(), "10"
        );
    }

    @Test
    void setK1energy() {
        String k1energy = "11";
        person.setK1energy(k1energy);
        assertEquals(
            person.getK1energy(), k1energy
        );
    }

    @Test
    void getK2energy() {
        assertEquals(
            person.getK2energy(), "12"
        );
    }

    @Test
    void setK2energy() {
        String k2energy = "13";
        person.setK2energy(k2energy);
        assertEquals(
            person.getK2energy(), k2energy
        );
    }

    @Test
    void getK3trick1() {
        assertEquals(
            person.getK3trick1(), "1"
        );
    }

    @Test
    void setK3trick1() {
        String k3trick1 = "0";
        person.setK3trick1(k3trick1);
        assertEquals(
            person.getK3trick1(), k3trick1
        );
    }

    @Test
    void getK3trick2() {
        assertEquals(
            person.getK3trick2(), "1"
        );
    }

    @Test
    void setK3trick2() {
        String k3trick2 = "0";
        person.setK3trick2(k3trick2);
        assertEquals(
            person.getK3trick2(), k3trick2
        );
    }

    @Test
    void getMypreference() {
        assertEquals(
            person.getMypreference(), "3"
        );
    }

    @Test
    void setMypreference() {
        String mypreference = "0";
        person.setMypreference(mypreference);
        assertEquals(
            person.getMypreference(), mypreference
        );
    }

    @Test
    void getConcerns() {
        assertEquals(
            person.getConcerns(), "3"
        );
    }

    @Test
    void setConcerns() {
        String concerns = "i want good teammates";
        person.setConcerns(concerns);
        assertEquals(
            person.getConcerns(), concerns
        );
    }
}