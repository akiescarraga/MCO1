package com.stswengists;

import static org.apache.commons.lang3.StringUtils.*;
import static org.apache.commons.lang3.Validate.*;

import java.util.Objects;

class Section {

     private final String sectionId;
     private Schedule schedule;

     Section(String sectionId, Schedule schedule) {
         notBlank(sectionId,
                 "sectionId can't be null, empty or whitespace");

         isTrue(isAlphanumeric(sectionId),
                 "sectionId must be alphanumeric, was:" + sectionId);

         this.sectionId = sectionId;
         this.schedule = schedule;
     }

     boolean hasConflict(Section otherSection) {
         return this.schedule.equals(otherSection.getSchedule());
     }

     void checkForConflict(Section other) {
         if (this.schedule.equals(other.getSchedule())) {
             throw new ScheduleConflictException(
                     "schedule conflict between current section " + this.getSchedule() +
                             " and new section " + other + " at schedule " + other.getSchedule()
             );
         }
     }

     public Schedule getSchedule() {
         return schedule;
     }

     @Override
     public String toString(){
         return sectionId;
     }

     @Override
     public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Section section = (Section) o;
        return Objects.equals(sectionId, section.sectionId);
     }

    @Override
    public int hashCode() {
        return Objects.hash(sectionId);
    }
}
