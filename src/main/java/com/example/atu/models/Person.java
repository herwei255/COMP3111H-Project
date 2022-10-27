// package com.example.atu.models;
//
// import javafx.beans.property.SimpleStringProperty;
//
// public class Person {
//
//     private final SimpleStringProperty studentid;
//     private final SimpleStringProperty studentname;
//     private final SimpleStringProperty k1energy;
//     private final SimpleStringProperty k2energy;
//     private final SimpleStringProperty k3trick1;
//     private final SimpleStringProperty k3trick2;
//     private final SimpleStringProperty mypreference;
//     private final SimpleStringProperty concerns;
//     private SimpleStringProperty groupID;
//
//     public Person(String student_id, String student_name, String k1_energy, String k2_energy, String k3_trick1,
//                   String k3_trick2, String my_preference, String concerns) {
//         this.studentid = new SimpleStringProperty(student_id);
//         this.studentname = new SimpleStringProperty(student_name);
//         this.k1energy = new SimpleStringProperty(k1_energy);
//         this.k2energy = new SimpleStringProperty(k2_energy);
//         this.k3trick1 = new SimpleStringProperty(k3_trick1);
//         this.k3trick2 = new SimpleStringProperty(k3_trick2);
//         this.mypreference = new SimpleStringProperty(my_preference);
//         this.concerns = new SimpleStringProperty(concerns);
//     }
//
//     public String getGroupID() {
//         return groupID.get();
//     }
//
//     public SimpleStringProperty groupIDProperty() {
//         return groupID;
//     }
//
//     public void setGroupID(String groupID) {
//         this.groupID.set(groupID);
//     }
//
//     public String getStudentid() {
//         return studentid.get();
//     }
//
//     public void setStudentid(String val) {
//         studentid.set(val);
//     }
//
//     public String getStudentname() {
//         return studentname.get();
//     }
//
//     public void setStudentname(String val) {
//         studentname.set(val);
//     }
//
//     public String getK1energy() {
//         return k1energy.get();
//     }
//
//     public void setK1energy(String val) {
//         k1energy.set(val);
//     }
//
//     public String getK2energy() {
//         return k2energy.get();
//     }
//
//     public void setK2energy(String val) {
//         k2energy.set(val);
//     }
//
//     public String getK3trick1() {
//         return k3trick1.get();
//     }
//
//     public void setK3trick1(String val) {
//         k3trick1.set(val);
//     }
//
//     public String getK3trick2() {
//         return k3trick2.get();
//     }
//
//     public void setK3trick2(String val) {
//         k3trick2.set(val);
//     }
//
//     public String getMypreference() {
//         return mypreference.get();
//     }
//
//     public void setMypreference(String val) {
//         mypreference.set(val);
//     }
//
//     public String getConcerns() {
//         return concerns.get();
//     }
//
//     public void setConcerns(String val) {
//         concerns.set(val);
//     }
//
// }