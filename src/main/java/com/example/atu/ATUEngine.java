package com.example.atu;

import com.example.atu.MainController.Person;

public class ATUEngine { 
    private Person[] k1_students;
    private Person[] k2_students;
    private int numStudents;

    public String toString(Object o) {
    return o.getClass().getName() + "@" + 
           Integer.toHexString(System.identityHashCode(o));
    }

    public void printArray(Person[] array) {
        for (int i = 0; i < array.length; i++) {
            if (array[i] != null) {
                System.out.println(array[i].getStudentid() + " " + array[i].getK1energy() + " " + array[i].getK2energy());
            }
        }
    }

    public ATUEngine(Person[] people) {
        // from the given person array, create two different arrays based on ascending k1 and descending k2 students into the respective arrays
        // deep copy people array into k1_students and k2_students
        k1_students = new Person[people.length];
        k2_students = new Person[people.length];
        numStudents = people.length;
        for (int i = 0; i < people.length; i++) {
            // deep copy
            k1_students[i] = new Person(people[i].getStudentid(), people[i].getStudentname(), people[i].getEmail(), people[i].getK1energy(), people[i].getK2energy(), people[i].getK3trick1(), people[i].getK3trick2(), people[i].getMypreference(), people[i].getConcerns());    
            k2_students[i] = new Person(people[i].getStudentid(), people[i].getStudentname(), people[i].getEmail(), people[i].getK1energy(), people[i].getK2energy(), people[i].getK3trick1(), people[i].getK3trick2(), people[i].getMypreference(), people[i].getConcerns());
            // print the address of the object
            // System.out.println("k1_students[" + i + "] address = " + toString(k1_students[i]));
            // System.out.println("k2_students[" + i + "] address = " + toString(k2_students[i]));

        }

        // sort k1_students by ascending k1
        for (int i = 0; i < k1_students.length; i++) {
            for (int j = 0; j < k1_students.length - 1; j++) {
                if (Integer.parseInt(k1_students[j].getK1energy()) > Integer.parseInt(k1_students[j + 1].getK1energy())) {
                    Person temp = k1_students[j];
                    k1_students[j] = k1_students[j + 1];
                    k1_students[j + 1] = temp;
                }
            }
        }

        // sort k2_students by descending k2
        for (int i = 0; i < k2_students.length; i++) {
            for (int j = 0; j < k2_students.length - 1; j++) {
                if (Integer.parseInt(k2_students[j].getK2energy()) < Integer.parseInt(k2_students[j + 1].getK2energy())) {
                    Person temp = k2_students[j];
                    k2_students[j] = k2_students[j + 1];
                    k2_students[j + 1] = temp;
                }
            }
        }
        System.out.println("k1_students");
        printArray(k1_students);
        System.out.println("\n\n\n");
        System.out.println("k2_students");
        printArray(k2_students);

    }

    protected void removeStudent(int id) {
        // remove the student with the given id from the k1_students and k2_students array

        for (int i = 0; i < k1_students.length; i++) {
            if (Integer.parseInt(k1_students[i].getStudentid()) == id) {
                for (int j = i; j < k1_students.length - 1; j++) {
                    k1_students[j] = k1_students[j + 1];
                }
                k1_students[k1_students.length - 1] = null;
                break;
            }
        }

        for (int i = 0; i < k2_students.length; i++) {
            if (Integer.parseInt(k2_students[i].getStudentid()) == id) {
                for (int j = i; j < k2_students.length - 1; j++) {
                    k2_students[j] = k2_students[j + 1];
                }
                k2_students[k2_students.length - 1] = null;
                break;
            }
        }
        System.out.println("Student " + id + " removed.");

    }

    protected Person selectK1Member() {
        // select the student with the highest k1 value, then remove it from both k1 k2 arrays
        Person selected = k1_students[0];
        removeStudent(Integer.parseInt(selected.getStudentid()));
        return selected;
    }

    protected Person selectK2Member() {
        // select the student with the lowest k2 value, then remove it from both k1 k2 arrays
        Person selected = k2_students[0];
        removeStudent(Integer.parseInt(selected.getStudentid()));
        return selected;
    }

    protected Person selectK3Member(Person k1, Person k2) {
        //calculate the shortest distance between the given k1 and k2 students, select the student closest to the distance, then remove it from both k1 k2 arrays
        //get the average k1 value of k1 person and k2 person
        int k1_avg = (Integer.parseInt(k1.getK1energy()) + Integer.parseInt(k2.getK1energy())) / 2;
        //get the average k2 value of k1 person and k2 person
        int k2_avg = (Integer.parseInt(k1.getK2energy()) + Integer.parseInt(k2.getK2energy())) / 2;
        int minPersonId = 0;
        int minDistance = 1000000;
        //choose a person that has the lowest distance from the average k1 and k2 values
        for (int i = 0; i < k1_students.length; i++) {
            // choose a person
            Person selected = k1_students[i];
            if (selected == null) {
                break;
            }
            // calculate the distance between the average k1 and k2 values and the selected person's k1 and k2 values
            int distance = (int) Math.sqrt(Math.pow((k1_avg - Integer.parseInt(selected.getK1energy())), 2) + Math.pow((k2_avg - Integer.parseInt(selected.getK2energy())), 2));
            // if the distance is the lowest, then return the selected person
            if (distance < minDistance) {
                minDistance = distance;
                minPersonId =  Integer.parseInt(selected.getStudentid());
            }
        }
        // remove the selected person from the k1_students and k2_students array
        Person k3 = getPerson(minPersonId, k1_students, k1_students.length);
        removeStudent(Integer.parseInt(k3.getStudentid()));
        return k3;
    }

    public boolean checkStudent(int studentId, Person[] studentsArray, int length) {
        // return the student at the given index
        for (int i = 0; i < length; i++) {
            if (studentsArray[i] != null) {
                if (Integer.parseInt(studentsArray[i].getStudentid()) == studentId) {
                    return true;
                }
            }
        }
        return false;
    }

    public Person getPerson (int studentId, Person[] studentsArray, int length) {
        // return the student at the given index
        for (int i = 0; i < length; i++) {
            if (studentsArray[i] != null) {
                if (Integer.parseInt(studentsArray[i].getStudentid()) == studentId) {
                    return studentsArray[i];
                }
            }
        }
        return null;
    }

   public Person[][] createGroups() {
        // group the students into groups of 3 or 4
        // minimize the standard deviation of the group's average based on the k1 and k2 values
//        int sid = Integer.parseInt(k1_students[0].getStudentid());
//        System.out.println("K1: " + k1_students[0].getStudentid());
//        removeStudent(Integer.parseInt(k1_students[0].getStudentid()));
//        if (checkStudent(sid, k1_students, k1_students.length)) {
//            System.out.println("The student exist in k1");
//        } else {
//            System.out.println("The student does not exist in k1");
//        }
//
//        if (checkStudent(sid, k2_students, k2_students.length)) {
//            System.out.println("The student exist in k2");
//        } else {
//            System.out.println("The student does not exist in k2");
//        }
       //select k1, k2 and k3

       int num = (int) (numStudents / 3);
       Person[][] groups = new Person[num][3];

       for (int i = 0; i < (int) (numStudents / 3); i++) {
            groups[i] = new Person[3];
            groups[i][0] = selectK1Member();
            groups[i][1] = selectK2Member();
            groups[i][2] = selectK3Member(groups[i][0], groups[i][1]);
       }

        // need to take account of the missing kid

       return groups;

}

//    public int[][] getGroups() {
//        // return the groups of students in the form of an 2d array
//        /*
//        example:
//            [
//                [sid1, sid2, sid3], // group 1
//                [sid4, sid5, sid6], // group 2
//                ...
//                [sid(n-2), sid(n-1), sid(n)], // group n
//            ]
//         */
//    }

}
