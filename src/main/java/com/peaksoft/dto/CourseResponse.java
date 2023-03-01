package com.peaksoft.dto;

import com.peaksoft.entity.Company;
import lombok.Getter;
import lombok.Setter;

@Getter@Setter
public class CourseResponse {
    private Long id;
    private String courseName;
    private String durationMonth;
    //private Company company;
}
