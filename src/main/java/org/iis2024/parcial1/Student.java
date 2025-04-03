package org.iis2024.parcial1;

import java.util.ArrayList;
import java.util.List;

/** Students in an educational institution */
public class Student {

  public static final String ENROLLMENT_SUCCESSFUL = "Enrollment successful";
  public static final String ENROLLMENT_FAILED_ALREADY_ENROLLED = "Enrollment failed: Already enrolled";
  public static final String PROGRAMMING_II = "Programming II";
  public static final String PROGRAMMING_I = "Programming I";

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
    if (!courseCatalog.contains(courseName)) {
      enrollResult = "Enrollment failed: the course is not in the courses catalog";
    } else {
      if(currentCourses.contains(courseName)) {
        enrollResult = ENROLLMENT_FAILED_ALREADY_ENROLLED;
      // Comprueba si la asignatura a la que se quiere apuntar es Programming 2 y si puede hacerlo
      }else if(isProgramming(courseName)) {
        enrollResult = "Enrollment failed: Programming I has not been passed";
      // Comprueba si la asignatura a la que se quiere apuntar es Spanish y si puede hacerlo
      } else if (isSpanish(courseName)) {
        enrollResult = "Enrollment failed: The student must be a visiting student";
      // Comprueba si la asignatura a la que se quiere apuntar es otra cualquiera y si puede hacerlo
      }else{
        courseCatalog.enrollStudent(identifier, courseName);
        currentCourses.add(courseName);
        enrollResult = ENROLLMENT_SUCCESSFUL;
      }
    }
    return (enrollResult);
  }

  private boolean isSpanish(String courseName) {
    return courseName.equals("Spanish") && type.equals(STUDENT_TYPE.REGULAR);
  }

  private boolean isProgramming(String courseName) {
    return courseName.equals(PROGRAMMING_II) && !passedCourses.contains(PROGRAMMING_I);
  }
}
