package com.peaksoft.service;

import com.peaksoft.dto.CourseResponse;
import com.peaksoft.dto.GroupRequest;
import com.peaksoft.dto.GroupResponse;
import com.peaksoft.dto.StudentResponse;


import java.util.List;

public interface GroupService {
    List<GroupResponse> getAll();
    GroupResponse save(GroupRequest group);
    GroupResponse update(Long id, GroupRequest group);
    GroupResponse getById(Long id);
    void delete(Long id);
    List<StudentResponse> getStudentsByGroupId(Long id, int page, int size);
    List<CourseResponse> getCoursesByGroupId(Long id, int page, int size);

}
