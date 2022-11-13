// package com.example.atu;
//
// import com.example.atu.Library.Person;
//
// public class ATUEngine {
//     private Person[] k1_students;
//     private Person[] k2_students;
//
//     public ATUEngine(Person[] people) {
//         // from the given person array, create two different arrays based on ascending k1 and descending k2 students into the respective arrays
//     }
//
//     protected void removeStudent(int id) {
//         // remove the student with the given id from the k1 k2 arrays
//     }
//
//     protected Person selectK1Member() {
//         // select the student with the highest k1 value, then remove it from both k1 k2 arrays
//     }
//
//     protected Person selectK2Member() {
//         // select the student with the lowest k2 value, then remove it from both k1 k2 arrays
//     }
//
//     protected Person selectK3Member() {
//         //calculate the shortest distance between the k1 and k2 students, select the student closest to the distance, then remove it from both k1 k2 arrays
//     }
//
//     public Person getStudent(int index) {
//         // get the student at the given index
//     }
//
//     public void groupStudents() {
//         // group the students into groups of 3 or 4
//         // minimize the standard deviation of the group's average based on the k1 and k2 values
//     }
//
//     public int[][] getGroups() {
//         // return the groups of students in the form of an 2d array
//         /*
//         example:
//             [
//                 [sid1, sid2, sid3], // group 1
//                 [sid4, sid5, sid6], // group 2
//                 ...
//                 [sid(n-2), sid(n-1), sid(n)], // group n
//             ]
//          */
//     }
//
// }