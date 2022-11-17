package ATU;


















































































































import java.util.ArrayList;
import ATU.MainController.Person;

public class StudentAutoGenerator {
    static ArrayList<Person> students = new ArrayList<Person>();
    static String[] firstNames = { "Aiden", "Alex", "Alexis", "Alyssa", "Andrew", "Anthony", "Austin", "Benjamin", "Blake",
            "Brandon", "Brayden", "Brianna", "Brooklyn", "Caleb", "Cameron", "Carson", "Carter", "Chase", "Christian",
            "Christopher", "Claire", "Colin", "Connor", "Daniel", "David", "Dylan", "Elijah", "Ethan", "Evan", "Gabriel",
            "Gavin", "Grace", "Hannah", "Hayden", "Ian", "Isaac", "Isabella", "Jack", "Jackson", "Jacob", "Jaden",
            "James", "Jasmine", "Jason", "Jayden", "Jessica", "John", "Jonathan", "Jordan", "Joseph", "Joshua", "Julia",
            "Justin", "Kaitlyn", "Kayla", "Kenneth", "Blaise", "Lauren", "Liam", "Logan", "Lucas", "Luke", "Madison",
            "Mason", "Matthew", "Megan", "Michael", "Morgan", "Nathan", "Nicholas", "Noah", "Olivia", "Owen", "Peyton",
            "Rachel", "Riley", "Robert", "Jesus", "Samantha", "Sarah", "Savannah", "Sean", "Sophia", "Sophie", "Taylor",
            "Thomas", "Tristan", "Tyler", "Victoria", "William", "Zachary" };

    static String[] lastNames = { "Wang", "Li", "Zhang", "Liu", "Chen", "Yang", "Zhao", "Huang", "Wu", "Zhou", "Xu", "Zhu",
            "Sun", "Gao", "Hu", "Guo", "Lin", "Luo", "Tang", "Ma", "Zheng", "Wang", "Li", "Zhang", "Liu", "Chen", "Yang",
            "Zhao", "Huang", "Wu", "Zhou", "Xu", "Zhu", "Sun", "Gao", "Hu", "Guo", "Lin", "Luo", "Tang", "Ma", "Zheng",
            "Wang", "Li", "Zhang", "Liu", "Chen", "Yang", "Zhao", "Huang", "Wu", "Zhou", "Xu", "Zhu", "Sun", "Gao", "Hu",
            "Guo", "Lin", "Luo", "Tang", "Ma", "Zheng", "Wang", "Li", "Zhang", "Liu", "Chen", "Yang", "Zhao", "Huang", "Wu",
            "Zhou", "Xu", "Zhu", "Sun", "Gao", "Hu", "Guo", "Lin", "Luo", "Tang", "Ma", "Zheng", "Wang", "Li", "Zhang",
            "Liu", "Chen", "Yang", "Zhao", "Huang", "Wu", "Zhou", "Xu", "Zhu", "Sun", "Gao", "Hu", "Guo", "Lin", "Luo",
            "Tang", "Ma", "Zheng", "Wang", "Li", "Zhang", "Liu", "Chen", "Yang", "Zhao", "Huang", "Wu", "Zhou", "Xu", "Zhu",
            "Sun", "Gao", "Hu", "Guo", "Lin", "Luo", "Tang", "Ma", "Zheng", "Wang", "Li", "Zhang", "Liu"};


    public static ArrayList<Person> Generate(StudentGeneratorStatistics studentGeneratorStatistics) {
        int random;
        int k3Tick1Counter = 0, k3Tick2Counter = 0, probMyPrefCounter = 0;
        String k3Tick1, k3Tick2, myPref, k1Energy, k2Energy;
        int maxK3Tick1 = (int) Math.ceil((double)studentGeneratorStatistics.probK3Tick1 / 100 * studentGeneratorStatistics.numberOfStudents);
        int maxK3Tick2 = (int) Math.ceil((double)studentGeneratorStatistics.probK3Tick2 / 100 * studentGeneratorStatistics.numberOfStudents);
        int maxMyPref = (int) Math.ceil((double)studentGeneratorStatistics.probMyPref / 100 * studentGeneratorStatistics.numberOfStudents);

        // Create list of random StudentIDs
        Person.studentIndex = 0; // Reset student index to 0
        ArrayList<Integer> studentIDList = new ArrayList<Integer>();
        for (int i = 0; i < studentGeneratorStatistics.numberOfStudents; i++) {
            random = (int) (Math.random() * 1000000);
            while (studentIDList.contains(random)) {
                random = (int) (Math.random() * 1000000);
            }
            studentIDList.add(random);
        }

        // Create list of random k1Energy, k2Energy
        int[] k1EnergyList = setKEnergy(studentGeneratorStatistics.numberOfStudents, studentGeneratorStatistics.avgK1Energy);
        int[] k2EnergyList = setKEnergy(studentGeneratorStatistics.numberOfStudents, studentGeneratorStatistics.avgK2Energy);

        while(students.size() < studentGeneratorStatistics.numberOfStudents) {
            random = (int)(Math.random() * 100);
            String studentID = String.format("%08d", studentIDList.get(students.size()));
            String firstName = firstNames[(int)(Math.random() * firstNames.length)];
            String lastName = lastNames[(int)(Math.random() * lastNames.length)];
            String fullName = firstName + " " + lastName;
            String email = firstName + "" + lastName.toUpperCase() + "@connect.ust.hk";
            String concerns = firstName == "Blaise" ? "I need good teammates" : "";

            // K1 Energy, K2 Energy
            k1Energy = String.valueOf(k1EnergyList[students.size()]);
            k2Energy = String.valueOf(k2EnergyList[students.size()]);

            // K3_Tick 1
            random = (int)(Math.random() * 100);
            k3Tick1 = "0";
            if (random < studentGeneratorStatistics.probK3Tick1 && k3Tick1Counter < maxK3Tick1) {
                k3Tick1 = "1"; k3Tick1Counter++;
            }

            // K3_Tick 2
            random = (int)(Math.random() * 100);
            k3Tick2 = "0";
            if (random < studentGeneratorStatistics.probK3Tick1 && k3Tick1Counter < maxK3Tick1) {
                k3Tick2 = "1"; k3Tick2Counter++;
            }

            // My_Preference
            random = (int)(Math.random() * 100);
            myPref = "0";
            if (random < studentGeneratorStatistics.probMyPref && probMyPrefCounter < maxMyPref) {
                myPref = "1"; probMyPrefCounter++;
            }

            students.add(new Person(studentID, fullName, email, k1Energy, k2Energy, k3Tick1, k3Tick2, myPref, concerns));
        }

        // Add counters until their limit
        int i = 0;
        System.out.println(k3Tick1Counter);
        System.out.println(maxK3Tick1);
        while (k3Tick1Counter < maxK3Tick1) {
            if (students.get(i).getK3trick1() == "0" ) {
                students.get(i).setK3trick1("1");
                k3Tick1Counter++;
            }
            i++;
        }
        i = 0;
        while (k3Tick2Counter < maxK3Tick2) {
            if (students.get(i).getK3trick2() == "0" ) {
                students.get(i).setK3trick2("1");
                k3Tick2Counter++;
            }
            i++;
        }
        i = 0;
        while (probMyPrefCounter < maxMyPref) {
            if (students.get(i).getMypreference() == "0" ) {
                students.get(i).setMypreference("1");
                probMyPrefCounter++;
            }
            i++;
        }
        return students;
    }

    /**
     *
     * @param numberOfStudents number of Students requirements
     * @param avgKEnergy K1Energy / K2Energy requirements
     * @return kEnergyList that matches requirements
     */
    public static int[] setKEnergy(Integer numberOfStudents, Integer avgKEnergy) {
        int[] kEnergyList = new int[numberOfStudents];
        int random;
        int totalKEnergy = 0;

        // Initialize k1Energy list to random numbers
        for (int i = 0; i < numberOfStudents; i++) {
            random = (int) (Math.random() * 100);
            kEnergyList[i] = random;
            totalKEnergy += random;
        }

        // Modify random values in kEnergy list until it meets requirements
        while (totalKEnergy / numberOfStudents - avgKEnergy >= 3) {
            random = (int) (Math.random() * numberOfStudents);
            if (kEnergyList[random] > 0) {
                kEnergyList[random] -= 1;
                totalKEnergy -= 1;
            }
        }
        while (totalKEnergy / numberOfStudents - avgKEnergy <= -3) {
            random = (int) (Math.random() * numberOfStudents);
            if (kEnergyList[random] < 100) {
                kEnergyList[random] += 1;
                totalKEnergy += 1;
            }
        }
        return kEnergyList;
    }

    /**
     * Holder class for user inputted statistics.
     */
    public static class StudentGeneratorStatistics {
        Integer numberOfStudents;
        Integer avgK1Energy;
        Integer avgK2Energy;
        Integer probK3Tick1;
        Integer probK3Tick2;
        Integer probMyPref;

        public StudentGeneratorStatistics(Integer numberOfStudents, Integer avgK1Energy, Integer avgK2Energy, Integer probK3Tick1, Integer probK3Tick2, Integer probMyPref) {
            this.numberOfStudents = numberOfStudents;
            this.avgK1Energy = avgK1Energy;
            this.avgK2Energy = avgK2Energy;
            this.probK3Tick1 = probK3Tick1;
            this.probK3Tick2 = probK3Tick2;
            this.probMyPref = probMyPref;
        }
    }


}
