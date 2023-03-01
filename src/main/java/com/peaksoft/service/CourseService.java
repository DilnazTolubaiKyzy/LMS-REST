package com.peaksoft.service;

import com.peaksoft.dto.CourseRequest;
import com.peaksoft.dto.CourseResponse;
import com.peaksoft.dto.GroupResponse;
import com.peaksoft.dto.TeacherResponse;
import com.peaksoft.entity.Course;
import com.peaksoft.entity.Group;

import java.util.List;

public interface CourseService {
    List<CourseResponse> getAll();
    CourseResponse save(CourseRequest course);
    CourseResponse update(Long id, CourseRequest course);
    CourseResponse getById(Long id);
    void delete(Long id);
    List<TeacherResponse> getTeachersByCourseId(Long id, int page, int size);
    List<GroupResponse> getGroupsByCourseId(Long id, int page, int size);

}
