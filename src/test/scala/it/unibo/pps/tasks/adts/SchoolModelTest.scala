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