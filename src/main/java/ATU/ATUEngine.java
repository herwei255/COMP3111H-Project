package ATU;

import ATU.MainController.Person;

public class ATUEngine { 

    private Person[] group_A;
    private Person[] group_B;
    private Person[] group_C;
    private int numStudents;
    // private int num_people_in_group_A_C;
    // private int num_people_in_group_B;

    public String printAddress(Object o) {
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

        int num_people_in_group_A_C = Math.floorDiv(people.length, 3);
        int num_people_in_group_B = people.length - num_people_in_group_A_C * 2;
        group_A = new Person[num_people_in_group_A_C];
        group_B = new Person[num_people_in_group_B];
        group_C = new Person[num_people_in_group_A_C];
        numStudents = people.length;

        // sort sorted_students array by this formula: (0.55 * k1 + 0.45 * k2)
        for (int i = 0; i < people.length; i++) {
            for (int j = i + 1; j < people.length; j++) {
                if (Double.parseDouble(people[i].getK1energy()) * 0.55 + Double.parseDouble(people[i].getK2energy()) * 0.45 < Double.parseDouble(people[j].getK1energy()) * 0.55 + Double.parseDouble(people[j].getK2energy()) * 0.45) {
                    Person temp = people[i];
                    people[i] = people[j];
                    people[j] = temp;
                }
            }
        }

        for (int i = 0; i < num_people_in_group_A_C; i++) {
            if (i < num_people_in_group_B) {
                group_A[i] = new Person(people[i].getStudentid(), people[i].getStudentname(), people[i].getEmail(), people[i].getK1energy(), people[i].getK2energy(), people[i].getK3trick1(), people[i].getK3trick2(), people[i].getMypreference(), people[i].getConcerns());
                group_C[i] = new Person(people[i + num_people_in_group_A_C + num_people_in_group_B].getStudentid(), people[i + num_people_in_group_A_C + num_people_in_group_B].getStudentname(), people[i + num_people_in_group_A_C + num_people_in_group_B].getEmail(), people[i + num_people_in_group_A_C + num_people_in_group_B].getK1energy(), people[i + num_people_in_group_A_C + num_people_in_group_B].getK2energy(), people[i + num_people_in_group_A_C + num_people_in_group_B].getK3trick1(), people[i + num_people_in_group_A_C + num_people_in_group_B].getK3trick2(), people[i + num_people_in_group_A_C + num_people_in_group_B].getMypreference(), people[i + num_people_in_group_A_C + num_people_in_group_B].getConcerns());
            }
            group_B[i] = new Person(people[i + num_people_in_group_A_C].getStudentid(), people[i + num_people_in_group_A_C].getStudentname(), people[i + num_people_in_group_A_C].getEmail(), people[i + num_people_in_group_A_C].getK1energy(), people[i + num_people_in_group_A_C].getK2energy(), people[i + num_people_in_group_A_C].getK3trick1(), people[i + num_people_in_group_A_C].getK3trick2(), people[i + num_people_in_group_A_C].getMypreference(), people[i + num_people_in_group_A_C].getConcerns());
        }

        // System.out.println("group A " + "length = " + group_A.length);
        // printArray(group_A);
        // System.out.println("group B " + "length = " + group_B.length);
        // printArray(group_B);
        // System.out.println("group C " + "length = " + group_C.length);
        // printArray(group_C);

    }

    protected Person selectFromGroupA(int index) {
        // select the highest score student, then remove it from both k1 k2 arrays
        Person selected = group_A[index];
        return selected;
    }

    protected Person selectFromGroupC(int index) {
        // select the student with the lowest k2 value, then remove it from both k1 k2 arrays
        Person selected = group_C[group_C.length - 1 - index];
        return selected;
    }

    // protected Person selectK3Member(Person k1, Person k2) {
    //     //calculate the shortest distance between the given k1 and k2 students, select the student closest to the distance, then remove it from both k1 k2 arrays
    //     //get the average k1 value of k1 person and k2 person
    //     int k1_avg = (Integer.parseInt(k1.getK1energy()) + Integer.parseInt(k2.getK1energy())) / 2;
    //     //get the average k2 value of k1 person and k2 person
    //     int k2_avg = (Integer.parseInt(k1.getK2energy()) + Integer.parseInt(k2.getK2energy())) / 2;
    //     int minPersonId = 0;
    //     int minDistance = 1000000;
    //     //choose a person that has the lowest distance from the average k1 and k2 values
    //     for (int i = 0; i < k1_students.length; i++) {
    //         // choose a person
    //         Person selected = k1_students[i];
    //         if (selected == null) {
    //             break;
    //         }
    //         // calculate the distance between the average k1 and k2 values and the selected person's k1 and k2 values
    //         int distance = (int) Math.sqrt(Math.pow((k1_avg - Integer.parseInt(selected.getK1energy())), 2) + Math.pow((k2_avg - Integer.parseInt(selected.getK2energy())), 2));
    //         // if the distance is the lowest, then return the selected person
    //         if (distance < minDistance) {
    //             minDistance = distance;
    //             minPersonId =  Integer.parseInt(selected.getStudentid());
    //         }
    //     }
    //     // remove the selected person from the k1_students and k2_students array
    //     Person k3 = getPerson(minPersonId, k1_students, k1_students.length);
    //     removeStudent(Integer.parseInt(k3.getStudentid()));
    //     return k3;
    // }

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

       int num = (int) (numStudents / 3);
       Person[][] groups = new Person[num][3];

       for (int i = 0; i < Math.floorDiv(numStudents, 3); i++) {
            groups[i] = new Person[3];
            groups[i][0] = selectFromGroupA(i);
            groups[i][1] = selectFromGroupC(i);
            // groups[i][2] = selectK3Member(groups[i][0], groups[i][1]);
       }

        // need to take account of the missing kid
        // print 2d array of groups
        for (int i = 0; i < groups.length; i++) {
            System.out.println("Group " + (i + 1) + ":");
            double avg_k1 = 0;
            double avg_k2 = 0;
            for (int j = 0; j < groups[i].length; j++) {
                if (groups[i][j] != null) {
                    avg_k1 = avg_k1 + Integer.parseInt(groups[i][j].getK1energy());
                    avg_k2 = avg_k2 + Integer.parseInt(groups[i][j].getK2energy());
                    System.out.print(groups[i][j].getStudentid() + " ");
                }
            }
            System.out.printf("\nAverage k1: %,.2f", avg_k1 / 2);
            System.out.printf("\nAverage k2: %,.2f", avg_k2 / 2);
            System.out.println("\nOverall average = " + avg_k1 / 2 * 0.55 + avg_k2 / 2 * 0.45);
            System.out.println();
        }
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
