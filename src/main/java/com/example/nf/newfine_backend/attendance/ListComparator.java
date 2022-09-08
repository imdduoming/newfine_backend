package com.example.nf.newfine_backend.attendance;

import com.example.nf.newfine_backend.attendance.domain.StudentAttendance;
import com.example.nf.newfine_backend.course.Listener;

import java.util.Comparator;

public class ListComparator implements Comparator<StudentAttendance> {
    @Override
    public int compare(StudentAttendance o1, StudentAttendance o2) {

        String name1=((StudentAttendance)o1).getStudent().getName();
        String name2=((StudentAttendance)o2).getStudent().getName();

        return name1.compareTo(name2);
    }
}
