package com.peaksoft.service.impl;

import com.peaksoft.dto.CourseResponse;
import com.peaksoft.dto.GroupRequest;
import com.peaksoft.dto.GroupResponse;
import com.peaksoft.dto.StudentResponse;
import com.peaksoft.entity.Course;
import com.peaksoft.entity.Group;
import com.peaksoft.entity.Role;
import com.peaksoft.entity.User;
import com.peaksoft.repository.CourseRepository;
import com.peaksoft.repository.GroupRepository;
import com.peaksoft.repository.UserRepository;
import com.peaksoft.service.GroupService;
import lombok.RequiredArgsConstructor;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
@Service
@RequiredArgsConstructor
public class GroupServiceImpl implements GroupService {
    private final GroupRepository groupRepository;
    private final CourseRepository courseRepository;
    private final StudentServiceImpl studentService;
    @Override
    public List<GroupResponse> getAll() {
        List<Group> groups = groupRepository.findAll();
        List<GroupResponse> responses = new ArrayList<>();
        for (Group group : groups) {
            responses.add(mapToResponse(group));
        }
        return responses;
    }

    @Override
    public GroupResponse save(GroupRequest group) {
        Group group1 = mapToEntity(group);
        groupRepository.save(group1);
        return mapToResponse(group1);
    }

    @Override
    public GroupResponse update(Long id, GroupRequest group) {
        Group group1 = groupRepository.findById(id).get();
        group1.setGroupName(group.getGroupName());
        group1.setDateOfStart(group.getDateOfStart());
        group1.setDateOfFinish(group.getDateOfFinish());
        if (group.getCourseId() != null){
            List<Course> courses =new ArrayList<>();
            Course course = courseRepository.findById(group.getCourseId()).get();
            courses.add(course);
            group1.setCourse(courses);
        }
        groupRepository.saveAndFlush(group1);
        return mapToResponse(group1);
    }

    @Override
    public GroupResponse getById(Long id) {
        Group group = groupRepository.findById(id).get();
        return mapToResponse(group);
    }

    @Override
    public void delete(Long id) {
        Group group = groupRepository.findById(id).get();
        groupRepository.delete(group);
    }

    @Override
    public List<StudentResponse> getStudentsByGroupId(Long id, int page, int size) {
        Pageable pageable = PageRequest.of(size-1,page);
        List<User> students = groupRepository.getStudentsByGroupId(id,pageable);
        List<StudentResponse> studentResponses = new ArrayList<>();
        for (User student : students) {
            if (student.getRole().equals(Role.STUDENT)){
                studentResponses.add(studentService.mapToResponse(student));
            }
        }
        return studentResponses;
    }

    @Override
    public List<CourseResponse> getCoursesByGroupId(Long id, int page, int size) {
        Pageable pageable = PageRequest.of(size-1, page);
        List<Course> courses = groupRepository.getCoursesByGroupId(id,pageable);
        List<CourseResponse> courseResponses = new ArrayList<>();
        for (Course course : courses) {
            courseResponses.add(mapToResponse(course));
        }
        return courseResponses;
    }



    private Group mapToEntity(GroupRequest request){
        Group group = new Group();
        group.setGroupName(request.getGroupName());
        group.setDateOfStart(request.getDateOfStart());
        group.setDateOfFinish(request.getDateOfFinish());
        Course course =courseRepository.findById(request.getCourseId()).get();
        if ((request.getCourseId() != null)) {
            List<Course> courses = new ArrayList<>();
            courses.add(course);
            group.setCourse(courses);
        }
        return group;
    }
    public GroupResponse mapToResponse(Group group){
        GroupResponse response = new GroupResponse();
        response.setId(group.getId());
        response.setGroupName(group.getGroupName());
        response.setDateOfStart(group.getDateOfStart());
        response.setDateOfFinish(group.getDateOfFinish());
      // response.setCourse(group.getCourse());
        return response;
    }
    public CourseResponse mapToResponse(Course course){
        CourseResponse response = new CourseResponse();
        response.setId(course.getId());
        response.setCourseName(course.getCourseName());
        response.setDurationMonth(course.getDurationMonth());
      //  response.setCompany(course.getCompany());
        return response;
    }
}
