package com.stswengists;

import org.junit.jupiter.api.*;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

class StudentTest {

    @Test
    void enlist_two_section_no_conflict() {

        Student student = new Student(1, Collections.emptyList());
        Section sec1 = new Section("A", new Schedule(Days.MTH, Period.H0830), new Room("S12", 3));
        Section sec2 = new Section("B", new Schedule(Days.TF, Period.H1000), new Room("S12", 3));

        student.enlist(sec1);
        student.enlist(sec2);

        Collection<Section> sections = student.getSections();
        List<Section> compareList = Collections.unmodifiableList(Arrays.asList(sec1, sec2));
        assertAll(
                () -> assertTrue(sections.containsAll(compareList)),
                () -> assertEquals(2, sections.size())
        );
    }

    @Test
    void enlist_two_section_same_schedule() {
        Student student = new Student(1, Collections.emptyList());
        Section sec1 = new Section("A", new Schedule(Days.MTH,Period.H0830), new Room("S12", 3));
        Section sec2 = new Section("B", new Schedule(Days.MTH,Period.H0830), new Room("S12", 3));

        student.enlist(sec1);

        assertThrows(Exception.class,() -> student.enlist(sec2));
    }




    @Test
    void enlist_section_full_slots(){

        // enlisting students to increase current capacity
        Section sec1 = new Section("A", new Schedule(Days.MTH,Period.H0830), new Room("S12", 3));

        Student student1 = new Student(1, Collections.emptyList());
        Student student2 = new Student(2, Collections.emptyList());
        Student student3 = new Student(3, Collections.emptyList());

        student1.enlist(sec1);
        student2.enlist(sec1);
        student3.enlist(sec1);


       // Given a student who enlisted in a full room
        Student student = new Student(4, Collections.emptyList());

        // An exception should be thrown on the 4th enlistment
        assertThrows(Exception.class,() -> student.enlist(sec1));

    }

    @Test
    void enlist_section_with_available_slots(){

        // enlisting students to increase current capacity
        Section section = new Section("SECTIONID", new Schedule(Days.TF,Period.H1430), new Room("S15", 6));

        Student student1 = new Student(1, Collections.emptyList());
        Student student2 = new Student(2, Collections.emptyList());
        Student student3 = new Student(3, Collections.emptyList());
        Student student4 = new Student(4, Collections.emptyList());

        student1.enlist(section);
        student2.enlist(section);
        student3.enlist(section);
        student4.enlist(section);

        // given a new student who wants to enlist in the section
        Student addedStudent = new Student(5, Collections.emptyList());

        // The newly added student should be enlisted successfully since there is still capacity in the room
        assertAll(() -> addedStudent.enlist(section));
    }

    @Test
    void cancel_enlisted_section(){

        Section section = new Section("A", new Schedule(Days.MTH, Period.H0830), new Room("S12", 3));
        Student student =  new Student(1, Collections.emptyList());

        //enlisting student in the section
        student.enlist(section);

        // The student should be able to successfully cancel his/her enlistment for the section
        assertAll(() -> student.cancelEnlist(section));
    }

    @Test
    void cancel_section_not_enlisted_yet(){
        Section section1 = new Section("A", new Schedule(Days.MTH, Period.H0830), new Room("S12", 3));
        Section section2 = new Section("B", new Schedule(Days.TF, Period.H1000), new Room("S12", 3));
        Section section3 = new Section("C", new Schedule(Days.MTH, Period.H1300), new Room("S12", 3));

        Student student =  new Student(1, Collections.emptyList());

        // Enlisting student in sections 1 and 2, but not 3
        student.enlist(section1);
        student.enlist(section2);

        // An exception should be thrown since student has not enlisted in section3 yet
        assertThrows(Exception.class, () -> student.cancelEnlist(section3));
    }

    @Test
    void enlist_students_at_capacity_in_two_sections_sharing_the_same_room() {
        // Given 2 sections that share same room w/ capacity 1, and 2 students
        final int CAPACITY = 1;
        Room room = new Room("X", CAPACITY);
        Section sec1 = new Section("A", new Schedule(Days.MTH, Period.H0830), room);
        Section sec2 = new Section("B", new Schedule(Days.TF, Period.H0830), room);
        Student student1 = new Student(1, Collections.emptyList());
        Student student2 = new Student(2, Collections.emptyList());
        // When each student enlists in a different section
        student1.enlist(sec1);
        student2.enlist(sec2);
        // No exception should be thrown
    }

}

