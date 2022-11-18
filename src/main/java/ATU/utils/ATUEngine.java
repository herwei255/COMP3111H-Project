package ATU.utils;

import ATU.models.Person;
import java.util.HashMap;

public class ATUEngine { 

    HashMap<Integer, Integer> selected_students;

    private Person[] k1_students;
    private Person[] group_A;
    private Person[] group_B;
    private Person[] group_C;
    private Person[] missing_person;

    private boolean group_A_empty = false;
    private boolean group_B_empty = false;
    private boolean group_C_empty = false;

    private int numStudents;

    private double averageK1;
    private double averageK2;
    private double averageK1K2;

    // helper function to print the address of the inputted object
    public String printAddress(Object o) {
    return o.getClass().getName() + "@" + 
           Integer.toHexString(System.identityHashCode(o));
    }

    // helper function to print 1D array with type Person
    public void printArray(Person[] array) {
        for (int i = 0; i < array.length; i++) {
            System.out.println(i + " " + array[i].getStudentid() + " " + array[i].getK1energy() + " " + array[i].getK2energy());
        }
    }

    // helper function to check whether all students have been assigned to a group
    protected void checkAllStudents() {
        group_A_empty = true;
        group_B_empty = true;
        group_C_empty = true;
        int index = 0;
        System.out.println("Group A missing ppl:");
        for (int i = 0; i < group_A.length; i++) {
            if (group_A[i] != null) {
                group_A_empty = false;
                System.out.println("SID =  " + group_A[i].getStudentid());
                missing_person[index] = group_A[i];
                index++;
            }
        }
        System.out.println("Group B missing ppl:");
        for (int i = 0; i < group_B.length; i++) {
            if (group_B[i] != null) {
                group_B_empty = false;
                System.out.println("SID =  " + group_B[i].getStudentid());
                missing_person[index] = group_B[i];
                index++;
            }
        }
        System.out.println("Group C missing ppl:");
        for (int i = 0; i < group_C.length; i++) {
            if (group_C[i] != null) {
                group_C_empty = false;
                System.out.println("SID =  " + group_C[i].getStudentid());
                missing_person[index] = group_C[i];
                index++;
            }
        }
    }

    public ATUEngine(Person[] people) {

        int num_people_in_group_A_C = Math.floorDiv(people.length, 3);
        int num_people_in_group_B = people.length - num_people_in_group_A_C * 2;
        selected_students = new HashMap<Integer, Integer>();
        k1_students = new Person[people.length];
        group_A = new Person[num_people_in_group_A_C];
        group_B = new Person[num_people_in_group_B];
        group_C = new Person[num_people_in_group_A_C];
        missing_person = new Person[2];
        numStudents = people.length;

        // deep copy the people array into k1_students
        for (int i = 0; i < people.length; i++) {
            k1_students[i] = new Person(people[i].getStudentid(), people[i].getStudentname(), people[i].getEmail(), people[i].getK1energy(), people[i].getK2energy(), people[i].getK3trick1(), people[i].getK3trick2(), people[i].getMypreference(), people[i].getConcerns());
        }

        // sort the k1_students array by desending k1
        for (int i = 0; i < k1_students.length; i++) {
            for (int j = i + 1; j < k1_students.length; j++) {
                if (Integer.parseInt(k1_students[i].getK1energy()) < Integer.parseInt(k1_students[j].getK1energy())) {
                    Person temp = k1_students[i];
                    k1_students[i] = k1_students[j];
                    k1_students[j] = temp;
                }
            }
        }

        // sort the people array by this formula: (0.55 * k1 + 0.45 * k2)
        for (int i = 0; i < people.length; i++) {
            for (int j = i + 1; j < people.length; j++) {
                if (Double.parseDouble(people[i].getK1energy()) * 0.55 + Double.parseDouble(people[i].getK2energy()) * 0.45 < Double.parseDouble(people[j].getK1energy()) * 0.55 + Double.parseDouble(people[j].getK2energy()) * 0.45) {
                    Person temp = people[i];
                    people[i] = people[j];
                    people[j] = temp;
                }
            }
        }

        // calculate the average k1 and k2 values for the people array
        for (int i = 0; i < people.length; i++) {
            averageK1 += Double.parseDouble(people[i].getK1energy());
            averageK2 += Double.parseDouble(people[i].getK2energy());
        }

        averageK1 = averageK1 / people.length;
        averageK2 = averageK2 / people.length;

        averageK1K2 = 0.55 * averageK1 + 0.45 * averageK2;

        // divide the students into 3 different groups, A, B, and C where A represents students with high k1k2 average and C represents students with low k1k2 average
        for (int i = 0; i < num_people_in_group_B; i++) {
            if (i < num_people_in_group_A_C) {
                group_A[i] = new Person(people[i].getStudentid(), people[i].getStudentname(), people[i].getEmail(), people[i].getK1energy(), people[i].getK2energy(), people[i].getK3trick1(), people[i].getK3trick2(), people[i].getMypreference(), people[i].getConcerns());
                int c_index = i + num_people_in_group_A_C + num_people_in_group_B;
                group_C[i] = new Person(people[c_index].getStudentid(), people[c_index].getStudentname(), people[c_index].getEmail(), people[c_index].getK1energy(), people[c_index].getK2energy(), people[c_index].getK3trick1(), people[c_index].getK3trick2(), people[c_index].getMypreference(), people[c_index].getConcerns());
            }
            int b_index = i + num_people_in_group_A_C;
            group_B[i] = new Person(people[b_index].getStudentid(), people[b_index].getStudentname(), people[b_index].getEmail(), people[b_index].getK1energy(), people[b_index].getK2energy(), people[b_index].getK3trick1(), people[b_index].getK3trick2(), people[b_index].getMypreference(), people[b_index].getConcerns());
        }

    }

    // helper function to know which group the student is belong to
    protected int findStudentGroup(int sid) {
  
        for (int i = 0; i < group_A.length; i++) {
            if (group_A[i] != null) {
                if (Integer.parseInt(group_A[i].getStudentid()) == sid) {
                    return 1;
                }
            }
        }
        for (int i = 0; i < group_B.length; i++) {
            if (group_B[i] != null) {
                if (Integer.parseInt(group_B[i].getStudentid()) == sid) {
                    return 2;
                }
            }
        }
        for (int i = 0; i < group_C.length; i++) {
            if (group_C[i] != null) {
                if (Integer.parseInt(group_C[i].getStudentid()) == sid) {
                    return 3;
                }
            }
        }
        return -1;
    }

    // function to remove the students from the respective groups
    protected void removeStudent(int sid, int group) {
        if (group == 1) {
            for (int i = 0; i < group_A.length; i++) {
                if (group_A[i] != null) {
                    if (Integer.parseInt(group_A[i].getStudentid()) == sid) {
                        group_A[i] = null;
                    }
                }
            }
        } else if (group == 2) {
            for (int i = 0; i < group_B.length; i++) {
                if (group_B[i] != null) {
                    if (Integer.parseInt(group_B[i].getStudentid()) == sid) {
                        group_B[i] = null;
                    }
                }
            }
        } else if (group == 3) {
            for (int i = 0; i < group_C.length; i++) {
                if (group_C[i] != null) {
                    if (Integer.parseInt(group_C[i].getStudentid()) == sid) {
                        group_C[i] = null;
                    }
                }
            }
        }
    }

    // function to select student from the highest k1 to ensure every team has at least one member with k1 >= average k1
    protected Person selectK1Student(int index) {
        Person selected = k1_students[index];
        int group = findStudentGroup(Integer.parseInt(selected.getStudentid()));
        selected_students.put(Integer.parseInt(selected.getStudentid()), group);
        removeStudent(Integer.parseInt(selected.getStudentid()), group);
        return selected;
    }

    // function to select student from the chosen groups based on the existing team members's k1 and k2 values
    protected Person selectFromGroup(int which_group, Person group[], int size) {
        int cumulative_k1 = 0;
        int cumulative_k2 = 0;
        int real_size = 0;
        for (int i = 0; i < size; i++) {
            if (group[i] != null) {
                real_size++;
                Person selected = group[i];
                cumulative_k1 = cumulative_k1 + Integer.parseInt(selected.getK1energy());
                cumulative_k2 = cumulative_k2 + Integer.parseInt(selected.getK2energy());
            }
        }

        double average_k1 = cumulative_k1 / real_size;
        double average_k2 = cumulative_k2 / real_size;
        int k = 0;
        if (Math.abs(average_k1 - averageK1) > Math.abs(average_k2 - averageK2)) {
            k = 2;
        } else {
            k = 1;
        }

        double distance = Double.MAX_VALUE;
        int index = 0;
        Person selected = null;

        int length = 0;
        Person the_group[] = null;
        if (which_group == 1) {
            length = group_A.length;
            the_group = group_A;
        } else if (which_group == 2) {
            length = group_B.length;
            the_group = group_B;
        } else if (which_group == 3) {
            length = group_C.length;
            the_group = group_C;
        }
        
        for (int i = 0; i < length; i++) {
            if (the_group[i] != null) {
                double avg_temp = 0;
                double distance_temp = 0;
                if (k == 1) {
                    avg_temp = (cumulative_k1 + Integer.parseInt(the_group[i].getK1energy())) / (real_size + 1);
                    distance_temp = Math.abs(avg_temp - averageK1);
                } else if (k == 2) {
                    avg_temp = (cumulative_k2 + Integer.parseInt(the_group[i].getK2energy())) / (real_size + 1);
                    distance_temp = Math.abs(avg_temp - averageK2);
                }
                if (distance_temp <= distance) {
                    selected = the_group[i];
                    distance = distance_temp;
                    index = i;
                }
            }
        }
        the_group[index] = null;
        return selected;
    }

    public Person[][] createGroups() {
       int num = (int) (numStudents / 3);
       Person[][] groups = new Person[num][3];

        // select the first student with high k1
       for (int i = 0; i < Math.floorDiv(numStudents, 3); i++) {
            groups[i] = new Person[4];
            groups[i][0] = selectK1Student(i);
       }

       // select the second and the third members
       for (int i = 0; i < Math.floorDiv(numStudents, 3); i++) {
            int first_student_group = selected_students.get(Integer.parseInt(groups[i][0].getStudentid()));
            if (first_student_group == 1) {
                groups[i][2] = selectFromGroup(3, groups[i], groups[i].length);
                groups[i][1] = selectFromGroup(2, groups[i], groups[i].length);
            } else if (first_student_group == 2) {
                groups[i][1] = selectFromGroup(1, groups[i], groups[i].length);
                groups[i][2] = selectFromGroup(3, groups[i], groups[i].length);
            } else if (first_student_group == 3) {
                groups[i][1] = selectFromGroup(1, groups[i], groups[i].length);
                groups[i][2] = selectFromGroup(2, groups[i], groups[i].length);
            }
       }

        checkAllStudents();

        // if needed, assign the fourth member to the group
        int num_groups = Math.floorDiv(numStudents, 3);
        for (int i = 0; i < missing_person.length; ++i){
            if (missing_person[i] != null) {
                double distance_k1 = Double.MAX_VALUE;
                int missing_index = 0;
                double iter = Math.floor(num_groups * 0.6);
                double avg_temp_k1 = 0;
                for (int j = num_groups - 1; j >= iter; j--) {
                    for (int k = 0; k < 3; k++) {
                        avg_temp_k1 += Integer.parseInt(groups[j][k].getK1energy());
                    }
                    avg_temp_k1 += Integer.parseInt(missing_person[i].getK1energy());
                    avg_temp_k1 = avg_temp_k1 /4;
                    double distance_temp = Math.abs(avg_temp_k1 - averageK1);
                    if (distance_temp <= distance_k1) {
                        distance_k1 = distance_temp;
                        missing_index = j;
                    }
                }
                groups[missing_index][3] = missing_person[i];
                missing_person[i] = null;
            }
        }

        // try to spread those who has leader preference to different groups that does not have any leader preference while maintaining the SD
        for (int i = 0; i < num_groups; i++) {
            int num_leader = 0;
            for (int j = 0; j < 4; j++) {
                if (groups[i][j] != null) {
                    if (groups[i][j].getMypreference() == "1") {
                        num_leader++;
                    }
                }
            }
            
            if (num_leader > 1) {
                for (int j = 0; j < 4; j++) {
                    if (groups[i][j] != null) {
                        if (groups[i][j].getMypreference() == "1") {
                            double avg_k1_k2 = 0.55 * Integer.parseInt(groups[i][j].getK1energy()) + 0.45 * Integer.parseInt(groups[i][j].getK2energy());
                            for (int k = 0; k < num_groups; k++) {
                                if (k != i) {
                                    int temp_num_leader = 0;
                                    for (int z = 0; z < 4; z++) {
                                        if (groups[k][z] != null) {
                                            if (groups[k][z].getMypreference() == "1") {
                                                temp_num_leader++;
                                            }
                                        }
                                    }
                                    if (temp_num_leader == 0) {
                                        for (int l = 0; l < 4; l++) {
                                            if (groups[k][l] != null) {
                                                double avg_k1_k2_temp = 0.55 * Integer.parseInt(groups[k][l].getK1energy()) + 0.45 * Integer.parseInt(groups[k][l].getK2energy());
                                                if (avg_k1_k2_temp == avg_k1_k2 && groups[k][l].getMypreference() != "1") {
                                                    Person temp = groups[i][j];
                                                    groups[i][j] = groups[k][l];
                                                    groups[k][l] = temp;
                                                    num_leader--;
                                                    break;
                                                }
                                            }
                                        }
                                    }
                                }
                                if (num_leader == 1) {
                                    break;
                            }
                            }
                            if (num_leader == 1) {
                                break;
                            }
                        }
                    }
                }

            }
        }

        // try to spread those who are willing to allocate more time on the project while maintaining the SD
        for (int i = 0; i < num_groups; i++) {
            int k3 = 0;
            for (int j = 0; j < 4; j++) {
                if (groups[i][j] != null) {
                    if (groups[i][j].getK3trick2() == "1") {
                        k3++;
                    }
                }
            }
            
            if (k3 > 1) {
                for (int j = 0; j < 4; j++) {
                    if (groups[i][j] != null) {
                        if (groups[i][j].getK3trick2() == "1") {
                            double avg_k1_k2 = 0.55 * Integer.parseInt(groups[i][j].getK1energy()) + 0.45 * Integer.parseInt(groups[i][j].getK2energy());
                            for (int k = 0; k < num_groups; k++) {
                                if (k != i) {
                                    int temp_k2 = 0;
                                    for (int z = 0; z < 4; z++) {
                                        if (groups[k][z] != null) {
                                            if (groups[k][z].getK3trick2() == "1") {
                                                temp_k2++;
                                            }
                                        }
                                    }
                                    if (temp_k2 == 0) {
                                        for (int l = 0; l < 4; l++) {
                                            if (groups[k][l] != null) {
                                                double avg_k1_k2_temp = 0.55 * Integer.parseInt(groups[k][l].getK1energy()) + 0.45 * Integer.parseInt(groups[k][l].getK2energy());
                                                if (avg_k1_k2_temp == avg_k1_k2 && groups[k][l].getK3trick2() != "1") {
                                                    Person temp = groups[i][j];
                                                    groups[i][j] = groups[k][l];
                                                    groups[k][l] = temp;
                                                    k3--;
                                                    break;
                                                }
                                            }
                                        }
                                    }
                                }
                                if (k3 == 1) {
                                    break;
                            }
                            }
                            if (k3 == 1) {
                                break;
                            }
                        }
                    }
                }

            }
        }

        // try to spread those who are creative while maintaining the SD
        for (int i = 0; i < num_groups; i++) {
            int k3 = 0;
            for (int j = 0; j < 4; j++) {
                if (groups[i][j] != null) {
                    if (groups[i][j].getK3trick1() == "1") {
                        k3++;
                    }
                }
            }
            
            if (k3 > 1) {
                for (int j = 0; j < 4; j++) {
                    if (groups[i][j] != null) {
                        if (groups[i][j].getK3trick1() == "1") {
                            double avg_k1_k2 = 0.55 * Integer.parseInt(groups[i][j].getK1energy()) + 0.45 * Integer.parseInt(groups[i][j].getK2energy());
                            for (int k = 0; k < num_groups; k++) {
                                if (k != i) {
                                    int temp_k2 = 0;
                                    for (int z = 0; z < 4; z++) {
                                        if (groups[k][z] != null) {
                                            if (groups[k][z].getK3trick1() == "1") {
                                                temp_k2++;
                                            }
                                        }
                                    }
                                    if (temp_k2 == 0) {
                                        for (int l = 0; l < 4; l++) {
                                            if (groups[k][l] != null) {
                                                double avg_k1_k2_temp = 0.55 * Integer.parseInt(groups[k][l].getK1energy()) + 0.45 * Integer.parseInt(groups[k][l].getK2energy());
                                                if (avg_k1_k2_temp == avg_k1_k2 && groups[k][l].getK3trick1() != "1") {
                                                    Person temp = groups[i][j];
                                                    groups[i][j] = groups[k][l];
                                                    groups[k][l] = temp;
                                                    k3--;
                                                    break;
                                                }
                                            }
                                        }
                                    }
                                }
                                if (k3 == 1) {
                                    break;
                            }
                            }
                            if (k3 == 1) {
                                break;
                            }
                        }
                    }
                }

            }
        }

        // to debug
        for (int i = 0; i < groups.length; i++) {
            System.out.println("Group " + (i + 1) + ":");
            double avg_k1 = 0;
            double avg_k2 = 0;
            int num_of_ppl = 0;
            for (int j = 0; j < groups[i].length; j++) {
                if (groups[i][j] != null) {
                    num_of_ppl++;
                    avg_k1 = avg_k1 + Integer.parseInt(groups[i][j].getK1energy());
                    avg_k2 = avg_k2 + Integer.parseInt(groups[i][j].getK2energy());
                    System.out.print(groups[i][j].getStudentname() + " ");
                }
            }
            avg_k1 = avg_k1 / num_of_ppl;
            avg_k2 = avg_k2 / num_of_ppl;
            System.out.printf("\nAverage k1: %,.2f", avg_k1);
            System.out.printf("\nAverage k2: %,.2f", avg_k2);
            System.out.println("\nOverall average = " + (avg_k1 * 0.55 + avg_k2 * 0.45));
            System.out.println();
        }
        System.out.printf("\nTotal Average k1: %,.2f", averageK1);
        System.out.printf("\nTotal Average k2: %,.2f", averageK2);
        System.out.printf("\nTotal Average all: %,.2f", averageK1K2);
        System.out.println();

       return groups;

    }
}
