package com.example.nf.newfine_backend.attendance;

import com.example.nf.newfine_backend.attendance.domain.Attendance;
import com.example.nf.newfine_backend.attendance.domain.StudentAttendance;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Comparator;

public class DateComparator implements Comparator<Attendance> {
    @Override
    public int compare(Attendance o1, Attendance o2) {

        LocalDateTime date1=((Attendance)o1).getStartTime();
        LocalDateTime date2=((Attendance)o2).getStartTime();

        return date1.compareTo(date2);
    }
}
