package org.iis2024.parcial1;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/** Unit test cases for class Student */
class StudentTest {
  private CourseCatalog courseCatalog;

  @BeforeEach
  void setUp() {
    courseCatalog = new CourseCatalog();
  }

  @AfterEach
  void tearDown() {
    courseCatalog = null;
  }

  @Test
  void givenANonExistingCourseThenEnrollReturnsFail() {
    // Arrange
    String courseName = "Physics";
    Student student = new Student("Mario", Student.STUDENT_TYPE.REGULAR, 1);

    // Act
    String enrollmentResult = student.enroll(courseName, courseCatalog);

    // Assert
    String expectedResult = "Enrollment failed: the course is not in the courses catalog";
    assertEquals(expectedResult, enrollmentResult);
  }

  @Test
  void givenAStudentToEnrollInProgrammingIIWhenPassedProgrammingIThenEnrollmentIsSuccessful() {
    // Arrange
    String courseName = "Programming II";
    Student student = new Student("Mario", Student.STUDENT_TYPE.REGULAR, 1);
    student.passedCourses.add("Programming I");
    Course courseProgrammingI = courseCatalog.getCourse("Programming I");
    courseProgrammingI.enroll(student.identifier);

    // Act
    String enrollmentResult = student.enroll(courseName, courseCatalog);

    // Assert
    String expectedResult = "Enrollment successful";
    assertEquals(expectedResult, enrollmentResult);
    assertTrue(courseProgrammingI.isEnrolled(student.identifier));
  }

  @Test
  void givenAStudentToEnrollInProgrammingIIWhenAlreadyEnrolledThenEnrollFails() {
    // Arrange
    String courseName = "Programming II";
    Student student = new Student("Mario", Student.STUDENT_TYPE.REGULAR, 1);
    student.passedCourses.add("Programming I");
    Course courseProgrammingI = courseCatalog.getCourse("Programming I");
    courseProgrammingI.enroll(student.identifier);
    student.enroll(courseName, courseCatalog);

    // Act
    String enrollmentResult = student.enroll(courseName, courseCatalog);

    // Assert
    String expectedResult = "Enrollment failed: Already enrolled";
    assertEquals(expectedResult, enrollmentResult);
  }

  @Test
  void givenAStudentToEnrollInProgrammingIIWhenNotPassedProgrammingIThenEnrollFails() {
    // Arrange
    String courseName = "Programming II";
    Student student = new Student("Mario", Student.STUDENT_TYPE.REGULAR, 1);

    // Act
    String enrollmentResult = student.enroll(courseName, courseCatalog);

    // Assert
    String expectedResult = "Enrollment failed: Programming I has not been passed";
    assertEquals(expectedResult, enrollmentResult);
  }

  @Test
  void
      givenAVisitingStudentToEnrollInSpanishWhenNotPreviouslyEnrolledThenProgrammingIThenEnrollmentIsSuccessful() {
    // Arrange
    String courseName = "Spanish";
    Student student = new Student("Mary", Student.STUDENT_TYPE.VISITING, 2);

    // Act
    String enrollmentResult = student.enroll(courseName, courseCatalog);

    // Assert
    String expectedResult = "Enrollment successful";
    assertEquals(expectedResult, enrollmentResult);
  }

  @Test
  void givenAVisitingStudentToEnrollInSpanishWhenPreviouslyEnrolledThenEnrollFails() {
    // Arrange
    String courseName = "Spanish";
    Student student = new Student("Mary", Student.STUDENT_TYPE.VISITING, 2);
    student.enroll(courseName, courseCatalog);

    // Act
    String enrollmentResult = student.enroll(courseName, courseCatalog);

    // Assert
    String expectedResult = "Enrollment failed: Already enrolled";
    assertEquals(expectedResult, enrollmentResult);
  }

  @Test
  void givenARegularStudentToEnrollInSpanishThenEnrollFails() {
    // Arrange
    String courseName = "Spanish";
    Student student = new Student("Mario", Student.STUDENT_TYPE.REGULAR, 1);

    // Act
    String enrollmentResult = student.enroll(courseName, courseCatalog);

    // Assert
    String expectedResult = "Enrollment failed: The student must be a visiting student";
    assertEquals(expectedResult, enrollmentResult);
  }

  @Test
  void
      givenARegularStudentToEnrollInAnyCourseWhenNotPreviouslyEnrolledThenEnrollmentIsSuccessful() {
    // Arrange
    String courseName = "Programming I";
    Student student = new Student("Luisa", Student.STUDENT_TYPE.REGULAR, 3);

    // Act
    String enrollmentResult = student.enroll(courseName, courseCatalog);

    // Assert
    String expectedResult = "Enrollment successful";
    assertEquals(expectedResult, enrollmentResult);
  }

  @Test
  void givenARegularStudentToEnrollInAnyCourseWhenPreviouslyEnrolledThenEnrollFails() {
    // Arrange
    String courseName = "Programming I";
    Student student = new Student("Luisa", Student.STUDENT_TYPE.REGULAR, 3);
    student.enroll(courseName, courseCatalog);
    Course courseProgrammingI = courseCatalog.getCourse("Programming I");
    courseProgrammingI.enroll(student.identifier);

    // Act
    String enrollmentResult = student.enroll(courseName, courseCatalog);

    // Assert
    String expectedResult = "Enrollment failed: Already enrolled";
    assertEquals(expectedResult, enrollmentResult);
  }
}
