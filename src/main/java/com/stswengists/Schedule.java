package com.stswengists;

import static org.apache.commons.lang3.Validate.notNull;

enum Days {
    // Mon/Thu, Tue/Fri, Wed/Sat
    MTH, TF, WS
}

enum Period {
    // 8:30am-10am, 10am-11:30, 11:30am-1pm, 1pm-2:30pm, 2:30pm-4pm, 4pm-5:30pm
    H0830, H1000, H1130, H1300, H1430, H1600
}

class Schedule {
    private final Days days;
    private final Period period;

    Schedule(Days days, Period period) {
        notNull(days);
        notNull(period);
        this.days = days;
        this.period = period;
    }

    @Override
    public String toString() { return days + " " + period; }

    @Override
    public boolean equals(Object obj) {

        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;

        Schedule schedule = (Schedule) obj;

        if(days != schedule.days) return false;

        return period == schedule.period;


    }

    @Override
    public int hashCode() {

        int result = days != null ? days.hashCode() : 0;
        result = 31 * result + ( period != null ? period.hashCode() : 0);
        return result;


    }

}