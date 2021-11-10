package com.stswengists;

import java.util.Objects;

import static org.apache.commons.lang3.StringUtils.isAlphanumeric;
import static org.apache.commons.lang3.Validate.isTrue;
import static org.apache.commons.lang3.Validate.notBlank;

class Section {

     private final String sectionId;
     private Schedule schedule;
     private Room room;

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


     public Room getRoom(){ return room; }

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
