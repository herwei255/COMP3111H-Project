package ATU.models;

import javafx.beans.property.SimpleStringProperty;

/**
 * Person class
 * Each student is initialized as a person object
 */
public class Person {
    /**
     * Represents the starting index for person class creation
     */
    public static Integer studentIndex = 0;

    /**
     * Represents the id of the person(student)
     */
    private final SimpleStringProperty index;

    /**
     * Represents the id of the person(student)
     */
    private final SimpleStringProperty studentid;

    /**
     * Represents the name of the person(student)
     */
    private final SimpleStringProperty studentname;

    /**
     * Represents the email of the person(student)
     */
    private final SimpleStringProperty email;

    /**
     * Represents the k1 energy of the person(student)
     */
    private final SimpleStringProperty k1energy;

    /**
     * Represents the k2 energy of the person(student)
     */
    private final SimpleStringProperty k2energy;

    /**
     * Represents the k3 tick1 of the person(student)
     */
    private final SimpleStringProperty k3trick1;

    /**
     * Represents the k3 tick2 of the person(student)
     */
    private final SimpleStringProperty k3trick2;

    /**
     * Represents the preference of the person(student)
     */
    private final SimpleStringProperty mypreference;

    /**
     * Represents the concerns of the person(student)
     */
    private final SimpleStringProperty concerns;

    /**
     * Default constructor for the person object
     *
     * @param student_id    The student's student ID
     * @param student_name  The student's name
     * @param email         The student's email
     * @param k1_energy     The student's k1 energy
     * @param k2_energy     The student's k2 energy
     * @param k3_trick1     The student's k3 tick 1
     * @param k3_trick2     The student's k3 tick 2
     * @param my_preference The student's preference
     * @param concerns      The student's concerns
     */
    public Person(String student_id, String student_name, String email, String k1_energy, String k2_energy, String k3_trick1,
                  String k3_trick2, String my_preference, String concerns) {
        this.index = new SimpleStringProperty((studentIndex++).toString());
        this.studentid = new SimpleStringProperty(student_id);
        this.studentname = new SimpleStringProperty(student_name);
        this.email = new SimpleStringProperty(email);
        this.k1energy = new SimpleStringProperty(k1_energy);
        this.k2energy = new SimpleStringProperty(k2_energy);
        this.k3trick1 = new SimpleStringProperty(k3_trick1);
        this.k3trick2 = new SimpleStringProperty(k3_trick2);
        this.mypreference = new SimpleStringProperty(my_preference);
        this.concerns = new SimpleStringProperty(concerns);
    }

    /**
     * @return Return the student's email
     */
    public String getEmail() {
        return email.get();
    }

    /**
     * @param email The student's email
     */
    public void setEmail(String email) {
        this.email.set(email);
    }

    /**
     * @return Return the student's index
     */
    public String getIndex() {
        return index.get();
    }

    /**
     * @param index The student's index
     */
    public void setIndex(String index) {
        this.index.set(index);
    }

    /**
     * @return Return the student's ID
     */
    public String getStudentid() {
        return studentid.get();
    }

    /**
     * @param val The student's ID
     */
    public void setStudentid(String val) {
        studentid.set(val);
    }

    /**
     * @return Return the student's name
     */
    public String getStudentname() {
        return studentname.get();
    }

    /**
     * @param val The student's name
     */
    public void setStudentname(String val) {
        studentname.set(val);
    }

    /**
     * @return Return the student's k1 energy
     */
    public String getK1energy() {
        return k1energy.get();
    }

    /**
     * @param val The student's k1 energy
     */
    public void setK1energy(String val) {
        k1energy.set(val);
    }

    /**
     * @return Return the student's k2 energy
     */
    public String getK2energy() {
        return k2energy.get();
    }

    /**
     * @param val The student's k2 energy
     */
    public void setK2energy(String val) {
        k2energy.set(val);
    }

    /**
     * @return Return the student's k3 trick 1
     */
    public String getK3trick1() {
        return k3trick1.get();
    }

    /**
     * @param val The student's k3 trick 1
     */
    public void setK3trick1(String val) {
        k3trick1.set(val);
    }

    /**
     * @return Return the student's k3 trick 2
     */
    public String getK3trick2() {
        return k3trick2.get();
    }

    /**
     * @param val The student's k3 trick 2
     */
    public void setK3trick2(String val) {
        k3trick2.set(val);
    }

    /**
     * @return Return the student's preference
     */
    public String getMypreference() {
        return mypreference.get();
    }

    /**
     * @param val The student's preference
     */
    public void setMypreference(String val) {
        mypreference.set(val);
    }

    /**
     * @return Return the student's concerns
     */
    public String getConcerns() {
        return concerns.get();
    }

    /**
     * @param val The student's concerns
     */
    public void setConcerns(String val) {
        concerns.set(val);
    }
}