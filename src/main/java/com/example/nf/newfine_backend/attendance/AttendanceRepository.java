package com.example.nf.newfine_backend.attendance;

import org.springframework.data.jpa.repository.JpaRepository;
import org.yaml.snakeyaml.events.Event;

import java.util.List;

public interface AttendanceRepository extends JpaRepository <Attendance,Long> {

}

