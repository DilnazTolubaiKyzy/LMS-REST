package com.peaksoft.dto;

import com.peaksoft.entity.Course;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter@Setter
public class GroupResponse {
    private Long id;
    private String groupName;
    private String dateOfStart;
    private String dateOfFinish;
    // private List<Course> course;
}
