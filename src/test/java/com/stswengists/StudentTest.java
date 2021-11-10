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
        addedStudent.enlist(section);

        // The newly added student should be enlisted successfully since there is still capacity in the room
        assertAll(() -> section.getRoom().checkCapacity());
    }

    @Test
    void cancel_enlisted_section(){
        Section section = new Section("A", new Schedule(Days.MTH, Period.H0830), new Room("S12", 3));
        Student student =  new Student(1, Collections.emptyList());

        student.enlist(section);

        assertAll(() -> student.cancelEnlist(section));
    }

}

