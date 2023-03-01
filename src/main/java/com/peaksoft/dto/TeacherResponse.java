package com.peaksoft.dto;

import com.peaksoft.entity.Course;
import com.peaksoft.entity.Group;
import com.peaksoft.entity.Role;
import com.peaksoft.entity.StudyFormat;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
@Getter@Setter
public class TeacherResponse {
    private Long id;
    private String email;
    private String firstName;
    private String lastName;
    private LocalDate created;
    private Role role;
//    private Course course;

}
