package org.iis2024.parcial1;

import java.util.ArrayList;
import java.util.List;

/** Students in an educational institution */
public class Student {
  public enum STUDENT_TYPE {
    REGULAR,
    VISITING
  }

  public String name;
  public STUDENT_TYPE type;
  public Integer identifier;
  public List<String> passedCourses;
  public List<String> currentCourses;

  public Student(String name, STUDENT_TYPE type, int identifier) {
    this.name = name;
    this.type = type;
    this.identifier = identifier;
    this.currentCourses = new ArrayList<>();
    this.passedCourses = new ArrayList<>();
  }

  /**
   * Method that, given a course name and a course catalog, allows a student to be enrolled in the
   * course
   *
   * @param courseName
   * @param courseCatalog
   * @return A string with the result of the enrollment
   */
  public String enroll(String courseName, CourseCatalog courseCatalog) {
    String enrollResult;
    if (courseCatalog.contains(courseName)) {
      if (courseName.equals("Programming II")) {
        if (passedCourses.contains("Programming I")) {
          if (!currentCourses.contains(courseName)) {
            courseCatalog.enrollStudent(identifier, courseName);
            currentCourses.add(courseName);
            enrollResult = "Enrollment successful";
          } else {
            enrollResult = "Enrollment failed: Already enrolled";
          }
        } else {
          enrollResult = "Enrollment failed: Programming I has not been passed";
        }
      } else {
        if (courseName.equals("Spanish")) {
          if (type.equals(STUDENT_TYPE.VISITING)) {
            if (!currentCourses.contains(courseName)) {
              courseCatalog.enrollStudent(identifier, courseName);
              currentCourses.add(courseName);
              enrollResult = "Enrollment successful";
            } else {
              enrollResult = "Enrollment failed: Already enrolled";
            }
          } else {
            enrollResult = "Enrollment failed: The student must be a visiting student";
          }
        } else {
          if (!currentCourses.contains(courseName)) {
            courseCatalog.enrollStudent(identifier, courseName);
            currentCourses.add(courseName);
            enrollResult = "Enrollment successful";
          } else {
            enrollResult = "Enrollment failed: Already enrolled";
          }
        }
      }
    } else {
      enrollResult = "Enrollment failed: the course is not in the courses catalog";
    }
    return (enrollResult);
  }
}
