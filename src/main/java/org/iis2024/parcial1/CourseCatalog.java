package org.iis2024.parcial1;

import java.util.ArrayList;
import java.util.List;

public class CourseCatalog {
  private final List<Course> availableCourses;

  CourseCatalog() {
    availableCourses = new ArrayList<>();
    availableCourses.add(new Course("Programming I"));
    availableCourses.add(new Course("Programming II"));
    availableCourses.add(new Course("Introduction to Software Engineering"));
    availableCourses.add(new Course("Spanish"));
  }

  public boolean contains(String courseName) {
    return (availableCourses.stream().anyMatch(course -> courseName.equals(course.getName())));
  }

  public Course getCourse(String courseName) {
    for (Course currentCourse : availableCourses) {
      if (courseName.equals(currentCourse.getName())) {
        return currentCourse ;
      }
    }
    return null ;
  }


  public boolean enrollStudent(Integer studentID, String courseName) {
    for (Course currentCourse : availableCourses) {
      if (courseName.equals(currentCourse.getName())) {
        currentCourse.enroll(studentID);
        return (true);
      }
    }
    return false;
  }

  public boolean passStudent(Integer studentID, String courseName) {
    boolean result = false;
    for (Course currentCourse : availableCourses) {
      if (courseName.equals(currentCourse.getName())) {
        currentCourse.pass(studentID);
        result = true;
        break;
      }
    }
    return result;
  }
}
