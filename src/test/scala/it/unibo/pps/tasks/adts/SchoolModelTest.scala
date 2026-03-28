package it.unibo.pps.tasks.adts

import org.junit.*
import org.junit.Assert.*
import it.unibo.pps.u03.extensionmethods.Sequences.Sequence, Sequence.*
import it.unibo.pps.tasks.adts.SchoolModel.*

class SchoolModelTest:

  @Test def testEmptySchool(): Unit =
    val school = BasicSchoolModule.emptySchool
    assertEquals(Nil(), school.teachers)
    assertEquals(Nil(), school.courses)
    assertFalse(school.hasTeacher("Alice"))
    assertFalse(school.hasCourse("Math"))

  @Test def testAddTeacherAndCourse(): Unit =
    val empty = BasicSchoolModule.emptySchool
    val alice = BasicSchoolModule.teacher("Alice")
    val math = BasicSchoolModule.course("Math")
    val school = empty.setTeacherToCourse(alice, math)
    assertTrue(school.hasTeacher("Alice"))
    assertTrue(school.hasCourse("Math"))

  @Test def testCoursesOfATeacher(): Unit =
    val alice = BasicSchoolModule.teacher("Alice")
    val bob = BasicSchoolModule.teacher("Bob")
    val math = BasicSchoolModule.course("Math")
    val science = BasicSchoolModule.course("Science")
    val school = BasicSchoolModule.emptySchool
      .setTeacherToCourse(alice, math)
      .setTeacherToCourse(alice, science)
      .setTeacherToCourse(bob, science)
    val expectedAliceCourses = Cons(science, Cons(math, Nil()))
    assertEquals(expectedAliceCourses, school.coursesOfATeacher(alice))
    val expectedBobCourses = Cons(science, Nil())
    assertEquals(expectedBobCourses, school.coursesOfATeacher(bob))

  @Test def testExistingCoursesWithoutDuplicates(): Unit =
    val alice = BasicSchoolModule.teacher("Alice")
    val bob = BasicSchoolModule.teacher("Bob")
    val math = BasicSchoolModule.course("Math")
    val science = BasicSchoolModule.course("Science")
    val school = BasicSchoolModule.emptySchool
      .setTeacherToCourse(alice, math)
      .setTeacherToCourse(bob, math)
      .setTeacherToCourse(alice, science)
    val expectedTeachers = Cons("Science", Cons("Math", Nil()))
    assertEquals(expectedTeachers, school.courses)

  @Test def testExistingTeachersWithoutDuplicates() : Unit =
    val alice = BasicSchoolModule.teacher("Alice")
    val bob = BasicSchoolModule.teacher("Bob")
    val math = BasicSchoolModule.course("Math")
    val science = BasicSchoolModule.course("Science")
    val school = BasicSchoolModule.emptySchool
      .setTeacherToCourse(alice, math)
      .setTeacherToCourse(bob, math)
      .setTeacherToCourse(alice, science)
    val expectedTeachers = Cons("Bob", Cons("Alice", Nil()))
    assertEquals(expectedTeachers, school.teachers)