package org.iis2024.parcial1;

import java.util.*;

public class Course {
  private final String name;
  private final List<Integer> enrolledStudents;
  private final List<Integer> passedStudents;

  Course(String name) {
    this.name = name;
    enrolledStudents = new ArrayList<>();
    passedStudents = new ArrayList<>();
  }

  public String getName() {
    return name;
  }

  public void enroll(Integer studentID) {
    enrolledStudents.add(studentID);
  }

  public boolean isEnrolled(Integer studentID) {
    return enrolledStudents.contains(studentID);
  }

  public void pass(Integer studentID) {
    if (isEnrolled(studentID)) {
      passedStudents.add(studentID);
      enrolledStudents.remove(studentID);
    }
  }

  public boolean hasPassed(Integer studentID) {
    return passedStudents.contains(studentID);
  }
}
