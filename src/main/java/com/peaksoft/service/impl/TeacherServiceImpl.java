package com.peaksoft.service.impl;

import com.peaksoft.dto.*;
import com.peaksoft.entity.*;
import com.peaksoft.repository.CourseRepository;
import com.peaksoft.repository.UserRepository;
import com.peaksoft.service.CourseService;
import com.peaksoft.service.StudentService;
import com.peaksoft.service.TeacherService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
@Service
@RequiredArgsConstructor
public class TeacherServiceImpl implements TeacherService {
    private final UserRepository userRepository;
    private  final PasswordEncoder passwordEncoder;
    private final CourseRepository courseRepository;
    private final StudentServiceImpl studentService;
    @Override
    public List<TeacherResponse> getAllTeacher() {
        List<User > users = userRepository.findAll();
        List<TeacherResponse> responses = new ArrayList<>();
        for (User user : users) {
            if(user.getRole().equals(Role.INSTRUCTOR)) {
                responses.add(mapToResponse(user));
            }
        }
        return responses;
    }

    @Override
    public TeacherResponse save(TeacherRequest teacher) {
        User user = mapToEntity(teacher);
        userRepository.save(user);
        return mapToResponse(user);
    }

    @Override
    public TeacherResponse update(Long id, TeacherRequest request) {
        User user = userRepository.findById(id).get();
        user.setEmail(request.getEmail());
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        user.setFirstName(request.getFirstName());
        user.setLastName(request.getLastName());
        if (request.getCourseId()!=null) {
            Course course = courseRepository.findById(request.getCourseId()).get();
            user.setCourse(course);
        }
        userRepository.saveAndFlush(user);
        return mapToResponse(user);
    }

    @Override
    public TeacherResponse getById(Long id) {
        User user = userRepository.findById(id).get();
        return mapToResponse(user);
    }

    @Override
    public void delete(Long id) {
      User user = userRepository.findById(id).get();
      userRepository.delete(user);
    }

    @Override
    public List<CourseResponse> getCoursesByTeacherId(Long id, int page, int size) {
        Pageable pageable = PageRequest.of(page-1,size);
        List<Course> courses =  userRepository.getCourseByTeacherId(id,pageable);
        List<CourseResponse>courseResponses = new ArrayList<>();
        for (Course course : courses) {
            courseResponses.add(mapToResponse(course));
        }
        return courseResponses;
    }

    @Override
    public List<StudentResponse> quantityOfStudents(Long id, int page, int size) {
        Pageable pageable = PageRequest.of(page-1,size);
        List<User>students = userRepository.getStudentsById(id,pageable);
        List<StudentResponse>studentResponses = new ArrayList<>();
        for (User student : students) {
            if(student.getRole().equals(Role.STUDENT))
                studentResponses.add(studentService.mapToResponse(student));
        }
        return studentResponses;

    }

    private User mapToEntity(TeacherRequest request){
        User user = new User();
        user.setEmail(request.getEmail());
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        user.setFirstName(request.getFirstName());
        user.setLastName(request.getLastName());
        user.setRole(Role.INSTRUCTOR);
        user.setCreatedDate(LocalDate.now());
        if (request.getCourseId() != null) {
            Course course = courseRepository.findById(request.getCourseId()).get();
            user.setCourse(course);
        }
        return user;
    }
    public TeacherResponse mapToResponse(User user){
        TeacherResponse response = new TeacherResponse();
        response.setId(user.getId());
        response.setEmail(user.getEmail());
        response.setFirstName(user.getFirstName());
        response.setLastName(user.getLastName());
        response.setRole(user.getRole());
        response.setCreated(user.getCreatedDate());
      //  response.setCourse(user.getCourse());
        return response;
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
