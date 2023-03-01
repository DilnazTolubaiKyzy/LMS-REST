package com.peaksoft.service;

import com.peaksoft.dto.*;
import com.peaksoft.entity.Course;

import javax.swing.event.ListDataEvent;
import java.util.List;

public interface TeacherService {
    List<TeacherResponse> getAllTeacher();
    TeacherResponse save(TeacherRequest teacher);
    TeacherResponse update(Long id, TeacherRequest request);
   TeacherResponse getById(Long id);
    void delete(Long id);
    List<CourseResponse> getCoursesByTeacherId(Long id, int page, int size);

    List<StudentResponse>  quantityOfStudents(Long id, int page, int size);


}
