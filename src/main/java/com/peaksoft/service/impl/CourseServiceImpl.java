package com.peaksoft.service.impl;

import com.peaksoft.dto.CourseRequest;
import com.peaksoft.dto.CourseResponse;
import com.peaksoft.dto.GroupResponse;
import com.peaksoft.dto.TeacherResponse;
import com.peaksoft.entity.Company;
import com.peaksoft.entity.Course;
import com.peaksoft.entity.Group;
import com.peaksoft.entity.User;
import com.peaksoft.repository.CompanyRepository;
import com.peaksoft.repository.CourseRepository;
import com.peaksoft.repository.UserRepository;
import com.peaksoft.service.CourseService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
@Service
@RequiredArgsConstructor
public class CourseServiceImpl implements CourseService {
    private final CourseRepository courseRepository;
    private final CompanyRepository companyRepository;
    private final TeacherServiceImpl teacherService;
    private final GroupServiceImpl groupService;


    @Override
    public List<CourseResponse> getAll() {
        List<Course> courses = courseRepository.findAll();
        List<CourseResponse> responses  = new ArrayList<>();
        for (Course course : courses) {
           responses.add(mapToResponse(course));
        }
        return responses;
    }

    @Override
    public CourseResponse save(CourseRequest course ) {
        Course course1 = mapToEntity(course);
        courseRepository.save(course1);
        return mapToResponse(course1);
    }

    @Override
    public CourseResponse update(Long id, CourseRequest course) {
        Course course1 = courseRepository.findById(id).get();
        course1.setCourseName(course.getCourseName());
        course1.setDurationMonth(course.getDurationMonth());
        if (course.getCompanyId() != null){
            Company company = companyRepository.findById(course.getCompanyId()).get();
            course1.setCompany(company);
        }
        courseRepository.saveAndFlush(course1);
        return mapToResponse(course1);
    }

    @Override
    public CourseResponse getById(Long id) {
        Course course = courseRepository.findById(id).get();
        return mapToResponse(course);
    }

    @Override
    public void delete(Long id) {
       Course course = courseRepository.findById(id).get();
       courseRepository.delete(course);
    }

    @Override
    public List<TeacherResponse> getTeachersByCourseId(Long id, int page, int size) {
        Pageable pageable = PageRequest.of(size-1, page);
        List<User> teachers = courseRepository.getTeachersByCourseId(id,pageable);
        List<TeacherResponse> teacherResponses = new ArrayList<>();
        for (User teacher : teachers) {
            teacherResponses.add(teacherService.mapToResponse(teacher));
        }
        return teacherResponses;
    }

    @Override
    public List<GroupResponse> getGroupsByCourseId(Long id, int page, int size) {
        Pageable pageable = PageRequest.of(size-1, page);
        List<Group> groups = courseRepository.getGroupsByCourseId(id, pageable);
        List<GroupResponse> groupResponses = new ArrayList<>();
        for (Group group : groups) {
            groupResponses.add(groupService.mapToResponse(group));
        }
        return groupResponses;
    }

    private Course mapToEntity(CourseRequest request){
        Course course = new Course();
        course.setCourseName(request.getCourseName());
        course.setDurationMonth(request.getDurationMonth());
        if (request.getCompanyId() != null) {
            Company company = companyRepository.findById(request.getCompanyId()).get();
            course.setCompany(company);
        }
        return course;
    }
    public CourseResponse mapToResponse(Course course){
        CourseResponse response = new CourseResponse();
        response.setId(course.getId());
        response.setCourseName(course.getCourseName());
        response.setDurationMonth(course.getDurationMonth());
       // response.setCompany(course.getCompany());
        return response;
    }
}
