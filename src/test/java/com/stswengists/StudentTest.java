package com.stswengists;

import org.junit.jupiter.api.*;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

class StudentTest {

    @Test
    void enlist_two_section_no_conflict() {

        Student student = new Student(1, Collections.emptyList());
        Section sec1 = new Section("A", new Schedule(Days.MTH, Period.H0830));
        Section sec2 = new Section("B", new Schedule(Days.TF, Period.H1000));

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
        Section sec1 = new Section("A", new Schedule(Days.MTH,Period.H0830));
        Section sec2 = new Section("B", new Schedule(Days.MTH,Period.H0830));

        student.enlist(sec1);

        assertThrows(Exception.class,() -> student.enlist(sec2));
    }

}
